package com.asakatu.response;

public class ObjectResponse<T> {
    private T data;
    private String status;

    public ObjectResponse(String status) {
        this.status = status;
    }

    public ObjectResponse(String status,T data) {
        this.data = data;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
