package it.bitify.libreria.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "Libro")
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "codiceLibro")
    private int codiceLibro;

    @Column(name = "titolo", nullable = false, unique = true)
    private String titolo;

    @Column(name = "autore", nullable = false)
    private String autore;

    @Column(name = "anno")
    private Integer anno;

    @ManyToOne
    @JoinColumn(name="codiceCategoria")
    @JsonBackReference
    private Categoria categoria;

    @OneToMany(mappedBy = "libro", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    private List<Prestito> prestiti;

    public long getCodiceLibro() {
        return this.codiceLibro;
    }

    public String getAuthor() {
        return autore;
    }

    public String getTitle() {
        return titolo;
    }

    public void setAuthor(String author) {
        this.autore = author;
    }

    public void setCodiceLibro(int id) {
        this.codiceLibro = id;
    }

    public void setTitle(String title) {
        this.titolo = title;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Categoria getCategoria() {
        return this.categoria;
    }

    public Integer getAnno() {
        return anno;
    }

    public void setAnno(int anno) {
        this.anno = anno;
    }
}