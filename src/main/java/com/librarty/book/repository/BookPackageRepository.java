package com.librarty.book.repository;

import com.librarty.book.entity.BookPackage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookPackageRepository extends JpaRepository<BookPackage,Long> {
}
