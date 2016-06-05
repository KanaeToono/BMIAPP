package com.example.conga.bmi;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.MenuItem;
import android.view.View;

import com.example.conga.bmi.abstracts.BaseActivity;
import com.example.conga.bmi.fragments.KiemTraBMIFragment;
import com.example.conga.bmi.fragments.KiemTraVocDangFragmnet;
import com.example.conga.bmi.fragments.NangLuongTieuHaoFragmnet;
import com.example.conga.bmi.fragments.NoteBookFragment;
import com.example.conga.bmi.fragments.TuVanCanNangFragment;
import com.example.conga.bmi.fragments.TuVanVocDangFragment;
import com.example.conga.bmi.utils.TypefaceSpan;


public class MainActivity extends BaseActivity {
    private static String TAG = MainActivity.class.getSimpleName();
    private NavigationView mNavigationView;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mActionBarDrawerToggle;
    private Toolbar mToolbar;

    @Override
    protected int setLayoutById() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        mNavigationView = (NavigationView) findViewById(R.id.navigationView);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        // mặc định là kiểm tra BMI fragment
        if (savedInstanceState == null) {
            selectDrawerItem(mNavigationView.getMenu().findItem(R.id.checkBMI));
        }
    }

    // xử lí sự kiện
    @Override
    protected void addEvents() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        setupNavigationDrawerContent(mNavigationView);

        // hamburgu icon ( khi nhấn nút home , display mDrawerLayout
        mActionBarDrawerToggle = new
                ActionBarDrawerToggle(MainActivity.this, mDrawerLayout, R.string.drawer_open, R.string.drawer_close) {
                    @Override
                    public void onDrawerClosed(View drawerView) {
                        super.onDrawerClosed(drawerView);
                    }

                    @Override
                    public void onDrawerOpened(View drawerView) {
                        // setMenuCounter(R.id.music_favorite, application.getDatabaseFavorite().getCountDB());
                        super.onDrawerOpened(drawerView);

                    }
                }

        ;
        mDrawerLayout.addDrawerListener(mActionBarDrawerToggle);

    }

    // Xử lí khi chọn ITEM ở thanh NavigationView
    private void setupNavigationDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                //  int navItemId = menuItem.getItemId();
                selectDrawerItem(menuItem);
                return true;
            }
        });
    }


    public void selectDrawerItem(MenuItem menuItem) {
        // Create a new fragment and specify the fragment to show based on nav item clicked
        Fragment fragment = null;
        Class fragmentClass;
        switch (menuItem.getItemId()) {
            case R.id.checkBMI:
                fragmentClass = KiemTraBMIFragment.class;
                break;
            case R.id.checkBody:
                fragmentClass = NangLuongTieuHaoFragmnet.class;
                break;
            case R.id.checkCalo:
                fragmentClass = KiemTraVocDangFragmnet.class;
                break;
            case R.id.consultweight:
                fragmentClass = TuVanCanNangFragment.class;
                break;
            case R.id.consultbody:
                fragmentClass = TuVanVocDangFragment.class;
                break;
            case R.id.notebook:
                fragmentClass = NoteBookFragment.class;
                break;
            default:
                fragmentClass = KiemTraBMIFragment.class;
                break;
        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.container, fragment).commit();

        // Highlight the selected item has been done by NavigationView
        menuItem.setChecked(true);
        // Set action bar title
        SpannableString string = new SpannableString(menuItem.getTitle());
        string.setSpan(new TypefaceSpan(this, "Tabitha full.ttf"), 0, string.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        setTitle(string);

        // Close the navigation drawer
        mDrawerLayout.closeDrawers();
    }

    // được gọi sau hàm onStart();
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // đồng bộ trạng thái
        mActionBarDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        mActionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

    // khi nhấn home trên toolbar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

