package com.example.crumby;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DashBoard extends AppCompatActivity {

    //buttons
    Button btn_findOrder;
    Button btn_changeOrder;
    Button btn_startOver;

    //listeners
    private View.OnClickListener findOrderListener = v -> findOrderClicked();
    private View.OnClickListener changeOrderListener = v -> changeOrderClicked();
    private View.OnClickListener startOverListener = v -> startOverClicked();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        btn_findOrder = findViewById(R.id.btn_findOrder);
        btn_changeOrder = findViewById(R.id.btn_changeOrder);
        btn_startOver = findViewById(R.id.btn_startOver);
        btn_findOrder.setOnClickListener(findOrderListener);
        btn_changeOrder.setOnClickListener(changeOrderListener);
        btn_startOver.setOnClickListener(startOverListener);
    }

    private void findOrderClicked(){
        Intent int_Find = new Intent(this, Search.class);
        startActivity(int_Find);
    }

    private void changeOrderClicked(){
        Intent int_Update = new Intent(this, UpdateOrder.class);
        startActivity(int_Update);
    }

    private void startOverClicked(){
        Intent int_StartOver = new Intent(this, NewOrder.class);
        startActivity(int_StartOver);
    }
}