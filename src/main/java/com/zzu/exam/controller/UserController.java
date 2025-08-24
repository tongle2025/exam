package com.zzu.exam.controller;


import com.zzu.exam.config.JwtConfig;
import com.zzu.exam.constant.JwtClaimsConstant;
import com.zzu.exam.context.BaseContext;
import com.zzu.exam.dto.UserDTO;
import com.zzu.exam.dto.UserPageQueryDTO;
import com.zzu.exam.entity.User;
import com.zzu.exam.result.PageResult;
import com.zzu.exam.result.Result;
import com.zzu.exam.service.UserService;
import com.zzu.exam.utils.JwtUtil;
import com.zzu.exam.vo.UserLoginVO;
import com.zzu.exam.vo.UserRegisterVO;
import com.zzu.exam.vo.UserUpdateVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public Result register(@RequestBody UserDTO userDTO) {
        log.info("用户注册：{} 注册人id：{}", userDTO, BaseContext.getCurrentId());
        User user = userService.register(userDTO);

        UserRegisterVO userRegisterVO = UserRegisterVO.builder()
                .id(user.getId())
                .username(user.getUserName())
                .password(user.getPassword())
                .createTime(user.getCreateTime())
                .updateTime(user.getUpdateTime())
                .build();
        return Result.success(userRegisterVO, "register success");
    }

    @PostMapping("/login")
    @Operation(summary = "用户登录")
    public Result<UserLoginVO> login(@RequestBody UserDTO userDTO) {
        log.info("用户登录：{}", userDTO);

        User user = userService.login(userDTO);

        // 登陆成功， 生成jwt令牌
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

    @PutMapping("/update")
    @Operation(summary = "用户信息更新")
    public Result update(@RequestBody UserDTO userDTO) {
        log.info("用户信息更新：{}", userDTO);
        userService.update(userDTO);
        return Result.success();
    }

    @PostMapping("/logout")
    @Operation(summary = "用户登出")
    public Result<String> logout() {
        return Result.success();
    }


    /**
     * @param userPageQueryDTO
     * @return
     */
    @GetMapping("/page")
    @Operation(summary = "分页查询用户")
    public Result<PageResult> page(@ParameterObject UserPageQueryDTO userPageQueryDTO) {
        log.info("用户分页查询，参数为：{}", userPageQueryDTO);
        PageResult pageResult = userService.pageQuery(userPageQueryDTO);

        return Result.success(pageResult, "查询结果");
    }


    @PostMapping("/switchRole/{role}")
    @Operation(summary = "切换用户角色")
    public Result switchRole(@PathVariable int role, String id) {
        log.info("切换用户角色：{}，{}", role, id);
        userService.switchRole(role, id);
        return Result.success();
    }


    @GetMapping("/{id}")
    @Operation(summary = "查询用户信息")
    public Result<User> getById(@PathVariable String id){
        User user = userService.getById(id);
        return Result.success(user, "用户信息");
    }


}
