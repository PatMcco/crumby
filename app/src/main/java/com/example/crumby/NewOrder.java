package com.example.crumby;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

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

    //listeners
    private View.OnClickListener submitOrderListener = v -> submitOrderClicked();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_order);

        rad_cheese = findViewById(R.id.rad_cheese);
        rad_pep = findViewById(R.id.rad_pep);
        rad_pin = findViewById(R.id.rad_pin);
        rad_small = findViewById(R.id.rad_small);
        rad_med = findViewById(R.id.rad_med);
        rad_large = findViewById(R.id.rad_large);
        cust_name = findViewById(R.id.cust_name);
        cust_num = findViewById(R.id.cust_num);
        btn_submitOrder = findViewById(R.id.btn_submitOrder);
        btn_submitOrder.setOnClickListener(submitOrderListener);

    }

    private void submitOrderClicked(){
        Intent intent = new Intent(this, OrderComplete.class);
        startActivity(intent);
    }
}