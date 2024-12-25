package com.example.hanghe_2week_assignment.domain.lecture.domain;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.hanghe_2week_assignment.domain.lecture.dto.LectureResponseDto;

import lombok.RequiredArgsConstructor;

/**
 * 파사드
 * - 비즈니스 로직을 작성하는 레이어
 * - 도메인 로직을 조합 및 구성
 * - 레포지토리 인터페이스 호출
 * - DTO로 변환
 */
@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LectureFacade {
    
    private final LectureRepository lectureRepository;

    public LectureResponseDto.GetPossibleOutDto getPossibleLectures() {
        List<Lecture> PSlectureList = lectureRepository.findPossibleLectureList();
        return new LectureResponseDto.GetPossibleOutDto(PSlectureList);
    }
}
