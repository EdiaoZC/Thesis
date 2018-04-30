package com.thesis.common.exception;

/**
 * @Author: ZcEdiaos
 * @Date: 2018/4/28 15:37
 * @Description:
 */
public class AccountException extends RuntimeException {

    public AccountException(String message) {
        super(message);
    }

    public AccountException(String message, Throwable cause) {
        super(message, cause);
    }
}
