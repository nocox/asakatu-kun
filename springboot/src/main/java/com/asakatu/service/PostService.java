package com.asakatu.service;

import com.asakatu.controller.JoinedUserInfo;
import com.asakatu.entity.Event;
import com.asakatu.entity.User;
import com.asakatu.repository.UserRepository;
import com.asakatu.response.ForFrontEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class PostService{

    private final UserRepository userRepository;

    public PostService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String getDesignDate(LocalDateTime startDate, double duration) {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("[]H:mm");

        LocalDateTime endLocalDate = startDate.plusHours((long) duration);
        String dayOfWeek = startDate.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.JAPANESE);
        return dayOfWeek + " "
                + dateFormat.format(startDate)
                + " 〜 "
                + dateFormat.format(endLocalDate);
    }

    private String getDesignDateTime(Event event){
        DateTimeFormatter dateFormatDate = DateTimeFormatter.ofPattern("[]MM月dd日(eee)", Locale.JAPAN);
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("[]H:mm");

        LocalDateTime endLocalDate = event.getStartDate().plusHours((long) event.getDuration());
        return dateFormatDate.format(event.getStartDate()) + " "
                + dateFormat.format(event.getStartDate())
                + " 〜 "
                + dateFormat.format(endLocalDate);
    }

    public ForFrontEvent convertEventForFront(Event event){
        ForFrontEvent forFrontEvent = new ForFrontEvent();
        forFrontEvent.setDesignDate(getDesignDateTime(event));
        forFrontEvent.setEvent(event);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUsername(authentication.getName()).orElseThrow();
        List<User> eventJoinedUsersList = userRepository.findUsersByEventsListIn(event);

        forFrontEvent.setHasJoin(eventJoinedUsersList.contains(user));

        List<JoinedUserInfo> joinedUserInfoList = new ArrayList<>();
        for (User joinedUser : eventJoinedUsersList) {
            JoinedUserInfo joinedUserInfo = new JoinedUserInfo();
            joinedUserInfo.setImagePath(joinedUser.getImagePath());
            joinedUserInfo.setId(joinedUser.getId());
            joinedUserInfoList.add(joinedUserInfo);
        }
        forFrontEvent.setJoinedUserInfo(joinedUserInfoList);

        return forFrontEvent;
    }
}