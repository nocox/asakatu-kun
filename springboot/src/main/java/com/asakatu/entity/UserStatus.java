package com.asakatu.entity;

import javax.persistence.*;

@Entity
@Table(name = "user_status")
public class UserStatus {

	@Id
	@Column(name = "user_status_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "user_status_master_id")
	private Long MasterId;

	@Column(name = "user_status_comment")
	private String userStatusComment;

	@Column(name = "user_id")
	private Long userId;

	@Column(name = "event_id")
	private Long eventId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserStatusComment() {
		return userStatusComment;
	}

	public void setUserStatusComment(String userStatusComment) {
		this.userStatusComment = userStatusComment;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getEventId() {
		return eventId;
	}

	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}

	public Long getMasterId() {
		return MasterId;
	}

	public void setMasterId(Long masterId) {
		MasterId = masterId;
	}
}
