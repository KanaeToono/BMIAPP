package com.example.conga.bmi.fragments;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.conga.bmi.R;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NangLuongTieuHaoFragmnet extends Fragment {
    private static String TAG = NangLuongTieuHaoFragmnet.class.getSimpleName();
    public static final String ITEM_NAME = "iTem";
    private LinearLayout mLinearLayout;
    private TextView textViewBMIResult;
    private TextView textViewResult;
   // private TextView textViewKgResult;
    private EditText editTextAge;
    private EditText editTextHeight;
    private EditText editTextWeight;

    private RadioButton mAction1RadioButton;
    private RadioButton mAction2RadioButton;
    private RadioButton mAction3RadioButton;
    private RadioButton mAction4RadioButton;
    private RadioButton mAction5RadioButton;
    private RadioButton mMaleRadioButton;
    private RadioButton mFemaleRadioButton;
    private RadioGroup mRadioGroupChoice;
    private RadioGroup mRadioGroupSex;
    private int id_RadioGroup_Choice;
    private int id_RadioGroup_Sex;
    private TextView informationTittleTextView;
    private TextView choiceTextView;
    private TextView genderTextView;
    private TextView resultTextView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.nangluongtieuhaofrag, container, false);
        Log.d(TAG, "on create");
        mLinearLayout = (LinearLayout) v.findViewById(R.id.resultlayout);
        mLinearLayout.setVisibility(View.INVISIBLE);

        //get id informationTextVew
        informationTittleTextView = (TextView) v.findViewById(R.id.text);
        choiceTextView = (TextView) v.findViewById(R.id.textView);
        genderTextView = (TextView) v.findViewById(R.id.textViewSex);
        Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "Tabitha full.ttf");
        informationTittleTextView.setTypeface(font);
        genderTextView.setTypeface(font);
        choiceTextView.setTypeface(font);

        //get Id exditText
        //editTextUserName = (EditText) v.findViewById(R.id.editText_user_name);
        editTextAge = (EditText) v.findViewById(R.id.editText_age);
        editTextHeight = (EditText) v.findViewById(R.id.editText_height);
        editTextWeight = (EditText) v.findViewById(R.id.editText_weight);

        // set font editText
        //editTextUserName.setTypeface(font);
        editTextAge.setTypeface(font);
        editTextWeight.setTypeface(font);
        editTextHeight.setTypeface(font);

        //getIdRadioButton/ GroupRadio
        mAction1RadioButton = (RadioButton) v.findViewById(R.id.radio_btn_action1);
        mAction2RadioButton = (RadioButton) v.findViewById(R.id.radio_btn_action2);
        mAction3RadioButton = (RadioButton) v.findViewById(R.id.radio_btn_action3);
        mAction4RadioButton = (RadioButton) v.findViewById(R.id.radio_btn_action4);
        mAction5RadioButton = (RadioButton) v.findViewById(R.id.radio_btn_action5);
        mFemaleRadioButton = (RadioButton) v.findViewById(R.id.female_radio_btn);
        mMaleRadioButton = (RadioButton) v.findViewById(R.id.male_radio_btn);
        mAction1RadioButton.setTypeface(font);
        mFemaleRadioButton.setTypeface(font);
        mMaleRadioButton.setTypeface(font);
        mAction2RadioButton.setTypeface(font);
        mAction3RadioButton.setTypeface(font);
        mAction4RadioButton.setTypeface(font);
        mAction5RadioButton.setTypeface(font);
        // radioGroup
        mRadioGroupChoice = (RadioGroup) v.findViewById(R.id.radioGroupChoice);
        mRadioGroupSex = (RadioGroup) v.findViewById(R.id.radioGroupSex);

        //get Id TextView
        resultTextView = (TextView) v.findViewById(R.id.resultTextView);
        textViewBMIResult = (TextView) v.findViewById(R.id.textViewBMI);
        textViewResult = (TextView) v.findViewById(R.id.textViewResult);
       // textViewKgResult = (TextView) v.findViewById(R.id.textViewKg);
        ///
        textViewBMIResult.setTypeface(font);
       // textViewKgResult.setTypeface(font);
        textViewResult.setTypeface(font);
        resultTextView.setTypeface(font);

        //invisible textvieww Result
      //  textViewKgResult.setVisibility(View.INVISIBLE);
        //format bmi before display textview
        final DecimalFormat decimalFormat = new DecimalFormat("#.##");
        decimalFormat.setRoundingMode(RoundingMode.CEILING); // làm tròn
        // handle when click resultTextView
        resultTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // getId RadioButton in RadioGroup
                id_RadioGroup_Choice = mRadioGroupChoice.getCheckedRadioButtonId();
                id_RadioGroup_Sex = mRadioGroupSex.getCheckedRadioButtonId();

                if (id_RadioGroup_Choice == -1 || editTextHeight.getText().toString().equals("")
                        || editTextWeight.getText().toString().equals("")
                        || editTextAge.getText().toString().equals("") || id_RadioGroup_Sex == -1
                        || !isValidAge(editTextAge.getText().toString()) ||
                        !isValid(editTextHeight.getText().toString()) ||
                        !isValid(editTextWeight.getText().toString())) {
                    Toast.makeText(getActivity(), getString(R.string.fillupfieldss), Toast.LENGTH_SHORT).show();
                    return;
                }
                //display layout
                mLinearLayout.setVisibility(View.VISIBLE);
                // get Data from EditText
                int age = Integer.parseInt(editTextAge.getText().toString());
                float height = Float.parseFloat(editTextHeight.getText().toString());
                float weight = Float.parseFloat(editTextWeight.getText().toString());

                // Tính BMR cho nữ giới
                float BMRNu = (float) ((9.99 * weight) + (6.25 * height) - (4.92 * age) - 161);
                // Tính BMR cho Nam Giới
                float BMRNam = (float) ((9.99 * weight) + (6.25 * height) - (4.92 * age) + 5);
                if (mFemaleRadioButton.isChecked()) {
                    textViewBMIResult.setText(getString(R.string.bmrNu) + decimalFormat.format(BMRNu));
                    if (mAction1RadioButton.isChecked()) {
                        textViewResult.setText(getString(R.string.calo) + decimalFormat.format(BMRNu * (1.2)));
                    } else if (mAction2RadioButton.isChecked()) {
                        textViewResult.setText(getString(R.string.calo) + decimalFormat.format(BMRNu * (1.35)));

                    } else if (mAction3RadioButton.isChecked()) {
                        textViewResult.setText(getString(R.string.calo) + decimalFormat.format(BMRNu * (1.55)));
                    } else if (mAction4RadioButton.isChecked()) {
                        textViewResult.setText(getString(R.string.calo) + decimalFormat.format(BMRNu * (1.8)));
                    } else if (mAction5RadioButton.isChecked()) {
                        textViewResult.setText(getString(R.string.calo) + decimalFormat.format(BMRNu * (1.95)));
                    }
                }
                else if(mMaleRadioButton.isChecked()){
                    textViewBMIResult.setText(getString(R.string.bmrNu) + decimalFormat.format(BMRNam));
                    if (mAction1RadioButton.isChecked()) {
                        textViewResult.setText(getString(R.string.calo) + decimalFormat.format(BMRNam * (1.2)));
                    } else if (mAction2RadioButton.isChecked()) {
                        textViewResult.setText(getString(R.string.calo) + decimalFormat.format(BMRNam * (1.35)));

                    } else if (mAction3RadioButton.isChecked()) {
                        textViewResult.setText(getString(R.string.calo) + decimalFormat.format(BMRNam * (1.55)));
                    } else if (mAction4RadioButton.isChecked()) {
                        textViewResult.setText(getString(R.string.calo) + decimalFormat.format(BMRNam * (1.8)));
                    } else if (mAction5RadioButton.isChecked()) {
                        textViewResult.setText(getString(R.string.calo) + decimalFormat.format(BMRNam * (1.95)));
                    }
                }
                        }



        });

