package hello.controller;

import hello.entity.Event;
import hello.repository.EventRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EventController {
	private final EventRepository eventRepository;

	public EventController(EventRepository eventRepository) {
		this.eventRepository = eventRepository;
	}

	@RequestMapping("/events")
	public OkResponse getEventsList() {
		List<Event> eventsList = eventRepository.findAll();
		return new OkResponse(new GetEventsListResponse("success", eventsList));
	}
}

class GetEventsListResponse {
	private String message;
	private List<Event> eventsList;

	GetEventsListResponse(String message, List<Event> eventsList) {
		this.message = message;
		this.eventsList = eventsList;
	}

	public String getMessage() {
		return message;
	}

	public List<Event> getEventsList() {
		return eventsList;
	}
}

class OkResponse {
	private Object data;

	public OkResponse(Object data) {
		this.data = data;
	}

	public Integer getStatus() { return 200; }

	public Object getData() {
		return data;
	}
}