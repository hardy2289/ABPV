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

import com.example.ganesha.abpv.MainActivities.MainActivities.Model.DoctorDetails;
import com.example.ganesha.abpv.MainActivities.MainActivities.Model.DoctorPrescriptionHolder;
import com.example.ganesha.abpv.MainActivities.MainActivities.Patient.FragmentSupport;
import com.example.ganesha.abpv.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;


public class PrescriptionConfirmation extends Fragment {

    FragmentSupport fragmentSupport = new FragmentSupport();
    final String userId = fragmentSupport.getUid();

    private DatabaseReference mDatabase;
    private FirebaseRecyclerAdapter<DoctorDetails, DoctorPrescriptionHolder> mAdapter;
    private RecyclerView mRecycler;
    private LinearLayoutManager mManager;
    public ImageView RemoveList;
    private RecyclerView myRecyclerView;
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
    private LinearLayoutManager linearLayoutManager;
    private static View view;
    private AdapterView.OnItemClickListener mItemClickListener;

    public PrescriptionConfirmation() {
        // Required empty public constructor
    }



    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_prescription_confirmation, container, false);
        // [START create_database_reference]
        mDatabase = FirebaseDatabase.getInstance().getReference();
        // [END create_database_reference]

        mRecycler = (RecyclerView) rootView.findViewById(R.id.doctor_prescription_list);
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

        final ProgressDialog dialog = new ProgressDialog(getActivity());
        dialog.setMessage("Patient Details are Loading..");
        dialog.show();
        dialog.setProgress(300);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                dialog.dismiss();
            }
        }, 1000);

        // Set up FirebaseRecyclerAdapter with the Query
        Query postsQuery = getQuery(mDatabase);
        // Query postsQuery = getQuery(mDatabase).orderByChild("shoppingStatus").equalTo("In Process");

        mAdapter = new FirebaseRecyclerAdapter<DoctorDetails, DoctorPrescriptionHolder>(DoctorDetails.class, R.layout.listview_prescription_patient_display, DoctorPrescriptionHolder.class, postsQuery) {
            @SuppressLint("SetTextI18n")
            @Override
            protected void populateViewHolder(DoctorPrescriptionHolder viewHolder, final DoctorDetails model, final int position) {

                viewHolder.AppointmentIDP.setText(model.AppointmentIDD);
                viewHolder.LastNameP.setText(model.LastNameD);
                viewHolder.PatientIDP.setText(model.PatientIDD);

                viewHolder.providePres.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        LayoutInflater inflater = (LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                        View promptsView = inflater.inflate(R.layout.prescription_view, null);

                        builder.setView(promptsView);

                        final AlertDialog dialog = builder.create();

                        final TextView aPID = (TextView) promptsView.findViewById(R.id.patientID);
                        aPID.setText(model.PatientIDD);
                        final TextView aLName = (TextView) promptsView.findViewById(R.id.patientName);
                        aLName.setText(model.LastNameD);
                        final TextView aPatientDOB = (TextView) promptsView.findViewById(R.id.patient_dob);
                        aPatientDOB.setText(model.DOB);
                        final TextView aAppointmentDatec=(TextView) promptsView.findViewById(R.id.appointment_date);
                        aAppointmentDatec.setText(model.AppointmentDateD);
                        final TextView aDrName = (TextView) promptsView.findViewById(R.id.doctor_Name);
                        aDrName.setText(model.DoctorName);
                        final TextView aDrID = (TextView) promptsView.findViewById(R.id.doctorid);
                        aDrID.setText(model.DoctorID);


                        Button dialogButton = (Button) promptsView.findViewById(R.id.prescriptionorder);
                        // if button is clicked, close the custom dialog
                        dialogButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Holder.remove(position);
                                mAdapter.notifyItemRemoved(position);
                                dialog.dismiss();

                                Toast.makeText(getActivity(), "Prescription Copy Has been Provided", Toast.LENGTH_LONG).show();
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
