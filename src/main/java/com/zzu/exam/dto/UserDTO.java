package com.zzu.exam.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
@Schema(description = "用户持久层")
public class UserDTO implements Serializable {
    @Schema(description = "用户id")
    private String id;

    @Schema(description = "用户名")
    private String userName;

    @Schema(description = "密码")
    private String password;
}
