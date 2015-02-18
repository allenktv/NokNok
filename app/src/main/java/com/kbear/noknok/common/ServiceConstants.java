package com.kbear.noknok.common;

import com.kbear.noknok.ServerConstants;

/**
 * Created by allen on 2/16/15.
 */
public final class ServiceConstants {

    public static final String BASE_SERVER_URL = ServerConstants.BASE_SERVER_URL;

    /**
     * Routes
     */

    //Account
    private static final String ACCOUNT = "account/";
    public static final String ACCOUNT_LOGIN = ACCOUNT + "login/";
    public static final String ACCOUNT_CREATE = ACCOUNT + "create/";

    //Message


    /**
     * Request Parameters
     */
    public static final String REQUEST_PARAMETER_ID = "id";
    public static final String REQUEST_PARAMETER_USERNAME = "username";
    public static final String REQUEST_PARAMETER_PASSWORD = "password";
    public static final String REQUEST_PARAMETER_VERIFY_PASSWORD = "verify";
    public static final String REQUEST_PARAMETER_GENDER = "gender";
}
