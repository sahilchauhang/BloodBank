package com.example.sahil.bloodbank;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {
    EditText ph,pass;
    Button login;
FirebaseDatabase database;
    DatabaseReference reference;
    SQLiteDatabase db;
    String pas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ph=(EditText)findViewById(R.id.phone);
        pass=(EditText)findViewById(R.id.password);
        login=(Button)findViewById(R.id.login1);
        database=FirebaseDatabase.getInstance();




        reference=database.getReference("users");
     login.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             try {

                 db = getApplicationContext().openOrCreateDatabase("rec", MODE_APPEND, null);
                 db.execSQL("Create table if not exists login(mobile VARCHAR,pass VARCHAR);");
                 db.execSQL("Insert into login values('" + ph.getText().toString() + "','" + pass.getText().toString() + "');");
             }catch (Exception e){

                 //Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();

             }

             reference.child(ph.getText().toString()).addValueEventListener(new ValueEventListener() {
                 @Override
                 public void onDataChange(DataSnapshot dataSnapshot) {
                     if(pass.getText().toString().equals(dataSnapshot.getValue(artist.class).getPass())){
                         Intent intent=new Intent(getApplicationContext(),Main2Activity.class);
                         startActivity(intent);
                     }
                     else{
                         Toast.makeText(getApplicationContext(),"Invalid Credentials",Toast.LENGTH_LONG).show();
                     }
                 }

                 @Override
                 public void onCancelled(DatabaseError databaseError) {

                 }
             });

         }
     });




    }
}
