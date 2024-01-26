--
-- PostgreSQL database dump
--

-- Dumped from database version 16.0
-- Dumped by pg_dump version 16.0

-- Started on 2024-01-26 15:14:38

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 6 (class 2615 OID 2200)
-- Name: SavingMoneyUnina; Type: SCHEMA; Schema: -; Owner: pg_database_owner
--

CREATE SCHEMA "SavingMoneyUnina";


ALTER SCHEMA "SavingMoneyUnina" OWNER TO pg_database_owner;

--
-- TOC entry 4918 (class 0 OID 0)
-- Dependencies: 6
-- Name: SCHEMA "SavingMoneyUnina"; Type: COMMENT; Schema: -; Owner: pg_database_owner
--

COMMENT ON SCHEMA "SavingMoneyUnina" IS 'standard public schema';


--
-- TOC entry 240 (class 1255 OID 17144)
-- Name: inserisci_parolechiave_multiple(integer, character varying); Type: PROCEDURE; Schema: SavingMoneyUnina; Owner: postgres
--

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


ALTER PROCEDURE "SavingMoneyUnina".inserisci_parolechiave_multiple(IN portafogli integer, IN lista character varying) OWNER TO postgres;

--
-- TOC entry 242 (class 1255 OID 17145)
-- Name: limitespesa_check_f(); Type: FUNCTION; Schema: SavingMoneyUnina; Owner: postgres
--

CREATE FUNCTION "SavingMoneyUnina".limitespesa_check_f() RETURNS trigger
    LANGUAGE plpgsql
    AS $$

	DECLARE
	totale_speso float;
	tipo_carta varchar;
	limite_spesa float8;
	plafond float8;

BEGIN
	SELECT C.tipo, C.limitespesa, C.plafond
	FROM "SavingMoneyUnina".Carta as C
    INTO tipo_carta, limite_spesa, plafond
	WHERE numero=NEW.numerocarta;

if tipo_carta = 'debito' THEN
		SELECT SUM(valore) INTO totale_speso
		FROM "SavingMoneyUnina".Transazione
		WHERE tipo='uscita'
		GROUP BY ();
		
		IF totale_speso >= limite_spesa THEN
			RAISE EXCEPTION 'È stato superato il limite di spesa della carta associata';
		END IF;

	end if;

	IF tipo_carta = 'credito' AND NEW.saldo < 0 and new.saldo <= -plafond THEN
		raise exception 'È stato superato il plafond';
	end if;
	return new;
end;
$$;


ALTER FUNCTION "SavingMoneyUnina".limitespesa_check_f() OWNER TO postgres;

--
-- TOC entry 241 (class 1255 OID 17095)
-- Name: limitespesa_plafond_check_f(); Type: FUNCTION; Schema: SavingMoneyUnina; Owner: postgres
--

CREATE FUNCTION "SavingMoneyUnina".limitespesa_plafond_check_f() RETURNS trigger
    LANGUAGE plpgsql
    AS $$

	DECLARE
	totale_speso float;
	tipo_carta varchar;
	limite_spesa float8;
	plafond float8;

BEGIN
	SELECT C.tipo, C.limitespesa, C.plafond
	FROM "SavingMoneyUnina".Carta as C
    INTO tipo_carta, limite_spesa, plafond
	WHERE numero=NEW.numerocarta;

if tipo_carta = 'debito' THEN
		SELECT SUM(valore) INTO totale_speso
		FROM "SavingMoneyUnina".Transazione
		WHERE tipo='uscita'
		GROUP BY ();
		
		IF totale_speso >= limite_spesa THEN
			RAISE EXCEPTION 'È stato superato il limite di spesa della carta associata';
		END IF;

	end if;

	IF tipo_carta = 'credito' AND NEW.saldo < 0 and new.saldo <= -plafond THEN
		raise exception 'È stato superato il plafond';
	end if;
return null;
end;
$$;


ALTER FUNCTION "SavingMoneyUnina".limitespesa_plafond_check_f() OWNER TO postgres;

--
-- TOC entry 239 (class 1255 OID 17097)
-- Name: parolechiave_limite_check_f(); Type: FUNCTION; Schema: SavingMoneyUnina; Owner: postgres
--

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
	
$$;


ALTER FUNCTION "SavingMoneyUnina".parolechiave_limite_check_f() OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 219 (class 1259 OID 16900)
-- Name: carta; Type: TABLE; Schema: SavingMoneyUnina; Owner: postgres
--

