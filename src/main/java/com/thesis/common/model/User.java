package com.thesis.common.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class User {
    private Long id;

    private String username;

    private String password;

    private String salt;

    private String nickname;

    private SexEnum sex;

    private Byte status;

    private String comment;

    private Date createTime;

    private Date updateTime;
}