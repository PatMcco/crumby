package com.example.crumby;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class AppInit extends AppCompatActivity {

    //buttons
    Button btn_startOrder;
    Button btn_langSwap;

    //listeners
    SharedPreferences langPref;
    private View.OnClickListener startOrderOnClickListener = v -> startOrderClicked();
    private View.OnClickListener languageSwapListener = v -> langSwapClicked();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appinit);

        btn_startOrder = findViewById(R.id.btn_startOrder);
        btn_langSwap = findViewById(R.id.btn_langSwap);
        btn_startOrder.setOnClickListener(startOrderOnClickListener);
        btn_langSwap.setOnClickListener(languageSwapListener);
    }

    private void startOrderClicked(){
        Intent intent = new Intent(AppInit.this, NewOrder.class);
        startActivity(intent);
    }

    private void langSwapClicked(){
        btn_langSwap.setText("ENGLISH");
    }
}