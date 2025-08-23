package com.zzu.exam.service;

import com.zzu.exam.dto.UserDTO;
import com.zzu.exam.entity.User;

public interface UserService {
    User login(UserDTO userDTO);
    User update(UserDTO userDTO);
    User register(UserDTO userDTO);
}
