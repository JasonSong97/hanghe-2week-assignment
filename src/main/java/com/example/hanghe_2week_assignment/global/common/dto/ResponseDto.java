package com.example.hanghe_2week_assignment.global.common.dto;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class ResponseDto<T> {
    
    private Integer status;
    private String message;
    private T data;

    public ResponseDto() {
        this.status = HttpStatus.OK.value();
        this.message = "OK";
    }

    public ResponseDto(T data) {
        this.status = HttpStatus.OK.value();
        this.message = "OK";
        this.data = data;
    }

    public ResponseDto(HttpStatus httpStatus, String message, T data) {
        this.status = httpStatus.value();
        this.message = message;
        this.data = data;
    }
}
