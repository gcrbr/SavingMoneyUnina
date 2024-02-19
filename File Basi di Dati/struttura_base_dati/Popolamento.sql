INSERT INTO "SavingMoneyUnina".persona(nome, cognome, indirizzo, paese, codicefiscale, "città")
VALUES('Salvatore', 'Brandi', 'Via Duomo', 'Italia', '000', 'Napoli')

INSERT INTO "SavingMoneyUnina".utente(email, password, codicefiscale)
VALUES('ciao', '1234', '000')

INSERT INTO "SavingMoneyUnina".contocorrente(saldo, iban, email, numerocarta)
VALUES(0, 'IT9510510', 'ciao', '402592'),
(0, 'IT910349', 'ciao', '493245'),
(1017, 'IT8395823', 'ciao', '3589238523'),
(10260, 'IT5930492304923', 'ciao', '404050');

INSERT INTO "SavingMoneyUnina".carta(numero, scadenza, cvv, plafond, limitespesa, tipo)
VALUES('404050', '2025-10-10', '335', 0, 1000, 'debito'),
('3589238523', '2031-01-15', '333', 0, 0, 'credito'),
('402592', '2035-01-12', '351', 0, 0, 'debito'),
('493245', '2035-11-10', '123', 10000, 0, 'credito');

INSERT INTO "SavingMoneyUnina".transazione(valore, data, descrizione, tipo, altroiban, idtransazione, iban)
VALUES(250, '2024-01-01', 'Stipendio', 'entrata', '', 82, 'IT5930492304923'),
(75, '2024-01-15', 'Vendita bici', 'entrata', '', 83, 'IT8395823'),
(15, '2024-01-17', 'Vitamine C e B2', 'uscita', '', 84, 'IT5930492304923'),
(35, '2024-01-13', 'Vendita vinted', 'entrata', '', 85, 'IT5930492304923'),
(200, '2024-02-02', 'vendita bici 2', 'entrata', '', 87, 'IT5930492304923'),
(25, '2024-02-03', 'prova', 'entrata', '', 88, 'IT5930492304923'),
(15, '2024-02-03', 'Vendita occhiali', 'entrata', '', 89, 'IT5930492304923');

INSERT INTO "SavingMoneyUnina".portafogli(nome, idportafogli, email)
VALUES('Estate 2023', 3, 'ciao'),
('Spese mediche', 4, 'ciao'),
('Spese mediche nuovo', 5, 'ciao'),
('prova', 6, 'ciao'),
('Estate 2024', 7, 'ciao'),
('proooova', 12, 'ciao'),
('xfxhxh', 13, 'ciao'),
('zrzr', 14, 'ciao');

INSERT INTO "SavingMoneyUnina".transazione_portafogli (idtransazione, idportafogli)
VALUES(84, 5),
(84, 7),
(87, 3),
(89, 3),
(88, 3),
(84, 3),
(88, 3),
(84, 3),
(85, 3);

INSERT INTO "SavingMoneyUnina".categoria(nome)
VALUES('Svago'),
('Salute'),
('Finanze'),
('Trasporti'),
('Bollette');

INSERT INTO portafogli_categoria(idportafogli, nomecategoria)
VALUES(3, 'Svago'),
(5, 'Salute'),
(6, 'Svago'),
(6, 'Finanze'),
(7, 'Svago');

INSERT INTO "SavingMoneyUnina".parolachiave(parola, idportafogli)
VALUES('booking', 3),
('airbnb', 3),
('ryanair', 3),
('medicine', 5),
('vitamine', 5),
('integratore', 5),
('test', 6),
('esempio', 6),
('wizzair', 7),
('booking', 7),
('spiaggia', 7);