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

public class Search extends AppCompatActivity {

    //buttons
    Button btn_findAll;
    Button btn_back2;
    EditText et_searchResults;
    EditText et_orderIdSearch;
    SharedPreferences sp;
    Boolean defEnglish;
    String[] eng;
    String[] fr;
    TextView orderSearch_msg;
    TextView et_orderFind;



    private View.OnClickListener findAllListener = v -> findAllClicked();
    private View.OnClickListener back2Listener = v -> back2Clicked();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        sp = getApplicationContext().getSharedPreferences("UserPref", Context.MODE_PRIVATE);
        if (sp.contains("langSetting")) {
            defEnglish = sp.getBoolean("langSetting",true);
        }
        eng = getResources().getStringArray(R.array.english);
        fr = getResources().getStringArray(R.array.french);
        btn_findAll = findViewById(R.id.btn_findAll);
        btn_back2 = findViewById(R.id.btn_back2);
        et_searchResults = findViewById(R.id.et_searchResults);
        et_orderIdSearch = findViewById(R.id.et_orderIdSearch);
        orderSearch_msg = findViewById(R.id.orderSearch_msg);
        et_orderFind = findViewById(R.id.et_orderFind);
        btn_findAll.setOnClickListener(findAllListener);
        btn_back2.setOnClickListener(back2Listener);
        populateButtonText(eng, fr);
    }

    private void populateButtonText(String[] eng, String[] fr){
        if(defEnglish){
            orderSearch_msg.setText(eng[23]);
            et_orderFind.setText(eng[24]);
            btn_findAll.setText(eng[25]);
            btn_back2.setText(eng[26]);

        }
        else {
            orderSearch_msg.setText(fr[23]);
            et_orderFind.setText(fr[24]);
            btn_findAll.setText(fr[25]);
            btn_back2.setText(fr[26]);
        }
    }

    private void findAllClicked(){
        //
    }

    private void back2Clicked(){
        Intent int_Back = new Intent(this, DashBoard.class);
        startActivity(int_Back);
    }
}