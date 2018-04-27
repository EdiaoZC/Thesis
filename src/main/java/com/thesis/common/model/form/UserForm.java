package com.thesis.common.model.form;

import com.thesis.common.model.SexEnum;
import lombok.*;

import javax.validation.constraints.Pattern;
import java.util.List;

/**
 * @Author: ZcEdiaos
 * @Date: 2018/4/26 16:19
 * @Description:
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class UserForm {

    private Long id;

    @NonNull
    @Pattern(regexp = "[A-Z][a-zA-Z0-9]{7,15}", message = "首字母必须大写，长度8-16位")
    private String username;

    @NonNull
    @Pattern(regexp = "\\w{2,5}", message = "长度2-5位")
    private String nickname;

    @Pattern(regexp = "\\w{8,16}", message = "长度8-16位")
    private String password;

    @Pattern(regexp = "\\w{8,16}", message = "长度8-16位")
    private String confirmPassword;

    private SexEnum sex;

    private List<Byte> status;
}
