package oleksiienko.volodymyr.controller;

import oleksiienko.volodymyr.dto.ErrorDto;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import oleksiienko.volodymyr.exception.EntityNotFoundException;
import oleksiienko.volodymyr.exception.HttpRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ErrorHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {ValidationException.class, MethodArgumentNotValidException.class})
    public ErrorDto handleException(Exception ex) {
        log.error(ex.getMessage(), ex);
        return new ErrorDto(ex.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = {HttpRequestException.class, EntityNotFoundException.class})
    public ErrorDto handleException(RuntimeException ex) {
        log.error(ex.getMessage(), ex);
        return new ErrorDto(ex.getMessage());
    }
}
