package com.example.ganesha.abpv.MainActivities.MainActivities.PatientFragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.ganesha.abpv.MainActivities.MainActivities.Model.AppointmentDetails;
import com.example.ganesha.abpv.MainActivities.MainActivities.Model.Users;
import com.example.ganesha.abpv.MainActivities.MainActivities.Patient.FragmentSupport;
import com.example.ganesha.abpv.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


public class RequestAppointmentF extends Fragment implements AdapterView.OnItemSelectedListener{


    FragmentSupport fragmentSupport = new FragmentSupport();
    final String userId = fragmentSupport.getUid();

    private static final String REQUIRED = "Required";
    private static final String TAG = "MakeAppointment";

    protected EditText RPatientNamePatient;
    protected EditText RAppointmentDatePatient;
    protected EditText RAppointmentTimePatient;
    protected EditText RpatientPhone;
    protected EditText RPatientDOB;
    protected Button RAppointmentRequestbtn;
    Spinner spinnerDoctor;
    ProgressDialog mProgressDialog;
    String Doctor;
    private int mYear;
    private int mMonth;
    private int mDay;
    private String[] state = {"Dr Patel", "Dr Bakari", "Dr Shah"};
    private static View view;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private DatabaseReference mAppointmentDate;
    private DatabaseReference mAppointmentID;
    private DatabaseReference mAppointmentTime;
    private DatabaseReference mDoctorName;

    private DatabaseReference mPatientDOB;
    private DatabaseReference mPatientName;
    private DatabaseReference mPatientNo;



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
            view = inflater.inflate(R.layout.fragment_request_appointment, container, false);

            mDatabase = FirebaseDatabase.getInstance().getReference();

            mAppointmentDate = mDatabase.child("appointments").child(userId).child("AppointmentDate");
            mAppointmentTime = mDatabase.child("appointments").child(userId).child("AppointmentTime");
            mDoctorName = mDatabase.child("appointments").child(userId).child("DoctorName");

            mPatientName = mDatabase.child("users").child(userId).child("LastName");
            mPatientDOB = mDatabase.child("users").child(userId).child("DOB");
            mPatientNo = mDatabase.child("users").child(userId).child("PhoneNo");

            spinnerDoctor = (Spinner) view.findViewById(R.id.RDoctor);
            RAppointmentDatePatient = (EditText) view.findViewById(R.id.RAppointmentDate);
            RAppointmentDatePatient.requestFocus();
            RAppointmentTimePatient = (EditText) view.findViewById(R.id.RAppointmentTime);
            RPatientNamePatient = (EditText) view.findViewById(R.id.RPatientName);
            RpatientPhone = (EditText) view.findViewById(R.id.PatientPhone);
            RAppointmentRequestbtn = (Button) view.findViewById(R.id.Request);
            RPatientDOB =(EditText) view.findViewById(R.id.txtPatientDate);

