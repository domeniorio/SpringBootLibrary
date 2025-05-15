package it.bitify.libreria.service.serviceImpl;

import it.bitify.libreria.model.dto.NameSurnameLoansDTO;
import it.bitify.libreria.model.dto.StudentStatsDTO;
import it.bitify.libreria.model.entity.Book;
import it.bitify.libreria.model.entity.Category;
import it.bitify.libreria.model.entity.Loan;
import it.bitify.libreria.model.entity.Student;
import it.bitify.libreria.exception.BookAlreadyLentException;
import it.bitify.libreria.exception.EntityNotFoundException;
import it.bitify.libreria.repository.BookRepo;
import it.bitify.libreria.repository.CategoryRepo;
import it.bitify.libreria.repository.LoanRepo;
import it.bitify.libreria.repository.StudentRepo;
import it.bitify.libreria.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Service
public class StudentServiceImpl implements StudentService {

    Logger logger = LogManager.getLogger(StudentServiceImpl.class);

    @Autowired
    private StudentRepo studentRepo;
    
    @Autowired
    private LoanRepo loanRepo;

    @Autowired
    private BookRepo bookRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public Student getStudentById(Long id) {
        return studentRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Valore non presente all'interno del database!"));
    }

    @Override
    public void saveStudent(Student Student) {
        studentRepo.save(Student);
    }

    @Override
    public void updateStudent(Student newStudent) {
        if(studentRepo.existsById(newStudent.getStudentId())) {
            studentRepo.save(newStudent);
        }
        else throw new EntityNotFoundException("Valore non presente all'interno del database!");
    }

    @Override
    public void deleteStudent(Long id) {
        if(studentRepo.existsById(id)) {
            studentRepo.deleteById(id);
        }
        else throw new EntityNotFoundException("Valore non presente all'interno del database!");
    }

    @Override
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
    public Loan loanBook(Long idStudent, Long idBook) {
        Student student = studentRepo.findById(idStudent).orElseThrow(() -> {
            EntityNotFoundException ex = new EntityNotFoundException("Studente non presente all'interno del database!");
            logger.error("Errore durante il recupero dello studente con ID {}", idStudent, ex);
            return ex;
        });
        logger.debug("Studente con ID {} trovato", idStudent);
        Book book = bookRepo.findById(idBook).orElseThrow(() -> {
            EntityNotFoundException ex = new EntityNotFoundException("Libro non presente all'interno del database!");
            logger.error("Errore durante il recupero del libro con ID {}", idBook, ex);
            return ex;
        });
        logger.info("Libro con ID {} trovato", idBook);

        Optional<Loan> queryRes = loanRepo.findByBookAndEndDateIsNull(book);
        if(queryRes.isPresent()) {
            BookAlreadyLentException ex = new BookAlreadyLentException("Il libro selezionato è stato già preso in prestito");
            logger.error("Errore durante il prestito del libro con ID {}", idBook, ex);
            throw ex;
        } else {
            Loan newLoan = new Loan();
            newLoan.setStartDate(LocalDate.now());
            newLoan.setBook(book);
            newLoan.setStudent(student);
            loanRepo.save(newLoan);
            logger.debug("nuovo prestito istanziato, con ID-LOAN: {}, ID-STUDENT: {}, ID-BOOK: {}", newLoan.getLoanId(), idStudent, idBook);
            return newLoan;
        }
    }

    @Override
    public Loan returnBook(Long idStudent, Long idBook) {
        Student student = studentRepo.findById(idStudent).orElseThrow(() -> {
            EntityNotFoundException ex = new EntityNotFoundException("Studente non presente all'interno del database!");
            logger.error("Errore durante il recupero dello studente con ID {}", idStudent, ex);
            return ex;
        });
        logger.debug("Studente con ID {} trovato", idStudent);
        Book book = bookRepo.findById(idBook).orElseThrow(() -> {
            EntityNotFoundException ex = new EntityNotFoundException("Libro non presente all'interno del database!");
            logger.error("Errore durante il recupero del libro con ID {}", idBook, ex);
            return ex;
        });
        logger.info("Libro con ID {} trovato", idBook);
        Loan loan = loanRepo.findByStudentAndBookAndEndDateIsNull(student,book).orElseThrow(() -> {
            EntityNotFoundException ex = new EntityNotFoundException("Non esiste un presttito per studente e libro selezionati");
            logger.error("Errore durante la resistuzione del libro con ID {} da parte dello studente con ID {}", idBook, idStudent, ex);
            return ex;
        });

        loan.setEndDate(LocalDate.now());
        loanRepo.save(loan);
        logger.debug("libro con ID: {} restituito da parte dello studente con ID: {}, prestito con ID: {} modificato", idBook, idStudent, loan.getLoanId());
        return loan;
    }

    @Override
    public StudentStatsDTO studentStats(Long idStudent) {
        if(!studentRepo.existsById(idStudent)) {
            EntityNotFoundException ex = new EntityNotFoundException("Studente non presente all'interno del database!");
            logger.error("Errore durante il recupero dello studente con ID {}", idStudent, ex);
            throw ex;
        }
        Long numLoans = studentRepo.findNumberLoans(idStudent);
        logger.debug("ID: {} - TOTAL_LOANS: {}", idStudent, numLoans);
        Long currLoans = studentRepo.findNumberOngoingLoans(idStudent);
        logger.debug("ID: {} - ONGOING_LOANS: {}", idStudent, currLoans);
        LocalDate lastDate = studentRepo.findLastLoanDate(idStudent);
        logger.debug("ID: {} - LAST_DATE: {}", idStudent, lastDate);
        List<Category> favourites = studentRepo.findTopCategoryByStudentId(idStudent);
        Category favouriteCategory = null;
        if(!favourites.isEmpty())  favouriteCategory = favourites.getFirst();
        logger.debug("ID: {} - FAVOURITE_CATEGORY: {}", idStudent, favouriteCategory);
        //provare a implementare questa operazione con una sola query
        return new StudentStatsDTO(numLoans, currLoans, lastDate, favouriteCategory);
    }

    @Override
    public Page<Book> suggestions(Long idStudent, Pageable pageable) {
        Category favouriteCategory = studentStats(idStudent).getFavouriteCategory();
        if(favouriteCategory == null) {
            EntityNotFoundException ex = new EntityNotFoundException("Lo studente non ha una categoria preferita");
            logger.error("Lo studente con ID {} non ha prestiti", idStudent, ex);
            throw ex;
        }

        return loanRepo.findByCategoryOrderedByLoans(favouriteCategory, idStudent, pageable);
    }
}
