package com.asakatu.response;

import com.asakatu.entity.Event;

import java.util.List;

public class GetListResponse<T> {
    private String message;

    private List<T> objectList;

    public GetListResponse(String message, List<T> objectList) {
        this.message = message;
        this.objectList = objectList;
    }

    public String getMessage() {
        return message;
    }

    public List<T> getObjectList() {
        return objectList;
    }
}
