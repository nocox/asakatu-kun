package com.asakatu.controller;

import com.asakatu.OkResponse;
import com.asakatu.entity.User;
import com.asakatu.entity.UserStatus;
import com.asakatu.repository.UserRepository;
import com.asakatu.repository.UserStatusRepository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserStatusController {
	private final UserRepository userRepository;

	private final UserStatusRepository userStatusRepository;

	public UserStatusController(UserRepository userRepository, UserStatusRepository userStatusRepository) {
		this.userRepository = userRepository;
		this.userStatusRepository = userStatusRepository;
	}
	@RequestMapping("/event/{eventId}/user/{userId}")
	public OkResponse getUserStatus(@PathVariable Long eventId, @PathVariable Long userId) {
		User user = userRepository.findById(userId).get();
		UserStatus userStatus = userStatusRepository.findByEventIdAndUserId(eventId, userId).get();
		return new OkResponse(new UserStatusResponse(
				"success",
				userStatus.getId(),
				user.getUsername(),
				user.getDisplayName(),
				user.getImagePath(),
				userStatus.getUserStatusComment()
		));
	}
}

class UserStatusResponse {
	private String message;
	private Long statusId;
	private String userName;
	private String displayName;
	private String imagePath;
	private String comment;

	UserStatusResponse(String message, Long statusId, String userName, String displayName, String imagePath, String comment) {
		this.message = message;
		this.statusId = statusId;
		this.userName = userName;
		this.displayName = displayName;
		this.imagePath = imagePath;
		this.comment = comment;
	}

	public String getMessage() {
		return message;
	}

	public Long getStatusId() {
		return statusId;
	}

	public String getUserName() {
		return userName;
	}

	public String getDisplayName() {
		return displayName;
	}

	public String getImagePath() {
		return imagePath;
	}

	public String getComment() {
		return comment;
	}
}