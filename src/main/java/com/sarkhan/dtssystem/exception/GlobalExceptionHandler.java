package com.sarkhan.dtssystem.exception;


import com.sarkhan.dtssystem.dto.request.CompanyRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        String companyName = "BİLİNMİR";

        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }

        Object target = ex.getBindingResult().getTarget();
        if (target instanceof CompanyRequest request) {
            if (request.getCompanyData() != null && request.getCompanyData().getCompanyName() != null) {
                companyName = request.getCompanyData().getCompanyName();
            }
        }

        log.warn("Şirkət validasiyadan keçmədi: {}. Səbəblər: {}", companyName, errors);

        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("timestamp", LocalDateTime.now());
        responseBody.put("status", HttpStatus.BAD_REQUEST.value());
        responseBody.put("message", "Validasiya xətası baş verdi");
        responseBody.put("companyName", companyName);
        responseBody.put("errors", errors);

        return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
    }
}