package com.thesis.common.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Device {
    private Short id;

    private String deviceId;

    private Byte equipmentType;

    private Byte equipmentState;

    private Date createTime;

    private Date updateTime;


}