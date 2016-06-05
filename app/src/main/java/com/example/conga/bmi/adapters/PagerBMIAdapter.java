package com.example.conga.bmi.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import com.example.conga.bmi.fragments.CheDoDinhDuongBMIFrag;
import com.example.conga.bmi.fragments.ChuongTrinhLuyenTapBMIFrag;
import com.example.conga.bmi.fragments.ThoiQuenSinhHoatBMIFrag;


public class PagerBMIAdapter extends FragmentStatePagerAdapter {
    private static String TAG = PagerBMIAdapter.class.getSimpleName();

    // constructor
    public PagerBMIAdapter(FragmentManager fm) {
        super(fm);
        Log.d(TAG, "CONSTRUCTOR ");
    }

    // LIỆT KÊ CÁC TAB CÓ SẴN
    @Override
    public Fragment getItem(int position) {

        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new CheDoDinhDuongBMIFrag();
                break;
            case 1:
                fragment = new ChuongTrinhLuyenTapBMIFrag();
                break;
            case 2:
                fragment = new ThoiQuenSinhHoatBMIFrag();
                break;
        }
        return fragment;
    }

    // SỐ TAB
    @Override
    public int getCount() {
        return 3;
    }

    // Tittle

    @Override
    public CharSequence getPageTitle(int position) {
        String mTitle = "";
        switch (position) {
            case 0:
                mTitle = "Chế độ dinh dưỡng";
                break;
            case 1:
                mTitle = "Chương trình luyện tập";
                break;
            case 2:
                mTitle = "Thói quen sinh hoạt";
                break;
        }
        return mTitle;
    }
}