CREATE TABLE "SavingMoneyUnina".carta (
    numero character varying(16) NOT NULL,
    scadenza date NOT NULL,
    cvv integer NOT NULL,
    plafond double precision NOT NULL,
    limitespesa double precision NOT NULL,
    tipo character varying NOT NULL,
    CONSTRAINT scadenza_carta_check CHECK ((scadenza > now())),
    CONSTRAINT tipo_carta_check CHECK (((((tipo)::text = 'credito'::text) AND (limitespesa = (0)::double precision)) OR (((tipo)::text = 'debito'::text) AND (plafond = (0)::double precision))))
);


ALTER TABLE "SavingMoneyUnina".carta OWNER TO postgres;

--
-- TOC entry 224 (class 1259 OID 16930)
-- Name: categoria; Type: TABLE; Schema: SavingMoneyUnina; Owner: postgres
--

CREATE TABLE "SavingMoneyUnina".categoria (
    nome character varying NOT NULL
);


ALTER TABLE "SavingMoneyUnina".categoria OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 16895)
-- Name: contocorrente; Type: TABLE; Schema: SavingMoneyUnina; Owner: postgres
--

CREATE TABLE "SavingMoneyUnina".contocorrente (
    saldo double precision NOT NULL,
    iban character varying(27) NOT NULL,
    email character varying NOT NULL,
    numerocarta character varying(16) NOT NULL
);


ALTER TABLE "SavingMoneyUnina".contocorrente OWNER TO postgres;

--
-- TOC entry 227 (class 1259 OID 17130)
-- Name: parolachiave; Type: TABLE; Schema: SavingMoneyUnina; Owner: postgres
--

CREATE TABLE "SavingMoneyUnina".parolachiave (
    parola character varying NOT NULL,
    idportafogli integer NOT NULL
);


ALTER TABLE "SavingMoneyUnina".parolachiave OWNER TO postgres;

--
-- TOC entry 216 (class 1259 OID 16881)
-- Name: persona; Type: TABLE; Schema: SavingMoneyUnina; Owner: postgres
--

CREATE TABLE "SavingMoneyUnina".persona (
    nome character varying(50) NOT NULL,
    cognome character varying(50) NOT NULL,
    indirizzo character varying,
    paese character varying NOT NULL,
    codicefiscale character varying NOT NULL,
    "città" character varying NOT NULL
);


ALTER TABLE "SavingMoneyUnina".persona OWNER TO postgres;

--
-- TOC entry 223 (class 1259 OID 16916)
-- Name: portafogli; Type: TABLE; Schema: SavingMoneyUnina; Owner: postgres
--

CREATE TABLE "SavingMoneyUnina".portafogli (
    nome character varying NOT NULL,
    idportafogli integer NOT NULL
);


ALTER TABLE "SavingMoneyUnina".portafogli OWNER TO postgres;

--
-- TOC entry 225 (class 1259 OID 17102)
-- Name: portafogli_categoria; Type: TABLE; Schema: SavingMoneyUnina; Owner: postgres
--

CREATE TABLE "SavingMoneyUnina".portafogli_categoria (
    idportafogli integer NOT NULL,
    nomecategoria character varying NOT NULL
);


ALTER TABLE "SavingMoneyUnina".portafogli_categoria OWNER TO postgres;

--
-- TOC entry 222 (class 1259 OID 16915)
-- Name: portafogli_idportafogli_seq; Type: SEQUENCE; Schema: SavingMoneyUnina; Owner: postgres
--

ALTER TABLE "SavingMoneyUnina".portafogli ALTER COLUMN idportafogli ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME "SavingMoneyUnina".portafogli_idportafogli_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 221 (class 1259 OID 16908)
-- Name: transazione; Type: TABLE; Schema: SavingMoneyUnina; Owner: postgres
--

CREATE TABLE "SavingMoneyUnina".transazione (
    valore double precision NOT NULL,
    data date NOT NULL,
    descrizione character varying(200) NOT NULL,
    tipo character varying NOT NULL,
    altroiban character varying(27) NOT NULL,
    idtransazione integer NOT NULL,
    iban character varying(27) NOT NULL,
    CONSTRAINT tipo_transazione_check CHECK ((((tipo)::text = 'entrata'::text) OR ((tipo)::text = 'uscita'::text)))
);


