package it.bitify.libreria.model.dto;

import it.bitify.libreria.model.entity.Category;

import java.time.LocalDate;

public class CategoryOrderingDTO {
    private Category category;
    private Long bookCount;
    private LocalDate lastDate;

    public CategoryOrderingDTO(Category category, Long bookCount, LocalDate lastDate) {
        this.category = category;
        this.bookCount = bookCount;
        this.lastDate = lastDate;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Long getBookCount() {
        return bookCount;
    }

    public void setBookCount(Long bookCount) {
        this.bookCount = bookCount;
    }

    public LocalDate getLastDate() {
        return lastDate;
    }

    public void setLastDate(LocalDate lastDate) {
        this.lastDate = lastDate;
    }
}
