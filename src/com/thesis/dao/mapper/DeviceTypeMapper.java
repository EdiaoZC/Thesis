package com.thesis.dao.mapper;

import com.thesis.common.model.DeviceType;

public interface DeviceTypeMapper {
    int deleteByPrimaryKey(Byte id);

    int insert(DeviceType record);

    int insertSelective(DeviceType record);

    DeviceType selectByPrimaryKey(Byte id);

    int updateByPrimaryKeySelective(DeviceType record);

    int updateByPrimaryKey(DeviceType record);
}