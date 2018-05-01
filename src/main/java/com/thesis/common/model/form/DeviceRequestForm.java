package com.thesis.common.model.form;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Author: ZcEdiaos
 * @Date: 2018/5/1 14:55
 * @Description:
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class DeviceRequestForm {

    private String username;

    private String deviceId;
}
