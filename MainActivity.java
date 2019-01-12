package com.example.sahil.bloodbank;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Intent int1,int2;
    Button btn1,btn2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1=(Button)findViewById(R.id.loginbtn);
        btn2=(Button)findViewById(R.id.registerbtn);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    SQLiteDatabase sqLiteDatabase = getApplicationContext().openOrCreateDatabase("rec", MODE_APPEND, null);
                    sqLiteDatabase.execSQL("Create table if not exists login(mobile VARCHAR,pass VARCHAR);");
                    Cursor cursor = sqLiteDatabase.rawQuery("Select * from login;", null);
                    cursor.moveToLast();
                    if(cursor.getString(0).equals("")){
                    int1 = new Intent(getApplicationContext(), Login.class);
                    startActivity(int1);}
                    else{
                        Intent intent=new Intent(getApplicationContext(),Main2Activity.class);
                        startActivity(intent);
                    }


                }catch(Exception e){

                    int1 = new Intent(getApplicationContext(), Login.class);
                    startActivity(int1);
                    //Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();

                }
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int2 = new Intent(getApplicationContext(),Register.class);
                startActivity(int2);
            }
        });

    }
}
