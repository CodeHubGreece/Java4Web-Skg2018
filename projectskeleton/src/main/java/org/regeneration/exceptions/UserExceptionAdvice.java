package org.regeneration.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class UserExceptionAdvice {

   @ResponseBody
   @ExceptionHandler(NoLoggedInUserException.class)
   @ResponseStatus(HttpStatus.UNAUTHORIZED)
   String noLoggedInUserHandler(NoLoggedInUserException e) {
        return e.getMessage();
    }
}
