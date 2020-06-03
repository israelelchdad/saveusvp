package com.example.saveus.Activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.example.saveus.R;

public class OnPlash extends AppCompatActivity {
    String name = "FIRSTTIME";
    String KEY=  "firsttime";

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
                moveToActivty();

                finish();
            }
        }, 3000);
    }
    private void moveToActivty() {

        SharedPreferences prefs = getSharedPreferences(name, 0);
        boolean noFirst = prefs.getBoolean(KEY, false);
        if(!noFirst ){
            SharedPreferences settings = getSharedPreferences(name, 0);
            SharedPreferences.Editor edit = settings.edit();
            edit.putBoolean(KEY, true);
            edit.commit();
            finish();

            startActivity(new Intent(getApplicationContext(), Welcome.class));
        }else {
            finish();
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));

        }


    }
}
