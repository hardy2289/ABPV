package com.example.ganesha.abpv.MainActivities.MainActivities.Doctor;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ganesha.abpv.MainActivities.MainActivities.Model.Doctors;
import com.example.ganesha.abpv.MainActivities.MainActivities.Support;
import com.example.ganesha.abpv.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;

public class DoctorRegistration extends Activity {

    private static final String TAG1 = "EmailPassword";
    public Support support = new Support();
    private EditText txtEmail;
    private EditText txtPassword;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    protected Button btnRegister;
    protected Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_registration);

        txtEmail = (EditText) findViewById(R.id.editDoctorEmailRegistration);
        txtPassword = (EditText) findViewById(R.id.editDoctorPasswordRegistration);
        btnRegister = (Button) findViewById(R.id.btnDoctorRegistration);
        btnLogin = (Button) findViewById(R.id.btnDoctorLoginReg);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

        btnRegister.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String email = txtEmail.getText().toString().trim();
                String password = txtPassword.getText().toString().trim();
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                //progressDialog = new ProgressDialog(this);


                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(DoctorRegistration.this, "Enter email address!", Toast.LENGTH_SHORT).show();
                }
                else if (!email.matches(emailPattern)){
                    Toast.makeText(DoctorRegistration.this, "Invalid email address", Toast.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(password)) {
                    Toast.makeText(DoctorRegistration.this, "Enter password!", Toast.LENGTH_SHORT).show();
                }
                else if (password.length() < 6) {
                    Toast.makeText(DoctorRegistration.this, "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
                }
                else{

                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    Log.d(TAG1, "createUserWithEmail:onComplete:" + task.isSuccessful());

                                    if (task.isSuccessful()) {
                                        // calling onAutSuccess method when task is successful
                                        onAuthSuccess(task.getResult().getUser());

                                    } else if (!task.isSuccessful()) {
                                        // setting up toast error message
                                        Toast.makeText(DoctorRegistration.this, "Registration Error.", Toast.LENGTH_SHORT).show();

                                    }

                                }

                            });
                }

            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent UserLogin = new Intent(DoctorRegistration.this, DoctorLogin.class);
                startActivity(UserLogin);
            }
        });

    }

    public void onAuthSuccess(FirebaseUser user) {
        String username = usernameFromEmail(user.getEmail());
        String uid = mAuth.getCurrentUser().getUid();
        String FemalePatient="";
        String userFirstname = "";
        String MalePatient="";
        String PopularDoctor="";
        String TotalPatient="";
        String TotalAppointments="";
        String zDoctorID="";

        writeNewUser(FirebaseInstanceId.getInstance().getToken(), user.getEmail(), FemalePatient, userFirstname, MalePatient,PopularDoctor, TotalPatient, TotalAppointments, uid,  username, zDoctorID, user.getUid());

        Toast.makeText(DoctorRegistration.this, "Welcome to the Appointments Booking and Prescription Viewer", Toast.LENGTH_LONG).show();
        Intent takeUserHome = new Intent(DoctorRegistration.this, DoctorLogin.class);
        startActivity(takeUserHome);
        finish();
    }


    private String usernameFromEmail(String email) {
        if (email.contains("@")) {
            return email.split("@")[0];
        } else {
            return email;
        }
    }

    // [START basic_write]
    private void writeNewUser( String Authentication,String Email, String FemalePatient, String FirstName, String MalePatient, String PopularDoctor, String TotalPatient,String TotalAppointments, String Uid, String Username, String zDoctorID,  String userId) {

        Doctors doctor = new Doctors( Authentication, Email,FemalePatient,FirstName, MalePatient,PopularDoctor, TotalPatient,TotalAppointments, Uid, Username, zDoctorID);

        mDatabase.child("doctors").child(userId).setValue(doctor);
    }
    @Override
    public void onStop() {
        super.onStop();
        this.finish();
    }
}
