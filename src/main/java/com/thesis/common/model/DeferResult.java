package com.thesis.common.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.context.request.async.DeferredResult;

/**
 * @Author: ZcEdiaos
 * @Date: 2018/4/24 11:49
 * @Description:
 */
@Setter
@Getter
public class DeferResult<T> extends DeferredResult<T> {


    public static final int START = 0;
    public static final int RUNNING = 1;
    public static final int COMPLETE = 2;

    /**
     * 处理状态：0：未处理，1：正在处理，2：已处理
     */
    public volatile int status;
}
