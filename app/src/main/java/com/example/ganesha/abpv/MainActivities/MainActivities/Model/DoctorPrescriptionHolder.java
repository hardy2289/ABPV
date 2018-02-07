package com.example.ganesha.abpv.MainActivities.MainActivities.Model;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Ganesha on 04/10/2017.
 */

public class DoctorPrescriptionHolder extends RecyclerView.ViewHolder {


    public TextView AppointmentIDP;
    public TextView LastNameP;
    public TextView PatientIDP;

    public ImageView providePres;


    public TextView Name;
    public TextView Address;
    public TextView Postcode;
    public TextView Contact;
    public TextView Email;

    public ImageView Confirm;



    public DoctorPrescriptionHolder(View itemView) {
        super(itemView);

        AppointmentIDP=(TextView) itemView.findViewById(com.example.ganesha.abpv.R.id.AppointmentIDP);
        LastNameP=(TextView) itemView.findViewById(com.example.ganesha.abpv.R.id.LastNameDP);
        PatientIDP=(TextView) itemView.findViewById(com.example.ganesha.abpv.R.id.PatientIDP);

        providePres=(ImageView) itemView.findViewById(com.example.ganesha.abpv.R.id.imageView16);

        Name=(TextView)itemView.findViewById(com.example.ganesha.abpv.R.id.PharName);
        Address = (TextView) itemView.findViewById(com.example.ganesha.abpv.R.id.Address);
        Postcode = (TextView) itemView.findViewById(com.example.ganesha.abpv.R.id.Postcode);
        Contact = (TextView) itemView.findViewById(com.example.ganesha.abpv.R.id.PhoneNo);
        Email = (TextView) itemView.findViewById(com.example.ganesha.abpv.R.id.EmailAdd);


        Confirm=(ImageView) itemView.findViewById(com.example.ganesha.abpv.R.id.imageView145);

    }
}
