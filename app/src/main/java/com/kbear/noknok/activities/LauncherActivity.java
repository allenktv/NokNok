package com.kbear.noknok.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.kbear.noknok.R;
import com.kbear.noknok.bo.AccountBO;
import com.kbear.noknok.bo.SocketBO;
import com.kbear.noknok.dtos.Account;
import com.kbear.noknok.dtos.CustomError;
import com.kbear.noknok.fragments.LoginFragment;
import com.kbear.noknok.service.completionhandlers.AccountCompletionHandler;
import com.kbear.noknok.utils.helpers.SharedPreferencesHelper;

import javax.inject.Inject;

import butterknife.ButterKnife;


public class LauncherActivity extends BaseActivity {

    @Inject
    AccountBO accountBO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        ButterKnife.inject(this);
        if (SharedPreferencesHelper.getInstance().contains("accountId")) {
            accountBO.getAccount(SharedPreferencesHelper.getInstance().getString("accountId"), new AccountCompletionHandler() {
                @Override
                public void onSuccess(Account account) {
                    Toast.makeText(LauncherActivity.this, "account detected: " + account.getUsername(), Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LauncherActivity.this, MainActivity.class));
                }

                @Override
                public void onFailure(CustomError error) {
                    Toast.makeText(LauncherActivity.this, "error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            openFragment(new LoginFragment());
        }
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
