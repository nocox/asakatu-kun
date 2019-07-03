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
	public List<Event> getEventsList() {
		return eventRepository.findAll();
	}
}