package com.example.sahil.bloodbank;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ArrayAdapter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.net.HttpURLConnection;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.ref.Reference;

public class Register extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    artist art;
    AutoCompleteTextView et1,et2;
    EditText et3;
    RadioButton r1,r2;
    Spinner s1,s2,s3;
    Button btn1;
    RadioGroup radioGroup;
    RadioButton genderradioButton;
   // FirebaseDatabase data;
   // DatabaseReference ref;
    String city,state;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);
        radioGroup=(RadioGroup)findViewById(R.id.radioGroup);
        et1=(AutoCompleteTextView)findViewById(R.id.name);
        et2=(AutoCompleteTextView)findViewById(R.id.phone);
        et3=(EditText)findViewById(R.id.password);
        r1=(RadioButton)findViewById(R.id.male);
        r2=(RadioButton)findViewById(R.id.female);
        s1=(Spinner)findViewById(R.id.spinnerblood);
        s2=(Spinner)findViewById(R.id.spinnerstate);
        s3=(Spinner)findViewById(R.id.spinnercity);
        btn1=(Button)findViewById(R.id.registerbtn);
       // data =FirebaseDatabase.getInstance();
       // ref=data.getReference("users").child(et2.getText().toString());

            s2.setOnItemSelectedListener(this);
            btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int otp = (int) Math.floor(Math.random() * 9000 + 1000);
                    String name = et1.getText().toString();
                    String phone = et2.getText().toString();
                    String pass = et3.getText().toString();
                    String blood = s1.getSelectedItem().toString();
                    state = s2.getSelectedItem().toString();
                    city = s3.getSelectedItem().toString();
                    int selectedId = radioGroup.getCheckedRadioButtonId();
                    genderradioButton = (RadioButton) findViewById(selectedId);

                        if (!name.isEmpty() && !phone.isEmpty() && !pass.isEmpty() && radioGroup.getCheckedRadioButtonId() != -1) {
                            Bundle bundle = new Bundle();
                            bundle.putString("name", name);
                            bundle.putString("phone", phone);
                            bundle.putString("pass", pass);
                            bundle.putString("gender", genderradioButton.getText().toString());
                            bundle.putString("blood", blood);
                            bundle.putString("state", state);
                            bundle.putString("city", city);
                            bundle.putString("otp", String.valueOf(otp));
                            intent = new Intent(getApplicationContext(), Otp.class);
                            intent.putExtras(bundle);
                            startActivity(intent);

                            Toast.makeText(getApplicationContext(), "OTP: " +otp, Toast.LENGTH_LONG).show();

                /*art=new artist(name,phone,pass,genderradioButton.getText().toString(),blood,state,city);
                ref.child(phone).setValue(art);

                Toast.makeText
                        (getApplicationContext(),"Name is " + name +"\nPhone no. is " + phone +
                                "\nPassword is " + pass,Toast.LENGTH_LONG).show();
                if(selectedId==-1){
                    Toast.makeText(Register.this,"Nothing selected", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(Register.this,genderradioButton.getText(), Toast.LENGTH_SHORT).show();
                }
                Toast.makeText(getApplicationContext(),"your blood group is "+ blood, Toast.LENGTH_LONG).show();*/
                        } else {
                            Toast.makeText(getApplicationContext(), "Please fill all the fields.", Toast.LENGTH_LONG).show();
                        }


                }
            });


    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            adapterView.getItemAtPosition(i);

            if (i == 0) {
                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource
                        (this, R.array.city_haryana, android.R.layout.simple_spinner_item);
                s3.setAdapter(adapter);


            }
            if (i == 1) {
                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource
                        (this, R.array.city_uttrakhand, android.R.layout.simple_spinner_item);
                s3.setAdapter(adapter);


            }
            if (i == 2) {
                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource
                        (this, R.array.city_chandigarh, android.R.layout.simple_spinner_item);
                s3.setAdapter(adapter);
            }
            if (i == 3) {
                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource
                        (this, R.array.city_delhi, android.R.layout.simple_spinner_item);
                s3.setAdapter(adapter);

            }


    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
