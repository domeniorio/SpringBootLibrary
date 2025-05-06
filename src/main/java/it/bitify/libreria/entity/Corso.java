package it.bitify.libreria.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "Corso")
public class Corso {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "codiceCorso")
    private int codiceCorso;

    @Column(name = "nome", nullable = false, unique = true)
    private String nome;

    @Column(name = "descrizione")
    private String descrizione;

    @ManyToMany(mappedBy = "corsi", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    private Set<Studente> studenti;

    public int getCodiceCorso() {
        return codiceCorso;
    }

    public void setCodiceCorso(int codiceCorso) {
        this.codiceCorso = codiceCorso;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public Set<Studente> getStudenti() {
        return studenti;
    }

    public void setStudenti(Set<Studente> studenti) {
        this.studenti = studenti;
    }

    public void addStudente(Studente studente){
        this.studenti.add(studente);
    }

    public void removeStudente(Studente studente){
        this.studenti.remove(studente);
    }
}
