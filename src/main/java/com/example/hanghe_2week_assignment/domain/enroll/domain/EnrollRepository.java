package com.example.hanghe_2week_assignment.domain.enroll.domain;

import java.util.List;

public interface EnrollRepository {

    List<Enroll> findEnrolledLectureList(long anyLong);
    
}
