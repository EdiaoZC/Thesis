package com.thesis.common.model.vo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @Author: ZcEdiaos
 * @Date: 2018/5/1 15:10
 * @Description:
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class DeviceRequestVo {

    private String params;

    private List<TrainingResultVo> results;

    private String comment;
}
