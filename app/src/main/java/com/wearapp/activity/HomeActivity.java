package com.wearapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.wearapp.R;

public class HomeActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private Toast backtoast;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    Intent j = new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(j);
                    finish();

                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);

                    Intent i = new Intent(getApplicationContext(), RecomActivity.class);
                    startActivity(i);

                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the main_menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        setTitle("Wear App");

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    @Override
    public void onBackPressed() {
        if(backtoast!=null&&backtoast.getView().getWindowToken()!=null) {
            //other stuff...
            super.onBackPressed();
            finish();
        } else {
            backtoast = Toast.makeText(this, "Press again to exit", Toast.LENGTH_SHORT);
            backtoast.show();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {

            case R.id.action_recon:
                Intent i = new Intent(getApplicationContext(), uploadActivity.class);
                startActivity(i);
                return true;

            case R.id.action_share:
                shareTextUrl();
                return true;

            case R.id.action_Logout:
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private void shareTextUrl() {
        Intent share = new Intent(android.content.Intent.ACTION_SEND);
        share.setType("text/plain");
        share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);

        // Add data to the intent, the receiving app will decide
        // what to do with it.
        share.putExtra(Intent.EXTRA_SUBJECT, "Title Of The Post");
        share.putExtra(Intent.EXTRA_TEXT, "Am enjoying good content with wearapp! download app to enjoy too. playstorelinkhere");

        startActivity(Intent.createChooser(share, "Share wearapp"));
    }


}
