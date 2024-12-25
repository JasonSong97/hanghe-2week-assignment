package com.example.hanghe_2week_assignment.domain.lecture.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.hanghe_2week_assignment.domain.lecture.domain.Lecture;

/**
 * Jpa 레포지토리
 * - DB에 접속해 데이터를 호출하는 인터페이스 계층
 * - JpaRepository 상속
 */
public interface LectureJpaRepository extends JpaRepository<Lecture, Long>{

    @Query("SELECT l FROM Lecture AS l WHERE l.lectureCountCheck = false AND l.lectureStudentCount > 0")
    List<Lecture> findPossibleLectureList();
}
