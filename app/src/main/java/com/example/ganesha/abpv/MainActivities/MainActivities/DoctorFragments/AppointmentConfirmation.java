package com.example.ganesha.abpv.MainActivities.MainActivities.DoctorFragments;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ganesha.abpv.MainActivities.MainActivities.Model.DoctorAppConfirmListHolder;
import com.example.ganesha.abpv.MainActivities.MainActivities.Model.DoctorDetails;
import com.example.ganesha.abpv.MainActivities.MainActivities.Patient.FragmentSupport;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;


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
    private List<DoctorDetails> Holder= new List<DoctorDetails>() {
        @Override
        public int size() {
            return 0;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public boolean contains(Object o) {
            return false;
        }

        @NonNull
        @Override
        public Iterator<DoctorDetails> iterator() {
            return null;
        }

        @NonNull
        @Override
        public Object[] toArray() {
            return new Object[0];
        }

        @NonNull
        @Override
        public <T> T[] toArray(@NonNull T[] a) {
            return null;
        }

        @Override
        public boolean add(DoctorDetails doctorDetails) {
            return false;
        }

        @Override
        public boolean remove(Object o) {
            return false;
        }

        @Override
        public boolean containsAll(@NonNull Collection<?> c) {
            return false;
        }

        @Override
        public boolean addAll(@NonNull Collection<? extends DoctorDetails> c) {
            return false;
        }

        @Override
        public boolean addAll(int index, @NonNull Collection<? extends DoctorDetails> c) {
            return false;
        }

        @Override
        public boolean removeAll(@NonNull Collection<?> c) {
            return false;
        }

        @Override
        public boolean retainAll(@NonNull Collection<?> c) {
            return false;
        }

        @Override
        public void clear() {

        }

        @Override
        public DoctorDetails get(int index) {
            return null;
        }

        @Override
        public DoctorDetails set(int index, DoctorDetails element) {
            return null;
        }

        @Override
        public void add(int index, DoctorDetails element) {

        }

        @Override
        public DoctorDetails remove(int index) {
            return null;
        }

        @Override
        public int indexOf(Object o) {
            return 0;
        }

        @Override
        public int lastIndexOf(Object o) {
            return 0;
        }

        @Override
        public ListIterator<DoctorDetails> listIterator() {
            return null;
        }

        @NonNull
        @Override
        public ListIterator<DoctorDetails> listIterator(int index) {
            return null;
        }

        @NonNull
        @Override
        public List<DoctorDetails> subList(int fromIndex, int toIndex) {
            return null;
        }
    };
    public AppointmentConfirmation() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(com.example.ganesha.abpv.R.layout.fragment_appointment_confirmation, container, false);
        // [START create_database_reference]
        mDatabase = FirebaseDatabase.getInstance().getReference();
        // [END create_database_reference]

        mRecycler = (RecyclerView) rootView.findViewById(com.example.ganesha.abpv.R.id.doctor_list);
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

            final ProgressDialog dialog = new ProgressDialog(getActivity());
            dialog.setMessage("Appointments are Loading..");
            dialog.show();
            dialog.setProgress(300);

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    dialog.dismiss();
                }
            }, 1000);


            mAdapter = new FirebaseRecyclerAdapter<DoctorDetails, DoctorAppConfirmListHolder>(DoctorDetails.class, com.example.ganesha.abpv.R.layout.listview_item_display_doctor, DoctorAppConfirmListHolder.class, postsQuery) {
            @SuppressLint("SetTextI18n")
            @Override
            protected void populateViewHolder(DoctorAppConfirmListHolder viewHolder, final DoctorDetails model, final int position) {
                viewHolder.dAppointmentDate.setText(model.AppointmentDateD);
                viewHolder.dAppointmentID.setText(model.AppointmentIDD);
                viewHolder.dAppointmentTime.setText(model.AppointmentTimeD);
                viewHolder.dLastName.setText(model.LastNameD);
                viewHolder.dPatientID.setText(model.PatientIDD);

                viewHolder.confirmApp.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        LayoutInflater inflater = (LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                        View promptsView = inflater.inflate(com.example.ganesha.abpv.R.layout.custom_doctor_confirming, null);



                        builder.setView(promptsView);

                        final AlertDialog dialog = builder.create();

                        final TextView aAppID = (TextView) promptsView.findViewById(com.example.ganesha.abpv.R.id.AppId);
                        aAppID.setText(model.AppointmentIDD);
                        final TextView aAppointmentDatec=(TextView) promptsView.findViewById(com.example.ganesha.abpv.R.id.AppDate);
                        aAppointmentDatec.setText(model.AppointmentDateD);
                        final TextView aAppointmentTimec = (TextView) promptsView.findViewById(com.example.ganesha.abpv.R.id.AppTime);
                        aAppointmentTimec.setText(model.AppointmentTimeD);
                        final TextView aLName = (TextView) promptsView.findViewById(com.example.ganesha.abpv.R.id.LNAME);
                        aLName.setText(model.LastNameD);
                        final TextView aPID = (TextView) promptsView.findViewById(com.example.ganesha.abpv.R.id.PID);
                        aPID.setText(model.PatientIDD);

                        Button dialogButton = (Button) promptsView.findViewById(com.example.ganesha.abpv.R.id.confirmappointment);
                        // if button is clicked, close the custom dialog
                        dialogButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                               Holder.remove(position);
                                mAdapter.getRef(position).removeValue();
                                mAdapter.notifyItemRemoved(position);
                                dialog.dismiss();

                                Toast.makeText(getActivity(), "Appointment Confirmed Successfully", Toast.LENGTH_LONG).show();
                            }

                        });
                        dialog.show();

                    }
                });



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
