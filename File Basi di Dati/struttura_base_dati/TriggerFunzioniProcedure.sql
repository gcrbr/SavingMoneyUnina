--Funzione calcola_saldo_totale
CREATE FUNCTION "SavingMoneyUnina".calcola_saldo_totale(emailutente character varying) RETURNS double precision
    LANGUAGE plpgsql
    AS $$
	declare 
		totale float8;
	BEGIN
		totale := 0;
		select SUM(saldo)
		from "SavingMoneyUnina".contocorrente
		into totale
		where email=emailutente;
		
		return totale;
	END;
$$;

--Procedura crea_nuovo_conto
CREATE PROCEDURE "SavingMoneyUnina".crea_nuovo_conto(IN iban character varying, IN saldo double precision, IN num_carta character varying, IN scadenza date, IN cvv character varying, IN tipo_carta character varying, IN limite double precision, IN email character varying)
    LANGUAGE plpgsql
    AS $$		
	begin
		if tipo_carta = 'credito' then
			insert into "SavingMoneyUnina".carta VALUES(num_carta, scadenza, cvv, limite, 0, tipo_carta);
		end if;
		if tipo_carta = 'debito' then
			insert into "SavingMoneyUnina".carta VALUES(num_carta, scadenza, cvv, 0, limite, tipo_carta);
		end if;
		insert into "SavingMoneyUnina".contocorrente VALUES(saldo, iban, email, num_carta);
	END;
$$;

--Funzione get_parolechiave_string
CREATE FUNCTION "SavingMoneyUnina".get_parolechiave_string(portafogli integer) RETURNS character varying
    LANGUAGE plpgsql
    AS $$
	declare
		result varchar;
		parolatrovata record;
	BEGIN
		for parolatrovata in (select parola from "SavingMoneyUnina".parolachiave where idportafogli=portafogli) loop
			result := concat(result, parolatrovata.parola, ', ');
		end loop;
		result := rtrim(result, ', ');
		return result;
	END;
$$;

--Funzione inserisci_parolechiave_multiple
CREATE PROCEDURE "SavingMoneyUnina".inserisci_parolechiave_multiple(IN portafogli integer, IN lista character varying)
    LANGUAGE plpgsql
    AS $$
	DECLARE
	parola varchar;
	contatore int4;
BEGIN
	contatore := 1;
	parola := SPLIT_PART(lista, ',', 1);
	
	WHILE parola <> '' loop
		INSERT INTO "SavingMoneyUnina".parolachiave(idportafogli, parola) VALUES(portafogli, parola);
		contatore := contatore + 1;
		parola := SPLIT_PART(lista, ',', contatore);
	END LOOP;

	return;
	END;
$$;

--Procedura inserisci_transazione
CREATE PROCEDURE "SavingMoneyUnina".inserisci_transazione(IN ibanconto character varying, IN tipo character varying, IN valore double precision, IN data date, IN descrizione character varying, IN altroiban character varying)
    LANGUAGE plpgsql
    AS $$
	declare 
		saldo_conto float8;
		numcarta varchar;
		tipo_carta varchar;
		plafond_carta varchar;
	BEGIN
		select saldo,numcarta
		from "SavingMoneyUnina".contocorrente
		into saldo_conto,numcarta
		where iban = ibanconto;
	
		select c.tipo,c.plafond
		from "SavingMoneyUnina".carta c
		into tipo_carta,plafond_carta
		where numero = numcarta;
	
		if tipo = 'uscita' then 
			if tipo_carta = 'debito' and valore > saldo_conto then
				raise exception 'Saldo insufficiente per completare la transazione';
			end if;
		end if;
	
		if valore < 0 then
			raise exception 'Importo invalido';
		end if;
	
		if ibanconto = altroiban then
			raise exception 'Non è possibile effettuare una transazione sullo stesso conto';
		end if;
	
		insert into "SavingMoneyUnina".transazione(valore, data, descrizione, tipo, altroiban, iban) VALUES(valore, data, descrizione, tipo, altroiban, ibanconto);
		
		if tipo = 'entrata' then
			update "SavingMoneyUnina".contocorrente
			set saldo = saldo + valore
			where iban=ibanconto;
		end if;
		if tipo = 'uscita' then
			update "SavingMoneyUnina".contocorrente
			set saldo = saldo - valore
			where iban=ibanconto;
		end if;
	END;
