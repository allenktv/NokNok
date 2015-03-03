package com.kbear.noknok.activities;

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
import com.kbear.noknok.managers.SocketManager;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by allen on 2/17/15.
 */
public class MainActivity extends BaseActivity {

    @InjectView(R.id.chat_view)LinearLayout mScrollView;
    @InjectView(R.id.message_box) EditText mMessageBox;
    @InjectView(R.id.send_message) ImageButton mSendMessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_layout);

        ButterKnife.inject(this);

        mSendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SocketManager.getSocket().emit("send message", mMessageBox.getText().toString(), new Ack() {
                    @Override
                    public void call(final Object... args) {
                        Gson gson = new Gson();
                        final AAA asfdfa = gson.fromJson(args[0].toString(), AAA.class);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(MainActivity.this, asfdfa.getCode(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
                mMessageBox.setText("");
            }
        });

        SocketManager.getSocket().on("new message", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                JSONObject data = (JSONObject) args[0];
                final String username;
                final String message;
                try {
                    username = data.getString("username");
                    message = data.getString("msg");
                } catch (JSONException e) {
                    return;
                }

                // add the message to view
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        TextView view = new TextView(MainActivity.this);
                        view.setText(username + " : " + message);
                        mScrollView.addView(view);
                    }
                });
            }
        });
    }

    private class AAA {
        private String code;

        public String getCode() {
            return code;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
