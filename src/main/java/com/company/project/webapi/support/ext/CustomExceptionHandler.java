package com.company.project.webapi.support.ext;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(Throwable.class)
    public void handle(Exception ex) {

    }
}