ALTER TABLE "SavingMoneyUnina".transazione OWNER TO postgres;

--
-- TOC entry 220 (class 1259 OID 16907)
-- Name: transazione_idtransazione_seq; Type: SEQUENCE; Schema: SavingMoneyUnina; Owner: postgres
--

ALTER TABLE "SavingMoneyUnina".transazione ALTER COLUMN idtransazione ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME "SavingMoneyUnina".transazione_idtransazione_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 226 (class 1259 OID 17117)
-- Name: transazione_portafogli; Type: TABLE; Schema: SavingMoneyUnina; Owner: postgres
--

CREATE TABLE "SavingMoneyUnina".transazione_portafogli (
    idtransazione integer NOT NULL,
    idportafogli integer NOT NULL
);


ALTER TABLE "SavingMoneyUnina".transazione_portafogli OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 16888)
-- Name: utente; Type: TABLE; Schema: SavingMoneyUnina; Owner: postgres
--

CREATE TABLE "SavingMoneyUnina".utente (
    email character varying NOT NULL,
    password character varying NOT NULL,
    codicefiscale character varying(16) NOT NULL
);


ALTER TABLE "SavingMoneyUnina".utente OWNER TO postgres;

--
-- TOC entry 4904 (class 0 OID 16900)
-- Dependencies: 219
-- Data for Name: carta; Type: TABLE DATA; Schema: SavingMoneyUnina; Owner: postgres
--

COPY "SavingMoneyUnina".carta (numero, scadenza, cvv, plafond, limitespesa, tipo) FROM stdin;
1233	2030-12-12	123	1000	0	credito
\.


--
-- TOC entry 4909 (class 0 OID 16930)
-- Dependencies: 224
-- Data for Name: categoria; Type: TABLE DATA; Schema: SavingMoneyUnina; Owner: postgres
--

COPY "SavingMoneyUnina".categoria (nome) FROM stdin;
Svago
Salute
Finanze
\.


--
-- TOC entry 4903 (class 0 OID 16895)
-- Dependencies: 218
-- Data for Name: contocorrente; Type: TABLE DATA; Schema: SavingMoneyUnina; Owner: postgres
--

COPY "SavingMoneyUnina".contocorrente (saldo, iban, email, numerocarta) FROM stdin;
10000	IT30	ciao	1233
\.


--
-- TOC entry 4912 (class 0 OID 17130)
-- Dependencies: 227
-- Data for Name: parolachiave; Type: TABLE DATA; Schema: SavingMoneyUnina; Owner: postgres
--

COPY "SavingMoneyUnina".parolachiave (parola, idportafogli) FROM stdin;
a	1
b	1
c	1
\.


--
-- TOC entry 4901 (class 0 OID 16881)
-- Dependencies: 216
-- Data for Name: persona; Type: TABLE DATA; Schema: SavingMoneyUnina; Owner: postgres
--

COPY "SavingMoneyUnina".persona (nome, cognome, indirizzo, paese, codicefiscale, "città") FROM stdin;
Salvatore	Brandi	Via Duomo	Italia	000	Napoli
\.


--
-- TOC entry 4908 (class 0 OID 16916)
-- Dependencies: 223
-- Data for Name: portafogli; Type: TABLE DATA; Schema: SavingMoneyUnina; Owner: postgres
--

COPY "SavingMoneyUnina".portafogli (nome, idportafogli) FROM stdin;
Estate 2023	1
\.


--
-- TOC entry 4910 (class 0 OID 17102)
-- Dependencies: 225
-- Data for Name: portafogli_categoria; Type: TABLE DATA; Schema: SavingMoneyUnina; Owner: postgres
--

COPY "SavingMoneyUnina".portafogli_categoria (idportafogli, nomecategoria) FROM stdin;
1	Svago
\.


--
-- TOC entry 4906 (class 0 OID 16908)
-- Dependencies: 221
-- Data for Name: transazione; Type: TABLE DATA; Schema: SavingMoneyUnina; Owner: postgres
--

COPY "SavingMoneyUnina".transazione (valore, data, descrizione, tipo, altroiban, idtransazione, iban) FROM stdin;
\.


--
-- TOC entry 4911 (class 0 OID 17117)
-- Dependencies: 226
-- Data for Name: transazione_portafogli; Type: TABLE DATA; Schema: SavingMoneyUnina; Owner: postgres
--

