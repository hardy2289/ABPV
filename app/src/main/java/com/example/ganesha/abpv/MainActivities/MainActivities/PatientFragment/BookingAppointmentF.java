package com.example.ganesha.abpv.MainActivities.MainActivities.PatientFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ganesha.abpv.MainActivities.MainActivities.Model.AppointmentHolder;
import com.example.ganesha.abpv.MainActivities.MainActivities.Model.NewAppointments;
import com.example.ganesha.abpv.MainActivities.MainActivities.Patient.FragmentSupport;
import com.example.ganesha.abpv.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;


public class BookingAppointmentF extends Fragment {

    FragmentSupport fragmentSupport = new FragmentSupport();
    final String userId = fragmentSupport.getUid();

    private DatabaseReference mDatabase;
    private FirebaseRecyclerAdapter<NewAppointments, AppointmentHolder> mAdapter;
    private RecyclerView mRecycler;
    private LinearLayoutManager mManager;



    private static View view;

    public BookingAppointmentF() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_booking_appointment, container, false);

        // [START create_database_reference]
        mDatabase = FirebaseDatabase.getInstance().getReference();
        // [END create_database_reference]

        mRecycler = (RecyclerView) rootView.findViewById(R.id.points_list);
        mRecycler.setHasFixedSize(true);
        return rootView;
    }



    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Set up Layout Manager, reverse layout
        mManager = new LinearLayoutManager(getActivity());
        mManager.setReverseLayout(true);
        mManager.setStackFromEnd(true);
        mRecycler.setLayoutManager(mManager);
        mDatabase = FirebaseDatabase.getInstance().getReference();


        // Set up FirebaseRecyclerAdapter with the Query
        Query postsQuery = getQuery(mDatabase);
        // Query postsQuery = getQuery(mDatabase).orderByChild("shoppingStatus").equalTo("In Process");

        mAdapter = new FirebaseRecyclerAdapter<NewAppointments, AppointmentHolder>(NewAppointments.class, R.layout.listview_item, AppointmentHolder.class, postsQuery) {

            @Override
            protected void populateViewHolder(AppointmentHolder viewHolder, NewAppointments model, int position) {

                viewHolder.aDoctorID.setText(model.DoctorIDA);
                viewHolder.aDoctorName.setText(model.DoctorNameA);
                viewHolder.aAppointmentTime.setText(model.AppointmentTimeA);
                viewHolder.aAppointmentDate.setText(model.AppointmentDateA);

            }


        };
        mRecycler.setAdapter(mAdapter);

    }

    public void onDestroy() {
        super.onDestroy();
        if (mAdapter != null) {
            mAdapter.cleanup();
        }
    }


    public Query getQuery(DatabaseReference databaseReference) {

        Query doctorapp = databaseReference.child("newappointment").child(userId);

        return doctorapp;

    }

}
