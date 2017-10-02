package com.example.ganesha.abpv.MainActivities.MainActivities.Model;

import android.support.v7.app.AppCompatActivity;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ganesha on 05/09/2017.
 */
@IgnoreExtraProperties
public class DoctorDetails extends AppCompatActivity {
    public String AppointmentDateD;
    public String AppointmentIDD;
    public String AppointmentTimeD;
    public String LastNameD;
    public String PatientIDD;

    public DoctorDetails() {
        // Default constructor required for calls to DataSnapshot.getValue(Users.class)
    }

    public DoctorDetails( String AppointmentDateD, String AppointmentIDD, String AppointmentTimeD,  String LastNameD, String PatientIDD) {
        this.AppointmentDateD = AppointmentDateD;
        this.AppointmentIDD = AppointmentIDD;
        this.AppointmentTimeD = AppointmentTimeD;
        this.LastNameD = LastNameD;
        this.PatientIDD = PatientIDD;
    }

    @Exclude
    public Map<String, Object> toMap(){

        HashMap<String, Object> result= new HashMap<>();


        result.put("AppointmentDate", AppointmentDateD);
        result.put("AppointmentID",AppointmentIDD);
        result.put("AppointmentTime", AppointmentTimeD);
        result.put("LastName",LastNameD);
        result.put("PatientID", PatientIDD);
        return result;
    }

}