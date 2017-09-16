package com.example.ganesha.abpv.MainActivities.MainActivities.Model;

import android.support.v7.app.AppCompatActivity;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
public class AppointmentDetails extends AppCompatActivity {
    public String AStatus;
    public String dAppointmentDate;
    public String dAppointmentID;
    public String dAppointmentTime;
    public String dDOB;
    public String dDoctorName;
    public String dLastName;
    public String dPhoneNo;
    public String dzDoctorID;
    public String dzPatientID;
    public String Uid;

    public Map<String, Boolean> status = new HashMap<>();

    public AppointmentDetails(){
        // Default constructor required for calls to DataSnapshot.getValue(Users.class)
    }

    public AppointmentDetails(  String AStatus,String AppointmentDate,String AppointmentID, String AppointmentTime, String DOB,  String DoctorName, String LastName, String PhoneNo,String dzDoctorID, String dzPatientID, String Uid) {

        this.AStatus=AStatus;
        this.dAppointmentDate = AppointmentDate;
        this.dAppointmentID = AppointmentID;
        this.dAppointmentTime = AppointmentTime;
        this.dDOB = DOB;
        this.dDoctorName = DoctorName;
        this.dLastName = LastName;
        this.dPhoneNo = PhoneNo;
        this.dzDoctorID=dzDoctorID;
        this.dzPatientID=dzPatientID;
        this.Uid=Uid;


    }

    @Exclude
    public Map<String, Object> toMap(){

        HashMap<String, Object> result= new HashMap<>();

        result.put("AStatus",AStatus);
        result.put("AppointmentDate", dAppointmentDate);
        result.put("AppointmentID", dAppointmentID);
        result.put("AppointmentTime", dAppointmentTime);
        result.put("DOB", dDOB);
        result.put("DoctorName", dDoctorName);
        result.put("LastName", dLastName);
        result.put("PhoneNo", dPhoneNo);
        result.put("zDoctorID", dzDoctorID);
        result.put("zPatientID", dzPatientID);
        result.put("Uid", Uid);

        return result;
    }
}
