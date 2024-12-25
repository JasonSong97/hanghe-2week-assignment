package com.example.hanghe_2week_assignment.domain.enroll.api;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.example.hanghe_2week_assignment.domain.enroll.application.EnrollService;
import com.example.hanghe_2week_assignment.domain.enroll.domain.Enroll;
import com.example.hanghe_2week_assignment.domain.enroll.dto.EnrollResponse;

@WebMvcTest(controllers = {
    EnrollController.class
})
public class EnrollControllerUnitTest {
    
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EnrollService enrollService;

    @Test
    @DisplayName(value = "[성공] user가 신청한 강의 목록을 반환하고 상태코드 200과 상태메세지 OK와 데이터를 반환한다.")
    void 사용자가_신청한_강의_목록을_반환하고_상태코드_200과_상태메세지_OK와_데이터를_반환_성공() throws Exception {
        // given
        final Long userId = 1L;
        
        List<Enroll> enrollList = List.of(
            new Enroll(1L, 1L, 1L, LocalDateTime.now(), LocalDateTime.now()),
            new Enroll(2L, 1L, 4L, LocalDateTime.now(), LocalDateTime.now()),
            new Enroll(3L, 1L, 3L, LocalDateTime.now(), LocalDateTime.now())
        );
        EnrollResponse.GetEnrolledOutDto mockEnrollResposneDto = new EnrollResponse.GetEnrolledOutDto(enrollList);

        // when
        given(enrollService.getEnrolledLectures(anyLong())).willReturn(mockEnrollResposneDto);
        ResultActions resultActions = mockMvc.perform(get("/lectures/{userId}", userId)
            .contentType(MediaType.APPLICATION_JSON));
    
        // then
        resultActions
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.message").value("OK"))
            .andExpect(jsonPath("$.data.enrollList[0].enrollId").value(1))
            .andExpect(jsonPath("$.data.enrollList[0].userId").value(1))
            .andExpect(jsonPath("$.data.enrollList[0].lectureId").value(1))
            .andExpect(jsonPath("$.data.enrollList[1].enrollId").value(2))
            .andExpect(jsonPath("$.data.enrollList[1].userId").value(1))
            .andExpect(jsonPath("$.data.enrollList[1].lectureId").value(4))
            .andExpect(jsonPath("$.data.enrollList.length()").value(3));
    }
}