$$;

--Funzione report_mensile
CREATE FUNCTION "SavingMoneyUnina".report_mensile(mese integer, anno integer, utente character varying) RETURNS character varying
    LANGUAGE plpgsql
    AS $$
	declare
		risultato varchar;
	
		conto record;
	
		entrata_max float8;
		entrata_avg float8;
		entrata_min float8;
	
		uscita_max float8;
		uscita_avg float8;
		uscita_min float8;
	
		saldo_iniziale float8;
		saldo_finale float8;
	
		tr record;
	BEGIN
		for conto in (select * from "SavingMoneyUnina".ContoCorrente where email = utente) loop 
			
			entrata_max := 0;
			entrata_avg := 0;
			entrata_min := 0;
			uscita_max := 0;
			uscita_avg := 0;
			uscita_min := 0;
			saldo_iniziale := 0;
			saldo_finale := 0;
			
			select MAX(valore), AVG(valore), MIN(valore)
			from "SavingMoneyUnina".transazione
			into entrata_max, entrata_avg, entrata_min
			where iban = conto.iban and tipo = 'entrata' and extract(month from data) = mese and extract(year from data) = anno
			group by();
		
			select MAX(valore), AVG(valore), MIN(valore)
			from "SavingMoneyUnina".transazione
			into uscita_max, uscita_avg, uscita_min
			where iban = conto.iban and tipo = 'uscita' and extract(month from data) = mese and extract(year from data) = anno
			group by();
		
			for tr in (select * from "SavingMoneyUnina".transazione where iban = conto.iban and data <= concat(anno,'-',mese,'-','1')::date) loop 
				if tr.tipo = 'entrata' then
					saldo_iniziale := saldo_iniziale + tr.valore;
				else
					saldo_iniziale := saldo_iniziale - tr.valore;
				end if;
			end loop;
			
			for tr in (select * from "SavingMoneyUnina".transazione where iban = conto.iban and data <= (concat(anno,'-',mese,'-','1')::date + interval '1 month' - interval '1 day')) loop --ultimo giorno del mese, gli aggiungo 1 mese e gli tolgo 1 giorno
				if tr.tipo = 'entrata' then
					saldo_finale := saldo_finale + tr.valore;
				else
					saldo_finale := saldo_finale - tr.valore;
				end if;
			end loop;
			
			if entrata_max is null then
				entrata_max := 0;
			end if;
			if entrata_avg is null then
				entrata_avg := 0;
			end if;
			if entrata_min is null then
				entrata_min := 0;
			end if;
			if uscita_max is null then
				uscita_max := 0;
			end if;
			if uscita_avg is null then
				uscita_avg := 0;
			end if;
			if uscita_min is null then
				uscita_min := 0;
			end if;
		
			risultato := concat(risultato, conto.iban, ';', entrata_max, ';', entrata_avg, ';', entrata_min, ';', uscita_max, ';', uscita_avg, ';', uscita_min, ';', saldo_iniziale, ';', saldo_finale, ',');
		end loop;
		risultato := rtrim(risultato, ',');
		return risultato;
	END;
$$;

--Trigger limitespesa_plafond_check
CREATE TRIGGER limitespesa_plafond_check BEFORE INSERT ON "SavingMoneyUnina".transazione FOR EACH ROW EXECUTE FUNCTION "SavingMoneyUnina".limitespesa_plafond_check_f();

