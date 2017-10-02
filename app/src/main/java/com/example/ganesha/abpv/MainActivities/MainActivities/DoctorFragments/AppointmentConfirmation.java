package com.example.ganesha.abpv.MainActivities.MainActivities.DoctorFragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.example.ganesha.abpv.MainActivities.MainActivities.Model.DoctorAppConfirmListHolder;
import com.example.ganesha.abpv.MainActivities.MainActivities.Model.DoctorDetails;
import com.example.ganesha.abpv.MainActivities.MainActivities.Patient.FragmentSupport;
import com.example.ganesha.abpv.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;


public class AppointmentConfirmation extends Fragment {


    FragmentSupport fragmentSupport = new FragmentSupport();
    final String userId = fragmentSupport.getUid();

    private DatabaseReference mDatabase;
    private FirebaseRecyclerAdapter<DoctorDetails, DoctorAppConfirmListHolder> mAdapter;
    private RecyclerView mRecycler;
    private LinearLayoutManager mManager;
    public ImageView RemoveList;
    private RecyclerView myRecyclerView;
    private LinearLayoutManager linearLayoutManager;

    private static View view;
    private AdapterView.OnItemClickListener mItemClickListener;

    public AppointmentConfirmation() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_appointment_confirmation, container, false);
        // [START create_database_reference]
        mDatabase = FirebaseDatabase.getInstance().getReference();
        // [END create_database_reference]

        mRecycler = (RecyclerView) rootView.findViewById(R.id.doctor_list);
        mRecycler.setHasFixedSize(true);

// Set up Layout Manager, reverse layout


        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        mManager = new LinearLayoutManager(getActivity());
        mManager.setReverseLayout(true);
        mManager.setStackFromEnd(true);
        mRecycler.setLayoutManager(mManager);
        mDatabase = FirebaseDatabase.getInstance().getReference();

        // Set up FirebaseRecyclerAdapter with the Query
        Query postsQuery = getQuery(mDatabase);
        // Query postsQuery = getQuery(mDatabase).orderByChild("shoppingStatus").equalTo("In Process");

        mAdapter = new FirebaseRecyclerAdapter<DoctorDetails, DoctorAppConfirmListHolder>(DoctorDetails.class, R.layout.listview_item_display_doctor, DoctorAppConfirmListHolder.class, postsQuery) {
            @SuppressLint("SetTextI18n")
            @Override
            protected void populateViewHolder(DoctorAppConfirmListHolder viewHolder, DoctorDetails model, int position) {
                viewHolder.dAppointmentDate.setText(model.AppointmentDateD);
                viewHolder.dAppointmentID.setText(model.AppointmentIDD);
                viewHolder.dAppointmentTime.setText(model.AppointmentTimeD);
                viewHolder.dLastName.setText(model.LastNameD);
                viewHolder.dPatientID.setText(model.PatientIDD);
            }
        };
        mRecycler.setAdapter(mAdapter);

    }

    public String getUid() {
        return FirebaseAuth.getInstance().getCurrentUser().getUid();
    }


    public void onDestroy() {
        super.onDestroy();
        if (mAdapter != null) {
            mAdapter.cleanup();
        }
    }

    public Query getQuery(DatabaseReference databaseReference) {

        Query doctorapp = databaseReference.child("doctors").child(userId).child("app1/");
        return doctorapp;

    }

}
