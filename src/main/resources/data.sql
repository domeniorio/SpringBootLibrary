-- Insert per Card
INSERT INTO Card (id, release_date) VALUES
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
(20, '2023-09-20'),
(21, '2023-09-21'),
(22, '2023-09-22'),
(23, '2023-09-23'),
(24, '2023-09-24'),
(25, '2023-09-25'),
(26, '2023-09-26'),
(27, '2023-09-27'),
(28, '2023-09-28'),
(29, '2023-09-29'),
(30, '2023-09-30'),
(31, '2023-10-01'),
(32, '2023-10-02'),
(33, '2023-10-03'),
(34, '2023-10-04'),
(35, '2023-10-05'),
(36, '2023-10-06'),
(37, '2023-10-07'),
(38, '2023-10-08'),
(39, '2023-10-09'),
(40, '2023-10-10'),
(41, '2025-10-10');
ALTER TABLE Card ALTER COLUMN id RESTART WITH 42;


-- Insert per student
INSERT INTO student (id, name, surname, class, email, id_card) VALUES
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
(19, 'Laura', 'Esposito', '3F', 'laura.esposito@example.com', 19),
(20, 'Nicola', 'Moretti', '6A', 'nicola.moretti@example.com', 20),
(21, 'Alessia', 'Conti', '4B', 'alessia.conti@example.com', 21),
(22, 'Francesca', 'Rizzo', '5A', 'francesca.rizzo@example.com', 22),
(23, 'Chiara', 'Grieco', '3A', 'chiara.grieco@example.com', 23),
(24, 'Elena', 'Barbieri', '5A', 'elena.barbieri@example.com', 24),
(25, 'Elena', 'Contini', '3B', 'elena.contini@example.com', 25),
(26, 'Matteo', 'Ferrari', '4B', 'matteo.ferrari@example.com', 26),
(27, 'Francesco', 'Marini', '4A', 'francesco.marini@example.com', 27),
(28, 'Matteo', 'Conti', '3A', 'matteo.conti@example.com', 28),
(29, 'Federica', 'Barbieri', '5B', 'federica.barbieri@example.com', 29),
(30, 'Chiara', 'Ferrari', '3A', 'chiara.ferrari@example.com', 30),
(31, 'Chiara', 'Marini', '4B', 'chiara.marini@example.com', 31),
(32, 'Matteo', 'Barbieri', '5B', 'matteo.barbieri@example.com', 32),
(33, 'Francesco', 'Greco', '3B', 'francesco.greco@example.com', 33),
(34, 'Elena', 'Greco', '4B', 'elena.greco@example.com', 34),
(35, 'Elena', 'Conti', '5B', 'elena.conti@example.com', 35),
(36, 'Elena', 'Colombo', '3B', 'elena.colombo@example.com', 36),
(37, 'Simone', 'Rizzo', '4A', 'simone.rizzo@example.com', 37),
(38, 'Chiara', 'Rizzo', '4B', 'chiara.rizzo@example.com', 38),
(39, 'Francesco', 'Rizzo', '3A', 'francesco.rizzo@example.com', 39),
(40, 'Alessia', 'Ferrari', '3B', 'alessia.ferrari@example.com', 40),
(41, 'Domenico', 'Iorio', '5C', 'domenico.iorio@example.com', 41);
ALTER TABLE student ALTER COLUMN id RESTART WITH 42;


--Insert per Category
INSERT INTO Category (id, name) VALUES
(1, 'Narrativa'),
(2, 'Saggistica'),
(3, 'Fantascienza'),
(4, 'Biografia'),
(5, 'Storico'),
(6, 'Manuale');
ALTER TABLE Category ALTER COLUMN id RESTART WITH 7;


--Insert per Book
INSERT INTO Book (id, title, author, publish_year, id_category) VALUES
(1, 'Il mondo new', 'Aldous Huxley', 1932, 3),
(2, '1984', 'George Orwell', 1949, 3),
(3, 'Breve storia del tempo', 'Stephen Hawking', 1988, 2),
(4, 'Il name della rosa', 'Umberto Eco', 1980, 1),
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
(20, 'Nel mare ci sono i coccodrilli', 'Fabio Geda', 2010, 4),
(21, 'Manuale di prova', 'Domenico', 2024, 6),
(22, 'Orgoglio e pregiudizio', 'Jane Austen', 1813, 1),
(23, 'Guerra e pace', 'Lev Tolstoj', 1869, 1),
(24, 'Cento anni di solitudine', 'Gabriel García Márquez', 1967, 1),
(25, 'Don Chisciotte della Mancia', 'Miguel de Cervantes', 1605, 1),
(26, 'Cronache marziane', 'Ray Bradbury', 1950, 3),
(27, 'Guida galattica per autostoppisti', 'Douglas Adams', 1979, 3),
(28, 'Cosmos', 'Carl Sagan', 1980, 2),
(29, 'Homo Deus', 'Yuval Noah Harari', 2015, 2),
(30, 'Einstein: La sua vita e il suo universo', 'Walter Isaacson', 2007, 4),
(31, 'Leonardo da Vinci', 'Walter Isaacson', 2017, 4),
(32, 'Il signore degli anelli', 'J.R.R. Tolkien', 1954, 1),
(33, 'Harry Potter e la pietra filosofale', 'J.K. Rowling', 1997, 1),
(34, 'La svastica sul sole', 'Philip K. Dick', 1962, 3),
(35, 'Una brevissima storia di (quasi) tutto', 'Bill Bryson', 2003, 2),
(36, 'Delitto e castigo', 'Fëdor Dostoevskij', 1866, 1),
(37, 'Dracula', 'Bram Stoker', 1897, 1),
(38, 'Frankenstein', 'Mary Shelley', 1818, 1),
(39, 'Il ritratto di Dorian Gray', 'Oscar Wilde', 1890, 1),
(40, 'Viaggio al centro della Terra', 'Jules Verne', 1864, 3),
(41, 'Ventimila leghe sotto i mari', 'Jules Verne', 1870, 3),
(42, 'L origine delle specie', 'Charles Darwin', 1859, 2),
(43, 'Il capitale nel XXI secolo', 'Thomas Piketty', 2013, 2),
(44, 'Io sono Malala', 'Malala Yousafzai', 2013, 4),
(45, 'Autobiografia di Malcolm X', 'Malcolm X', 1965, 4),
(46, 'Storia d Italia', 'Benedetto Croce', 1928, 5),
(47, 'Guerra del Peloponneso', 'Tucidide', -431, 5);
ALTER TABLE Book ALTER COLUMN id RESTART WITH 22;


