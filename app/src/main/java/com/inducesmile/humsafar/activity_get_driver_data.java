package com.inducesmile.humsafar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

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
import java.util.List;

public class activity_get_driver_data extends AppCompatActivity {

    ListView listview;
    FirebaseDatabase Database;
    DatabaseReference reference;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    Driver driver;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_driver_data);

        driver = new Driver();
        listview = (ListView) findViewById(R.id.driver_data);
        progressBar=findViewById(R.id.driverProgress);

        Database = FirebaseDatabase.getInstance();
        reference = Database.getReference("Driver");
        list = new ArrayList<>();
        adapter = new ArrayAdapter<String>(this, R.layout.driver_info_list, R.id.driverinfo, list);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();
                for(DataSnapshot ds: dataSnapshot.getChildren())
                {

                    driver = ds.getValue(Driver.class);
                    list.add("Name: "+ driver.getdName()+"\n" + "Source: " + driver.getdSource().toString() + "\n" + "Destination: " +driver.getdDestination().toString()+ " ");

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