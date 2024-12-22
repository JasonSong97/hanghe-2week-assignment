package com.example.hanghe_2week_assignment.global.error.exception;

import org.springframework.http.HttpStatus;

import com.example.hanghe_2week_assignment.global.common.dto.ResponseDto;
import com.example.hanghe_2week_assignment.global.common.dto.ValidDto;

import lombok.Getter;

@Getter
public class Exception400 extends RuntimeException {
    
    private String key;
    private String value;

    public Exception400(String key, String value) {
        super(value);
        this.key = key;
        this.value = value;
    }

    public ResponseDto<?> body() {
        ValidDto validDto = new ValidDto(key, value);
        return new ResponseDto<>(HttpStatus.BAD_REQUEST, "BAD REQUEST", validDto);
    }

    public HttpStatus status() {
        return HttpStatus.BAD_REQUEST;
    }
}
