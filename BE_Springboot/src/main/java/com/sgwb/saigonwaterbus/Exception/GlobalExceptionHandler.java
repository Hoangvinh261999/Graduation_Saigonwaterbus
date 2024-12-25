package com.sgwb.saigonwaterbus.Exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sgwb.saigonwaterbus.Model.DTO.ApiResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.nio.file.attribute.UserPrincipalNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<ApiResponse> handlingRuntimeException(RuntimeException exception) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(1001);
        apiResponse.setMessage(exception.getMessage());
        return ResponseEntity.badRequest().body(apiResponse);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ApiResponse> handleNoHandlerFoundException(NoResourceFoundException ex) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(1002);
        apiResponse.setMessage("Resource not found cannot call api");
        apiResponse.setResult(null);
        return  ResponseEntity.badRequest().body(apiResponse);
    }
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiResponse>violentDataException(DataIntegrityViolationException ex) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(1004);
        apiResponse.setMessage(ex.getMessage());
        apiResponse.setResult(null);
        return  ResponseEntity.ok(apiResponse);
    }
    @ExceptionHandler(JsonProcessingException.class)
    public ResponseEntity<ApiResponse>jsonDataProcess(JsonProcessingException ex) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(1004);
        apiResponse.setMessage(ex.getMessage());
        apiResponse.setResult(null);
        return  ResponseEntity.ok(apiResponse);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ApiResponse>userNotFound(UsernameNotFoundException ex) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(1005);
        apiResponse.setMessage(ex.getMessage());
        apiResponse.setResult(null);
        return  ResponseEntity.ok(apiResponse);
    }

}
