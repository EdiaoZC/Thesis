package com.thesis.common.model.form;

import com.thesis.common.model.RunningParam;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @Author: ZcEdiaos
 * @Date: 2018/5/1 19:59
 * @Description:
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class RequestForm {

    private String token;

    private List<RunningParam> run;
}
