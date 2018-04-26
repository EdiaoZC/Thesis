package com.thesis.common.security;

import com.thesis.common.holder.SaltHolder;
import com.thesis.common.util.Md5Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @Author: ZcEdiaos
 * @Date: 2018/4/8 15:53
 * @Description:
 */
@Slf4j
@Component
public class Md5PassEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence rawPassword) {
        String salt = Md5Util.gensalt(6);
        log.debug("盐时多少{}", salt);
        SaltHolder.setSalt(salt);
        return Md5Util.getMD5AndSalt(rawPassword.toString(), salt);
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        if (encodedPassword == null || encodedPassword.length() == 0) {
            log.error("Empty encoded password");
            return false;
        }
        String password = Md5Util.getMD5AndSalt(rawPassword.toString(), SaltHolder.getSalt());
        SaltHolder.remove();
        log.debug(password + "========" + encodedPassword);
        return password.equals(encodedPassword);
    }
}
