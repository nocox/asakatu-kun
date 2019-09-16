package com.asakatu.controller;

import com.asakatu.entity.Event;
import com.asakatu.entity.User;
import com.asakatu.entity.UserStatus;
import com.asakatu.entity.UserStatusMaster;
import com.asakatu.repository.EventRepository;
import com.asakatu.repository.UserRepository;
import com.asakatu.repository.UserStatusMasterRepository;
import com.asakatu.repository.UserStatusRepository;
import com.asakatu.response.GetListResponse;
import com.asakatu.response.ObjectResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReactionController {

    private final UserStatusMasterRepository userStatusMasterRepository;
    private final UserRepository userRepository;
    private final UserStatusRepository userStatusRepository;
    private final EventRepository eventRepository;

    public ReactionController(UserStatusMasterRepository userStatusMasterRepository, UserRepository userRepository, UserStatusRepository userStatusRepository, EventRepository eventRepository) {
        this.userStatusMasterRepository = userStatusMasterRepository;
        this.userRepository = userRepository;
        this.userStatusRepository = userStatusRepository;
        this.eventRepository = eventRepository;
    }

    @GetMapping(path = "/reaction/list")
    public List<UserStatusMaster> getReactionList() {
        return userStatusMasterRepository.findAll();
    }

    @GetMapping(path = "/reaction/myself/{eventId}")
    public UserStatusMaster getMyReaction(@PathVariable Long eventId) {
        // ログインユーザの取得
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findUsersByUsername(authentication.getName()).get(0);

        // TODO: 2019-08-07 nocox ここDBにアクセスしすぎ．．なんとかしたい
        Event event = eventRepository.findById(eventId).orElseThrow();
        UserStatus userStatus = userStatusRepository.findUserStatusByEventAndUserIs(event, user);

        return userStatusMasterRepository.findById(userStatus.getMasterId()).orElseThrow();
    }

    @PutMapping(path = "/reaction/change/{eventId}/{reactionId}")
    public UserStatusMaster changeReaction(@PathVariable Long eventId, @PathVariable Long reactionId) {
        // ログインユーザの取得
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findUsersByUsername(authentication.getName()).get(0);

        // TODO: 2019-08-07 nocox ここDBにアクセスしすぎ．．なんとかしたい
        Event event = eventRepository.getOne(eventId);
        UserStatus userStatus = userStatusRepository.findUserStatusByEventAndUserIs(event, user);
        UserStatusMaster reaction = userStatusMasterRepository.findById(reactionId).orElseThrow();
        userStatus.setMasterId(reactionId);
        userStatusRepository.save(userStatus);

        return reaction;
    }
}
