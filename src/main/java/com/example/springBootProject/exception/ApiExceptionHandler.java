package com.example.springBootProject.exception;

import com.example.springBootProject.exception.customException.AlreadyExistsException;
import com.example.springBootProject.exception.customException.DoesNotExistException;
import com.example.springBootProject.exception.customException.MissingValuesException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Handler for handling specified exceptions and returning responses to user.
 */

@ControllerAdvice
public class ApiExceptionHandler {
    private final HttpStatus badRequest = HttpStatus.BAD_REQUEST;
    private final HttpStatus conflict = HttpStatus.CONFLICT;
    private final HttpStatus notFound = HttpStatus.NOT_FOUND;

    @ExceptionHandler(value = {
            AlreadyExistsException.class,
            DoesNotExistException.class,
            MissingValuesException.class
    })
    public ResponseEntity<Object> handleApiException(Exception e) {
        HttpStatus status;
        if (e instanceof AlreadyExistsException) status = conflict;
        else if (e instanceof DoesNotExistException) status = notFound;
        else status = badRequest;
        ApiException apiException = new ApiException(
                e.getMessage(),
                status,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(apiException, status);
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<Object> handleValidationException(MethodArgumentNotValidException e) {
        List<String> messages = new ArrayList<>();
        e.getBindingResult().getAllErrors().forEach( err -> messages.add(err.getDefaultMessage()));
        String message = "[" + String.join(", ", messages) + "]";

        ApiException apiException = new ApiException(
                message,
                badRequest,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(apiException, badRequest);
    }
}
