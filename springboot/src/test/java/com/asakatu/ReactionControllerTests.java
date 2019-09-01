package com.asakatu;

import com.asakatu.entity.UserStatusMaster;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ReactionControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Test
    @WithMockUser(username = "doiiii")
    public void getReactionList() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(get("/reaction/list"))
                .andDo(print()).andExpect(status().isOk()).andReturn();
        List<UserStatusMaster> contentObj = mapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<List<UserStatusMaster>>() {
        });
        Assert.assertThat(contentObj.size(), Is.is(4));

        Assert.assertThat(contentObj.get(0).getUserStatusContent(), Is.is("far fa-meh"));
        Assert.assertThat(contentObj.get(3).getUserStatusContent(), Is.is("far fa-dizzy"));
    }

    @Test
    @WithMockUser(username = "doiiii")
    public void getMyReaction() throws Exception {
        this.mockMvc.perform(get("/reaction/myself/1"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.userStatusContent").value("far fa-grin-squint-tears"));
    }

    @Test
    @WithMockUser(username = "doiiii")
    public void changeMyReaction() throws Exception {
        this.mockMvc.perform(get("/reaction/myself/1"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.userStatusContent").value("far fa-grin-squint-tears"));

        this.mockMvc.perform(
                put("/reaction/change/1/4")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.userStatusContent").value("far fa-dizzy"));

        this.mockMvc.perform(get("/reaction/myself/1"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.userStatusContent").value("far fa-dizzy"));
    }
}
