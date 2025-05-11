package it.bitify.libreria.service.serviceImpl;

import it.bitify.libreria.entity.Student;
import it.bitify.libreria.exception.EntityNotFoundException;
import it.bitify.libreria.repository.StudentRepo;
import it.bitify.libreria.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;



@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepo repo;

    @Override
    public Student getStudentById(Long id) {
        return repo.findById(id).orElseThrow(() -> new EntityNotFoundException("Valore non presente all'interno del database!"));
    }

    @Override
    public void saveStudent(Student Student) {
        repo.save(Student);
    }

    @Override
    public void updateStudent(Student newStudent) {
        if(repo.existsById(newStudent.getStudentId())) {
            repo.save(newStudent);
        }
        else throw new EntityNotFoundException("Valore non presente all'interno del database!");
    }

    @Override
    public void deleteStudent(Long id) {
        if(repo.existsById(id)) {
            repo.deleteById(id);
        }
        else throw new EntityNotFoundException("Valore non presente all'interno del database!");
    }

    @Override
    public Page<Student> getAllStudents(Pageable pageable) {
        return repo.findAll(pageable);
    }

    @Override
    public Page<Student> getStudentBynameAndsurname(String name, String surname, Pageable pageable) {
       return repo.findByNameAndSurname(name, surname, pageable);
    }
}
