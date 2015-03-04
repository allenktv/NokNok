package com.kbear.noknok.dtos;

/**
 * Created by allen on 2/16/15.
 */
public class CustomError {

    private String title;
    private String message;
    private String errorCode;

    public CustomError(Exception e) {
        this.title = "Error";
        this.message = e.getMessage();
        this.errorCode = e.getClass().toString();
    }

    public CustomError(String error) {
        this.title = "Error";
        this.message = error;
        this.errorCode = "400";
    }


    public CustomError(CustomErrorType customErrorType) {
        this.title = customErrorType.getTitle();
        this.message = customErrorType.getMessage();
        this.errorCode = customErrorType.getErrorCode();
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
