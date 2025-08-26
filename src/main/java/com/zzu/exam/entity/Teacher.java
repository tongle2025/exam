package com.zzu.exam.entity;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Teacher {
    /**
     * 教师ID
     */
    private Integer teacherId;

    /**
     * 教师姓名
     */
    private String name;

    /**
     * 院系ID
     */
    private Integer departmentId;
}
