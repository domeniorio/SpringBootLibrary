package it.bitify.libreria.entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Studente")
public class Studente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome", nullable = false, length = 30)
    private String nome;

    @Column(name = "cognome", nullable = false, length = 30)
    private String cognome;

    @Column(name = "classe", nullable = false, length = 2)
    private String classe;

    @Column(name = "email", nullable = false, length = 30, unique = true)
    private String email;

    @OneToMany(mappedBy = "studente", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    private List<Prestito> prestiti;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="idTessera")
    private Tessera tessera;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(name = "ISCRIZIONE", joinColumns = @JoinColumn(name = "idStudente"),
            inverseJoinColumns = @JoinColumn(name = "idCorso"))
    private Set<Corso> corsi;

    public Long getCodiceStudente() {
        return id;
    }

    public void setCodiceStudente(Long id) {
        this.id = id;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Tessera getTessera() {
        return tessera;
    }

    public void setTessera(Tessera tessera) {
        this.tessera = tessera;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
