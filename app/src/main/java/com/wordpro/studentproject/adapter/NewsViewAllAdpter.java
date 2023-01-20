package com.wordpro.studentproject.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
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
import com.wordpro.studentproject.activities.NewsAllViewDetailsList;
import com.wordpro.studentproject.model.NoticPattenChaild;
import com.wordpro.studentproject.model.NoticPattenParents;

import java.util.List;

import static com.wordpro.studentproject.webConfig.URLEndPoints.ConstanceNoticPattenChaildNOTES;

public class NewsViewAllAdpter extends RecyclerView.Adapter<NewsViewAllAdpter.ViewHolder>{

    Context Mcontext;
    List<NoticPattenChaild> noticPattenChailds;
    private  String  NoticPattern;

    String ATTACHMENT_LINK;
    public NewsViewAllAdpter(Context context, List<NoticPattenChaild> pendingJobDetails, String NOTICE_PATTERN) {
        this.Mcontext =context;
        this.noticPattenChailds =pendingJobDetails;
        this.NoticPattern =NOTICE_PATTERN;
    }

    @NonNull
    @Override
    public NewsViewAllAdpter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int postion) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.news_view_all_layout, viewGroup, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int postion) {

        holder.txt_header_Name.setText(noticPattenChailds.get(postion).getgROUPNAME());
        holder.txt_header_date.setText(noticPattenChailds.get(postion).getnOTICESTARTDATE());
        holder.txt_news_details.setText(noticPattenChailds.get(postion).getnOTICEDESCRIPTION());
       // String imgUrl = "http://192.168.1.72:500/"+noticPattenChailds.get(postion).getiMAGEPATH();

        String imgUrl = "http://"+noticPattenChailds.get(postion).getIMAGEPATH_STATIC();
           Picasso.with(Mcontext).load(imgUrl).fit().centerCrop()
                        .placeholder(R.drawable.event_img)
                        .error(R.drawable.event_img)
                        .into(holder.img_ex_news);


    }

    @Override
    public int getItemCount() {
        return noticPattenChailds.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt_header_Name,txt_header_date,txt_news_details,txt_readMore,txt_share,txt_bookmark;;
        private ImageView img_ex_news,newsdetails_img;
        private RecyclerView  recyVwNewsGroup;
        private LinearLayout  layout_news_detail,layout_show_img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            img_ex_news =(ImageView)  itemView.findViewById(R.id.img_ex_news);
            txt_header_Name =(TextView)  itemView.findViewById(R.id.txt_header_Name);
            txt_header_date =(TextView)  itemView.findViewById(R.id.txt_header_date);
            txt_news_details =(TextView)  itemView.findViewById(R.id.txt_news_details);
            //txt_readMore =(TextView)  itemView.findViewById(R.id.txt_readMore);

            Typeface type_faceHeading = Typeface.createFromAsset(Mcontext.getAssets(), "font/Poppins-Bold.ttf");
            Typeface type_faceContent = Typeface.createFromAsset(Mcontext.getAssets(), "font/Poppins-Regular.ttf");

            txt_header_Name.setTypeface(type_faceHeading);
            txt_header_date.setTypeface(type_faceContent);
            txt_news_details.setTypeface(type_faceContent);

        }
    }


}
