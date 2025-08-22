package com.zzu.exam.controller;


import com.zzu.exam.config.JwtConfig;
import com.zzu.exam.constant.JwtClaimsConstant;
import com.zzu.exam.dto.UserDTO;
import com.zzu.exam.entity.User;
import com.zzu.exam.result.Result;
import com.zzu.exam.service.UserService;
import com.zzu.exam.utils.JwtUtil;
import com.zzu.exam.vo.UserLoginVO;
import com.zzu.exam.vo.UserUpdateVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/user")
@Slf4j
@Tag(name = "用户接口")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtConfig jwtConfig;

    @PostMapping("/register")
    @Operation(summary = "用户注册")
    public Result register(@RequestBody UserDTO userDTO){
        log.info("用户注册：{}", userDTO);
        // userService.register(userDTO);

        return Result.success();
}

    @PostMapping("/login")
    @Operation(summary = "用户登录")
    public Result<UserLoginVO> login(@RequestBody UserDTO userDTO){
        log.info("用户登录：{}", userDTO);

        User user = userService.login(userDTO);

        //登陆成功， 生成jwt令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.USER_ID, user.getId());
        String token = JwtUtil.createJWT(
                jwtConfig.getUserSecretKey(),
                jwtConfig.getUserTtl(),
                claims);

        UserLoginVO userLoginVO = UserLoginVO.builder()
                .id(user.getId())
                .username(user.getUserName())
                .role(user.getRole())
                .token(token)
                .build();

        return Result.success(userLoginVO, "login success");
    }

    @PostMapping("/update")
    @Operation(summary = "用户更新")
    public Result<UserUpdateVO> update(@RequestBody UserDTO userDTO){
        log.info("用户更新：{}", userDTO);
        User user = userService.update(userDTO);
        UserUpdateVO userUpdateVO = UserUpdateVO.builder()
                .id(user.getId())
                .username(user.getUserName())
                .role(user.getRole())
                .password(user.getPassword())
                .build();
        return Result.success(userUpdateVO, "update success");
    }

    @PostMapping("/logout")
    @Operation(summary = "用户登出")
    public Result<String> logout(){
        return Result.success();
    }

}
