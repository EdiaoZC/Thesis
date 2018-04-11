package com.thesis.controllerTest;

import com.thesis.SpringTest;
import com.thesis.SpringWebTest;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @Author: ZcEdiaos
 * @Date: 2018/4/9 15:52
 * @Description:
 */
public class UserControllerTest extends SpringWebTest {

    @Test
    public void loginTest() throws Exception {
        mockMvc.perform(post("/user/login")
                .param("username","Ediaos")
                .param("password","As110695")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
    }

}
