package com.kbear.noknok.fragments;

import android.app.Fragment;
import android.os.Bundle;

import com.kbear.noknok.activities.NokNokApplication;
import com.kbear.noknok.events.EventProducer;

/**
 * Created by allen on 2/18/15.
 */
public class BaseFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((NokNokApplication)getActivity().getApplication()).inject(this);
    }

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
