package com.example.conga.bmi.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.conga.bmi.R;


public class NoteBookFragment extends Fragment {
    private static String TAG = NoteBookFragment.class.getSimpleName();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.notebookfragment, container, false);
        Fragment noteBookDetailsFrag = new NoteBookDetailsFragment();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction transition = fragmentManager.beginTransaction().replace(R.id.containernotefrg, noteBookDetailsFrag);
        transition.commit();
        return view;

    }
}
