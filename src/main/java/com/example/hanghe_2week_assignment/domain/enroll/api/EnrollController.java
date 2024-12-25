package com.example.hanghe_2week_assignment.domain.enroll.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.hanghe_2week_assignment.domain.enroll.application.EnrollService;
import com.example.hanghe_2week_assignment.domain.enroll.dto.EnrollResponse;
import com.example.hanghe_2week_assignment.global.common.dto.ResponseDto;
import com.fasterxml.jackson.core.JsonProcessingException;

import lombok.RequiredArgsConstructor;

/**
 * 컨트롤러
 * - 사용자 요청 받음
 * - service 계층에게 데이터 전달
 * - 사용자 응답 반환
 */
@RequiredArgsConstructor
@RestController
public class EnrollController {
    
    private final EnrollService enrollService;

    @GetMapping("/lectures/{userId}")
    public ResponseEntity<?> getEnrolledLectures(@PathVariable Long userId) throws JsonProcessingException {
        EnrollResponse.GetEnrolledOutDto getEnrolledOutDto = enrollService.getEnrolledLectures(userId);
        ResponseDto responseDto = new ResponseDto<>(getEnrolledOutDto);
        return ResponseEntity.ok().body(responseDto);
    }
}
