package hello.controller;

import hello.entity.Event;
import hello.repository.EventRepository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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

	@RequestMapping("/event/new")
	public OkResponse createEvent(@RequestBody Event event) {
		eventRepository.save(event);
		return new OkResponse(new EventResponse("success", event));
	}

	@RequestMapping("/event/{eventId}/cancel")
	public OkResponse cancelEvent(@PathVariable Long eventId) {
		Event cancelEvent = eventRepository.findById(eventId).get();
		cancelEvent.setEventStatus("canceled");
		eventRepository.save(cancelEvent);
		return new OkResponse(new EventResponse("success", cancelEvent));
	}

	@RequestMapping("/event/{eventId}/edit")
	public OkResponse updateEvent(@RequestBody Event event, @PathVariable Long eventId) {
		Event updateEvent = eventRepository.findById(eventId).get();
		updateEvent.setStartDate(event.getStartDate());
		updateEvent.setDuration(event.getDuration());
		updateEvent.setAddress(event.getAddress());
		updateEvent.setSeatInfo(event.getSeatInfo());
		updateEvent.setEventStatus(event.getEventStatus());
		eventRepository.save(updateEvent);
		return new OkResponse(new EventResponse("success", event));
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

class EventResponse {
	private String message;
	private Event event;

	EventResponse(String message, Event event) {
		this.message = message;
		this.event = event;
	}

	public String getMessage() {
		return message;
	}

	public Event getEvent() {
		return event;
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