package hello;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.entity.User;
import hello.repository.UserRepository;
import org.apache.catalina.mapper.Mapper;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

    @Test
    public void userRegistration() throws Exception {
        User testUser = new User();
        testUser.setUsername("hogehoge");
        testUser.setPassword("takatakataka");
        testUser.setPasswordConfirm("takatakataka");
        testUser.setEmail("doi@aaa.com");
        testUser.setDisplay_name("nocox");

        this.mockMvc.perform(
                post("/registration")
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsBytes(testUser))
        ).andDo(print()).andExpect(status().isOk());

        Optional<User> findUser = userRepository.findByUsername("hogehoge");
        assert findUser.isPresent();

        Assert.assertThat(findUser.get().getUsername() , Is.is(testUser.getUsername()));
        Assert.assertThat(bCryptPasswordEncoder.matches(testUser.getPassword(), findUser.get().getPassword()), Is.is(true));
        Assert.assertThat(findUser.get().getEmail() , Is.is(testUser.getEmail()));
        Assert.assertThat(findUser.get().getDisplay_name() , Is.is(testUser.getDisplay_name()));
    }


}
