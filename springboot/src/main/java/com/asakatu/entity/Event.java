package com.asakatu.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Entity
@Table(name = "event")
public class Event {
	@Id
	@Column(name = "event_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "event_title")
	private String eventTitle;

	@Column(name = "event_detail")
	private String eventDetail;

	@Column(name = "start_date")
	private LocalDateTime startDate;

	@Column(name = "duration")
	private double duration;

	@Column(name = "address")
	private String address;

	@Column(name = "seat_info")
	private String seatInfo;

	@Column(name = "event_status")
	private String eventStatus;

	@Column(name = "created_at")
	private Timestamp createdAt;

	@Column(name = "updated_at")
	private Timestamp updatedAt;

	@ManyToMany
	@JoinTable(name = "user_event_association",
			joinColumns = @JoinColumn(name = "event_id"),
			inverseJoinColumns = @JoinColumn(name = "user_id"))
	private List<User> userList;

	@OneToMany(mappedBy = "event")
    private List<UserStatus> userStatusList;
	@PrePersist
	public void onPrePersist() {
		setCreatedAt(new Timestamp(System.currentTimeMillis()));
		setUpdatedAt(new Timestamp(System.currentTimeMillis()));
	}

	@PreUpdate
	public void onPreUpdate() {
		setUpdatedAt(new Timestamp(System.currentTimeMillis()));
	}

	public Long getId() {
		return id;
	}

	public String getEventTitle() {
		return eventTitle;
	}

	public void setEventTitle(String eventTitle) {
		this.eventTitle = eventTitle;
	}

	public String getEventDetail() {
		return eventDetail;
	}

	public void setEventDetail(String eventDetail) {
		this.eventDetail = eventDetail;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getStartDate() {
		return startDate.atZone(ZoneId.of("Asia/Tokyo")).toLocalDateTime();
	}

	public void setStartDate(LocalDateTime startDate) {
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

//    public List<User> getUserList() {
//        return userList;
//    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public List<UserStatus> getUserStatusList() {
        return userStatusList;
    }

    public void setUserStatusList(List<UserStatus> userStatusList) {
        this.userStatusList = userStatusList;
    }
}