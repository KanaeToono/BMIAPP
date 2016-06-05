package com.example.conga.bmi.fragments;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.conga.bmi.R;
import com.example.conga.bmi.adapters.PagerBMIAdapter;


public class TuVanCanNangFragment extends Fragment {
    private static String TAG = TuVanCanNangFragment.class.getSimpleName();
    public static final String ITEM_NAME = "iTem";
    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tuvancannangfragment, container, false);
        //view payer
        mViewPager = (ViewPager) v.findViewById(R.id.view_pager);
        mTabLayout = (TabLayout) v.findViewById(R.id.tab_layout);
        FragmentManager manager=getActivity().getSupportFragmentManager();
        PagerBMIAdapter adapter=new PagerBMIAdapter(manager);
        mViewPager.setAdapter(adapter);

        mTabLayout.setupWithViewPager(mViewPager);
        // mTabLayout.setupWithViewPager(mPager1);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));

        mTabLayout.setTabsFromPagerAdapter(adapter);

        return v;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("fragment_added", true);
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, " start");

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "PAUSE ");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "STOP ");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "on destroy view");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "DESTROY ");
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d(TAG, "ATTACH");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, "ON DETACH ");
    }
}
