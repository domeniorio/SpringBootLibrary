package it.bitify.libreria.service.serviceImpl;
import it.bitify.libreria.model.dto.CategoryOrderingDTO;
import it.bitify.libreria.model.dto.NameSurnameLoansDTO;
import it.bitify.libreria.model.dto.StudentStatsDTO;
import it.bitify.libreria.model.entity.Book;
import it.bitify.libreria.model.entity.Category;
import it.bitify.libreria.model.entity.Loan;
import it.bitify.libreria.model.entity.Student;
import it.bitify.libreria.exception.BookAlreadyLentException;
import it.bitify.libreria.exception.EntityNotFoundException;
import it.bitify.libreria.repository.StudentRepo;
import it.bitify.libreria.service.BookService;
import it.bitify.libreria.service.LoanService;
import it.bitify.libreria.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Service
public class StudentServiceImpl implements StudentService {

    private Logger logger = LogManager.getLogger(StudentServiceImpl.class);

    @Autowired
    private StudentRepo studentRepo;
    
    @Autowired
    private LoanService loanService;

    @Autowired
    private BookService bookService;

    @Override
    @Cacheable(value = "students", key = "#id")
    public Student getStudentById(Long id) {
        Student student = studentRepo.findById(id).orElseThrow(() -> {
            EntityNotFoundException ex = new EntityNotFoundException("Studente non presente all'interno del database!");
            logger.error("Errore durante il recupero dello studente con ID {}", id, ex);
            return ex;
        });
        logger.debug("Studente trovato con ID: {}", id);
        return student;
    }

    @Override
    @CacheEvict(value="students", key=" 'allStudents' ")
    public void saveStudent(Student student) {
        logger.debug("Studente salvato con ID: {}", student.getId());
        studentRepo.save(student);
    }

