package hello.controller;

import hello.entity.Event;
import hello.entity.SimpleLoginUser;
import hello.entity.User;
import hello.entity.UserStatus;
import hello.repository.EventRepository;
import hello.repository.UserRepository;
import hello.repository.UserStatusRepository;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.web.bind.annotation.*;

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
	public List<Event> getEventsList() {
		return eventRepository.findAll();
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
            result.add(user);
        }
        return new OkResponse(new EventJoinedResponse("success", result));
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

        return new CreatedResponse(new EventResponse("created", userStatus.getComment()));
    }
}

class EventResponse {
    private String message;
    private String comment;

    EventResponse(String message, String comment) {
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

class JoinedUserInfo {
    private Long id;

    private String displayName;

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
}