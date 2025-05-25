package com.example.auth_server.exceptions;

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
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@ControllerAdvice
public class ExceptionHandlerGlobal {

    private static final String EXCEPTION_HANDLED_BY = "(Rest)ResponseEntityExceptionHandler (@ControllerAdvice)";

    private ResponseEntity<Object> buildErrorResponse(
            Exception e,
            String exceptionName,
            HttpStatus status,
            String uri,
            List<String> messages) {

        APIErrorEntity apiError = new APIErrorEntity(
                EXCEPTION_HANDLED_BY,
                exceptionName,
                status,
                status,
                uri,
                e.getLocalizedMessage(),
                messages
        );

        return ResponseEntity.status(status).body(apiError);
    }

    private ResponseEntity<Object> buildErrorResponse(
            Exception e,
            String exceptionName,
            HttpStatus status,
            String uri,
            String message) {

        return buildErrorResponse(e, exceptionName, status, uri, List.of(message));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e, WebRequest request) {
        List<String> errors = new ArrayList<>();
        e.getBindingResult().getFieldErrors().forEach(error ->
                errors.add(error.getField() + ": " + error.getDefaultMessage()));
        e.getBindingResult().getGlobalErrors().forEach(error ->
                errors.add(error.getObjectName() + ": " + error.getDefaultMessage()));

        String uri = ((ServletWebRequest) request).getRequest().getRequestURI();
        return buildErrorResponse(e, "MethodArgumentNotValidException", HttpStatus.BAD_REQUEST, uri, errors);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException e, WebRequest request) {
        String uri = ((ServletWebRequest) request).getRequest().getRequestURI();
        return buildErrorResponse(e, "MissingServletRequestParameterException", HttpStatus.BAD_REQUEST, uri,
                e.getParameterName() + " parameter is missing");
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException e, WebRequest request) {
        String uri = ((ServletWebRequest) request).getRequest().getRequestURI();
        return buildErrorResponse(e, "HttpMessageNotReadableException", HttpStatus.BAD_REQUEST, uri,
                "Request body inexistente o mal formado");
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<Object> handleNoHandlerFound(NoHandlerFoundException e, WebRequest request) {
        String uri = ((ServletWebRequest) request).getRequest().getRequestURI();
        return buildErrorResponse(e, "NoHandlerFoundException", HttpStatus.NOT_FOUND, uri,
                "No handler found for " + e.getHttpMethod() + " " + e.getRequestURL());
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFound(ResourceNotFoundException e, WebRequest request) {
        String uri = ((ServletWebRequest) request).getRequest().getRequestURI();
        return buildErrorResponse(e, "ResourceNotFoundException", HttpStatus.NOT_FOUND, uri, e.getMessage());
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Object> handleBadRequest(BadRequestException e, WebRequest request) {
        String uri = ((ServletWebRequest) request).getRequest().getRequestURI();
        return buildErrorResponse(e, "BadRequestException", HttpStatus.BAD_REQUEST, uri, e.getMessage());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException e, HttpServletRequest request) {
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        List<String> errors = new ArrayList<>();
        violations.forEach(violation ->
                errors.add(violation.getRootBeanClass().getSimpleName() + " " +
                        violation.getPropertyPath() + ": " + violation.getMessage()));

        return buildErrorResponse(e, "ConstraintViolationException", HttpStatus.BAD_REQUEST, request.getRequestURI(), errors);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException e, HttpServletRequest request) {
        String requiredType = Optional.ofNullable(e.getRequiredType())
                                      .map(Class::getSimpleName)
                                      .orElse("unknown");

        String error = e.getName() + " should be of type " + requiredType;

        return buildErrorResponse(e, "MethodArgumentTypeMismatchException", HttpStatus.BAD_REQUEST, request.getRequestURI(), error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAll(Exception e, HttpServletRequest request) {
        return buildErrorResponse(e, "Unhandled Exception", HttpStatus.INTERNAL_SERVER_ERROR,
                request.getRequestURI(), "Unexpected error occurred");
    }
}
