package com.zzu.exam.service.impl;

import com.zzu.exam.constant.MessageConstant;
import com.zzu.exam.dto.UserDTO;
import com.zzu.exam.entity.User;
import com.zzu.exam.exception.AccountLockedException;
import com.zzu.exam.exception.AccountNotFoundException;
import com.zzu.exam.exception.PasswordErrorException;
import com.zzu.exam.mapper.UserMapper;
import com.zzu.exam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(UserDTO userDTO) {
        String username = userDTO.getUsername();
        String password = userDTO.getPassword();

        // 1、根据用户名查询数据库中的数据
        User user = userMapper.getByUsername(username);

        // 2、处理各种异常情况（用户名不存在、密码不对、账号被锁定）
        if (user == null) {
            // 账号不存在
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        }

        // 密码比对
        // md5加密，然后再进行比对
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        if (!password.equals(user.getPassword())) {
            // 密码错误
            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
        }

        // if (user.getStatus() == StatusConstant.DISABLE) {
        //     //账号被锁定
        //     throw new AccountLockedException(MessageConstant.ACCOUNT_LOCKED);
        // }

        // 3、返回实体对象
        return user;
    }

    @Override
    public User update(UserDTO userDTO) {
        String username = userDTO.getUsername();
        String password = userDTO.getPassword();
        // 1、根据用户名查询数据库中的数据
        User user = userMapper.getByUsername(username);

        // 2、处理各种异常情况（用户名不存在、密码不对、账号被锁定）
        if (user == null) {
            // 账号不存在
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        }
        //获取localtime
        LocalDateTime localtime = LocalDateTime.now();
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        userMapper.updatePassword(username, password, localtime);
        user = userMapper.getByUsername(username);
        return user;
    }
}
