package com.thesis.dao.mapper;

import com.thesis.common.model.EquipmentInfo;

public interface EquipmentInfoMapper {
    int deleteByPrimaryKey(Byte id);

    int insert(EquipmentInfo record);

    int insertSelective(EquipmentInfo record);

    EquipmentInfo selectByPrimaryKey(Byte id);

    int updateByPrimaryKeySelective(EquipmentInfo record);

    int updateByPrimaryKey(EquipmentInfo record);
}