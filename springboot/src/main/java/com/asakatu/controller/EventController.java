package com.asakatu.controller;

import com.asakatu.entity.SimpleLoginUser;
import com.asakatu.entity.User;
import com.asakatu.entity.UserStatus;
import com.asakatu.repository.UserStatusRepository;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.web.bind.annotation.*;
import com.asakatu.OkResponse;
import com.asakatu.entity.Event;
import com.asakatu.repository.EventRepository;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.List;

@RestController
public class EventController {
	private final EventRepository eventRepository;

    private final UserStatusRepository userStatusRepository;

	public EventController(EventRepository eventRepository, UserStatusRepository userStatusRepository) {
		this.eventRepository = eventRepository;
		this.userStatusRepository = userStatusRepository;
	}

	@RequestMapping("/events")
	public OkResponse getEventsList() {
		List<Event> eventsList = eventRepository.findAll();
		return new OkResponse(new GetEventsListResponse("success", eventsList));
	}

	@RequestMapping("/event/{eventId}")
	public OkResponse getEvent(@PathVariable Long eventId) {
		Event event = eventRepository.findById(eventId).get();
		return new OkResponse(new EventResponse("success", event));
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

    @PostMapping("/event/{id}/user")
    public CreatedResponse getJoinedUser(@PathVariable long id, @RequestBody UserStatus request, HttpSession session) {
        // ログインユーザの取得
        SecurityContext sessionContext = (SecurityContext) session.getAttribute("SPRING_SECURITY_CONTEXT");
        SimpleLoginUser loginUser = (SimpleLoginUser) sessionContext.getAuthentication().getPrincipal();
        User user = loginUser.getUser();

        // イベントの取得,設定
        Event event = eventRepository.findById(id).orElseThrow(IllegalStateException::new);
        event.getUserList().add(user);
        eventRepository.save(event);

        // ユーザステータスの設定
        UserStatus userStatus = new UserStatus();
        userStatus.setComment(request.getComment());
        userStatus.setEvent(event);
        userStatus.setUser(user);
        userStatus.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        userStatus.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        userStatusRepository.save(userStatus);

        return new CreatedResponse(new EventOnlyCommentResponse("created", userStatus.getComment()));
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

class EventOnlyCommentResponse {
    private String message;
    private String comment;

    EventOnlyCommentResponse(String message, String comment) {
        this.message = message;
        this.comment = comment;
    }

    public String getMessage() {
        return message;
    }

    public String getComment() {
        return comment;
    }

}


class CreatedResponse {
	private Object data;

	public CreatedResponse(Object data) {
		this.data = data;
	}

	public Integer getStatus() {
		return 201;
	}

	public Object getData() {
		return data;
	}
}