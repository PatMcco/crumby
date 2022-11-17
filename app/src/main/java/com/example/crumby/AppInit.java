package com.example.crumby;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AppInit extends AppCompatActivity {

    //buttons
    Button btn_startOrder;



    private View.OnClickListener startOrderOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startOrderClicked();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appinit);

        btn_startOrder = findViewById(R.id.btn_startOrder);
        btn_startOrder.setOnClickListener(startOrderOnClickListener);



    }

    private void startOrderClicked(){
        Intent intent = new Intent(getApplicationContext(), NewOrder.class);
        startActivity(intent);

    }
}