package com.kbear.noknok.utils.helpers;

import android.text.TextUtils;

import com.kbear.noknok.dtos.CustomError;

/**
 * Created by allen on 3/3/15.
 */
public class AccountValidationHelper {

    public static CustomError isAccountValid(String username, String password, String verify) {
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password) || TextUtils.isEmpty(verify)) {
            return new CustomError("Username and password cannot be blank");
        }
        if (!password.equals(verify)) {
            return new CustomError("Passwords must match");
        }
        return null;
    }

    public static CustomError validateLogin(String username, String password) {
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            return new CustomError("Username and password cannot be blank");
        }
        return null;
    }
}
