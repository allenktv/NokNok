package com.kbear.noknok.bo;

import com.kbear.noknok.dtos.CustomError;
import com.kbear.noknok.service.AccountService;
import com.kbear.noknok.service.completionhandlers.AccountCompletionHandler;
import com.kbear.noknok.utils.helpers.AccountValidationHelper;

/**
 * Created by allen on 2/17/15.
 */
public final class AccountBO {
    
    public static void createAccount(final String username, final String password, final String verifyPassword, final AccountCompletionHandler completionHandler) {
        CustomError error = AccountValidationHelper.isAccountValid(username, password, verifyPassword);
        if (error == null) {
            AccountService.createAccount(username, password, completionHandler);
        } else {
            completionHandler.onFailure(error);
        }
    }

    public static void login(final String username, final String password, final AccountCompletionHandler completionHandler) {
        CustomError error = AccountValidationHelper.validateLogin(username, password);
        if (error == null) {
            AccountService.login(username, password, completionHandler);
        } else {
            completionHandler.onFailure(error);
        }
    }
}
