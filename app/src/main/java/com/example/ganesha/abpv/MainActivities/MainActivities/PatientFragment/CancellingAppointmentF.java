package com.example.ganesha.abpv.MainActivities.MainActivities.PatientFragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ganesha.abpv.MainActivities.MainActivities.Model.Appointments;
import com.example.ganesha.abpv.MainActivities.MainActivities.Model.BookedAppointmentHolder;
import com.example.ganesha.abpv.MainActivities.MainActivities.Patient.FragmentSupport;
import com.example.ganesha.abpv.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;


public class CancellingAppointmentF extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    FragmentSupport fragmentSupport = new FragmentSupport();
    final String userId = fragmentSupport.getUid();
    private DatabaseReference mDatabase;
    private FirebaseRecyclerAdapter<Appointments, BookedAppointmentHolder> mAdapter;
    private RecyclerView mRecycler;
    private LinearLayoutManager mManager;
    public ImageView RemoveList;
    private RecyclerView myRecyclerView;
    private LinearLayoutManager linearLayoutManager;
    private static View view;
    private List<Appointments> Holder= new List<Appointments>() {
        @Override
        public int size() {
            return 0;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public boolean contains(Object o) {
            return false;
        }

        @NonNull
        @Override
        public Iterator<Appointments> iterator() {
            return null;
        }

        @NonNull
        @Override
        public Object[] toArray() {
            return new Object[0];
        }

        @NonNull
        @Override
        public <T> T[] toArray(@NonNull T[] a) {
            return null;
        }

        @Override
        public boolean add(Appointments appointments) {
            return false;
        }

        @Override
        public boolean remove(Object o) {
            return false;
        }

        @Override
        public boolean containsAll(@NonNull Collection<?> c) {
            return false;
        }

        @Override
        public boolean addAll(@NonNull Collection<? extends Appointments> c) {
            return false;
        }

        @Override
        public boolean addAll(int index, @NonNull Collection<? extends Appointments> c) {
            return false;
        }

        @Override
        public boolean removeAll(@NonNull Collection<?> c) {
            return false;
        }

        @Override
        public boolean retainAll(@NonNull Collection<?> c) {
            return false;
        }

        @Override
        public void clear() {

        }

        @Override
        public Appointments get(int index) {
            return null;
        }

        @Override
        public Appointments set(int index, Appointments element) {
            return null;
        }

        @Override
        public void add(int index, Appointments element) {

        }

        @Override
        public Appointments remove(int index) {
            return null;
        }

        @Override
        public int indexOf(Object o) {
            return 0;
        }

        @Override
        public int lastIndexOf(Object o) {
            return 0;
        }

        @Override
        public ListIterator<Appointments> listIterator() {
            return null;
        }

        @NonNull
        @Override
        public ListIterator<Appointments> listIterator(int index) {
            return null;
        }

        @NonNull
        @Override
        public List<Appointments> subList(int fromIndex, int toIndex) {
            return null;
        }
    };
    private AdapterView.OnItemClickListener mItemClickListener;
    private OnFragmentInteractionListener mListener;
    public Button CancelApp;
    public Button BookedApp;

    public CancellingAppointmentF() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_cancelling_appointment, container, false);
        // [START create_database_reference]
        mDatabase = FirebaseDatabase.getInstance().getReference();
        // [END create_database_reference]
        mRecycler = (RecyclerView) rootView.findViewById(R.id.app_cancelling_list);
        mRecycler.setHasFixedSize(true);
// Set up Layout Manager, reverse layout

        CancelApp=(Button) rootView.findViewById(R.id.CancelHistory);

        CancelApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCancelledHistory fragment2 = new AppCancelledHistory();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, fragment2);
                fragmentTransaction.commit();
            }
        });

        BookedApp=(Button) rootView.findViewById(R.id.BookingHistory);
        BookedApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppBookedHistory fragment3 = new AppBookedHistory();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, fragment3);
                fragmentTransaction.commit();
            }
        });
        return rootView;
    }
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mManager = new LinearLayoutManager(getActivity());
        mManager.setReverseLayout(true);
        mManager.setStackFromEnd(true);
        mRecycler.setLayoutManager(mManager);
        mDatabase = FirebaseDatabase.getInstance().getReference();
//    RemoveList=(ImageView)view.findViewById(R.id.imageView12);
        // Set up FirebaseRecyclerAdapter with the Query
        Query postsQuery = getQuery(mDatabase);
        // Query postsQuery = getQuery(mDatabase).orderByChild("shoppingStatus").equalTo("In Process");
        mAdapter = new FirebaseRecyclerAdapter<Appointments, BookedAppointmentHolder>(Appointments.class, R.layout.listview_booked_display, BookedAppointmentHolder.class, postsQuery) {
            @SuppressLint("SetTextI18n")
            @Override
            protected void populateViewHolder(BookedAppointmentHolder viewHolder, final Appointments model, final int position) {

                viewHolder.bDoctorName.setText(model.DoctorName);
                viewHolder.bAppointmentTime.setText(model.AppointmentTime);
                viewHolder.bAppointmentDate.setText(model.AppointmentDate);
                viewHolder.removeC.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                        View promptsView = inflater.inflate(R.layout.customenew, null);

                        builder.setView(promptsView);

                        final AlertDialog dialog = builder.create();

                        final TextView aAppointmentDateb = (TextView) promptsView.findViewById(R.id.Appointment_Dateb);
                        aAppointmentDateb.setText(model.AppointmentDate);
                        final TextView aAppointmentTimeb = (TextView) promptsView.findViewById(R.id.Appointment_Timeb);
                        aAppointmentTimeb.setText(model.AppointmentTime);
                        final TextView aDoctorNamec = (TextView) promptsView.findViewById(R.id.Doctor_nameb);
                        aDoctorNamec.setText(model.DoctorName);

                        Button dialogButton = (Button) promptsView.findViewById(R.id.cancelappointment);
                        // if button is clicked, close the custom dialog
                        dialogButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Holder.remove(position);
                                mAdapter.getRef(position).removeValue();
                                mAdapter.notifyItemRemoved(position);
                                dialog.dismiss();

                                Toast.makeText(getActivity(), "Appointment Cancelled Successfully", Toast.LENGTH_LONG).show();
                            }

                        });
                        dialog.show();

                    }
                });
            }
        };
        mRecycler.setAdapter(mAdapter);
    }


    public String getUid() {
        return FirebaseAuth.getInstance().getCurrentUser().getUid();
    }

    public void onDestroy() {
        super.onDestroy();
        if (mAdapter != null) {
            mAdapter.cleanup();
        }
    }

    public Query getQuery(DatabaseReference databaseReference) {
        Query doctorapp = databaseReference.child("appointment").child(userId).child("/");
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
