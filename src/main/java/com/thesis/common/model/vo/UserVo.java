package com.thesis.common.model.vo;

import com.thesis.common.model.Role;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @Author: ZcEdiaos
 * @Date: 2018/4/16 16:35
 * @Description:
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class UserVo {

    private Long id;

    private String username;

    private String nickname;

    private String token;

    private String sex;

    private Date createTime;

    private List<String> status;

    private Set<Role> roles;

    private String comment;
}
