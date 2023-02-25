package com.example.academyhomework;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class TemporaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temporary);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.flForFirstFragment, new BlankFragment())
                .commit();

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.flForSecFragment, new SecondBlankFragment())
                .commit();
    }
}