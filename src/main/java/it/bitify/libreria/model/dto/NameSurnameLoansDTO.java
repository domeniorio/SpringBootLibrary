package it.bitify.libreria.model.dto;

public class NameSurnameLoansDTO {
    private String name;
    private String surname;
    private Long loans;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Long getLoans() {
        return loans;
    }

    public void setLoans(Long loans) {
        this.loans = loans;
    }

    public NameSurnameLoansDTO(String name, String surname, Long loans) {
        this.name = name;
        this.surname = surname;
        this.loans = loans;
    }
}
