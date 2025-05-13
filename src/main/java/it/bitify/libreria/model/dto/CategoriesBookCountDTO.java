package it.bitify.libreria.model.dto;

public class CategoriesBookCountDTO {
    private String categoryName;
    private Long bookCount;

    public CategoriesBookCountDTO(String categoryName, Long bookCount) {
        this.categoryName = categoryName;
        this.bookCount = bookCount;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Long getBookCount() {
        return bookCount;
    }

    public void setBookCount(Long bookCount) {
        this.bookCount = bookCount;
    }
}
