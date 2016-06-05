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
import android.widget.TextView;
import android.widget.Toast;

import com.example.conga.bmi.R;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class KiemTraVocDangFragmnet extends Fragment {
    private static String TAG = KiemTraVocDangFragmnet.class.getSimpleName();
    public static final String ITEM_NAME = "iTem";
    private LinearLayout mLinearLayout;
    private TextView textViewBMIResult;
    private TextView textViewResult;
    private TextView textViewKgResult;
    private EditText editTextRound1;
    private EditText editTextHeight;
    private EditText editTextRound2;
    private EditText editTextRound3;


    private TextView informationTittleTextView;
    private TextView resultTextView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.kiemtravocdangfrag, container, false);
        Log.d(TAG, "on create");
        mLinearLayout = (LinearLayout) v.findViewById(R.id.resultlayout);
        mLinearLayout.setVisibility(View.INVISIBLE);

        //get id informationTextVew
        informationTittleTextView = (TextView) v.findViewById(R.id.text);

        Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "Tabitha full.ttf");
        informationTittleTextView.setTypeface(font);

        //get Id exditText
        //editTextUserName = (EditText) v.findViewById(R.id.editText_user_name);
        editTextRound1 = (EditText) v.findViewById(R.id.editText_round1);
        editTextHeight = (EditText) v.findViewById(R.id.editText_height);
        editTextRound2 = (EditText) v.findViewById(R.id.editText_round2);
        editTextRound3 = (EditText) v.findViewById(R.id.editText_round3);

        // set font editText
        //editTextUserName.setTypeface(font);
        editTextRound2.setTypeface(font);
        editTextRound1.setTypeface(font);
        editTextRound3.setTypeface(font);
        editTextHeight.setTypeface(font);


        //get Id TextView
        resultTextView = (TextView) v.findViewById(R.id.resultTextView);
        textViewBMIResult = (TextView) v.findViewById(R.id.textViewBMI);
        textViewResult = (TextView) v.findViewById(R.id.textViewResult);
        textViewKgResult = (TextView) v.findViewById(R.id.textViewKg);
        ///
        textViewBMIResult.setTypeface(font);
        textViewKgResult.setTypeface(font);
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


                if (editTextHeight.getText().toString().equals("")
                        || editTextRound1.getText().toString().equals("")
                        || editTextRound2.getText().toString().equals("") ||
                        editTextRound3.getText().toString().equals("") || !isValid(editTextHeight.getText().toString())
                        || !isValid(editTextRound1.getText().toString()) ||
                        !isValid(editTextRound2.getText().toString()) ||
                        !isValid(editTextRound3.getText().toString())) {
                    Toast.makeText(getActivity(), getString(R.string.fillupfieldss), Toast.LENGTH_SHORT).show();
                    return;
                }
                //display layout
                mLinearLayout.setVisibility(View.VISIBLE);
                // get Data from EditText
                float round1 = Float.parseFloat(editTextRound1.getText().toString());
                float height = Float.parseFloat(editTextHeight.getText().toString());
                float round2 = Float.parseFloat(editTextRound2.getText().toString());
                float round3 = Float.parseFloat(editTextRound3.getText().toString());
                // kiem tra ket qua
                Log.d(TAG, round1 + "");
                Log.d(TAG, round2 + "");
                Log.d(TAG, round3 + "");
                Log.d(TAG, height + "");


                // vòng 1 , vòng 2 , vòng 3 chuẩn
                if ((round1 >= ((height / 2) + 3) && round1 <= ((height / 2) + 6)) &&
                        round2 < ((height / 2) - 16) &&
                        round3 >= 80 && round3 <= 92) {
                    textViewBMIResult.setText(getString(R.string.chuan));
                    textViewResult.setText(getString(R.string.chuan2));
                    textViewKgResult.setText(getString(R.string.chuan3));
                } else if ((round1 < ((height / 2) + 3) || round1 > ((height / 2) + 6)) &&
                        round2 > ((height / 2) - 16) &&
                        (round3 < 80 || round3 > 92)) {
                    textViewBMIResult.setText(getString(R.string.chuachuan));
                    textViewResult.setText(getString(R.string.chuachuan2));
                    textViewKgResult.setText(getString(R.string.chuachuan3));
                } else if ((round1 >= ((height / 2) + 3) && round1 <= ((height / 2) + 6)) &&
                        round2 > ((height / 2) - 16) &&
                        (round3 < 80 || round3 > 92)) {
                    textViewBMIResult.setText(getString(R.string.chuan));
                    textViewResult.setText(getString(R.string.chuachuan2));
                    textViewKgResult.setText(getString(R.string.chuachuan3));
                } else if (((round1 < (
                        (height / 2) + 3))
                        || (round1 > ((height / 2) + 6))) &&
                        (round2 <= ((height / 2) - 16)) && ((round3 >= 80)
                        && (round3 <= 92))) {
                    textViewBMIResult.setText(getString(R.string.chuachuan));
                    textViewResult.setText(getString(R.string.chuan2));
                    textViewKgResult.setText(getString(R.string.chuan3));
                } else if ((round1 >= ((height / 2) + 3) && round1 <= ((height / 2) + 6)) &&
                        round2 > ((height / 2) - 16) &&
                        round3 >= 80 && round3 <= 92
                        ) {
                    textViewBMIResult.setText(getString(R.string.chuan));
                    textViewResult.setText(getString(R.string.chuachuan2));
                    textViewKgResult.setText(getString(R.string.chuan3));
                } else if ((round1 >= ((height / 2) + 3) && round1 <= ((height / 2) + 6)) &&
                        round2 < ((height / 2) - 16) &&
                        (round3 < 80 || round3 > 92)) {
                    textViewBMIResult.setText(getString(R.string.chuan));
                    textViewResult.setText(getString(R.string.chuan2));
                    textViewKgResult.setText(getString(R.string.chuachuan3));
                } else if ((round1 < ((height / 2) + 3) || round1 > ((height / 2) + 6)) &&
                        round2 > ((height / 2) - 16) &&
                        round3 >= 80 && round3 <= 92) {
                    textViewBMIResult.setText(getString(R.string.chuachuan));
                    textViewResult.setText(getString(R.string.chuachuan2));
                    textViewKgResult.setText(getString(R.string.chuan3));
                } else if ((round1 < ((height / 2) + 3) || round1 > ((height / 2) + 6)) &&
                        round2 < ((height / 2) - 16) &&
                        (round3 < 80 || round3 > 92)) {
                    textViewBMIResult.setText(getString(R.string.chuachuan));
                    textViewResult.setText(getString(R.string.chuan2));
                    textViewKgResult.setText(getString(R.string.chuachuan3));
                }

            }
        }
        );
                //HEIGHT TEXTWATCHER
                editTextHeight.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                        if (editTextHeight.getText().toString().length() == 0) {
                            editTextHeight.setError(getString(R.string.empty));
                        } else if (!isValid(editTextHeight.getText().toString())) {
                            editTextHeight.setError(getString(R.string.validData));
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                });
                // ROUND 1 TEXTWATCHER
                editTextRound1.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                        if (editTextRound1.getText().toString().length() == 0) {
                            editTextRound1.setError(getString(R.string.empty));
                        } else if (!isValid(editTextRound1.getText().toString())) {
                            editTextRound1.setError(getString(R.string.validData));
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                });
// WRound 2 TEXTWATCHER

                editTextRound2.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                        if (editTextRound2.getText().toString().length() == 0) {
                            editTextRound2.setError(getString(R.string.empty));
                        } else if (!isValid(editTextRound2.getText().toString())) {
                            editTextRound2.setError(getString(R.string.validData));
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                });
                // Round 3
                editTextRound3.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                        if (editTextRound3.getText().toString().length() == 0) {
                            editTextRound3.setError(getString(R.string.empty));
                        } else if (!isValid(editTextRound3.getText().toString())) {
                            editTextRound3.setError(getString(R.string.validData));
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
