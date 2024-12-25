package com.example.hanghe_2week_assignment.domain.lecture.application;

import org.springframework.stereotype.Service;

import com.example.hanghe_2week_assignment.domain.lecture.domain.LectureFacade;
import com.example.hanghe_2week_assignment.domain.lecture.dto.LectureResponseDto;

import lombok.RequiredArgsConstructor;

/**
 * 서비스
 * - facade 호출
 */
@Service
@RequiredArgsConstructor
public class LectureService {
    
    private final LectureFacade lectureFacade;

    public LectureResponseDto.GetPossibleOutDto getPossibleLectures() {
        return lectureFacade.getPossibleLectures();
    }
}
