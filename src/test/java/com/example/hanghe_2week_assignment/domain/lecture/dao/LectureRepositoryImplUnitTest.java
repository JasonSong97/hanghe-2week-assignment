package com.example.hanghe_2week_assignment.domain.lecture.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.hanghe_2week_assignment.domain.lecture.domain.Lecture;

@ExtendWith(MockitoExtension.class)
public class LectureRepositoryImplUnitTest {
    
    @InjectMocks
    private LectureRepositoryImpl lectureRepositoryImpl;

    @Mock
    private LectureJpaRepository lectureJpaRepository;

    @Test
    @DisplayName(value = "[성공] 신청 가능한 목록을 조회하면 신청 가능한 목록을 반환합니다.")
    void 신청_가능한_목록을_조회하면_신청_가능한_목록을_반환_성공() throws Exception {
        // given
        List<Lecture> lectureList = List.of(
            new Lecture(1L, "국어", "ssar", LocalDateTime.now(), 30, false, LocalDateTime.now(), LocalDateTime.now()),
            new Lecture(2L, "영어", "cos", LocalDateTime.now(), 30, false, LocalDateTime.now(), LocalDateTime.now()),
            new Lecture(3L, "수학", "love", LocalDateTime.now(), 30, false, LocalDateTime.now(), LocalDateTime.now()),
            new Lecture(4L, "과학", "ssar", LocalDateTime.now(), 30, false, LocalDateTime.now(), LocalDateTime.now()),
            new Lecture(5L, "사회", "ssar", LocalDateTime.now(), 30, false, LocalDateTime.now(), LocalDateTime.now())
        );
    
        // when
        when(lectureJpaRepository.findPossibleLectureList()).thenReturn(lectureList);
        List<Lecture> result =  lectureRepositoryImpl.findPossibleLectureList();
    
        // then
        assertNotNull(result, "결과가 null이어서는 안됩니다.");
        assertEquals(5, result.size(), "신청 가능한 강의 목록은 5개여야 합니다.");
        assertEquals("국어", result.get(0).getLectureTitle(), "첫 번째 강의의 제목은 '국어'여야 합니다.");
        assertEquals("영어", result.get(1).getLectureTitle(), "두 번째 강의의 제목은 '영어'여야 합니다.");

        verify(lectureJpaRepository, times(1)).findPossibleLectureList();
    }
}
