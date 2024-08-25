package com.smirnov.greenatom.controller;

import com.smirnov.greenatom.exception.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Обработчик исключений.
 */
@ControllerAdvice
@Slf4j
public class ExceptionHandlerAdvice {

    /**
     * Обрабатывает исключения при отсутствии entity объектов в БД.
     *
     * @param e исключение
     * @return Сообщение об ошибке
     */
    @ResponseBody
    @ExceptionHandler({EntityNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String entityException(RuntimeException e) {
        return responseServer(HttpStatus.NOT_FOUND, e.getMessage());
    }

    /**
     * Обрабатывает исключения при передаче невалидных данных.
     *
     * @param e исключение
     * @return Сообщение об ошибке
     */
    @ResponseBody
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String validatedException(ConstraintViolationException e) {
        return responseServer(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    /**
     * Обрабатывает исключения при передаче невалидных данных.
     *
     * @param e исключение
     * @return Сообщение об ошибке
     */
    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String validatedException(MethodArgumentNotValidException e) {
        StringBuilder exceptionMessage = new StringBuilder();
        e.getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .forEach(str -> exceptionMessage.append("\n").append(str));
        return responseServer(HttpStatus.BAD_REQUEST, exceptionMessage.toString());
    }

    private String responseServer(HttpStatus httpStatus, String message) {
        log.error("{}. {}", httpStatus, message);
        return "%s %n%s".formatted(httpStatus, message);
    }
}