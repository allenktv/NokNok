package com.kbear.noknok.activities;

import android.location.Location;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.kbear.noknok.R;
import com.kbear.noknok.bo.ChatBO;
import com.kbear.noknok.bo.SocketBO;
import com.kbear.noknok.dtos.CustomError;
import com.kbear.noknok.dtos.Message;
import com.kbear.noknok.managers.LocationManager;
import com.kbear.noknok.service.completionhandlers.BooleanCompletionHandler;
import com.kbear.noknok.service.completionhandlers.MessageCompletionHandler;
import com.kbear.noknok.service.completionhandlers.StringsCompletionHandler;
import com.kbear.noknok.utils.helpers.StringHelper;

import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by allen on 2/17/15.
 */
public class MainActivity extends BaseActivity {

    @Inject ChatBO mChatBO;
    @Inject LocationManager mLocationManager;
    @Inject SocketBO mSocketBO;

    @InjectView(R.id.chat_view) LinearLayout mScrollView;
    @InjectView(R.id.message_box) EditText mMessageBox;
    @InjectView(R.id.send_message) ImageButton mSendMessage;
    @InjectView(R.id.typing_textview) TextView mTypingText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_layout);

        ButterKnife.inject(this);

        mSocketBO.handleOnDisconnect();

        mSendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Location location = mLocationManager.getLastLocation();
                Toast.makeText(MainActivity.this, "lat: " + location.getLatitude() + ", long: " + location.getLongitude(), Toast.LENGTH_SHORT).show();
                mChatBO.sendMessage(mMessageBox.getText().toString(), new BooleanCompletionHandler() {
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

        mChatBO.onMessageReceived(new MessageCompletionHandler() {
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

        mChatBO.onTypingReceived(new StringsCompletionHandler() {
            @Override
            public void onSuccess(List<String> strings) {
                if (strings.isEmpty()) {
                    mTypingText.setVisibility(View.GONE);
                    mTypingText.setText("");
                } else {
                    mTypingText.setVisibility(View.VISIBLE);
                    mTypingText.setText(StringHelper.getCommaSeparatedString(strings) + " is typing");
                }
            }

            @Override
            public void onFailure(CustomError error) {
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        mMessageBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 0) {
                    mChatBO.sendTyping(false, new BooleanCompletionHandler() {
                        @Override
                        public void onSuccess(boolean success) {

                        }

                        @Override
                        public void onFailure(CustomError error) {

                        }
                    });
                } else {
                    mChatBO.sendTyping(true, new BooleanCompletionHandler() {
                        @Override
                        public void onSuccess(boolean success) {

                        }

                        @Override
                        public void onFailure(CustomError error) {

                        }
                    });
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
