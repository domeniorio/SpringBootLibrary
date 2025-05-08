-- Insert per Tessera
INSERT INTO Tessera (codice_tessera, data_rilascio) VALUES
(1, '2023-09-01'),
(2, '2023-09-02'),
(3, '2023-09-03'),
(4, '2023-09-04'),
(5, '2023-09-05'),
(6, '2023-09-06'),
(7, '2023-09-07'),
(8, '2023-09-08'),
(9, '2023-09-09'),
(10, '2023-09-10'),
(11, '2023-09-11'),
(12, '2023-09-12'),
(13, '2023-09-13'),
(14, '2023-09-14'),
(15, '2023-09-15'),
(16, '2023-09-16'),
(17, '2023-09-17'),
(18, '2023-09-18'),
(19, '2023-09-19'),
(20, '2023-09-20');
ALTER TABLE Tessera ALTER COLUMN codice_tessera RESTART WITH 21;


-- Insert per Studente
INSERT INTO Studente (codice_studente, nome, cognome, classe, email, codice_tessera) VALUES
(1, 'Luca', 'Rossi', '3A', 'luca.rossi@example.com', 1),
(2, 'Marco', 'Verdi', '3A', 'marco.verdi@example.com', 2),
(3, 'Anna', 'Bianchi', '3B', 'anna.bianchi@example.com', 3),
(4, 'Sara', 'Neri', '4A', 'sara.neri@example.com', 4),
(5, 'Giulia', 'Russo', '4B', 'giulia.russo@example.com', 5),
(6, 'Andrea', 'Gallo', '5A', 'andrea.gallo@example.com', 6),
(7, 'Davide', 'Fontana', '5B', 'davide.fontana@example.com', 7),
(8, 'Elisa', 'Marino', '3C', 'elisa.marino@example.com', 8),
(9, 'Francesca', 'Romano', '4C', 'francesca.romano@example.com', 9),
(10, 'Matteo', 'Leone', '5C', 'matteo.leone@example.com', 10),
(11, 'Chiara', 'Greco', '3A', 'chiara.greco@example.com', 11),
(12, 'Simone', 'Lombardi', '4A', 'simone.lombardi@example.com', 12),
(13, 'Martina', 'Ferrari', '3B', 'martina.ferrari@example.com', 13),
(14, 'Alessandro', 'Conti', '4B', 'alessandro.conti@example.com', 14),
(15, 'Ilaria', 'De Luca', '5B', 'ilaria.deluca@example.com', 15),
(16, 'Giorgio', 'Costa', '3C', 'giorgio.costa@example.com', 16),
(17, 'Valentina', 'Barbieri', '4C', 'valentina.barbieri@example.com', 17),
(18, 'Federico', 'Santoro', '5C', 'federico.santoro@example.com', 18),
(19, 'Laura', 'Esposito', '3A', 'laura.esposito@example.com', 19),
(20, 'Nicola', 'Moretti', '4A', 'nicola.moretti@example.com', 20);
ALTER TABLE Studente ALTER COLUMN codice_studente RESTART WITH 21;


--Insert per Categoria
INSERT INTO Categoria (codice_categoria, nome) VALUES
(1, 'Narrativa'),
(2, 'Saggistica'),
(3, 'Fantascienza'),
(4, 'Biografia'),
(5, 'Storico');
ALTER TABLE Categoria ALTER COLUMN codice_categoria RESTART WITH 6;


