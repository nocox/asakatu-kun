package com.asakatu.response;

import com.asakatu.controller.JoinedUserInfo;
import com.asakatu.entity.Event;

import java.util.List;

// フロントのための整形されたEventClassです。追加したい情報があったらこちらへ
public class ForFrontEvent {
    private String designDate;
    private Event event;
    private boolean hasJoin;
    private List<JoinedUserInfo> joinedUserInfo;

    public String getDesignDate() {
        return designDate;
    }

    public void setDesignDate(String designDate) {
        this.designDate = designDate;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public boolean isHasJoin() {
        return hasJoin;
    }

    public void setHasJoin(boolean hasJoin) {
        this.hasJoin = hasJoin;
    }

    public List<JoinedUserInfo> getJoinedUserInfo() {
        return joinedUserInfo;
    }

    public void setJoinedUserInfo(List<JoinedUserInfo> joinedUserInfo) {
        this.joinedUserInfo = joinedUserInfo;
    }
}
