package com.librarty.book.service.common;

import com.librarty.book.entity.Book;

import java.util.List;
import java.util.Optional;

public interface CrudService<T> {
    public void add(T t);
    public void update(T t);
    public Optional<Book> get(Long id);
    public void delete(Long id);
    public List<T> getAll();
}
