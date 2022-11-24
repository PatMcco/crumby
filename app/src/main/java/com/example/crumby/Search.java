package com.example.crumby;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Search extends AppCompatActivity {

    //buttons
    Button btn_findAll;
    Button btn_back2;
    EditText et_searchResults;
    EditText et_orderIdSearch;


    private View.OnClickListener findAllListener = v -> findAllClicked();
    private View.OnClickListener back2Listener = v -> back2Clicked();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        btn_findAll = findViewById(R.id.btn_findAll);
        btn_back2 = findViewById(R.id.btn_back);
        et_searchResults = findViewById(R.id.et_searchResults);
        et_orderIdSearch = findViewById(R.id.et_orderIdSearch);
        btn_findAll.setOnClickListener(findAllListener);
        btn_back2.setOnClickListener(back2Listener);
    }

    private void findAllClicked(){
        //
    }

    private void back2Clicked(){
        //
    }
}