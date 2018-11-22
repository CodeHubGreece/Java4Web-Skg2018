package org.regeneration.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import java.util.Date;

@RestControllerAdvice
public class BookControllerAdvice {

    @ExceptionHandler(BookNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ErrorDetails bookNotFoundHandler(BookNotFoundException ex) {
        return new ErrorDetails("Book not found", ex.getMessage());
    }


    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ErrorDetails validationError(ConstraintViolationException e) {
        return new ErrorDetails("Validation Error", e.getConstraintViolations().toString());
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ErrorDetails bookNotFoundHandler(MethodArgumentNotValidException ex) {
        ErrorDetails errorDetails = new ErrorDetails("Validation Failed",
                ex.getBindingResult().toString());
        return errorDetails;
    }


}
