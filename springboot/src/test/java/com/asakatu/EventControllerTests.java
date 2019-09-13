package com.asakatu;

import com.asakatu.entity.Event;
import com.asakatu.property.FromFrontEventProperties;
import com.asakatu.response.ForFrontEvent;
import com.asakatu.response.GetEventsListResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.asakatu.entity.UserStatus;
import com.asakatu.repository.EventRepository;
import org.hamcrest.Matcher;
import org.hamcrest.core.Is;
import org.junit.Assert;
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

import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
		MvcResult mvcResult = mvc.perform(get(uri)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andDo(print()).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();

		TOkResponse<GetEventsListResponse> contentObj = mapper.readValue(content, new TypeReference<TOkResponse<GetEventsListResponse>>(){});
		List<ForFrontEvent   > eventsList = contentObj.getData().getEventsList();
		Assert.assertThat(eventsList.size(), greaterThan(0));


		Assert.assertThat(eventsList.get(0).getEvent().getAddress(), Is.is("東京都渋谷区1-2-3"));
		Assert.assertThat(eventsList.get(0).getEvent().getEventTitle(), Is.is("第1回19新卒朝活"));
		Assert.assertThat(eventsList.get(1).getEvent().getAddress(), Is.is("東京都渋谷区2-2-3"));
		Assert.assertThat(eventsList.get(1).getEvent().getEventTitle(), Is.is("第2回19新卒朝活"));
		Assert.assertThat(eventsList.get(2).getEvent().getAddress(), Is.is("東京都渋谷区3-2-3"));
		Assert.assertThat(eventsList.get(2).getEvent().getEventTitle(), Is.is("第3回19新卒朝活"));
		Assert.assertThat(eventsList.get(3).getEvent().getAddress(), Is.is("東京都渋谷区4-2-3"));
		Assert.assertThat(eventsList.get(3).getEvent().getEventTitle(), Is.is("第4回19新卒朝活"));
		Assert.assertThat(eventsList.get(4).getEvent().getAddress(), Is.is("東京都渋谷区5-2-3"));
        Assert.assertThat(eventsList.get(4).getEvent().getEventTitle(), Is.is("第5回19新卒朝活"));
    }

	private UserStatus joinUserStatus() {
		UserStatus userStatus = new UserStatus();
		userStatus.setComment("comment");
		return userStatus;
	}

    @Test
    @WithMockUser(username = "doiiii")
    public void joinEventComment() throws Exception {
        this.mockMvc.perform(
                post("/event/2/user")
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsBytes(joinUserStatus())))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.data.comment").value("comment"))
                .andExpect(jsonPath("$.data.message").value("created"));
    }

	private FromFrontEventProperties getTestEventProperties() {
		FromFrontEventProperties eventProperties = new FromFrontEventProperties();
		eventProperties.setEventTitle("作成テストイベント");
		eventProperties.setAddress("テスト街テスト市1-2-3");
		eventProperties.setSeatInfo("席情報");
		eventProperties.setStartDate(LocalDateTime.of(2019, 10, 31, 9, 0));
		eventProperties.setEndDate(LocalDateTime.of(2019, 10, 31, 12, 0));
		return eventProperties;
	}

	@Test
	@WithMockUser(username = "doiiii")
	public void createEvent() throws Exception {
		this.mockMvc.perform(
				post("/event/new")
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsBytes(getTestEventProperties())))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$.data.event.eventTitle").value("作成テストイベント"))
				.andExpect(jsonPath("$.data.event.address").value("テスト街テスト市1-2-3"))
				.andExpect(jsonPath("$.data.event.seatInfo").value("席情報"))
				.andExpect(jsonPath("$.data.event.eventStatus").value("yet"))
				// 時刻はUTCなので9時間マイナスになる。取得の時直す方針
				.andExpect(jsonPath("$.data.event.startDate").value("2019-10-31T09:00:00"))
				.andExpect(jsonPath("$.data.event.duration").value("3.0"))
				.andExpect(jsonPath("$.data.message").value("success"));

		// 後片付け
		Event event = eventRepository.findEventByEventTitle("作成テストイベント");
		eventRepository.delete(event);
	}

    @Test
    @WithMockUser(username = "doiiii")
    public void joinEventUsers() throws Exception {
        this.mockMvc.perform(
                get("/event/1/users")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "doiiii")
    public void joinEventUserListSize() throws Exception {
        this.mockMvc.perform(
                get("/event/1/users")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.data.message").value("success"))
                .andExpect(jsonPath("$.data.userList",hasSize(4)));
	}

    @Test
    @WithMockUser(username = "doiiii")
    public void joinEventUserListName() throws Exception {
        this.mockMvc.perform(
                get("/event/1/users")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.data.message").value("success"))
                .andExpect(jsonPath("$.data.userList",hasSize(4)))
                .andExpect(jsonPath("$.data.userList[0].id").value(1))
                .andExpect(jsonPath("$.data.userList[0].displayName").value("きしー"))
                .andExpect(jsonPath("$.data.userList[0].imagePath").value("/images/profile/2u1e0h30x38.jpg"));
    }
}


class TOkResponse<T>{
	private T data;

	public T getData() {
		return data;
	}
}