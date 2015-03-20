package com.kbear.noknok.modules;

import com.kbear.noknok.ui.activities.MainActivity;
import com.kbear.noknok.ui.activities.NokNokApplication;
import com.kbear.noknok.ui.fragments.ChatFragment;
import com.kbear.noknok.managers.LocationManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by allen on 3/9/15.
 */
@Module(
    injects = {
            MainActivity.class,
            ChatFragment.class
    },
    complete = false
)
public class UtilsModule {

    @Provides
    @Singleton
    LocationManager provideLocationManager(NokNokApplication context) {
        return new LocationManager(context);
    }
}
