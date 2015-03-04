package com.kbear.noknok.service.completionhandlers;

import com.kbear.noknok.dtos.Message;

/**
 * Created by allen on 3/3/15.
 */
public abstract class MessageCompletionHandler extends AbstractBaseCompletionHandler {
    public abstract void onSuccess(final Message message);
}
