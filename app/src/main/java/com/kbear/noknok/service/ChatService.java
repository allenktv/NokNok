package com.kbear.noknok.service;

import com.kbear.noknok.common.ServiceConstants;
import com.kbear.noknok.dtos.CustomError;
import com.kbear.noknok.factories.JsonFactory;
import com.kbear.noknok.managers.SocketManager;
import com.kbear.noknok.service.completionhandlers.BooleanCompletionHandler;
import com.kbear.noknok.service.completionhandlers.MessageCompletionHandler;
import com.kbear.noknok.service.completionhandlers.StringsCompletionHandler;
import com.kbear.noknok.service.request.RequestParameters;
import com.kbear.noknok.service.response.SocketResponseParser;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import javax.inject.Inject;

/**
 * Created by allen on 3/3/15.
 */
public class ChatService {

    private final SocketManager mSocketManager;

    //TODO: add request param so no more json builder

    @Inject
    public ChatService(SocketManager socketManager) {
        mSocketManager = socketManager;
    }

    public void sendMessage(final String message, final BooleanCompletionHandler completionHandler) {
        RequestParameters parameters = new RequestParameters(
                ServiceConstants.REQUEST_PARAMETER_MESSAGE, message
        );
        SocketResponseParser.BooleanResponseHandler responseHandler = new SocketResponseParser.BooleanResponseHandler(completionHandler);
        mSocketManager.emit(ServiceConstants.CLIENT_MESSAGE, parameters, responseHandler);
    }

    public void sendTyping(final boolean isTyping, final BooleanCompletionHandler completionHandler) {
        RequestParameters parameters = new RequestParameters(
                ServiceConstants.REQUEST_PARAMETER_IS_TYPING, isTyping
        );
        SocketResponseParser.BooleanResponseHandler responseHandler = new SocketResponseParser.BooleanResponseHandler(completionHandler);
        mSocketManager.emit(ServiceConstants.CLIENT_TYPING, parameters, responseHandler);
    }

    public void receiveMessage(MessageCompletionHandler completionHandler) {
        SocketResponseParser.MessageResponseHandler responseHandler = new SocketResponseParser.MessageResponseHandler(completionHandler);
        mSocketManager.on(ServiceConstants.SERVER_MESSAGE, responseHandler);
    }

    public void receiveTypingEvent(StringsCompletionHandler completionHandler) {
        SocketResponseParser.UsernamesResponseHandler responseHandler = new SocketResponseParser.UsernamesResponseHandler(completionHandler);
        mSocketManager.on(ServiceConstants.SERVER_TYPING, responseHandler);
    }
}
