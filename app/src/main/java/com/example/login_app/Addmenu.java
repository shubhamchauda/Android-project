package com.example.login_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Addmenu extends AppCompatActivity implements View.OnClickListener {
    EditText itemname, price, category;
    Button addmenu;
    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addmenu);
        itemname = (EditText) findViewById(R.id.itemname);
        price = (EditText) findViewById(R.id.price);
        category = (EditText) findViewById(R.id.category);

        addmenu = (Button) findViewById(R.id.addmenu);
        addmenu.setOnClickListener(this);
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();

    }

    void addMenu() {
        String iname = itemname.getText().toString().trim();
        String iprice = price.getText().toString().trim();
        String icategory = category.getText().toString().trim();
        if (iname.isEmpty()) {
            Toast.makeText(this, "enter item name", Toast.LENGTH_SHORT).show();

        }
        if (iprice.isEmpty()) {
            Toast.makeText(this, "enter item price", Toast.LENGTH_SHORT).show();

        }
        if (icategory.isEmpty()) {
            Toast.makeText(this, "enter item cateogry", Toast.LENGTH_SHORT).show();

        }
        MenuItem menuitem = new MenuItem(iname, iprice, icategory);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        databaseReference.child("user").child(user.getUid()).child("menu").push().setValue(menuitem);
        Toast.makeText(this, "Information is saving...", Toast.LENGTH_LONG).show();
        finish();
        startActivity(new Intent(Addmenu.this, Selection.class));

    }

    @Override
    public void onClick(View v) {
        if (addmenu == v) {
            addMenu();

        }
    }
}
