package com.example.organizer;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.evernote.client.android.login.EvernoteLoginFragment;
import com.evernote.client.android.EvernoteSession;

public class LoginActivity extends AppCompatActivity implements EvernoteLoginFragment.ResultCallback {

    public static void launch(Activity activity) {
        activity.startActivity(new Intent(activity, LoginActivity.class));
    }

    private Button mButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mButton = (Button) findViewById(R.id.button_login);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EvernoteSession.getInstance().authenticate(LoginActivity.this);
                mButton.setEnabled(false);
            }
        });
    }
    @Override
    public void onLoginFinished(boolean successful) {
        if (successful) {
            finish();
        } else {
            mButton.setEnabled(true);
        }
    }
}