package com.example.uli2.cobachart;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import layout.LineFragment;
import layout.Pie2Fragment;
import layout.PieFragment;

/**
 * Created by uli2 on 07/07/17.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {
    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new LineFragment();
            case 1:
                return new PieFragment();
            case 2:
                return new Pie2Fragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Domain";
            case 1:
                return "Upload";
            case 2:
                return "Download";
        }
        return null;
    }

}
