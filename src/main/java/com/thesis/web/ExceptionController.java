package com.thesis.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @Author: ZcEdiaos
 * @Date: 2018/4/9 15:44
 * @Description:
 */
@ControllerAdvice
@Slf4j
public class ExceptionController {


    @ExceptionHandler(value = {InternalAuthenticationServiceException.class})
    public ResponseEntity<String> UsernameNOtFoundException(Throwable e) {
        log.error("发生错误");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }
}
