package com.example.ganesha.abpv.MainActivities.MainActivities;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

/**
 * Created by Ganesha on 01/09/2017.
 */

public class BaseActivity extends AppCompatActivity {

    private static final String TAG = "EmailPassword";
    public Support support = new Support();
    protected Button mButtonRegisterRegister;
    private ProgressDialog mProgressDialog;
    private EditText mEmailFieldRegister;
    private EditText mPasswordFieldRegister;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;


    public void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage("Please Wait");
            mProgressDialog.setIndeterminate(true);
        }

        mProgressDialog.show();
    }


    public void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    public String getUid() {
        return FirebaseAuth.getInstance().getCurrentUser().getUid();
    }

    @Override
    public void onStop() {
        super.onStop();
        hideProgressDialog();
    }

}
