package com.inducesmile.humsafar.ui.main;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.inducesmile.humsafar.R;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link riderFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link riderFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class riderFragment extends Fragment {

    private static final String TAG = "Rider";
    private PageViewModel pageViewModel;

    //private OnFragmentInteractionListener mListener;

    public riderFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment riderFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static riderFragment newInstance() {
        riderFragment fragment = new riderFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
        //pageViewModel.setIndex(TAG);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_rider, container, false );

        return root;
    }

}