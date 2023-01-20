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
import android.widget.LinearLayout;

import com.wordpro.studentproject.R;

import static com.wordpro.studentproject.activities.NavigationActivity.newsRecyVwAdpter;
import static com.wordpro.studentproject.activities.VerficationActivity.newsArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFragment extends Fragment {

    public static String TAG = NewsFragment.class.getSimpleName();
    ProgressDialog mProgressDialog;
    LinearLayout linearNewsLyt;
    RecyclerView newsExpandableView;

    public NewsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_news, container, false);
        newsExpandableView = (RecyclerView) view.findViewById(R.id.newsExpandableView);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        newsExpandableView.setLayoutManager(layoutManager);
        newsExpandableView.setItemAnimator(new DefaultItemAnimator());
        newsExpandableView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));

        mProgressDialog = new ProgressDialog(getActivity());
        linearNewsLyt = (LinearLayout) view.findViewById(R.id.linearNewsLyt);

        if(newsArrayList!=null && newsArrayList.size()!=0){
            newsExpandableView.setAdapter(newsRecyVwAdpter);
        }
        return view;

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
