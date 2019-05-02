package com.example.login_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ShowMenuForCust extends AppCompatActivity implements View.OnClickListener {
    MenuItem menuItem;
    Button showorder;
    HoteInfo hoteInfo = new HoteInfo();
    ArrayList<String>menulist;
    ArrayAdapter<String>adapter;
    TextView hname;
    ListView menudetaillist;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final String hotelname;
        menulist = new ArrayList<>();
        menuItem = new MenuItem();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_menu_for_cust);
        hname = (TextView) findViewById(R.id.hotelname);
        menudetaillist = (ListView)findViewById(R.id.listofmenu);
        showorder = (Button)findViewById(R.id.showorder);
        showorder.setOnClickListener(ShowMenuForCust.this);
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
               menudetaillist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
                    {
                       String mname = menulist.get(position);
                        Log.d("menuitem", "name :"+mname );
                        Intent intent = new Intent(ShowMenuForCust.this,Orderbycust.class);
                        intent.putExtra("itemname",mname);
                        intent.putExtra("hotelname",name);
                        startActivity(intent);
                    }
                });


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {


            }
        });
    }


    @Override
    public void onClick(View v)
    {
        if(v==showorder)
        {
           startActivity(new Intent(this,Show_Order.class));
        }
    }
}


/*
class ViewHolder
{
    TextView itemname ;
    Button incbutton;
    Button decbutton;
    TextView orderquantity;
}
class ShowMenuAdapter extends BaseAdapter
{
    List<Order>list ;
    Context context;

    public ShowMenuAdapter( List<Order> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    @SuppressLint("SetTextI18n")
    public View getView(final int position, View convertView, ViewGroup parent)
    {               ViewHolder holder = null;
                      LayoutInflater inflater = (LayoutInflater)
                              context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                if(convertView == null)
                {

                    convertView = inflater.inflate(R.layout.order_menu,null);
                      holder =new ViewHolder();
                    holder.incbutton = (Button)convertView.findViewById(R.id.listIncButton);
                    holder.decbutton = (Button)convertView.findViewById(R.id.listDecButton);
                    holder.orderquantity = (TextView)convertView.findViewById(R.id.listOrderQuantity);
                    holder.itemname = (TextView)convertView.findViewById(R.id.itemcat);
                    convertView.setTag(holder);



                }
                else
                {
                    holder = (ViewHolder)convertView.getTag();

                }
                final Order order = (Order) getItem(position);
        Log.d("position", "getView: " + getItem(position));
                    holder.itemname.setText(order.name);
                    holder.incbutton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(list.get(position).getCounnter()>0)
                            {
                               list.get(position).setCounnter(order.getCounnter()+1);
                                notifyDataSetChanged();
                            }

                        }
                    });



        return convertView;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return list.indexOf(getItem(position));
    }

}
*/


