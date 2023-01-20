package com.wordpro.studentproject.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.wordpro.studentproject.fragment.DynamicEventFragmen;
import com.wordpro.studentproject.fragment.DynamicGalleryFragmen;
import com.wordpro.studentproject.model.NoticesModel;

import java.util.ArrayList;

public class ViewPagerGalleryAdapter extends FragmentStatePagerAdapter {
    ArrayList<NoticesModel.DataBean> galleryArrayList;
    Context mcontext;

    int po ;

    public ViewPagerGalleryAdapter(FragmentManager fm, ArrayList<NoticesModel.DataBean> galleryArrayList) {

        super(fm);
        this.galleryArrayList = galleryArrayList;
    }

    @Override
    public Fragment getItem(int position) {
        return DynamicGalleryFragmen.addfrag(position);
    }

    @Override
    public int getCount() {
        return galleryArrayList.size();
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return galleryArrayList.get (position).getGROUP_NAME();
    }
}
