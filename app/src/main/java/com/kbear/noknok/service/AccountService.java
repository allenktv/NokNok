package com.kbear.noknok.service;

import com.kbear.noknok.common.ServiceConstants;
import com.kbear.noknok.dtos.CustomError;
import com.kbear.noknok.factories.JsonFactory;
import com.kbear.noknok.managers.SocketManager;
import com.kbear.noknok.service.completionhandlers.AccountCompletionHandler;
import com.kbear.noknok.service.completionhandlers.BooleanCompletionHandler;
import com.kbear.noknok.service.response.SocketResponseParser;

import org.json.JSONObject;

import javax.inject.Inject;

/**
 * Created by allen on 2/13/15.
 */
public final class AccountService {

    private final SocketManager mSocketManager;

    @Inject
    public AccountService(SocketManager socketManager) {
        mSocketManager = socketManager;
    }

    public void createAccount(String username, String password, AccountCompletionHandler completionHandler) {
        JSONObject account = JsonFactory.AccountJsonBuilder(username, password);
        if (account != null) {
            SocketResponseParser.AccountResponseHandler responseHandler = new SocketResponseParser.AccountResponseHandler(completionHandler);
            mSocketManager.emit(ServiceConstants.CREATE_ACCOUNT, account, responseHandler);
        } else {
            completionHandler.onFailure(new CustomError("Failed to parse Json"));
        }
    }

    public void login(String username, String password, AccountCompletionHandler completionHandler) {
        JSONObject account = JsonFactory.AccountJsonBuilder(username, password);
        if (account != null) {
            SocketResponseParser.AccountResponseHandler responseHandler = new SocketResponseParser.AccountResponseHandler(completionHandler);
            mSocketManager.emit(ServiceConstants.LOGIN_ACCOUNT, account, responseHandler);
        } else {
            completionHandler.onFailure(new CustomError("Failed to parse Json"));
        }
    }

    public void deleteAccount(String username, BooleanCompletionHandler completionHandler) {
        JSONObject user = JsonFactory.UsernameJsonBuilder(username);
        if (user != null) {
            SocketResponseParser.BooleanResponseHandler responseHandler = new SocketResponseParser.BooleanResponseHandler(completionHandler);
            mSocketManager.emit(ServiceConstants.DELETE_ACCOUNT, user, responseHandler);
        } else {
            completionHandler.onFailure(new CustomError("Failed to parse Json"));
        }
    }
}
