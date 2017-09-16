package com.example.ganesha.abpv.MainActivities.MainActivities.PatientFragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ganesha.abpv.R;
import com.trncic.library.DottedProgressBar;

public class FindNearestGP extends Fragment {
    DottedProgressBar progressBar;

    public FindNearestGP() {
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
        View rootView= inflater.inflate(R.layout.fragment_find_nearest_g, container, false);

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
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
