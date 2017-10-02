package com.example.ganesha.abpv.MainActivities.MainActivities.PatientFragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.ganesha.abpv.MainActivities.MainActivities.Model.AppointmentHolder;
import com.example.ganesha.abpv.MainActivities.MainActivities.Model.NewAppointments;
import com.example.ganesha.abpv.MainActivities.MainActivities.Patient.FragmentSupport;
import com.example.ganesha.abpv.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class BookingAppointmentF extends Fragment {
    FragmentSupport fragmentSupport = new FragmentSupport();
    final String userId = fragmentSupport.getUid();
    private DatabaseReference mDatabase;
    private FirebaseRecyclerAdapter<NewAppointments, AppointmentHolder> mAdapter;
    private RecyclerView mRecycler;
    private LinearLayoutManager mManager;
    public ImageView RemoveList;
    private RecyclerView myRecyclerView;
    private LinearLayoutManager linearLayoutManager;
    private static View view;
    private AdapterView.OnItemClickListener mItemClickListener;
    RecyclerView RView;
    private List<NewAppointments> Holder;
    EditText SearchAppointment;

    private Context context;
    private ArrayList<NewAppointments> data;
    private LayoutInflater inflater;
    private int previousPosition = 0;


    public BookingAppointmentF() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_booking_appointment, container, false);
        // [START create_database_reference]
        mDatabase = FirebaseDatabase.getInstance().getReference();
        // [END create_database_reference]
        mRecycler = (RecyclerView) rootView.findViewById(R.id.points_list);


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
        mAdapter = new FirebaseRecyclerAdapter<NewAppointments, AppointmentHolder>(NewAppointments.class, R.layout.listview_item_display, AppointmentHolder.class, postsQuery) {
            @SuppressLint("SetTextI18n")
            @Override
            protected void populateViewHolder(final AppointmentHolder viewHolder, NewAppointments model,final int position) {

                viewHolder.aDoctorID.setText(model.DoctorIDA);
                viewHolder.aDoctorName.setText(model.DoctorNameA);
                viewHolder.aAppointmentTime.setText(model.AppointmentTimeA);
                viewHolder.aAppointmentDate.setText(model.AppointmentDateA);

                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        builder.setTitle("Confirm");
                        builder.setMessage("Are you sure?");

                    }
                });

            }
        };

        mRecycler.setAdapter(mAdapter);
    }

    public void onBindViewHolder(AppointmentHolder holder, final int position) {

        holder.aAppointmentDate.setText(Holder.get(position).AppointmentTimeA);
        holder.aAppointmentTime.setText(Holder.get(position).AppointmentDateA);
        holder.aDoctorID.setText(Holder.get(position).DoctorIDA);
        holder.aDoctorName.setText(Holder.get(position).DoctorNameA);

        holder.remove.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                NewAppointments theRemovedItem = Holder.get(position);
                // remove your item from data base
                Holder.remove(position);  // remove the item from list
                mAdapter.notifyItemRemoved(position); // notify the adapter about the removed item
            }
        });
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
        Query doctorapp = databaseReference.child("users").child("znewapp").child("app1/");
        return doctorapp;
    }


    public void onPostExecute(Void result) {
        // Locate the listview in activity_booking_appointment.xmlappointment.xml
        RView=(RecyclerView)view.findViewById(R.id.points_list);
        // Pass the results into Booking_ListViewAdapter.java

        // Binds the Adapter to the ListView
        RView.setAdapter(mAdapter);
        // Close the progressdialog
        SearchAppointment = (EditText) view.findViewById(R.id.ApponintmentSearch);
        //  SearchAppointment.setSelected(false);
        // Capture Text in EditText
        SearchAppointment.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
                String text = SearchAppointment.getText().toString()
                        .toLowerCase(Locale.getDefault());
            }
            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1,
                                          int arg2, int arg3) {
                // TODO Auto-generated method stub
            }
            @Override
            public void onTextChanged(CharSequence arg0, int arg1,
                                      int arg2, int arg3) {
                // TODO Auto-generated method stub
            }
        });

    }
}
