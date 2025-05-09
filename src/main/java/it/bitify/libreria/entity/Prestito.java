package it.bitify.libreria.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "Prestito")
public class Prestito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "dataInizio", nullable = false)
    private LocalDate dataInizio;

    @Column(name = "dataFine")
    private LocalDate dataFine;

    @ManyToOne
    @JoinColumn(name="idStudente")
    private Studente studente;

    @ManyToOne
    @JoinColumn(name="idLibro")
    private Libro libro;


    public Long getCodicePrestito() {
        return id;
    }

    public void setCodicePrestito(Long id) {
        this.id = id;
    }

    public Libro getLibro() { return libro; }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public Studente getStudente() {
        return studente;
    }

    public void setStudente(Studente studente) {
        this.studente = studente;
    }

    public LocalDate getDataInizio() {
        return dataInizio;
    }

    public void setDataInizio(LocalDate dataInizio) {
        this.dataInizio = dataInizio;
    }

    public LocalDate getDataFine() {
        return dataFine;
    }

    public void setDataFine(LocalDate dataFine) {
        this.dataFine = dataFine;
    }
}
