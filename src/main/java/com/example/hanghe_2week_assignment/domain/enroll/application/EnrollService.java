package com.example.hanghe_2week_assignment.domain.enroll.application;

import org.springframework.stereotype.Service;

import com.example.hanghe_2week_assignment.domain.enroll.domain.EnrollFacade;
import com.example.hanghe_2week_assignment.domain.enroll.dto.EnrollResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EnrollService {
    
    private final EnrollFacade enrollFacade;

    public EnrollResponse.GetEnrolledOutDto getEnrolledLectures(Long userId) {
        return enrollFacade.getEnrolledLectures(userId);
    }
}
