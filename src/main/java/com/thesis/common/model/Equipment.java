package com.thesis.common.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Equipment {
    private Short id;

    private Byte equipmentType;

    private Byte equipmentState;

    private Date createTime;

    private Date updateTime;


}