package com.example.ganesha.abpv.MainActivities.MainActivities.DoctorFragments;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ganesha.abpv.MainActivities.MainActivities.Doctor.DoctorSupport.Doctor_ConfirmAppointment_ListViewAdapter;
import com.example.ganesha.abpv.MainActivities.MainActivities.Doctor.DoctorSupport.Doctor_ConfirmAppointment_support;
import com.example.ganesha.abpv.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class AppointmentConfirmation extends Fragment {
    ListView listview;
    DataSnapshot snapshot;
    ProgressDialog mProgressDialog;
    Doctor_ConfirmAppointment_ListViewAdapter adapter;
    private List<Doctor_ConfirmAppointment_support> AppointmentConfirmList = null;
    private static View view;
    private DatabaseReference mDatabase;

    public AppointmentConfirmation() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (view != null) {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null)
                parent.removeView(view);
        }
        try {
            view = inflater.inflate(R.layout.fragment_appointment_confirmation, container, false);
            new RemoteDataTask().execute();
            TextView head = (TextView) view.findViewById(R.id.textView);
            TextView empty = (TextView) view.findViewById(R.id.noAppointments);



        }

        catch (InflateException e) {
        /* map is already there, just return view as it is */
        }

        return view;
    }


    public void showAlertDialog(Context context, String title, String message, Boolean status) {


        AlertDialog alertDialog = new AlertDialog.Builder(context).create();

        // Setting Dialog Title
        alertDialog.setTitle(title);

        // Setting Dialog Message
        alertDialog.setMessage(message);


        // Setting OK Button
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        // Showing Alert Message
        alertDialog.show();

    }




    private class  RemoteDataTask extends AsyncTask<Void, Void, Void>  {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Create a progressdialog
            mProgressDialog = new ProgressDialog(getActivity());
            // Set progressdialog title
            mProgressDialog.setTitle("Prescription");
            // Set progressdialog message
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setIndeterminate(false);
            // Show progressdialog
            mProgressDialog.show();
        }

        protected Void doInBackground( Void... params) {
            // Create the array
            AppointmentConfirmList = new ArrayList<Doctor_ConfirmAppointment_support>();
            try {
                // Locate the class table named "PatientAppointmentRequest" in Parse.com
                mDatabase = FirebaseDatabase.getInstance().getReference();

                Query mQueryRef = mDatabase.child("appointment").child("Uid").orderByChild("DoctorName").equalTo("Dr Shah");
                // Locate the column named "DrName" in Parse.com and getting appointment to
                // Particular doctor


                 mQueryRef.addChildEventListener(new ChildEventListener() {

                    public void onChildAdded(DataSnapshot snapshot, String previousChild) {
                        // This will fire for each matching child node.
                        Map<String, Object> post = new HashMap<String, Object>();

                    Doctor_ConfirmAppointment_support map = snapshot.getValue(Doctor_ConfirmAppointment_support.class);
                    map.setPatient_Id((String) post.get("zPatientID"));
                    map.setPatient_Name((String) post.get("LastName"));
                    map.setDate_of_Birth((String) post.get("DOB"));
                    map.setAppointment_Date((String) post.get("AppointmentDate"));
                    map.setDoctorname((String) post.get("DoctorName"));
                    map.setDocId((String) post.get("zDoctorID"));
                    map.setPatientContactNo((String) post.get("PhoneNo"));

                    AppointmentConfirmList.add(map);
                    }

                    @Override
                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onChildRemoved(DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }  catch (InflateException e) {
        /* map is already there, just return view as it is */
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            TextView noApp = (TextView) view.findViewById(R.id.noAppointments);
            // Locate the listview in activity_booking_appointmenting_appointment.xml
            listview = (ListView) view.findViewById(R.id.ConfirmListView);
            // Pass the results into Booking_ListViewAdapter.java
            adapter = new Doctor_ConfirmAppointment_ListViewAdapter(getActivity(),
                    AppointmentConfirmList);
            // Binds the Adapter to the ListView
            if (adapter.getCount() != 0) {
                listview.setAdapter(adapter);
                noApp.setVisibility(View.GONE);
            } else {
                Toast.makeText(getActivity(), "No Appointments Requested", Toast.LENGTH_SHORT).show();
                noApp.setVisibility(View.VISIBLE);

            }
            // Close the progressdialog
            mProgressDialog.dismiss();
        }
    /**
     *
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */

    }
}