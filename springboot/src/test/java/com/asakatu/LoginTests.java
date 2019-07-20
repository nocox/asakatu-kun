package com.asakatu;

import com.asakatu.entity.User;
import com.asakatu.service.UserRegistrationService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class LoginTests {

    @Autowired
    private UserRegistrationService userRegistrationService;

    @Autowired
    private MockMvc mockMvc;


    private User createTestUser() {
        User testUser = new User();
        testUser.setUsername("hogehoge");
        testUser.setPassword("takatakataka");
        testUser.setPasswordConfirm("takatakataka");
        testUser.setEmail("hogehoge@aaa.com");
        testUser.setDisplayName("nocox");
        return testUser;
    }


    @Test
    public void login() throws Exception {
        User testUser = createTestUser();
        testUser.setUsername("loginTester");
        userRegistrationService.save(testUser);

        this.mockMvc.perform(
                post("/login")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("username", testUser.getUsername())
                        .param("password", createTestUser().getPassword())) // エンコード前のパスワードを代入
                .andDo(print()).andExpect(status().isOk());
    }


    @Test
    public void loginFailure() throws Exception{
        User testUser = createTestUser();
        testUser.setUsername("loginFailureTester");
        userRegistrationService.save(testUser);

        this.mockMvc.perform(
                post("/login")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("username", "tekitou")
                        .param("password", createTestUser().getPassword()))
                .andDo(print()).andExpect(status().is4xxClientError());
    }
}