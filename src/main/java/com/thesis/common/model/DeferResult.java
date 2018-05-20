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
    public static final int CANCEL = 3;

    /**
     * 处理状态：0：未处理，1：正在处理，2：已处理，3：取消
     */
    public volatile int status = 0;

    public DeferResult() {
    }

    public DeferResult(Long timeout) {
        super(timeout);
    }

    public DeferResult(Long timeout, Object timeoutResult) {
        super(timeout, timeoutResult);
    }
}
