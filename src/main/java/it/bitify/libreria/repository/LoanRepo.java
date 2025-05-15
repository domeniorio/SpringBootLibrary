package it.bitify.libreria.repository;

import it.bitify.libreria.model.entity.Book;
import it.bitify.libreria.model.entity.Category;
import it.bitify.libreria.model.entity.Loan;
import it.bitify.libreria.model.entity.Student;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface LoanRepo extends JpaRepository<Loan, Long> {
    Page<Loan> findByEndDateIsNull(Pageable pageable);
    Optional<Loan> findByBookAndEndDateIsNull(Book book);
    Optional<Loan> findByStudentAndBookAndEndDateIsNull(Student student, Book book);

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
