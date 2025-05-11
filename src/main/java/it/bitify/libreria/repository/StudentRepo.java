package it.bitify.libreria.repository;

import it.bitify.libreria.entity.Student;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepo extends JpaRepository<Student, Long> {
    Page<Student> findByNameAndSurname(String name, String surname, Pageable pageable);
}
