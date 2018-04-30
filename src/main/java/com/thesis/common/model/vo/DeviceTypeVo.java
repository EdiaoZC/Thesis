package com.thesis.common.model.vo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Author: ZcEdiaos
 * @Date: 2018/4/29 16:22
 * @Description:
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class DeviceTypeVo {


    /**
     * 主键
     */
    private Byte id;
    /**
     * 设备类型
     */
    private String deviceType;

    /**
     * 运行参数
     */
    private String runningParam;
}
