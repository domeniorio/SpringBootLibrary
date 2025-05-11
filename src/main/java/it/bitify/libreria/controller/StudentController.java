package it.bitify.libreria.controller;

import it.bitify.libreria.entity.Student;
import it.bitify.libreria.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/student")
public class StudentController {

    @Autowired
    StudentService service;

    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable Long id){
        return service.getStudentById(id);
    }

    @PostMapping
    public void addStudent(@RequestBody Student student){
        service.saveStudent(student);
    }

    @PutMapping
    public void editStudent(@RequestBody Student student){
        service.updateStudent(student);
    }

    @GetMapping
    public Page<Student> getAllStudents(@RequestParam(defaultValue = "0") int page,
    @RequestParam(defaultValue = "5") int size,
    @RequestParam(defaultValue = "id") String sortBy,
    @RequestParam(defaultValue = "true") boolean ascending){
        Sort sort = ascending ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return service.getAllStudents(pageable);
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Long id){
        service.deleteStudent(id);
    }

    @GetMapping("/find")
    public Page<Student> getBynameAndsurname(@RequestParam String name,
    @RequestParam String surname,
    @RequestParam(defaultValue = "0") int page,
    @RequestParam(defaultValue = "5") int size,
    @RequestParam(defaultValue = "id") String sortBy,
    @RequestParam(defaultValue = "true") boolean ascending){
        Sort sort = ascending ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return service.getStudentBynameAndsurname(name,surname,pageable);
    }

}