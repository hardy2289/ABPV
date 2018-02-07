package com.example.ganesha.abpv.MainActivities.MainActivities.Patient;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ganesha.abpv.MainActivities.MainActivities.ConnectionDetector;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ResetPassword extends PatientLogin {


    private static final String TAG="EmailPassword";
    private Button btnRestPasswordRP;
    private Button btnLoginRP;
    private EditText txtEmailRP;
    ConnectionDetector cd;

    public ResetPassword() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.ganesha.abpv.R.layout.activity_reset_password);
        cd = new ConnectionDetector(getApplicationContext());
        btnRestPasswordRP=(Button) findViewById(com.example.ganesha.abpv.R.id.btn_patient_password_reset_FP);
        btnLoginRP=(Button) findViewById(com.example.ganesha.abpv.R.id.btn_patient_login_FP);
        txtEmailRP=(EditText) findViewById(com.example.ganesha.abpv.R.id.edit_txt_email_FP);
        btnRestPasswordRP.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                ConnectionDetector cd;

                cd = new ConnectionDetector(getApplicationContext());
                // get Internet status
                isInternetPresent = cd.isConnectingToInternet();
                // check for Internet status
                if (isInternetPresent) {


                String email = txtEmailRP.getText().toString().trim();
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                FirebaseAuth auth = FirebaseAuth.getInstance();
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(ResetPassword.this, "Enter email address!", Toast.LENGTH_SHORT).show();}
                else if (!email.matches(emailPattern)){
                    Toast.makeText(ResetPassword.this, "Invalid email address", Toast.LENGTH_SHORT).show();}
                else
                    { auth.sendPasswordResetEmail(email)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Log.d(TAG, "Email sent.");
                                            Intent takeUserHome = new Intent(ResetPassword.this, PatientLogin.class);
                                            startActivity(takeUserHome);
                                            Toast.makeText(ResetPassword.this, "Please Reset Your Password Through Email Link",
                                                    Toast.LENGTH_SHORT).show();
                                        } else if (task.isSuccessful()) {
                                            Log.w(TAG, "signInWithEmail", task.getException());
                                            Toast.makeText(ResetPassword.this, "Authentication failed.",
                                                    Toast.LENGTH_SHORT).show();
                                            Intent takeUserHome = new Intent(ResetPassword.this, PatientLogin.class);
                                            startActivity(takeUserHome);
                                        }
                                    }
                                });

                    }
                }else
                {
                    Toast.makeText(ResetPassword.this, "No Internet Connection,Please check your internet connection.", Toast.LENGTH_SHORT).show();

                }

            }
        });

        btnLoginRP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent UserLogin = new Intent(ResetPassword.this, PatientLogin.class);
                startActivity(UserLogin);
            }
        });
    }
    @Override
    public void onStop() {
        super.onStop();
       // this.finish();
    }
}
