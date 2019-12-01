package com.inducesmile.humsafar.ui.main;

import android.content.Context;
import android.net.Uri;
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
 * {@link driverFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link driverFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

public class driverFragment extends Fragment {

    private PageViewModel pageViewModel;
    public driverFragment() {
        // Required empty public constructor
    }


    public static driverFragment newInstance() {
        driverFragment fragment = new driverFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_driver, container, false);
        return root;
    }

}
