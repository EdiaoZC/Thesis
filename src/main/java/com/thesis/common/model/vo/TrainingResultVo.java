package com.thesis.common.model.vo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Date;

/**
 * @Author: ZcEdiaos
 * @Date: 2018/4/20 18:53
 * @Description:
 */
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class TrainingResultVo {

    /**
     * 用户名
     */
    private String username;

    /**
     * 设备编号
     */
    private String deviceId;

    /**
     * 实际运行参数
     */
    private String actualParams;

    /**
     * 分数
     */
    private String score;

    /**
     * 训练日期
     */
    private Date trainingTime;

}
