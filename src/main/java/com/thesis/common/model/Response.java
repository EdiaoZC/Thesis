package com.thesis.common.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author: ZcEdiaos
 * @Date: 2018/4/9 17:14
 * @Description: 默认响应信息
 */
@Getter
@Setter
@Builder
public class Response<T> {

    int code;

    String msg;

    T data;

}
