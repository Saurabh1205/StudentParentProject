package com.wordpro.studentproject.fragment;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.wordpro.studentproject.R;

import static com.wordpro.studentproject.activities.NavigationActivity.eventRecyVwAdpter;
import static com.wordpro.studentproject.activities.VerficationActivity.eventsArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class EventsFragment extends Fragment {


    public static String TAG = EventsFragment.class.getSimpleName();
    ProgressDialog mProgressDialog;
    FrameLayout frameLytEvents;
    RecyclerView eventsExpandableView;
    public EventsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_events, container, false);
        frameLytEvents=(FrameLayout)view.findViewById(R.id.frameLytEvents);
        eventsExpandableView=(RecyclerView) view.findViewById(R.id.eventsExpandableView);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        eventsExpandableView.setLayoutManager(layoutManager);
        eventsExpandableView.setItemAnimator(new DefaultItemAnimator());
        eventsExpandableView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        mProgressDialog = new ProgressDialog(getActivity());

        if(eventsArrayList!=null && eventsArrayList.size()!=0){
            eventsExpandableView.setAdapter(eventRecyVwAdpter);
        }
        return view;
    }

}
