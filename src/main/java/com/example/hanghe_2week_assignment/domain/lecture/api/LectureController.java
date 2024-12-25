package com.example.hanghe_2week_assignment.domain.lecture.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.example.hanghe_2week_assignment.domain.lecture.application.LectureService;
import com.example.hanghe_2week_assignment.domain.lecture.dto.LectureResponseDto;
import com.example.hanghe_2week_assignment.global.common.dto.ResponseDto;
import com.fasterxml.jackson.core.JsonProcessingException;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 컨트롤러
 * - 사용자 요청 받음
 * - service 계층에게 데이터 전달
 * - 사용자 응답 반환
 */
@RequiredArgsConstructor
@RestController
public class LectureController<T> {
    
    private final LectureService lectureService;

    @GetMapping("/lectures")
    public ResponseEntity<?> getPossibleLectures() throws JsonProcessingException {
        LectureResponseDto.GetPossibleOutDto getPossibleOutDto = lectureService.getPossibleLectures();
        ResponseDto responseDto = new ResponseDto<>(getPossibleOutDto);
        return ResponseEntity.ok().body(responseDto);
    }
}
