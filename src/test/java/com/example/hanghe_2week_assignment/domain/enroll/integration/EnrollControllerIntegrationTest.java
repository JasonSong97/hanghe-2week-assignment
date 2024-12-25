package com.example.hanghe_2week_assignment.domain.enroll.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import com.example.hanghe_2week_assignment.domain.enroll.dao.EnrollJpaRepository;
import com.example.hanghe_2week_assignment.domain.enroll.domain.Enroll;
import com.example.hanghe_2week_assignment.domain.enroll.dto.EnrollResponse;
import com.example.hanghe_2week_assignment.global.common.dto.ResponseDto;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class EnrollControllerIntegrationTest {
    
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private EnrollJpaRepository enrollJpaRepository;

    @BeforeEach
    void setUp() {
        List<Enroll> enrollList = List.of(
            new Enroll(1L, 1L, 1L, LocalDateTime.now(), LocalDateTime.now()),
            new Enroll(2L, 1L, 4L, LocalDateTime.now(), LocalDateTime.now()),
            new Enroll(3L, 1L, 3L, LocalDateTime.now(), LocalDateTime.now()),
            new Enroll(4L, 3L, 1L, LocalDateTime.now(), LocalDateTime.now()),
            new Enroll(5L, 2L, 1L, LocalDateTime.now(), LocalDateTime.now()),
            new Enroll(6L, 3L, 2L, LocalDateTime.now(), LocalDateTime.now()),
            new Enroll(7L, 3L, 3L, LocalDateTime.now(), LocalDateTime.now()),
            new Enroll(8L, 3L, 4L, LocalDateTime.now(), LocalDateTime.now())
        );
        enrollJpaRepository.saveAll(enrollList);
    }

    @AfterEach
    void cleanUp() {
        enrollJpaRepository.deleteAll();
    }

    @Test
    @DisplayName(value = "[성공] user가 자기가 신청한 강의 목록을 조회하면 신청한 깅의 목록을 반환합니다.")
    void 사용자는_자기가_신청한_강의_목록을_조회하면_신청한_강의목록을_반환_성공() throws Exception {
        // given
        final Long userId = 1L;

        // when
        ResponseEntity<ResponseDto<EnrollResponse.GetEnrolledOutDto>> response = restTemplate.exchange(
            "/lectures/{userId}",
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<ResponseDto<EnrollResponse.GetEnrolledOutDto>>() {},
            userId
        );

        // then
        assertEquals(HttpStatus.OK, response.getStatusCode(), "응답 상태 코드는 200이어야 합니다.");
        assertNotNull(response.getBody(), "응답 본문이 null이면 안 됩니다.");
        assertEquals("OK", response.getBody().getMessage(), "응답 메시지는 'OK'이어야 합니다.");
        assertNotNull(response.getBody().getData(), "응답 데이터가 null이면 안 됩니다.");

        assertEquals(3, response.getBody().getData().getEnrollList().size(), "사용자가 신청한 강의는 3개여야 합니다.");
        assertEquals(1L, response.getBody().getData().getEnrollList().get(0).getLectureId(), "첫 번째 강의 ID는 1이어야 합니다.");
    }
}
