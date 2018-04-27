package com.thesis.common.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * @Author: ZcEdiaos
 * @Date: 2018/4/27 20:52
 * @Description:
 */
@Getter
@AllArgsConstructor
@ToString
public enum DeviceStatus {

    NORMAL("正常"), RUNNING("使用中"), BROKNE("故障");

    private String status;
}
