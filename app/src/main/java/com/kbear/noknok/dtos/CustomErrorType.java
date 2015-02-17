package com.kbear.noknok.dtos;

/**
 * Created by allen on 2/16/15.
 */
public enum  CustomErrorType {
    NoInternetConnection("Error", "No internet connection", "500"),
    InternalServerError("Error", "Internal Server error", "500");

    private final String title;
    private final String message;
    private final String errorCode;

    private CustomErrorType(String title, String message, String errorCode) {
        this.title = title;
        this.message = message;
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }
}
