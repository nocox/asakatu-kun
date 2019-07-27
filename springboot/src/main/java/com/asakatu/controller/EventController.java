package com.asakatu.controller;

import com.asakatu.entity.SimpleLoginUser;
import com.asakatu.entity.User;
import com.asakatu.entity.UserStatus;
import com.asakatu.repository.UserRepository;
import com.asakatu.repository.UserStatusRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import com.asakatu.OkResponse;
import com.asakatu.entity.Event;
import com.asakatu.repository.EventRepository;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@RestController
public class EventController {
	private final EventRepository eventRepository;

    private final UserStatusRepository userStatusRepository;

    private final UserRepository userRepository;

	public EventController(EventRepository eventRepository, UserStatusRepository userStatusRepository, UserRepository userRepository) {
		this.eventRepository = eventRepository;
		this.userStatusRepository = userStatusRepository;
		this.userRepository = userRepository;
	}

	@RequestMapping("/events")
	public OkResponse getEventsList() {
        List<SimpleEvent> result = new ArrayList<>();
		List<Event> eventsList = eventRepository.findAll();
        for (Event event : eventsList) {
            SimpleEvent simpleEvent = new SimpleEvent();
            simpleEvent.setId(event.getId());
            simpleEvent.setStartDate(event.getStartDate());
            simpleEvent.setDuration(event.getDuration());
            simpleEvent.setAddress(event.getAddress());
            simpleEvent.setSeatInfo(event.getSeatInfo());
            simpleEvent.setEventStatus(event.getEventStatus());
            simpleEvent.setCreatedAt(event.getCreatedAt());
            simpleEvent.setUpdatedAt(event.getUpdatedAt());
            result.add(simpleEvent);
        }
		return new OkResponse(new GetEventsListResponse("success", result));
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
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findUsersByUsername(authentication.getName()).get(0);

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

class SimpleEvent {
    private Long id;

    private Timestamp startDate;

    private double duration;

    private String address;

    private String seatInfo;

    private String eventStatus;

    private Timestamp createdAt;

    private Timestamp updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSeatInfo() {
        return seatInfo;
    }

    public void setSeatInfo(String seatInfo) {
        this.seatInfo = seatInfo;
    }

    public String getEventStatus() {
        return eventStatus;
    }

    public void setEventStatus(String eventStatus) {
        this.eventStatus = eventStatus;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }
}

class GetEventsListResponse {
	private String message;
	private List<SimpleEvent> eventsList;

	GetEventsListResponse(String message, List<SimpleEvent> eventsList) {
		this.message = message;
		this.eventsList = eventsList;
	}

	public String getMessage() {
		return message;
	}

	public List<SimpleEvent> getEventsList() {
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