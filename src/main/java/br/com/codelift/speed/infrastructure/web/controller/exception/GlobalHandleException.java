package br.com.codelift.speed.infrastructure.web.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalHandleException {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_CONTENT)
    public ApiResponseError handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {

        List<ErrorField> errorFields = e.getFieldErrors()
                .stream()
                .map(ef -> {
                    return new ErrorField(ef.getField(), ef.getDefaultMessage());
                }).toList();

        return new ApiResponseError(
                HttpStatus.UNPROCESSABLE_CONTENT.value(),
                "Validation error",
                errorFields
        );
    }
}
