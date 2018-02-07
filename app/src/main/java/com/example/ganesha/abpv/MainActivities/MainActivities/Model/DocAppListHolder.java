package com.example.ganesha.abpv.MainActivities.MainActivities.Model;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Ganesha on 30/09/2017.
 */

public class DocAppListHolder extends RecyclerView.ViewHolder {

    public TextView AppointmentDateL;
    public TextView AppointmentIDL;
    public TextView AppointmentTimeL;



    public DocAppListHolder(View itemView) {
        super(itemView);

        AppointmentDateL = (TextView) itemView.findViewById(com.example.ganesha.abpv.R.id.AppointmentDateDA);
        AppointmentIDL=(TextView) itemView.findViewById(com.example.ganesha.abpv.R.id.AppointmentIDDA);
        AppointmentTimeL=(TextView) itemView.findViewById(com.example.ganesha.abpv.R.id.AppointmentTimeDA);


    }
}
