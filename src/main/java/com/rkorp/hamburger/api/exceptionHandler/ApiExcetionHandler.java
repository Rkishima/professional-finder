package com.rkorp.hamburger.api.exceptionHandler;

import com.rkorp.hamburger.domain.exception.BusinessException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.Serial;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@ControllerAdvice
@AllArgsConstructor
public class ApiExcetionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Problem problem = new Problem();
        problem.setStatus(status.value());
        problem.setDataHora(OffsetDateTime.now());
        problem.setTitulo("Um ou mais  campos estão inválidos. Faça o preenchimento correto e tente novamente");
        return handleExceptionInternal(ex, problem, headers, status, request);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Object> handleBusiness(BusinessException ex, WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        Problem problem = new Problem();
        problem.setStatus(status.value());
        problem.setDataHora(OffsetDateTime.now());
        problem.setTitulo(ex.getMessage());

        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);


    }

}