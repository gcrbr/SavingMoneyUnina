--Creazione schema
CREATE SCHEMA "SavingMoneyUnina";
ALTER SCHEMA "SavingMoneyUnina" OWNER TO pg_database_owner;
COMMENT ON SCHEMA "SavingMoneyUnina" IS 'standard public schema';

--Creazione tabella Carta
CREATE TABLE "SavingMoneyUnina".carta (
    numero character varying(16) NOT NULL,
    scadenza date NOT NULL,
    cvv character varying(3) NOT NULL,
    plafond double precision NOT NULL,
    limitespesa double precision NOT NULL,
    tipo character varying NOT NULL,
    CONSTRAINT scadenza_carta_check CHECK ((scadenza > now())),
    CONSTRAINT tipo_carta_check CHECK (((((tipo)::text = 'credito'::text) AND (limitespesa = (0)::double precision)) OR (((tipo)::text = 'debito'::text) AND (plafond = (0)::double precision))))
);
ALTER TABLE ONLY "SavingMoneyUnina".carta
    ADD CONSTRAINT numero_pk PRIMARY KEY (numero);

--Creazione tabella Categoria
CREATE TABLE "SavingMoneyUnina".categoria (
    nome character varying NOT NULL
);
ALTER TABLE ONLY "SavingMoneyUnina".categoria
    ADD CONSTRAINT nome_pk PRIMARY KEY (nome);

--Creazione tabella ContoCorrente
CREATE TABLE "SavingMoneyUnina".contocorrente (
    saldo double precision NOT NULL,
    iban character varying(27) NOT NULL,
    email character varying NOT NULL,
    numerocarta character varying(16) NOT NULL
);
ALTER TABLE ONLY "SavingMoneyUnina".contocorrente
    ADD CONSTRAINT iban_pk PRIMARY KEY (iban);
ALTER TABLE ONLY "SavingMoneyUnina".contocorrente
    ADD CONSTRAINT email_fk FOREIGN KEY (email) REFERENCES "SavingMoneyUnina".utente(email) ON DELETE CASCADE;
ALTER TABLE ONLY "SavingMoneyUnina".contocorrente
    ADD CONSTRAINT numerocarta_fk FOREIGN KEY (numerocarta) REFERENCES "SavingMoneyUnina".carta(numero);

--Creazione tabella ParolaChiave
CREATE TABLE "SavingMoneyUnina".parolachiave (
    parola character varying NOT NULL,
    idportafogli integer NOT NULL
);
ALTER TABLE ONLY "SavingMoneyUnina".parolachiave
    ADD CONSTRAINT idportafogli_fk FOREIGN KEY (idportafogli) REFERENCES "SavingMoneyUnina".portafogli(idportafogli);

--Creazione tabella Persona
CREATE TABLE "SavingMoneyUnina".persona (
    nome character varying(50) NOT NULL,
    cognome character varying(50) NOT NULL,
    indirizzo character varying,
    paese character varying NOT NULL,
    codicefiscale character varying NOT NULL,
    "città" character varying NOT NULL
);
ALTER TABLE ONLY "SavingMoneyUnina".persona
    ADD CONSTRAINT codicefiscale_pk PRIMARY KEY (codicefiscale);

--Creazione tabella Portafogli
CREATE TABLE "SavingMoneyUnina".portafogli (
    nome character varying NOT NULL,
    idportafogli integer NOT NULL,
    email character varying NOT NULL
);
ALTER TABLE "SavingMoneyUnina".portafogli ALTER COLUMN idportafogli ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME "SavingMoneyUnina".portafogli_idportafogli_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
ALTER TABLE ONLY "SavingMoneyUnina".portafogli
    ADD CONSTRAINT portafogli_pk PRIMARY KEY (idportafogli);
ALTER TABLE ONLY "SavingMoneyUnina".portafogli
    ADD CONSTRAINT email_fk FOREIGN KEY (email) REFERENCES "SavingMoneyUnina".utente(email);

--Creazione tabella Portafogli_Categoria
CREATE TABLE "SavingMoneyUnina".portafogli_categoria (
    idportafogli integer NOT NULL,
    nomecategoria character varying NOT NULL
);
ALTER TABLE ONLY "SavingMoneyUnina".portafogli_categoria
    ADD CONSTRAINT idportafogli_fk FOREIGN KEY (idportafogli) REFERENCES "SavingMoneyUnina".portafogli(idportafogli) ON DELETE CASCADE;
ALTER TABLE ONLY "SavingMoneyUnina".portafogli_categoria
    ADD CONSTRAINT nomecategoria_fk FOREIGN KEY (nomecategoria) REFERENCES "SavingMoneyUnina".categoria(nome);

--Creazione tabella Transazione
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
ALTER TABLE "SavingMoneyUnina".transazione ALTER COLUMN idtransazione ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME "SavingMoneyUnina".transazione_idtransazione_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
ALTER TABLE ONLY "SavingMoneyUnina".transazione
    ADD CONSTRAINT transazione_pk PRIMARY KEY (idtransazione);
ALTER TABLE ONLY "SavingMoneyUnina".transazione
    ADD CONSTRAINT iban_fk FOREIGN KEY (iban) REFERENCES "SavingMoneyUnina".contocorrente(iban) ON DELETE CASCADE;

--Creazione tabella Transazione_Portafogli
CREATE TABLE "SavingMoneyUnina".transazione_portafogli (
    idtransazione integer NOT NULL,
    idportafogli integer NOT NULL
);
ALTER TABLE ONLY "SavingMoneyUnina".transazione_portafogli
    ADD CONSTRAINT idportafogli_fk FOREIGN KEY (idportafogli) REFERENCES "SavingMoneyUnina".portafogli(idportafogli) ON DELETE CASCADE;
ALTER TABLE ONLY "SavingMoneyUnina".transazione_portafogli
    ADD CONSTRAINT idtransazione_fk FOREIGN KEY (idtransazione) REFERENCES "SavingMoneyUnina".transazione(idtransazione) ON DELETE CASCADE;

--Creazione tabella Utente
CREATE TABLE "SavingMoneyUnina".utente (
    email character varying NOT NULL,
    password character varying NOT NULL,
    codicefiscale character varying(16) NOT NULL
);
ALTER TABLE ONLY "SavingMoneyUnina".utente
    ADD CONSTRAINT email_pk PRIMARY KEY (email);
ALTER TABLE ONLY "SavingMoneyUnina".utente
    ADD CONSTRAINT codicefiscale_fk FOREIGN KEY (codicefiscale) REFERENCES "SavingMoneyUnina".persona(codicefiscale) ON DELETE CASCADE;