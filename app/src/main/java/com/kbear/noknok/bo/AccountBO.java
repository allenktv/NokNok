package com.kbear.noknok.bo;

import com.kbear.noknok.service.AccountService;
import com.kbear.noknok.service.completionhandlers.AccountCompletionHandler;

/**
 * Created by allen on 2/17/15.
 */
public final class AccountBO {
    
    public static void createAccount(final String username, final String password, final String verifyPassword, final AccountCompletionHandler completionHandler) {
        AccountService.createAccount(username, password, verifyPassword, completionHandler);
    }

    public static void login(final String username, final String password, final AccountCompletionHandler completionHandler) {
//        AccountService.login(username, password, completionHandler);
    }
}
