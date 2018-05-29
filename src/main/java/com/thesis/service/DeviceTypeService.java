package com.thesis.service;

import com.thesis.common.model.Response;
import com.thesis.common.model.form.DeviceTypeForm;
import com.thesis.common.model.vo.DeviceTypeVo;

import java.util.List;

/**
 * @Author: ZcEdiaos
 * @Date: 2018/4/29 16:36
 * @Description:
 */
public interface DeviceTypeService {

    /**
     * 获取所有设备类型
     *
     * @return 所有设备类型
     */
    List<DeviceTypeVo> deviceTypeList();


    /**
     * 获取某个设备类型详细信息
     *
     * @param deviceTypeId 设备类型id
     * @return 设备类型详细信息
     */
    DeviceTypeVo deviceType(Byte deviceTypeId);

    /**
     * 添加设备类型
     *
     * @param deviceTypeForm
     * @return 是否添加成功
     */
    boolean addType(DeviceTypeForm deviceTypeForm);


    /**
     * 更新设备类型信息
     *
     * @param deviceTypeForm
     * @return 是否更新成功
     */
    boolean updateInfo(DeviceTypeForm deviceTypeForm);

    /**
     * 删除设备类型信息
     *
     * @param id
     * @return 是否删除成功
     */
    Response<String> delType(Byte id);
}
