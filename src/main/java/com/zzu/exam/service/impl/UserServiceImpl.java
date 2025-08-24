package com.zzu.exam.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zzu.exam.annotation.AutoFill;
import com.zzu.exam.constant.MessageConstant;
import com.zzu.exam.constant.PasswordConstant;
import com.zzu.exam.dto.UserDTO;
import com.zzu.exam.dto.UserPageQueryDTO;
import com.zzu.exam.entity.OperationType;
import com.zzu.exam.entity.User;
import com.zzu.exam.exception.AccountLockedException;
import com.zzu.exam.exception.AccountNotFoundException;
import com.zzu.exam.exception.PasswordErrorException;
import com.zzu.exam.mapper.UserMapper;
import com.zzu.exam.result.PageResult;
import com.zzu.exam.service.UserService;
import org.springframework.beans.BeanUtils;
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
        String id = userDTO.getId();
        String password = userDTO.getPassword();
        // 获取localtime
        LocalDateTime localtime = LocalDateTime.now();

        // 1、根据用户名查询数据库中的数据
        User user = userMapper.getById(id);

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

        userMapper.updateLastActiveTime(id, localtime);

        // 3、返回实体对象
        return user;
    }

    @Override
    public void update(UserDTO userDTO) {
        User user = new User();
        BeanUtils.copyProperties(userDTO, user);
        userMapper.update(user);
    }

    @Override
    public User insert(UserDTO userDTO) {
        User user = new User();

        // 对象属性拷贝
        BeanUtils.copyProperties(userDTO, user);

        // 默认密码123456且加密
        user.setPassword(DigestUtils.md5DigestAsHex(PasswordConstant.DEFAULT_PASSWORD.getBytes()));
        userMapper.insert(user);

        user = userMapper.getById(user.getId());
        return user;
    }

    @Override
    public PageResult pageQuery(UserPageQueryDTO userPageQueryDTO) {
        //开始分页查询
        PageHelper.startPage(userPageQueryDTO.getPage(), userPageQueryDTO.getPageSize());

        Page<User> page = userMapper.pageQuery(userPageQueryDTO);

        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    public void switchRole(int role, String id) {
        User user = User.builder()
                .id(id)
                .role(role)
                .build();
        userMapper.update(user);
    }

    @Override
    public User getById(String id) {
        User user = userMapper.getById(id);
        return user;
    }
}
