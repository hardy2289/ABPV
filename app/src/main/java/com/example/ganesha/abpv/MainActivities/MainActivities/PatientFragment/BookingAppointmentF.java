package com.example.ganesha.abpv.MainActivities.MainActivities.PatientFragment;

import android.annotation.SuppressLint;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.ganesha.abpv.MainActivities.MainActivities.Doctor.DoctorDetailsHolder;
import com.example.ganesha.abpv.MainActivities.MainActivities.Model.DoctorDetails;
import com.example.ganesha.abpv.MainActivities.MainActivities.Patient.FragmentSupport;
import com.example.ganesha.abpv.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;


public class BookingAppointmentF extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    public ProgressBar Shopping;
    public DatabaseReference mShoppingPoint;
    public FloatingActionButton fab;
    FragmentSupport fragmentSupport = new FragmentSupport();
    final String userId = fragmentSupport.getUid();

    private DatabaseReference mDatabase;
    private FirebaseRecyclerAdapter<DoctorDetails, DoctorDetailsHolder> mAdapter;
    private RecyclerView mRecycler;
    private LinearLayoutManager mManager;
    private Location TODO = null;


    public TextView dTotalPatient;
    public TextView dFemalePatient;
    public TextView dMalePatient;
    public TextView dPopularDoctor;
    public TextView dTotalAppointments;
    public TextView DisplayPatientName;
    EditText SearchAppointment;


    private static View view;

    public BookingAppointmentF() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_booking_appointment, container, false);


        dTotalPatient = (TextView) view.findViewById(R.id.TotalPatient);
        dFemalePatient = (TextView) view.findViewById(R.id.FemalePat);
        dMalePatient=(TextView) view.findViewById(R.id.MalePatient);
        dPopularDoctor=(TextView) view.findViewById(R.id.popDoc);
        dTotalAppointments=(TextView) view.findViewById(R.id.TotalAppointments);
        DisplayPatientName = (TextView) view.findViewById(R.id.PatientName);

        // [START create_database_reference]
        mDatabase = FirebaseDatabase.getInstance().getReference();
        // [END create_database_reference]

        mRecycler = (RecyclerView) rootView.findViewById(R.id.points_list);
        mRecycler.setHasFixedSize(true);
       // fab = (FloatingActionButton) rootView.findViewById(R.id.fab_new_post);
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

        TextView head = (TextView) view.findViewById(R.id.textView);
        TextView head1 = (TextView) view.findViewById(R.id.textView2);
        TextView head2 = (TextView) view.findViewById(R.id.textView3);
        TextView head3 = (TextView) view.findViewById(R.id.textView4);
        TextView head4 = (TextView) view.findViewById(R.id.DocPopular);
        TextView head5 = (TextView) view.findViewById(R.id.AvailableApp);
        TextView head6 = (TextView) view.findViewById(R.id.DocAvailable);
        TextView head7 = (TextView) view.findViewById(R.id.Available);
        head.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
        head1.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        head2.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        head3.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        head4.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        head5.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        head6.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        head7.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        dTotalPatient.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        dMalePatient.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        dFemalePatient.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        dPopularDoctor.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        dTotalAppointments.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        DisplayPatientName.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
        SearchAppointment = (EditText) view.findViewById(R.id.ApponintmentSearch);
        SearchAppointment.setSelected(false);

        // Set up FirebaseRecyclerAdapter with the Query
        Query postsQuery = getQuery(mDatabase);
        // Query postsQuery = getQuery(mDatabase).orderByChild("shoppingStatus").equalTo("In Process");

        mAdapter = new FirebaseRecyclerAdapter<DoctorDetails, DoctorDetailsHolder>(DoctorDetails.class, R.layout.listview_item,
                DoctorDetailsHolder.class, postsQuery) {





            @SuppressLint("SetTextI18n")
            @Override
            protected void populateViewHolder(DoctorDetailsHolder viewHolder, DoctorDetails model, int position) {


                viewHolder.dDoctorID.setText(model.zDoctorID);
                viewHolder.dAppointmentDate.setText(model.TransactionCompletedAt);
                viewHolder.dDoctorName.setText(model.FirstName);
                viewHolder.dAppointmentID.setText(model.TotalPatient);

              /**  viewHolder.dTotalPatient.setText(model.TotalPatient);
                viewHolder.dTotalAppointments.setText(model.TotalAppointments);
                viewHolder.dMalePatient.setText(model.MalePatient);
                viewHolder.dFemalePatient.setText(model.FemalePatint);
                viewHolder.dPopularDoctor.setText(model.PopularDoctor); **/

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

        Query doctorapp = databaseReference.child("doctors").child(userId);

        // [END recent_posts_query]

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
