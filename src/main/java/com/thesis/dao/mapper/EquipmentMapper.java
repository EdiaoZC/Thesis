package com.thesis.dao.mapper;

import com.thesis.common.model.Equipment;

public interface EquipmentMapper {
    int deleteByPrimaryKey(Short id);

    int insert(Equipment record);

    int insertSelective(Equipment record);

    Equipment selectByPrimaryKey(Short id);

    int updateByPrimaryKeySelective(Equipment record);

    int updateByPrimaryKey(Equipment record);
}