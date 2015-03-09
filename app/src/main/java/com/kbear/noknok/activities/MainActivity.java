package com.kbear.noknok.activities;

import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.Ack;
import com.google.gson.Gson;
import com.kbear.noknok.R;
import com.kbear.noknok.bo.ChatBO;
import com.kbear.noknok.dtos.CustomError;
import com.kbear.noknok.dtos.Message;
import com.kbear.noknok.managers.LocationManager;
import com.kbear.noknok.managers.SocketManager;
import com.kbear.noknok.service.completionhandlers.BooleanCompletionHandler;
import com.kbear.noknok.service.completionhandlers.MessageCompletionHandler;

import org.json.JSONException;
import org.json.JSONObject;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by allen on 2/17/15.
 */
public class MainActivity extends BaseActivity {

    @InjectView(R.id.chat_view)LinearLayout mScrollView;
    @InjectView(R.id.message_box) EditText mMessageBox;
    @InjectView(R.id.send_message) ImageButton mSendMessage;

    @Inject ChatBO chatBO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_layout);

        ButterKnife.inject(this);

        mSendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Location location = LocationManager.getLastLocation();
                Toast.makeText(MainActivity.this, "lat: " + location.getLatitude() + ", long: " + location.getLongitude(), Toast.LENGTH_SHORT).show();
                chatBO.sendMessage(mMessageBox.getText().toString(), new BooleanCompletionHandler() {
                    @Override
                    public void onSuccess(boolean success) {
                    }

                    @Override
                    public void onFailure(final CustomError error) {
                        Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
                mMessageBox.setText("");
            }
        });

        chatBO.onMessageReceived(new MessageCompletionHandler() {
            @Override
            public void onSuccess(Message message) {
                TextView view = new TextView(MainActivity.this);
                view.setText(message.getUsername() + " : " + message.getMessage());
                mScrollView.addView(view);
            }

            @Override
            public void onFailure(CustomError error) {
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
