package com.librarty.book.service;

import com.librarty.book.dto.BookDTO;
import com.librarty.book.dto.request.SaveBookDTO;
import com.librarty.book.service.common.CrudService;

public interface BookService extends CrudService<SaveBookDTO> {
}
