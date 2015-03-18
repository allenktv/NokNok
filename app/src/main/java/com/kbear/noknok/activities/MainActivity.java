package com.kbear.noknok.activities;

import android.os.Bundle;

import com.kbear.noknok.R;
import com.kbear.noknok.bo.SocketBO;
import com.kbear.noknok.fragments.ChatFragment;

import javax.inject.Inject;

/**
 * Created by allen on 2/17/15.
 */
public class MainActivity extends BaseActivity {

    @Inject SocketBO mSocketBO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        mSocketBO.handleOnDisconnect();

        openFragment(new ChatFragment());
    }
}
