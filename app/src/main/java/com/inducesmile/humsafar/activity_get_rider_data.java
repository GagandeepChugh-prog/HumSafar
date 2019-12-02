package com.inducesmile.humsafar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class activity_get_rider_data extends AppCompatActivity {
    ListView listview;
    FirebaseDatabase Database;
    DatabaseReference reference;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    Rider rider;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_rider_data);

        rider = new Rider();
        listview = (ListView) findViewById(R.id.rider_data);
        progressBar=findViewById(R.id.riderProgressBar);
        Database = FirebaseDatabase.getInstance();
        reference = Database.getReference("Rider");
        list = new ArrayList<>();
        adapter = new ArrayAdapter<String>(this, R.layout.rider_info_list, R.id.riderinfo, list);


        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();
                for(DataSnapshot ds: dataSnapshot.getChildren())
                {

                    rider = ds.getValue(Rider.class);
                    list.add("Name: "+ rider.getrName()+"\n" + "Source: " + rider.getrSource().toString() + "\n" + "Destination: " +rider.getrDestination().toString()+ " ");

                }

                progressBar.setVisibility(View.GONE);
                listview.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}