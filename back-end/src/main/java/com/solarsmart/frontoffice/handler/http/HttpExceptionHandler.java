package com.solarsmart.frontoffice.handler.http;

import com.solarsmart.frontoffice.models.dto.response.ErrorResponse;
import com.solarsmart.frontoffice.models.exception.DomainException;
import com.solarsmart.frontoffice.models.exception.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class HttpExceptionHandler {
    @ExceptionHandler({ DomainException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> handleException(Exception ex){
        ErrorResponse validationResponse = ErrorResponse.builder()
                .message(ex.getMessage())
                .build();

//        return ResponseEntity.badRequest().body(validationResponse);
        return new ResponseEntity<>(validationResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({UnauthorizedException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<?> handleUnauthorizedException(){

//        return ResponseEntity.badRequest().body(validationResponse);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
