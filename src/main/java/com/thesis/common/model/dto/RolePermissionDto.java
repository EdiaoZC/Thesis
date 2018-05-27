package com.thesis.common.model.dto;

import com.thesis.common.model.Permission;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @Author: ZcEdiaos
 * @Date: 2018/5/27 20:54
 * @Description:
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class RolePermissionDto {

    private Byte roleId;

    private String name;

    private String descr;

    private List<Permission> permissions;

}
