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
public class DeviceType {
    private Byte id;

    private String equipmentName;

    private Byte pid;

    private Date createTime;

    private Date updateTime;


}