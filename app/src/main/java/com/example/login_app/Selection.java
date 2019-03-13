package com.example.login_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class Selection extends AppCompatActivity implements View.OnClickListener {
    Button show1, hregister, logout;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);
        hregister = (Button) findViewById(R.id.hregister);
        show1 = (Button) findViewById(R.id.show);
        logout = (Button) findViewById(R.id.logout);
        hregister.setOnClickListener(this);
        show1.setOnClickListener(this);
        logout.setOnClickListener(this);
        firebaseAuth = FirebaseAuth.getInstance();


    }


    @Override
    public void onClick(View view) {
        if (view == hregister) {
            startActivity(new Intent(this, RegisterActivity.class));
        }
        if (view == show1) {
            startActivity(new Intent(this, show.class));
        }
        if (view == logout) {
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }

    }
}
