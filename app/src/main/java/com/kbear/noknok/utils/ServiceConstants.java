package com.kbear.noknok.utils;

import com.kbear.noknok.ServerConstants;

/**
 * Created by allen on 2/16/15.
 */
public final class ServiceConstants {

    public static final String BASE_SERVER_URL = ServerConstants.BASE_SERVER_URL;

    private static final String ACCOUNTS = "accounts/";

    public static final String ACCOUNTS_LOGIN = ACCOUNTS + "login/";
    public static final String ACCOUNTS_CREATE = ACCOUNTS + "create/";

    public static final String REQUEST_PARAMETER_ID = "id";
    public static final String REQUEST_PARAMETER_USERNAME = "username";
    public static final String REQUEST_PARAMETER_PASSWORD = "password";
    public static final String REQUEST_PARAMETER_VERIFY_PASSWORD = "verify";
    public static final String REQUEST_PARAMETER_GENDER = "gender";
}
