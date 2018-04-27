package com.thesis.dao.mapper;

import com.thesis.common.model.Device;
import com.thesis.common.model.vo.DeviceVo;

import java.util.List;

public interface DeviceMapper {
    int deleteByPrimaryKey(Short id);

    int insert(Device record);

    int insertSelective(Device record);

    Device selectByPrimaryKey(Short id);

    int updateByPrimaryKeySelective(Device record);

    int updateByPrimaryKey(Device record);

    List<DeviceVo> getAllInfo();
}