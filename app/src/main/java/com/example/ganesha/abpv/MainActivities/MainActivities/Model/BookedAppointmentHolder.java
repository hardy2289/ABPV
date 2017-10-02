package com.example.ganesha.abpv.MainActivities.MainActivities.Model;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.ganesha.abpv.R;

/**
 * Created by Ganesha on 28/09/2017.
 */

public class BookedAppointmentHolder extends RecyclerView.ViewHolder{

    public TextView bAppointmentDate;
    public TextView bAppointmentTime;
    public TextView bDoctorName;

    public BookedAppointmentHolder(View itemView) {
        super(itemView);


        bAppointmentDate=(TextView)itemView.findViewById(R.id.Appointment_Date_Booked);
        bAppointmentTime = (TextView) itemView.findViewById(R.id.Appointment_Time_Booked);
        bDoctorName = (TextView) itemView.findViewById(R.id.Doctor_name_Booked);
    }
}
