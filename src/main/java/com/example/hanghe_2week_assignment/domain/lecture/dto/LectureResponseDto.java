package com.example.hanghe_2week_assignment.domain.lecture.dto;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import com.example.hanghe_2week_assignment.domain.lecture.domain.Lecture;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class LectureResponseDto {
    
    @Getter
    @Setter
    @NoArgsConstructor
    public static class GetPossibleOutDto {

        private List<LectureDto> lectureList;

        public GetPossibleOutDto(List<Lecture> lectureList) {
            this.lectureList = lectureList.stream()
                .map(LectureDto::new)
                .collect(Collectors.toList());
        }

        @Getter
        @Setter
        @NoArgsConstructor
        public static class LectureDto {
            
            private Long lectureId;
            private String lectureTitle;
            private String lectureInstructor;
            private String lectureDate;
            private Integer lectureStudentCount;
            private String createdAt;
            private String updatedAt;

            public LectureDto(Lecture lecture) {
                this.lectureId = lecture.getLectureId();
                this.lectureTitle = lecture.getLectureTitle();
                this.lectureInstructor = lecture.getLectureInstructor();
                this.lectureDate = lecture.getLectureDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
                this.lectureStudentCount = lecture.getLectureStudentCount();
                this.createdAt = lecture.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
                this.updatedAt = lecture.getUpdatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
            }
        }
    }
}
