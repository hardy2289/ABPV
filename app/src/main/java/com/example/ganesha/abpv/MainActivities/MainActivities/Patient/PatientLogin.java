package com.example.ganesha.abpv.MainActivities.MainActivities.Patient;

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
import com.example.ganesha.abpv.R;
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

/**
 * Created by Ganesha on 11/08/2017.
 */

public class PatientLogin extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener, View.OnClickListener {

    private static final String TAG= "GoogleActivity";
    //  private static final int RC_SIGN_IN=9001;
    private EditText txtEmailPL;
    private EditText txtPasswordPL;
    protected Button btnRegisterPL;
    protected Button btnGoogleLoginPL;
    protected Button btnLoginPL;
    protected Button btnResetPasswordPL;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private GoogleApiClient mGoogleApiClient;
    Boolean isInternetPresent = true;
    ConnectionDetector cd;

    @Override
    protected void onCreate (Bundle savedInstanceState) {

        super.onCreate (savedInstanceState);
        setContentView(R.layout.activity_patient_login);

        cd = new ConnectionDetector(getApplicationContext());
        txtEmailPL=(EditText) findViewById(R.id.edit_txt_email_patient_login);
        txtPasswordPL=(EditText) findViewById(R.id.edit_txt_password_patient_login);
        btnRegisterPL = (Button) findViewById(R.id.btn_patient_register_login);
        btnLoginPL = (Button) findViewById(R.id.btn_patient_login);
        btnGoogleLoginPL = (Button) findViewById(R.id.btn_patient_google_login);
        btnResetPasswordPL = (Button) findViewById(R.id.btn_patient_password_reset_login);
        mAuth = FirebaseAuth.getInstance();

        Log.d(TAG,"InstanceID token: "+ FirebaseInstanceId.getInstance().getToken());

        //Google Login
        GoogleSignInOptions gso=new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id)).requestEmail().build();

        mGoogleApiClient = new GoogleApiClient.Builder(this).addApi(Auth.GOOGLE_SIGN_IN_API,gso).build();


        btnGoogleLoginPL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent UserGoogleLogin = new Intent(PatientLogin.this, GoogleLogin.class);
                startActivity(UserGoogleLogin);
            }
        });
        btnRegisterPL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent UserRegister = new Intent(PatientLogin.this, PatientRegister.class);
                startActivity(UserRegister);
            }
        });
        btnResetPasswordPL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent UserPasswordReset = new Intent(PatientLogin.this, ResetPassword.class);
                startActivity(UserPasswordReset);
            }
        });

        btnLoginPL.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                ConnectionDetector cd;
                cd = new ConnectionDetector(getApplicationContext());
                // get Internet /
                isInternetPresent = cd.isConnectingToInternet();
                // check for Internet status

                if (isInternetPresent) {

                    // Internet connection is not present
                    // Ask user to connect to Internet
                    String email = txtEmailPL.getText().toString().trim();
                    String password = txtPasswordPL.getText().toString().trim();
                    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

                    // check for Internet status
                    if (TextUtils.isEmpty(email)) {
                        Toast.makeText(PatientLogin.this, "Enter email address!", Toast.LENGTH_SHORT).show();
                    } else if (!email.matches(emailPattern)) {
                        Toast.makeText(PatientLogin.this, "Invalid email address", Toast.LENGTH_SHORT).show();
                    } else if (TextUtils.isEmpty(password)) {
                        Toast.makeText(PatientLogin.this, "Enter password!", Toast.LENGTH_SHORT).show();
                    } else if (password.length() < 6) {
                        Toast.makeText(PatientLogin.this, "Password too short!", Toast.LENGTH_SHORT).show();
                    } else if ((email.matches("doctor1@gmail.com") || email.matches("doctor2@gmail.com")) || ((email.matches("doctor3@gmail.com")) || (email.matches("doctor4@gmail.com"))) || (email.matches("doctor5@gmail.com") || email.matches("doctor6@gmail.com")) || ((email.matches("doctor7@gmail.com")) || (email.matches("doctor8@gmail.com")))) {
                        Toast.makeText(PatientLogin.this, "Access Denied!", Toast.LENGTH_SHORT).show();
                    } else {
                        mAuth.signInWithEmailAndPassword(email, password)
                                .addOnCompleteListener(PatientLogin.this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());
                                        if (task.isSuccessful()) {
                                            Intent UserHomepage = new Intent(PatientLogin.this, Navigation.class);
                                            startActivity(UserHomepage);
                                        } else if (!task.isSuccessful()) {
                                            Log.w(TAG, "signInWithEmail", task.getException());
                                            Toast.makeText(PatientLogin.this, "Authentication failed.",
                                                    Toast.LENGTH_SHORT).show();
                                            Intent UserHomepage = new Intent(PatientLogin.this, PatientLogin.class);
                                            startActivity(UserHomepage);
                                        }

                                    }
                                });
                    }

                     }else
                {
                    Toast.makeText(PatientLogin.this, "No Internet Connection,Please check your internet connection.", Toast.LENGTH_SHORT).show();

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
                    Toast.makeText(PatientLogin.this, "Welcome " + user.getEmail(), Toast.LENGTH_LONG).show();
                    Intent UserPasswordReset = new Intent(PatientLogin.this, Navigation.class);
                    startActivity(UserPasswordReset);

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
            //startActivity(new Intent(PatientLogin.this, AddDetailsPHome.class));
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
