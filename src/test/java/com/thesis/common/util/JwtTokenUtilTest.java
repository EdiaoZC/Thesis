package com.thesis.common.util;

import com.thesis.SpringTest;
import net.bytebuddy.asm.Advice;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * @Author: ZcEdiaos
 * @Date: 2018/4/15 16:04
 * @Description:
 */
public class JwtTokenUtilTest extends SpringTest {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Test
    public void JwtTokenUtilTest() {
    }
}