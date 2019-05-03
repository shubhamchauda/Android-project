package com.example.login_app;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SelectionShowOrder extends AppCompatActivity implements View.OnClickListener {
    TextView tableno;
    ListView list;
    Button show,showallorders,clear;
    Order order;
    String table;
    String hname;
    ArrayList<String> orders;
    ArrayAdapter<String> adapter;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_selection_show_order);
        super.onCreate(savedInstanceState);
        list = (ListView) findViewById(R.id.listoforder);
        orders = new ArrayList<>();
         clear = (Button)findViewById(R.id.clear);
        tableno = (TextView)findViewById(R.id.tableno);
        adapter = new ArrayAdapter<>(this ,R.layout.order_menu,R.id.itemcat,orders);
        show = (Button)findViewById(R.id.show);
        showallorders = (Button)findViewById(R.id.showallorders);
        show.setOnClickListener(this);
        showallorders.setOnClickListener(this);
        clear.setOnClickListener(this);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("user").child(user.getUid());

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                HoteInfo hotelInfo = new HoteInfo();
                hotelInfo = dataSnapshot.getValue(HoteInfo.class);
                Log.d("asd", "hotel info   " + hotelInfo.hotelname);

                  hname = hotelInfo.hotelname;
                Log.d("asd", "hotel info   " + hname);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    void  showOrder()
    {  orders.clear();
        table  = tableno.getText().toString();
        if(TextUtils.isEmpty(table))
        {
            Toast.makeText(this,"please enter table no.",Toast.LENGTH_SHORT).show();
            return;
        }
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Order");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds: dataSnapshot.getChildren())
                {
                    order = ds.getValue(Order.class);
                    Log.d("orders", "onDataChange: "+order.hname);
                    if(table.equals(order.table) && hname.equals(order.hname))
                    {
                        orders.add(order.itemname +"    Quantity "+order.quantity +"  Table no." + order.table);
                        Log.d("list", "orders "+order.table);
                    }
                }
                if(orders.size()>0) {
                    list.setAdapter(adapter);
                }
                else
                {
                    orders.add("no order");
                    list.setAdapter(adapter);


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
    void showallorders()
    {
        orders.clear();

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Order");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds: dataSnapshot.getChildren())
                {
                    order = ds.getValue(Order.class);
                    Log.d("orders", "onDataChange: "+hname);
                  if( order.hname.equals(hname))
                   {
                        orders.add(order.itemname +"    Quantity "+order.quantity +"  Table no." + order.table);
                        Log.d("list", "orders "+order.table);
                    }
                }
                if(orders.size()>0) {
                    list.setAdapter(adapter);
                }
                else
                {
                    orders.add("no order");
                    list.setAdapter(adapter);


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    void clear()
    {
        orders.clear();
        table  = tableno.getText().toString();
        if(TextUtils.isEmpty(table))
        {
            Toast.makeText(this,"please enter table no.",Toast.LENGTH_SHORT).show();
            return;
        }
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Order");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds: dataSnapshot.getChildren())
                {
                    order = ds.getValue(Order.class);
                    String key = ds.getKey();
                    Log.d("orders", "onDataChange: "+hname);
                    if(table.equals(order.table) && hname.equals(order.hname))

                    {
                        Log.d("clear","clear data"+key);
                        DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference();
                        databaseReference1.child("Order").child(key).removeValue();
                        finish();
                    }
                }
                if(orders.size()>0) {
                    list.setAdapter(adapter);
                }
                else
                {
                    orders.add("no order");
                    list.setAdapter(adapter);


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
    @Override
    public void onClick(View v) {
        if(v == show)
        {
            showOrder();
        }
        if(v==showallorders)
        {
            showallorders();
        }
        if(v==clear)
        {
            clear();

        }
    }
}