COPY "SavingMoneyUnina".transazione_portafogli (idtransazione, idportafogli) FROM stdin;
\.


--
-- TOC entry 4902 (class 0 OID 16888)
-- Dependencies: 217
-- Data for Name: utente; Type: TABLE DATA; Schema: SavingMoneyUnina; Owner: postgres
--

COPY "SavingMoneyUnina".utente (email, password, codicefiscale) FROM stdin;
ciao	1234	000
\.


--
-- TOC entry 4919 (class 0 OID 0)
-- Dependencies: 222
-- Name: portafogli_idportafogli_seq; Type: SEQUENCE SET; Schema: SavingMoneyUnina; Owner: postgres
--

SELECT pg_catalog.setval('"SavingMoneyUnina".portafogli_idportafogli_seq', 1, true);


--
-- TOC entry 4920 (class 0 OID 0)
-- Dependencies: 220
-- Name: transazione_idtransazione_seq; Type: SEQUENCE SET; Schema: SavingMoneyUnina; Owner: postgres
--

SELECT pg_catalog.setval('"SavingMoneyUnina".transazione_idtransazione_seq', 1, false);


--
-- TOC entry 4734 (class 2606 OID 16887)
-- Name: persona codicefiscale_pk; Type: CONSTRAINT; Schema: SavingMoneyUnina; Owner: postgres
--

ALTER TABLE ONLY "SavingMoneyUnina".persona
    ADD CONSTRAINT codicefiscale_pk PRIMARY KEY (codicefiscale);


--
-- TOC entry 4736 (class 2606 OID 16894)
-- Name: utente email_pk; Type: CONSTRAINT; Schema: SavingMoneyUnina; Owner: postgres
--

ALTER TABLE ONLY "SavingMoneyUnina".utente
    ADD CONSTRAINT email_pk PRIMARY KEY (email);


--
-- TOC entry 4738 (class 2606 OID 16899)
-- Name: contocorrente iban_pk; Type: CONSTRAINT; Schema: SavingMoneyUnina; Owner: postgres
--

ALTER TABLE ONLY "SavingMoneyUnina".contocorrente
    ADD CONSTRAINT iban_pk PRIMARY KEY (iban);


--
-- TOC entry 4746 (class 2606 OID 16936)
-- Name: categoria nome_pk; Type: CONSTRAINT; Schema: SavingMoneyUnina; Owner: postgres
--

ALTER TABLE ONLY "SavingMoneyUnina".categoria
    ADD CONSTRAINT nome_pk PRIMARY KEY (nome);


--
-- TOC entry 4740 (class 2606 OID 17060)
-- Name: carta numero_pk; Type: CONSTRAINT; Schema: SavingMoneyUnina; Owner: postgres
--

ALTER TABLE ONLY "SavingMoneyUnina".carta
    ADD CONSTRAINT numero_pk PRIMARY KEY (numero);


--
-- TOC entry 4744 (class 2606 OID 16922)
-- Name: portafogli portafogli_pk; Type: CONSTRAINT; Schema: SavingMoneyUnina; Owner: postgres
--

ALTER TABLE ONLY "SavingMoneyUnina".portafogli
    ADD CONSTRAINT portafogli_pk PRIMARY KEY (idportafogli);


--
-- TOC entry 4742 (class 2606 OID 16914)
-- Name: transazione transazione_pk; Type: CONSTRAINT; Schema: SavingMoneyUnina; Owner: postgres
--

ALTER TABLE ONLY "SavingMoneyUnina".transazione
    ADD CONSTRAINT transazione_pk PRIMARY KEY (idtransazione);


--
-- TOC entry 4756 (class 2620 OID 17096)
-- Name: contocorrente limitespesa_plafond_check; Type: TRIGGER; Schema: SavingMoneyUnina; Owner: postgres
--

CREATE TRIGGER limitespesa_plafond_check BEFORE UPDATE ON "SavingMoneyUnina".contocorrente FOR EACH ROW EXECUTE FUNCTION "SavingMoneyUnina".limitespesa_plafond_check_f();


--
-- TOC entry 4757 (class 2620 OID 17141)
-- Name: parolachiave parolechiave_check; Type: TRIGGER; Schema: SavingMoneyUnina; Owner: postgres
--

