package com.thesis.service;

import com.thesis.common.exception.RequestAlreadyException;
import com.thesis.common.exception.TimeoutException;
import com.thesis.common.model.Device;
import com.thesis.common.model.Response;
import com.thesis.common.model.RunningParam;

/**
 * @Author: ZcEdiaos
 * @Date: 2018/4/9 14:03
 * @Description:
 */
public interface DeviceService {


    /**
     * 添加设备
     *
     * @return
     */
    int addDevice(Device device);

    /**
     * 删除设备
     *
     * @return
     */
    int delDevice(Short deviceId);

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


}
