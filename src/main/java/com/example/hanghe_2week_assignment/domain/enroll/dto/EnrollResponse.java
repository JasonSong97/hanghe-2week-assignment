package com.example.hanghe_2week_assignment.domain.enroll.dto;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import com.example.hanghe_2week_assignment.domain.enroll.domain.Enroll;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class EnrollResponse {
    
    @Getter
    @Setter
    @NoArgsConstructor
    public static class GetEnrolledOutDto {

        private List<EnrollDto> enrollList;

        public GetEnrolledOutDto(List<Enroll> enrollList) {
            this.enrollList = enrollList.stream()
                .map(EnrollDto::new)
                .collect(Collectors.toList());
        }

        @Getter
        @Setter
        @NoArgsConstructor
        public static class EnrollDto {

            private Long enrollId;
            private Long userId;
            private Long lectureId;
            private String createdAt;
            private String updatedAt;

            public EnrollDto(Enroll enroll) {
                this.enrollId = enroll.getEnrollId();
                this.userId = enroll.getUserId();
                this.lectureId = enroll.getLectureId();
                this.createdAt = enroll.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
                this.updatedAt = enroll.getUpdatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
            }
        }
    }
}
