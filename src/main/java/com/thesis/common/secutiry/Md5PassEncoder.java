package com.thesis.common.secutiry;

import com.thesis.common.holder.SaltHolder;
import com.thesis.common.util.Md5Util;
import com.thesis.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @Author: ZcEdiaos
 * @Date: 2018/4/8 15:53
 * @Description:
 */
@Slf4j
public class Md5PassEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence rawPassword) {
        String salt = Md5Util.gensalt(6);
        log.info("盐时多少{}", salt);
        SaltHolder.setSalt(salt);
        return Md5Util.getMD5AndSalt(rawPassword.toString(), salt);
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        if (encodedPassword == null || encodedPassword.length() == 0) {
            log.warn("Empty encoded password");
            return false;
        }
        log.info(SaltHolder.getSalt());
        log.info(rawPassword.toString());
        String password = Md5Util.getMD5AndSalt(rawPassword.toString(), SaltHolder.getSalt());
        SaltHolder.remove();
        log.info(password + "========" + encodedPassword);
        return password.equals(encodedPassword);
    }
}
