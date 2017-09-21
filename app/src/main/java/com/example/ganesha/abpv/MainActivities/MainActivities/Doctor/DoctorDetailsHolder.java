package com.example.ganesha.abpv.MainActivities.MainActivities.Doctor;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.ganesha.abpv.R;

/**
 * Created by Ganesha on 05/09/2017.
 */

public class DoctorDetailsHolder extends RecyclerView.ViewHolder{


    public TextView dDoctorID;
    public TextView dDoctorName;
    public TextView dAppointmentDate;
    public TextView dAppointmentID;






    public DoctorDetailsHolder(View itemView) {
        super(itemView);
        dDoctorID = (TextView) itemView.findViewById(R.id.Doctor_id);
        dDoctorName = (TextView) itemView.findViewById(R.id.Doctor_name);
        dAppointmentDate=(TextView)itemView.findViewById(R.id.Appointment);
        dAppointmentID = (TextView) itemView.findViewById(R.id.Appointment_no);


    }
}
