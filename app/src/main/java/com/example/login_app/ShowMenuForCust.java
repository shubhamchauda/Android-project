package com.example.login_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class ShowMenuForCust extends AppCompatActivity {
    TextView hname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_menu_for_cust);
        hname = (TextView) findViewById(R.id.hotelname);
        Intent intent = getIntent();
        String itemname = intent.getStringExtra("Menuitem");
        hname.setText(itemname);


    }
}
