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

    Category findByName(String name);


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
            "    AND s2.schoolClass <> :targetClass" +
            ")")
    Page<Category> findCategoriesWithBooksLentOnlyByClass(@Param("targetClass") String targetClass, Pageable pageable);
}



/*

*/