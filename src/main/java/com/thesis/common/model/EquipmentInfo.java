package com.thesis.common.model;

import java.util.Date;

public class EquipmentInfo {
    private Byte id;

    private String equipmentParameter;

    private Date createTime;

    private Date updateTime;

    public Byte getId() {
        return id;
    }

    public void setId(Byte id) {
        this.id = id;
    }

    public String getEquipmentParameter() {
        return equipmentParameter;
    }

    public void setEquipmentParameter(String equipmentParameter) {
        this.equipmentParameter = equipmentParameter == null ? null : equipmentParameter.trim();
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