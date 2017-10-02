package com.example.ganesha.abpv.MainActivities.MainActivities.PatientFragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ganesha.abpv.MainActivities.MainActivities.BaseActivity;
import com.example.ganesha.abpv.MainActivities.MainActivities.Model.PersonalDetails;
import com.example.ganesha.abpv.MainActivities.MainActivities.Model.Users;
import com.example.ganesha.abpv.MainActivities.MainActivities.Patient.FragmentSupport;
import com.example.ganesha.abpv.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


public class AddDetailsF extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    FragmentSupport fragmentSupport = new FragmentSupport();
    final String userId = fragmentSupport.getUid();
    private static final String TAG = "AddDetailsPHome";
    private static final String REQUIRED = "Required";
    private DatabaseReference mDatabase;
    private DatabaseReference mUserFirstName;
    private DatabaseReference mUserLastName;
    private DatabaseReference mUserDOB;
    private DatabaseReference mUserGender;
    private DatabaseReference mUserPhoneNo;
    private String[] state = {"Male", "Female", "Others"};
    private int mYear;
    private int mMonth;
    private int mDay;
    private EditText txtFirstName;
    private EditText txtLastName;
    private EditText txtDOB;
    private EditText txtPhoneNo;
    private AutoCompleteTextView txtGender;
    private Button btnSubmit;
    private FirebaseAuth mAuth;
    private static View view;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (view != null) {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null)
                parent.removeView(view);
        }
        try{



        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_add_details, container, false);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mUserFirstName = mDatabase.child("users").child(userId).child("FirstName");
        mUserLastName = mDatabase.child("users").child(userId).child("LastName");
        mUserDOB = mDatabase.child("users").child(userId).child("DOB");
        mUserGender = mDatabase.child("users").child(userId).child("Gender");
        mUserPhoneNo = mDatabase.child("users").child(userId).child("PhoneNo");
        mAuth = FirebaseAuth.getInstance();

        txtFirstName = (EditText) view.findViewById(R.id.editFirstName);
            txtFirstName.requestFocus();
        txtLastName = (EditText) view.findViewById(R.id.editLastName);
        txtDOB = (EditText) view.findViewById(R.id.editDOB);
        txtPhoneNo = (EditText) view.findViewById(R.id.editPhoneNo);
        btnSubmit = (Button) view.findViewById(R.id.btnAddDetails);


            mDatabase = FirebaseDatabase.getInstance().getReference();
            mDatabase.child("users").child(new BaseActivity().getUid()).addValueEventListener(new ValueEventListener() {
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Users user = dataSnapshot.getValue(Users.class);
                    Bundle bundle = new Bundle();

                    if (user.DOB.isEmpty()) {
                        // User is null, error out
                        Toast.makeText(getActivity(), "Please Update your Profile", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.select_dialog_singlechoice, state);
        txtGender = (AutoCompleteTextView) view.findViewById(R.id.editGender);
        //Set the number of characters the user must type before the drop down list is shown
        txtGender.setThreshold(1);
        //Set the adapter
        txtGender.setAdapter(adapter);
        txtDOB.setInputType(0);
        txtDOB.setOnClickListener(new View.OnClickListener() {
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
                        txtDOB.setFocusable(false);
                        txtDOB.setText(i3 + "/" + (i2 + 1) + "/" + i);
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
        mUserFirstName.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String text = dataSnapshot.getValue(String.class);
                txtFirstName.setText(text);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        mUserLastName.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String text = dataSnapshot.getValue(String.class);
                txtLastName.setText(text);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        mUserDOB.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String text = dataSnapshot.getValue(String.class);
                txtDOB.setText(text);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        mUserGender.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String text = dataSnapshot.getValue(String.class);
                txtGender.setText(text);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        mUserPhoneNo.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String text = dataSnapshot.getValue(String.class);
                txtPhoneNo.setText(text);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                submitProfile();

            }
        });

    }catch (InflateException e) {
        /* map is already there, just return view as it is */
        }

        return view;
    }

    private void onDataChange() {
    }

    private void submitProfile() {

        final String sFirstName = txtFirstName.getText().toString();
        final String sLastName = txtLastName.getText().toString();
        final String sDateOfBirth = txtDOB.getText().toString();
        final String sGender = txtGender.getText().toString();
        final String sPhoneNo=txtPhoneNo.getText().toString();
        // FirstName is required
        if (TextUtils.isEmpty(sFirstName)) {
            txtFirstName.setError(REQUIRED);
            return;  }
        // LastName is required
        if (TextUtils.isEmpty(sLastName)) {
            txtLastName.setError(REQUIRED);
            return;  }
        // DateOfBirth is required
        if (TextUtils.isEmpty(sDateOfBirth)) {
            txtDOB.setError(REQUIRED);
            return;  }
        // Gender is required
        if (TextUtils.isEmpty(sGender)) {
            txtGender.setError(REQUIRED);
            return;  }
        //Phone No is Required
        if (TextUtils.isEmpty(sPhoneNo)) {
            txtPhoneNo.setError(REQUIRED);
            return;  }


        final String sEmail = mAuth.getCurrentUser().getEmail();
        final String sUserName = usernameFromEmail(mAuth.getCurrentUser().getEmail());
        final String authentication = FirebaseInstanceId.getInstance().getToken();



        if (mAuth.getCurrentUser().getProviders().contains("password")) {
            final String userId = fragmentSupport.getUid();
            mDatabase.child("users");
            mDatabase.child(userId);
            mDatabase.addListenerForSingleValueEvent(
                    new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            // Get user value
                            Users user = dataSnapshot.getValue(Users.class);
                            if (user == null) {
                                // User is null, error out
                                Log.e(TAG, "User " + userId + " is unexpectedly null");
                                Toast.makeText(getActivity() ,
                                        "Error: could not fetch user.",
                                        Toast.LENGTH_SHORT).show();
                            }
                            else {
                                writeToProfile(authentication, sDateOfBirth, sEmail, sFirstName, sGender,
                                        sLastName, sPhoneNo, userId, sUserName);
                                Toast.makeText(getActivity(), "Profile updated Successfully", Toast.LENGTH_LONG).show();
                            }
                        }
                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            Log.w(TAG, "getUser:onCancelled", databaseError.toException());
                        }
                    });
            // [END single_value_read]


        } else {

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



                                writeToProfile(authentication, sDateOfBirth, sEmail, sFirstName,userId, sGender, sLastName,sPhoneNo, sUserName);
                                Toast.makeText(getActivity(), "Profile updated Successfully", Toast.LENGTH_LONG).show();

                            }
                        }
                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            Log.w(TAG, "getUser:onCancelled", databaseError.toException());
                        }
                    });
            // [END single_value_read
        }
//this.finish();
    }

    private String usernameFromEmail(String email) {
        if (email.contains("@")) {
            return email.split("@")[0];
        } else {
            return email;
        }
    }



    public void writeToProfile(String authentication,String dateofbirth, String email,String firstname,
                               String gender, String lastname, String phoneno, String userId,String username ) {


        PersonalDetails personalDetails = new PersonalDetails(authentication, dateofbirth, email,firstname,
                gender,  lastname,phoneno,  userId, username  );
        Map<String, Object> myDetailsValues = personalDetails.toMap();

        Map<String, Object> childUpdates = new HashMap<>();

        childUpdates.put("/users/" + userId + "/", myDetailsValues);

        mDatabase.updateChildren(childUpdates);
    }

}
