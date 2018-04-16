package com.thesis.common.model.Vo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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

    private String username;

    private String nickname;

    private String token;


}
