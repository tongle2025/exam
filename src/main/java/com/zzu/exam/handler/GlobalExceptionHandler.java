package com.zzu.exam.handler;

import com.zzu.exam.constant.MessageConstant;
import com.zzu.exam.exception.BaseException;
import com.zzu.exam.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * 全局异常处理器，处理项目中抛出的业务异常
 * @RestControllerAdvice在springboot3.4已被移除 这里使用了
 * <dependency>
 *  <groupId>com.github.xingfudeshi</groupId>
 *  <artifactId>knife4j-openapi3-jakarta-spring-boot-starter</artifactId>
 *  <version>4.6.0</version>
 * </dependency>
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 捕获业务异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler
    public Result exceptionHandler(BaseException ex) {
        log.error("异常信息：{}", ex.getMessage());
        return Result.error(ex.getMessage());
    }

    /**
     * 处理用户id唯一异常
     *
     * @param ex
     * @return Result
     */
    @ExceptionHandler
    public Result exceptionHandler(SQLIntegrityConstraintViolationException ex) {
        // Duplicate entry 'id' for key 'user.PRIMARY'
        String message = ex.getMessage();
        if (message.contains("Duplicate entry")) {
            String[] split = message.split(" ");
            String id = split[2];
            message = "用户id" + id + MessageConstant.ALREADY_EXIST;
            return Result.error(message);
        } else return Result.error(MessageConstant.UNKNOWN_ERROR);
    }

}
