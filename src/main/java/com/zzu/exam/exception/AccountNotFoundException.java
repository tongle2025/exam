package com.zzu.exam.exception;

// 账号不存在异常
public class AccountNotFoundException extends RuntimeException {
    public AccountNotFoundException(String message) {
        super(message);
    }
}
