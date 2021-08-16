package com.librarty.book.service.impl;

import com.librarty.book.dto.application.TokenDTO;
import com.librarty.book.entity.User;
import com.librarty.book.exceptions.CustomOauthException;
import com.librarty.book.repository.UserRepository;
import com.librarty.book.service.Oauth2UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service(value = "userService")
public class Oauth2UserServiceImpl implements Oauth2UserService, UserDetailsService {

    private static final Logger LOGGER = LogManager.getLogger(Oauth2UserServiceImpl.class);

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public Oauth2UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public TokenDTO getUserTokenData(String username) {
        try {
            LOGGER.info("Start findUserTokenDetails function  username: " + username);

            User userEntity = userRepository.findByUsername(username);
            if (userEntity == null) {
                return null;
            }
            return modelMapper.map(userEntity, TokenDTO.class);
        } catch (Exception e) {
            LOGGER.error("Function findUserTokenDetails  : " + e.getMessage());
            return null;
        }
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        try {
            LOGGER.info("New user authentication username : " + s);

            User userEntity = userRepository.findByUsername(s);
            if (userEntity == null) {
                LOGGER.error("Invalid Grant  : Bad credentials, username : " + s);
                throw new CustomOauthException("The username you entered is incorrect.");
            }

            return new org.springframework.security.core.userdetails.User(userEntity.getUsername(), userEntity.getPassword(), getAuthority(userEntity));
        } catch (Exception e) {
            LOGGER.error("Function loadUserByUsername  : " + e.getMessage());
            throw e;
        }
    }

    private List<SimpleGrantedAuthority> getAuthority(User user) {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + user.getUserRole()));
    }

}
