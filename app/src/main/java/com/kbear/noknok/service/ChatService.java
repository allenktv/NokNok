package com.kbear.noknok.service;

import com.kbear.noknok.common.ServiceConstants;
import com.kbear.noknok.dtos.CustomError;
import com.kbear.noknok.factories.JsonFactory;
import com.kbear.noknok.managers.SocketManager;
import com.kbear.noknok.service.completionhandlers.BooleanCompletionHandler;
import com.kbear.noknok.service.completionhandlers.MessageCompletionHandler;
import com.kbear.noknok.service.response.SocketResponseParser;

import org.json.JSONObject;

/**
 * Created by allen on 3/3/15.
 */
public class ChatService {

    private SocketManager socketManager = SocketManager.getInstance();

    public void sendMessage(String message, BooleanCompletionHandler completionHandler) {
        JSONObject msg = JsonFactory.MessageJsonBuilder(message);
        if (msg != null) {
            SocketResponseParser.BooleanResponseHandler responseHandler = new SocketResponseParser.BooleanResponseHandler(completionHandler);
            socketManager.emit(ServiceConstants.SEND_MESSAGE, msg, responseHandler);
        } else {
            completionHandler.onFailure(new CustomError(new Exception("Failed to parse Json")));
        }
    }

    public void receiveMessage(MessageCompletionHandler completionHandler) {
        SocketResponseParser.MessageResponseHandler responseHandler = new SocketResponseParser.MessageResponseHandler(completionHandler);
        socketManager.on(ServiceConstants.NEW_MESSAGE, responseHandler);
    }
}
