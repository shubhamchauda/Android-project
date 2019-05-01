package com.example.login_app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ShowMenuForCust extends AppCompatActivity {
    MenuItem menuItem;
    HoteInfo hoteInfo = new HoteInfo();
    ArrayList<String>menulist;
    ArrayAdapter<String>adapter;
    TextView hname;
    ListView menudetaillist;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        menulist = new ArrayList<>();
        menuItem = new MenuItem();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_menu_for_cust);
        hname = (TextView) findViewById(R.id.hotelname);
        menudetaillist = (ListView)findViewById(R.id.listofmenu);


       adapter = new ArrayAdapter<String>(this, R.layout.order_menu, R.id.itemcat,menulist);


        Intent intent = getIntent();
     final String name = intent.getStringExtra("hotel");
        Log.d("name", "name "+name);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("user");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                for(DataSnapshot ds : dataSnapshot.getChildren())
                {  hoteInfo = ds.getValue(HoteInfo.class);
                    Log.d("hotel name", "onDataChange:" + hoteInfo.getHotelname());
                    if(hoteInfo.getHotelname().equals(name))
                    {
                       for(DataSnapshot dsq :ds.child("menu").getChildren())
                       { menuItem = dsq.getValue(MenuItem.class);
                           Log.d("menu item ","menu name "+menuItem.iname);
                           menulist.add(menuItem .getIname()+"   Price:"+menuItem.iprice);
                       }
                    }
                }
                menudetaillist.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {


            }
        });
    }

}
class ShowMenuAdapter extends ArrayAdapter<String>
{  //  Context context;
    // ArrayList<String>list;
    private  int layout;

    public ShowMenuAdapter(Context context, int resource, List<String>objects) {
        super(context, resource, objects);
       // this.context = context1;
      //  this.list = list;
        layout = resource;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
                if(convertView == null)
                {
                    LayoutInflater inflater = LayoutInflater.from(getContext());
                    convertView = inflater.inflate(layout,parent,false);


                }
        return super.getView(position, convertView, parent);
    }
}