// AGE TEXTWATCHER
        editTextAge.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
//                if (editTextAge.getText().toString().length() == 0) {
//                    editTextAge.setError(getString(R.string.empty));
//                }
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                if (editTextAge.getText().toString().length() == 0) {
                    editTextAge.setError(getString(R.string.empty) );
                } else if (!isValidAge(editTextAge.getText().toString())) {
                    editTextAge.setError(getString(R.string.validData));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        // HEIGHT TEXTWATCHER
        editTextHeight.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {
//                if (editTextHeight.getText().toString().length() == 0) {
//                    editTextHeight.setError(getString(R.string.empty) );
//                }
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                if (editTextHeight.getText().toString().length() == 0) {
                    editTextHeight.setError(getString(R.string.empty) );
                } else if (!isValid(editTextHeight.getText().toString())) {
                    editTextHeight.setError(getString(R.string.validData) );
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
// WEIGHT TEXTWATCHER
        editTextWeight.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {
//                if (editTextWeight.getText().toString().length() == 0) {
//                    editTextWeight.setError(getString(R.string.empty) );
//                }
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                if (editTextWeight.getText().toString().length() == 0) {
                    editTextWeight.setError(getString(R.string.empty) );
                } else if (!isValid(editTextWeight.getText().toString())) {
                    editTextWeight.setError(getString(R.string.validData) );
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }

        });

        return v;
    }
    // textWatcher for editText age
// check valid height/weight
    private boolean isValid(String string) {
        Pattern pattern;
        Matcher matcher;
        String PASSWORD_PATTERN = "^[0-9]*.*[0-9]+";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(string);
        return matcher.matches();

    }// check valid height/weight
    private boolean isValidAge(String string) {
        Pattern pattern;
        Matcher matcher;
        String PASSWORD_PATTERN = "^[0-9]+";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(string);
        return matcher.matches();

    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, " on start");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d(TAG, " on attach 1");
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Log.d(TAG, " on attatch 2");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, " on resume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, " on pause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, " on stop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, " on destroy view");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, " on destroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, " on  detach");
    }
}
