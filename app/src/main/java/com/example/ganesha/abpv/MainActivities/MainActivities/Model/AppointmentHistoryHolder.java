package com.example.ganesha.abpv.MainActivities.MainActivities.Model;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ganesha.abpv.R;

/**
 * Created by Ganesha on 28/09/2017.
 */

public class AppointmentHistoryHolder extends RecyclerView.ViewHolder{

    public TextView hAppointmentDate;
    public TextView hAppointmentTime;
    public TextView hDoctorName;
   // public TextView hStatus;

    public ImageView removeC;

    public AppointmentHistoryHolder (View itemView) {
        super(itemView);

        hAppointmentDate=(TextView)itemView.findViewById(R.id.Appointment_DateH);
        hAppointmentTime = (TextView) itemView.findViewById(R.id.Appointment_TimeH);
        hDoctorName = (TextView) itemView.findViewById(R.id.Doctor_nameH);
      //  hStatus=(TextView) itemView.findViewById(R.id.Appointment_StatusH);

        removeC=(ImageView) itemView.findViewById(R.id.imageView14);
    }
}
