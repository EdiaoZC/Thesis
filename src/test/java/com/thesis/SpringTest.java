package com.thesis;

import com.thesis.common.util.Md5Util;
import com.thesis.config.RootConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: ZcEdiaos
 * @Date: 2018/4/8 17:28
 * @Description:
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {RootConfig.class})
public class SpringTest {

    @Test
    public void genPassword() {
        System.out.println(Md5Util.getMD5AndSalt("As110695","qweddd"));
    }
}
