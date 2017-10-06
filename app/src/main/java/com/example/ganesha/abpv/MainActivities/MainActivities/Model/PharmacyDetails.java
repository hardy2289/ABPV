package com.example.ganesha.abpv.MainActivities.MainActivities.Model;

/**
 * Created by Ganesha on 04/10/2017.
 */

public class PharmacyDetails {

    public String Name;
    public String Address;
    public String Postcode;
    public String Contact;
    public String Email;

    public PharmacyDetails() {
        // Default constructor required for calls to DataSnapshot.getValue(Users.class)
    }

    public PharmacyDetails(String Name, String Address, String Postcode, String Contact, String Email) {

        this.Name=Name;
        this.Address=Address;
        this.Postcode=Postcode;
        this.Contact=Contact;
        this.Email=Email;
    }
}