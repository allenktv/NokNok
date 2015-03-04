package com.kbear.noknok.service;

import com.kbear.noknok.dtos.CustomError;
import com.kbear.noknok.factories.JsonFactory;
import com.kbear.noknok.managers.SocketManager;
import com.kbear.noknok.service.completionhandlers.AccountCompletionHandler;
import com.kbear.noknok.service.response.SocketResponseParser;

import org.json.JSONObject;

/**
 * Created by allen on 2/13/15.
 */
public final class AccountService {

    public static void createAccount(String username, String password, String verifyPassword, AccountCompletionHandler completionHandler) {
        JSONObject account = JsonFactory.AccountJsonBuilder(username, password, verifyPassword);
        if (account != null) {
            SocketResponseParser.AccountSocketResponseHandler responseHandler = new SocketResponseParser.AccountSocketResponseHandler(completionHandler);
            SocketManager.emit("createUser", account, responseHandler);
        } else {
            completionHandler.onFailure(new CustomError(new Exception("Failed to parse Json")));
        }
    }

//    public static void createAccount(String username, String password, String verifyPassword, AccountCompletionHandler completionHandler) {
//        String url = ServiceConstants.BASE_SERVER_URL + ServiceConstants.ACCOUNT_CREATE;
//        RequestParams params = new RequestParams(
//            ServiceConstants.REQUEST_PARAMETER_USERNAME, username,
//            ServiceConstants.REQUEST_PARAMETER_PASSWORD, password,
//            ServiceConstants.REQUEST_PARAMETER_VERIFY_PASSWORD, verifyPassword
//        );
//        ResponseParser.AccountJsonHttpResponseHandler accountJsonHttpResponseHandler = new ResponseParser.AccountJsonHttpResponseHandler(completionHandler);
//        NetworkManager.post(url, params, accountJsonHttpResponseHandler);
//    }
//
//    public static void login(String username, String password, AccountCompletionHandler completionHandler) {
//        String url = ServiceConstants.BASE_SERVER_URL + ServiceConstants.ACCOUNT_LOGIN;
//        RequestParams params = new RequestParams(
//                ServiceConstants.REQUEST_PARAMETER_USERNAME, username,
//                ServiceConstants.REQUEST_PARAMETER_PASSWORD, password
//        );
//        ResponseParser.AccountJsonHttpResponseHandler accountJsonHttpResponseHandler = new ResponseParser.AccountJsonHttpResponseHandler(completionHandler);
//        NetworkManager.post(url, params, accountJsonHttpResponseHandler);
//    }
}
