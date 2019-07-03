package hello;

import hello.entity.Event;
import hello.repository.EventRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EventControllerTests {

	@Autowired
	EventRepository eventRepository;

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Test
	public void testFinaAllEvent() {
		List<Event> eventList = eventRepository.findAll();

		String sql = "SELECT * FROM event";
		List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);

		for (int i = 0; i < eventList.size(); i++) {

			Long eventId = ((long) maps.get(i).get("event_id"));
			Timestamp startDate = ((Timestamp) maps.get(i).get("start_date"));
			Double duration = ((Double) maps.get(i).get("duration"));
			String address = ((String) maps.get(i).get("address"));
			String seatInfo = ((String) maps.get(i).get("seat_info"));
			String eventStatus = ((String) maps.get(i).get("event_status"));

			assertThat(eventList.get(i).getId(), is(eventId));
			assertThat(eventList.get(i).getStartDate(), is(startDate));
			assertThat(eventList.get(i).getDuration(), is(duration));
			assertThat(eventList.get(i).getAddress(), is(address));
			assertThat(eventList.get(i).getSeatInfo(), is(seatInfo));
			assertThat(eventList.get(i).getEventStatus(), is(eventStatus));
		}
	}
}
