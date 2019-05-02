package com.example.login_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class Selection extends AppCompatActivity implements View.OnClickListener {
    Button show1, hregister, logout, addmenu,showorders;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);
        hregister = (Button) findViewById(R.id.hregister);
        show1 = (Button) findViewById(R.id.show);
        logout = (Button) findViewById(R.id.logout);
        showorders = (Button) findViewById(R.id.showorders);
        addmenu = (Button) findViewById(R.id.addmenu);
        hregister.setOnClickListener(this);
        show1.setOnClickListener(this);
        logout.setOnClickListener(this);
        addmenu.setOnClickListener(this);
        showorders.setOnClickListener(this);
        firebaseAuth = FirebaseAuth.getInstance();


    }


    @Override
    public void onClick(View view) {
        if (view == hregister) {
            startActivity(new Intent(this, RegisterActivity.class));
        }
        if (view == show1) {
            startActivity(new Intent(this, ShowSelection.class));
        }
        if (view == addmenu) {

            startActivity(new Intent(this, Addmenu.class));
        }
        if(view == showorders)
        {
              startActivity(new Intent(this,SelectionShowOrder.class));
        }
        if (view == logout) {
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }


    }
}
