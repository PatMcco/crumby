package com.example.crumby;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
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
    Button btn_delete;
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
    private View.OnClickListener deleteListener = v -> deleteClicked();

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
        btn_delete = findViewById(R.id.btn_delete);
        btn_delete.setOnClickListener(deleteListener);
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
        String orderId = et_orderIdNum.getText().toString();
            if(orderId.equals("")){
                et_orderIdNum.setText("Please enter an order ID");}
            else {
                //single contact
                DBAdapter db = new DBAdapter(this);
                db.open();
                String orderIdInput = et_orderIdNum.getText().toString();
                long finalOrderId = Long.parseLong(orderIdInput);
                Cursor c = db.getContact(finalOrderId);
                String formattedContact = formatContacts(c);
                et_orderIdNum.setText(formattedContact);

            }
        }

    private void changeClicked(){//not working
        String orderId = et_orderIdNum.getText().toString();
        if(orderId.equals("")){
            et_orderIdNum.setText("Please enter an order ID");}
        else {
            //single contact
            DBAdapter db = new DBAdapter(this);
            db.open();
            String orderIdInput = et_orderIdNum.getText().toString();
            long finalOrderId = Long.parseLong(orderIdInput);
            Cursor c = db.getContact(finalOrderId);
            String formattedContact = formatContacts(c);
            et_orderIdNum.setText(formattedContact);

        }
    }

    private void deleteClicked(){
        String orderId = et_orderIdNum.getText().toString();
        if(orderId.equals("")){
            et_orderIdNum.setText("Please enter an order ID");}
        else {
            //single contact
            DBAdapter db = new DBAdapter(this);
            db.open();
            String orderIdInput = et_orderIdNum.getText().toString();
            long finalOrderId = Long.parseLong(orderIdInput);
            db.deleteContact(finalOrderId);
            et_orderIdNum.setText("Order deleted");

        }
    }
    private void backClicked(){
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