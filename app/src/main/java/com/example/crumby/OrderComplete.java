package com.example.crumby;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class OrderComplete extends AppCompatActivity {

    //buttons
    Button btn_viewDashboard;

    //listeners
    private View.OnClickListener viewDashboardListener = v -> viewDashboardClicked();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_complete);
        btn_viewDashboard = findViewById(R.id.btn_viewDashboard);
        btn_viewDashboard.setOnClickListener(viewDashboardListener);
    }

    private void viewDashboardClicked(){
        Intent intent = new Intent(this, DashBoard.class);
        startActivity(intent);
    }
}