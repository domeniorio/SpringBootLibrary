package it.bitify.libreria.controller;

import it.bitify.libreria.entity.Course;
import it.bitify.libreria.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/course")
public class CourseController {

    @Autowired
    CourseService service;

    @GetMapping("/{id}")
    public Course getCourseById(@PathVariable Long id){
        return service.getCourseById(id);
    }

    @PostMapping
    public void addCourse(@RequestBody Course course){
        service.saveCourse(course);
    }

    @PutMapping
    public void editCourse(@RequestBody Course course){
        service.updateCourse(course);
    }

    @GetMapping
    public Page<Course> getAllCourses(@RequestParam(defaultValue = "0") int page,
    @RequestParam(defaultValue = "5") int size,
    @RequestParam(defaultValue = "id") String sortBy,
    @RequestParam(defaultValue = "true") boolean ascending){
        Sort sort = ascending ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return service.getAllCourses(pageable);
    }

    @DeleteMapping("/{id}")
    public void deleteCourse(@PathVariable Long id){
        service.deleteCourse(id);
    }
}
