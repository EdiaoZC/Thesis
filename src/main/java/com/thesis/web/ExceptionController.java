package com.thesis.web;

import com.thesis.common.exception.AccountException;
import com.thesis.common.model.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @Author: ZcEdiaos
 * @Date: 2018/4/9 15:44
 * @Description:
 */
@ControllerAdvice
@Slf4j
public class ExceptionController {

    @ExceptionHandler(RuntimeException.class)
    public Response<String> exception(Throwable e) {
        return Response.<String>builder().code(400)
                .msg("fail").msg(e.getMessage()).build();
    }
}
