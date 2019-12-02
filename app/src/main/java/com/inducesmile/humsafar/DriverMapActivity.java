package com.inducesmile.humsafar;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DriverMapActivity extends FragmentActivity implements OnMapReadyCallback {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;

    Button confirmButton;

    private GoogleMap mMap;
    LatLng sourceCoordinator,destinationCoordinator;
    String userName="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_map);

        confirmButton=findViewById(R.id.ConfirmCoordinates);

        mAuth=FirebaseAuth.getInstance();

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        LocationChecker locationChecker=new LocationChecker();
        final String dSource=getIntent().getStringExtra("dSource");
        final String dDestination=getIntent().getStringExtra("dDestination");
        final String dDate=getIntent().getStringExtra("dDate");
        final String dTime=getIntent().getStringExtra("dTime");
        final String dSeat=getIntent().getStringExtra("dSeat");

        Double[] sourceArray=locationChecker.getLocationFromAddress(DriverMapActivity.this,dSource);
        Double[] destinationArray=locationChecker.getLocationFromAddress(DriverMapActivity.this,dDestination);
        sourceCoordinator = new LatLng(sourceArray[0],sourceArray[1]);
        destinationCoordinator = new LatLng(destinationArray[0],destinationArray[1]);




        mapFragment.getMapAsync(this);

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user_id=mAuth.getCurrentUser().getUid();
                DatabaseReference current_user_db= FirebaseDatabase.getInstance().getReference().child("Driver").child(user_id);
                current_user_db.setValue(true);
                DatabaseReference currentuser1=FirebaseDatabase.getInstance().getReference().child("Driver").child(user_id).child("dSource");
                currentuser1.setValue(dSource);
                DatabaseReference currentuser2=FirebaseDatabase.getInstance().getReference().child("Driver").child(user_id).child("dDestination");
                currentuser2.setValue(dDestination);
                DatabaseReference currentuser3=FirebaseDatabase.getInstance().getReference().child("Driver").child(user_id).child("dTime");
                currentuser3.setValue(dTime);
                DatabaseReference currentuser4=FirebaseDatabase.getInstance().getReference().child("Driver").child(user_id).child("dSeats");
                currentuser4.setValue(dSeat);
                DatabaseReference currentuser5=FirebaseDatabase.getInstance().getReference().child("Driver").child(user_id).child("dDate");
                currentuser5.setValue(dDate);
                DatabaseReference currentuser6=FirebaseDatabase.getInstance().getReference().child("Users").child(user_id).child("CurrentDriver");
                currentuser6.setValue("True");

                //to get name of the current user.
                DatabaseReference getNameOfUser=FirebaseDatabase.getInstance().getReference().child("Users").child(user_id);

                getNameOfUser.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        userName=dataSnapshot.child("Name").getValue().toString();

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                DatabaseReference currentuser7=FirebaseDatabase.getInstance().getReference().child("Driver").child(user_id).child("dName");
                currentuser7.setValue(userName);

                Intent intent=new Intent(DriverMapActivity.this,activity_get_rider_data.class);
                startActivity(intent);

            }
        });
    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        //LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sourceCoordinator).title("Source").icon(BitmapDescriptorFactory
                .defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sourceCoordinator));
        mMap.addMarker(new MarkerOptions().position(destinationCoordinator).title("Destination"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(destinationCoordinator));


    }
}
