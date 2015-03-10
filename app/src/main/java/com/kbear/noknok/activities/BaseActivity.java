package com.kbear.noknok.activities;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;

import com.kbear.noknok.events.EventProducer;

/**
 * Created by allen on 2/18/15.
 */
public class BaseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((NokNokApplication)getApplication()).inject(this);
    }

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