--Insert per Loan
INSERT INTO Loan (id, start_date, end_date, id_student, id_book) VALUES
(1, '2023-01-10', '2023-01-24', 1, 5),
(2, '2023-01-15', NULL, 2, 12),
(3, '2023-02-01', '2023-02-15', 3, 7),
(4, '2023-02-10', '2023-02-24', 19, 15),
(5, '2023-03-05', '2023-03-19', 5, 1),
(6, '2023-03-20', '2023-04-03', 6, 19),
(7, '2023-04-01', '2023-04-15', 7, 9),
(8, '2023-04-10', '2023-04-24', 8, 6),
(9, '2023-05-01', '2023-05-15', 9, 14),
(10, '2023-05-20', '2023-06-03', 10, 3),
(11, '2023-06-10', '2023-06-24', 11, 17),
(12, '2023-07-01', '2023-07-15', 12, 8),
(13, '2023-07-20', '2023-08-03', 5, 1),
(14, '2023-08-10', '2023-08-24', 14, 4),
(15, '2023-09-01', '2023-09-15', 15, 3),
(16, '2023-09-15', '2023-09-29', 16, 2),
(17, '2023-10-05', NULL, 1, 8),
(18, '2023-11-01', '2023-11-15', 18, 20),
(19, '2023-11-20', '2023-12-04', 1, 11),
(20, '2023-12-10', '2023-12-24', 20, 20),
(21, '2025-05-01', NULL, 20 ,21),
(22, '2024-01-05', '2024-01-19', 22, 32), -- Completato
(23, '2024-01-10', '2024-01-24', 23, 25), -- Completato
(24, '2024-02-01', NULL, 24, 40), -- Attivo (book 40 non in prestito)
(25, '2024-02-15', '2024-03-01', 25, 16), -- Completato
(26, '2024-03-10', '2024-03-24', 26, 4),  -- Completato (book 4 libero)
(27, '2024-04-01', NULL, 27, 22), -- Attivo (book 22 non in prestito)
(28, '2024-04-15', '2024-04-29', 28, 35), -- Completato
(29, '2024-05-01', '2024-05-15', 29, 45), -- Completato
(30, '2024-05-20', NULL, 30, 38), -- Attivo (book 38 non in prestito)
(31, '2024-06-10', '2024-06-24', 31, 13), -- Completato (book 13 libero)
(32, '2024-07-01', '2024-07-15', 32, 47), -- Completato
(33, '2024-08-01', NULL, 33, 26), -- Attivo (book 26 non in prestito)
(34, '2024-09-01', '2024-09-15', 34, 1),  -- Completato (book 1 libero)
(35, '2024-10-01', '2024-10-15', 35, 2),  -- Completato (book 2 libero)
(36, '2024-11-01', NULL, 36, 30), -- Attivo (book 30 non in prestito)
(37, '2024-12-01', '2024-12-15', 37, 10), -- Completato (book 10 libero)
(38, '2025-01-10', NULL, 38, 27), -- Attivo (book 27 non in prestito)
(39, '2025-01-20', '2025-02-03', 39, 3),  -- Completato (book 3 libero)
(40, '2025-02-15', '2025-03-01', 40, 6),  -- Completato (book 6 libero)
(41, '2025-03-10', NULL, 1, 42),  -- Attivo (book 42 non in prestito, studente 1 libero)
(42, '2025-03-20', '2025-04-03', 2, 44),  -- Completato (studente 2 libero per nuovi prestiti)
(43, '2025-04-01', NULL, 3, 46),  -- Attivo (book 46 non in prestito)
(44, '2025-04-15', '2025-04-29', 4, 18), -- Completato (book 18 libero)
(45, '2025-05-10', NULL, 5, 33),  -- Attivo (book 33 non in prestito)
(46, '2025-05-12', '2025-05-26', 6, 23), -- Completato (book 23 libero)
(47, '2025-05-14', NULL, 7, 24),  -- Attivo (book 24 non in prestito)
(48, '2025-05-15', '2025-05-29', 8, 39), -- Completato (book 39 libero)
(49, '2025-05-16', NULL, 9, 41),  -- Attivo (book 41 non in prestito)
(50, '2025-05-17', '2025-05-31', 10, 5), -- Completato (book 5 libero)
(51, '2025-05-18', NULL, 11, 29); -- Attivo (book 29 non in prestito)
ALTER TABLE Loan ALTER COLUMN id RESTART WITH 52;


--Insert per Course
INSERT INTO Course (id, name, description) VALUES
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
ALTER TABLE Course ALTER COLUMN id RESTART WITH 11;


--Insert per iscrizione
INSERT INTO Enrollment (id_student,id_course) VALUES
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