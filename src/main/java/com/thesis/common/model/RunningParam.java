package com.thesis.common.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/**
 * @Author: ZcEdiaos
 * @Date: 2018/4/24 10:09
 * @Description: 康复设备运行时所需参数
 */
@Setter
@Getter
@ToString
@EqualsAndHashCode
public class RunningParam {

    /**
     * 设备运行时所需参数,格式为json
     */
    private String key;
    /**
     * 设备运行时所需参数,格式为json
     */
    private String value;

}
