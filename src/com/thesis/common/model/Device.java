package com.thesis.common.model;

import java.util.Date;

public class Device {
    private Short id;

    private Byte equipmentType;

    private Byte equipmentState;

    private Date createTime;

    private Date updateTime;

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public Byte getEquipmentType() {
        return equipmentType;
    }

    public void setEquipmentType(Byte equipmentType) {
        this.equipmentType = equipmentType;
    }

    public Byte getEquipmentState() {
        return equipmentState;
    }

    public void setEquipmentState(Byte equipmentState) {
        this.equipmentState = equipmentState;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}