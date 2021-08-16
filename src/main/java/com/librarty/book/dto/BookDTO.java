package com.librarty.book.dto;

import java.util.List;

public class BookDTO {
    private Long bookId;
    private String bookName;
    private String author;
    private double price;
    private List<PackageDTO> packageDTO;

    public BookDTO() {
    }

    public BookDTO(Long bookId, String bookName, String author, double price, List<PackageDTO> packageDTO) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.author = author;
        this.price = price;
        this.packageDTO = packageDTO;
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

    public List<PackageDTO> getPackageDTO() {
        return packageDTO;
    }

    public void setPackageDTO(List<PackageDTO> packageDTO) {
        this.packageDTO = packageDTO;
    }
}
