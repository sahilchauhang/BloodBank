package com.example.sahil.bloodbank;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class second extends Fragment {

Spinner s1,s2,s3;
    RecyclerView recyclerView;
    FirebaseDatabase database;
    DatabaseReference reference;
    ArrayList<String> names,mobile,city;
    Button b;
    SQLiteDatabase db;
    Myadapter myadapter;
    ArrayList<String> blood,state,city2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        final View v= inflater.inflate(R.layout.fragment_second,container,false);
        s1=(Spinner)v.findViewById(R.id.spinnerblood);
        s2=(Spinner)v.findViewById(R.id.spinnerstate);
        s3=(Spinner)v.findViewById(R.id.spinnercity);
        blood=new ArrayList<>();
        state=new ArrayList<>();
        city2=new ArrayList<>();
        b=(Button)v.findViewById(R.id.find);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db = getActivity().openOrCreateDatabase("rec", Context.MODE_APPEND, null);
                database=FirebaseDatabase.getInstance();
                reference=database.getReference("available");
                Cursor cursor=db.rawQuery("Select * from login;",null);
                cursor.moveToLast();
               reference.addValueEventListener(new ValueEventListener() {
                   @Override
                   public void onDataChange(DataSnapshot dataSnapshot) {
                       blood.clear();
                       state.clear();
                       city2.clear();
                       for(DataSnapshot snapshot:dataSnapshot.getChildren()) {
                           if(s1.getSelectedItem().toString().equals(snapshot.getValue(artist.class).getBlood())&&s2.getSelectedItem().toString().equals(snapshot.getValue(artist.class).getState())&&s3.getSelectedItem().toString().equals(snapshot.getValue(artist.class).getCity())){

                               blood.add(dataSnapshot.getValue(artist.class).getBlood());
                               state.add(dataSnapshot.getValue(artist.class).getState());
                               city2.add(dataSnapshot.getValue(artist.class).getCity());
                           }
                       }
                       if(blood.isEmpty()&&state.isEmpty()&&city2.isEmpty())
                       {
                           //Toast.makeText(getActivity(),"Record Not Found",Toast.LENGTH_LONG).show();


                        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
                        builder.setTitle("No Record");
                        builder.setMessage("No Person Found");
                        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent=new Intent(getActivity(),Main2Activity.class);
                                startActivity(intent);
                            }
                        }).show();
                    //Toast.makeText(getApplicationContext(), "Record Not Found.", Toast.LENGTH_LONG).show();

                }

                       else
                       {
                          Intent intent=new Intent(getActivity(),Find.class);
                           intent.putExtra("blood",s1.getSelectedItem().toString());
                             intent.putExtra("state",s2.getSelectedItem().toString());
                           intent.putExtra("city",s3.getSelectedItem().toString());

                          startActivity(intent);
                       }
                   }

                   @Override
                   public void onCancelled(DatabaseError databaseError) {

                   }
               });



            }
        });
        try {
            names = new ArrayList<>();
            mobile = new ArrayList<>();
            city = new ArrayList<>();
            s2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    adapterView.getItemAtPosition(i);

                    if (i == 0) {
                        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource
                                (getActivity(), R.array.city_haryana, android.R.layout.simple_spinner_item);
                        s3.setAdapter(adapter);


                    }
                    if (i == 1) {
                        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource
                                (getActivity(), R.array.city_uttrakhand, android.R.layout.simple_spinner_item);
                        s3.setAdapter(adapter);


                    }
                    if (i == 2) {
                        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource
                                (getActivity(), R.array.city_chandigarh, android.R.layout.simple_spinner_item);
                        s3.setAdapter(adapter);
                    }
                    if (i == 3) {
                        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource
                                (getActivity(), R.array.city_delhi, android.R.layout.simple_spinner_item);
                        s3.setAdapter(adapter);

                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
            database = FirebaseDatabase.getInstance();
            reference = database.getReference("users");
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    names.clear();
                    mobile.clear();
                    city.clear();



                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }catch (Exception e){
            Toast.makeText(getActivity(),e.getMessage(),Toast.LENGTH_LONG).show();
        }

        return v;
    }

}
