package com.example.ganesha.abpv.MainActivities.MainActivities.Model;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.ganesha.abpv.R;

/**
 * Created by Ganesha on 20/09/2017.
 */

public class AppointmentHolder extends RecyclerView.ViewHolder{

    public TextView aAppointmentDate;
    public TextView aAppointmentTime;
    public TextView aDoctorID;
    public TextView aDoctorName;


    public AppointmentHolder(View itemView) {
        super(itemView);


        aAppointmentDate=(TextView)itemView.findViewById(R.id.Appointment_Date);
        aAppointmentTime = (TextView) itemView.findViewById(R.id.Appointment_Time);
        aDoctorID = (TextView) itemView.findViewById(R.id.Doctor_id);
        aDoctorName = (TextView) itemView.findViewById(R.id.Doctor_name);
    }
}
