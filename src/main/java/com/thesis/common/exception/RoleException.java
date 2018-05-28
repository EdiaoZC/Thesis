package com.thesis.common.exception;

/**
 * @Author: ZcEdiaos
 * @Date: 2018/5/28 18:46
 * @Description:
 */
public class RoleException extends RuntimeException {
    public RoleException() {
    }

    public RoleException(String message) {
        super(message);
    }

    public RoleException(Throwable cause) {
        super(cause);
    }
}
