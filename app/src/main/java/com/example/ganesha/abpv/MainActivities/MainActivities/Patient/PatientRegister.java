package com.example.ganesha.abpv.MainActivities.MainActivities.Patient;

import android.app.ProgressDialog;
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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;

import com.example.ganesha.abpv.MainActivities.MainActivities.ConnectionDetector;
import com.example.ganesha.abpv.MainActivities.MainActivities.Model.Appointments;
import com.example.ganesha.abpv.MainActivities.MainActivities.Model.Users;
import com.example.ganesha.abpv.MainActivities.MainActivities.Support;

public class PatientRegister extends AppCompatActivity {

    private static final String TAG = "EmailPassword";
    public Support support = new Support();
    private EditText txtEmail;
    private EditText txtPassword;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    protected Button btnRegister;
    protected Button btnGoogleLogin;
    protected Button btnLogin;
    protected Button btnResetPassword;
    private ProgressDialog progressDialog;
    ConnectionDetector cd;
    Boolean isInternetPresent = true;

    @Override
    protected void onCreate (Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(com.example.ganesha.abpv.R.layout.patient_register_ep);
        cd = new ConnectionDetector(getApplicationContext());

        txtEmail = (EditText) findViewById(com.example.ganesha.abpv.R.id.edit_txt_email_patient_loginep);
        txtPassword = (EditText) findViewById(com.example.ganesha.abpv.R.id.edit_txt_password_patient_loginep);
        btnRegister = (Button) findViewById(com.example.ganesha.abpv.R.id.btn_register_loginpagep);
        btnLogin = (Button) findViewById(com.example.ganesha.abpv.R.id.btn_patient_loginep);
        btnGoogleLogin = (Button) findViewById(com.example.ganesha.abpv.R.id.btn_google_signinep);
        btnResetPassword = (Button) findViewById(com.example.ganesha.abpv.R.id.btn_password_resetep);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               ConnectionDetector cd;

                cd = new ConnectionDetector(getApplicationContext());
                // get Internet status
                isInternetPresent = cd.isConnectingToInternet();
                // check for Internet status

                if (isInternetPresent) {
                String email = txtEmail.getText().toString().trim();
                String password = txtPassword.getText().toString().trim();
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                //progressDialog = new ProgressDialog(this);
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(PatientRegister.this, "Enter email address!", Toast.LENGTH_SHORT).show();
                } else if (!email.matches(emailPattern)) {
                    Toast.makeText(PatientRegister.this, "Invalid email address", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(password)) {
                    Toast.makeText(PatientRegister.this, "Enter password!", Toast.LENGTH_SHORT).show();
                } else if (password.length() < 6) {
                    Toast.makeText(PatientRegister.this, "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
                } else {
                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());

                                    if (task.isSuccessful()) {
                                        // calling onAutSuccess method when task is successful
                                        onAuthSuccess(task.getResult().getUser());
                                    } else if (!task.isSuccessful()) {
                                        // setting up toast error message
                                        Toast.makeText(PatientRegister.this, "Registration Error.", Toast.LENGTH_SHORT).show();
                                    }

                                }

                            });
                }

           }else
            {
                Toast.makeText(PatientRegister.this, "No Internet Connection,Please check your internet connection.", Toast.LENGTH_SHORT).show();

            }

            }
        });


        btnGoogleLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent UserGoogleLogin = new Intent(PatientRegister.this, GoogleLogin.class);
                startActivity(UserGoogleLogin);
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent UserLogin = new Intent(PatientRegister.this, PatientLogin.class);
                startActivity(UserLogin);
            }
        });
        btnResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent UserPasswordReset = new Intent(PatientRegister.this, ResetPassword.class);
                startActivity(UserPasswordReset);
            }
        });

    }
    public void onAuthSuccess(FirebaseUser user) {
        String username = usernameFromEmail(user.getEmail());
        String userFirstname = "";
        String userLastname = "";
        String userDateofBirth = "";
        String userGender = "";
        String userPhoneno="";
        String userAppointmentDate="";
        String userAppointmentTime="";
        String userDoctorName="";
        String PatientID="";
        String DoctorId= "";
        String AppointmentId= "";
        String Status=" ";
        String uid = mAuth.getCurrentUser().getUid();

        writeNewUser(FirebaseInstanceId.getInstance().getToken(), userDateofBirth, user.getEmail(),  userFirstname,  userGender, userLastname,
                userPhoneno, uid,  username, user.getUid());

        writeNewAppointment(Status,userAppointmentDate,AppointmentId,userAppointmentTime, userDateofBirth, userDoctorName, userLastname,userPhoneno, DoctorId,PatientID, uid, user.getUid());

       // writeNewAppointments(userAppointmentDate,userAppointmentTime,DoctorId,userDoctorName, uid);

      //  writeNewDocAppointments(userAppointmentDate,AppointmentId,userAppointmentTime, userLastname, PatientID, uid );

        Toast.makeText(PatientRegister.this, "Welcome to the Appointments Booking and Prescription Viewer", Toast.LENGTH_LONG).show();
        Intent takeUserHome = new Intent(PatientRegister.this, Navigation.class);
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
    private void writeNewUser( String Authentication, String DateOfBirth,String Email,  String FirstName, String Gender, String LastName,
                               String Phoneno,  String Uid, String Username, String userId) {

        Users user = new Users( Authentication, DateOfBirth, Email, FirstName,  Gender, LastName, Phoneno,Uid,Username);

        mDatabase.child("users").child(userId).setValue(user);
    }

    public void writeNewAppointment(String Status,String AppointmentDate,String AppointmentID, String AppointmentTime, String DOB, String DoctorName,
                                    String LastName, String PhoneNo, String zDocotrID, String zPatientID, String Uid, String userId ) {

        Appointments appointments = new Appointments(Status, AppointmentDate, AppointmentID, AppointmentTime, DOB, DoctorName, LastName, PhoneNo,
                zDocotrID, zPatientID, Uid);

        mDatabase.child("appointment").child(userId).child("1").setValue(appointments);
    }

     /*   public void writeNewAppointments(String AppointmentDateA, String AppointmentTimeA, String DoctorIDA, String DoctorNameA, String userId){

            NewAppointments newAppointments=new NewAppointments(AppointmentDateA, AppointmentTimeA,DoctorIDA,DoctorNameA);

        mDatabase.child("newappointment").child(userId).child("app1").setValue(newAppointments);
    }*/

   /* public void writeNewDocAppointments (String AppointmentDateD, String AppointmentIDD, String AppointmentTimeD, String LastNameD, String PatientIDD,String userId){

        DoctorDetails doctorDetails= new DoctorDetails(AppointmentDateD, AppointmentIDD,AppointmentTimeD, LastNameD,PatientIDD );

        mDatabase.child("doctorappointments").child(userId).setValue(doctorDetails);*/
    @Override
    public void onStop() {
        super.onStop();
        this.finish();
    }

}
