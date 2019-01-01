package com.frag.q.frag;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.frag.q.frag.Fragment.FragmentA;
import com.frag.q.frag.Fragment.FragmentB;
import com.frag.q.frag.Fragment.FragmentC;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ViewPager viewPager;
    ActionBar bar;
    private FragmentManager fm;
    private ArrayList<Fragment> fList;
    public static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 1;
    private final int MY_PERMISSIONS_RECORD_AUDIO = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_CONTACTS)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_CONTACTS},
                        MY_PERMISSIONS_REQUEST_READ_CONTACTS);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }

        }

        viewPager = (ViewPager) findViewById(R.id.pager);
        fm = getSupportFragmentManager();

        bar = getSupportActionBar();
        bar.setDisplayShowTitleEnabled(true);
        bar.setTitle("CHACHAGO");
        bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        ActionBar.Tab tab1 = bar.newTab().setText("NUMBERS").setTabListener(tabListener);
        ActionBar.Tab tab2 = bar.newTab().setText("IMAGE").setTabListener(tabListener);
        ActionBar.Tab tab3 = bar.newTab().setText("SEARCH").setTabListener(tabListener);

        bar.addTab(tab1);
        bar.addTab(tab2);
        bar.addTab(tab3);

        fList = new ArrayList<Fragment>();
        fList.add(FragmentA.newInstance());
        fList.add(FragmentB.newInstance());
        fList.add(FragmentC.newInstance());

        viewPager.setOnPageChangeListener(viewPagerListener);

        CustomFragmentPagerAdapter adapter = new CustomFragmentPagerAdapter(fm, fList);
        viewPager.setAdapter(adapter);
    }

    ViewPager.SimpleOnPageChangeListener viewPagerListener = new ViewPager.SimpleOnPageChangeListener() {
        @Override
        public void onPageSelected(int position) {
            super.onPageSelected(position);

            bar.setSelectedNavigationItem(position);
        }
    };

    ActionBar.TabListener tabListener = new ActionBar.TabListener() {
        @Override
        public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
            viewPager.setCurrentItem(tab.getPosition());
        }

        @Override
        public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

        }

        @Override
        public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

        }
    };

    static final int REQUEST_PERMISSIONS = 1;
    private static String[] PERMISSIONS = {
            Manifest.permission.READ_CONTACTS,
            Manifest.permission.WRITE_CONTACTS,
            Manifest.permission.RECORD_AUDIO,
    };

    public static boolean verifyPermission(int[] grantresults){
        if(grantresults.length<1){
            return false;
        }
        for(int result: grantresults){
            if(result != PackageManager.PERMISSION_GRANTED){
                return false;
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        if(requestCode == MainActivity.REQUEST_PERMISSIONS){

            if (MainActivity.verifyPermission(grantResults)) {

                // permission was granted, yay! Do the
                // contacts-related task you need to do.

            } else {
                super.onRequestPermissionsResult(requestCode,permissions,grantResults);
            }

        }
    }
}
