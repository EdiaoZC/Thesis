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
public class DeviceInfo {
    private Byte id;

    private String equipmentParameter;

    private Date createTime;

    private Date updateTime;

}