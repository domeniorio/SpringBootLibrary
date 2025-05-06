package it.bitify.libreria.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Categoria")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "codiceCategoria")
    private int codiceCategoria;

    @Column(name = "nome", nullable = false)
    private String nome;

    @OneToMany(mappedBy = "categoria", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JsonManagedReference
    private List<Libro> libri;


    public int getCodiceCategoria() {
        return codiceCategoria;
    }

    public String getNome() {
        return nome;
    }

    public void setCodiceCategoria(int codiceCategoria) {
        this.codiceCategoria = codiceCategoria;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Libro> getLibri() { return libri; }

    public void setLibri(List<Libro> libri) { this.libri = libri; }
}
