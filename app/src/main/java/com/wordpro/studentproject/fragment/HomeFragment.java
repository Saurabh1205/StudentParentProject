package com.wordpro.studentproject.fragment;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.wordpro.studentproject.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TextView tv;
    private TextView txtEvents;
    private TextView txtJobs;
    private TextView txtVideo;
    private TextView txtGallery;


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_home, container, false);

        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) view.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);


        Typeface typefaceHeading = Typeface.createFromAsset(getActivity().getAssets(), "font/KaushanScript-Regular.otf");
        tv = (TextView) view.findViewById(R.id.mywidget);
        Animation marquee = AnimationUtils.loadAnimation(getActivity(),R.anim.marquee);
        tv.startAnimation(marquee);
        tv.setTypeface(typefaceHeading);

        txtEvents=(TextView)view.findViewById(R.id.txtEvents);
        txtEvents.setTypeface(typefaceHeading);

        txtJobs=(TextView)view.findViewById(R.id.txtJobs);
        txtJobs.setTypeface(typefaceHeading);

        txtVideo=(TextView)view.findViewById(R.id.txtVideo);
        txtVideo.setTypeface(typefaceHeading);

        txtGallery=(TextView)view.findViewById(R.id.txtGallery);
        txtGallery.setTypeface(typefaceHeading);

        return view;
    }


    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager());
        adapter.addFragment(new NewsFragment(), "NEWS");
        adapter.addFragment(new EventsFragment(), "EVENTS");
        adapter.addFragment(new VideosFragment(), "VIDEOS");
        adapter.addFragment(new GalleryFragment(),"GALLERY");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

}
