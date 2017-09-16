package com.example.ganesha.abpv.MainActivities.MainActivities.Appointment;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Ganesha on 04/09/2017.
 */

public class AppointmentViewHolder extends RecyclerView.ViewHolder {

    public TextView DoctorName;
    public TextView AppointmentDate;
    public TextView AppointmentTime;
    public TextView PatientLastName;
    public TextView PatientDOB;
    public TextView PatientNo;

    public AppointmentViewHolder(View itemView) {
        super(itemView);
    }
}
