package com.tahara.amarshimi.adapters;

/**
 * Created by amarshimi on 2/22/2015.
 */


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.tahara.amarshimi.tahara.Halaha;
import com.tahara.amarshimi.tahara.JewishCalendar;
import com.tahara.amarshimi.tahara.Mikvaot;


public class TabsPagerAdapter extends FragmentPagerAdapter {

    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int index) {

        switch (index) {
            case 0:
                // Top Rated fragment activity
                return new Mikvaot();
            case 1:
                // Games fragment activity
                return new JewishCalendar();
            case 2:
                // Movies fragment activity
                return new Halaha();
        }

        return null;
    }

    @Override
    public int getCount() {
        // get item count - equal to number of tabs
        return 3;
    }

}