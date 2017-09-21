package com.example.ganesha.abpv.MainActivities.MainActivities.Model;

/**
 * Created by Ganesha on 22/08/2017.
 */

public class Doctors {

    public String Authentication;
    public String Email;
    public String FemalePatint;
    public String FirstName;
    public String MalePatient;
    public String PopularDoctor;
    public String TotalPatient;
    public String TotalAppointments;
    public String Uid;
    public String Username;
    public String zDoctorID;


    public Doctors() {
        // Default constructor required for calls to DataSnapshot.getValue(Users.class)
    }

    public Doctors( String Authentication,String Email,String FirstName,String FemalePatient,  String Uid,String MalePatient, String PopularDoctor, String TotalPatient, String TotalAppointments, String Username, String zDoctorID ) {

        this.Authentication = Authentication;
        this.Email = Email;
        this.FemalePatint=FemalePatient;
        this.FirstName=FirstName;
        this.MalePatient=MalePatient;
        this.PopularDoctor=PopularDoctor;
        this.TotalPatient=TotalPatient;
        this.TotalAppointments = TotalAppointments;
        this.Uid=Uid;
        this.Username=Username;
        this.zDoctorID=zDoctorID;

    }
}
