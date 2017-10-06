package com.example.ganesha.abpv.MainActivities.MainActivities.Model;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ganesha.abpv.R;

/**
 * Created by Ganesha on 04/10/2017.
 */

public class PharmacyDetailsHolder extends RecyclerView.ViewHolder {

    public TextView Name;
    public TextView Address;
    public TextView Postcode;
    public TextView Contact;
    public TextView Email;

    public ImageView Confirm;

    public PharmacyDetailsHolder(View itemView) {
        super(itemView);

        Name=(TextView)itemView.findViewById(R.id.PharName);
        Address = (TextView) itemView.findViewById(R.id.Address);
        Postcode = (TextView) itemView.findViewById(R.id.Postcode);
        Contact = (TextView) itemView.findViewById(R.id.PhoneNo);
        Email = (TextView) itemView.findViewById(R.id.EmailAdd);


        Confirm=(ImageView) itemView.findViewById(R.id.imageView145);
    }
}
