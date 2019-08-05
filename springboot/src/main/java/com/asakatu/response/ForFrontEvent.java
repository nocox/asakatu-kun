package com.asakatu.response;

import com.asakatu.entity.Event;

// フロントのための整形されたEventClassです。追加したい情報があったらこちらへ
public class ForFrontEvent {
    private String designDate;
    private Event event;

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
}
