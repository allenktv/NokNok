package com.kbear.noknok.service;

import com.kbear.noknok.common.ServiceConstants;
import com.kbear.noknok.dtos.CustomError;
import com.kbear.noknok.factories.JsonFactory;
import com.kbear.noknok.managers.SocketManager;
import com.kbear.noknok.service.completionhandlers.BooleanCompletionHandler;
import com.kbear.noknok.service.completionhandlers.MessageCompletionHandler;
import com.kbear.noknok.service.response.SocketResponseParser;

import org.json.JSONObject;

import javax.inject.Inject;

import dagger.Provides;

/**
 * Created by allen on 3/3/15.
 */
public class ChatService {

    private final SocketManager mSocketManager;

    @Inject
    public ChatService(SocketManager socketManager) {
        mSocketManager = socketManager;
    }

    public void sendMessage(String message, BooleanCompletionHandler completionHandler) {
        JSONObject msg = JsonFactory.MessageJsonBuilder(message);
        if (msg != null) {
            SocketResponseParser.BooleanResponseHandler responseHandler = new SocketResponseParser.BooleanResponseHandler(completionHandler);
            mSocketManager.emit(ServiceConstants.SEND_MESSAGE, msg, responseHandler);
        } else {
            completionHandler.onFailure(new CustomError(new Exception("Failed to parse Json")));
        }
    }

    public void receiveMessage(MessageCompletionHandler completionHandler) {
        SocketResponseParser.MessageResponseHandler responseHandler = new SocketResponseParser.MessageResponseHandler(completionHandler);
        mSocketManager.on(ServiceConstants.NEW_MESSAGE, responseHandler);
    }
}
