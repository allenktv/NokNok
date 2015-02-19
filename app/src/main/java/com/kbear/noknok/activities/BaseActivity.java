package com.kbear.noknok.activities;

import android.app.Activity;

import com.kbear.noknok.events.EventProducer;

/**
 * Created by allen on 2/18/15.
 */
public class BaseActivity extends Activity {

    @Override
    protected void onResume() {
        super.onResume();
        EventProducer.register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        EventProducer.unregister(this);
    }
}
