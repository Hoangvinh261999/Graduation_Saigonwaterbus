package com.sgwb.saigonwaterbus.Config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sgwb.saigonwaterbus.Model.DTO.ApiResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.management.relation.InvalidRoleInfoException;
import javax.management.relation.InvalidRoleValueException;
import java.io.IOException;

@Slf4j
@Component
public class ExceptionHandleCustomer extends InvalidRoleValueException implements AuthenticationEntryPoint, AccessDeniedHandler {
    private final ObjectMapper objectMapper;

    public ExceptionHandleCustomer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        ApiResponse<String> apiResponse = ApiResponse.<String>builder()
                .code(HttpStatus.UNAUTHORIZED.value())
                .message("Unauthorized: JWT token is missing or invalid")
                .build();
        response.setContentType("application/json");
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        objectMapper.writeValue(response.getWriter(), apiResponse);
        log.error("Cannot call API without a valid JWT token", authException);
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        ApiResponse<String> apiResponse = ApiResponse.<String>builder()
                .code(HttpStatus.FORBIDDEN.value())
                .message("Access denied: You do not have the necessary permissions to access this resource")
                .build();
        response.setContentType("application/json");
        response.setStatus(HttpStatus.FORBIDDEN.value());
        objectMapper.writeValue(response.getWriter(), apiResponse);
        log.error("Access denied: User does not have the necessary permissions", accessDeniedException);
    }
}
