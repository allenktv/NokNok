package com.kbear.noknok.common;

import com.kbear.noknok.ServerConstants;

/**
 * Created by allen on 2/16/15.
 */
public final class ServiceConstants {

    public static final String BASE_SERVER_URL = ServerConstants.BASE_SERVER_URL;

    /**
     * Events
     */

    //Account
    public static final String CREATE_ACCOUNT = "createAccount";
    public static final String LOGIN_ACCOUNT = "loginAccount";
    public static final String GET_ACCOUNT = "getAccount";
    public static final String DELETE_ACCOUNT = "deleteAccount";

    //Message
    public static final String CLIENT_MESSAGE = "clientMessage";
    public static final String SERVER_MESSAGE = "serverMessage";
    public static final String CLIENT_TYPING = "clientTyping";
    public static final String SERVER_TYPING = "serverTyping";

    //Socket events
    public static final String CONNECTED = "connected";
    public static final String DISCONNECTED = "disconnected";


    /**
     * Request Parameters
     */
    public static final String REQUEST_PARAMETER_ACCOUNT_ID = "accountId";
    public static final String REQUEST_PARAMETER_USERNAME = "username";
    public static final String REQUEST_PARAMETER_PASSWORD = "password";
    public static final String REQUEST_PARAMETER_VERIFY_PASSWORD = "verify";
    public static final String REQUEST_PARAMETER_GENDER = "gender";
    public static final String REQUEST_PARAMETER_MESSAGE = "msg";
    public static final String REQUEST_PARAMETER_IS_TYPING = "isTyping";
}
