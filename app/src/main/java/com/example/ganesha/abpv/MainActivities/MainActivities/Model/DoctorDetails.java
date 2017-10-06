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
    public String DOB;
    public String DoctorID;
    public String DoctorName;

    public String Name;
    public String Address;
    public String Postcode;
    public String Contact;
    public String Email;


    public DoctorDetails() {
        // Default constructor required for calls to DataSnapshot.getValue(Users.class)
    }

    public DoctorDetails( String AppointmentDateD, String AppointmentIDD, String AppointmentTimeD,  String LastNameD, String PatientIDD,
                          String DOB, String DoctorID, String DoctorName, String Name, String Address, String Postcode, String Contact, String Email) {
        this.AppointmentDateD = AppointmentDateD;
        this.AppointmentIDD = AppointmentIDD;
        this.AppointmentTimeD = AppointmentTimeD;
        this.LastNameD = LastNameD;
        this.PatientIDD = PatientIDD;
        this.DOB=DOB;
        this.DoctorID=DoctorID;
        this.DoctorName=DoctorName;

        this.Name=Name;
        this.Address=Address;
        this.Postcode=Postcode;
        this.Contact=Contact;
        this.Email=Email;
    }

    @Exclude
    public Map<String, Object> toMap(){

        HashMap<String, Object> result= new HashMap<>();


        result.put("AppointmentDate", AppointmentDateD);
        result.put("AppointmentID",AppointmentIDD);
        result.put("AppointmentTime", AppointmentTimeD);
        result.put("LastName",LastNameD);
        result.put("PatientID", PatientIDD);
        result.put("DOB", DOB);
        result.put("DoctorID", DoctorID);
        result.put("DoctorName",DoctorName);
        result.put("Name", Name);
        result.put("Address", Address);
        result.put("Postcode", Postcode);
        result.put("Contact", Contact);
        result.put("Email", Email);
        return result;
    }

}