package com.example.hanghe_2week_assignment.global.error.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.example.hanghe_2week_assignment.global.common.dto.ResponseDto;
import com.example.hanghe_2week_assignment.global.error.exception.Exception400;
import com.example.hanghe_2week_assignment.global.error.exception.Exception404;
import com.example.hanghe_2week_assignment.global.error.exception.Exception500;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ExceptionAdvice {
    
    @ExceptionHandler(Exception400.class)
    public ResponseEntity<?> badRequest(Exception400 e) {
        return new ResponseEntity<>(e.body(), e.status());
    }

    @ExceptionHandler(Exception404.class)
    public ResponseEntity<?> notFound(Exception404 e) {
        return new ResponseEntity<>(e.body(), e.status());
    }

    @ExceptionHandler(Exception500.class)
    public ResponseEntity<?> serverError(Exception500 e) {
        return new ResponseEntity<>(e.body(), e.status());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> unknownServerError(Exception e) {
        ResponseDto<String> responseDto = new ResponseDto<>(HttpStatus.INTERNAL_SERVER_ERROR, "UNKNOWN SERVER ERROR", e.getMessage());
        return new ResponseEntity<>(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
