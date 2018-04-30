package com.thesis.common.model.form;

import com.thesis.common.model.RunningParam;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @Author: ZcEdiaos
 * @Date: 2018/4/29 19:52
 * @Description:
 */
@Setter
@Getter
@ToString
@EqualsAndHashCode
public class DeviceTypeForm {

    /**
     * 主键
     */
    private Byte id;

    /**
     * 设备类型
     */
    private String name;


    /**
     * 运行时参数
     */
    private List<RunningParam> run;

}
