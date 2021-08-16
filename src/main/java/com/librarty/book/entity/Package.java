package com.librarty.book.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "packages")
public class Package implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String packageName;
    private double price;

    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany(mappedBy = "aPackage", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<BookPackage> bookPackages;

    public Package() {
    }

    public Package(Long id, String packageName, double price, List<BookPackage> bookPackages) {
        this.id = id;
        this.packageName = packageName;
        this.price = price;
        this.bookPackages = bookPackages;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
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
