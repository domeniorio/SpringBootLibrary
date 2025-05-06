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

--Insert per Categoria
INSERT INTO Categoria (codice_categoria, nome) VALUES
(1, 'Narrativa'),
(2, 'Saggistica'),
(3, 'Fantascienza'),
(4, 'Biografia'),
(5, 'Storico');

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
