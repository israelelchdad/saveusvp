package com.example.saveus.Activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.saveus.Fragments.HomeStart;
import com.example.saveus.Objects.Profil;
import com.example.saveus.R;
import com.google.gson.Gson;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private Button skip;
    private Button registar;
    private EditText name;
    private EditText email;
    private EditText phone;
    public static String KEYOPROFILE ="PROFILE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        skip = findViewById(R.id.login_skip_button);
        registar = findViewById(R.id.Activity_login_registar);
        registar.setOnClickListener(this);
        initLisitenerSkip();
    }

    private void initLisitenerSkip() {
        skip.setOnClickListener(this );
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_skip_button:
                Intent goToHomePagetivity = new Intent(getBaseContext(), HomePage.class);
                finish();
                startActivity(goToHomePagetivity);
                break;
            case R.id.Activity_login_registar:
                saveProfile();
                break;


    }
    }

    private void saveProfile() {
        Profil myProfil = new Profil();
        name = findViewById(R.id.A_LOGIN_name);
        email =findViewById(R.id.A_LOGIN_email);
        phone = findViewById(R.id.A_LOGIN_phone);
        myProfil.setName(name.getText().toString());
        myProfil.setEmail(email.getText().toString());
        myProfil.setPhone(phone.getText().toString());
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(myProfil);
        editor.putString(KEYOPROFILE, json);
        editor.commit();

    }

}

