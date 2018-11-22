package org.regeneration.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class UserExceptionAdvice {

    @ExceptionHandler(NoLoggedInUserException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    ErrorDetails noLoggedInUserHandler(NoLoggedInUserException e) {
        return new ErrorDetails(e.getMessage(), e.getMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ErrorDetails notFoundHandler(UserNotFoundException e) {
       return new ErrorDetails("User not found", e.getMessage());
    }

}
