package com.example.ganesha.abpv.MainActivities.MainActivities.Model;

import android.support.v7.app.AppCompatActivity;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ganesha on 20/09/2017.
 */
@IgnoreExtraProperties

public class NewAppointments extends AppCompatActivity{

    public String DoctorNameA;
    public String AppointmentDateA;
    public String DoctorIDA;
    public String AppointmentTimeA;



    public NewAppointments( ) {
        // Default constructor required for calls to DataSnapshot.getValue(Users.class)
    }

    public NewAppointments(String DoctorNameA, String AppointmentDateA, String DoctorIDA, String AppointmentTimeAb) {

        this.DoctorNameA = DoctorNameA;
        this.AppointmentDateA = AppointmentDateA;
        this.DoctorIDA = DoctorIDA;
        this.AppointmentTimeA = AppointmentTimeAb;

    }
    @Exclude
    public Map<String, Object> toMap(){

        HashMap<String, Object> result= new HashMap<>();


        result.put("AppointmentDate", AppointmentDateA);
        result.put("AppointmentTime", AppointmentTimeA);
        result.put("DoctorID",DoctorIDA);
        result.put("DoctorName", DoctorNameA);
        return result;
    }

}
