package com.example.saveus.Activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.saveus.R;

public class OnPlash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_plash);
        moveToActivityWelcome();
    }

    private void moveToActivityWelcome() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
               Intent goToWelcomActivity = new Intent(getBaseContext(), Welcome.class);
//                Intent goToWelcomActivity = new Intent(getBaseContext(), HomePage.class);
                startActivity(goToWelcomActivity);;
                finish();
            }
        }, 3000);
    }
}
