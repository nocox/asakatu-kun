package com.asakatu;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.asakatu.entity.UserStatus;
import com.asakatu.repository.EventRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EventControllerTests extends AbstractTest{

	@Autowired
	EventRepository eventRepository;

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper mapper;


	@Override
	@Before
	public void setUp() {
		super.setUp();
	}

	@Test
    @WithMockUser
    public void getEventsList() throws Exception {
		String uri = "/events";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
//		String content = mvcResult.getResponse().getContentAsString();
//		Event[] eventList = super.mapFromJson(content, Event[].class);
//		assertTrue(eventList.length > 0);
	}

	private UserStatus joinUserStatus() {
		UserStatus userStatus = new UserStatus();
		userStatus.setComment("comment");
		return userStatus;
	}

	@Test
    @WithMockUser(username = "doiiii")
	public void joinEvent() throws Exception {
		this.mockMvc.perform(
				post("/event/1/user")
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsBytes(joinUserStatus())))
				.andDo(print()).andExpect(status().isOk());
	}

    @Test
    @WithMockUser(username = "doiiii")
    public void joinEventComment() throws Exception {
        this.mockMvc.perform(
                post("/event/1/user")
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsBytes(joinUserStatus())))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.data.comment").value("comment"))
                .andExpect(jsonPath("$.data.message").value("created"));
    }
}