package com.librarty.book.service;

import com.librarty.book.dto.application.TokenDTO;
import com.librarty.book.entity.User;

public interface UserService {
    User findUserByUserName(String userName);
    TokenDTO saveUser (TokenDTO tokenDTO);
}
