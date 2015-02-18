package com.kbear.noknok.activities;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
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
import com.kbear.noknok.service.completionhandlers.AccountCompletionHandler;
import com.kbear.noknok.service.completionhandlers.BooleanCompletionHandler;


public class LauncherActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        final EditText username = (EditText)findViewById(R.id.username);
        final EditText password = (EditText)findViewById(R.id.password);
        final EditText verifyPassword = (EditText)findViewById(R.id.verify_password);
        final Button create = (Button) findViewById(R.id.create_account);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AccountBO.createAccount(username.getText().toString(), password.getText().toString(), verifyPassword.getText().toString(), new AccountCompletionHandler() {
                    @Override
                    public void onSuccess(Account account) {
                        Toast.makeText(LauncherActivity.this, "id: " + account.getId() + ", username: " + account.getUsername(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(CustomError error) {
                        Toast.makeText(LauncherActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        Button login = (Button) findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
