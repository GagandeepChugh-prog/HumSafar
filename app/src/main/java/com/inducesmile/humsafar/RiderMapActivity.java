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

public class RiderMapActivity extends FragmentActivity implements OnMapReadyCallback {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;

    Button confirmButtonRider;

    private GoogleMap mMap;
    LatLng sourceCoordinator,destinationCoordinator;

    String userName="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rider_map);


        confirmButtonRider=findViewById(R.id.ConfirmRiderCoordinates);

        mAuth= FirebaseAuth.getInstance();

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        final String rSource=getIntent().getStringExtra("rSource");
        final String rDestination=getIntent().getStringExtra("rDestination");
        final String rDate=getIntent().getStringExtra("rDate");
        final String rTime=getIntent().getStringExtra("rTime");
        userName=getIntent().getStringExtra("rName");
        LocationChecker locationChecker=new LocationChecker();
        Double[] sourceArray=locationChecker.getLocationFromAddress(RiderMapActivity.this,rSource);
        Double[] destinationArray=locationChecker.getLocationFromAddress(RiderMapActivity.this,rDestination);
        sourceCoordinator = new LatLng(sourceArray[0],sourceArray[1]);
        destinationCoordinator = new LatLng(destinationArray[0],destinationArray[1]);

        confirmButtonRider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user_id=mAuth.getCurrentUser().getUid();
                DatabaseReference current_user_db= FirebaseDatabase.getInstance().getReference().child("Rider").child(user_id);
                current_user_db.setValue(true);
                DatabaseReference currentuser1=FirebaseDatabase.getInstance().getReference().child("Rider").child(user_id).child("rSource");
                currentuser1.setValue(rSource);
                DatabaseReference currentuser2=FirebaseDatabase.getInstance().getReference().child("Rider").child(user_id).child("rDestination");
                currentuser2.setValue(rDestination);
                DatabaseReference currentuser3=FirebaseDatabase.getInstance().getReference().child("Rider").child(user_id).child("rTime");
                currentuser3.setValue(rTime);
//                    DatabaseReference currentuser4=FirebaseDatabase.getInstance().getReference().child("Rider").child(user_id).child("StudentIdentityNumber");
//                    currentuser4.setValue(driverSeats);
                DatabaseReference currentuser5=FirebaseDatabase.getInstance().getReference().child("Rider").child(user_id).child("rDate");
                currentuser5.setValue(rDate);
                DatabaseReference currentuser6=FirebaseDatabase.getInstance().getReference().child("Users").child(user_id).child("CurrentRider");
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

                DatabaseReference currentuser7=FirebaseDatabase.getInstance().getReference().child("Rider").child(user_id).child("rName");
                currentuser7.setValue(userName);

                Intent intent=new Intent(RiderMapActivity.this,activity_get_driver_data.class);
                startActivity(intent);

            }
        });
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
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
