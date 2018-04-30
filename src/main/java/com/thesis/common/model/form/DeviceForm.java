package com.thesis.common.model.form;

import lombok.*;

/**
 * @Author: ZcEdiaos
 * @Date: 2018/4/27 19:36
 * @Description:
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class DeviceForm {


    /**
     * 主键
     */
    private Short id;

    /**
     * 设备编号
     */
    private String deviceId;
    /**
     * 设备类型
     */
    private Byte deviceType;

    /**
     * 设备状态
     */
    private Byte status;

}
