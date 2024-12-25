package com.example.hanghe_2week_assignment.domain.lecture.application;

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
import com.example.hanghe_2week_assignment.domain.lecture.domain.LectureFacade;
import com.example.hanghe_2week_assignment.domain.lecture.dto.LectureResponseDto;

@ExtendWith(MockitoExtension.class)
public class LectureServiceUnitTest {
    
    @InjectMocks
    private LectureService lectureService;

    @Mock
    private LectureFacade lectureFacade;

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
        LectureResponseDto.GetPossibleOutDto mockLectureResponseDto = new LectureResponseDto.GetPossibleOutDto(lectureList); 
    
        // when
        when(lectureFacade.getPossibleLectures()).thenReturn(mockLectureResponseDto);
        LectureResponseDto.GetPossibleOutDto result = lectureService.getPossibleLectures();
    
        // then
        assertNotNull(result);
        assertEquals(5, result.getLectureList().size());
        
        assertEquals(1L, result.getLectureList().get(0).getLectureId(), "첫 번째 강의의 ID가 1이어야 합니다.");
        assertEquals("국어", result.getLectureList().get(0).getLectureTitle(), "첫 번째 강의의 제목이 '국어'이어야 합니다.");
        assertEquals("ssar", result.getLectureList().get(0).getLectureInstructor(), "첫 번째 강의의 강사가 'ssar'이어야 합니다.");
        assertEquals(30, result.getLectureList().get(0).getLectureStudentCount(), "첫 번째 강의의 학생 수가 30이어야 합니다.");
        assertEquals(2L, result.getLectureList().get(1).getLectureId(), "두 번째 강의의 ID가 2이어야 합니다.");
        assertEquals("영어", result.getLectureList().get(1).getLectureTitle(), "두 번째 강의의 제목이 '영어'이어야 합니다.");
        assertEquals("cos", result.getLectureList().get(1).getLectureInstructor(), "두 번째 강의의 강사가 'cos'이어야 합니다.");

        verify(lectureFacade, times(1)).getPossibleLectures();
    }
}
