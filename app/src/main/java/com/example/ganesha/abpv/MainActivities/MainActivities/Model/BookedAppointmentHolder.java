package com.example.ganesha.abpv.MainActivities.MainActivities.Model;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Ganesha on 28/09/2017.
 */

public class BookedAppointmentHolder extends RecyclerView.ViewHolder{

    public TextView bAppointmentDate;
    public TextView bAppointmentTime;
    public TextView bDoctorName;

    public ImageView removeC;

    public BookedAppointmentHolder(View itemView) {
        super(itemView);

        bAppointmentDate=(TextView)itemView.findViewById(com.example.ganesha.abpv.R.id.Appointment_Date_Booked);
        bAppointmentTime = (TextView) itemView.findViewById(com.example.ganesha.abpv.R.id.Appointment_Time_Booked);
        bDoctorName = (TextView) itemView.findViewById(com.example.ganesha.abpv.R.id.Doctor_name_Booked);

       // removeC=(ImageView) itemView.findViewById(R.id.imageView13);
    }
}
