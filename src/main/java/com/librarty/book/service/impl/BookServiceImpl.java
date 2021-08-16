package com.librarty.book.service.impl;

import com.librarty.book.dto.BookDTO;
import com.librarty.book.dto.PackageDTO;
import com.librarty.book.dto.request.SaveBookDTO;
import com.librarty.book.entity.Book;
import com.librarty.book.entity.BookPackage;
import com.librarty.book.entity.Package;
import com.librarty.book.exceptions.BookServiceException;
import com.librarty.book.repository.BookPackageRepository;
import com.librarty.book.repository.BookRepository;
import com.librarty.book.repository.PackageRepository;
import com.librarty.book.service.BookService;
import org.apache.logging.log4j.LogManager;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.librarty.book.constance.AppConstance.DuplicatedConstance.BOOK_ALREADY_EXISTS;
import static com.librarty.book.constance.AppConstance.ErrorCodeConstance.RESOURCE_ALREADY_EXISTS;
import static com.librarty.book.constance.AppConstance.ErrorCodeConstance.RESOURCE_NOT_FOUND;
import static com.librarty.book.constance.AppConstance.NotFoundConstance.BOOK_NOT_FOUND;

@Service
public class BookServiceImpl implements BookService {

    private static final Logger LOGGER = LogManager.getLogger(BookServiceImpl.class);

    @Autowired
    BookRepository bookRepository;

    @Autowired
    PackageRepository packageRepository;

    @Autowired
    BookPackageRepository bookPackageRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, PackageRepository packageRepository) {
        this.bookRepository = bookRepository;
        this.packageRepository = packageRepository;
    }

    @Override
    public void add(SaveBookDTO saveBookDTO) {
        try {
            LOGGER.info("Execute Function : save book");
            Optional<Book> optionalBookEntity = bookRepository.findById(saveBookDTO.getBookId());
            if (optionalBookEntity.isPresent())
                throw new BookServiceException(RESOURCE_ALREADY_EXISTS, BOOK_ALREADY_EXISTS);

            Book bookEntity = new Book();
            bookEntity.setId(saveBookDTO.getBookId());
            bookEntity.setAuthor(saveBookDTO.getAuthor());
            bookEntity.setBookName(saveBookDTO.getBookName());
            bookEntity.setPrice(saveBookDTO.getPrice());
            bookEntity = bookRepository.save(bookEntity);

            List<PackageDTO> packageDTOList = new ArrayList<>();

            for (String packageId : saveBookDTO.getPackageIds()) {
                Package onePackage = packageRepository.getOne(Long.valueOf(packageId));

                BookPackage bookPackage = new BookPackage();
                bookPackage.setaPackage(onePackage);
                bookPackage.setBook(bookEntity);
                bookPackageRepository.save(bookPackage);
                packageDTOList.add(modelMapper.map(onePackage, PackageDTO.class));
            }

            BookDTO bookDTO = modelMapper.map(bookEntity, BookDTO.class);
            bookDTO.setBookId(bookEntity.getId());
            bookDTO.setPackageDTO(packageDTOList);


        } catch (Exception e) {
            LOGGER.error("Function : saveBookEvent " + e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public void update(SaveBookDTO saveBookDTO) {
        try {
            LOGGER.info("Execute Function : update book");
            Optional<Book> optionalBookEntity = bookRepository.findById(saveBookDTO.getBookId());
            if (!optionalBookEntity.isPresent())
                throw new BookServiceException(RESOURCE_NOT_FOUND, BOOK_NOT_FOUND);

            Book bookEntity = new Book();
            bookEntity.setId(saveBookDTO.getBookId());
            bookEntity.setAuthor(saveBookDTO.getAuthor());
            bookEntity.setBookName(saveBookDTO.getBookName());
            bookEntity.setPrice(saveBookDTO.getPrice());
            bookRepository.save(bookEntity);

        } catch (Exception e) {
            LOGGER.error("Function : updateBookEvent " + e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public Optional<Book> get(Long id) {
        try {
            LOGGER.info("Execute Function : book get by id ");
            Optional<Book> optionalBook = bookRepository.findById(id);
            if (!optionalBook.isPresent())
                throw new BookServiceException(RESOURCE_NOT_FOUND, BOOK_NOT_FOUND);

            return bookRepository.findById(id);
        } catch (Exception e) {
            LOGGER.error("Function : getByIdBookEvent " + e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public void delete(Long id) {
        try {
            LOGGER.info("Execute Function : delete book");
            Optional<Book> optionalBook = bookRepository.findById(id);
            if (!optionalBook.isPresent())
                throw new BookServiceException(RESOURCE_NOT_FOUND, BOOK_NOT_FOUND);

            bookRepository.delete(optionalBook.get());
        } catch (Exception e) {
            LOGGER.error("Function:deleteBook:" + e.getMessage());
            throw e;
        }
    }

    @Override
    public List<SaveBookDTO> getAll() {
        List<Book> all = bookRepository.findAll();
        return getBookDTO(all);
    }

    private List<SaveBookDTO> getBookDTO(List<Book> bookList) {
        Type targetType = new TypeToken<List<BookDTO>>() {
        }.getType();
        return modelMapper.map(bookList, targetType);
    }
}
