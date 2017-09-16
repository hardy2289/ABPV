package com.example.ganesha.abpv.MainActivities.MainActivities.DoctorFragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ganesha.abpv.R;
import com.trncic.library.DottedProgressBar;


public class ContactPatient extends Fragment {
    DottedProgressBar progressBar;

    public ContactPatient() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView= inflater.inflate(R.layout.fragment_contact_patient, container, false);



        progressBar = (DottedProgressBar) rootView.findViewById(R.id.chat_dot_progress);
        progressBar.startProgress();


        return rootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        progressBar.stopProgress();
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


}
