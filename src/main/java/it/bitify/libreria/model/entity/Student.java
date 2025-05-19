package it.bitify.libreria.model.entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false, length = 30)
    private String name;

    @Column(name = "surname", nullable = false, length = 30)
    private String surname;

    @Column(name = "class", nullable = false, length = 2)
    private String schoolClass;

    @Column(name = "email", nullable = false, length = 30, unique = true)
    private String email;

    @OneToMany(mappedBy = "student", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    private List<Loan> loans;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="idCard")
    private Card card;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(name = "ENROLLMENT", joinColumns = @JoinColumn(name = "idStudent"),
            inverseJoinColumns = @JoinColumn(name = "idCourse"))
    private Set<Course> courses;


    public Student(Long id, String name, String surname, String schoolClass, String email) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.schoolClass = schoolClass;
        this.email = email;
    }

    public Student(String name, String surname, String schoolClass, String email) {
        this.name = name;
        this.surname = surname;
        this.schoolClass = schoolClass;
        this.email = email;
    }

    public Student(Set<Course> courses, Card card, List<Loan> loans, String email, String schoolClass, String surname, String name, Long id) {
        this.courses = courses;
        this.card = card;
        this.loans = loans;
        this.email = email;
        this.schoolClass = schoolClass;
        this.surname = surname;
        this.name = name;
        this.id = id;
    }

    public Student() {
    }

    public Long getId() {
        return id;
    }

    public void seId(Long id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getSchoolClass() {
        return schoolClass;
    }

    public void setSchoolClass(String schoolClass) {
        this.schoolClass = schoolClass;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
