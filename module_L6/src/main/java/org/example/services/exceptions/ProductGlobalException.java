package org.example.services.exceptions;

import org.example.entity.response.ProductErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ProductGlobalException {

    @ExceptionHandler
    public ResponseEntity<ProductErrorResponse> handleException(ProductNotFoundException e) {
        ProductErrorResponse ProductErrorResponse = new ProductErrorResponse();
        ProductErrorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        ProductErrorResponse.setMessage(e.getMessage());
        ProductErrorResponse.setTimestamp(System.currentTimeMillis());
        return new ResponseEntity<>(ProductErrorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ProductErrorResponse> handleException(Exception e) {
        ProductErrorResponse ProductErrorResponse = new ProductErrorResponse();
        ProductErrorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        ProductErrorResponse.setMessage(e.getMessage());
        ProductErrorResponse.setTimestamp(System.currentTimeMillis());
        return new ResponseEntity<>(ProductErrorResponse, HttpStatus.BAD_REQUEST);
    }
}
