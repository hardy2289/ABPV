package com.example.ganesha.abpv.MainActivities.MainActivities.Model;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ganesha on 05/09/2017.
 */

public class DoctorDetails {

    public String Authentication;
    public String Email;
    public String FemalePatint;
    public String FirstName;
    public String MalePatient;
    public String PopularDoctor;
    public String TotalPatient;
    public String TotalAppointments;
    public String TransactionCompletedAt;
    public String Uid;
    public String Username;
    public String zDoctorID;


    public DoctorDetails() {
        // Default constructor required for calls to DataSnapshot.getValue(Users.class)
    }

    public DoctorDetails(String Authentication, String Email, String FirstName, String FemalePatient, String Uid, String MalePatient,String PopularDoctor, String TotalPatient, String TotalAppointments, String TransactionCompletedAt, String Username, String zDoctorID) {

        this.Authentication = Authentication;
        this.Email = Email;
        this.FemalePatint = FemalePatient;
        this.FirstName = FirstName;
        this.MalePatient = MalePatient;
        this.PopularDoctor=PopularDoctor;
        this.TotalPatient = TotalPatient;
        this.TransactionCompletedAt=TransactionCompletedAt;
        this.TotalAppointments = TotalAppointments;
        this.Uid = Uid;
        this.Username = Username;
        this.zDoctorID = zDoctorID;

    }

    @Exclude
    public Map<String, Object> toMap(){

        HashMap<String, Object> result= new HashMap<>();


        result.put("Authentication", Authentication);
        result.put("Email", Email);
        result.put("FemalePatient", FemalePatint);
        result.put("FirstName", FirstName);
        result.put("MalePatient",MalePatient);
        result.put("PopularDoctor",PopularDoctor);
        result.put("TotalPatient", TotalPatient);
        result.put("TotalAppointments", TotalAppointments);
        result.put("TransactionCompletedAt",TransactionCompletedAt);
        result.put("Uid", Uid);
        result.put("Username", Username);
        result.put("zDoctorID", zDoctorID);
        return result;
    }
}