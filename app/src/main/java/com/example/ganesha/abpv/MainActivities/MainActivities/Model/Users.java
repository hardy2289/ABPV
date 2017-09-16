package com.example.ganesha.abpv.MainActivities.MainActivities.Model;

/**
 * Created by Ganesha on 11/08/2017.
 */

public class Users {

    public String Authentication;
    public String DOB;
    public String Email;
    public String FirstName;
    public String Gender;
    public String LastName;
    public String PhoneNo;
    public String Uid;
    public String Username;





    public Users() {
        // Default constructor required for calls to DataSnapshot.getValue(Users.class)
    }

    public Users( String Authentication,String DOB,String Email, String FirstName, String Gender,String LastName,   String PhoneNo, String Uid,  String Username ) {

        this.Authentication = Authentication;
        this.DOB = DOB;
        this.Email = Email;
        this.FirstName = FirstName;
        this.Gender = Gender;
        this.LastName = LastName;
        this.PhoneNo = PhoneNo;
        this.Uid=Uid;
        this.Username=Username;
    }
}
