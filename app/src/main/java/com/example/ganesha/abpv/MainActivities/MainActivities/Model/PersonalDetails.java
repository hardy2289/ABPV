package com.example.ganesha.abpv.MainActivities.MainActivities.Model;

import android.support.v7.app.AppCompatActivity;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ganesha on 22/08/2017.
 */
@IgnoreExtraProperties
public class PersonalDetails extends AppCompatActivity {

    public String dAuthentication;
    public String dDOB;
    public String dEmail;
    public String dFirstName;
    public String dGender;
    public String dLastName;
    public String dPhoneNo;
    public String dUid;
    public String dUsername;




    public Map<String, Boolean> stars = new HashMap<>();

    public PersonalDetails() {
        // Default constructor required for calls to DataSnapshot.getValue(Users.class)
    }

    public PersonalDetails( String Authentication,String DOB,String Email, String FirstName, String Gender,String LastName,   String PhoneNo, String Uid,  String Username ) {

        this.dAuthentication = Authentication;
        this.dDOB = DOB;
        this.dEmail = Email;
        this.dFirstName = FirstName;
        this.dGender = Gender;
        this.dLastName = LastName;
        this.dPhoneNo = PhoneNo;
        this.dUid=Uid;
        this.dUsername=Username;
    }

    @Exclude
    public Map<String, Object> toMap(){

        HashMap<String, Object> result= new HashMap<>();

        result.put("Authentication", dAuthentication);
        result.put("DOB", dDOB);
        result.put("Email", dEmail);
        result.put("FirstName", dFirstName);
        result.put("Gender",dGender);
        result.put("LastName", dLastName);
        result.put("PhoneNo", dPhoneNo);
        result.put("Uid", dUid);
        result.put("Username", dUsername);
        return result;
    }
}
