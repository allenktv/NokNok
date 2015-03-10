package com.kbear.noknok.activities;

import android.app.Application;

import com.kbear.noknok.managers.LocationManager;
import com.kbear.noknok.modules.AndroidModule;
import com.kbear.noknok.modules.BusinessModule;
import com.kbear.noknok.modules.NetworkModule;
import com.kbear.noknok.utils.helpers.ConnectionHelper;

import java.util.Arrays;
import java.util.List;

import dagger.ObjectGraph;

/**
 * Created by allen on 2/16/15.
 */
public class NokNokApplication extends Application {

    private ObjectGraph mObjectGraph;

    @Override
    public void onCreate() {
        super.onCreate();

        mObjectGraph = ObjectGraph.create(getModules().toArray());
        init();
    }

    private void init() {
        ConnectionHelper.getInstance().init(this);
    }

    private List<Object> getModules() {
        return Arrays.<Object>asList(
                new AndroidModule(this)
        );
    }

    public void inject(Object object) {
        mObjectGraph.inject(object);
    }
}
