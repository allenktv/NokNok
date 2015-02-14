package com.kbear.noknok.service;

import com.kbear.noknok.managers.NetworkManager;
import com.loopj.android.http.RequestParams;

/**
 * Created by allen on 2/13/15.
 */
public final class AccountService {

    public static void createAccount(String username, String password) {
        RequestParams params = new RequestParams(

        );
        NetworkManager.post("", params, null);
    }
}
