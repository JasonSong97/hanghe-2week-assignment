package com.example.hanghe_2week_assignment.domain.enroll.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.hanghe_2week_assignment.domain.enroll.domain.Enroll;

public interface EnrollJpaRepository extends JpaRepository<Enroll, Long> {

    @Query("SELECT e FROM Enroll AS e WHERE e.userId = :userId")
    List<Enroll> findEnrolledLectureList(@Param("userId") long userId);
    
}
