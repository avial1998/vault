package com.avinash.vault.exceptions;

import com.avinash.vault.dtos.ResponseDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
       HashMap<String,String> errors = new HashMap<>();
       List<ObjectError> errorList=ex.getBindingResult().getAllErrors();
       errorList.forEach(err->{
           String fieldName=((FieldError)err).getField();
           String errorMessage= err.getDefaultMessage();
           errors.put(fieldName, Objects.requireNonNullElse(errorMessage, "Invalid value"));
       });
         return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CredentialAlreadyExistsException.class)
    public ResponseEntity<ResponseDto> handleCredentialAlreadyExistsException(CredentialAlreadyExistsException ex) {
        ResponseDto response = new ResponseDto(HttpStatus.BAD_REQUEST.toString(), ex.getMessage());
        return ResponseEntity.badRequest().body(response);
    }
    @ExceptionHandler(InvalidRequestDetailsException.class)
    public ResponseEntity<ResponseDto> handleInvalidRequestDetailsException(InvalidRequestDetailsException ex) {
        ResponseDto response = new ResponseDto(HttpStatus.UNAUTHORIZED.toString(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }
    @ExceptionHandler(CredentialNotFoundException.class)
    public ResponseEntity<ResponseDto> handleCredentialNotFoundException(CredentialNotFoundException ex) {
        ResponseDto response = new ResponseDto(HttpStatus.NOT_FOUND.toString(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDto> handleGenericException(Exception ex) {
        ResponseDto response = new ResponseDto(HttpStatus.INTERNAL_SERVER_ERROR.toString(), "An unexpected error occurred: " + ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
