package com.kbear.noknok.activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.kbear.noknok.R;

/**
 * Created by allen on 2/17/15.
 */
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loggedin);
        TextView username = (TextView)findViewById(R.id.username_view);
    }
}
