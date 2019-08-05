package com.asakatu.controller;

import com.asakatu.entity.User;
import com.asakatu.entity.UserStatus;
import com.asakatu.repository.UserRepository;
import com.asakatu.repository.UserStatusRepository;
import com.asakatu.response.ForFrontEvent;
import com.asakatu.response.GetEventsListResponse;
import com.asakatu.service.PostService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import com.asakatu.OkResponse;
import com.asakatu.entity.Event;
import com.asakatu.repository.EventRepository;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class EventController {
	private final EventRepository eventRepository;

    private final UserStatusRepository userStatusRepository;

    private final UserRepository userRepository;

    private final PostService postService;

	public EventController(EventRepository eventRepository, UserStatusRepository userStatusRepository, UserRepository userRepository, PostService postService) {
		this.eventRepository = eventRepository;
		this.userStatusRepository = userStatusRepository;
		this.userRepository = userRepository;
		this.postService = postService;
	}

	@RequestMapping("/events")
	public OkResponse getEventsList() {
		List<Event> entityEventsList = eventRepository.findAll();
		List<ForFrontEvent> noSortedEventsList = new ArrayList<>();
        for (Event event : entityEventsList) {
            ForFrontEvent forFrontEvent = new ForFrontEvent();
            forFrontEvent.setDesignDate(postService.getDesignDate(event.getStartDate(), event.getDuration()));
            forFrontEvent.setEvent(event);
            noSortedEventsList.add(forFrontEvent);
        }

        List<ForFrontEvent> eventsList = noSortedEventsList.stream()
                .sorted(Comparator.comparing(event -> event.getEvent().getStartDate()))
                .collect(Collectors.toList());

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

    @RequestMapping("/event/{id}/users")
    public OkResponse getEventJoinedUsersList(@PathVariable long id) {
        Event event = eventRepository.findById(id).orElseThrow(IllegalStateException::new);
        List<User> eventJoinedUsersList = userRepository.findUsersByEventsListIn(event);

        List<JoinedUserInfo> result = new ArrayList<>();
        JoinedUserInfo user;
        // 全部だとパスワードとかも入っちゃうので厳選
        for (User jounedUser : eventJoinedUsersList) {
            user = new JoinedUserInfo();
            user.setDisplayName(jounedUser.getDisplayName());
            user.setId(jounedUser.getId());
            user.setImagePath(jounedUser.getImagePath());
            String comment = userStatusRepository.findUserStatusByEventAndUserIs(event, jounedUser).getComment();
            user.setComment(comment);
            result.add(user);
        }
        return new OkResponse(new EventJoinedResponse("success", result));
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

class JoinedUserInfo {
    private Long id;

    private String displayName;

    private String imagePath;

    private String comment;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}

class EventJoinedResponse {
    private String message;
    private List<JoinedUserInfo> userList;

    EventJoinedResponse(String message, List<JoinedUserInfo> userList) {
        this.message = message;
        this.userList = userList;
    }

    public String getMessage() {
        return message;
    }

    public List<JoinedUserInfo> getUserList() {
        return userList;
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