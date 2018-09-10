package com.wearapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;


public class Splash extends AppCompatActivity {
    private SharedPreferences pref = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        //turn title off
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        pref = getSharedPreferences("com.wearapp", MODE_PRIVATE);

        //set to full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //setContentView(new GamePanel(this));


        setContentView(R.layout.splash);
        pref = getPreferences(0);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Thread timer = new Thread() {
                public void run() {
                    try {
                        sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        initFragment();
                    }
                }
            };
            timer.start();
        }


    }



    private void initFragment() {
            Intent i = new Intent(Splash.this, About.class);
            startActivity(i);
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (pref.getBoolean("firstrun", true)) {
            // Do first run stuff here then set 'firstrun' as false
            // using the following line to edit/commit prefs
            pref.edit().putBoolean("firstrun", false).apply();
        }
    }
}
