/*
Elenca tutti gli studenti.
Elenca tutti i libri.
Elenca tutte le categorie disponibili.
Mostra nome, cognome e email di tutti gli studenti.
Elenca tutti i libri pubblicati dopo il 2020.
Elenca tutti i prestiti con data di fine nulla (libri ancora in prestito).
Mostra tutti gli studenti della classe “3A”.
Elenca i libri ordinati per anno di pubblicazione decrescente.
Elenca i libri appartenenti alla categoria “Romanzo”.
Mostra nome e cognome degli studenti con tessera rilasciata nel 2023.
Elenca i prestiti con nome studente, titolo del libro e date di inizio/fine.
Elenca le categorie con il numero di libri per ciascuna.
Elenca tutti gli studenti che non hanno mai fatto un prestito.
Mostra i libri che non sono mai stati presi in prestito.
Elenca gli studenti che hanno preso più di 3 libri in totale.
Elenca i titoli dei libri presi in prestito nell’ultimo mese.
Elenca gli studenti che hanno preso in prestito almeno un libro della categoria “Manuale”.
Mostra lo studente con più prestiti effettuati in assoluto.
Elenca i libri con numero di prestiti maggiore della media dei prestiti per libro.
Elenca le categorie che hanno libri presi in prestito solo da studenti della classe “5B”.
*/


--mostra tutti i libri pubblicati dopo il 2020
SELECT * from Book
WHERE Book.publish_year > 2020;

--mostra tutti i prestiti ancora in corso
SELECT * FROM Loan
WHERE Loan.end_date is null;

--mostra tutti gli studenti della classe 3A
SELECT * FROM student
WHERE class='3A';

--mostra tutti i libri appartenenti alla categoria romanzo
SELECT * from Book
JOIN Category on Book.id_category = Category.id
WHERE Category.name = 'Romanzo'

--mostra nome e cognome di tutti gli studenti con tessera rilasciata nel 2023
SELECT name, surname from STUDENT
JOIN Card on Student.id_card = Card.id
WHERE release_date >= '2023-01-01' and release_date <= '2024-01-01';
