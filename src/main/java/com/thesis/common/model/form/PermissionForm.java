package com.thesis.common.model.form;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Author: ZcEdiaos
 * @Date: 2018/5/28 14:44
 * @Description:
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class PermissionForm {

    private String perName;

    private String perUrl;

    private String perDesc;
}
