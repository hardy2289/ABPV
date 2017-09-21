package com.example.ganesha.abpv.MainActivities.MainActivities.Model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ganesha on 20/09/2017.
 */

public class NewAppointmentDetail {

    public String AppointmentDateA;
    public String AppointmentTimeA;
    public String DoctorIDA;
    public String DoctorNameA;




    public NewAppointmentDetail( ) {
        // Default constructor required for calls to DataSnapshot.getValue(Users.class)
    }

    public NewAppointmentDetail(String AppointmentDateA, String AppointmentTimeA, String DoctorIDA, String DoctorNameA) {


        this.AppointmentDateA = AppointmentDateA;
        this.AppointmentTimeA = AppointmentTimeA;
        this.DoctorIDA = DoctorIDA;
        this.DoctorNameA = DoctorNameA;
    }

    public Map<String, Object> toMap(){

        HashMap<String, Object> result= new HashMap<>();


        result.put("AppointmentDate", AppointmentDateA);
        result.put("AppointmentTime", AppointmentTimeA);
        result.put("DoctorID",DoctorIDA);
        result.put("DoctorName", DoctorNameA);
        return result;
    }
}
