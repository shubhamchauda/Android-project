package com.example.login_app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    Button save;
    EditText hotalname, ownername, tableno, address;
    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);
        hotalname = (EditText) findViewById(R.id.hotelname);
        ownername = (EditText) findViewById(R.id.ownername);
        tableno = (EditText) findViewById(R.id.tableno);
        address = (EditText) findViewById(R.id.address);
        save = (Button) findViewById(R.id.save);
        save.setOnClickListener(this);

        firebaseAuth.getInstance();


    }

    void saveHotal() {
        String hname = hotalname.getText().toString().trim();
        String oname = ownername.getText().toString().trim();
        String tno = tableno.getText().toString().trim();
        String add = address.getText().toString().trim();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        if (TextUtils.isEmpty(hname)) {
            Toast.makeText(this, "please enter Hotel Name", Toast.LENGTH_SHORT).show();

            return;
        }
        if (TextUtils.isEmpty(oname)) {
            Toast.makeText(this, "please enter Owner Name", Toast.LENGTH_SHORT).show();

            return;
        }
        if (TextUtils.isEmpty(tno)) {
            Toast.makeText(this, "please enter No. of Tables", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(add)) {
            Toast.makeText(this, "please enter address", Toast.LENGTH_SHORT).show();
            return;
        }
        HotalInformation hotalinfo = new HotalInformation(hname, oname, tno, add);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference.child(user.getUid()).push().setValue(hotalinfo);
        Toast.makeText(this, "Information is save...", Toast.LENGTH_LONG).show();


    }


    @Override
    public void onClick(View v) {
        if (v == save) {
            saveHotal();
        }
    }
}
