package it.bitify.libreria.repository;

import it.bitify.libreria.entity.Student;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface StudentRepo extends JpaRepository<Student, Long> {
    Page<Student> findByNameAndSurname(String name, String surname, Pageable pageable);
    Page<Student> findBySchoolClass(String schoolClass, Pageable pageable);
    Page<Student> findByCard_ReleaseDateBetween(LocalDate start, LocalDate end, Pageable pageable);
}
