package com.example.hanghe_2week_assignment.domain.lecture.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.hanghe_2week_assignment.domain.lecture.domain.Lecture;
import com.example.hanghe_2week_assignment.domain.lecture.domain.LectureRepository;

import lombok.RequiredArgsConstructor;

/**
 * 레포지토리 구현체
 * - JpaRepository 호출
 * - repository 인터페이스의 구현체
 */
@Repository
@RequiredArgsConstructor
public class LectureRepositoryImpl implements LectureRepository{

    private final LectureJpaRepository lectureJpaRepository;
    
    @Override
    public List<Lecture> findPossibleLectureList() {
        return lectureJpaRepository.findPossibleLectureList();
    }
}
