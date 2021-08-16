package com.librarty.book.controller;

import com.librarty.book.dto.BookDTO;
import com.librarty.book.dto.request.SaveBookDTO;
import com.librarty.book.dto.response.CommonDataResponseDTO;
import com.librarty.book.entity.Book;
import com.librarty.book.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.librarty.book.constance.AppConstance.DetailConstance.*;

@RestController
@CrossOrigin
@RequestMapping("/v1/book")
public class BookController {
    @Autowired
    BookService bookService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addBooks(@RequestBody SaveBookDTO saveBookDTO) {
        bookService.add(saveBookDTO);
        return new ResponseEntity<>(new CommonDataResponseDTO(true, BOOK_ADDED), HttpStatus.OK);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAllBooks() {
        List<SaveBookDTO> allBooks = bookService.getAll();
        return new ResponseEntity<>(new CommonDataResponseDTO(true,allBooks),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable("id") Long id) {
        Optional<Book> bookDTO = bookService.get(id);
        return new ResponseEntity<>(new CommonDataResponseDTO(true,bookDTO),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteBook(@PathVariable("id") Long id) {
        bookService.delete(id);
        return new ResponseEntity<>(new CommonDataResponseDTO(true, BOOK_DELETED), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity updateBook(@RequestBody SaveBookDTO saveBookDTO) {
        bookService.update(saveBookDTO);
        return new ResponseEntity<>(new CommonDataResponseDTO(true, BOOK_UPDATED), HttpStatus.OK);
    }

}
