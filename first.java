package com.example.sahil.bloodbank;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.github.ksoichiro.android.observablescrollview.ObservableListView;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class first extends Fragment {


    SQLiteDatabase db;
    FirebaseDatabase database;
    DatabaseReference reference;
    String name,blood,state,city,phone,pass,gender;
    TextView t1;
    Button btn;
    artist art;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View v=inflater.inflate(R.layout.fragment_first, container, false);
        t1=(TextView)v.findViewById(R.id.name);
        btn =(Button)v.findViewById(R.id.donate);
        db = getActivity().openOrCreateDatabase("rec", Context.MODE_APPEND, null);
        database=FirebaseDatabase.getInstance();
        reference=database.getReference("users");
        Cursor cursor=db.rawQuery("Select * from login;",null);
        cursor.moveToLast();
        //Toast.makeText(getActivity(), cursor.getString(0),Toast.LENGTH_LONG).show();
        reference.child(cursor.getString(0)).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                name = dataSnapshot.getValue(artist.class).getName();
                blood = dataSnapshot.getValue(artist.class).getBlood();
                state = dataSnapshot.getValue(artist.class).getState();
                city = dataSnapshot.getValue(artist.class).getCity();
                pass = dataSnapshot.getValue(artist.class).getPass();
                gender = dataSnapshot.getValue(artist.class).getGender();
                phone = dataSnapshot.getValue(artist.class).getPhone();


                t1.setText("Welcome, "+name);
               // Toast.makeText(getActivity(),name + blood + state + city,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"Congratulations! You are a Blood Donor",Toast.LENGTH_LONG).show();
                db = getActivity().openOrCreateDatabase("rec", Context.MODE_APPEND, null);
                Cursor cursor=db.rawQuery("Select * from login;",null);
                cursor.moveToLast();
                database=FirebaseDatabase.getInstance();
                reference=database.getReference("available").child(cursor.getString(0));
                art=new artist(name,phone,pass,gender,blood,state,city);
                reference.setValue(art);
            }
        });


        return  v;
    }

}
