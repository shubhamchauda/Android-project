package com.example.login_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class ShowSelection extends AppCompatActivity implements View.OnClickListener {
    Button hoteldetail1, hotelmenu, logout;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_selection);
        hoteldetail1 = (Button) findViewById(R.id.hoteldetail);
        hotelmenu = (Button) findViewById(R.id.hotelmenu);
        logout = (Button) findViewById(R.id.logout);
        hotelmenu.setOnClickListener(this);
        hoteldetail1.setOnClickListener(this);
        logout.setOnClickListener(this);
        firebaseAuth = FirebaseAuth.getInstance();

    }

    @Override
    public void onClick(View v) {
        if (v == hoteldetail1) {
            startActivity(new Intent(ShowSelection.this, show.class));
        }
        if (v == hotelmenu) {

            startActivity(new Intent(ShowSelection.this, ShowMenu.class));
        }
        if (v == logout) {
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(this, show.class));
        }

    }
}