--Insert per Libro
INSERT INTO Libro (codice_libro, titolo, autore, anno, codice_categoria) VALUES
(1, 'Il mondo nuovo', 'Aldous Huxley', 1932, 3),
(2, '1984', 'George Orwell', 1949, 3),
(3, 'Breve storia del tempo', 'Stephen Hawking', 1988, 2),
(4, 'Il nome della rosa', 'Umberto Eco', 1980, 1),
(5, 'Open', 'Andre Agassi', 2009, 4),
(6, 'Il cacciatore di aquiloni', 'Khaled Hosseini', 2003, 1),
(7, 'Sapiens', 'Yuval Noah Harari', 2011, 2),
(8, 'Steve Jobs', 'Walter Isaacson', 2011, 4),
(9, 'La ragazza con l’orecchino di perla', 'Tracy Chevalier', 1999, 5),
(10, 'Dune', 'Frank Herbert', 1965, 3),
(11, 'L’ombra del vento', 'Carlos Ruiz Zafón', 2001, 1),
(12, 'Il diario di Anna Frank', 'Anna Frank', 1947, 4),
(13, 'Il gene egoista', 'Richard Dawkins', 1976, 2),
(14, 'Il giorno della civetta', 'Leonardo Sciascia', 1961, 5),
(15, 'Fahrenheit 451', 'Ray Bradbury', 1953, 3),
(16, 'La lunga marcia', 'Stephen King', 1979, 1),
(17, 'La mente nuova dell’imperatore', 'Roger Penrose', 1989, 2),
(18, 'L’arte della guerra', 'Sun Tzu', -500, 5),
(19, 'La strada', 'Cormac McCarthy', 2006, 1),
(20, 'Nel mare ci sono i coccodrilli', 'Fabio Geda', 2010, 4);
ALTER TABLE Libro ALTER COLUMN codice_libro RESTART WITH 21;


--Insert per Prestito
INSERT INTO Prestito (codice_prestito, data_inizio, data_fine, codice_studente, codice_libro) VALUES
(1, '2023-01-10', '2023-01-24', 1, 5),
(2, '2023-01-15', '2023-01-29', 2, 12),
(3, '2023-02-01', '2023-02-15', 3, 7),
(4, '2023-02-10', '2023-02-24', 4, 15),
(5, '2023-03-05', '2023-03-19', 5, 1),
(6, '2023-03-20', '2023-04-03', 6, 19),
(7, '2023-04-01', '2023-04-15', 7, 9),
(8, '2023-04-10', '2023-04-24', 8, 6),
(9, '2023-05-01', '2023-05-15', 9, 14),
(10, '2023-05-20', '2023-06-03', 10, 3),
(11, '2023-06-10', '2023-06-24', 11, 17),
(12, '2023-07-01', '2023-07-15', 12, 8),
(13, '2023-07-20', '2023-08-03', 13, 20),
(14, '2023-08-10', '2023-08-24', 14, 4),
(15, '2023-09-01', '2023-09-15', 15, 10),
(16, '2023-09-15', '2023-09-29', 16, 2),
(17, '2023-10-05', '2023-10-19', 17, 13),
(18, '2023-11-01', '2023-11-15', 18, 16),
(19, '2023-11-20', '2023-12-04', 19, 11),
(20, '2023-12-10', '2023-12-24', 20, 18);
ALTER TABLE Prestito ALTER COLUMN codice_prestito RESTART WITH 21;


--Insert per Corso
INSERT INTO Corso (codice_corso, nome, descrizione) VALUES
(1, 'Programmazione I', 'Introduzione alla programmazione con linguaggi imperativi.'),
(2, 'Analisi Matematica I', 'Studio delle funzioni, limiti, derivate e integrali.'),
(3, 'Fisica Generale', 'Fondamenti di meccanica, termodinamica ed elettromagnetismo.'),
(4, 'Basi di Dati', 'Concetti fondamentali sui database relazionali e SQL.'),
(5, 'Architettura degli Elaboratori', 'Struttura interna e funzionamento dei computer.'),
(6, 'Sistemi Operativi', 'Concetti di processi, thread, gestione della memoria e file system.'),
(7, 'Ingegneria del Software', 'Metodologie per l’analisi, progettazione e sviluppo del software.'),
(8, 'Reti di Calcolatori', 'Architetture, protocolli e sicurezza nelle reti informatiche.'),
(9, 'Algoritmi e Strutture Dati', 'Analisi, progettazione e implementazione di algoritmi efficienti.'),
(10, 'Intelligenza Artificiale', 'Introduzione a tecniche e applicazioni dell’AI, tra cui machine learning.');

--Insert per iscrizione
INSERT INTO Iscrizione (codice_studente, codice_corso) VALUES
(1, 1),
(1, 2),
(2, 3),
(2, 4),
(3, 1),
(3, 5),
(4, 2),
(4, 6),
(5, 3),
(5, 7),
(6, 4),
(6, 8),
(7, 5),
(7, 9),
(8, 6),
(8, 10),
(9, 1),
(9, 7),
(10, 2),
(10, 8);
