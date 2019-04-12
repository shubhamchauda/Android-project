package com.example.login_app;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ShowMenu extends AppCompatActivity {
    DatabaseReference databaseReference;
    ListView menulist;

    FirebaseUser user;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    MenuItem itemstore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_menu);
        menulist = findViewById(R.id.listview);
        list = new ArrayList<>();
        adapter = new ArrayAdapter<String>(this, R.layout.listmenudetail, R.id.itemcat, list);

        user = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("user").child(user.getUid()).child("menu");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    itemstore = ds.getValue(MenuItem.class);
                    Log.d("name:-", "onDataChange: " + itemstore.iname);
                    Log.d("name:-", "onDataChange: " + itemstore.icategory);
                    Log.d("name:-", "onDataChange: " + itemstore.iprice);

                    list.add("Name:- " + itemstore.getIname() + "   Category:- " + itemstore.getIcategory() + "   Price:- " + itemstore.getIprice());

                }
                menulist.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("sdfd", "onCancelled: " + databaseError);


            }
        });

    }
}
