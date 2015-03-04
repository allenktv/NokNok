package com.kbear.noknok.activities;

import android.app.Application;

import com.kbear.noknok.managers.SocketManager;
import com.kbear.noknok.utils.helpers.ConnectionHelper;

/**
 * Created by allen on 2/16/15.
 */
public class NokNokApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        init();
    }

    private void init() {
        ConnectionHelper.getInstance().init(this);
        SocketManager.init();
    }
}
