package com.wordpro.studentproject.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.wordpro.studentproject.R;

import static com.wordpro.studentproject.activities.NavigationActivity.videoRecyVwAdpter;
import static com.wordpro.studentproject.activities.VerficationActivity.videosArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class VideosFragment extends Fragment {

    FrameLayout frameLytVideo;
    RecyclerView videoExpandableView;
    public VideosFragment() {
        // Required empty public constructor
    }
    public static VideosFragment newInstance() {
        VideosFragment f = new VideosFragment();
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_videos, container, false);
        frameLytVideo=(FrameLayout)view.findViewById(R.id.frameLytVideo);
        videoExpandableView=(RecyclerView) view.findViewById(R.id.videoExpandableView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        videoExpandableView.setLayoutManager(layoutManager);
        videoExpandableView.setItemAnimator(new DefaultItemAnimator());
        videoExpandableView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));

        if(videosArrayList!=null && videosArrayList.size()!=0){
            videoExpandableView.setAdapter(videoRecyVwAdpter);
        }
        return view;
    }

    public void show(FragmentTransaction ft, String s) {
    }
}
