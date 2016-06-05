package com.example.conga.bmi.fragments;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.conga.bmi.R;


public class ThoiQuenSinhHoatBMIFrag extends Fragment {
    public static final  String ITEM_NAME ="iTem" ;
    private static String TAG = ThoiQuenSinhHoatBMIFrag.class.getSimpleName();
    private TextView mAdultTextView;
    private TextView mChildTextView;
    private TextView mChildNormalTextView;
    private TextView mChildConsult1TextView;
    private TextView mChildConsult2TextView;
    private TextView mChildConsult3TextView;
    private TextView mChildUnderWeightTextView;
    private TextView mChildUnderWeightConsult1TextView;
    private TextView mChildUnderWeightConsult2TextView;
    private TextView mChildUnderWeightConsult3TextView;
    private TextView mChildOverWeightTextView;
    private TextView mChildOverWeightConsult1TextView;
    private TextView mChildOverWeightConsult2TextView;
    private TextView mChildOverWeightConsult3TextView;
    private TextView mOverWeightTextView;
    private TextView mCOverWeightConsult1TextView;
    private TextView mOverWeightConsult2TextView;
    private TextView mOverWeightConsult3TextView;
    private TextView mNormalWeightTextView;
    private TextView mNormalWeightConsult1TextView;
    private TextView mNormalWeightConsult2TextView;
    private TextView mNormalWeightConsult3TextView;
    private TextView mUnderWeightTextView;
    private TextView mUnderWeightConsult1TextView;
    private TextView mUnderWeightConsult2TextView;
    private TextView mUnderWeightConsult3TextView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v;
        v = inflater.inflate(R.layout.thoiquensinhhoatweightfrag, container, false);
        // get id
        mAdultTextView = (TextView) v.findViewById(R.id.adultTextView);
        mChildTextView = (TextView) v.findViewById(R.id.childweightBmiTextView);
        // get id aduld
        // normal
        mNormalWeightTextView = (TextView) v.findViewById(R.id.normalBmiTextView);
        mNormalWeightConsult1TextView = (TextView) v.findViewById(R.id.normalBmiconsult1TextView);
        mNormalWeightConsult2TextView = (TextView) v.findViewById(R.id.normalBmiconsult2TextView);
        mNormalWeightConsult3TextView = (TextView) v.findViewById(R.id.normalBmiconsult3TextView);
        //under weight
        mUnderWeightTextView = (TextView) v.findViewById(R.id.underweightBmiTextView);
        mUnderWeightConsult1TextView = (TextView) v.findViewById(R.id.underweightBmiconsult1TextView);
        mUnderWeightConsult2TextView = (TextView) v.findViewById(R.id.underweightBmiconsult2TextView);
        mUnderWeightConsult3TextView = (TextView) v.findViewById(R.id.underweightBmiconsult3TextView);
        //over weight
        mOverWeightTextView = (TextView) v.findViewById(R.id.overweightBmiTextView);
        mCOverWeightConsult1TextView = (TextView) v.findViewById(R.id.overrweightBmiconsult1TextView);
        mOverWeightConsult2TextView = (TextView) v.findViewById(R.id.overweightBmiconsult2TextView);
        mOverWeightConsult3TextView = (TextView) v.findViewById(R.id.overweightBmiconsult3TextView);
        // child
        // normal
        mChildNormalTextView = (TextView) v.findViewById(R.id.normalweightBmiTextView);
        mChildConsult1TextView = (TextView) v.findViewById(R.id.childnormalweightBmiconsult1TextView);
        mChildConsult2TextView = (TextView) v.findViewById(R.id.childnormalweightBmiconsult2TextView);
        mChildConsult3TextView = (TextView) v.findViewById(R.id.childnormalweightBmiconsult3TextView);
        //under weight
        mChildUnderWeightTextView = (TextView) v.findViewById(R.id.childunderweightBmiTextView);
        mChildUnderWeightConsult1TextView = (TextView) v.findViewById(R.id.chilunderweightBmiconsult1TextView);
        mChildUnderWeightConsult2TextView = (TextView) v.findViewById(R.id.childunderweightBmiconsult2TextView);
        mChildUnderWeightConsult3TextView = (TextView) v.findViewById(R.id.childunderweightBmiconsult3TextView);
        //over weight
        mChildOverWeightTextView = (TextView) v.findViewById(R.id.chiloverweightBmiTextView);
        mChildOverWeightConsult1TextView = (TextView) v.findViewById(R.id.childoverweightBmiconsult2TextView);
        mChildOverWeightConsult2TextView = (TextView) v.findViewById(R.id.childoverweightBmiconsult3TextView);
        mChildOverWeightConsult3TextView = (TextView) v.findViewById(R.id.chiloverweightBmiconsult1TextView);
        // set font
        Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "Tabitha full.ttf");
        mAdultTextView.setTypeface(font);
        mChildTextView.setTypeface(font);
        mChildNormalTextView.setTypeface(font);
        mChildConsult1TextView.setTypeface(font);
        mChildConsult2TextView.setTypeface(font);
        mChildConsult3TextView.setTypeface(font);
        mChildUnderWeightTextView.setTypeface(font);
        mChildUnderWeightConsult1TextView.setTypeface(font);
        mChildUnderWeightConsult2TextView.setTypeface(font);
        mChildUnderWeightConsult3TextView.setTypeface(font);
        mChildOverWeightTextView.setTypeface(font);
        mChildOverWeightConsult1TextView.setTypeface(font);
        mChildOverWeightConsult2TextView.setTypeface(font);
        mChildOverWeightConsult3TextView.setTypeface(font);
        mOverWeightTextView.setTypeface(font);
        mCOverWeightConsult1TextView.setTypeface(font);
        mOverWeightConsult2TextView.setTypeface(font);
        mOverWeightConsult3TextView.setTypeface(font);
        mNormalWeightTextView.setTypeface(font);
        mNormalWeightConsult1TextView.setTypeface(font);
        mNormalWeightConsult2TextView.setTypeface(font);
        mNormalWeightConsult3TextView.setTypeface(font);
        mUnderWeightTextView.setTypeface(font);
        mUnderWeightConsult1TextView.setTypeface(font);
        mUnderWeightConsult2TextView.setTypeface(font);
        mUnderWeightConsult3TextView.setTypeface(font);
        return v;
    }
}
