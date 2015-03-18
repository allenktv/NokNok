package com.kbear.noknok.service;

import com.kbear.noknok.common.ServiceConstants;
import com.kbear.noknok.managers.SocketManager;
import com.kbear.noknok.service.completionhandlers.AccountCompletionHandler;
import com.kbear.noknok.service.completionhandlers.BooleanCompletionHandler;
import com.kbear.noknok.service.request.RequestParameters;
import com.kbear.noknok.service.response.SocketResponseParser;

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

    public void createAccount(final String username, final String password, final AccountCompletionHandler completionHandler) {
        RequestParameters parameters = new RequestParameters(
            ServiceConstants.REQUEST_PARAMETER_USERNAME, username,
            ServiceConstants.REQUEST_PARAMETER_PASSWORD, password
        );
        SocketResponseParser.AccountResponseHandler responseHandler = new SocketResponseParser.AccountResponseHandler(completionHandler);
        mSocketManager.emit(ServiceConstants.CREATE_ACCOUNT, parameters, responseHandler);
    }

    public void login(final String username, final String password, final AccountCompletionHandler completionHandler) {
        RequestParameters parameters = new RequestParameters(
            ServiceConstants.REQUEST_PARAMETER_USERNAME, username,
            ServiceConstants.REQUEST_PARAMETER_PASSWORD, password
        );
        SocketResponseParser.AccountResponseHandler responseHandler = new SocketResponseParser.AccountResponseHandler(completionHandler);
        mSocketManager.emit(ServiceConstants.LOGIN_ACCOUNT, parameters, responseHandler);
    }

    public void getAccount(final String accountId, final AccountCompletionHandler completionHandler) {
        RequestParameters parameters = new RequestParameters(
            ServiceConstants.REQUEST_PARAMETER_ACCOUNT_ID, accountId
        );
        SocketResponseParser.AccountResponseHandler responseHandler = new SocketResponseParser.AccountResponseHandler(completionHandler);
        mSocketManager.emit(ServiceConstants.GET_ACCOUNT, parameters, responseHandler);
    }

    public void deleteAccount(final String username, final BooleanCompletionHandler completionHandler) {
        RequestParameters parameters = new RequestParameters(
            ServiceConstants.REQUEST_PARAMETER_USERNAME, username
        );
        SocketResponseParser.BooleanResponseHandler responseHandler = new SocketResponseParser.BooleanResponseHandler(completionHandler);
        mSocketManager.emit(ServiceConstants.DELETE_ACCOUNT, parameters, responseHandler);
    }
}
