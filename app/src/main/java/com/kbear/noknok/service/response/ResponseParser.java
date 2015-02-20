package com.kbear.noknok.service.response;

import com.kbear.noknok.dtos.Account;
import com.kbear.noknok.dtos.CustomError;
import com.kbear.noknok.dtos.CustomErrorType;
import com.kbear.noknok.service.completionhandlers.AccountCompletionHandler;
import com.kbear.noknok.service.completionhandlers.BooleanCompletionHandler;
import com.kbear.noknok.service.completionhandlers.IBaseCompletionHandler;
import com.kbear.noknok.service.completionhandlers.StringCompletionHandler;
import com.kbear.noknok.utils.helpers.ConnectionHelper;
import com.loopj.android.http.BinaryHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONObject;

/**
 * Created by allen on 2/13/15.
 */
public final class ResponseParser {

    private static ResponseHandler sResponseHandler = new ResponseHandler();

    public static class StringJsonHttpResponseHandler extends BaseJsonHttpResponseHandler {
        public StringJsonHttpResponseHandler(StringCompletionHandler stringCompletionHandler) {
            super(stringCompletionHandler);
        }

        @Override
        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
            if (completionHandler != null) {
                String value = sResponseHandler.stringResponseParser.parse(statusCode, headers, response);
                if (value != null) {
                    ((StringCompletionHandler) completionHandler).onSuccess(value);
                } else {
                    CustomError customError = sResponseHandler.customErrorParser.parse(statusCode, headers, response);
                    handleNoResult(customError);
                }
            }
        }
    }

    public static class BooleanJsonHttpResponseHandler extends BaseJsonHttpResponseHandler {
        public BooleanJsonHttpResponseHandler(BooleanCompletionHandler booleanCompletionHandler) {
            super(booleanCompletionHandler);
        }

        @Override
        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
            if (completionHandler != null) {
                boolean success = sResponseHandler.booleanResponseParser.parse(statusCode, headers, response);
                if (success) {
                    ((BooleanCompletionHandler)completionHandler).onSuccess(true);
                } else {
                    CustomError customError = sResponseHandler.customErrorParser.parse(statusCode, headers, response);
                    if (customError != null) {
                        completionHandler.onFailure(customError);
                    } else {
                        ((BooleanCompletionHandler) completionHandler).onSuccess(false);
                    }
                }
            }
        }
    }

    public static class AccountJsonHttpResponseHandler extends BaseJsonHttpResponseHandler {
        public AccountJsonHttpResponseHandler(AccountCompletionHandler booleanCompletionHandler) {
            super(booleanCompletionHandler);
        }

        @Override
        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
            if (completionHandler != null) {
                Account account = sResponseHandler.accountResponseParser.parse(statusCode, headers, response);
                if (account != null) {
                    ((AccountCompletionHandler)completionHandler).onSuccess(account);
                } else {
                    CustomError customError = sResponseHandler.customErrorParser.parse(statusCode, headers, response);
                    handleNoResult(customError);
                }
            }
        }
    }

    private abstract static class BaseJsonHttpResponseHandler extends JsonHttpResponseHandler {
        final IBaseCompletionHandler completionHandler;

        public BaseJsonHttpResponseHandler(IBaseCompletionHandler completionHandler) {
            this.completionHandler = completionHandler;
        }

        @Override
        public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
            handleOnFailure();
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

    private abstract static class BaseBytesHttpRespnoseHandler extends BinaryHttpResponseHandler {
        final IBaseCompletionHandler completionHandler;

        public BaseBytesHttpRespnoseHandler(IBaseCompletionHandler completionHandler) {
            this.completionHandler = completionHandler;
        }

        @Override
        public void onFailure(int statusCode, Header[] headers, byte[] binaryData, Throwable error) {
            handleOnFailure();
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
