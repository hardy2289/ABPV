package com.example.ganesha.abpv.MainActivities.MainActivities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.ganesha.abpv.MainActivities.MainActivities.Doctor.DoctorLogin;
import com.example.ganesha.abpv.MainActivities.MainActivities.Patient.PatientLogin;
import com.example.ganesha.abpv.R;

public class MainActivity extends AppCompatActivity {

    private Button btnDoctorPage;
    private Button btnPatientPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnDoctorPage=(Button) findViewById(R.id.btnDoctorPage);
        btnPatientPage=(Button) findViewById(R.id.btnPatientPage);



        btnDoctorPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent DoctorPage = new Intent(MainActivity.this, DoctorLogin.class);
                startActivity(DoctorPage);

            }
        });

        btnPatientPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent PatientPage = new Intent(MainActivity.this, PatientLogin.class);
                startActivity(PatientPage);
            }
        });
    }
}
