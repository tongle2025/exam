package com.zzu.exam.entity;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassCourse {
    /**
     * 班级ID
     */
    private Integer classId;

    /**
     * 课程ID
     */
    private Integer courseId;

    /**
     * 教师ID
     */
    private Integer teacherId;
}
