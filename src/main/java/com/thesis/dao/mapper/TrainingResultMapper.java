package com.thesis.dao.mapper;

import com.thesis.common.model.TrainingResult;

public interface TrainingResultMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TrainingResult record);

    int insertSelective(TrainingResult record);

    TrainingResult selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TrainingResult record);

    int updateByPrimaryKey(TrainingResult record);
}