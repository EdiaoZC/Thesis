package com.thesis.service;

import com.thesis.common.model.form.TrainingResultForm;
import com.thesis.common.model.vo.TrainingResultVo;

import java.util.List;

/**
 * @Author: ZcEdiaos
 * @Date: 2018/4/11 11:23
 * @Description:
 */
public interface TrainingResultService {

    /**
     * 返回所有训练结果
     *
     * @return
     */
    List<TrainingResultVo> resultList();

    /**
     * 按用户名查询患者的训练结果
     *
     * @param name 用户名
     * @return 某个用户的训练结果
     */
    List<TrainingResultVo> personalResult(String name);

    /**
     * 记录训练结果
     *
     * @param resultForm 记录训练结果
     * @return 是否记录成功
     */
    boolean addTrainingReult(TrainingResultForm resultForm);
}
