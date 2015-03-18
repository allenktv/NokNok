package com.kbear.noknok.activities;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

import com.kbear.noknok.R;
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

    public void openFragment(Fragment fragment) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.add(R.id.container, fragment);
        ft.commit();
    }
}
