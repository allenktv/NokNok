package com.kbear.noknok.service.completionhandlers;

import com.kbear.noknok.dtos.Account;

/**
 * Created by allen on 2/17/15.
 */
public abstract class AccountCompletionHandler extends AbstractBaseCompletionHandler {
    public abstract void onSuccess(final Account account);
}
