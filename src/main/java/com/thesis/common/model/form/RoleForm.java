package com.thesis.common.model.form;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @Author: ZcEdiaos
 * @Date: 2018/5/27 20:43
 * @Description:
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class RoleForm {


    private String name;

    private String descr;

    /**
     * 权限主键id
     */
    private List<Integer> permissions;
}
