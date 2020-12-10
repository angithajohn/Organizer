package com.example.organizer;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.evernote.client.android.EvernoteSession;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private static final String CONSUMER_KEY = "ahdynamics";
    private static final String CONSUMER_SECRET = "4d5d38d635628401";
    private static final EvernoteSession.EvernoteService EVERNOTE_SERVICE = EvernoteSession.EvernoteService.SANDBOX;
    private static final boolean SUPPORT_APP_LINKED_NOTEBOOKS = true;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (!EvernoteSession.getInstance().isLoggedIn()) {
//            return;
//        }
        setContentView(R.layout.activity_main);

        String consumerKey = CONSUMER_KEY;
        String consumerSecret = CONSUMER_SECRET;

        EvernoteSession mEvernoteSession = new EvernoteSession.Builder(this)
                .setEvernoteService(EVERNOTE_SERVICE)
                .setSupportAppLinkedNotebooks(SUPPORT_APP_LINKED_NOTEBOOKS)
                .build(consumerKey, consumerSecret)
                .asSingleton();
        mEvernoteSession.authenticate(this);
        if (!EvernoteSession.getInstance().isLoggedIn()) {
            Log.i("Logged in","Not Success");
           return;
        }
        else{
            Log.i("Logged in","Success");
            // do reqired

        }
        Resources resources = getResources();
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        toolbar.setTitleTextColor(resources.getColor(R.color.tb_text));
//
//        setSupportActionBar(toolbar);

        DrawerLayout mDrawerLayout = findViewById(R.id.drawer_layout);
//        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.open_drawer, R.string.close_drawer);

//        mDrawerLayout.setDrawerListener(drawerToggle);
//        drawerToggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                menuItem.setChecked(true);
                onNavDrawerItemClick(menuItem.getItemId());
                return true;
            }
        });
//        navigationView.getMenu().findItem(mSelectedNavItem).setChecked(true);
    }

    private void setSupportActionBar(Toolbar toolbar) {
    }

    private void onNavDrawerItemClick(int itemId) {
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case EvernoteSession.REQUEST_CODE_LOGIN:
                if (resultCode == Activity.RESULT_OK) {
                    Log.i("onActivity result","success");
                    // handle success
                } else {
                    Log.i("onActivity result","Failure");
                    // handle failure
                }
                break;

            default:
                super.onActivityResult(requestCode, resultCode, data);
                break;
        }
    }


}