package com.kbear.noknok.service;

import android.util.Log;
import android.widget.Toast;

import com.github.nkzawa.socketio.client.Ack;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;
import com.kbear.noknok.ServerConstants;
import com.kbear.noknok.managers.NetworkManager;
import com.kbear.noknok.managers.SocketManager;
import com.kbear.noknok.service.completionhandlers.AccountCompletionHandler;
import com.kbear.noknok.service.response.ResponseParser;
import com.kbear.noknok.common.ServiceConstants;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;

/**
 * Created by allen on 2/13/15.
 */
public final class AccountService {

    public static void createAccount(String username, String password, String verifyPassword, AccountCompletionHandler completionHandler) {
            SocketManager.getSocket().connect();
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("username", username);
                jsonObject.put("password", password);
                jsonObject.put("verify", verifyPassword);
            } catch (JSONException e) {}
            SocketManager.getSocket().emit("createUser", jsonObject, new Ack() {
                @Override
                public void call(Object... args) {
                    Log.d("socket: ", args[0].toString());
                }
            });
    }

//    public static void createAccount(String username, String password, String verifyPassword, AccountCompletionHandler completionHandler) {
//        String url = ServiceConstants.BASE_SERVER_URL + ServiceConstants.ACCOUNT_CREATE;
//        RequestParams params = new RequestParams(
//            ServiceConstants.REQUEST_PARAMETER_USERNAME, username,
//            ServiceConstants.REQUEST_PARAMETER_PASSWORD, password,
//            ServiceConstants.REQUEST_PARAMETER_VERIFY_PASSWORD, verifyPassword
//        );
//        ResponseParser.AccountJsonHttpResponseHandler accountJsonHttpResponseHandler = new ResponseParser.AccountJsonHttpResponseHandler(completionHandler);
//        NetworkManager.post(url, params, accountJsonHttpResponseHandler);
//    }
//
//    public static void login(String username, String password, AccountCompletionHandler completionHandler) {
//        String url = ServiceConstants.BASE_SERVER_URL + ServiceConstants.ACCOUNT_LOGIN;
//        RequestParams params = new RequestParams(
//                ServiceConstants.REQUEST_PARAMETER_USERNAME, username,
//                ServiceConstants.REQUEST_PARAMETER_PASSWORD, password
//        );
//        ResponseParser.AccountJsonHttpResponseHandler accountJsonHttpResponseHandler = new ResponseParser.AccountJsonHttpResponseHandler(completionHandler);
//        NetworkManager.post(url, params, accountJsonHttpResponseHandler);
//    }
}
