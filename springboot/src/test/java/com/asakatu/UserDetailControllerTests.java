package com.asakatu;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserDetailControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Test
    @WithMockUser
    public void testGetUserDetail() throws Exception {
        this.mockMvc.perform(get("/user/1"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.data.username").value("kishiiii"))
                .andExpect(jsonPath("$.data.email").value("kishida@bizreach.co.jp"))
                .andExpect(jsonPath("$.data.myself").value(false));

    }

    @Test
    public void testUnauthorizedGetUserDetail() throws Exception {
        this.mockMvc.perform(get("/user/1"))
                .andDo(print()).andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(username = "doiiii")
    public void testGetUserDetailAtMyself() throws Exception {
        this.mockMvc.perform(get("/user/2"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.data.username").value("doiiii"))
                .andExpect(jsonPath("$.data.email").value("doi@bizreach.co.jp"))
                .andExpect(jsonPath("$.data.myself").value(true));
    }

    @Test
    @WithMockUser(username = "doiiii")
    public void testModifyDisplayName() throws Exception{
        this.mockMvc.perform(get("/user/2"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.data.displayName").value("ツッチー"))
                .andExpect(jsonPath("$.data.myself").value(true));

        this.mockMvc.perform(
                put("/user/edit/display_name")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("displayName", "つっちー")
        ).andDo(print()).andExpect(status().isOk());

        this.mockMvc.perform(get("/user/2"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.data.displayName").value("つっちー"))
                .andExpect(jsonPath("$.data.myself").value(true));
    }

    @Test
    public void testUnauthorizedModifyDisplayName() throws Exception {
        this.mockMvc.perform(
                put("/user/edit/display_name")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("displayName", "つっちー")
        ).andDo(print()).andExpect(status().isUnauthorized());
    }
}