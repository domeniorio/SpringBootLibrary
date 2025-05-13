package it.bitify.libreria.repository;

import it.bitify.libreria.model.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepo extends JpaRepository<Course, Long> {
}
