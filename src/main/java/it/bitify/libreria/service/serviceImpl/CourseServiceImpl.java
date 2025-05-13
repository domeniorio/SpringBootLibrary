package it.bitify.libreria.service.serviceImpl;

import it.bitify.libreria.model.entity.Course;
import it.bitify.libreria.exception.EntityNotFoundException;
import it.bitify.libreria.repository.CourseRepo;
import it.bitify.libreria.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepo repo;

    @Override
    public Course getCourseById(Long id) {
        return repo.findById(id).orElseThrow(() -> new EntityNotFoundException("Valore non presente all'interno del database!"));
    }

    @Override
    public void saveCourse(Course Course) {
        repo.save(Course);
    }

    @Override
    public void updateCourse(Course newCourse) {
        if(repo.existsById(newCourse.getIdCourse())){
            repo.save(newCourse);
        }
        else new EntityNotFoundException("Valore non presente all'interno del database!");
    }

    @Override
    public void deleteCourse(Long id) {
        if(repo.existsById(id))  repo.deleteById(id);
        else throw new EntityNotFoundException("Valore non presente all'interno del database!");
    }

    @Override
    public Page<Course> getAllCourses(Pageable pageable) {
        return repo.findAll(pageable);
    }
}
