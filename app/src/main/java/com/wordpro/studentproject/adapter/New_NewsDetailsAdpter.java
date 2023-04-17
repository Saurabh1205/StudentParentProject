package com.wordpro.studentproject.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.wordpro.studentproject.R;
import com.wordpro.studentproject.activities.NewsAllViewDetailsList;
import com.wordpro.studentproject.activities.YoutubeListActivity;
import com.wordpro.studentproject.adapter.homeAdpter.NewsAdapter;
import com.wordpro.studentproject.helper.PrefManager;
import com.wordpro.studentproject.model.GetStudentDetailsModel;
import com.wordpro.studentproject.model.NoticPattenParents;
import com.wordpro.studentproject.model.NoticesModel;
import com.wordpro.studentproject.model.YoutubeVideo;
import com.wordpro.studentproject.webConfig.URLEndPoints;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.wordpro.studentproject.webConfig.URLEndPoints.ConstanceNoticPattenChaildNOTES;

public class New_NewsDetailsAdpter extends RecyclerView.Adapter<New_NewsDetailsAdpter.ViewHolder>{

    Context Mcontext;
    List<NoticPattenParents> noticPattenParents;
    private PrefManager pref;
    String ATTACHMENT_LINK;
    public New_NewsDetailsAdpter(Context context, List<NoticPattenParents> pendingJobDetails, String NOTICE_PATTERN) {
        this.Mcontext =context;
        this.noticPattenParents =pendingJobDetails;
        pref = new PrefManager(Mcontext);
    }

    @NonNull
    @Override
    public New_NewsDetailsAdpter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int postion) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.gallery_item_adapter, viewGroup, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int postion) {

        holder.txt_header_Name.setText(noticPattenParents.get(postion).getnOTICEHEADER());
        holder.txt_header_date.setText(noticPattenParents.get(postion).getChild().get(0).getnOTICESTARTDATE());
        holder.txt_news_details.setText(noticPattenParents.get(postion).getChild().get(0).getnOTICEDESCRIPTION());
       // String imgUrl = "http://192.168.1.72:500/"+noticPattenParents.get(postion).getChild().get(0).getiMAGEPATH();

        String sourcePath = pref.getURL();

        /*String fileName, Extent;
        String ORG,Path;


        //"\\\\" +

        String str = sourcePath.substring(sourcePath.lastIndexOf("\\") + 1);
        String[] value = str.split(Pattern.quote("http"));
        fileName = value[0];
        Extent = value[1];


        String[] colon = sourcePath.split(Pattern.quote(":"));
        ORG= colon[0];
        Path= colon[1];





        String aa = ORG+":"+Path;*/





        String imgUrl ="http://"+noticPattenParents.get(postion).getChild().get(0).getIMAGEPATH_STATIC();
           Picasso.with(Mcontext).load(imgUrl).fit().centerCrop()
                        .placeholder(R.drawable.event_img)
                        .error(R.drawable.event_img)
                        .into(holder.img_ex_news);


           holder.txt_readMore.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   Intent intent =new Intent(Mcontext, NewsAllViewDetailsList.class);
                   intent.putExtra("NoticeHeader",noticPattenParents.get(postion).getnOTICEHEADER());
                   Mcontext.startActivity(intent);
                   ConstanceNoticPattenChaildNOTES = noticPattenParents.get(postion).getChild();
               }
           });
    }

    @Override
    public int getItemCount() {
        return noticPattenParents.size();
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
            txt_readMore =(TextView)  itemView.findViewById(R.id.txt_readMore);

            Typeface customTypeOne = Typeface.createFromAsset(itemView.getContext().getAssets(), "font/Poppins-Medium.otf");
            Typeface type_faceHeading = Typeface.createFromAsset(Mcontext.getAssets(), "font/Poppins-Bold.ttf");
            Typeface type_faceContent = Typeface.createFromAsset(Mcontext.getAssets(), "font/Poppins-Regular.ttf");
            //gallery_img =(ImageView)  itemView.findViewById(R.id.gallery_img);
            txt_header_Name.setTypeface(type_faceHeading);
            txt_header_date.setTypeface(type_faceContent);
            txt_news_details.setTypeface(type_faceContent);
        }
    }


}
