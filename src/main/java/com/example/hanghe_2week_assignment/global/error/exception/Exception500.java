package com.example.hanghe_2week_assignment.global.error.exception;

import org.springframework.http.HttpStatus;

import com.example.hanghe_2week_assignment.global.common.dto.ResponseDto;

import lombok.Getter;

@Getter
public class Exception500 extends RuntimeException {

    public Exception500(String message) {
        super(message);
    }

    public ResponseDto<?> body() {
        return new ResponseDto<>(HttpStatus.NOT_FOUND, "NOT FOUND", getMessage());
    }

    public HttpStatus status() {
        return HttpStatus.NOT_FOUND;
    }
}
