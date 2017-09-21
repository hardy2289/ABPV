package com.example.ganesha.abpv.MainActivities.MainActivities.Model;

/**
 * Created by Ganesha on 20/09/2017.
 */

public class NewAppointments {

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
}
