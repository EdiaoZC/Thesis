package com.thesis.service.impl;

import com.alibaba.fastjson.JSON;
import com.thesis.common.model.DeviceType;
import com.thesis.common.model.Response;
import com.thesis.common.model.form.DeviceTypeForm;
import com.thesis.common.model.vo.DeviceTypeVo;
import com.thesis.dao.mapper.DeviceMapper;
import com.thesis.dao.mapper.DeviceTypeMapper;
import com.thesis.service.DeviceTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: ZcEdiaos
 * @Date: 2018/4/29 16:38
 * @Description:
 */
@Service
@Slf4j
public class DeviceTypeServiceImpl implements DeviceTypeService {

    @Autowired
    private DeviceTypeMapper deviceTypeMapper;

    @Autowired
    private DeviceMapper deviceMapper;

    @Override
    public List<DeviceTypeVo> deviceTypeList() {
        List<DeviceType> deviceTypes = deviceTypeMapper.getAllInfo();
        List<DeviceTypeVo> deviceTypeVos = new ArrayList<>();
        for (DeviceType deviceType : deviceTypes) {
            DeviceTypeVo deviceTypeVo = new DeviceTypeVo();
            deviceTypeVo.setId(deviceType.getId());
            deviceTypeVo.setDeviceType(deviceType.getEquipmentName());
            deviceTypeVo.setRunningParam(deviceType.getRunningParam());
            deviceTypeVos.add(deviceTypeVo);
        }
        return deviceTypeVos;
    }

    @Override
    public DeviceTypeVo deviceType(Byte deviceTypeId) {
        DeviceType type = deviceTypeMapper.selectByPrimaryKey(deviceTypeId);
        DeviceTypeVo typeVo = new DeviceTypeVo();
        typeVo.setDeviceType(type.getEquipmentName());
        typeVo.setRunningParam(type.getRunningParam());
        typeVo.setId(type.getId());
        return typeVo;
    }

    @Override
    public boolean addType(DeviceTypeForm deviceTypeForm) {
        DeviceType type = new DeviceType();
        type.setId(deviceTypeForm.getId());
        type.setEquipmentName(deviceTypeForm.getName());
        type.setRunningParam(JSON.toJSONString(deviceTypeForm.getRun()));
        log.info("type对象是:{}", type);
        return deviceTypeMapper.insertSelective(type) == 1;
    }

    @Override
    public boolean updateInfo(DeviceTypeForm deviceTypeForm) {
        DeviceType type = new DeviceType();
        type.setId(deviceTypeForm.getId());
        type.setEquipmentName(deviceTypeForm.getName());
        type.setRunningParam(JSON.toJSONString(deviceTypeForm.getRun()));
        return deviceTypeMapper.updateByPrimaryKeySelective(type) == 1;
    }

    @Override
    public Response<String> delType(Byte id) {
        int count = deviceMapper.getDeviceByType(id);
        if (count > 0) {
            return Response.<String>builder().code(400).msg("该类型下有其它设备，无法删除").build();
        } else {
            deviceTypeMapper.deleteByPrimaryKey(id);
            return Response.<String>builder().code(200).msg("success").build();
        }
    }
}
