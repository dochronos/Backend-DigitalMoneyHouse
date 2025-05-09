package com.example.activities_server.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
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
import java.util.Set;

@Slf4j
@ControllerAdvice
public class ExceptionHandlerGlobal {

    private static final String EXCEPTION_HANDLED_BY = "(Rest)ResponseEntityExceptionHandler (@ControllerAdvice)";

    // Manejo de MethodArgumentNotValidException
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e, WebRequest request) {
        List<String> errors = new ArrayList<>();
        e.getBindingResult().getFieldErrors().forEach(error ->
                errors.add(error.getField() + ": " + error.getDefaultMessage()));
        e.getBindingResult().getGlobalErrors().forEach(error ->
                errors.add(error.getObjectName() + ": " + error.getDefaultMessage()));

        return buildApiError(e, HttpStatus.BAD_REQUEST, request, "MethodArgumentNotValidException (overriden)", errors);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException e, WebRequest request) {
        String error = e.getParameterName() + " parameter is missing";
        return buildApiError(e, HttpStatus.BAD_REQUEST, request, "MissingServletRequestParameterException (overriden)", error);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException e, WebRequest request) {
        String error = "Request body inexistente o mal formado";
        return buildApiError(e, HttpStatus.BAD_REQUEST, request, "HttpMessageNotReadableException (overriden)", error);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<Object> handleNoHandlerFound(NoHandlerFoundException e, WebRequest request) {
        String error = "No handler found for " + e.getHttpMethod() + " " + e.getRequestURL();
        return buildApiError(e, HttpStatus.NOT_FOUND, request, "NoHandlerFoundException (overriden)", error);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFound(ResourceNotFoundException e, WebRequest request) {
        return buildApiError(e, HttpStatus.NOT_FOUND, request, "(Custom)ResourceNotFoundException (@ExceptionHandler)", e.getMessage());
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Object> handleBadRequest(BadRequestException e, WebRequest request) {
        return buildApiError(e, HttpStatus.BAD_REQUEST, request, "(Custom)BadRequestException (@ExceptionHandler)", e.getMessage());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException e, WebRequest request) {
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        List<String> errors = new ArrayList<>();
        violations.forEach(violation ->
                errors.add(violation.getRootBeanClass().getSimpleName() + " " +
                        violation.getPropertyPath() + ": " + violation.getMessage()));
        return buildApiError(e, HttpStatus.BAD_REQUEST, request, "ConstraintViolationException (@ExceptionHandler)", errors);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException e, WebRequest request) {
        String error = e.getName() + " should be of type " + (e.getRequiredType() != null ? e.getRequiredType().getSimpleName() : "unknown");
        return buildApiError(e, HttpStatus.BAD_REQUEST, request, "MethodArgumentTypeMismatchException (@ExceptionHandler)", error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAll(Exception e, WebRequest request) {
        return buildApiError(e, HttpStatus.INTERNAL_SERVER_ERROR, request, "Exception (@ExceptionHandler)", "Error occurred");
    }

    private ResponseEntity<Object> buildApiError(Exception e, HttpStatus status, WebRequest request, String exceptionType, Object errorDetails) {
        String path = ((ServletWebRequest) request).getRequest().getRequestURI();
        log.error("[{}] {} at {} | Details: {}", exceptionType, e.getMessage(), path, errorDetails);

        APIErrorEntity apiError = new APIErrorEntity(
                EXCEPTION_HANDLED_BY,
                exceptionType,
                null,
                status,
                path,
                e.getLocalizedMessage(),
                errorDetails
        );
        return new ResponseEntity<>(apiError, status);
    }
}