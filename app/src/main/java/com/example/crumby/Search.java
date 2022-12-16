package com.example.crumby;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.database.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Search extends AppCompatActivity {

    //buttons
    Button btn_findAll;
    Button btn_back2;
    TextView et_searchResults;
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

        //get db file
        try{ DBAdapter db = new DBAdapter(this);
            String destPath = "/data/data/" + getPackageName() + "/MyDB";
            File f = new File(destPath);
            if (!f.exists()) {
                db.CopyDB(getBaseContext().getAssets().open("MyDB"), new FileOutputStream(destPath));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        btn_findAll = findViewById(R.id.btn_findAll);
        btn_back2 = findViewById(R.id.btn_back2);
        et_searchResults = findViewById(R.id.et_searchResults);
        et_searchResults.setMovementMethod(new ScrollingMovementMethod());
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
        //all contacts
        String orderFind = et_orderIdSearch.getText().toString();
        if(orderFind.equals("")){
            DBAdapter db = new DBAdapter(this);
            db.open();
            Cursor c = db.getAllContacts();
            if (c.moveToFirst())
            {
                do {
                    //formats each contact as it is retrieved from the cursor and adds it
                    //to the result box
                        String formattedContact = formatContacts(c);
                        et_searchResults.append(formattedContact);

                } while (c.moveToNext());
            }
            db.close();
        }
        else{
            //single contact
            DBAdapter db = new DBAdapter(this);
            db.open();
            String orderId = et_orderIdSearch.getText().toString();
            long finalOrderId = Long.parseLong(orderId);
            Cursor c = db.getContact(finalOrderId);
            String formattedContact = formatContacts(c);
            et_searchResults.setText(formattedContact);

            }
        }


    private void back2Clicked(){
        Intent int_Back = new Intent(this, DashBoard.class);
        startActivity(int_Back);
    }

    //formats the cursor data into a readable string
    public String formatContacts(Cursor c){
        String result;
        result = (
                "id: " + c.getString(0) + "\n" +
                        "Name: " + c.getString(1) + "\n" +
                        "Phone: " + c.getString(2) + "\n" +
                        "Date: " + c.getString(3) + "\n" +
                        "Time: " + c.getString(4) + "\n" +
                        "Toppings: " + c.getString(5) + ","
                        + c.getString(6) + "," + c.getString(7) + "\n" +
                        "Size: " + c.getString(8) + "\n\n");

        return result;
    }
}