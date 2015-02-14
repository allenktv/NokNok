package com.kbear.noknok.dtos;

/**
 * Created by allen on 2/13/15.
 */
public class Account extends AbstractBaseDto {

    private final String username;

    public Account(String id, String username) {
        super(id);
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
