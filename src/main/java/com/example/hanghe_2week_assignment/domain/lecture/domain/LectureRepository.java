package com.example.hanghe_2week_assignment.domain.lecture.domain;

import java.util.List;

/**
 * 레포지토리
 * - 인터페이스
 * - 데이터 접근 레이어의 추상화
 */
public interface LectureRepository {

    List<Lecture> findPossibleLectureList();
    
}
