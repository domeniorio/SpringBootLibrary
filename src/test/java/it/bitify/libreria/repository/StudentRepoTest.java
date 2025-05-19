package it.bitify.libreria.repository;

import it.bitify.libreria.model.entity.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class StudentRepoTest {

    @Autowired
    private StudentRepo studentRepo;
    @Autowired
    private LoanRepo loanRepo;

    @BeforeEach
    void setUp() {

        loanRepo.deleteAll();
        studentRepo.deleteAll();

        studentRepo.save(new Student("Alice", "Alice", "4B" ,"alice@example.com"));
        studentRepo.save(new Student("Bob", "bob", "4B", "bob@example.com"));
        studentRepo.save(new Student("Charlie", "charlie", "4B", "charlie@example.com"));
        studentRepo.save(new Student("David", "david", "4B", "david@example.com"));
        studentRepo.save(new Student("Eve", "Eve", "4B", "eve@example.com"));
    }

    @Test
    void testFindAll_FirstPage_DefaultSort() {
        Pageable pageable = PageRequest.of(0, 2);

        Page<Student> studentsPage = studentRepo.findAll(pageable);
        assertNotNull(studentsPage);
        assertEquals(5, studentsPage.getTotalElements());
        assertEquals(3, studentsPage.getTotalPages());
        assertEquals(0, studentsPage.getNumber());
        assertEquals(2, studentsPage.getSize());
        assertTrue(studentsPage.isFirst());
        assertFalse(studentsPage.isLast());

        assertEquals("Alice", studentsPage.getContent().get(0).getName());
        assertEquals("Bob", studentsPage.getContent().get(1).getName());
    }

}
