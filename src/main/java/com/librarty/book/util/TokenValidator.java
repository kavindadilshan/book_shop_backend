package com.librarty.book.util;

import com.librarty.book.entity.User;
import com.librarty.book.exceptions.BookServiceException;
import com.librarty.book.service.UserService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import static com.librarty.book.constance.AppConstance.ErrorCodeConstance.RESOURCE_NOT_FOUND;

@Component
public class TokenValidator {
    private static final Logger LOGGER = LogManager.getLogger(TokenValidator.class);

    private final UserService userService;

    @Autowired
    public TokenValidator(UserService userService) {
        this.userService = userService;
    }

    public User retrieveUserInformationFromOAuth2Token() {

        LOGGER.info("Execute method retrieveUserInformationFromOAuth2Token");
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (!(authentication instanceof AnonymousAuthenticationToken)) {
                return userService.findUserByUserName(authentication.getName());
            }
            throw new BookServiceException(RESOURCE_NOT_FOUND, "Can't find user details from token");
        } catch (Exception e) {
            LOGGER.error("Method retrieveUserInformationFromAuthentication : " + e.getMessage());
            throw e;
        }
    }

}
