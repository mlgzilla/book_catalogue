package com.example.book_catalogue.exception.handler;

import com.example.book_catalogue.exception.InternalServerErrorException;
import com.example.book_catalogue.exception.ResourceNotFoundException;
import com.example.book_catalogue.exception.handler.model.ErrorModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorModel> handleResourceNotFoundException(ResourceNotFoundException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorModel(HttpStatus.NOT_FOUND.value(), e.getMessage()));
    }

    @ExceptionHandler(InternalServerErrorException.class)
    public ResponseEntity<ErrorModel> handleInternalServerErrorException(InternalServerErrorException e) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorModel(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()));
    }
}
