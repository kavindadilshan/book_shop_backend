package com.librarty.book.service.impl;

import com.librarty.book.dto.application.TokenDTO;
import com.librarty.book.entity.User;
import com.librarty.book.exceptions.UserServiceException;
import com.librarty.book.repository.UserRepository;
import com.librarty.book.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.librarty.book.constance.AppConstance.DuplicatedConstance.USER_ALREADY_EXISTS;
import static com.librarty.book.constance.AppConstance.ErrorCodeConstance.RESOURCE_ALREADY_EXISTS;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LogManager.getLogger(UserServiceImpl.class);
    private final UserRepository userRepository;

    @Autowired
    private  PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findUserByUserName(String userName) {
        try {
            LOGGER.info("Execute Function : findUserByUserName : @username: " + userName);
            return userRepository.findByUsername(userName);
        } catch (Exception e) {
            LOGGER.error("Function : findUserByUserName  : " + e.getMessage());
            return null;
        }
    }

    @Override
    public TokenDTO saveUser(TokenDTO tokenDTO) {
        try{
            LOGGER.info("Execute Function : save User");
            Optional<User> optionUserEntity = userRepository.findById(tokenDTO.getId());
            if (optionUserEntity.isPresent())
                throw new UserServiceException(RESOURCE_ALREADY_EXISTS,USER_ALREADY_EXISTS);
            User userEntity = new User();
            userEntity.setId(tokenDTO.getId());
            userEntity.setUsername(tokenDTO.getUsername());
            userEntity.setPassword(passwordEncoder.encode(tokenDTO.getPassword()));
            userEntity.setUserRole(tokenDTO.getUserRole());
            userRepository.save(userEntity);

        }catch (Exception e){
            LOGGER.error("Function : saveUser " + e.getMessage(), e);
            throw e;
        }
        return tokenDTO;
    }

}
