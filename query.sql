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

--elenca le categorie con il numero di libri per ciascuna
SELECT category.name AS category_name, COUNT(book.id) AS number_of_books
FROM category
LEFT JOIN book ON category.id = book.id_category
GROUP BY category.id;

--elenca tutti gli studenti che non hanno mai fatto un prestito
SELECT * from student
LEFT JOIN loan on student.id = loan.id_student
WHERE loan.id IS NULL;

--elenca tutti i libri che non sono mai stati presi in prestito
SELECT * FROM book
LEFT JOIN Loan on book.id = loan.id_book
WHERE loan.id IS NULL

--elenca gli studenti che hanno preso più di 2 libri
SELECT s.name, s.surname, COUNT(l.id)
FROM student s
JOIN loan l on s.id = l.id_student
GROUP BY s.id
HAVING COUNT(l.id)>=2

--elenca i titoli dei libri presi in prestito nell'ultimo mese

SELECT b.title
FROM book b
JOIN loan l ON l.id_book = b.id
WHERE l.end_date >= CURDATE() - INTERVAL 1 MONTH;


--Elenca gli studenti che hanno preso in prestito almeno un libro della categoria “Manuale”.
SELECT * FROM student s
JOIN loan l ON s.id  = l.id_student
JOIN book b ON b.id = l.id_book
JOIN category c on c.id = b.id_category
WHERE c.name='Storico'  

--Elenca lo studente con più prestiti in assoluto
SELECT s.*, COUNT(l.id) AS total_loans
FROM student s
JOIN loan l ON s.id = l.id_student
GROUP BY s.id
ORDER BY total_loans DESC
LIMIT 1;

--Elenca i libri con numero di prestiti maggiore della media
SELECT b.id, b.title, COUNT(l.id) AS total_loans
FROM book b
JOIN loan l ON b.id = l.id_book
GROUP BY b.id, b.title
HAVING COUNT(l.id) > (
    SELECT AVG(loans_per_book.total)
    FROM (
        SELECT COUNT(*) AS total
        FROM loan
        GROUP BY id_book
    ) AS loans_per_book
);

--Elenca le categorie che hanno libri presi in prestito solo da studenti della classe “5B”.
