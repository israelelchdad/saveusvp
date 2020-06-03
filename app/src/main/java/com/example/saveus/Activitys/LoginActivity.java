package com.example.saveus.Activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.saveus.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    Button skip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        skip = findViewById(R.id.login_skip_button);
        initLisitenerSkip();
    }

    private void initLisitenerSkip() {
        skip.setOnClickListener(this );
    }

    @Override
    public void onClick(View v) {

        Intent goToHomePagetivity = new Intent(getBaseContext(), HomePage.class);
       finish();
        startActivity(goToHomePagetivity);
    }

    }

