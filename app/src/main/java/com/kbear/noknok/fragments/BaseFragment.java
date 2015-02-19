package com.kbear.noknok.fragments;

import android.app.Fragment;

import com.kbear.noknok.events.EventProducer;

/**
 * Created by allen on 2/18/15.
 */
public class BaseFragment extends Fragment {

    @Override
    public void onResume() {
        super.onResume();
        EventProducer.register(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        EventProducer.unregister(this);
    }
}
