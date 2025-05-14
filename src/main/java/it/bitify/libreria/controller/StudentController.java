package it.bitify.libreria.controller;

import it.bitify.libreria.model.dto.IdStudentBookDTO;
import it.bitify.libreria.model.dto.NameSurnameLoansDTO;
import it.bitify.libreria.model.dto.StudentStatsDTO;
import it.bitify.libreria.model.entity.Book;
import it.bitify.libreria.model.entity.Loan;
import it.bitify.libreria.model.entity.Student;
import it.bitify.libreria.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;


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

    @GetMapping("/without-loan")
    public Page<Student> getStudentWithoutLoan(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "true") boolean ascending){
        Sort sort = ascending ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return service.findStudentWithoutLoan(pageable);
    }

    @GetMapping("/by-class")
    public Page<Student> findStudentBySchoolClass(@RequestParam String schoolClass,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "true") boolean ascending){
        Sort sort = ascending ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return service.findBySchoolClass(schoolClass, pageable);
    }

    @GetMapping("/by-card-release")
    public Page<Student> getByCard_ReleaseDateBetween(@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "true") boolean ascending){
        Sort sort = ascending ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return service.findByCard_ReleaseDateBetween(start, end ,pageable);
    }

    @GetMapping("/min-loan")
    public Page<NameSurnameLoansDTO> getStudentMoreThanLoan(@RequestParam int minLoan,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "true") boolean ascending) {
        Sort sort = ascending ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);

        return service.findStudentMoreThanLoans(minLoan, pageable);
    }

    @GetMapping("/by-category-name")
    public Page<Student> getStudentByCategoryName(@RequestParam String categoryName,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "true") boolean ascending){
        Sort sort = ascending ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return service.findStudentLoanCategory(categoryName, pageable);
    }

    @GetMapping("/by-loan-count")
    public Page<Student> getStudentByLoanCount(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "true") boolean ascending){
        Sort sort = ascending ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return service.findStudentsOrderedByLoanCount(pageable);
    }

    @PostMapping("/loan-book")
    public Loan loanBook(@RequestBody IdStudentBookDTO body){
        return service.loanBook(body.getIdStudent(), body.getIdBook());
    }

    @PostMapping("/return-book")
    public Loan returnBook(@RequestBody IdStudentBookDTO body){
        return service.returnBook(body.getIdStudent(), body.getIdBook());
    }

    @GetMapping("stats")
    public StudentStatsDTO studentStats(@RequestParam Long idStudent){
        return service.studentStats(idStudent);
    }

    @GetMapping("suggestions")
    public Page<Book> studentSuggestion(@RequestParam Long idStudent,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "true") boolean ascending){
        Sort sort = ascending ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return service.suggestions(idStudent, pageable);
    }
}
