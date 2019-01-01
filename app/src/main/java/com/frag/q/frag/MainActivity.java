package com.frag.q.frag;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.frag.q.frag.Fragment.FragmentA;
import com.frag.q.frag.Fragment.FragmentB;
import com.frag.q.frag.Fragment.FragmentC;
import com.frag.q.frag.CustomFragmentPagerAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ViewPager viewPager;
    ActionBar bar;
    private FragmentManager fm;
    private ArrayList<Fragment> fList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = (ViewPager) findViewById(R.id.pager);
        fm = getSupportFragmentManager();

        bar = getSupportActionBar();
        bar.setDisplayShowTitleEnabled(true);
        bar.setTitle("Project 1");
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
}
