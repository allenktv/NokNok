package com.kbear.noknok.service.response;

import com.kbear.noknok.dtos.Account;
import com.kbear.noknok.dtos.CustomError;
import com.kbear.noknok.dtos.CustomErrorType;
import com.kbear.noknok.service.completionhandlers.AccountCompletionHandler;
import com.kbear.noknok.service.completionhandlers.BooleanCompletionHandler;
import com.kbear.noknok.service.completionhandlers.IBaseCompletionHandler;
import com.kbear.noknok.utils.helpers.ConnectionHelper;

import org.json.JSONObject;

/**
 * Created by allen on 3/3/15.
 */
public class SocketResponseParser {

    private static SocketResponseHandler sResponseHandler = new SocketResponseHandler();

    public static class BooleanResponseHandler extends BaseResponseHandler {
        public BooleanResponseHandler(BooleanCompletionHandler booleanCompletionHandler) {
            super(booleanCompletionHandler);
        }

        @Override
        public void onResponseReceived(JSONObject response) {
            boolean success = sResponseHandler.booleanResponseParser.parse(response);
            if (success) {
                ((BooleanCompletionHandler)completionHandler).onSuccess(true);
            } else {
                CustomError customError = sResponseHandler.customErrorParser.parse(response);
                if (customError != null) {
                    completionHandler.onFailure(customError);
                } else {
                    ((BooleanCompletionHandler) completionHandler).onSuccess(false);
                }
            }
        }
    }

    public static class AccountSocketResponseHandler extends BaseResponseHandler {
        public AccountSocketResponseHandler(AccountCompletionHandler accountCompletionHandler) {
            super(accountCompletionHandler);
        }

        @Override
        public void onResponseReceived(JSONObject response) {
            if (completionHandler != null) {
                Account account = sResponseHandler.accountResponseParser.parse(response);
                if (account != null) {
                    ((AccountCompletionHandler)completionHandler).onSuccess(account);
                } else {
                    CustomError customError = sResponseHandler.customErrorParser.parse(response);
                    handleNoResult(customError);
                }
            }
        }
    }

    public abstract static class BaseResponseHandler implements IBaseResponseHandler {
        final IBaseCompletionHandler completionHandler;

        public BaseResponseHandler(IBaseCompletionHandler completionHandler) {
            this.completionHandler = completionHandler;
        }

        protected void handleOnFailure() {
            if (completionHandler != null) {
                if (ConnectionHelper.getInstance().isConnected())
                    completionHandler.onFailure(new CustomError(CustomErrorType.InternalServerError));
                else
                    completionHandler.onFailure(new CustomError(CustomErrorType.NoInternetConnection));
            }
        }

        protected void handleOnFailure(CustomError error) {
            if (completionHandler != null) {
                completionHandler.onFailure(error);
            }
        }

        protected void handleNoResult(CustomError error) {
            if (error != null)
                handleOnFailure(error);
            else {
                handleOnFailure();
            }
        }
    }
}
