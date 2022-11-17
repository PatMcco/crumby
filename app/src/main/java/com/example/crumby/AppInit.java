package com.example.crumby;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AppInit extends AppCompatActivity {

    //buttons
    private Button startOrder;


    private View.OnClickListener startOrderOnClickListener= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startOrderClicked();
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appinit);

        startOrder = new Button(this);


        startOrder.setOnClickListener(startOrderOnClickListener);



    }

    private void startOrderClicked(){
        Intent intent = new Intent(AppInit.this,NewOrder.class);
        startActivity(intent);
    }
}