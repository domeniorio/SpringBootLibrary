package it.bitify.libreria.repository;

import it.bitify.libreria.model.dto.CategoriesBookCountDTO;
import it.bitify.libreria.model.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CategoryRepo extends JpaRepository<Category, Long> {
    @Query("SELECT NEW it.bitify.libreria.model.dto.CategoriesBookCountDTO(c.name, COUNT(b.id)) FROM Category c LEFT JOIN c.books b GROUP BY c.id")
    Page<CategoriesBookCountDTO> findCategoriesWithBookCount(Pageable pageable);


    @Query(value = """
        SELECT DISTINCT c.*
        FROM category c
        JOIN book b ON c.id = b.id_category
        JOIN loan l ON b.id = l.id_book
        JOIN student s ON l.id_student = s.id
        WHERE NOT EXISTS (
            SELECT 1
            FROM loan l2
            JOIN book b2 ON b2.id = l2.id_book
            JOIN category c2 ON c2.id = b2.id_category
            JOIN student s2 ON s2.id = l2.id_student
            WHERE c2.id = c.id
            AND s2.class <> :targetClass
        )
        """, countQuery = """
        SELECT COUNT(DISTINCT c.id)
        FROM category c
        JOIN book b ON c.id = b.id_category
        JOIN loan l ON b.id = l.id_book
        JOIN student s ON l.id_student = s.id
        WHERE NOT EXISTS (
            SELECT 1
            FROM loan l2
            JOIN book b2 ON b2.id = l2.id_book
            JOIN category c2 ON c2.id = b2.id_category
            JOIN student s2 ON s2.id = l2.id_student
            WHERE c2.id = c.id
            AND s2.class <> :targetClass
        )
        """, nativeQuery = true)
    Page<Category> findCategoriesWithBooksLentOnlyByClass(@Param("targetClass") String targetClass, Pageable pageable);
}



/*
    @Query("SELECT DISTINCT c " +
           "FROM Category c " +
           "JOIN c.books b " +
           "JOIN b.loans l " +
           "JOIN l.student s_outer " +
           "WHERE NOT EXISTS (" +
           "    SELECT l2.id " +
           "    FROM Loan l2 " +
           "    JOIN l2.book b2 " +
           "    JOIN b2.category c2 " +
           "    JOIN l2.student s2 " +
           "    WHERE c2.id = c.id " +
           "    AND s2.studentClass <> :targetClass" +
           ")")
*/