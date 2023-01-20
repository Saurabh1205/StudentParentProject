package com.wordpro.studentproject.adapter.homeAdpter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wordpro.studentproject.R;
import com.wordpro.studentproject.model.ImageModel;
import com.wordpro.studentproject.model.NoticesModel;
import com.wordpro.studentproject.model.YoutubeVideo;

import java.util.ArrayList;
import java.util.List;

public class GalleryRecyVwAdpter extends RecyclerView.Adapter<GalleryRecyVwAdpter.ViewHolder> {

    Context context;
    List<String> listDataHeaderGallery;
    ArrayList<NoticesModel.DataBean> galleryArrayList;
    ArrayList<ImageModel.UrlBean> imagesURLArrayList;
    private static ArrayList<ImageModel> imageModelList;


    public GalleryRecyVwAdpter(Context context, List<String> listDataHeaderGallery, ArrayList<NoticesModel.DataBean> galleryArrayList) {
        this.context = context;
        this.listDataHeaderGallery = listDataHeaderGallery;
        this.galleryArrayList = galleryArrayList;
        imageModelList=new ArrayList<>();
    }

    GalleryAdapter galleryAdapter;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_list_group, null);
        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        String groupName = listDataHeaderGallery.get(position);
        holder.txtNewsGroupName.setText(groupName);

        imagesURLArrayList=new ArrayList<>();


        ArrayList<YoutubeVideo> youtubeVideosGallery = new ArrayList<>();
        //  ImageModel.UrlBean urlBean=new ImageModel.UrlBean();


        for (int i = 0; i < galleryArrayList.size(); i++) {

            String GROUP_NAME = galleryArrayList.get(i).getGROUP_NAME();

            if (groupName.equalsIgnoreCase(GROUP_NAME)) {

                YoutubeVideo videoNewsData = new YoutubeVideo();
                ImageModel model=new ImageModel();

                String ATTACHMENT_NAME=galleryArrayList.get(i).getATTACHMENT_NAME();
                String ATTACHMENT_LINK = galleryArrayList.get(i).getATTACHMENT_LINK();
                String ATTACHMENT_SOURCE = galleryArrayList.get(i).getATTACHMENT_SOURCE();
                String ATTACHMENT_TYPE = galleryArrayList.get(i).getATTACHMENT_TYPE();
                String NOTICE_DESCRIPTION = galleryArrayList.get(i).getNOTICE_DESCRIPTION();
                String NOTICE_HEADER = galleryArrayList.get(i).getNOTICE_HEADER();
                String SOFT_COPY_PATH = galleryArrayList.get(i).getSOFT_COPY_PATH();
                String STANDARD_PATH = galleryArrayList.get(i).getSTANDARD_PATH();

                if (ATTACHMENT_SOURCE.equalsIgnoreCase("OFFLINE") && !STANDARD_PATH.isEmpty() && !SOFT_COPY_PATH.isEmpty()) {

                    if (ATTACHMENT_TYPE.equalsIgnoreCase("FILE")) {

                        ImageModel.UrlBean urlBean=new ImageModel.UrlBean();

                        videoNewsData.setVideoId(String.valueOf(i + 1));
                        videoNewsData.setImageUrl(ATTACHMENT_LINK);
                        videoNewsData.setTitle(NOTICE_HEADER + "$" + NOTICE_DESCRIPTION);
                        videoNewsData.setVideoId("SimpleEvent");
                        videoNewsData.setGroupName(GROUP_NAME);
                        youtubeVideosGallery.add(videoNewsData);


                        urlBean.setSmall(ATTACHMENT_LINK);
                        urlBean.setMedium(ATTACHMENT_LINK);
                        urlBean.setLarge(ATTACHMENT_LINK);

                        model.setName(ATTACHMENT_NAME);
                        model.setTimestamp("");
                        model.setUrl(urlBean);

                        imagesURLArrayList.add(urlBean);
                        imageModelList.add(model);

                    }

                }


            }


        }

        galleryAdapter=new GalleryAdapter(context,imagesURLArrayList,imageModelList);
        holder.recyVwNewsGroup.setAdapter(galleryAdapter);

    }

    public interface ClickListener {
        void onClick(View view, int position,ArrayList<ImageModel> imageModelList);

        void onLongClick(View view, int position);
    }

    @Override
    public int getItemCount() {
        return listDataHeaderGallery.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtNewsGroupName;
        RecyclerView recyVwNewsGroup;

        public ViewHolder(View itemView) {
            super(itemView);

            Typeface customTypeOne = Typeface.createFromAsset(itemView.getContext().getAssets(), "font/Poppins-Medium.otf");

            txtNewsGroupName = (TextView) itemView.findViewById(R.id.txtNewsGroupName);
            txtNewsGroupName.setTypeface(customTypeOne);
            recyVwNewsGroup = (RecyclerView) itemView.findViewById(R.id.recyVwNewsGroup);
            RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(context, 2);
            recyVwNewsGroup.setLayoutManager(mLayoutManager);
            recyVwNewsGroup.setItemAnimator(new DefaultItemAnimator());

        }
    }

    public static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private GalleryRecyVwAdpter.ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final GalleryRecyVwAdpter.ClickListener clickListener) {
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, recyclerView.getChildPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildPosition(child),imageModelList);
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }
}
