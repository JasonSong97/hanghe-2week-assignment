package com.example.hanghe_2week_assignment.domain.enroll.domain;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.hanghe_2week_assignment.domain.enroll.dto.EnrollResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EnrollFacade {
    
    private final EnrollRepository enrollRepository;

    public EnrollResponse.GetEnrolledOutDto getEnrolledLectures(Long userId) {
        List<Enroll> PSenrollList = enrollRepository.findEnrolledLectureList(userId);
        return new EnrollResponse.GetEnrolledOutDto(PSenrollList);
    }
}
