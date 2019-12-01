package com.inducesmile.humsafar.ui.main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.inducesmile.humsafar.MainActivity;
import com.inducesmile.humsafar.PhoneNumberVerification;
import com.inducesmile.humsafar.R;
import com.inducesmile.humsafar.SignUpActivity;


///**
// * A simple {@link Fragment} subclass.
// * Activities that contain this fragment must implement the
// * {@link driverFragment.OnFragmentInteractionListener} interface
// * to handle interaction events.
// * Use the {@link driverFragment#newInstance} factory method to
// * create an instance of this fragment.
// */

public class driverFragment extends Fragment {

    EditText driverSource,driverDest,driverDate,driverTime,driverSeat;
    Button OfferRide,LogOuts;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;


    private PageViewModel pageViewModel;
    public driverFragment() {
        // Required empty public constructor
    }


    public static driverFragment newInstance() {
        driverFragment fragment = new driverFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);




    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_driver, container, false);
        driverSource=root.findViewById(R.id.driverSource);
        driverDest=root.findViewById(R.id.driverDest);
        driverDate=root.findViewById(R.id.driverDate);
        driverTime=root.findViewById(R.id.driverTime);
        driverSeat=root.findViewById(R.id.driverSeats);
        OfferRide=root.findViewById(R.id.OfferRide);
        LogOuts=root.findViewById(R.id.logOut);

        mAuth=FirebaseAuth.getInstance();




        OfferRide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String driverSources=driverSource.getText().toString();
                final String driverDests=driverDest.getText().toString();
                final String driverTimes=driverTime.getText().toString();
                final String driverSeats=driverSeat.getText().toString();
                final String driverDates=driverDate.getText().toString();


                if(driverSource.getText().toString().length()==0){
                    Toast.makeText(getActivity(),"Please Enter Source",Toast.LENGTH_LONG).show();
                }
                if(driverDest.getText().toString().length()==0){
                    Toast.makeText(getActivity(),"Please Enter Destination",Toast.LENGTH_LONG).show();
                }
                if(driverTime.getText().toString().length()==0){
                    Toast.makeText(getActivity(),"Please Enter Time",Toast.LENGTH_LONG).show();
                }
                if(driverSeat.getText().toString().length()==0){
                    Toast.makeText(getActivity(),"Please enter Seats",Toast.LENGTH_LONG).show();
                }
                if(driverDate.getText().toString().length()==0){
                    Toast.makeText(getActivity(),"Please enter Date",Toast.LENGTH_LONG).show();
                }
                else{
                    String user_id=mAuth.getCurrentUser().getUid();
                    DatabaseReference current_user_db= FirebaseDatabase.getInstance().getReference().child("Driver").child(user_id);
                    current_user_db.setValue(true);
                    DatabaseReference currentuser1=FirebaseDatabase.getInstance().getReference().child("Driver").child(user_id).child("Source");
                    currentuser1.setValue(driverSources);
                    DatabaseReference currentuser2=FirebaseDatabase.getInstance().getReference().child("Driver").child(user_id).child("Destination");
                    currentuser2.setValue(driverDests);
                    DatabaseReference currentuser3=FirebaseDatabase.getInstance().getReference().child("Driver").child(user_id).child("Time");
                    currentuser3.setValue(driverTimes);
                    DatabaseReference currentuser4=FirebaseDatabase.getInstance().getReference().child("Driver").child(user_id).child("Seats");
                    currentuser4.setValue(driverSeats);
                    DatabaseReference currentuser5=FirebaseDatabase.getInstance().getReference().child("Driver").child(user_id).child("Date");
                    currentuser5.setValue(driverDates);
                    DatabaseReference currentuser6=FirebaseDatabase.getInstance().getReference().child("Users").child(user_id).child("CurrentDriver");
                    currentuser6.setValue("True");

                    Intent intent=new Intent(getActivity(), SignUpActivity.class);
                    startActivity(intent);
                    ((Activity) getActivity()).overridePendingTransition(0, 0);
                }

            }
        });
        LogOuts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent=new Intent(getActivity(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);

                startActivity(intent);
            }
        });
        return root;
    }

}
