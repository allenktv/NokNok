package com.kbear.noknok.bo;

import com.kbear.noknok.service.ChatService;
import com.kbear.noknok.service.completionhandlers.BooleanCompletionHandler;

/**
 * Created by allen on 3/3/15.
 */
public class ChatBO {

    public static void sendMessage(String message, BooleanCompletionHandler completionHandler) {
        ChatService.sendMessage(message, completionHandler);
    }
}
