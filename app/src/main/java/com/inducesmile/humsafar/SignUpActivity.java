package com.inducesmile.humsafar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

public class SignUpActivity extends AppCompatActivity {

    EditText email,password,confirmPassword, phoneNumber,sid;
    Button signUp;
    private FirebaseAuth mAuth;
    private  FirebaseAuth.AuthStateListener firebaseAuthListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAuth=FirebaseAuth.getInstance();
        firebaseAuthListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
                if(user!=null){
                    Intent intent=new Intent(SignUpActivity.this,PhoneNumberVerification.class);
                    startActivity(intent);
                    finish();
                    return;
                }
            }
        };

        setContentView(R.layout.activity_sign_up);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        confirmPassword=findViewById(R.id.confirmPassword);
        phoneNumber=findViewById(R.id.phoneNumber);
        sid=findViewById(R.id.sid);
        signUp=findViewById(R.id.signUp);


        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String Email=email.getText().toString();
                final String Password=password.getText().toString();
                final String ConfirmPassword=confirmPassword.getText().toString();
                final String PhoneNumber=phoneNumber.getText().toString();
                final String SID=sid.getText().toString();
                if(!Password.equals(ConfirmPassword)){
                    Toast.makeText(getApplicationContext(),"Password does not match",Toast.LENGTH_LONG).show();
                }
                else{
                    mAuth.createUserWithEmailAndPassword(Email,Password).addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(getApplicationContext(),"Error in Sign Up",Toast.LENGTH_LONG).show();
                            }
                            else {
                                String user_id=mAuth.getCurrentUser().getUid();
                                DatabaseReference current_user_db= FirebaseDatabase.getInstance().getReference().child("Users").child(user_id);
                                current_user_db.setValue(true);
                                DatabaseReference currentuser1=FirebaseDatabase.getInstance().getReference().child("Users").child(user_id).child("Email");
                                currentuser1.setValue(Email);
                                DatabaseReference currentuser2=FirebaseDatabase.getInstance().getReference().child("Users").child(user_id).child("Password");
                                currentuser2.setValue(Password);
                                DatabaseReference currentuser3=FirebaseDatabase.getInstance().getReference().child("Users").child(user_id).child("PhoneNumber");
                                currentuser3.setValue(PhoneNumber);
                                DatabaseReference currentuser4=FirebaseDatabase.getInstance().getReference().child("Users").child(user_id).child("StudentIdentityNumber");
                                currentuser4.setValue(SID);


                            }
                        }
                    });
                }

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(firebaseAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAuth.removeAuthStateListener(firebaseAuthListener);
    }
}
