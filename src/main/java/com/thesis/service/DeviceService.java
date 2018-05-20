package com.thesis.service;

import com.thesis.common.exception.RequestAlreadyException;
import com.thesis.common.exception.TimeoutException;
import com.thesis.common.model.Device;
import com.thesis.common.model.Response;
import com.thesis.common.model.RunningParam;
import com.thesis.common.model.form.DeviceForm;
import com.thesis.common.model.form.DeviceRequestForm;
import com.thesis.common.model.form.RequestForm;
import com.thesis.common.model.vo.DeviceRequestVo;
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
     * @param form
     * @return 设备运行时所需参数
     * @throws TimeoutException
     */
    List<RunningParam> requestDevice(String token, DeviceRequestForm form) throws TimeoutException;


    /**
     * 医生返回设备运行参数
     *
     * @param request 设备运行时参数
     * @return 处理结果是否成功
     * @throws RequestAlreadyException
     */
    Response<String> handleRequest(RequestForm request) throws RequestAlreadyException;


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

    /**
     * 预处理请求
     *
     * @param token
     * @return
     */
    Response<String> preHandle(String token);

    /**
     * 返回默认运行参数，及以往训练结果
     *
     * @param token
     * @return
     */
    DeviceRequestVo doHandle(String token);

    /**
     * 返回默认运行参数
     *
     * @param deviceId
     * @return
     */
    String getRun(String deviceId);

    /**
     * 返回未处理的设备请求
     *
     * @return 未处理的设备请求的url
     */
    Response<Object> unHandleRequest();

    /**
     * 取消使用设备的请求
     *
     * @param token    用户的标识
     * @param deviceId 设备标识
     * @return
     */
    Response<String> cancelRequest(String token, String deviceId);
}
