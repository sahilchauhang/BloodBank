package com.example.sahil.bloodbank;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Find extends AppCompatActivity {
    RecyclerView recyclerView;
    FirebaseDatabase database;
    DatabaseReference reference;
    ArrayList<String> names,mobile,city;
    String blood,state,city2;
    Myadapter myadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find);
        recyclerView=(RecyclerView)findViewById(R.id.rec);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        names = new ArrayList<>();
        mobile = new ArrayList<>();
        city = new ArrayList<>();
        blood=getIntent().getStringExtra("blood");
        state=getIntent().getStringExtra("state");
        city2=getIntent().getStringExtra("city");
        database=FirebaseDatabase.getInstance();
        reference=database.getReference("available");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                names.clear();
                mobile.clear();
                city.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    if (blood.equals(snapshot.getValue(artist.class).getBlood())&&state.equals(snapshot.getValue(artist.class).getState())&&city2.equals(snapshot.getValue(artist.class).getCity())) {
                        names.add(snapshot.getValue(artist.class).getName());
                        mobile.add(snapshot.getValue(artist.class).getPhone());
                        city.add(snapshot.getValue(artist.class).getCity());
                    }

                }
               /* if(names.isEmpty()&& mobile.isEmpty()&& city.isEmpty()){

                        AlertDialog.Builder builder=new AlertDialog.Builder(Find.this);
                        builder.setTitle("No Record");
                        builder.setMessage("No Person Found");
                        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent=new Intent(getApplicationContext(),Main2Activity.class);
                                startActivity(intent);
                            }
                        }).show();
                    //Toast.makeText(getApplicationContext(), "Record Not Found.", Toast.LENGTH_LONG).show();

                }*/
                myadapter=new Myadapter(names,mobile,city);
                recyclerView.setAdapter(myadapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {



            }
        });

    }
}
