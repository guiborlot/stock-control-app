package com.borlot.marketapp.products.adapter.in.web;

import com.borlot.marketapp.products.domain.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({InvalidNameException.class})
    public ResponseEntity<String> invalidNameException(RuntimeException e){
        return new ResponseEntity<String>("Invalid product name", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({InvalidCategoryException.class})
    public ResponseEntity<String> invalidCategoryException(RuntimeException e){
        return new ResponseEntity<String>("Invalid category name", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({InvalidPriceException.class})
    public ResponseEntity<String> invalidPriceException(RuntimeException e){
        return new ResponseEntity<String>("Invalid price value", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({InvalidQuantityException.class})
    public ResponseEntity<String> wrongQuantityException(RuntimeException e){
        return new ResponseEntity<String>("Invalid quantity value", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ProductExistsException.class})
    public ResponseEntity<String> productExistsException(RuntimeException e){
        return new ResponseEntity<String>("this product already exists in the database", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ProductNotExistsException.class})
    public ResponseEntity<String> productNotExistsException(RuntimeException e){
        return new ResponseEntity<String>("this product does not exists in the database", HttpStatus.BAD_REQUEST);
    }
}
