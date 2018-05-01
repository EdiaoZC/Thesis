package com.thesis.dao.mapper;

import com.thesis.common.model.TrainingResult;
import com.thesis.common.model.vo.TrainingResultVo;

import java.util.List;

/**
 * @Author: ZcEdiaos
 * @Date: 2018/4/16 16:35
 * @Description:
 */
public interface TrainingResultMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TrainingResult record);

    int insertSelective(TrainingResult record);

    TrainingResult selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TrainingResult record);

    int updateByPrimaryKey(TrainingResult record);

    List<TrainingResultVo> getAllInfo(String username, String deviceId);

    List<TrainingResultVo> getAllInfo(String username);

    List<TrainingResultVo> getAllInfo();
}