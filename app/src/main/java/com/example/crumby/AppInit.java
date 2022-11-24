package com.example.crumby;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class AppInit extends AppCompatActivity {

    //buttons
    Button btn_startOrder;
    Button btn_langSwap;
    TextView delivery;
    String[] eng;
    String[] fr;
    Boolean defEnglish;

    //shared preferences
    SharedPreferences pref;
    //listeners

    private View.OnClickListener startOrderOnClickListener = v -> startOrderClicked();
    private View.OnClickListener languageSwapListener = v -> langSwapClicked();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appinit);
        defEnglish = true;
        btn_startOrder = findViewById(R.id.btn_startOrder);
        btn_langSwap = findViewById(R.id.btn_langSwap);
        btn_startOrder.setOnClickListener(startOrderOnClickListener);
        btn_langSwap.setOnClickListener(languageSwapListener);
        delivery = findViewById(R.id.delivery_msg);
        eng = getResources().getStringArray(R.array.english);
        fr = getResources().getStringArray(R.array.french);
        pref = getSharedPreferences("UserPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        populateButtonText(eng, fr);
    }

    private void startOrderClicked(){
        Intent intent = new Intent(AppInit.this, NewOrder.class);
        startActivity(intent);
    }

    private void langSwapClicked(){
        btn_langSwap.setText("ENGLISH");
    }

    private void populateButtonText(String[] eng, String[] fr){
        if(defEnglish){
        btn_startOrder.setText(eng[0]);
        delivery.setText(eng[1]);
        }
        else if(!defEnglish){
            btn_startOrder.setText(fr[0]);
            delivery.setText(fr[1]);
    }
    }
}