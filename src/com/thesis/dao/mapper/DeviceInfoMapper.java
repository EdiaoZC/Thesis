package com.thesis.dao.mapper;

import com.thesis.common.model.DeviceInfo;

public interface DeviceInfoMapper {
    int deleteByPrimaryKey(Byte id);

    int insert(DeviceInfo record);

    int insertSelective(DeviceInfo record);

    DeviceInfo selectByPrimaryKey(Byte id);

    int updateByPrimaryKeySelective(DeviceInfo record);

    int updateByPrimaryKey(DeviceInfo record);
}