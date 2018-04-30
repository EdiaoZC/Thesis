package com.thesis.common.model.form;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Author: ZcEdiaos
 * @Date: 2018/4/30 22:11
 * @Description:
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class TrainingResultForm {

    /**
     * 用户编号
     */
    private String patientId;

    /**
     * 设备类型
     */
    private String deviceType;

    /**
     * 实际运行参数
     */
    private String actualParam;

    /**
     * 评价
     */
    private String score;
}
