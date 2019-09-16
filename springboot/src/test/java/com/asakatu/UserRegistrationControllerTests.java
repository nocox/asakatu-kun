package com.asakatu;

import com.asakatu.entity.USER_REGISTRATION_ERROR;
import com.asakatu.entity.User;
import com.asakatu.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserRegistrationControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private User createTestUser() {
        User testUser = new User();
        testUser.setUsername("hogehoge");
        testUser.setPassword("takatakataka");
        testUser.setPasswordConfirm("takatakataka");
        testUser.setEmail("doi@aaa.com");
        testUser.setDisplayName("nocox");
        return testUser;
    }

    @Test
    public void userRegistration() throws Exception {
        User testUser = createTestUser();
        testUser.setUsername("registrationTester");
        this.mockMvc.perform(
                post("/user_registration")
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsBytes(testUser)))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.data.username").value(testUser.getUsername()));

        Optional<User> findUser = userRepository.findByUsername(testUser.getUsername());
        assert findUser.isPresent();

        Assert.assertThat(findUser.get().getUsername(), Is.is(testUser.getUsername()));
        Assert.assertThat(bCryptPasswordEncoder.matches(testUser.getPassword(), findUser.get().getPassword()), Is.is(true));
        Assert.assertThat(findUser.get().getEmail(), Is.is(testUser.getEmail()));
        Assert.assertThat(findUser.get().getDisplayName(), Is.is(testUser.getDisplayName()));
    }

    @Test
    public void shortUserNameException() throws Exception {

        User testUser = createTestUser();
        testUser.setUsername("shortNameUser");
        testUser.setUsername("aa");

        this.mockMvc.perform(
                post("/user_registration")
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsBytes(testUser)))
                .andDo(print()).andExpect(status().is5xxServerError())
                .andExpect(jsonPath("$.data.message").value(USER_REGISTRATION_ERROR.USER_NAME_LENGTH_ERROR));
        ;
    }

    @Test
    public void longUserNameException() throws Exception {

        User testUser = createTestUser();
        testUser.setUsername("longNameUser");
        testUser.setUsername("abababababababababababababababababababababababababa");

        this.mockMvc.perform(
                post("/user_registration")
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsBytes(testUser)))
                .andDo(print()).andExpect(status().is5xxServerError())
                .andExpect(jsonPath("$.data.message").value(USER_REGISTRATION_ERROR.USER_NAME_LENGTH_ERROR));
    }

    @Test
    public void userAlreadyExistException() throws Exception {

        User testUser = createTestUser();
        testUser.setUsername("alreadyUser");

        this.mockMvc.perform(
                post("/user_registration")
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsBytes(testUser)))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.data.username").value("alreadyUser"));

        ;


        this.mockMvc.perform(
                post("/user_registration")
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsBytes(testUser)))
                .andDo(print()).andExpect(status().is5xxServerError())
                .andExpect(jsonPath("$.data.message").value(USER_REGISTRATION_ERROR.USER_NAME_ALREADY_USED));

    }

    @Test
    public void shortPasswordException() throws Exception {

        User testUser = createTestUser();
        testUser.setUsername("shortPasswordUser");
        testUser.setPassword("aabbc");

        this.mockMvc.perform(
                post("/user_registration")
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsBytes(testUser)))
                .andDo(print()).andExpect(status().is5xxServerError())
                .andExpect(jsonPath("$.data.message").value(USER_REGISTRATION_ERROR.PASSWORD_LENGTH_ERROR));

    }

    @Test
    public void longPasswordException() throws Exception {

        User testUser = createTestUser();
        testUser.setUsername("longPasswordUser");
        testUser.setPassword("aaaaaaaaabbbbbbbbbbbcc");

        this.mockMvc.perform(
                post("/user_registration")
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsBytes(testUser)))
                .andDo(print()).andExpect(status().is5xxServerError())
                .andExpect(jsonPath("$.data.message").value(USER_REGISTRATION_ERROR.PASSWORD_LENGTH_ERROR));

    }


    @Test
    public void passwordNotMatch() throws Exception {

        User testUser = createTestUser();
        testUser.setUsername("passwordNotMatchUser");
        testUser.setPasswordConfirm("aaaaaaaaa");

        this.mockMvc.perform(
                post("/user_registration")
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsBytes(testUser)))
                .andDo(print()).andExpect(status().is5xxServerError())
                .andExpect(jsonPath("$.data.message").value(USER_REGISTRATION_ERROR.INCORRECT_PASSWORD));

    }

}
