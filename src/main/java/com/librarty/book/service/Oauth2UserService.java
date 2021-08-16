package com.librarty.book.service;

import com.librarty.book.dto.application.TokenDTO;

public interface Oauth2UserService {
    TokenDTO getUserTokenData(String username);
}
