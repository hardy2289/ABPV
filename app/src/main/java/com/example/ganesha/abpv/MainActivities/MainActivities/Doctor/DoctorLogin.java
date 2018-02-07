package com.example.ganesha.abpv.MainActivities.MainActivities.Doctor;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ganesha.abpv.MainActivities.MainActivities.ConnectionDetector;
import com.example.ganesha.abpv.MainActivities.MainActivities.Patient.GoogleLogin;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.iid.FirebaseInstanceId;


public class DoctorLogin extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener, View.OnClickListener{

    private static final String TAG= "GoogleActivity";
    private EditText txtEmailDL;
    private EditText txtPasswordDL;
    private Button btnDoctorLoginDL;
    private Button btnResetPasswordDL;
    private Button btnGoogleLoginDL;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private GoogleApiClient mGoogleApiClient;
    ConnectionDetector cd;
    Boolean isInternetPresent = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.ganesha.abpv.R.layout.activity_doctor_login);

        cd = new ConnectionDetector(getApplicationContext());
        txtEmailDL=(EditText) findViewById(com.example.ganesha.abpv.R.id.editDoctorEmailLogin);
        txtEmailDL.requestFocus();
        txtPasswordDL=(EditText) findViewById(com.example.ganesha.abpv.R.id.edit_doctor_password);
        btnDoctorLoginDL=(Button) findViewById(com.example.ganesha.abpv.R.id.btn_doctor_login);
        btnGoogleLoginDL=(Button) findViewById(com.example.ganesha.abpv.R.id.btn_doctor_google_login);
        btnResetPasswordDL=(Button) findViewById(com.example.ganesha.abpv.R.id.btn_doctor_password_reset);
        mAuth = FirebaseAuth.getInstance();

        Log.d(TAG,"InstanceID token: "+ FirebaseInstanceId.getInstance().getToken());

        GoogleSignInOptions gso=new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(com.example.ganesha.abpv.R.string.default_web_client_id)).requestEmail().build();

        mGoogleApiClient = new GoogleApiClient.Builder(this).addApi(Auth.GOOGLE_SIGN_IN_API,gso).build();

        btnGoogleLoginDL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent UserGoogleLogin = new Intent(DoctorLogin.this, GoogleLogin.class);
                startActivity(UserGoogleLogin);
            }
        });


        btnResetPasswordDL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent UserPasswordReset = new Intent(DoctorLogin.this, DoctorResetPassword.class);
                startActivity(UserPasswordReset);
            }
        });

        btnDoctorLoginDL.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){


               ConnectionDetector cd;

                cd = new ConnectionDetector(getApplicationContext());
                // get Internet status
                isInternetPresent = cd.isConnectingToInternet();
                // check for Internet status

                if (isInternetPresent) {



                String email = txtEmailDL.getText().toString().trim();
                String password = txtPasswordDL.getText().toString().trim();
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(DoctorLogin.this, "Enter email address!", Toast.LENGTH_SHORT).show();
                }
                else if (!email.matches(emailPattern)){
                    Toast.makeText(DoctorLogin.this, "Invalid email address", Toast.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(password)) {
                    Toast.makeText(DoctorLogin.this, "Enter password!", Toast.LENGTH_SHORT).show();
                }
                else if (password.length() < 6) {
                    Toast.makeText(DoctorLogin.this, "Password too short!", Toast.LENGTH_SHORT).show();
                }


                else
                {
                    if ((email.matches("doctor1@gmail.com")||email.matches("doctor2@gmail.com"))|| ((email.matches("doctor3@gmail.com"))||(email.matches("doctor4@gmail.com")))||(email.matches("doctor5@gmail.com")||email.matches("doctor6@gmail.com"))|| ((email.matches("doctor7@gmail.com"))||(email.matches("doctor8@gmail.com")))) {


                    mAuth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(DoctorLogin.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());

                                    if (task.isSuccessful()) {

                                        Intent UserHomepage = new Intent(DoctorLogin.this, NavigationDoctor.class);
                                        startActivity(UserHomepage);
                                    } else if (!task.isSuccessful()) {

                                        Log.w(TAG, "signInWithEmail", task.getException());
                                        Toast.makeText(DoctorLogin.this, "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();
                                        Intent UserHomepage = new Intent(DoctorLogin.this, DoctorLogin.class);
                                        startActivity(UserHomepage);
                                    }

                                }
                            });}
                    else
                    {
                        Toast.makeText(DoctorLogin.this, "Access Denied!", Toast.LENGTH_SHORT).show();
                    }
                }

           }
                else
                {
                    Toast.makeText(DoctorLogin.this, "No Internet Connection,Please check your internet connection.", Toast.LENGTH_SHORT).show();

                }

            }

        });

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                    Toast.makeText(DoctorLogin.this, "Welcome " + user.getEmail(), Toast.LENGTH_LONG).show();
                    Intent takeUserResetPassword = new Intent(DoctorLogin.this, NavigationDoctor.class);
                    startActivity(takeUserResetPassword);

                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };


    }
    @Override
    public void onStart() {
        super.onStart();
        if (mAuth.getCurrentUser() != null) {
          // startActivity(new Intent(DoctorLogin.this, DoctorHome.class));
           // finish();

        }


    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
        this.finish();
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d(TAG, "onConnectionFailed:" + connectionResult);
        Toast.makeText(this, "Google Play Services error.", Toast.LENGTH_SHORT).show();
    }

}