CREATE FUNCTION "SavingMoneyUnina".limitespesa_plafond_check_f() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
	DECLARE
	totale_speso float8;
	tipo_carta varchar;
	limite_spesa float8;
	plafond float8;
	
	numcarta varchar;
	saldoconto float8;
begin
	select numerocarta, saldo
	from "SavingMoneyUnina".contocorrente
	into numcarta, saldoconto
	where iban=new.iban;

	SELECT C.tipo, C.limitespesa, C.plafond
	FROM "SavingMoneyUnina".Carta as C
    INTO tipo_carta, limite_spesa, plafond
	WHERE numero=numcarta;
	
	if tipo_carta = 'debito' THEN
		SELECT SUM(valore) INTO totale_speso
		FROM "SavingMoneyUnina".Transazione
		WHERE tipo='uscita'
		GROUP BY ();
		
		if new.tipo = 'uscita' then
			totale_speso := totale_speso + new.valore;
		end if;
	
		IF totale_speso >= limite_spesa THEN
			RAISE EXCEPTION 'È stato superato il limite di spesa della carta associata';
		END IF;
	end if;

	IF tipo_carta = 'credito' then
		if new.tipo = 'uscita' then
			saldoconto := saldoconto - new.valore;
		end if;
	
		if new.tipo = 'entrata' then
			saldoconto := saldoconto + new.valore;
		end if;
	
		if saldoconto < 0 and saldoconto < -plafond THEN
			raise exception 'È stato superato il plafond';
		end if;
	end if;
	return new;
END;
$$;

--Trigger no_duplicati_portafogli
CREATE TRIGGER no_duplicati_portafogli BEFORE INSERT ON "SavingMoneyUnina".transazione_portafogli FOR EACH ROW EXECUTE FUNCTION "SavingMoneyUnina".no_duplicati_portafogli_f();

CREATE FUNCTION "SavingMoneyUnina".no_duplicati_portafogli_f() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
	declare
		f record;
	BEGIN
		for f in (select * from "SavingMoneyUnina".transazione_portafogli where idportafogli=new.idportafogli and idtransazione=new.idtransazione) loop 
			raise exception 'Questa transazione è già presente nel portafogli';			
		end loop;
		--il loop parte solo se trova un occorrenza esistente, quindi errore solo se trova duplicato
		return new;
	END;
$$;

--Trigger parolechiave_limite_check
CREATE TRIGGER parolechiave_check BEFORE INSERT ON "SavingMoneyUnina".parolachiave FOR EACH ROW EXECUTE FUNCTION "SavingMoneyUnina".parolechiave_limite_check_f();

CREATE FUNCTION "SavingMoneyUnina".parolechiave_limite_check_f() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
declare
	conteggioid_v int4;
begin
	conteggioid_v := 0;

	SELECT COUNT(*)
	INTO conteggioid_v
	FROM "SavingMoneyUnina".parolachiave
	WHERE idportafogli=NEW.idportafogli;

	IF  conteggioid_v >= 5 THEN
		RAISE EXCEPTION 'Ci sono già 5 parole';
	END IF;

	return NEW;
END;

--Trigger sincronizzazione_automatica
CREATE TRIGGER sincronizzazione_automatica AFTER INSERT ON "SavingMoneyUnina".transazione FOR EACH ROW EXECUTE FUNCTION "SavingMoneyUnina".sincronizzazione_automatica_f();

CREATE FUNCTION "SavingMoneyUnina".sincronizzazione_automatica_f() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
	declare
		pc record;
	BEGIN
		for pc in (select * from parolachiave) loop
			if new.descrizione ilike concat('% ',pc.parola,'%') or new.descrizione ilike concat('%', pc.parola, ' %') or new.descrizione ilike concat('% ', pc.parola, ' %') or lower(new.descrizione)=lower(pc.parola) then
				insert into "SavingMoneyUnina".transazione_portafogli(idportafogli, idtransazione) values(pc.idportafogli, new.idtransazione);
			end if;
		end loop;
		return new;
	END;
$$;