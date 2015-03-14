package com.kbear.noknok.bo;

import com.kbear.noknok.dtos.CustomError;
import com.kbear.noknok.service.AccountService;
import com.kbear.noknok.service.completionhandlers.AccountCompletionHandler;
import com.kbear.noknok.service.completionhandlers.BooleanCompletionHandler;
import com.kbear.noknok.utils.helpers.AccountValidationHelper;

import javax.inject.Inject;

/**
 * Created by allen on 2/17/15.
 */
public final class AccountBO {

    private final AccountService mAccountService;

    @Inject
    public AccountBO(AccountService accountService) {
        mAccountService = accountService;
    }
    
    public void createAccount(final String username, final String password, final String verifyPassword, final AccountCompletionHandler completionHandler) {
        CustomError error = AccountValidationHelper.isAccountValid(username, password, verifyPassword);
        if (error == null) {
            mAccountService.createAccount(username, password, completionHandler);
        } else {
            completionHandler.onFailure(error);
        }
    }

    public void login(final String username, final String password, final AccountCompletionHandler completionHandler) {
        CustomError error = AccountValidationHelper.validateLogin(username, password);
        if (error == null) {
            mAccountService.login(username, password, completionHandler);
        } else {
            completionHandler.onFailure(error);
        }
    }

    public void getAccount(final String accountId, final AccountCompletionHandler completionHandler) {
        mAccountService.getAccount(accountId, completionHandler);
    }

    public void deleteAccount(final String username, final BooleanCompletionHandler completionHandler) {
        mAccountService.deleteAccount(username, completionHandler);
    }
}
