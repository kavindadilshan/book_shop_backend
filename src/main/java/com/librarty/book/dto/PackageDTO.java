package com.librarty.book.dto;

public class PackageDTO {
    private Long packageId;
    private String packageName;
    private double price;

    public PackageDTO() {
    }

    public PackageDTO(Long packageId, String packageName, double price) {
        this.packageId = packageId;
        this.packageName = packageName;
        this.price = price;
    }

    public Long getPackageId() {
        return packageId;
    }

    public void setPackageId(Long packageId) {
        this.packageId = packageId;
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
}
