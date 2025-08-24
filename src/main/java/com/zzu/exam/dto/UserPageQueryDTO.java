package com.zzu.exam.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
@Schema(description = "用户分页查询参数")
public class UserPageQueryDTO implements Serializable {

    @Schema(description = "用户id")
    private String id;

    @Schema(description = "当前页码")
    private int page;

    @Schema(description = "每页条数")
    private int pageSize;
}
