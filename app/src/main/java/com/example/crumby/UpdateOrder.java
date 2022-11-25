package com.example.crumby;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class UpdateOrder extends AppCompatActivity {

    //buttons
    Button btn_find;
    Button btn_change;
    Button btn_back;
    EditText et_orderIdNum;
    TextView orderChange_msg;
    SharedPreferences sp;
    Boolean defEnglish;
    String[] eng;
    String[] fr;

    //listeners
    private View.OnClickListener findListener = v -> findClicked();
    private View.OnClickListener changeListener = v -> changeClicked();
    private View.OnClickListener backListener = v -> backClicked();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_order);
        sp = getApplicationContext().getSharedPreferences("UserPref", Context.MODE_PRIVATE);
        if (sp.contains("langSetting")) {
            defEnglish = sp.getBoolean("langSetting",true);
        }
        eng = getResources().getStringArray(R.array.english);
        fr = getResources().getStringArray(R.array.french);
        btn_find = findViewById(R.id.btn_find);
        btn_change = findViewById(R.id.btn_change);
        btn_back = findViewById(R.id.btn_back);
        et_orderIdNum = findViewById(R.id.orderIdNum);
        orderChange_msg = findViewById(R.id.orderChange_msg);
        btn_find.setOnClickListener(findListener);
        btn_change.setOnClickListener(changeListener);
        btn_back.setOnClickListener(backListener);
        populateButtonText(eng, fr);
    }

    //methods

    private void populateButtonText(String[] eng, String[] fr){
        if(defEnglish){
            orderChange_msg.setText(eng[16]);
            btn_find.setText(eng[20]);
            btn_change.setText(eng[21]);
            btn_back.setText(eng[22]);

        }
        else {
            orderChange_msg.setText(fr[16]);
            btn_find.setText(fr[20]);
            btn_change.setText(fr[21]);
            btn_back.setText(fr[22]);
        }
    }

    private void findClicked(){
        //
    }

    private void changeClicked(){
        //
    }

    private void backClicked(){
        Intent int_Back = new Intent(this, DashBoard.class);
        startActivity(int_Back);
    }

}