CREATE TRIGGER parolechiave_check BEFORE INSERT ON "SavingMoneyUnina".parolachiave FOR EACH ROW EXECUTE FUNCTION "SavingMoneyUnina".parolechiave_limite_check_f();


--
-- TOC entry 4747 (class 2606 OID 17013)
-- Name: utente codicefiscale_fk; Type: FK CONSTRAINT; Schema: SavingMoneyUnina; Owner: postgres
--

ALTER TABLE ONLY "SavingMoneyUnina".utente
    ADD CONSTRAINT codicefiscale_fk FOREIGN KEY (codicefiscale) REFERENCES "SavingMoneyUnina".persona(codicefiscale) ON DELETE CASCADE;


--
-- TOC entry 4748 (class 2606 OID 17018)
-- Name: contocorrente email_fk; Type: FK CONSTRAINT; Schema: SavingMoneyUnina; Owner: postgres
--

ALTER TABLE ONLY "SavingMoneyUnina".contocorrente
    ADD CONSTRAINT email_fk FOREIGN KEY (email) REFERENCES "SavingMoneyUnina".utente(email) ON DELETE CASCADE;


--
-- TOC entry 4750 (class 2606 OID 17028)
-- Name: transazione iban_fk; Type: FK CONSTRAINT; Schema: SavingMoneyUnina; Owner: postgres
--

ALTER TABLE ONLY "SavingMoneyUnina".transazione
    ADD CONSTRAINT iban_fk FOREIGN KEY (iban) REFERENCES "SavingMoneyUnina".contocorrente(iban) ON DELETE CASCADE;


--
-- TOC entry 4751 (class 2606 OID 17107)
-- Name: portafogli_categoria idportafogli_fk; Type: FK CONSTRAINT; Schema: SavingMoneyUnina; Owner: postgres
--

ALTER TABLE ONLY "SavingMoneyUnina".portafogli_categoria
    ADD CONSTRAINT idportafogli_fk FOREIGN KEY (idportafogli) REFERENCES "SavingMoneyUnina".portafogli(idportafogli);


--
-- TOC entry 4753 (class 2606 OID 17120)
-- Name: transazione_portafogli idportafogli_fk; Type: FK CONSTRAINT; Schema: SavingMoneyUnina; Owner: postgres
--

ALTER TABLE ONLY "SavingMoneyUnina".transazione_portafogli
    ADD CONSTRAINT idportafogli_fk FOREIGN KEY (idportafogli) REFERENCES "SavingMoneyUnina".portafogli(idportafogli);


--
-- TOC entry 4755 (class 2606 OID 17135)
-- Name: parolachiave idportafogli_fk; Type: FK CONSTRAINT; Schema: SavingMoneyUnina; Owner: postgres
--

ALTER TABLE ONLY "SavingMoneyUnina".parolachiave
    ADD CONSTRAINT idportafogli_fk FOREIGN KEY (idportafogli) REFERENCES "SavingMoneyUnina".portafogli(idportafogli);


--
-- TOC entry 4754 (class 2606 OID 17125)
-- Name: transazione_portafogli idtransazione_fk; Type: FK CONSTRAINT; Schema: SavingMoneyUnina; Owner: postgres
--

ALTER TABLE ONLY "SavingMoneyUnina".transazione_portafogli
    ADD CONSTRAINT idtransazione_fk FOREIGN KEY (idtransazione) REFERENCES "SavingMoneyUnina".transazione(idtransazione);


--
-- TOC entry 4752 (class 2606 OID 17112)
-- Name: portafogli_categoria nomecategoria_fk; Type: FK CONSTRAINT; Schema: SavingMoneyUnina; Owner: postgres
--

ALTER TABLE ONLY "SavingMoneyUnina".portafogli_categoria
    ADD CONSTRAINT nomecategoria_fk FOREIGN KEY (nomecategoria) REFERENCES "SavingMoneyUnina".categoria(nome);


--
-- TOC entry 4749 (class 2606 OID 17073)
-- Name: contocorrente numerocarta_fk; Type: FK CONSTRAINT; Schema: SavingMoneyUnina; Owner: postgres
--

ALTER TABLE ONLY "SavingMoneyUnina".contocorrente
    ADD CONSTRAINT numerocarta_fk FOREIGN KEY (numerocarta) REFERENCES "SavingMoneyUnina".carta(numero);


-- Completed on 2024-01-26 15:14:38

--
-- PostgreSQL database dump complete
--

