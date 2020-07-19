package com.socgen.employeeportal.web.rest.errors;

/**
 * WebExceptionHandler Class
 * Class to handle exceptions and returns appropriate response entity
 *
 * @author: Ravi Kumar Soni
 * @authorLinkedProfile: https://www.linkedin.com/in/tenaciousprevailer
 * @authorContact: 8955047966
 * @purpose: Societe Generale Test
 */

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Instant;

@Controller
@ControllerAdvice
public class WebExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    protected ResponseEntity<Object> handleBadRequestException(Exception ex, WebRequest request) {
        return createExceptionResponse(ex.getMessage(), request, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    protected ResponseEntity<Object> handleResourceNotFoundException(Exception ex, WebRequest request) {
        return createExceptionResponse(ex.getMessage(), request, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UnauthorizedException.class)
    protected ResponseEntity<Object> handleUnauthorizedException(Exception ex, WebRequest request) {
        return createExceptionResponse(ex.getMessage(), request, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(EmployeePortalException.class)
    protected ResponseEntity<Object> handleAllEmployeePortalException(Exception ex, WebRequest request) {
        return createExceptionResponse(ex.getMessage(), request, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) {
        return createExceptionResponse(ex.getMessage(), request, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest webRequest) {
        return new ResponseEntity(
                new ExceptionResponse(
                        "Validation Failed",
                        ex.getBindingResult().toString(),
                        Instant.now()
                ),
                HttpStatus.BAD_REQUEST
        );
    }

    private ResponseEntity createExceptionResponse(
            String errorMessage,
            WebRequest webRequest,
            HttpStatus httpStatus) {

        return new ResponseEntity(
                new ExceptionResponse(
                        errorMessage,
                        webRequest.getDescription(false),
                        Instant.now()
                ),
                httpStatus
        );
    }
}
