package it.bitify.libreria.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "Prestito")
public class Prestito {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "codicePrestito")
    private int codicePrestito;

    @Column(name = "dataInizio", nullable = false)
    private LocalDate dataInizio;

    @Column(name = "dataFine")
    private LocalDate dataFine;

    @ManyToOne
    @JoinColumn(name="codiceStudente")
    private Studente studente;

    @ManyToOne
    @JoinColumn(name="codiceLibro")
    private Libro libro;


    public int getCodicePrestito() {
        return codicePrestito;
    }

    public void setCodicePrestito(int codicePrestito) {
        this.codicePrestito = codicePrestito;
    }

    public Libro getLibro() { return libro; }

    public void setLibro(Libro Libro) {
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
