package com.kbear.noknok.bo;

import com.kbear.noknok.service.AccountService;
import com.kbear.noknok.service.completionhandlers.BooleanCompletionHandler;

/**
 * Created by allen on 2/17/15.
 */
public final class AccountBO {
    
    public static void createAccount(final String username, final String password, final BooleanCompletionHandler completionHandler) {
        AccountService.createAccount(username, password, completionHandler);
    }
}
