package com.thesis.dao.mapper;

import com.thesis.SpringTest;
import com.thesis.common.model.UserRole;
import com.thesis.common.model.dto.UserRoleDto;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author: ZcEdiaos
 * @Date: 2018/5/28 19:05
 * @Description:
 */
public class UserRoleMapperTest extends SpringTest {

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Test
    public void batchInsert() {
        UserRoleDto userRoleDto = new UserRoleDto();
        userRoleDto.setUserId(5L);
        final List<Byte> roles = Arrays.asList((byte) 1, (byte) 3, (byte) 5);
        userRoleDto.setRoleIds(roles);
        userRoleMapper.batchInsert(userRoleDto);
    }

}