package com.librarty.book.controller;

import com.librarty.book.dto.application.TokenDTO;
import com.librarty.book.dto.response.CommonDataResponseDTO;
import com.librarty.book.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;

@RestController
@CrossOrigin
@RequestMapping("/v1/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping(value = "/register",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity saveUser(@RequestBody TokenDTO tokenDTO){
        TokenDTO user = userService.saveUser(tokenDTO);
        return new ResponseEntity<>(new CommonDataResponseDTO(true,user), HttpStatus.OK);
    }
}
