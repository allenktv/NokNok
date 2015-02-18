package com.kbear.noknok.service;

import com.kbear.noknok.managers.NetworkManager;
import com.kbear.noknok.service.completionhandlers.AccountCompletionHandler;
import com.kbear.noknok.service.completionhandlers.BooleanCompletionHandler;
import com.kbear.noknok.service.response.ResponseParser;
import com.kbear.noknok.utils.ServiceConstants;
import com.loopj.android.http.RequestParams;

/**
 * Created by allen on 2/13/15.
 */
public final class AccountService {

    public static void createAccount(String username, String password, String verifyPassword, AccountCompletionHandler completionHandler) {
        String url = ServiceConstants.BASE_SERVER_URL + ServiceConstants.ACCOUNTS_CREATE;
        RequestParams params = new RequestParams(
            ServiceConstants.REQUEST_PARAMETER_USERNAME, username,
            ServiceConstants.REQUEST_PARAMETER_PASSWORD, password,
            ServiceConstants.REQUEST_PARAMETER_VERIFY_PASSWORD, verifyPassword
        );
        ResponseParser.AccountJsonHttpResponseHandler accountJsonHttpResponseHandler = new ResponseParser.AccountJsonHttpResponseHandler(completionHandler);
        NetworkManager.post(url, params, accountJsonHttpResponseHandler);
    }
}
