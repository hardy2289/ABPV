package com.example.ganesha.abpv.MainActivities.MainActivities.Patient;

import android.app.Activity;
import android.app.ProgressDialog;

import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by Ganesha on 22/08/2017.
 */

public class FragmentSupport extends Activity {

    private ProgressDialog mProgressDialog;

    public String getUid() {
        return FirebaseAuth.getInstance().getCurrentUser().getUid();
    }

    public void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(FragmentSupport.this);
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


    @Override
    public void onStop() {
        super.onStop();
        hideProgressDialog();
    }
}
