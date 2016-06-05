package com.example.conga.bmi.abstracts;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;


public abstract class BaseActivity extends AppCompatActivity {
    private static String TAG = BaseActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayoutById());
        initViews(savedInstanceState);
        addEvents();
    }

    protected abstract void addEvents();

    protected abstract void initViews(Bundle savedInstanceState);

    protected abstract int setLayoutById();
}
