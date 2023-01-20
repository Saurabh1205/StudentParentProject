package com.wordpro.studentproject.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.wordpro.studentproject.R;
import com.wordpro.studentproject.activities.GalleryMobileActivity;
import com.wordpro.studentproject.adapter.homeAdpter.NewsAdapter;
import com.wordpro.studentproject.model.DynamicArrayList;
import com.wordpro.studentproject.model.NoticPattenParents;
import com.wordpro.studentproject.model.NoticPattenSub;
import com.wordpro.studentproject.model.NoticesModel;
import com.wordpro.studentproject.model.YoutubeVideo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.wordpro.studentproject.webConfig.URLEndPoints.ConstanceNoticPattenChaild;
import static com.wordpro.studentproject.webConfig.URLEndPoints.ConstanceNoticPattenParents;

public class New_GalleryDetailsAdpter extends RecyclerView.Adapter<New_GalleryDetailsAdpter.ViewHolder>{

    Context Mcontext;

   // ArrayList<DynamicArrayList>arrayLists;
    List<NoticPattenParents>noticPattenParents;
    Sub_GalleryImageAdpter imageAdapter;
    String NOTICE_PATTERN;
    public New_GalleryDetailsAdpter(Context context, List<NoticPattenParents> dynamicArrayLists, String NOTICE_PATTERN) {
        this.Mcontext =context;
        this.noticPattenParents =dynamicArrayLists;
        this.NOTICE_PATTERN = NOTICE_PATTERN;


       // ConstanceDynamicArrayList = dynamicArrayLists;
    }

    @NonNull
    @Override
    public New_GalleryDetailsAdpter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.gallery_list_view, viewGroup, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txt_header_Name.setText(noticPattenParents.get(position).getnOTICEHEADER());


        if(NOTICE_PATTERN.equalsIgnoreCase("GALLERYBOX")){

            try {
                String imgurl0  = "http://"+noticPattenParents.get(position).getChild().get(0).getIMAGEPATH_STATIC();
                //String imgurl0 = "http://192.168.1.72:500/" + noticPattenParents.get(position).getChild().get(0).getiMAGEPATH();
                Picasso.with(Mcontext).load(imgurl0).fit().centerCrop()
                        .placeholder(R.drawable.event_img)
                        .error(R.drawable.event_img)
                        .into(holder.img_1);
                String imgurl1  = "http://"+noticPattenParents.get(position).getChild().get(1).getIMAGEPATH_STATIC();
               // String imgurl1 = "http://192.168.1.72:500/" + noticPattenParents.get(position).getChild().get(1).getiMAGEPATH();
                Picasso.with(Mcontext).load(imgurl1).fit().centerCrop()
                        .placeholder(R.drawable.event_img)
                        .error(R.drawable.event_img)
                        .into(holder.img_2);
                String imgurl2  = "http:"+ noticPattenParents.get(position).getChild().get(2).getIMAGEPATH_STATIC();
               // String imgurl2 = "http://192.168.1.72:500/" +  noticPattenParents.get(position).getChild().get(2).getiMAGEPATH();
                Picasso.with(Mcontext).load(imgurl2).fit().centerCrop()
                        .placeholder(R.drawable.event_img)
                        .error(R.drawable.event_img)
                        .into(holder.img_3);
                String imgurl3  = "http://"+ noticPattenParents.get(position).getChild().get(3).getIMAGEPATH_STATIC();
                //String imgurl3 = "http://192.168.1.72:500/" +  noticPattenParents.get(position).getChild().get(3).getiMAGEPATH();
                Picasso.with(Mcontext).load(imgurl3).fit().centerCrop()
                        .placeholder(R.drawable.event_img)
                        .error(R.drawable.event_img)
                        .into(holder.img_4);
                String imgurl4  = "http://"+ noticPattenParents.get(position).getChild().get(4).getIMAGEPATH_STATIC();
               // String imgurl4 = "http://192.168.1.72:500/" +  noticPattenParents.get(position).getChild().get(4).getiMAGEPATH();
                Picasso.with(Mcontext).load(imgurl4).fit().centerCrop()
                        .placeholder(R.drawable.event_img)
                        .error(R.drawable.event_img)
                        .into(holder.img_5);
            }catch (Exception e){
                e.printStackTrace();
            }


        }
        ConstanceNoticPattenParents = noticPattenParents;
        //ConstanceNoticPattenChaild =noticPattenParents.get(position).getChild();





       /* imageAdapter = new Sub_GalleryImageAdpter(Mcontext, arrayLists.get(position).getNotice());
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(Mcontext, 3);
        holder.gallery_recycleView.setLayoutManager(mLayoutManager);
        holder.gallery_recycleView.setAdapter(imageAdapter);*/
       /* if (arrayLists.get(position).getNotice().size()<=5){
            RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(Mcontext, 3);
            holder.gallery_recycleView.setLayoutManager(mLayoutManager);
            holder.gallery_recycleView.setAdapter(imageAdapter);
        }*/


       holder.img_6.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent  =new Intent(Mcontext, GalleryMobileActivity.class);
               intent.putExtra("NoticeHeader", noticPattenParents.get(position).getnOTICEHEADER());
               Mcontext.startActivity(intent);
           }
       });

    }

    @Override
    public int getItemCount() {
        return noticPattenParents.size();
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt_header_Name,txt_header_date,txt_share,txt_bookmark,img_6;
        private ImageView gallery_img,img_1,img_2,img_3,img_4,img_5;
        private RecyclerView gallery_recycleView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txt_header_Name =(TextView)  itemView.findViewById(R.id.txt_header_Name);
            txt_header_date =(TextView)  itemView.findViewById(R.id.txt_header_date);
            txt_share =(TextView)  itemView.findViewById(R.id.txt_share);
            txt_bookmark =(TextView)  itemView.findViewById(R.id.txt_bookmark);

            img_1 =(ImageView)  itemView.findViewById(R.id.img_1);
            img_2 =(ImageView)  itemView.findViewById(R.id.img_2);
            img_3 =(ImageView)  itemView.findViewById(R.id.img_3);
            img_4 =(ImageView)  itemView.findViewById(R.id.img_4);
            img_5 =(ImageView)  itemView.findViewById(R.id.img_5);
            img_6 =(TextView)  itemView.findViewById(R.id.img_6);



            gallery_recycleView  =(RecyclerView)  itemView.findViewById(R.id.gallery_recycleView);
        }
    }

}
