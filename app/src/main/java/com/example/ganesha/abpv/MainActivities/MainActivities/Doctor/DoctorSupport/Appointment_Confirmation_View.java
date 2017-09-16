package com.example.ganesha.abpv.MainActivities.MainActivities.Doctor.DoctorSupport;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.ganesha.abpv.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

import static com.example.ganesha.abpv.R.id.appointment;

public class Appointment_Confirmation_View extends Activity {
	// Declare Variables
	TextView PatientId;
    TextView PatientAppointmentdate;
    TextView DoctorName;
    TextView DoctorID;
    private DatabaseReference mDatabase;
	String P_ID;
    String P_APPOINTMENT;
    String P_DOCTOR_NAME;
    String P_DoctorId;

    Button ConfirmAppointment;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.confirm_single_item_view);
		// Retrieve data from booking appointment on item click event
		Intent i = getIntent();
        mDatabase = FirebaseDatabase.getInstance().getReference();
		P_ID = i.getStringExtra("PatientId");
        P_APPOINTMENT = i.getStringExtra("AppointmentDate");
        P_DOCTOR_NAME = i.getStringExtra("DoctorName");
        P_DoctorId = i.getStringExtra("DoctorID");


        // Locate the TextViews in singleitemview.xml

        PatientAppointmentdate = (TextView) findViewById(R.id.Appointment);
        DoctorName = (TextView) findViewById(R.id.Doctor_name);
        DoctorID = (TextView)findViewById(R.id.Doctor_id);
        PatientId = (TextView)findViewById(R.id.Appointment_no);


        ConfirmAppointment = (Button)findViewById(appointment);

		// Load the results into the TextViews

        PatientAppointmentdate.setText(P_APPOINTMENT);
        DoctorName.setText(P_DOCTOR_NAME);
        DoctorID.setText(P_DoctorId);
        PatientId.setText(P_ID);

        ConfirmAppointment.setOnClickListener(new View.OnClickListener() {

            String pid = PatientId.getText().toString();
            String pappointment = PatientAppointmentdate.getText().toString();
            String pdocotor = DoctorName.getText().toString().trim();
            String pdoctorId = DoctorID.getText().toString().trim();


            @Override
            public void onClick(View view) {
                DatabaseReference firebaseRef = FirebaseDatabase.getInstance().getReference();
// Create the author
                Map<String, Object> myAppointment = new HashMap();


                myAppointment.put("zPatientID", pid);
                myAppointment.put("AppointmentDate",pappointment);
                myAppointment.put("DoctorName",pdocotor);
                myAppointment.put("zDoctorID",pdoctorId);
                myAppointment.put("AppointmentID","No Number (Requested)");

                    Appointment_Confirmation_View.this.finish();

            }
        });




}}