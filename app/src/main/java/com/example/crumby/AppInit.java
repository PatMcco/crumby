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
    TextView sarc_slogan;
    String[] eng;
    String[] fr;
    Boolean defEnglish;

    //shared preferences
    SharedPreferences pref;
    //listeners

    private View.OnClickListener startOrderOnClickListener = v -> startOrderClicked();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appinit);
        SharedPreferences sp = getApplicationContext().getSharedPreferences("UserPref", Context.MODE_PRIVATE);
        if (sp.contains("langSetting")) {
            defEnglish = sp.getBoolean("langSetting",true);
        }
        btn_startOrder = findViewById(R.id.btn_startOrder);
        sarc_slogan = findViewById(R.id.sarc_slogan);
        btn_langSwap = findViewById(R.id.btn_langSwap);
        btn_startOrder.setOnClickListener(startOrderOnClickListener);
        delivery = findViewById(R.id.delivery_msg);
        eng = getResources().getStringArray(R.array.english);
        fr = getResources().getStringArray(R.array.french);
        pref = getSharedPreferences("UserPref", Context.MODE_PRIVATE);
        btn_langSwap.setOnClickListener(v -> {
            langSwapClicked();
            SharedPreferences.Editor editor = pref.edit();
            editor.putBoolean("langSetting", defEnglish);
            editor.commit();
        });
        populateButtonText(eng, fr);
    }

    private void startOrderClicked(){
        Intent intent = new Intent(AppInit.this, NewOrder.class);
        startActivity(intent);
    }

    private void langSwapClicked(){
        if(!defEnglish){
        defEnglish = true;
        }
        else {
            defEnglish = false;
        }
        populateButtonText(eng,fr);
    }

    private void populateButtonText(String[] eng, String[] fr){
        if(defEnglish){
            sarc_slogan.setText(eng[27]);
            btn_startOrder.setText(eng[0]);
            delivery.setText(eng[1]);
        }
        else {
            sarc_slogan.setText(fr[27]);
            btn_startOrder.setText(fr[0]);
            delivery.setText(fr[1]);
    }
    }
}