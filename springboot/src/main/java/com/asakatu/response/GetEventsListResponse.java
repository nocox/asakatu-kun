package com.asakatu.response;

import java.util.List;

public class GetEventsListResponse {
    private String message;

    private List<ForFrontEvent> eventsList;

    public GetEventsListResponse(String message, List<ForFrontEvent> eventsList) {
        this.message = message;
        this.eventsList = eventsList;
    }

    public String getMessage() {
        return message;
    }

    public List<ForFrontEvent> getEventsList() {
        return eventsList;
    }
}