package com.example.ganesha.abpv.MainActivities.MainActivities.Model;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ganesha.abpv.R;

/**
 * Created by Ganesha on 25/09/2017.
 */

public class DoctorAppConfirmListHolder extends RecyclerView.ViewHolder{
    public TextView dAppointmentDate;
    public TextView dAppointmentID;
    public TextView dAppointmentTime;
    public TextView dLastName;
    public TextView dPatientID;

    public ImageView confirmApp;



    public DoctorAppConfirmListHolder(View itemView) {
        super(itemView);

        dAppointmentDate = (TextView) itemView.findViewById(R.id.AppointmentDateD);
        dAppointmentID=(TextView) itemView.findViewById(R.id.AppointmentIDD);
        dAppointmentTime=(TextView) itemView.findViewById(R.id.AppointmentTimeD);
        dLastName=(TextView) itemView.findViewById(R.id.LastNameD);
        dPatientID=(TextView) itemView.findViewById(R.id.PatientIDD);
        confirmApp=(ImageView) itemView.findViewById(R.id.imageView15);

    }
}