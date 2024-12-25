package com.example.hanghe_2week_assignment.domain.enroll.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.hanghe_2week_assignment.domain.enroll.domain.Enroll;
import com.example.hanghe_2week_assignment.domain.enroll.domain.EnrollRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class EnrollRepositoryImpl implements EnrollRepository{
    
    private final EnrollJpaRepository enrollJpaRepository;

    @Override
    public List<Enroll> findEnrolledLectureList(long userId) {
        return enrollJpaRepository.findEnrolledLectureList(userId);
    }
}
