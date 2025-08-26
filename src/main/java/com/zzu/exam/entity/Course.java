package com.zzu.exam.entity;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    /**
     * 课程ID
     */
    private Integer courseId;

    /**
     * 课程代码
     */
    private String courseCode;

    /**
     * 课程名称
     */
    private String courseName;

    /**
     * 教师ID
     */
    private Integer teacherId;

    /**
     * 课程描述
     */
    private String description;

    /**
     * 学期
     */
    private String semester;

    /**
     * 状态 1.正常 2.结课
     */
    private Integer status = 1;
}
