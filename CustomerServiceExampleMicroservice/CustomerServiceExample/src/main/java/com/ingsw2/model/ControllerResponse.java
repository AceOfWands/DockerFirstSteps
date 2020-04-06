package com.ingsw2.model;

public class ControllerResponse<T> {

    private boolean status;
    private String message;
    private T data;

    public ControllerResponse(boolean status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public ControllerResponse(boolean status, String message) {
        this.status = status;
        this.message = message;
        this.data = null;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
