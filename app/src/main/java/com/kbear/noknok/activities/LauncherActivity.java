package com.kbear.noknok.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.kbear.noknok.R;
import com.kbear.noknok.bo.AccountBO;
import com.kbear.noknok.dtos.Account;
import com.kbear.noknok.dtos.CustomError;
import com.kbear.noknok.modules.BOModules;
import com.kbear.noknok.modules.NetworkModules;
import com.kbear.noknok.service.completionhandlers.AccountCompletionHandler;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import dagger.ObjectGraph;


public class LauncherActivity extends BaseActivity {

    @InjectView(R.id.username) EditText mUsernameET;
    @InjectView(R.id.password) EditText mPasswordET;
    @InjectView(R.id.verify_password) EditText mVerifyPasswordET;
    @InjectView(R.id.create_account) Button mCreateButton;
    @InjectView(R.id.login) Button mLoginButton;

    @Inject AccountBO mAccountBO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        ObjectGraph objectGraph = ObjectGraph.create(
                new BOModules(),
                new NetworkModules()
        );

        ButterKnife.inject(this);

        mCreateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAccountBO.createAccount(mUsernameET.getText().toString(), mPasswordET.getText().toString(), mVerifyPasswordET.getText().toString(), new AccountCompletionHandler() {
                    @Override
                    public void onSuccess(final Account account) {
                        Intent intent = new Intent(LauncherActivity.this, MainActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(final CustomError error) {
                        Toast.makeText(LauncherActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAccountBO.login(mUsernameET.getText().toString(), mPasswordET.getText().toString(), new AccountCompletionHandler() {
                    @Override
                    public void onSuccess(Account account) {
                        Intent intent = new Intent(LauncherActivity.this, MainActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(CustomError error) {
                        Toast.makeText(LauncherActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_launcher, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