    @Override
    @Caching(put = {
            @CachePut(value="students", key = "#newStudent.id")
            },
            evict = {
            @CacheEvict(value="students", key=" 'allStudents' ")
            }
    )
    public Student updateStudent(Student newStudent) {
        if(studentRepo.existsById(newStudent.getId())) {
            logger.debug("Studente modificato con ID: {}", newStudent.getId());
            studentRepo.save(newStudent);
            return newStudent;
        }
        else{
            EntityNotFoundException ex = new EntityNotFoundException("Valore non presente all'interno del database!");
            logger.error("Errore durante il recupero dello studente con ID {}", newStudent.getId(), ex);
            throw ex;
        }
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value="students", key="#id"),
            @CacheEvict(value="students", key=" 'allStudents' ")
    })
    public void deleteStudent(Long id) {
        if(studentRepo.existsById(id)) {
            logger.debug("Studente eliminato con ID: {}", id);
            studentRepo.deleteById(id);
        }
        else{
            EntityNotFoundException ex = new EntityNotFoundException("Valore non presente all'interno del database!");
            logger.error("Errore durante il recupero dello studente con ID {}", id, ex);
            throw ex;
        }
    }

    @Override
    @Cacheable(value = "students", key = " 'allStudents' ")
    public Page<Student> getAllStudents(Pageable pageable) {
        return studentRepo.findAll(pageable);
    }

    @Override
    public Page<Student> findBySchoolClass(String schoolClass, Pageable pageable) {
        return studentRepo.findBySchoolClass(schoolClass, pageable);
    }

    @Override
    public Page<Student> findByCard_ReleaseDateBetween(LocalDate start, LocalDate end, Pageable pageable) {
        if (start == null) {
            start = LocalDate.of(1900, 1, 1);
        }
        if (end == null){
            end = LocalDate.of(2100, 1, 1);
        }
        return studentRepo.findByCard_ReleaseDateBetween(start, end, pageable);
    }

    @Override
    public Page<Student> findStudentWithoutLoan(Pageable pageable) {
        return studentRepo.findStudentWithoutLoan(pageable);
    }

    @Override
    public Page<NameSurnameLoansDTO> findStudentMoreThanLoans(int minLoan, Pageable pageable) {
        return studentRepo.findStudentMoreThanLoans(minLoan, pageable);
    }

    @Override
    public Page<Student> findStudentLoanCategory(String categoryName, Pageable pageable) {
        return studentRepo.findStudentLoanCategory(categoryName, pageable);
    }

    @Override
    public Page<Student> findStudentsOrderedByLoanCount(Pageable pageable) {
        return studentRepo.findStudentsOrderedByLoanCount(pageable);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "loans", key = " 'allLoans' "),
            @CacheEvict(value = "loans", key = " 'ongoing' "),
            @CacheEvict(value = "stats", key = "#idStudent")
    })
    public Loan loanBook(Long idStudent, Long idBook) {
        Student student = getStudentById(idStudent);
        logger.debug("Studente con ID {} trovato", idStudent);

        Book book = bookService.findBookById(idBook);
        logger.info("Libro con ID {} trovato", idBook);

        if(loanService.bookAlreadyLent(book)) {
            BookAlreadyLentException ex = new BookAlreadyLentException("Il libro selezionato è stato già preso in prestito");
            logger.error("Errore durante il prestito del libro con ID {}", idBook, ex);
            throw ex;
        } else {
            Loan newLoan = new Loan();
            newLoan.setStartDate(LocalDate.now());
            newLoan.setBook(book);
            newLoan.setStudent(student);
            loanService.saveLoan(newLoan);
            logger.debug("nuovo prestito istanziato, con ID-LOAN: {}, ID-STUDENT: {}, ID-BOOK: {}", newLoan.getLoanId(), idStudent, idBook);
            return newLoan;
        }
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "loans", key = " 'allLoans' "),
            @CacheEvict(value = "loans", key = " 'ongoing' "),
            @CacheEvict(value = "stats", key = "#idStudent")
    })
    public Loan returnBook(Long idStudent, Long idBook) {
        Student student = getStudentById(idStudent);
        logger.debug("Studente con ID {} trovato", idStudent);
        Book book = bookService.findBookById(idBook);
        logger.info("Libro con ID {} trovato", idBook);
        Loan loan = loanService.loanExists(book,student).orElseThrow(() -> {
            EntityNotFoundException ex = new EntityNotFoundException("Non esiste un presttito per studente e libro selezionati");
            logger.error("Errore durante la resistuzione del libro con ID {} da parte dello studente con ID {}", idBook, idStudent, ex);
            return ex;
        });

        loan.setEndDate(LocalDate.now());
        loanService.saveLoan(loan);
        logger.debug("libro con ID: {} restituito da parte dello studente con ID: {}, prestito con ID: {} modificato", idBook, idStudent, loan.getLoanId());
        return loan;
    }

    @Override
    @Cacheable(value="stats", key="#idStudent")
    public StudentStatsDTO studentStats(Long idStudent) {
        if(!studentRepo.existsById(idStudent)) {
            EntityNotFoundException ex = new EntityNotFoundException("Studente non presente all'interno del database!");
            logger.error("Errore durante il recupero dello studente con ID {}", idStudent, ex);
            throw ex;
        }

        StudentStatsDTO studentStats = studentRepo.findLoanStats(idStudent);
        List<CategoryOrderingDTO> categoryStats = studentRepo.findTopCategoryByStudentId(idStudent);
        Category favouriteCategory = null;
        if(!categoryStats.isEmpty()){
            Comparator<LocalDate> lastDateComparator = Comparator.nullsLast(Comparator.<LocalDate>naturalOrder()).reversed();
            categoryStats = categoryStats.stream()
                            .sorted(Comparator.comparing(CategoryOrderingDTO::getBookCount).reversed()
                            .thenComparing(CategoryOrderingDTO::getLastDate, lastDateComparator))
                            .toList();
            favouriteCategory = categoryStats.getFirst().getCategory();
            logger.debug("ID: {} - FAVOURITE_CATEGORY: {}", idStudent, favouriteCategory.getName());
        }

        studentStats.setFavouriteCategory(favouriteCategory);
        return studentStats;
    }

    @Override
    public Page<Book> suggestions(Long idStudent, Pageable pageable) {
        Category favouriteCategory = studentStats(idStudent).getFavouriteCategory();
        if(favouriteCategory == null) {
            EntityNotFoundException ex = new EntityNotFoundException("Lo studente non ha una categoria preferita");
            logger.error("Lo studente con ID {} non ha prestiti", idStudent, ex);
            throw ex;
        }

        return bookService.bookSuggestions(favouriteCategory, idStudent, pageable);
    }
}
