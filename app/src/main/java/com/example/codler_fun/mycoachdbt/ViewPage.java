package com.example.codler_fun.mycoachdbt;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPage extends FragmentPagerAdapter
{
    final private List<Fragment> lstFragment = new ArrayList<>();
    final private List<String> lsttitle = new ArrayList<>();

    public ViewPage(android.support.v4.app.FragmentManager fm)
    {
        super(fm);
    }



    @Override
    public Fragment getItem(int position) {
        return lstFragment.get(position);
    }


    @Override
    public int getCount() {
        return lsttitle.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return lsttitle.get(position);
    }
    public void addFragment(Fragment fragment,String title)
    {
        lstFragment.add(fragment);
        lsttitle.add(title);
    }
}
