package com.kbear.noknok.dtos;

/**
 * Created by allen on 2/13/15.
 */
public class Message extends AbstractBaseDto {

    private String message;

    public Message(String id, String message) {
        super(id);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
