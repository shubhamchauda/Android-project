package com.example.login_app;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class activity_register extends AppCompatActivity implements View.OnClickListener {
    Button register,login;
    EditText userid, password, cpassword;
    ProgressDialog progressDialog;
    FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        progressDialog  = new ProgressDialog(this);
        firebaseAuth =FirebaseAuth.getInstance();



        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_register);
        login = (Button)findViewById(R.id.blogin);
        register = (Button) findViewById(R.id.register);
        cpassword = (EditText) findViewById(R.id.cpassword);
        userid = (EditText) findViewById(R.id.userid);
        password = (EditText) findViewById(R.id.password);

        register.setOnClickListener(this);
        login.setOnClickListener(this);

    }
    void  login()

    {
        startActivity(new Intent(this,MainActivity.class));
    }
   public void registerUser() {

       String email = userid.getText().toString().trim();
        String pass = password.getText().toString().trim();
       String cpass = cpassword.getText().toString().trim();


        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "please enter email ", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(pass)) {
            Toast.makeText(this, "please enter password ", Toast.LENGTH_SHORT).show();
            return;
        }
       if (TextUtils.isEmpty(cpass)) {
           Toast.makeText(this, "please enter confirm password ", Toast.LENGTH_SHORT).show();
           return;
       }

       if (cpass.equals(pass)) {
           progressDialog.setMessage("Registring User.....");
           progressDialog.show();

           firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
               @Override
               public void onComplete(@NonNull Task<AuthResult> task) {
                   progressDialog.dismiss();
                   if (task.isSuccessful()) {
                       Toast.makeText(activity_register.this, "Register Successifully...", Toast.LENGTH_SHORT).show();
                       finish();
                       startActivity(new Intent(getApplicationContext(), Selection.class));
                       return;
                   } else {
                       Toast.makeText(activity_register.this, "could not register please try again", Toast.LENGTH_SHORT).show();

                       login();

                       return;
                   }
               }
           });
       } else {
           Toast.makeText(activity_register.this, "confirm password must be same as password", Toast.LENGTH_LONG).show();
       }
    }



    @Override
    public void onClick (View view)
    {if (register == view)
    {
        registerUser();
    }
    if (login == view)
         {
             login();
         }



    }
}
