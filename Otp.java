package com.example.sahil.bloodbank;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Otp extends AppCompatActivity {

    EditText otp1;
    Button verifiybtn;
    String name,pass,phone,gender,blood,state,city,otp;
    FirebaseDatabase data;
    DatabaseReference ref;
    artist art;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        Bundle bn = getIntent().getExtras();
        otp1 = (EditText)findViewById(R.id.otp);
        name = bn.getString("name");
        phone = bn.getString("phone");
        pass = bn.getString("pass");
        gender = bn.getString("gender");
        blood = bn.getString("blood");
        state = bn.getString("state");
        city = bn.getString("city");
        otp = bn.getString("otp");
        verifiybtn = (Button)findViewById(R.id.registerbtn1);
        data = FirebaseDatabase.getInstance();
        ref=data.getReference("users").child(phone);
        verifiybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(otp1.getText().toString().equals(otp))
                {
                    art=new artist(name,phone,pass,gender,blood,state,city);
                    ref.setValue(art);
                    Intent in=new Intent(getApplicationContext(),Login.class);
                    startActivity(in);
                    Toast.makeText(getApplicationContext(),"Successfully Registered",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Invalid OTP",Toast.LENGTH_LONG).show();
                }


                 //Toast.makeText(getApplicationContext()," Name "+name+" phone "+phone+" pass "+pass+
                       //" gender "+gender +" blood "+blood +" state "+state+" city "+city+" otp "+otp,Toast.LENGTH_LONG).show();
                //Toast.makeText(getApplicationContext(),"OTP "+otp,Toast.LENGTH_LONG).show();
            }
        });

    }
}
