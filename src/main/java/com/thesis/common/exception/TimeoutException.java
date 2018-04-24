package com.thesis.common.exception;

/**
 * @Author: ZcEdiaos
 * @Date: 2018/4/24 10:14
 * @Description: 超时异常
 */
public class TimeoutException extends RuntimeException {

    public TimeoutException(String message) {
        super(message);
    }

    public TimeoutException(String message, Throwable cause) {
        super(message, cause);
    }
}
