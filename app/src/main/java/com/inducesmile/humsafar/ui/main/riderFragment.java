package com.inducesmile.humsafar.ui.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.inducesmile.humsafar.LandingPageActivity;
import com.inducesmile.humsafar.MainActivity;
import com.inducesmile.humsafar.R;
import com.inducesmile.humsafar.SignUpActivity;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link riderFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link riderFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class riderFragment extends Fragment {

    EditText riderSource,riderDest,riderDate,riderTime,riderSeat;
    Button SearchRides,LogOuts;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;

    private static final String TAG = "Rider";
    private PageViewModel pageViewModel;

    //private OnFragmentInteractionListener mListener;

    public riderFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment riderFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static riderFragment newInstance() {
        riderFragment fragment = new riderFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
        //pageViewModel.setIndex(TAG);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_rider, container, false );


        riderSource=root.findViewById(R.id.riderSource);
        riderDest=root.findViewById(R.id.riderDest);
        riderDate=root.findViewById(R.id.riderDate);
        riderTime=root.findViewById(R.id.riderTime);
//        riderSeat=root.findViewById(R.id.driverSeats);
        SearchRides=root.findViewById(R.id.SearchRide);
        LogOuts=root.findViewById(R.id.logOut);

        mAuth= FirebaseAuth.getInstance();




        SearchRides.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String riderSources=riderSource.getText().toString();
                final String riderDests=riderDest.getText().toString();
                final String riderTimes=riderTime.getText().toString();
              //  final String riderSeats=riderSeat.getText().toString();
                final String riderDates=riderDate.getText().toString();


                if(riderSource.getText().toString().length()==0){
                    Toast.makeText(getActivity(),"Please Enter Source",Toast.LENGTH_LONG).show();
                }
                if(riderDest.getText().toString().length()==0){
                    Toast.makeText(getActivity(),"Please Enter Destination",Toast.LENGTH_LONG).show();
                }
                if(riderTime.getText().toString().length()==0){
                    Toast.makeText(getActivity(),"Please Enter Time",Toast.LENGTH_LONG).show();
                }
//                if(riderSeat.getText().toString().length()==0){
//                    Toast.makeText(getActivity(),"Please enter Seats",Toast.LENGTH_LONG).show();
//                }
                if(riderDate.getText().toString().length()==0){
                    Toast.makeText(getActivity(),"Please enter Date",Toast.LENGTH_LONG).show();
                }
                else{
                    String user_id=mAuth.getCurrentUser().getUid();
                    DatabaseReference current_user_db= FirebaseDatabase.getInstance().getReference().child("Rider").child(user_id);
                    current_user_db.setValue(true);
                    DatabaseReference currentuser1=FirebaseDatabase.getInstance().getReference().child("Rider").child(user_id).child("rSource");
                    currentuser1.setValue(riderSources);
                    DatabaseReference currentuser2=FirebaseDatabase.getInstance().getReference().child("Rider").child(user_id).child("rDestination");
                    currentuser2.setValue(riderDests);
                    DatabaseReference currentuser3=FirebaseDatabase.getInstance().getReference().child("Rider").child(user_id).child("rTime");
                    currentuser3.setValue(riderTimes);
//                    DatabaseReference currentuser4=FirebaseDatabase.getInstance().getReference().child("Rider").child(user_id).child("StudentIdentityNumber");
//                    currentuser4.setValue(driverSeats);
                    DatabaseReference currentuser5=FirebaseDatabase.getInstance().getReference().child("Rider").child(user_id).child("rDate");
                    currentuser5.setValue(riderDates);
                    DatabaseReference currentuser6=FirebaseDatabase.getInstance().getReference().child("Users").child(user_id).child("CurrentRider");
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