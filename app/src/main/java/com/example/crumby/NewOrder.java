package com.example.crumby;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
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
    String topping1 = "";
    String topping2 = "";
    String topping3 = "";


    //listeners
    private View.OnClickListener submitOrderListener = v -> submitOrderClicked();
    private View.OnClickListener rad_smallListener = v -> rad_smallClicked();
    private View.OnClickListener rad_medListener = v -> rad_medClicked();
    private View.OnClickListener rad_largeListener = v -> rad_largeClicked();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_order);
        sp = getApplicationContext().getSharedPreferences("UserPref", Context.MODE_PRIVATE);
        if (sp.contains("langSetting")) {
            defEnglish = sp.getBoolean("langSetting",true);
        }
        DBAdapter db = new DBAdapter(this);
        eng = getResources().getStringArray(R.array.english);
        fr = getResources().getStringArray(R.array.french);
        create_txt = findViewById(R.id.create_pizza);
        rad_cheese = findViewById(R.id.rad_cheese);
        cust_name = findViewById(R.id.cust_name);
        cust_name.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                // If the EditText loses focus, get a reference to the InputMethodManager
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

                // If the imm is not null, hide the keyboard
                if (imm != null) {
                    imm.hideSoftInputFromWindow(cust_name.getWindowToken(), 0);
                }
            }
        });
        cust_num = findViewById(R.id.cust_num);
        rad_pep = findViewById(R.id.rad_pep);
        rad_pin = findViewById(R.id.rad_pin);
        rad_small = findViewById(R.id.rad_small);
        rad_med = findViewById(R.id.rad_med);
        rad_large = findViewById(R.id.rad_large);
        cust_name = findViewById(R.id.cust_name);
        cust_num = findViewById(R.id.cust_num);
        top_txt = findViewById(R.id.toppings_txt);
        fry_txt = findViewById(R.id.fry_text);
        rad_small.setOnClickListener(rad_smallListener);
        rad_med.setOnClickListener(rad_medListener);
        rad_large.setOnClickListener(rad_largeListener);
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

    //this was less screwing around then making a radio group
    private void rad_smallClicked() {
        rad_med.setChecked(false);
        rad_large.setChecked(false);
    }
    private void rad_medClicked() {
        rad_small.setChecked(false);
        rad_large.setChecked(false);
    }
    private void rad_largeClicked() {
        rad_small.setChecked(false);
        rad_med.setChecked(false);
    }


    private void submitOrderClicked(){
        String name = cust_name.getText().toString();
        String num = cust_num.getText().toString();
        String side = "";

        //get date and time
        String date = java.text.DateFormat.getDateTimeInstance().format(java.util.Calendar.getInstance().getTime());
        String time = java.text.DateFormat.getTimeInstance().format(java.util.Calendar.getInstance().getTime());
        if(rad_small.isChecked()){
            side = "Small";
        }
        else if(rad_med.isChecked()){
            side = "Medium";
        }
        else if(rad_large.isChecked()){
            side = "Large";
        }
        if(rad_cheese.isChecked()){
            topping1 = "Cheese";
        }
        if(rad_pep.isChecked()){
            topping2 = "Pepperoni";
        }
        if(rad_pin.isChecked()){
            topping3 = "Pineapple";
        }

        DBAdapter db = new DBAdapter(this);
        db.open();
        db.insertContact(name, num, date, time, topping1, topping2, topping3, side);
        int custOrderID = db.countRowIds();
        String id = String.valueOf(custOrderID);
        db.close();
        Intent intent = new Intent(this, OrderComplete.class);
        //add order info to extras
        intent.putExtra("currentOrderId", id);
        intent.putExtra("name", name);
        intent.putExtra("num", num);
        intent.putExtra("topping1", topping1);
        intent.putExtra("topping2", topping2);
        intent.putExtra("topping3", topping3);
        intent.putExtra("side", side);
        startActivity(intent);
    }
}