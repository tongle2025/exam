package com.zzu.exam.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 试题类型枚举
 */
public enum QuestionType {

    /**
     * 选择题
     */
    CHOICE(1, "选择题"),

    /**
     * 判断题
     */
    JUDGMENT(2, "判断题"),

    /**
     * 简答题
     */
    ANSWER(3, "简答题");

    private final int code;
    private final String description;

    QuestionType(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    /**
     * 根据code获取试题类型
     * @param code 类型代码
     * @return 对应的试题类型
     */
    public static QuestionType fromCode(int code) {
        for (QuestionType type : QuestionType.values()) {
            if (type.getCode() == code) {
                return type;
            }
        }
        throw new IllegalArgumentException("无效的试题类型代码: " + code);
    }
}
