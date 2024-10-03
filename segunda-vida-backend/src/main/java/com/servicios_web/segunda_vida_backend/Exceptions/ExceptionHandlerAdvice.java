package com.servicios_web.segunda_vida_backend.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
public class ExceptionHandlerAdvice {
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<?> handleNotFoundException(NoSuchElementException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El campo usuario no se encuentra registrado");
    }
}
