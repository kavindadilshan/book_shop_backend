package com.librarty.book.exceptions;

import com.librarty.book.dto.response.ErrorMessageResponseDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static com.librarty.book.constance.AppConstance.ApplicationErrors.APPLICATION_ERROR_OCCURRED_MESSAGE;

@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {
    private static final Logger LOGGER = LogManager.getLogger(AppExceptionHandler.class);

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity handleAnyException(Exception ex, WebRequest webRequest){
        LOGGER.error("Function handleAnyException : " + ex.getMessage());
        return new ResponseEntity<>(new ErrorMessageResponseDTO(false,100,APPLICATION_ERROR_OCCURRED_MESSAGE), HttpStatus.EXPECTATION_FAILED);
    }

    @ExceptionHandler(value = {BookServiceException.class})
    public ResponseEntity handleBookServiceException(BookServiceException ex, WebRequest webRequest){
        LOGGER.error("Function handleBookServiceException : " + ex.getMessage());
        return new ResponseEntity<>(new ErrorMessageResponseDTO(false,ex.getStatus(),ex.getMessage()),HttpStatus.OK);
    }
}
