package com.thesis.service.impl;

import com.thesis.common.model.form.TrainingResultForm;
import com.thesis.common.model.vo.TrainingResultVo;
import com.thesis.service.TrainingResultService;
import lombok.extern.slf4j.Slf4j;
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


    @Override
    public List<TrainingResultVo> resultList() {
        return null;
    }

    @Override
    public List<TrainingResultVo> personalResult(String name) {
        return null;
    }

    @Override
    public boolean addTrainingReult(TrainingResultForm resultForm) {
        return false;
    }
}

