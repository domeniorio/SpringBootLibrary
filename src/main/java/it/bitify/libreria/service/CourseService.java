package it.bitify.libreria.service;

import it.bitify.libreria.model.entity.Course;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CourseService {

    Course getCourseById(Long id);

    void saveCourse(Course course);

    void updateCourse(Course course);

    void deleteCourse(Long id);

    Page<Course> getAllCourses(Pageable pageable);
}
