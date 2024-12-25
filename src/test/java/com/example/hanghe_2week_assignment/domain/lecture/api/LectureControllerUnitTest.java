package com.example.hanghe_2week_assignment.domain.lecture.api;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.hanghe_2week_assignment.domain.lecture.application.LectureService;
import com.example.hanghe_2week_assignment.domain.lecture.domain.Lecture;
import com.example.hanghe_2week_assignment.domain.lecture.dto.LectureResponseDto;


@WebMvcTest(controllers = {
    LectureController.class
}) // 필요한 Controller 가져오기, 특정 필터를 제외하기
public class LectureControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private LectureService lectureService;

    /**
     * Red: 테스트 실패
     * - 클래스가 구현되지 않아서 컴파일 에러발생
     * Green: 테스트 성공
     * - 동작하도록 최소 비즈니스 코드 구현
     */
    @Test
    @DisplayName(value = "[성공] 신청 가능한 목록을 조회하면 신청 가능한 목록을 반환하고 상태코드 200과 상태메세지 OK와 데이터를 반환한다.")
    void 신청_가능한_목록을_조회하면_신청_가능한_목록을_반환하고_상태코드_200과_상태메세지_OK와_데이터를_반환_성공() throws Exception {
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
        given(lectureService.getPossibleLectures()).willReturn(mockLectureResponseDto);
        ResultActions resultActions = mockMvc.perform(get("/lectures"));
    
        // then
        resultActions
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.message").value("OK"))
            .andExpect(jsonPath("$.data.lectureList[0].lectureId").value(1))
            .andExpect(jsonPath("$.data.lectureList[0].lectureTitle").value("국어"))
            .andExpect(jsonPath("$.data.lectureList[0].lectureInstructor").value("ssar"))
            .andExpect(jsonPath("$.data.lectureList[1].lectureId").value(2))
            .andExpect(jsonPath("$.data.lectureList[1].lectureTitle").value("영어"))
            .andExpect(jsonPath("$.data.lectureList[1].lectureInstructor").value("cos"))
            .andExpect(jsonPath("$.data.lectureList[4].lectureId").value(5))
            .andExpect(jsonPath("$.data.lectureList[4].lectureTitle").value("사회"))
            .andExpect(jsonPath("$.data.lectureList[4].lectureInstructor").value("ssar"))
            .andExpect(jsonPath("$.data.lectureList.length()").value(5));
    }
}
