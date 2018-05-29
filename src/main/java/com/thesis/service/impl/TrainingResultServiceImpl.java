package com.thesis.service.impl;

import com.thesis.common.constants.Status;
import com.thesis.common.holder.RolesHolder;
import com.thesis.common.constants.Role;
import com.thesis.common.holder.UserHolder;
import com.thesis.common.model.DeviceStatus;
import com.thesis.common.model.TrainingResult;
import com.thesis.common.model.form.TrainingResultForm;
import com.thesis.common.model.vo.TrainingResultVo;
import com.thesis.dao.mapper.DeviceMapper;
import com.thesis.dao.mapper.TrainingResultMapper;
import com.thesis.service.TrainingResultService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

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

    @Autowired
    private DeviceMapper deviceMapper;

    @Override
    public List<TrainingResultVo> resultList() {
        final Set<String> roles = RolesHolder.getRoles();
        for (String role : roles) {
            if (Role.PATIENTS.equals(role)) {
                String username = UserHolder.getUser();
                if (StringUtils.isNoneBlank(username)) {
                    return personalResult(username, null);
                }
            }
        }
        return resultMapper.getAllInfo();
    }

    @Override
    public List<TrainingResultVo> personalResult(String username, String deviceId) {
        return resultMapper.getAllInfo(username, deviceId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addTrainingResult(TrainingResultForm resultForm) {
        TrainingResult result = new TrainingResult();
        BeanUtils.copyProperties(resultForm, result);
        log.info("result结果是:{}", result);
        deviceMapper.updateStatus(resultForm.getDeviceId(), Status.NORMAL);
        return resultMapper.insertSelective(result) == 1;
    }
}

