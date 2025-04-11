package ru.storeMVC.storeMVC.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

@ExceptionHandler(NoExistsUser.class)
    public ResponseEntity<String> handlerException(){
    return ResponseEntity.badRequest().body("Пользователя не существует");
}

}
