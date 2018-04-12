package com.thesis.common.exception;

/**
 * @Author: ZcEdiaos
 * @Date: 2018/4/12 15:22
 * @Description:
 */
public class UsernameRepeatException extends RuntimeException {


    public UsernameRepeatException() {
    }

    public UsernameRepeatException(String message) {
        super(message);
    }
}
