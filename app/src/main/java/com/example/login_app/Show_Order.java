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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Show_Order extends AppCompatActivity  implements View.OnClickListener {
    TextView tableno;
    ListView list;
    Button show;
    Order order;
    String table;
    ArrayList<String>orders;
    ArrayAdapter<String>adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_show__order);
        super.onCreate(savedInstanceState);
        list = (ListView) findViewById(R.id.listoforder);
        orders = new ArrayList<>();

        tableno = (TextView)findViewById(R.id.tableno);
        adapter = new ArrayAdapter<>(this ,R.layout.order_menu,R.id.itemcat,orders);
        show = (Button)findViewById(R.id.show);
        show.setOnClickListener(this);

    }
  void  showOrder()
   {  orders.clear();
          table  = tableno.getText().toString();
       if(TextUtils.isEmpty(table))
       {
           Toast.makeText(this,"please enter table no.",Toast.LENGTH_SHORT).show();
       }
       DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Order");
       databaseReference.addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               for(DataSnapshot ds: dataSnapshot.getChildren())
               {
                   order = ds.getValue(Order.class);
                   Log.d("orders", "onDataChange: "+order.hname);
                   if(table.equals(order.table))
                   {
                      orders.add(order.itemname +"    Quantity "+order.quantity);
                       Log.d("list", "orders ");
                   }
               }
               if(orders.size()>0) {
                   list.setAdapter(adapter);
               }
               else
               {
                   orders.add("no orders");

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
    }
}
/*class OrderListAdapter extends BaseAdapter
{ Context context;
   ArrayList<Order>orders;

    public OrderListAdapter(Context context, ArrayList<Order> orders) {
        this.context = context;
        this.orders = orders;
    }
class ViewHolder
{

}

}*/