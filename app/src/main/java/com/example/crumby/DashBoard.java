package com.example.crumby;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DashBoard extends AppCompatActivity {

    //buttons
    Button btn_findOrder;
    Button btn_changeOrder;
    Button btn_startOver;
    SharedPreferences sp;
    Boolean defEnglish;
    String[] eng;
    String[] fr;

    //listeners
    private View.OnClickListener findOrderListener = v -> findOrderClicked();
    private View.OnClickListener changeOrderListener = v -> changeOrderClicked();
    private View.OnClickListener startOverListener = v -> startOverClicked();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        sp = getApplicationContext().getSharedPreferences("UserPref", Context.MODE_PRIVATE);
        if (sp.contains("langSetting")) {
            defEnglish = sp.getBoolean("langSetting",true);
        }
        eng = getResources().getStringArray(R.array.english);
        fr = getResources().getStringArray(R.array.french);
        btn_findOrder = findViewById(R.id.btn_findOrder);
        btn_changeOrder = findViewById(R.id.btn_changeOrder);
        btn_startOver = findViewById(R.id.btn_startOver);
        btn_findOrder.setOnClickListener(findOrderListener);
        btn_changeOrder.setOnClickListener(changeOrderListener);
        btn_startOver.setOnClickListener(startOverListener);
        populateButtonText(eng, fr);

    }

    private void populateButtonText(String[] eng, String[] fr){
        if(defEnglish){
            btn_findOrder.setText(eng[20]);
            btn_changeOrder.setText(eng[21]);
            btn_startOver.setText(eng[22]);
        }
        else {
            btn_findOrder.setText(fr[20]);
            btn_changeOrder.setText(fr[21]);
            btn_startOver.setText(fr[22]);
        }
    }

    private void findOrderClicked(){
        Intent intent = new Intent(this, Search.class);
        startActivity(intent);
    }

    private void changeOrderClicked(){
        Intent intent = new Intent(this, UpdateOrder.class);
        startActivity(intent);
    }

    private void startOverClicked(){
        Intent intent = new Intent(this, AppInit.class);
        startActivity(intent);
    }
}