package com.example.ganesha.abpv.MainActivities.MainActivities.Model;

/**
 * Created by Ganesha on 02/09/2017.
 */

public class Appointments {
    public String AStatus;
    public String AppointmentDate;
    public String AppointmentID;
    public String AppointmentTime;
    public String DOB;
    public String DoctorName;
    public String LastName;
    public String PhoneNo;
    public String zDoctorID;
    public String zPatientID;
    public String Uid;



    public Appointments( ) {
        // Default constructor required for calls to DataSnapshot.getValue(Users.class)
    }

    public Appointments(String AStatus, String AppointmentDate, String AppointmentID, String AppointmentTime, String DOB, String DoctorName, String LastName, String PhoneNo,
                        String zDoctorID, String zPatientID) {

        this.AStatus=AStatus;
        this.AppointmentDate=AppointmentDate;
        this.AppointmentID=AppointmentID;
        this.AppointmentTime=AppointmentTime;
        this.DOB = DOB;
        this.DoctorName=DoctorName;
        this.LastName = LastName;
        this.PhoneNo = PhoneNo;
        this.zDoctorID=zDoctorID;
        this.zPatientID=zPatientID;
        this.Uid=Uid;

    }
}

