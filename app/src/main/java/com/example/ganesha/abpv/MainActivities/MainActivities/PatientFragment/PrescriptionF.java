package com.example.ganesha.abpv.MainActivities.MainActivities.PatientFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ganesha.abpv.MainActivities.MainActivities.Patient.FragmentSupport;
import com.example.ganesha.abpv.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class PrescriptionF extends Fragment {

    FragmentSupport fragmentSupport = new FragmentSupport();
    final String userId = fragmentSupport.getUid();

    private static final String REQUIRED = "Required";
    private static final String TAG = "MakeAppointment";
    private static View view;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    protected TextView PatientIDP;
    protected TextView PatientLastNameP;
    protected TextView PatientDOBP;
    protected TextView AppDateP;
    protected TextView DrNameP;
    protected TextView DrIDP;
    protected Button Order;


    private DatabaseReference PID;
    private DatabaseReference PLN;
    private DatabaseReference PDOB;
    private DatabaseReference AD;
    private DatabaseReference DN;
    private DatabaseReference DI;

    public PrescriptionF() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        if (view != null) {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null)
                parent.removeView(view);
        }
        try {
            view = inflater.inflate(R.layout.fragment_prescription, container, false);


          mDatabase = FirebaseDatabase.getInstance().getReference();
           PID= mDatabase.child("appointment").child(userId).child("app1").child("zPatientID");
            PLN= mDatabase.child("appointment").child(userId).child("app1").child("LastName");
            PDOB= mDatabase.child("appointment").child(userId).child("app1").child("DOB");
            AD= mDatabase.child("appointment").child(userId).child("app1").child("AppointmentDate");
            DN = mDatabase.child("appointment").child(userId).child("app1").child("DoctorName");
          DI = mDatabase.child("appointment").child(userId).child("app2").child("zDoctorID");


            PatientIDP = (TextView) view.findViewById(R.id.patientIDPP);
            PatientLastNameP = (TextView) view.findViewById(R.id.patientNamePP);
            PatientDOBP = (TextView) view.findViewById(R.id.patient_dobPP);
            AppDateP = (TextView) view.findViewById(R.id.appointment_datePP);
            DrNameP = (TextView) view.findViewById(R.id.doctor_NamePP);
            DrIDP = (TextView) view.findViewById(R.id.doctoridPP);
            Order=(Button)view.findViewById(R.id.repeatprescriptionorder);


            PID.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String text = dataSnapshot.getValue(String.class);
                    PatientIDP.setText(text) ;
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            });


            PLN.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String text = dataSnapshot.getValue(String.class);
                    PatientLastNameP.setText(text);
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            });

            PDOB.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String text = dataSnapshot.getValue(String.class);
                    PatientDOBP.setText(text);
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            });

            DN.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String text = dataSnapshot.getValue(String.class);
                    DrNameP.setText(text);
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            });

           DI.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String text = dataSnapshot.getValue(String.class);
                    DrIDP.setText(text);
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            });

            AD.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String text = dataSnapshot.getValue(String.class);
                    AppDateP.setText(text);
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            });


            Order.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getActivity(), "Request has been made to Order Repeat Prescription", Toast.LENGTH_LONG).show();

                }
            });



        }
             catch (InflateException e) {
        /* map is already there, just return view as it is */
            }
            return view;
        }


    }


