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


public class KiemTraBMIFragment extends Fragment {
    private static String TAG = KiemTraBMIFragment.class.getSimpleName();
    public static final String ITEM_NAME = "iTem";
    private LinearLayout mLinearLayout;
    private TextView textViewBMIResult;
    private TextView textViewResult;
    private TextView textViewKgResult;
    private EditText editTextAge;
    private EditText editTextHeight;
    private EditText editTextWeight;

    private RadioButton mWHORadioButton;
    private RadioButton mIDIPRWORadioButton;
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
        View v = inflater.inflate(R.layout.kiemtrabmifrag, container, false);
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
        mWHORadioButton = (RadioButton) v.findViewById(R.id.who_radio_btn);
        mIDIPRWORadioButton = (RadioButton) v.findViewById(R.id.idi_wpro_radio_btn);
        mFemaleRadioButton = (RadioButton) v.findViewById(R.id.female_radio_btn);
        mMaleRadioButton = (RadioButton) v.findViewById(R.id.male_radio_btn);
        mWHORadioButton.setTypeface(font);
        mFemaleRadioButton.setTypeface(font);
        mMaleRadioButton.setTypeface(font);
        mIDIPRWORadioButton.setTypeface(font);
        // radioGroup
        mRadioGroupChoice = (RadioGroup) v.findViewById(R.id.radioGroupChoice);
        mRadioGroupSex = (RadioGroup) v.findViewById(R.id.radioGroupSex);

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
        textViewKgResult.setVisibility(View.INVISIBLE);
        //format bmi before display textview
        final DecimalFormat decimalFormat = new DecimalFormat("#.##");
        decimalFormat.setRoundingMode(RoundingMode.CEILING); // làm tròn

        // handle when click resultTextView
        resultTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // getId RadioButton in RadioGroup
                id_RadioGroup_Choice = mRadioGroupChoice.getCheckedRadioButtonId();

