package com.thesis.dao.mapper;

import com.thesis.common.model.Device;

public interface DeviceMapper {
    int deleteByPrimaryKey(Short id);

    int insert(Device record);

    int insertSelective(Device record);

    Device selectByPrimaryKey(Short id);

    int updateByPrimaryKeySelective(Device record);

    int updateByPrimaryKey(Device record);
}