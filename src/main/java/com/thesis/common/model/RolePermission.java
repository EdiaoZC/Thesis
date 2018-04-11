package com.thesis.common.model;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class RolePermission {
    private Integer id;

    private Integer roleId;

    private Integer permissionId;

}