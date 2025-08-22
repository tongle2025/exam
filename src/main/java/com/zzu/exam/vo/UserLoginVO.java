package com.zzu.exam.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "用户登录返回信息")
public class UserLoginVO implements Serializable {

    @Schema(description = "用户id")
    private String id;

    @Schema(description = "用户名")
    private String username;

    @Schema(description = "用户角色")
    private Integer role;

    @Schema(description = "jwt令牌")
    private String token;
}
