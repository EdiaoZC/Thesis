package com.thesis.service.impl;

import com.thesis.common.model.TrainingResult;
import com.thesis.common.model.form.TrainingResultForm;
import com.thesis.common.model.vo.TrainingResultVo;
import com.thesis.dao.mapper.TrainingResultMapper;
import com.thesis.service.TrainingResultService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: ZcEdiaos
 * @Date: 2018/4/11 11:22
 * @Description:
 */
@Slf4j
@Service
public class TrainingResultServiceImpl implements TrainingResultService {

    @Autowired
    private TrainingResultMapper resultMapper;

    @Override
    public List<TrainingResultVo> resultList() {
        return resultMapper.getAllInfo();
    }

    @Override
    public List<TrainingResultVo> personalResult(String username, String deviceId) {
        return resultMapper.getAllInfo(username, deviceId);
    }

    @Override
    public boolean addTrainingResult(TrainingResultForm resultForm) {
        TrainingResult result = new TrainingResult();
        BeanUtils.copyProperties(resultForm, result);
        log.info("result结果是:{}", result);
        return resultMapper.insertSelective(result) == 1;
    }
}

