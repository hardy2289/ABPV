package com.example.ganesha.abpv.MainActivities.MainActivities.DoctorFragments;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.example.ganesha.abpv.MainActivities.MainActivities.Model.DocAppListHolder;
import com.example.ganesha.abpv.MainActivities.MainActivities.Model.DoctorDetails;
import com.example.ganesha.abpv.MainActivities.MainActivities.Patient.FragmentSupport;
import com.example.ganesha.abpv.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;


public class PatientAppointments extends Fragment {

    FragmentSupport fragmentSupport = new FragmentSupport();
    final String userId = fragmentSupport.getUid();

    private DatabaseReference mDatabase;
    private FirebaseRecyclerAdapter<DoctorDetails, DocAppListHolder> mAdapter;
    private RecyclerView mRecycler;
    private LinearLayoutManager mManager;
    public ImageView RemoveList;
    private RecyclerView myRecyclerView;
    private LinearLayoutManager linearLayoutManager;

    private static View view;
    private AdapterView.OnItemClickListener mItemClickListener;


    public PatientAppointments() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_patient_appointments, container, false);

        // [START create_database_reference]
        mDatabase = FirebaseDatabase.getInstance().getReference();
        // [END create_database_reference]

        mRecycler = (RecyclerView) rootView.findViewById(R.id.doctor_app_list);
        mRecycler.setHasFixedSize(true);

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

        mAdapter = new FirebaseRecyclerAdapter<DoctorDetails, DocAppListHolder>(DoctorDetails.class, R.layout.listview_app_doc_display, DocAppListHolder.class, postsQuery) {
            @SuppressLint("SetTextI18n")
            @Override
            protected void populateViewHolder(DocAppListHolder viewHolder, DoctorDetails model, int position) {
                viewHolder.AppointmentDateL.setText(model.AppointmentDateD);
                viewHolder.AppointmentIDL.setText(model.AppointmentIDD);
                viewHolder.AppointmentTimeL.setText(model.AppointmentTimeD);

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



    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
