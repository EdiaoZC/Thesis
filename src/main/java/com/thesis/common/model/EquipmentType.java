package com.thesis.common.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class EquipmentType {
    private Byte id;

    private String equipmentName;

    private Byte pid;

    private Date createTime;

    private Date updateTime;


}