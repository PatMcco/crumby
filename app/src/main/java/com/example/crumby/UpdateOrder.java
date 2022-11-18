package com.example.crumby;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UpdateOrder extends AppCompatActivity {

    //buttons
    Button btn_find;
    Button btn_change;
    Button btn_back;
    EditText et_orderIdNum;

    //listeners
    private View.OnClickListener findListener = v -> findClicked();
    private View.OnClickListener changeListener = v -> changeClicked();
    private View.OnClickListener backListener = v -> backClicked();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_order);

        btn_find = findViewById(R.id.btn_find);
        btn_change = findViewById(R.id.btn_change);
        btn_back = findViewById(R.id.btn_back);
        et_orderIdNum = findViewById(R.id.orderIdNum);
        btn_find.setOnClickListener(findListener);
        btn_change.setOnClickListener(changeListener);
        btn_back.setOnClickListener(backListener);
    }

    //methods
    private void findClicked(){
        //
    }

    private void changeClicked(){
        //
    }

    private void backClicked(){
        //
    }

}