package com.wordpro.studentproject.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.wordpro.studentproject.R;
import com.wordpro.studentproject.activities.GalleryMobileActivity;
import com.wordpro.studentproject.adapter.homeAdpter.NewsAdapter;
import com.wordpro.studentproject.model.DynamicArrayList;
import com.wordpro.studentproject.model.DynamicNoticArray;
import com.wordpro.studentproject.model.NoticesModel;
import com.wordpro.studentproject.model.YoutubeVideo;

import java.util.ArrayList;
import java.util.List;



public class Sub_GalleryImageAdpter extends RecyclerView.Adapter<Sub_GalleryImageAdpter.ViewHolder>{

    Context Mcontext;
    ArrayList<NoticesModel.DataBean> galleryArrayList;
    ArrayList<YoutubeVideo> youtubeVideosEvents = new ArrayList<>();
    List<DynamicNoticArray>noticarray;
    NewsAdapter newsAdapter;
    String ATTACHMENT_LINK;
    DynamicNoticArray lastNum;
    public Sub_GalleryImageAdpter(Context context, List<DynamicNoticArray> noticarrayLists) {
        this.Mcontext =context;
        this.noticarray = noticarrayLists;

    }

    @NonNull
    @Override
    public Sub_GalleryImageAdpter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.only_gallery_images, viewGroup, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        String imgurl0  = "http://192.168.1.72:500/"+noticarray.get(position).getaTTACHMENTLINK();
        Picasso.with(Mcontext).load(imgurl0).fit().centerCrop()
                .placeholder(R.drawable.event_img)
                .error(R.drawable.event_img)
                .into(holder.img_gal);


/*
        if(position==noticarray.size()-1){
            holder.more.setVisibility(View.VISIBLE);
            holder.more.setText(" + More");
        }*/
        holder.more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  intent  =new Intent(Mcontext, GalleryMobileActivity.class);
                Mcontext.startActivity(intent);
            }
        });
       holder.gallery_layout.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent  intent  =new Intent(Mcontext, GalleryMobileActivity.class);
               Mcontext.startActivity(intent);
           }
       });



        //the problem is here when we put data inside textview



    }

    @Override
    public int getItemCount() {
        return noticarray.size();
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView img_gal;
        private TextView  more;
        private LinearLayout gallery_layout;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            more =(TextView)  itemView.findViewById(R.id.more);
            img_gal  =(ImageView)  itemView.findViewById(R.id.img_gal);
            gallery_layout =(LinearLayout)  itemView.findViewById(R.id.gallery_layout);
        }
    }

}
