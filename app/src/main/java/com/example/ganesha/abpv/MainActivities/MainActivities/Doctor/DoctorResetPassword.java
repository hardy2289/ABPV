package com.example.ganesha.abpv.MainActivities.MainActivities.Doctor;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ganesha.abpv.MainActivities.MainActivities.Patient.PatientLogin;
import com.example.ganesha.abpv.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class DoctorResetPassword extends DoctorLogin {

    private static final String TAG1="EmailPassword";
    private Button btnRestPasswordDRP;
    private Button btnLoginDRP;
    private EditText txtEmailDRP;

    public DoctorResetPassword() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_reset_password);

        btnRestPasswordDRP=(Button) findViewById(R.id.btnDoctorResetFP);
        btnLoginDRP=(Button) findViewById(R.id.btnDoctorLoginFP);
        txtEmailDRP=(EditText) findViewById(R.id.editEmailDFP);

        btnRestPasswordDRP.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                String email = txtEmailDRP.getText().toString().trim();
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                FirebaseAuth auth = FirebaseAuth.getInstance();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(DoctorResetPassword.this, "Enter email address!", Toast.LENGTH_SHORT).show();
                }
                else if (!email.matches(emailPattern)){
                    Toast.makeText(DoctorResetPassword.this, "Invalid email address", Toast.LENGTH_SHORT).show();
                }
                else


                {
                    auth.sendPasswordResetEmail(email)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Log.d(TAG1, "Email sent.");

                                        Intent takeUserHome = new Intent(DoctorResetPassword.this, PatientLogin.class);
                                        startActivity(takeUserHome);
                                        Toast.makeText(DoctorResetPassword.this, "Please Reset Your Password Through Email Link",
                                                Toast.LENGTH_SHORT).show();
                                    } else if (task.isSuccessful()) {

                                        Log.w(TAG1, "signInWithEmail", task.getException());
                                        Toast.makeText(DoctorResetPassword.this, "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();
                                        Intent takeUserHome = new Intent(DoctorResetPassword.this, DoctorLogin.class);
                                        startActivity(takeUserHome);


                                    }
                                }
                            });

                }


            }
        });

        btnLoginDRP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent UserLogin = new Intent(DoctorResetPassword.this, DoctorLogin.class);
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

