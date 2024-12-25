package com.example.hanghe_2week_assignment.domain.lecture.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import com.example.hanghe_2week_assignment.domain.lecture.dao.LectureJpaRepository;
import com.example.hanghe_2week_assignment.domain.lecture.domain.Lecture;
import com.example.hanghe_2week_assignment.domain.lecture.dto.LectureResponseDto;
import com.example.hanghe_2week_assignment.global.common.dto.ResponseDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class LectureControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private LectureJpaRepository lectureJpaRepository;

    @BeforeEach
    void setUp() {
        lectureJpaRepository.save(new Lecture(1L, "국어", "ssar", LocalDateTime.now(), 1, false, LocalDateTime.now(), LocalDateTime.now()));
        lectureJpaRepository.save(new Lecture(2L, "영어", "cos", LocalDateTime.now(), 0, true, LocalDateTime.now(), LocalDateTime.now()));
        lectureJpaRepository.save(new Lecture(3L, "수학", "love", LocalDateTime.now(), 10, false, LocalDateTime.now(), LocalDateTime.now()));
        lectureJpaRepository.save(new Lecture(4L, "과학", "ssar", LocalDateTime.now(), 20, false, LocalDateTime.now(), LocalDateTime.now()));
        lectureJpaRepository.save(new Lecture(5L, "사회", "ssar", LocalDateTime.now(), 0, true, LocalDateTime.now(), LocalDateTime.now()));
    }

    @AfterEach
    void cleanUp() {
        lectureJpaRepository.deleteAll();
    }

    @Test
    @DisplayName("[성공] 신청 가능한 목록을 조회하면 신청 가능한 목록을 반환합니다.")
    void 신청_가능한_목록을_조회하면_신청_가능한_목록을_반환_성공() throws JsonProcessingException {
        //given

        // when
        ResponseEntity<ResponseDto<LectureResponseDto.GetPossibleOutDto>> response = restTemplate.exchange(
            "/lectures",
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<ResponseDto<LectureResponseDto.GetPossibleOutDto>>() {}
        );

        // then
        assertEquals(HttpStatus.OK, response.getStatusCode(), "응답 상태 코드는 200이어야 합니다.");
        assertNotNull(response.getBody(), "응답 본문이 null이면 안 됩니다.");
        assertEquals("OK", response.getBody().getMessage(), "응답 메시지는 'OK'이어야 합니다.");
        assertNotNull(response.getBody().getData(), "응답 데이터가 null이면 안 됩니다.");

        assertEquals(3, response.getBody().getData().getLectureList().size(), "신청 가능한 강의는 3개여야 합니다.");
        assertEquals("국어", response.getBody().getData().getLectureList().get(0).getLectureTitle(), "첫 번째 강의 제목은 '국어'이어야 합니다.");
        assertEquals("ssar", response.getBody().getData().getLectureList().get(0).getLectureInstructor(), "첫 번째 강의 강사는 'ssar'이어야 합니다.");
    }
}

