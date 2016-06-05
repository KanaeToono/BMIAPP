package com.example.conga.bmi.abstracts;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



public abstract class BaseFragment extends Fragment {
    private static String TAG = BaseFragment.class.getSimpleName();
    protected View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(setLayoutById(), container, false);
        initViews();
        addEvents();
        return view;
    }

    protected abstract int setLayoutById();

    protected abstract void initViews();

    protected abstract void addEvents();

}
