package com.example.conga.bmi.fragments;

import android.graphics.Typeface;
import android.widget.TextView;

import com.example.conga.bmi.R;
import com.example.conga.bmi.abstracts.BaseFragment;


public class CheDoDinhDuongBodyFrag extends BaseFragment {
    public static final  String ITEM_NAME ="iTem" ;
    private static String TAG = CheDoDinhDuongBodyFrag.class.getSimpleName();
    private TextView mAdultTextView;
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
  //  private View v;

//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        v = inflater.inflate(R.layout.chedodinhduongbodyfrag, container, false);
//        // get id
//        mAdultTextView = (TextView) v.findViewById(R.id.adultTextView);
//        //   mChildTextView = (TextView) v.findViewById(R.id.childweightBmiTextView);
//        // get id aduld
//        // normal
//        mNormalWeightTextView = (TextView) v.findViewById(R.id.normalBmiTextView);
//        mNormalWeightConsult1TextView = (TextView) v.findViewById(R.id.normalBmiconsult1TextView);
//        mNormalWeightConsult2TextView = (TextView) v.findViewById(R.id.normalBmiconsult2TextView);
//        mNormalWeightConsult3TextView = (TextView) v.findViewById(R.id.normalBmiconsult3TextView);
//        //under weight
//        mUnderWeightTextView = (TextView) v.findViewById(R.id.underweightBmiTextView);
//        mUnderWeightConsult1TextView = (TextView) v.findViewById(R.id.underweightBmiconsult1TextView);
//        mUnderWeightConsult2TextView = (TextView) v.findViewById(R.id.underweightBmiconsult2TextView);
//        mUnderWeightConsult3TextView = (TextView) v.findViewById(R.id.underweightBmiconsult3TextView);
//        //over weight
//        mOverWeightTextView = (TextView) v.findViewById(R.id.overweightBmiTextView);
//        mCOverWeightConsult1TextView = (TextView) v.findViewById(R.id.overrweightBmiconsult1TextView);
//        mOverWeightConsult2TextView = (TextView) v.findViewById(R.id.overweightBmiconsult2TextView);
//        mOverWeightConsult3TextView = (TextView) v.findViewById(R.id.overweightBmiconsult3TextView);
//
//
//        return v;
//    }
    @Override
    protected int setLayoutById() {
        return R.layout.chedodinhduongbodyfrag;
    }
    @Override
    protected void initViews() {
        // get id
        mAdultTextView = (TextView) view.findViewById(R.id.adultTextView);
        //   mChildTextView = (TextView) v.findViewById(R.id.childweightBmiTextView);
        // get id aduld
        // normal
        mNormalWeightTextView = (TextView) view.findViewById(R.id.normalBmiTextView);
        mNormalWeightConsult1TextView = (TextView) view.findViewById(R.id.normalBmiconsult1TextView);
        mNormalWeightConsult2TextView = (TextView) view.findViewById(R.id.normalBmiconsult2TextView);
        mNormalWeightConsult3TextView = (TextView) view.findViewById(R.id.normalBmiconsult3TextView);
        //under weight
        mUnderWeightTextView = (TextView) view.findViewById(R.id.underweightBmiTextView);
        mUnderWeightConsult1TextView = (TextView) view.findViewById(R.id.underweightBmiconsult1TextView);
        mUnderWeightConsult2TextView = (TextView) view.findViewById(R.id.underweightBmiconsult2TextView);
        mUnderWeightConsult3TextView = (TextView) view.findViewById(R.id.underweightBmiconsult3TextView);
        //over weight
        mOverWeightTextView = (TextView) view.findViewById(R.id.overweightBmiTextView);
        mCOverWeightConsult1TextView = (TextView) view.findViewById(R.id.overrweightBmiconsult1TextView);
        mOverWeightConsult2TextView = (TextView) view.findViewById(R.id.overweightBmiconsult2TextView);
        mOverWeightConsult3TextView = (TextView) view.findViewById(R.id.overweightBmiconsult3TextView);
    }
    @Override
    protected void addEvents() {
        // set font
        Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "Tabitha full.ttf");
        mAdultTextView.setTypeface(font);

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
    }




}
