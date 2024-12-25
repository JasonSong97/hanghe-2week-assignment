package com.example.hanghe_2week_assignment.domain.lecture.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Builder
@Table(name = "lecture_tb")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Lecture {
    
    @Id
    @Column(name = "lecture_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lectureId;

    @Column(name = "lecture_title", nullable = false, length = 45)
    private String lectureTitle;

    @Column(name = "lecture_instructor", nullable = false, length = 45)
    private String lectureInstructor;

    @Column(name = "lecture_date", nullable = false)
    private LocalDateTime lectureDate;
    
    @Column(name = "lecture_student_count", nullable = false, length = 45)
    private Integer lectureStudentCount;

    @Builder.Default
    @Column(name = "lecture_count_check", nullable = false)
    private Boolean lectureCountCheck = false;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
