package it.bitify.libreria.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "Tessera")
public class Tessera {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "dataRilascio", nullable = false)
    private LocalDate dataRilascio;

    @OneToOne(mappedBy = "tessera")
    private Studente studente;

    public void setDataRilascio(LocalDate dataRilascio) {
        this.dataRilascio = dataRilascio;
    }

    public void setCodiceTessera(Long id) {
        this.id = id;
    }

    public LocalDate getDataRilascio() {
        return dataRilascio;
    }

    public Long getCodiceTessera() {
        return id;
    }
}
