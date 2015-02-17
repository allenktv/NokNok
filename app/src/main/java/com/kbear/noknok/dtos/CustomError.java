package com.kbear.noknok.dtos;

/**
 * Created by allen on 2/16/15.
 */
public class CustomError {

    private String message;
    private String title;
    private String errorCode;

    public CustomError(Exception e) {
        this.message = e.getMessage();
        this.title = "Error";
        this.errorCode = e.getClass().toString();
    }

    public CustomError(CustomErrorType customErrorType) {
        this.message = customErrorType.getMessage();
        this.title = customErrorType.getTitle();
        this.errorCode = customErrorType.getErrorCode();
    }

    public String getMessage() {
        return message;
    }

    public String getTitle() {
        return title;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
