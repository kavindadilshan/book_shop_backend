package com.librarty.book.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "books")
public class Book implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String bookName;
    private String author;
    private double price;

    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<BookPackage> bookPackages;

    public Book() {
    }

    public Book(Long id, String bookName, String author, double price, List<BookPackage> bookPackages) {
        this.id = id;
        this.bookName = bookName;
        this.author = author;
        this.price = price;
        this.bookPackages = bookPackages;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<BookPackage> getBookPackages() {
        return bookPackages;
    }

    public void setBookPackages(List<BookPackage> bookPackages) {
        this.bookPackages = bookPackages;
    }
}