            mPatientName.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String text = dataSnapshot.getValue(String.class);
                    RPatientNamePatient.setText(text);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            mPatientDOB.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String text = dataSnapshot.getValue(String.class);
                    RPatientDOB.setText(text);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            mPatientNo.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String text = dataSnapshot.getValue(String.class);
                    RpatientPhone.setText(text);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            ArrayAdapter<String> adapter_state = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, state);

            adapter_state.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            spinnerDoctor.setAdapter(adapter_state);
            spinnerDoctor.setOnItemSelectedListener(this);
            // setting up prompt to spinner as a select Doctor
            spinnerDoctor.setPrompt("Select Doctor");
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);



            // setting onClick listener on editText to select Appointments Time
            RAppointmentTimePatient.setInputType(0);
            RAppointmentTimePatient.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Calendar mAppointmentTime = Calendar.getInstance();
                    int hour = mAppointmentTime.get(Calendar.HOUR_OF_DAY);
                    int minute = mAppointmentTime.get(Calendar.MINUTE);
                    TimePickerDialog mTimePicker;
                    mTimePicker = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                         //   RAppointmentTimePatient.setFocusable(false);
                            RAppointmentTimePatient.setText(selectedHour + ":" + selectedMinute);
                        }
                    }, hour, minute, true);//Yes 24 hour time
                    mTimePicker.setTitle("Select Appointments Time");

                    mTimePicker.show();
                }
            });

            // setting onClick Listener on editText to select Appointments Date
            RAppointmentDatePatient.setInputType(0);
            RAppointmentDatePatient.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {

                    Calendar mAppointmentDate = Calendar.getInstance();
                    mYear = mAppointmentDate.get(Calendar.YEAR);
                    mMonth = mAppointmentDate.get(Calendar.MONTH);
                    mDay = mAppointmentDate.get(Calendar.DAY_OF_MONTH);
                    DatePickerDialog mDatePicker;

                    mDatePicker = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker datePicker, int i, int i2, int i3) {

                            RAppointmentDatePatient.setText(i3 + "-" + (i2+1) + "-"+ i);
                        }
                    }, mDay, mMonth, mYear);//Yes 24 hour time

                    mDatePicker.updateDate(mYear, mMonth, mDay);
                    long mSetMonth = 262800000*10;
                    mDatePicker.setTitle("Select Appointments Date");
                    mDatePicker.getDatePicker().setMinDate(System.currentTimeMillis() + 86400000);
                    mDatePicker.getDatePicker().setMaxDate(System.currentTimeMillis() - mSetMonth);

                    mDatePicker.show();
                }
            });

            RPatientDOB.setInputType(0);
            RPatientDOB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Calendar mDateofbirth = Calendar.getInstance();
                    mYear = mDateofbirth.get(Calendar.YEAR);
                    mMonth = mDateofbirth.get(Calendar.MONTH);
                    mDay = mDateofbirth.get(Calendar.DAY_OF_MONTH);
                    DatePickerDialog mDatePicker;

                    mDatePicker = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker datePicker, int i, int i2, int i3) {

                            RPatientDOB.setText(i3 + "-" + (i2 + 1) + "-" + i);
                        }
                    }, mDay, mMonth, mYear);//Yes 24 hour time

                    mDatePicker.setTitle("Select Date of Birth");
                    mDatePicker.updateDate(mYear - 18, mMonth, mDay);
                    mDateofbirth.set(mYear - 18, mMonth, mDay);
                    //mDatePicker.getDatePicker().setMaxDate(System.currentTimeMillis());
                    mDatePicker.getDatePicker().setMaxDate(mDateofbirth.getTimeInMillis());

                    mDatePicker.show();

                }
            });



            mAppointmentDate.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String text = dataSnapshot.getValue(String.class);
                    RAppointmentDatePatient.setText(text);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            mAppointmentTime.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String text = dataSnapshot.getValue(String.class);
                    RAppointmentTimePatient.setText(text);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            mPatientDOB.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String text = dataSnapshot.getValue(String.class);
                    RPatientDOB.setText(text);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            mDoctorName.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String text = dataSnapshot.getValue(String.class);
                    spinnerDoctor.getSelectedItem();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });



            mPatientName.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String text = dataSnapshot.getValue(String.class);
                    RPatientNamePatient.setText(text);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            mPatientNo.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String text = dataSnapshot.getValue(String.class);
                    RpatientPhone.setText(text);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });


            RAppointmentRequestbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    submitProfile();
                }

            });

           }
        catch (InflateException e) {
        /* map is already there, just return view as it is */
        }

        return view;
    }

    private void submitProfile() {


        final String sAppointmentDate = RAppointmentDatePatient.getText().toString();
        final String sAppointmentTime = RAppointmentTimePatient.getText().toString();
        final String sDoctorName = spinnerDoctor.getSelectedItem().toString().trim();
        final String sPatientName = RPatientNamePatient.getText().toString();
        final String sPatientPhoneNo = RpatientPhone.getText().toString();
        final String sPatientDob = RPatientDOB.getText().toString();
        final String sAppointmentID= " ", AStatus=" ";
         final String szDoctorID=" ", szPatientID=" ";

        // LastName is required
        if (TextUtils.isEmpty(sAppointmentDate)) {
            RAppointmentDatePatient.setError(REQUIRED);
            return;
        }
        // DateOfBirth is required
        if (TextUtils.isEmpty(sAppointmentTime)) {
            RAppointmentTimePatient.setError(REQUIRED);
            return;
        }
        // Gender is required
        if (TextUtils.isEmpty(sPatientName)) {
            RPatientNamePatient.setError(REQUIRED);
            return;
        }
        //Phone No is Required
        if (TextUtils.isEmpty(sPatientPhoneNo)) {
            RpatientPhone.setError(REQUIRED);
            return;
        }

        //Phone No is Required
        if (TextUtils.isEmpty(sPatientDob)) {
            RPatientDOB.setError(REQUIRED);
            return;
        }


            final String userId = fragmentSupport.getUid();
            mDatabase.child("users");
            mDatabase.child(userId);
            mDatabase.addListenerForSingleValueEvent(
                    new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            // Get user value
                            Users user = dataSnapshot.getValue(Users.class);
                            // [START_EXCLUDE]
                            if (user == null) {
                                // User is null, error out
                                Log.e(TAG, "User " + userId + " is unexpectedly null");
                                Toast.makeText(getActivity(),
                                        "Error: could not fetch user.",
                                        Toast.LENGTH_SHORT).show();
                            } else {
                                // Update User Details
                                writeToProfile(AStatus,sAppointmentDate,sAppointmentID, sAppointmentTime, sPatientDob,
                                        sDoctorName, sPatientName, sPatientPhoneNo, szDoctorID, szPatientID, userId);
                                Toast.makeText(getActivity(), "Appointment Request Has Been Made", Toast.LENGTH_LONG).show();
                            }
                        }
                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            Log.w(TAG, "getUser:onCancelled", databaseError.toException());
                        }
                    });
            // [END single_value_read]


    }
    public void writeToProfile(String AStatus, String AppointmentDate, String AppointmentID,  String AppointmentTime,String DOB,
               String DoctorName, String LastName, String PhoneNo , String zDoctorID, String zPatientID,String userId) {

        AppointmentDetails appointmentDetails = new AppointmentDetails(AStatus,AppointmentDate,AppointmentID,  AppointmentTime,DOB,
                DoctorName, LastName, PhoneNo, zDoctorID, zPatientID, userId);
        Map<String, Object> myDetailsValues = appointmentDetails.toMap();

        Map<String, Object> childUpdates = new HashMap<>();

        childUpdates.put("/appointment/" + userId + "/1/" +"/" , myDetailsValues);

        mDatabase.updateChildren(childUpdates);
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        spinnerDoctor.setSelection(i);
        String selState = (String) spinnerDoctor.getSelectedItem();
        Doctor = selState;

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

        Toast.makeText(getActivity(), "Please Select Doctor", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onStop() {
        super.onStop();
        //this.finish();

    }
    // TODO: Rename method, update argument and hook method into UI event

    public void onBackPressed() {
        new AlertDialog.Builder(getActivity())
                .setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        getActivity().finish();
                                            }
                })
                .setNegativeButton("No", null)
                .show();


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

    public RequestAppointmentF() {
        // Required empty public constructor
    }

}
