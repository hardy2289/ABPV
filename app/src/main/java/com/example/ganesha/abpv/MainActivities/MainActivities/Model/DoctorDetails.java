package com.example.ganesha.abpv.MainActivities.MainActivities.Model;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ganesha on 05/09/2017.
 */

public class DoctorDetails {


    public String FirstName;
    public String TotalPatient;
    public String TransactionCompletedAt;
    public String zDoctorID;


    public DoctorDetails() {
        // Default constructor required for calls to DataSnapshot.getValue(Users.class)
    }

    public DoctorDetails( String FirstName, String TotalPatient, String TransactionCompletedAt,  String zDoctorID) {


        this.FirstName = FirstName;
        this.TotalPatient = TotalPatient;
        this.TransactionCompletedAt = TransactionCompletedAt;
        this.zDoctorID = zDoctorID;

    }

    @Exclude
    public Map<String, Object> toMap(){

        HashMap<String, Object> result= new HashMap<>();


        result.put("FirstName", FirstName);
        result.put("TotalPatient", TotalPatient);
        result.put("TransactionCompletedAt",TransactionCompletedAt);
        result.put("zDoctorID", zDoctorID);
        return result;
    }
}