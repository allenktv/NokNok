package com.kbear.noknok.modules;

/**
 * Created by allen on 3/9/15.
 */

import android.content.Context;

import com.kbear.noknok.activities.NokNokApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * A module for Android-specific dependencies which require a {@link Context} or
 * {@link android.app.Application} to create.
 */
@Module(
        injects = {
            NokNokApplication.class
        },
        includes = {
            NetworkModule.class,
            BusinessModule.class,
            UtilsModule.class
        }
)
public class AndroidModule {
    private final NokNokApplication application;

    public AndroidModule(NokNokApplication nokNokApplication) {
        application = nokNokApplication;
    }

    @Provides
    @Singleton
    NokNokApplication provideApplication() {
        return application;
    }
}
