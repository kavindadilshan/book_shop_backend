package com.librarty.book.dto.request;

import java.util.List;

public class SaveBookDTO {
    private Long bookId;
    private String bookName;
    private String author;
    private double price;
    private List<String> packageIds;

    public SaveBookDTO() {
    }

    public SaveBookDTO(Long bookId, String bookName, String author, double price, List<String> packageIds) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.author = author;
        this.price = price;
        this.packageIds = packageIds;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<String> getPackageIds() {
        return packageIds;
    }

    public void setPackageIds(List<String> packageIds) {
        this.packageIds = packageIds;
    }
}
