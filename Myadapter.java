package com.example.sahil.bloodbank;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static android.content.Context.LOCATION_SERVICE;
import static android.content.Context.MODE_APPEND;


public class Myadapter extends RecyclerView.Adapter<Myadapter.CustomHolder> {

    ArrayList<String> names, mobile, city;

    FirebaseDatabase database;
    DatabaseReference reference, ref;

    public Myadapter(ArrayList<String> names, ArrayList<String> mobile, ArrayList<String> city) {

        this.names = names;
        this.mobile = mobile;
        this.city = city;
    }

    @Override
    public CustomHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter, parent, false);

        CustomHolder customHolder = new CustomHolder(view);
        return customHolder;
    }

    @Override
    public void onBindViewHolder(final CustomHolder holder, final int position) {

        holder.name.setText(names.get(position));
        holder.phone.setText(mobile.get(position));
        holder.city.setText(city.get(position));

    }

    @Override
    public int getItemCount() {
        return names.size();
    }

    public class CustomHolder extends RecyclerView.ViewHolder {
        TextView name, phone, city;
        ImageView img;

        public CustomHolder(final View itemView) {
            super(itemView);

            img = (ImageView) itemView.findViewById(R.id.call);
            name = (TextView) itemView.findViewById(R.id.name);
            phone = (TextView) itemView.findViewById(R.id.mobile);
            city = (TextView) itemView.findViewById(R.id.city);

            img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                     Context context = itemView.getContext();
                    //Toast.makeText(context,"hello",Toast.LENGTH_LONG).show();

                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.parse("tel:"+phone.getText()));
                    if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        return;
                    }
                    context.startActivity(callIntent);
                }
            });

        }
}}
