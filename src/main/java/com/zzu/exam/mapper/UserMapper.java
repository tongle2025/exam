package com.zzu.exam.mapper;

import com.github.pagehelper.Page;
import com.zzu.exam.dto.UserPageQueryDTO;
import com.zzu.exam.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.time.LocalDateTime;

@Mapper
public interface UserMapper {
    @Select("select * from user where id = #{id}")
    User getById(String id);

    @Update("update user " +
            "set password = #{password}, update_time = #{updateTime} " +
            "where id = #{id}")
    void updatePassword(String id, String password, LocalDateTime updateTime);

    @Update("update user " +
            "set last_active_time = #{lastActiveTime} " +
            "where id = #{id}")
    void updateLastActiveTime(String id, LocalDateTime lastActiveTime);

    @Insert("insert into user (id, user_name, password, create_time, update_time) " +
            "values " +
            "(#{id}, #{userName}, #{password}, #{createTime}, #{updateTime})")
    void insert(User user);


    /**
     * 分页查询用户
     * @param userPageQueryDTO
     * @return
     */
    Page<User> pageQuery(UserPageQueryDTO userPageQueryDTO);

    /**
     * 根据主键动态修改用户信息
     * @param user
     */
    void update(User user);
}

