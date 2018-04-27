package com.thesis.common.model.vo;

import com.thesis.common.model.DeviceStatus;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Author: ZcEdiaos
 * @Date: 2018/4/27 19:56
 * @Description:
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class DeviceVo {

    /**
     * 设备编号
     */
    private Short id;

    /**
     * 设备类型
     */
    private String name;

    /**
     * 设备运行时参数
     */
    private String runningParam;

    /**
     * 设备状态
     */
    private DeviceStatus status;

}
