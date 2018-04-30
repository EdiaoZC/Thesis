package com.thesis.service;

import com.thesis.common.exception.RequestAlreadyException;
import com.thesis.common.exception.TimeoutException;
import com.thesis.common.model.Device;
import com.thesis.common.model.Response;
import com.thesis.common.model.RunningParam;
import com.thesis.common.model.form.DeviceForm;
import com.thesis.common.model.vo.DeviceVo;

import java.util.List;

/**
 * @Author: ZcEdiaos
 * @Date: 2018/4/9 14:03
 * @Description:
 */
public interface DeviceService {


    /**
     * 添加设备
     *
     * @param device 设备详细信息
     * @return 是否成功
     */
    boolean addDevice(DeviceForm device);

    /**
     * 删除设备
     *
     * @param deviceId 设备编号
     * @return 是否成功
     */
    boolean delDevice(Short deviceId);

    /**
     * 康复患者请求使用设备
     *
     * @param token 用户token
     * @return 设备运行时所需参数
     * @throws TimeoutException
     */
    RunningParam requestDevice(String token) throws TimeoutException;


    /**
     * 医生返回设备运行参数
     *
     * @param token        用户token
     * @param runningParam 设备运行时参数
     * @return 处理结果是否成功
     * @throws RequestAlreadyException
     */
    Response<String> handleRequest(String token, RunningParam runningParam) throws RequestAlreadyException;


    /**
     * 更改设备信息
     *
     * @param device 设备参数
     * @return 是否成功
     */
    boolean updateInfo(DeviceForm device);

    /**
     * 获取设备列表
     *
     * @return 设备列表信息
     */
    List<DeviceVo> deviceList();

    /**
     * 根据设备 id 获取详细信息
     *
     * @param deviceId 设备id
     * @return 设备详细信息
     */
    Device getInfo(Short deviceId);
}
