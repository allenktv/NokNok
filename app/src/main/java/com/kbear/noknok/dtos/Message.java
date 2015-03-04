package com.kbear.noknok.dtos;

import com.google.gson.annotations.SerializedName;

/**
 * Created by allen on 2/13/15.
 */
public class Message extends AbstractBaseDto {

    @SerializedName("username")
    private String username;

    @SerializedName("msg")
    private String message;

    public Message(String id, String username, String message) {
        super(id);
        this.username = username;
        this.message = message;
    }

    public String getUsername() {
        return username;
    }

    public String getMessage() {
        return message;
    }
}
