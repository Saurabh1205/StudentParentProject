package com.wordpro.studentproject.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.wordpro.studentproject.fragment.DynamicGalleryFragmen;
import com.wordpro.studentproject.fragment.DynamicVideoFragmen;
import com.wordpro.studentproject.model.NoticesModel;

import java.util.ArrayList;

public class ViewPagerVideoAdapter extends FragmentStatePagerAdapter {
    ArrayList<NoticesModel.DataBean> videosArrayList;
    Context mcontext;

    int po ;

    public ViewPagerVideoAdapter(FragmentManager fm, ArrayList<NoticesModel.DataBean> videosArrayList) {

        super(fm);
        this.videosArrayList = videosArrayList;
    }

    @Override
    public Fragment getItem(int position) {
        return DynamicVideoFragmen.addfrag(position);
    }

    @Override
    public int getCount() {
        return videosArrayList.size();
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return videosArrayList.get (position).getGROUP_NAME();
    }
}
