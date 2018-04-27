package com.thesis.common.model.form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Author: ZcEdiaos
 * @Date: 2018/4/27 19:36
 * @Description:
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
public class DeviceForm {


    /**
     * 设备编号
     */
    private String deviceId;
    /**
     * 设备类型
     */
    private String equipment_type;

}
