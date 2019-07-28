package com.asakatu.response;

import com.asakatu.entity.Event;

import java.util.List;

public class GetEventsListResponse {
    private String message;

    private List<Event> eventsList;

    public GetEventsListResponse(String message, List<Event> eventsList) {
        this.message = message;
        this.eventsList = eventsList;
    }

    public String getMessage() {
        return message;
    }

    public List<Event> getEventsList() {
        return eventsList;
    }
}