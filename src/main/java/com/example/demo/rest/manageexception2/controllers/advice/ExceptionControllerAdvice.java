package com.example.demo.rest.manageexception2.controllers.advice;

import com.example.demo.rest.manageexception2.exceptions.NotEnoughMoneyException;
import com.example.demo.rest.manageexception2.model.ErrorDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/* С помощью аннотации @RestControllerAdvice отмечаем класс как СОВЕТ REST-контроллера.
* Теперь он будет перехватывать исключения, которые мы укажем. */
@RestControllerAdvice
public class ExceptionControllerAdvice {
    /* Используем аннотацию @ExceptionHandler, чтобы связать логику этого метода С КОНКРЕТНЫМ исключением */
    @ExceptionHandler(NotEnoughMoneyException.class)
    public ResponseEntity<ErrorDetails> exceptionNotEnoughMoneyHandler() {
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setMessage("Not enough money to make the payment.");
        return ResponseEntity
                .badRequest()
                .body(errorDetails);
    }
}
