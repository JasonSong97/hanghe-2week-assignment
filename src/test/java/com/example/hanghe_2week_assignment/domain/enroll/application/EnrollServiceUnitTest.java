package com.example.hanghe_2week_assignment.domain.enroll.application;

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

import com.example.hanghe_2week_assignment.domain.enroll.domain.Enroll;
import com.example.hanghe_2week_assignment.domain.enroll.domain.EnrollFacade;
import com.example.hanghe_2week_assignment.domain.enroll.dto.EnrollResponse;

@ExtendWith(MockitoExtension.class)
public class EnrollServiceUnitTest {
    
    @InjectMocks
    private EnrollService enrollService;

    @Mock
    private EnrollFacade enrollFacade;
    
    @Test
    @DisplayName(value = "[성공] user가 자기가 신청한 강의 목록을 조회하면 신청한 깅의 목록을 반환합니다.")
    void 사용자는_자기가_신청한_강의목록을_조회하면_신청한_강의목록을_반환_성공() throws Exception {
        // given
        final Long userId = 1L;
        List<Enroll> enrollList = List.of(
            new Enroll(1L, 1L, 1L, LocalDateTime.now(), LocalDateTime.now()),
            new Enroll(2L, 1L, 4L, LocalDateTime.now(), LocalDateTime.now()),
            new Enroll(3L, 1L, 3L, LocalDateTime.now(), LocalDateTime.now())
        );
        EnrollResponse.GetEnrolledOutDto mockEnrollResposneDto = new EnrollResponse.GetEnrolledOutDto(enrollList);
    
        // when
        when(enrollFacade.getEnrolledLectures(anyLong())).thenReturn(mockEnrollResposneDto);
        EnrollResponse.GetEnrolledOutDto result = enrollService.getEnrolledLectures(userId);
    
        // then
        assertNotNull(result);
        assertEquals(3, result.getEnrollList().size());
        assertEquals(1L, result.getEnrollList().get(0).getEnrollId(), "첫 번째 신청 내역의 ID가 1이어야 합니다.");
        assertEquals(1L, result.getEnrollList().get(0).getUserId(), "첫 번째 신청 내역의 userId가 1이어야 합니다.");
        assertEquals(1L, result.getEnrollList().get(0).getLectureId(), "첫 번째 신청 내역의 강의 ID가 1이어야 합니다.");
        assertEquals(2L, result.getEnrollList().get(1).getEnrollId(), "두 번째 신청 내역의 ID가 2이어야 합니다.");
        assertEquals(1L, result.getEnrollList().get(1).getUserId(), "두 번째 신청 내역의 userId가 1이어야 합니다.");
        assertEquals(4L, result.getEnrollList().get(1).getLectureId(), "두 번째 신청 내역의 강의 ID가 4이어야 합니다.");
        assertEquals(3L, result.getEnrollList().get(2).getEnrollId(), "세 번째 신청 내역의 ID가 3이어야 합니다.");
        assertEquals(1L, result.getEnrollList().get(2).getUserId(), "세 번째 신청 내역의 userId가 1이어야 합니다.");
        assertEquals(3L, result.getEnrollList().get(2).getLectureId(), "세 번째 신청 내역의 강의 ID가 3이어야 합니다.");

        verify(enrollFacade, times(1)).getEnrolledLectures(anyLong());
    }
}
