package com.thesis.common.model.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @Author: ZcEdiaos
 * @Date: 2018/5/28 14:52
 * @Description:
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class UserRoleDto {

    private Long userId;

    private List<Byte> roleIds;
}
