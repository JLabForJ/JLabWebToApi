package com.jlab.JLabWebToApi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ApiResponse<T> {
    private String status;
    private String message;

    @JsonProperty("data") // 將資料對應到 "data" 欄位
    private T data;

    // Getters and setters
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
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
