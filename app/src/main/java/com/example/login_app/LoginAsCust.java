package com.example.login_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class LoginAsCust extends AppCompatActivity {
    HoteInfo hotelInfo = new HoteInfo();
    MenuItem menuItem = new MenuItem();
    ListView hotellist;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    FirebaseUser user;
    DatabaseReference databaseReference ,databaseReference1;

    TextView selecthotel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_as_cust);
        hotellist = (ListView) findViewById(R.id.listofhotels);
        user = FirebaseAuth.getInstance().getCurrentUser();
        list = new ArrayList<>();
        adapter = new ArrayAdapter<String>(this, R.layout.hotels, R.id.hotels, list);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("user");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    hotelInfo = ds.getValue(HoteInfo.class);
                    Log.d("hotel name", "onDataChange:" + hotelInfo.getHotelname());
                    Log.d("hotel name", "onDataChange:" + hotelInfo.getOwnername());
                    Log.d("hotel name", "onDataChange:" + hotelInfo.getAddress());

                    list.add(hotelInfo.getHotelname());
                   for(DataSnapshot ds1:ds.child("menu").getChildren())
                   {

                     menuItem = ds1.getValue(MenuItem.class);
                       Log.d("itemName", "onDataChange:"+menuItem.iname);


                   }
                }
                hotellist.setAdapter(adapter);
                hotellist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String s = list.get(position);
                        Intent intent = new Intent(LoginAsCust.this, ShowMenuForCust.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("Menuitem",menuItem.iname);
                     intent.putExtras(bundle);

                        startActivity(intent);


                    }
                });


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("hotel name", "onDataChange:" + databaseError);


            }
        });


    }


}


