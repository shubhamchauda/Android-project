package com.example.login_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.app.ProgressDialog;
import com.example.shubham.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;


public class ProfileActivity extends AppCompatActivity  implements OnClickListener{
    Button save;
    EditText hotelname, ownername, tableno, address;
    FirebaseAuth firebaseAuth;
    DatabaseReference mDatabaseReference;
    FirebaseUser firebaseUser;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        save = (Button) findViewById(R.id.save);
        hotelname = (EditText) findViewById(R.id.hotelname);
        ownername = (EditText) findViewById(R.id.ownername);
        tableno = (EditText) findViewById(R.id.tableno);
        address = (EditText) findViewById(R.id.address);
        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        save.setOnClickListener(this);

    }

    void saveData()
    {   User user =  new User(hotelname.getText().toString().trim(),
        ownername.getText().toString().trim(),
        tableno.getText().toString().trim());
        if(TextUtils.isEmpty(user.getHname()))
        {
            Toast.makeText(this,"please enter Hotel Name",Toast.LENGTH_SHORT).show();

            return;
        }
        if(TextUtils.isEmpty(user.getOname()))
        {
            Toast.makeText(this,"please enter Owner Name",Toast.LENGTH_SHORT).show();

            return;
        }
        if(TextUtils.isEmpty(user.tableno))
        {
            Toast.makeText(this,"please enter No. of Tables",Toast.LENGTH_SHORT).show();
            return;
        }

        firebaseUser.updateProfile(new UserProfileChangeRequest.Builder().setDisplayName(user.getOname()).build());
        mDatabaseReference.child(firebaseUser.getUid()).setValue(user);
        startActivity(new Intent(ProfileActivity.this, MainActivity.class));
        ProfileActivity.this.finish();

    }


    @Override
    public void onClick(View v)
    {
        if (v == save)
        {
            saveData();
        }

    }
}
