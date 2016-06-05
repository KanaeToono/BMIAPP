package com.example.conga.bmi.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.conga.bmi.fragments.CheDoDinhDuongBodyFrag;
import com.example.conga.bmi.fragments.ChuongTrinhLuyenTapBodyFrag;
import com.example.conga.bmi.fragments.ThoiQuenSinhHoatBodyFrag;


public class PagerBodyAdapter extends FragmentStatePagerAdapter {
    private static String TAG = PagerBodyAdapter.class.getSimpleName();
    public PagerBodyAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position){
            case 0:  fragment = new CheDoDinhDuongBodyFrag();
                break;
            case 1: fragment = new ChuongTrinhLuyenTapBodyFrag();
                break;
            case 2: fragment = new ThoiQuenSinhHoatBodyFrag();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String mTitle ="";
        switch (position){
            case 0:  mTitle = "Chế độ dinh dưỡng";
                break;
            case 1: mTitle ="Chương trình luyện tập";
                break;
            case 2:mTitle ="Thói quen sinh hoạt";
                break;
        }
        return  mTitle;
    }
}
