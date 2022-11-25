package com.example.crumby;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class OrderComplete extends AppCompatActivity {

    //buttons
    Button btn_viewDashboard;
    TextView getReady_msg;
    Boolean defEnglish;
    String[] eng;
    String[] fr;
    SharedPreferences sp;

    //listeners
    private View.OnClickListener viewDashboardListener = v -> viewDashboardClicked();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_complete);
        sp = getApplicationContext().getSharedPreferences("UserPref", Context.MODE_PRIVATE);
        if (sp.contains("langSetting")) {
            defEnglish = sp.getBoolean("langSetting",true);
        }
        eng = getResources().getStringArray(R.array.english);
        fr = getResources().getStringArray(R.array.french);
        btn_viewDashboard = findViewById(R.id.btn_viewDashboard);
        btn_viewDashboard.setOnClickListener(viewDashboardListener);
        getReady_msg = findViewById(R.id.getReady_msg);
        populateButtonText(eng, fr);
    }

    private void populateButtonText(String[] eng, String[] fr){
        if(defEnglish){
            getReady_msg.setText(eng[14]);
            btn_viewDashboard.setText(eng[15]);
        }
        else {
            getReady_msg.setText(fr[14]);
            btn_viewDashboard.setText(fr[15]);
        }
    }
    private void viewDashboardClicked(){
        Intent intent = new Intent(this, DashBoard.class);
        startActivity(intent);
    }
}