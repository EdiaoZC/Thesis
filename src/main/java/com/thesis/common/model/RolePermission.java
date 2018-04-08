package com.thesis.common.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RolePermission {
    private Integer id;

    private Integer roleId;

    private Integer permissionId;

}