package com.example.accounts_server.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@ControllerAdvice
public class ExceptionHandlerGlobal {

    private static final String HANDLED_BY = "(Rest)ResponseEntityExceptionHandler (@ControllerAdvice)";

    private String getRequestUri(HttpServletRequest request) {
        return request.getRequestURI();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e, HttpServletRequest request) {
        List<String> errors = new ArrayList<>();
        e.getBindingResult().getFieldErrors().forEach(error ->
                errors.add(error.getField() + ": " + error.getDefaultMessage()));
        e.getBindingResult().getGlobalErrors().forEach(error ->
                errors.add(error.getObjectName() + ": " + error.getDefaultMessage()));

        APIErrorEntity apiError = new APIErrorEntity(
                HANDLED_BY,
                "MethodArgumentNotValidException",
                HttpStatus.BAD_REQUEST,
                HttpStatus.BAD_REQUEST,
                getRequestUri(request),
                e.getLocalizedMessage(),
                errors
        );
        return ResponseEntity.status(apiError.getStatus()).body(apiError);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException e, HttpServletRequest request) {
        String error = e.getParameterName() + " parameter is missing";

        APIErrorEntity apiError = new APIErrorEntity(
                HANDLED_BY,
                "MissingServletRequestParameterException",
                HttpStatus.BAD_REQUEST,
                HttpStatus.BAD_REQUEST,
                getRequestUri(request),
                e.getLocalizedMessage(),
                error
        );
        return ResponseEntity.status(apiError.getStatus()).body(apiError);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException e, HttpServletRequest request) {
        String error = "Request body is missing or malformed";

        APIErrorEntity apiError = new APIErrorEntity(
                HANDLED_BY,
                "HttpMessageNotReadableException",
                HttpStatus.BAD_REQUEST,
                HttpStatus.BAD_REQUEST,
                getRequestUri(request),
                e.getLocalizedMessage(),
                error
        );
        return ResponseEntity.status(apiError.getStatus()).body(apiError);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<Object> handleNoHandlerFound(NoHandlerFoundException e, HttpServletRequest request) {
        String error = "No handler found for " + e.getHttpMethod() + " " + e.getRequestURL();

        APIErrorEntity apiError = new APIErrorEntity(
                HANDLED_BY,
                "NoHandlerFoundException",
                HttpStatus.NOT_FOUND,
                HttpStatus.NOT_FOUND,
                getRequestUri(request),
                e.getLocalizedMessage(),
                error
        );
        return ResponseEntity.status(apiError.getStatus()).body(apiError);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
        APIErrorEntity apiError = new APIErrorEntity(
                HANDLED_BY,
                "ResourceNotFoundException (custom)",
                HttpStatus.NOT_FOUND,
                HttpStatus.NOT_FOUND,
                getRequestUri(request),
                e.getLocalizedMessage(),
                e.getMessage()
        );
        return ResponseEntity.status(apiError.getStatus()).body(apiError);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Object> handleBadRequest(BadRequestException e, HttpServletRequest request) {
        APIErrorEntity apiError = new APIErrorEntity(
                HANDLED_BY,
                "BadRequestException (custom)",
                HttpStatus.BAD_REQUEST,
                HttpStatus.BAD_REQUEST,
                getRequestUri(request),
                e.getLocalizedMessage(),
                e.getMessage()
        );
        return ResponseEntity.status(apiError.getStatus()).body(apiError);
    }

    @ExceptionHandler(InternalServerErrorException.class)
    public ResponseEntity<Object> handleInternalServerError(InternalServerErrorException e, HttpServletRequest request) {
        APIErrorEntity apiError = new APIErrorEntity(
                HANDLED_BY,
                "InternalServerErrorException (custom)",
                HttpStatus.INTERNAL_SERVER_ERROR,
                HttpStatus.INTERNAL_SERVER_ERROR,
                getRequestUri(request),
                e.getLocalizedMessage(),
                e.getMessage()
        );
        return ResponseEntity.status(apiError.getStatus()).body(apiError);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException e, HttpServletRequest request) {
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        List<String> errors = new ArrayList<>();
        violations.forEach(violation ->
                errors.add(violation.getPropertyPath() + ": " + violation.getMessage()));

        APIErrorEntity apiError = new APIErrorEntity(
                HANDLED_BY,
                "ConstraintViolationException",
                HttpStatus.BAD_REQUEST,
                HttpStatus.BAD_REQUEST,
                getRequestUri(request),
                e.getLocalizedMessage(),
                errors
        );
        return ResponseEntity.status(apiError.getStatus()).body(apiError);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException e, HttpServletRequest request) {
        String error = e.getName() + " should be of type " + e.getRequiredType().getSimpleName();

        APIErrorEntity apiError = new APIErrorEntity(
                HANDLED_BY,
                "MethodArgumentTypeMismatchException",
                HttpStatus.BAD_REQUEST,
                HttpStatus.BAD_REQUEST,
                getRequestUri(request),
                e.getLocalizedMessage(),
                error
        );
        return ResponseEntity.status(apiError.getStatus()).body(apiError);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAll(Exception e, HttpServletRequest request) {
        APIErrorEntity apiError = new APIErrorEntity(
                HANDLED_BY,
                "Unhandled Exception",
                HttpStatus.INTERNAL_SERVER_ERROR,
                HttpStatus.INTERNAL_SERVER_ERROR,
                getRequestUri(request),
                e.getLocalizedMessage(),
                "Unexpected error occurred"
        );
        return ResponseEntity.status(apiError.getStatus()).body(apiError);
    }
}