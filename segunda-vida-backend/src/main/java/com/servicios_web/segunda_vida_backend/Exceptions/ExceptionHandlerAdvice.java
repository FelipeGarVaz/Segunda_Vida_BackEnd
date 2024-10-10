package com.servicios_web.segunda_vida_backend.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import java.util.NoSuchElementException;

@ControllerAdvice
@RestController
public class ExceptionHandlerAdvice {


    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<?> handleException(NoSuchElementException e) {
        //return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The requested item is not registered");
        return new ResponseEntity<>("The requested item is not registered", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleException(IllegalArgumentException e) {
        //return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The requested item is not registered");
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
