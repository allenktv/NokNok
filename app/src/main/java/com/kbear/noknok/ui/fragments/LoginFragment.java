package com.kbear.noknok.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.kbear.noknok.R;
import com.kbear.noknok.ui.activities.MainActivity;
import com.kbear.noknok.bo.AccountBO;
import com.kbear.noknok.common.SharedPreferencesConstants;
import com.kbear.noknok.dtos.Account;
import com.kbear.noknok.dtos.CustomError;
import com.kbear.noknok.service.completionhandlers.AccountCompletionHandler;
import com.kbear.noknok.service.completionhandlers.BooleanCompletionHandler;
import com.kbear.noknok.utils.helpers.SharedPreferencesHelper;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by allen on 2/13/15.
 */
public class LoginFragment extends BaseFragment {

    @Inject
    AccountBO mAccountBO;

    @InjectView(R.id.username) EditText mUsernameET;
    @InjectView(R.id.password) EditText mPasswordET;
    @InjectView(R.id.verify_password) EditText mVerifyPasswordET;
    @InjectView(R.id.create_account) Button mCreateButton;
    @InjectView(R.id.login) Button mLoginButton;
    @InjectView(R.id.delete) Button mDelete;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.login_fragment, container, false);

        ButterKnife.inject(this, v);

        mCreateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAccountBO.createAccount(mUsernameET.getText().toString(), mPasswordET.getText().toString(), mVerifyPasswordET.getText().toString(), new AccountCompletionHandler() {
                    @Override
                    public void onSuccess(final Account account) {
                        SharedPreferencesHelper.getInstance().setPreference(SharedPreferencesConstants.ACCOUNT_ID, account.getId());
                        Intent intent = new Intent(getActivity(), MainActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(final CustomError error) {
                        Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();
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
                        SharedPreferencesHelper.getInstance().setPreference(SharedPreferencesConstants.ACCOUNT_ID, account.getId());
                        Intent intent = new Intent(getActivity(), MainActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(CustomError error) {
                        Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        mDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAccountBO.deleteAccount(mUsernameET.getText().toString(), new BooleanCompletionHandler() {
                    @Override
                    public void onSuccess(boolean success) {
                        Toast.makeText(getActivity(), "deleted account? " + success, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(CustomError error) {
                        Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        return v;
    }

}
