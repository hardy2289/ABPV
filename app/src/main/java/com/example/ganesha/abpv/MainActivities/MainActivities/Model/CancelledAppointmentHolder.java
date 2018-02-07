package com.example.ganesha.abpv.MainActivities.MainActivities.Model;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Ganesha on 28/09/2017.
 */

public class CancelledAppointmentHolder extends RecyclerView.ViewHolder {
    public TextView cAppointmentDate;
    public TextView cAppointmentTime;
    public TextView cDoctorName;

    public CancelledAppointmentHolder(View itemView) {
        super(itemView);

        cAppointmentDate=(TextView)itemView.findViewById(com.example.ganesha.abpv.R.id.Appointment_Date_Cancelled);
        cAppointmentTime = (TextView) itemView.findViewById(com.example.ganesha.abpv.R.id.Appointment_Time_Cancelled);
        cDoctorName = (TextView) itemView.findViewById(com.example.ganesha.abpv.R.id.Doctor_name_Cancelled);
    }
}
