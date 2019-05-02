package com.example.login_app;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Orderbycust extends AppCompatActivity implements View.OnClickListener {
    TextView itemname;
    EditText oname,quantity,tableno ;
    Button placeorder;
    String hname;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        progressDialog  = new ProgressDialog(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderbycust);
        itemname = (TextView) findViewById(R.id.itemname);
        Intent intent = getIntent();
        String name = intent.getStringExtra("itemname");
        this.hname = intent.getStringExtra("hotelname");
        Log.d("name ", "name"+ name);
        Log.d("name ", "hname"+ hname);
        itemname.setText(name);
        oname = (EditText) findViewById(R.id.ordername);
        quantity = (EditText) findViewById(R.id.quantity);
        tableno = (EditText) findViewById(R.id.tableno);
        placeorder = (Button) findViewById(R.id.placeOrderButton);
        placeorder.setOnClickListener(this);

    }
    void orderplace()
    {
        String name = oname.getText().toString();
        String qntty = quantity.getText().toString();
        String table = tableno.getText().toString();
        String iname = itemname.getText().toString();
        if(TextUtils.isEmpty(name))
        {
            Toast.makeText(Orderbycust.this ,"please enter name",Toast.LENGTH_SHORT).show();
        }
        if(TextUtils.isEmpty(qntty))
        {
            Toast.makeText(Orderbycust.this ,"please enter name",Toast.LENGTH_SHORT).show();
        }
        if(TextUtils.isEmpty(table))
        {
            Toast.makeText(Orderbycust.this ,"please enter name",Toast.LENGTH_SHORT).show();
        }
        progressDialog.setMessage("Ordering food");
        progressDialog.show();
        Order order = new Order(name,table,qntty,this.hname,iname);
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("Order").push().setValue(order);
        Toast.makeText(this, "Food is orderd", Toast.LENGTH_LONG).show();
        progressDialog.dismiss();
        Intent intent = new Intent(Orderbycust.this, ShowMenuForCust.class);
        intent.putExtra("hotel",hname);
        startActivity(intent);
        finish();
    }

    @Override
    public void onClick(View v)
    {
      if(placeorder == v)
      {
          orderplace();
      }

    }
}
