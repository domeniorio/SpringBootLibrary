package it.bitify.libreria.model.dto;

import it.bitify.libreria.model.entity.Category;

import java.time.LocalDate;

public class StudentStatsDTO {
    private Long totalLoans;
    private Long currentLoans;
    private LocalDate lastLoanDate;
    private Category favouriteCategory;

    public StudentStatsDTO(Long totalLoans, Long currentLoans, LocalDate lastLoanDate, Category favouriteCategory) {
        this.totalLoans = totalLoans;
        this.currentLoans = currentLoans;
        this.lastLoanDate = lastLoanDate;
        this.favouriteCategory = favouriteCategory;
    }

    public StudentStatsDTO() {
    }

    public Long getTotalLoans() {
        return totalLoans;
    }

    public void setTotalLoans(Long totalLoans) {
        this.totalLoans = totalLoans;
    }

    public Long getCurrentLoans() {
        return currentLoans;
    }

    public void setCurrentLoans(Long currentLoans) {
        this.currentLoans = currentLoans;
    }

    public LocalDate getLastLoanDate() {
        return lastLoanDate;
    }

    public void setLastLoanDate(LocalDate lastLoanDate) {
        this.lastLoanDate = lastLoanDate;
    }

    public Category getFavouriteCategory() {
        return favouriteCategory;
    }

    public void setFavouriteCategory(Category favouriteCategory) {
        this.favouriteCategory = favouriteCategory;
    }
}
