package com.thesis.dao.mapper;

import com.thesis.common.model.EquipmentType;

public interface EquipmentTypeMapper {
    int deleteByPrimaryKey(Byte id);

    int insert(EquipmentType record);

    int insertSelective(EquipmentType record);

    EquipmentType selectByPrimaryKey(Byte id);

    int updateByPrimaryKeySelective(EquipmentType record);

    int updateByPrimaryKey(EquipmentType record);
}