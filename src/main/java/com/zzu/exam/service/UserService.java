package com.zzu.exam.service;

import com.zzu.exam.dto.UserDTO;
import com.zzu.exam.dto.UserPageQueryDTO;
import com.zzu.exam.entity.User;
import com.zzu.exam.result.PageResult;

public interface UserService {
    User login(UserDTO userDTO);
    void update(UserDTO userDTO);
    User insert(UserDTO userDTO);


    /**
     * 用户分页查询
     * @param userPageQueryDTO
     * @return
     */
    PageResult pageQuery(UserPageQueryDTO userPageQueryDTO);

    /**
     * 切换用户角色
     * @param role
     * @param id
     */
    void switchRole(int role, String id);

    /**
     * 根据id查询用户信息
     * @param id
     * @return
     */
    User getById(String id);
}
