package it.bitify.libreria.repository;

import it.bitify.libreria.model.entity.Book;

import it.bitify.libreria.model.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface BookRepo extends JpaRepository<Book, Long> {
    Page<Book> findByTitleContaining(String infix, Pageable pageable);
    Page<Book> findByYearGreaterThan(Integer year, Pageable pageable);
    Page<Book> findByCategory_Name(String name, Pageable pageable);

    @Query("SELECT b FROM Book b LEFT JOIN b.loans l WHERE l.id IS NULL")
    Page<Book> findBookWithoutLoan(Pageable pageable);

    @Query("SELECT b.title FROM Book b JOIN b.loans l WHERE l.startDate >= :start GROUP BY b.title")
    Page<String> findBookLoanInterval(@Param("start")LocalDate start, Pageable pageable);

    @Query(value = """
    SELECT b.*
    FROM book b
    JOIN loan l ON b.id = l.id_book
    GROUP BY b.id
    HAVING COUNT(l.id) > (
        SELECT AVG(loan_count)
        FROM (
            SELECT COUNT(*) AS loan_count
            FROM loan
            GROUP BY id_book
        ) AS loan_counts
    )
    """, countQuery = """
    SELECT COUNT(*) FROM (
        SELECT b.id
        FROM book b
        JOIN loan l ON b.id = l.id_book
        GROUP BY b.id
        HAVING COUNT(l.id) > (
            SELECT AVG(loan_count)
            FROM (
                SELECT COUNT(*) AS loan_count
                FROM loan
                GROUP BY id_book
            ) AS loan_counts
        )
    ) AS filtered_books
    """, nativeQuery = true)
    Page<Book> findBooksWithLoanAboveAverage(Pageable pageable);

    @Query("""
            SELECT b
            FROM Book b
            JOIN Loan l ON l.book = b
            WHERE b.category = :category
            AND b NOT IN (
                SELECT l2.book
                FROM Loan l2
                WHERE l2.student.id = :studentId OR l2.endDate is NULL
            )
            GROUP BY b
            ORDER BY COUNT(l.id) DESC
            """)
    Page<Book> findByCategoryOrderedByLoans(@Param("category") Category category, @Param("studentId") Long studentId, Pageable pageable);

}
