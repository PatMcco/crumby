package com.example.crumby;

import androidx.appcompat.app.AppCompatActivity;
import android.content.*;

import android.os.Bundle;

public class AppInit extends AppCompatActivity {

    Intent intent = new Intent();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appinit);
        intent = getIntent();

        if (intent != null) {

        }


    }
}