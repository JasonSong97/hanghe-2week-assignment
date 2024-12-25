package com.example.hanghe_2week_assignment.domain.enroll.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
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

import com.example.hanghe_2week_assignment.domain.enroll.dto.EnrollResponse;

@ExtendWith(MockitoExtension.class)
public class EnrollFacadeUnitTest {
    
    @InjectMocks
    private EnrollFacade enrollFacade;

    @Mock
    private EnrollRepository enrollRepository;

    @Test
    @DisplayName(value = "[성공] user가 자기가 신청한 강의 목록을 조회하면 신청한 깅의 목록을 반환합니다.")
    void 사용자는_자기가_신청한_강의_목록을_조회하면_신청한_강의목록을_반환_성공() throws Exception {
        // given
        final Long uesrId = 1L;
        List<Enroll> enrollList = List.of(
            new Enroll(1L, 1L, 1L, LocalDateTime.now(), LocalDateTime.now()),
            new Enroll(2L, 1L, 4L, LocalDateTime.now(), LocalDateTime.now()),
            new Enroll(3L, 1L, 3L, LocalDateTime.now(), LocalDateTime.now())
        );
    
        // when
        when(enrollRepository.findEnrolledLectureList(anyLong())).thenReturn(enrollList);
        EnrollResponse.GetEnrolledOutDto result = enrollFacade.getEnrolledLectures(uesrId);
    
        // then
        assertNotNull(result);
        assertEquals(3, result.getEnrollList().size());
        assertEquals(4L, result.getEnrollList().get(1).getLectureId());

        verify(enrollRepository, times(1)).findEnrolledLectureList(anyLong());
    }
}