                if (id_RadioGroup_Choice == -1 || editTextHeight.getText().toString().equals("")
                        || editTextWeight.getText().toString().equals("")
                        || editTextAge.getText().toString().equals("")||!isValidAge(editTextAge.getText().toString()) ||
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

                // TEST
                Log.d(TAG, "age:" + age);
                Log.d(TAG, "height" + height);
                Log.d(TAG, "weight" + weight);
                //Caculate
                float result = weight / (height * height);
                //DÀNH CHO NGƯỜI CHÂU Á
                // người lớn hơn 20 tuổi
                // số cần cần tăng
                float weightNeedToIncrease = (float) ((18.5 * height * height) - weight);
                // số kg cần giảm
                float weightNeedToDecrease = (float) (weight - (22.8 * height * height));
                // người từ 5 đến 19 tuổi
                // số cần cần tăng / Boy
                float weightNeedToIncreaseMaleChild = (float) ((17 * height * height) - weight);
                // số kg cần giảm
                float weightNeedToDecreaseMaleChild = (float) (weight - (25.5 * height * height));
                // số cần cần tăng / Girl
                float weightNeedToIncreaseFemaleChild = (float) ((16.5 * height * height) - weight);
                // số kg cần giảm
                float weightNeedToDecreaseFemaleChild = (float) (weight - (25.2 * height * height));
                // DÀNH CHO THẾ GIỚI
                // số kg cần giảm
                float weightNeedToDecreaseWHO = (float) (weight - (24.9 * height * height));
                //   if(mWHORadioButton.isChecked())
                //Dành Cho Người Châu Á
                textViewBMIResult.setText("BMI:" + decimalFormat.format(result));
                if (mIDIPRWORadioButton.isChecked()) {
                    if (age >= 20) {
                        if (result < 18.5) {
                            textViewResult.setText(getString(R.string.underweight));
                            textViewKgResult.setVisibility(View.VISIBLE);
                            textViewKgResult.setText(getString(R.string.needtoincrease) + decimalFormat.format(weightNeedToIncrease));
                        } else if (result >= 18.5 && result <= 22.9) {
                            textViewKgResult.setVisibility(View.INVISIBLE);
                            textViewResult.setText(getString(R.string.normal));
                        } else if (result > 22.9 && result <= 23) {
                            textViewResult.setText(getString(R.string.overweight));
                            textViewKgResult.setVisibility(View.VISIBLE);
                            textViewKgResult.setText(getString(R.string.needtodecrease) + decimalFormat.format(weightNeedToDecrease));
                        } else if (result > 23 && result <= 24.9) {
                            textViewResult.setText(getString(R.string.obeseE));
                            textViewKgResult.setVisibility(View.VISIBLE);
                            textViewKgResult.setText(getString(R.string.needtodecrease)  + decimalFormat.format(weightNeedToDecrease));
                        } else if (result >= 25 && result <= 29.9) {
                            textViewResult.setText(getString(R.string.obese1) );
                            textViewKgResult.setVisibility(View.VISIBLE);
                            textViewKgResult.setText(getString(R.string.needtodecrease)  + decimalFormat.format(weightNeedToDecrease));
                        } else if (result >= 30 && result < 40) {
                            textViewResult.setText(getString(R.string.obese2) );
                            textViewKgResult.setVisibility(View.VISIBLE);
                            textViewKgResult.setText(getString(R.string.needtodecrease)  + decimalFormat.format(weightNeedToDecrease));
                        } else if (result >= 40) {
                            textViewResult.setText(getString(R.string.obese3) );
                            textViewKgResult.setVisibility(View.VISIBLE);
                            textViewKgResult.setText(getString(R.string.needtodecrease)  + decimalFormat.format(weightNeedToDecrease));
                        }
                    } else if (age >= 5 && age <= 19) {
                        id_RadioGroup_Sex = mRadioGroupSex.getCheckedRadioButtonId();
                        if (id_RadioGroup_Sex == -1) {
                            textViewResult.setVisibility(View.INVISIBLE);
                            textViewBMIResult.setVisibility(View.INVISIBLE);
                            textViewKgResult.setVisibility(View.INVISIBLE);
                            Toast.makeText(getActivity(), getString(R.string.chosegender) , Toast.LENGTH_SHORT).show();
                        } else {
                            textViewResult.setVisibility(View.VISIBLE);
                            textViewBMIResult.setVisibility(View.VISIBLE);
                            if (mFemaleRadioButton.isChecked()) {
                                if (result < 16.5) {
                                    textViewResult.setText(getString(R.string.underweight) );
                                    textViewKgResult.setVisibility(View.VISIBLE);
                                    textViewKgResult.setText(getString(R.string.needtoincrease)  + decimalFormat.format(weightNeedToIncreaseFemaleChild));
                                } else if (result >= 16.5 && result < 24.5) {
                                    textViewResult.setText(getString(R.string.normal) );
                                    textViewKgResult.setVisibility(View.INVISIBLE);

                                } else if (result >= 24.5 && result < 30) {
                                    textViewResult.setText(getString(R.string.overweight) );
                                    textViewKgResult.setVisibility(View.VISIBLE);
                                    textViewKgResult.setText(getString(R.string.needtodecrease)  + decimalFormat.format(weightNeedToDecreaseFemaleChild));
                                } else if (result >= 30) {
                                    textViewResult.setText(getString(R.string.obese) );
                                    textViewKgResult.setVisibility(View.VISIBLE);
                                    textViewKgResult.setText(getString(R.string.needtodecrease)  + decimalFormat.format(weightNeedToDecreaseFemaleChild));
                                }
                            } else if (mMaleRadioButton.isChecked()) {
                                if (result < 17) {
                                    textViewResult.setText(getString(R.string.underweight) );
                                    textViewKgResult.setVisibility(View.VISIBLE);
                                    textViewKgResult.setText(getString(R.string.needtoincrease)  + decimalFormat.format(weightNeedToIncreaseMaleChild));
                                } else if (result >= 17 && result < 25.5) {
                                    textViewResult.setText(getString(R.string.normal) );
                                    textViewKgResult.setVisibility(View.INVISIBLE);

                                } else if (result >= 25.5 && result < 30) {
                                    textViewResult.setText(getString(R.string.overweight) );
                                    textViewKgResult.setVisibility(View.VISIBLE);
                                    textViewKgResult.setText(getString(R.string.needtodecrease)  + decimalFormat.format(weightNeedToDecreaseMaleChild));
                                } else if (result >= 30) {
                                    textViewResult.setText(getString(R.string.obese) );
                                    textViewKgResult.setVisibility(View.VISIBLE);
                                    textViewKgResult.setText(getString(R.string.needtodecrease)  + decimalFormat.format(weightNeedToDecreaseMaleChild));
                                }
                            }

                        }
                    }
                }
                // TÍNH THEO WHO
                else if (mWHORadioButton.isChecked()) {
                    if (age >= 20) {
                        if (result < 18.5) {
                            textViewResult.setText(getString(R.string.underweight));
                            textViewKgResult.setVisibility(View.VISIBLE);
                            textViewKgResult.setText(getString(R.string.needtoincrease)  + decimalFormat.format(weightNeedToIncrease));
                        } else if (result >= 18.5 && result <= 24.9) {
                            textViewKgResult.setVisibility(View.INVISIBLE);
                            textViewResult.setText(getString(R.string.normal) );
                        } else if (result > 24.9 && result <= 25) {
                            textViewResult.setText(getString(R.string.overweight) );
                            textViewKgResult.setVisibility(View.VISIBLE);
                            textViewKgResult.setText(getString(R.string.needtodecrease) + decimalFormat.format(weightNeedToDecreaseWHO));
                        } else if (result > 25 && result <= 29.9) {
                            textViewResult.setText(getString(R.string.obese) );
                            textViewKgResult.setVisibility(View.VISIBLE);
                            textViewKgResult.setText(getString(R.string.needtodecrease)  + decimalFormat.format(weightNeedToDecreaseWHO));
                        } else if (result >= 30 && result <= 34.9) {
                            textViewResult.setText(getString(R.string.obese1) );
                            textViewKgResult.setVisibility(View.VISIBLE);
                            textViewKgResult.setText(getString(R.string.needtodecrease)  + decimalFormat.format(weightNeedToDecreaseWHO));
                        } else if (result >= 35 && result < 39.9) {
                            textViewResult.setText(getString(R.string.obese2) );
                            textViewKgResult.setVisibility(View.VISIBLE);
                            textViewKgResult.setText(getString(R.string.needtodecrease)  + decimalFormat.format(weightNeedToDecreaseWHO));
                        } else if (result >= 40) {
                            textViewResult.setText(getString(R.string.obese3) );
                            textViewKgResult.setVisibility(View.VISIBLE);
                            textViewKgResult.setText(getString(R.string.needtodecrease)  + decimalFormat.format(weightNeedToDecreaseWHO));
                        }
                    } else if (age >= 5 && age <= 19) {
                        if (id_RadioGroup_Sex == -1) {
                            Toast.makeText(getActivity(), getString(R.string.chosegender) , Toast.LENGTH_SHORT).show();
                        } else {
                            if (mFemaleRadioButton.isChecked()) {
                                if (result < 16.5) {
                                    textViewResult.setText(getString(R.string.underweight) );
                                    textViewKgResult.setVisibility(View.VISIBLE);
                                    textViewKgResult.setText(getString(R.string.needtoincrease)  + decimalFormat.format(weightNeedToIncreaseFemaleChild));
                                } else if (result >= 16.5 && result < 25) {
                                    textViewResult.setText(getString(R.string.normal) );
                                    textViewKgResult.setVisibility(View.INVISIBLE);

                                } else if (result >= 25 && result < 30) {
                                    textViewResult.setText(getString(R.string.overweight) );
                                    textViewKgResult.setVisibility(View.VISIBLE);
                                    textViewKgResult.setText(getString(R.string.needtodecrease)  + decimalFormat.format(weightNeedToDecreaseFemaleChild));
                                } else if (result >= 30) {
                                    textViewResult.setText(getString(R.string.obese) );
                                    textViewKgResult.setVisibility(View.VISIBLE);
                                    textViewKgResult.setText(getString(R.string.needtodecrease)  + decimalFormat.format(weightNeedToDecreaseFemaleChild));
                                }
                            } else if (mMaleRadioButton.isChecked()) {
                                if (result < 17) {
                                    textViewResult.setText(getString(R.string.underweight) );
                                    textViewKgResult.setVisibility(View.VISIBLE);
                                    textViewKgResult.setText(getString(R.string.needtoincrease)  + decimalFormat.format(weightNeedToIncreaseMaleChild));
                                } else if (result >= 17 && result < 26) {
                                    textViewResult.setText(getString(R.string.normal) );
                                    textViewKgResult.setVisibility(View.INVISIBLE);

                                } else if (result >= 26 && result < 30) {
                                    textViewResult.setText(getString(R.string.overweight) );
                                    textViewKgResult.setVisibility(View.VISIBLE);
                                    textViewKgResult.setText(getString(R.string.needtodecrease)  + decimalFormat.format(weightNeedToDecreaseMaleChild));
                                } else if (result >= 30) {
                                    textViewResult.setText(getString(R.string.obese) );
                                    textViewKgResult.setVisibility(View.VISIBLE);
                                    textViewKgResult.setText(getString(R.string.needtodecrease)  + decimalFormat.format(weightNeedToDecreaseMaleChild));
                                }
                            }

                        }
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
        String PASSWORD_PATTERN = "^[0-9]*.*[0-9]*$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(string);
        return matcher.matches();

    }// check valid height/weight
    private boolean isValidAge(String string) {
        Pattern pattern;
        Matcher matcher;
        String PASSWORD_PATTERN = "^[0-9]*$";
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
