package com.library.managment.library_managment_sistem.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@ControllerAdvice
public class GlobalExceptionHandler {

        @ExceptionHandler(IllegalStateException.class)
        public ResponseEntity<String> handleIllegalStateException(IllegalStateException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
        @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalStateException(IllegalArgumentException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception ex) {
        return new ResponseEntity<>("An error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleNotFoundException(EntityNotFoundException ex) {
        return new ResponseEntity<>("Entity not found: " + ex.getMessage(), HttpStatus.NOT_FOUND);
    }
//    @ExceptionHandler(IllegalStateException.class)
//    public ResponseEntity<String> handleIllegalState(IllegalStateException ex) {
//        return new ResponseEntity<>("Operation not allowed: " + ex.getMessage(), HttpStatus.BAD_REQUEST);
//    }

}