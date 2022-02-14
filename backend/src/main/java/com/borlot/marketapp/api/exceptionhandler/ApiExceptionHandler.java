package com.borlot.marketapp.api.exceptionhandler;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    private final MessageSource messageSource;

    public ApiExceptionHandler(MessageSource messageSource){
        this.messageSource = messageSource;
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<Error.Field> fields = new ArrayList<>();

        ex.getBindingResult().getAllErrors().forEach(error -> fields.add(new Error.Field(((FieldError)error).getField(), messageSource.getMessage(error, LocaleContextHolder.getLocale()))));

        Error error = new Error();
        error.setStatus(status.value());
        error.setDataHora(LocalDateTime.now());
        error.setTitle("Um ou mais campos estão inválidos. Faça o prenchimento correto e tente novamente");
        error.setFields(fields);

        return this.handleExceptionInternal(ex, error ,headers, status, request);
    }

}
