package com.itguigu.error;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ArithmeticException.class)
    public Object ArithmeticExceptionHandler(ArithmeticException e) {
        String message = e.getMessage();
        System.out.println("message: " + message);
        return message;
    }

    @ExceptionHandler(Exception.class)
    public Object ExceptionHandler(ArithmeticException e) {
        String message = e.getMessage();
        System.out.println("message: " + message);
        return message;
    }
}
