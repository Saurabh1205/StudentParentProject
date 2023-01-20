package com.wordpro.studentproject.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.wordpro.studentproject.fragment.DynamicEventFragmen;
import com.wordpro.studentproject.model.NoticesModel;

import java.util.ArrayList;

public class ViewPagerEventAdapter extends FragmentStatePagerAdapter {
    ArrayList<NoticesModel.DataBean> eventsArrayList;
    Context mcontext;

    int po ;

    public ViewPagerEventAdapter(FragmentManager fm, ArrayList<NoticesModel.DataBean> eventsArrayList) {

        super(fm);
        this.eventsArrayList = eventsArrayList;
    }

    @Override
    public Fragment getItem(int position) {
        return DynamicEventFragmen.addfrag(position);
    }

    @Override
    public int getCount() {
        return eventsArrayList.size();
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return eventsArrayList.get (position).getGROUP_NAME();
    }
}
