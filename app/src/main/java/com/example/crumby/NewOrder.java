package com.example.crumby;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class NewOrder extends AppCompatActivity {

    //buttons and edits
    Button btn_submitOrder;
    RadioButton rad_cheese;
    RadioButton rad_pep;
    RadioButton rad_pin;
    RadioButton rad_small;
    RadioButton rad_med;
    RadioButton rad_large;
    EditText cust_name;
    EditText cust_num;
    TextView top_txt;
    TextView fry_txt;
    TextView create_txt;
    Boolean defEnglish;
    String[] eng;
    String[] fr;
    SharedPreferences sp;


    //listeners
    private View.OnClickListener submitOrderListener = v -> submitOrderClicked();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_order);
        sp = getApplicationContext().getSharedPreferences("UserPref", Context.MODE_PRIVATE);
        if (sp.contains("langSetting")) {
            defEnglish = sp.getBoolean("langSetting",true);
        }
        eng = getResources().getStringArray(R.array.english);
        fr = getResources().getStringArray(R.array.french);
        create_txt = findViewById(R.id.create_pizza);
        rad_cheese = findViewById(R.id.rad_cheese);
        rad_pep = findViewById(R.id.rad_pep);
        rad_pin = findViewById(R.id.rad_pin);
        rad_small = findViewById(R.id.rad_small);
        rad_med = findViewById(R.id.rad_med);
        rad_large = findViewById(R.id.rad_large);
        cust_name = findViewById(R.id.cust_name);
        cust_num = findViewById(R.id.cust_num);
        top_txt = findViewById(R.id.toppings_txt);
        fry_txt = findViewById(R.id.fry_text);
        btn_submitOrder = findViewById(R.id.btn_submitOrder);
        btn_submitOrder.setOnClickListener(submitOrderListener);
        populateButtonText(eng, fr);

    }
    private void populateButtonText(String[] eng, String[] fr){
        if(defEnglish){
            create_txt.setText(eng[2]);
            top_txt.setText(eng[3]);
            rad_cheese.setText(eng[4]);
            rad_pep.setText(eng[5]);
            rad_pin.setText(eng[6]);
            fry_txt.setText(eng[7]);
            rad_small.setText(eng[8]);
            rad_med.setText(eng[9]);
            rad_large.setText(eng[10]);
            cust_name.setText(eng[11]);
            cust_num.setText(eng[12]);
            btn_submitOrder.setText(eng[13]);
        }
        else {
            create_txt.setText(fr[2]);
            top_txt.setText(fr[3]);
            rad_cheese.setText(fr[4]);
            rad_pep.setText(fr[5]);
            rad_pin.setText(fr[6]);
            fry_txt.setText(fr[7]);
            rad_small.setText(fr[8]);
            rad_med.setText(fr[9]);
            rad_large.setText(fr[10]);
            cust_name.setText(fr[11]);
            cust_num.setText(fr[12]);
            btn_submitOrder.setText(fr[13]);
        }
    }

    private void submitOrderClicked(){
        Intent intent = new Intent(this, OrderComplete.class);
        startActivity(intent);
    }
}