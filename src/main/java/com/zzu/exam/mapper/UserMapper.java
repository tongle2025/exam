package com.zzu.exam.mapper;

import com.zzu.exam.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.time.LocalDateTime;

@Mapper
public interface UserMapper {
    @Select("select * from user where user_name = #{username}")
    User getByUsername(String username);

    @Update("update user " +
            "set password = #{password}, update_time = #{updatetime} " +
            "where user_name = #{username}")
    int updatePassword(String username, String password, LocalDateTime updatetime);
}

