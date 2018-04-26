package com.thesis.common.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author: ZcEdiaos
 * @Date: 2018/4/12 14:00
 * @Description:
 */
@Getter
@AllArgsConstructor
public enum SexEnum {

    Male("男"), Female("女");

    private String sex;

}
