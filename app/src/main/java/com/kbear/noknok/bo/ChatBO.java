package com.kbear.noknok.bo;

import com.kbear.noknok.service.ChatService;
import com.kbear.noknok.service.completionhandlers.BooleanCompletionHandler;
import com.kbear.noknok.service.completionhandlers.MessageCompletionHandler;

import javax.inject.Inject;

/**
 * Created by allen on 3/3/15.
 */
public class ChatBO {

    @Inject private ChatService chatService;

    public void sendMessage(String message, BooleanCompletionHandler completionHandler) {
        chatService.sendMessage(message, completionHandler);
    }

    public void onMessageReceived(MessageCompletionHandler completionHandler) {
        chatService.receiveMessage(completionHandler);
    }
}
