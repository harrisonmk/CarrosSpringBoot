package com.projeto.CarrosSpringBoot.excecoes;

import java.nio.file.AccessDeniedException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ExcecaoConfig extends ResponseEntityExceptionHandler {

    @ExceptionHandler({EmptyResultDataAccessException.class}) //se cai em uma excessao desse tipo ele chama o metodo abaixo
    public ResponseEntity errorNotFound(Exception e) {

        return ResponseEntity.notFound().build(); //retorna 404 not found

    }

    
    @ExceptionHandler({IllegalArgumentException.class}) //se cai em uma excessao desse tipo ele chama o metodo abaixo
    public ResponseEntity errorBadRequest(Exception e) {

        return ResponseEntity.badRequest().build(); //retorna 400 bad request

    }

    
    @ExceptionHandler({AccessDeniedException.class})
    public ResponseEntity accessDenied() {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new Error("Acesso negado")); //retorna o erro 403 forbidden
    }
}

class Error {

    public String error;

    public Error(String error) {
        this.error = error;
    }

}
