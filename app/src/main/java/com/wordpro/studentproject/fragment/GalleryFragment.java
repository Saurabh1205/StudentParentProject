package com.wordpro.studentproject.fragment;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.wordpro.studentproject.R;
import com.wordpro.studentproject.adapter.homeAdpter.GalleryAdapter;
import com.wordpro.studentproject.adapter.homeAdpter.GalleryRecyVwAdpter;
import com.wordpro.studentproject.model.ImageModel;

import java.util.ArrayList;

import static com.wordpro.studentproject.activities.NavigationActivity.galleryRecyVwAdpter;
import static com.wordpro.studentproject.activities.VerficationActivity.galleryArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class GalleryFragment extends Fragment {

    public static String TAG = "GalleryFragment";
    RecyclerView recyclerView;
    private static final String endpoint = "https://api.androidhive.info/json/glide.json";
    private ArrayList<ImageModel> imageModelList;
    private ArrayList<ImageModel.UrlBean> imagesURLArrayList;
    private ProgressDialog pDialog;
    private GalleryAdapter mAdapter;
    private FrameLayout galleryFrag;

    public GalleryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        final View view = inflater.inflate(R.layout.fragment_gallery, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        galleryFrag = (FrameLayout) view.findViewById(R.id.galleryFrag);
        pDialog = new ProgressDialog(getActivity());

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        if(galleryArrayList!=null && galleryArrayList.size()!=0) {
            recyclerView.setAdapter(galleryRecyVwAdpter);
            recyclerView.addOnItemTouchListener(new GalleryRecyVwAdpter.RecyclerTouchListener(getActivity(), recyclerView, new GalleryRecyVwAdpter.ClickListener() {
                @Override
                public void onClick(View view, int position, ArrayList<ImageModel> imageModelList) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("images", imageModelList);
                    bundle.putInt("position", position);

                    FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                    SlideshowDialogFragment newFragment = SlideshowDialogFragment.newInstance();
                    newFragment.setArguments(bundle);

                    newFragment.show(ft, "slideshow");

                }

                @Override
                public void onLongClick(View view, int position) {


                }
            }));

            galleryRecyVwAdpter.notifyDataSetChanged();
        }
        //fetchImages();
        return view;

    }

}
