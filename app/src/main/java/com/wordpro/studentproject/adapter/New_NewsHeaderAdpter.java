package com.wordpro.studentproject.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pierfrancescosoffritti.youtubeplayer.player.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.youtubeplayer.player.YouTubePlayerView;
import com.squareup.picasso.Picasso;
import com.wordpro.studentproject.R;
import com.wordpro.studentproject.activities.NewsAllViewDetailsList;
import com.wordpro.studentproject.model.NoticPattenChaild;
import com.wordpro.studentproject.model.NoticPattenParents;
import com.wordpro.studentproject.model.NoticPattenSub;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.wordpro.studentproject.adapter.YouVideoDetailsAdpter.getYoutubeThumbnailUrlFromVideoUrl;
import static com.wordpro.studentproject.webConfig.URLEndPoints.ConstanceNoticPattenChaildNOTES;

public class New_NewsHeaderAdpter extends RecyclerView.Adapter<New_NewsHeaderAdpter.ViewHolder>{

    Context Mcontext;
    ArrayList<NoticPattenChaild> noticPattenParents;
    String ViewBox;
    String VideoLink_1,VideoLink_2;
    String ATTACHMENT_LINK;
    public New_NewsHeaderAdpter(Context context, ArrayList<NoticPattenChaild> pendingJobDetails,String viewName) {
        this.Mcontext =context;
        this.noticPattenParents =pendingJobDetails;
        this.ViewBox =viewName;
    }

    @NonNull
    @Override
    public New_NewsHeaderAdpter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int postion) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.all_header_newlist_layout, viewGroup, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int postion) {

        holder.action_News_title.setText(noticPattenParents.get(postion).getnOTICEHEADER());
        holder.action_News_date.setText(noticPattenParents.get(postion).getnOTICESTARTDATE());

        if (noticPattenParents.get(postion).getnOTICEPATTERN().equalsIgnoreCase("VIDEOBOX")){
            holder. video_layout.setVisibility(View.VISIBLE);
            holder. gallery_layout.setVisibility(View.GONE);
            try {

                VideoLink_1 = noticPattenParents.get(postion).getiMAGEPATH();
                VideoLink_2 = noticPattenParents.get(postion).getiMAGEPATH();
                String video_id = "";

                if (TextUtils.isEmpty(VideoLink_1)) {
                    video_id = "";
                }

                String expression = "^.*((youtu.be" + "\\/)" + "|(v\\/)|(\\/u\\/w\\/)|(embed\\/)|(watch\\?))\\??v?=?([^#\\&\\?]*).*"; // var regExp = /^.*((youtu.be\/)|(v\/)|(\/u\/\w\/)|(embed\/)|(watch\?))\??v?=?([^#\&\?]*).*/;
                CharSequence input = VideoLink_1;
                Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
                Matcher matcher = pattern.matcher(input);
                if (matcher.matches()) {
                    String groupIndex1 = matcher.group(7);
                    if (groupIndex1 != null && groupIndex1.length() == 11)
                        video_id = groupIndex1;
                }
                if (TextUtils.isEmpty(video_id)) {
                    if (VideoLink_1.contains("youtu.be/")) {
                        String spl = VideoLink_1.split("youtu.be/")[1];
                        if (spl.contains("\\?")) {
                            video_id = spl.split("\\?")[0];
                        } else {
                            video_id = spl;
                        }

                    }
                }

                //https://i.ytimg.com/vi/5aYSxW1e638

                if(VideoLink_1.contains("vi/")){
                    String[] val=VideoLink_1.split("vi/");
                    video_id=val[1];
                    VideoLink_1=VideoLink_1 + "/maxresdefault.jpg";
                }

                if(video_id.isEmpty() || video_id.equalsIgnoreCase("")){
                    video_id=VideoLink_1;
                }


                String ii = getYoutubeThumbnailUrlFromVideoUrl(VideoLink_1);
                Picasso.with(Mcontext).load(ii).fit().centerCrop()
                        .placeholder(R.drawable.event_img)
                        .error(R.drawable.event_img)
                        .into(holder.imageViewItem1);


                String finalVideo_id = video_id;
                holder.btnPlay1.setOnClickListener(view -> {
                    holder.imageViewItem1.setVisibility(View.GONE);
                    holder.youtube_view1.setVisibility(View.VISIBLE);
                    holder.btnPlay1.setVisibility(View.GONE);
                    holder. youtube_view1.initialize(
                            initializedYouTubePlayer -> initializedYouTubePlayer.addListener(
                                    new AbstractYouTubePlayerListener() {
                                        @Override
                                        public void onReady() {
                                            initializedYouTubePlayer.loadVideo(finalVideo_id, 0);
                                        }
                                    }), true);
                });


            }catch (Exception e){
                e.printStackTrace();
            }

        }else if (noticPattenParents.get(postion).getnOTICEPATTERN().equalsIgnoreCase("GALLERYBOX")){
            holder. gallery_layout.setVisibility(View.VISIBLE);
            holder. video_layout.setVisibility(View.GONE);
            String ImgLink_1 = "http://"+noticPattenParents.get(postion).getIMAGEPATH_STATIC();
           // String ImgLink_2 = "http://117.247.82.252:500/"+noticPattenSubs.get(i).getParent().get(0).getChild().get(1).getiMAGEPATH();
            Picasso.with(Mcontext).load(ImgLink_1).fit().centerCrop()
                    .placeholder(R.drawable.collage_event)
                    .error(R.drawable.collage_event)
                    .into(holder.img_gal_1);
        }


     //holder.txt_news_details.setText(noticPattenParents.get(postion).getChild().get(0).getnOTICEDESCRIPTION());
       // String imgUrl = "http://192.168.1.72:500/"+noticPattenParents.get(postion).getChild().get(0).getiMAGEPATH();
    /*    String imgUrl = "http://117.247.82.252:500/"+noticPattenParents.get(postion).getChild().get(0).getiMAGEPATH();
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
           });*/
    }

    @Override
    public int getItemCount() {
        return noticPattenParents.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView action_News_title,action_News_date;
        private ImageView img_ex_news,newsdetails_img;
        private RecyclerView  recyVwNewsGroup;
        private LinearLayout  video_layout,lay_e_n,gallery_layout;
        private TextView action_Gallery,action_Gallery_title,action_Gallery_date,viewAll_gallery,viewAll_Video,action_Video,action_Video_title,action_Video_date;
        private TextView imp_top_Link,imp_mid_Link,imp_last_Link;
        private ImageView imageViewItem1,imageViewItem,btnPlay1,btnPlay,img_gal_1;
        private YouTubePlayerView youtube_view1,youtube_view;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            //img_ex_news =(ImageView)  itemView.findViewById(R.id.img_ex_news);
            action_News_title =(TextView)  itemView.findViewById(R.id.action_News_title);
            action_News_date =(TextView)  itemView.findViewById(R.id.action_News_date);
            /*txt_news_details =(TextView)  itemView.findViewById(R.id.txt_news_details);
            txt_readMore =(TextView)  itemView.findViewById(R.id.txt_readMore);
*/

            video_layout =(LinearLayout) itemView.findViewById(R.id.video_layout);
            lay_e_n =(LinearLayout) itemView.findViewById(R.id.lay_e_n);
            gallery_layout =(LinearLayout) itemView.findViewById(R.id.gallery_layout);

            img_gal_1=(ImageView) itemView.findViewById(R.id.img_gal_1);
            //img_gal_2=(ImageView) itemView.findViewById(R.id.img_gal_2);

           // imageViewItem=(ImageView) itemView.findViewById(R.id.imageViewItem);
            imageViewItem1=(ImageView) itemView.findViewById(R.id.imageViewItem1);
           // btnPlay=(ImageView) itemView.findViewById(R.id.btnPlay);
            btnPlay1=(ImageView) itemView.findViewById(R.id.btnPlay1);

           // youtube_view=(YouTubePlayerView) itemView.findViewById(R.id.youtube_view);
            youtube_view1=(YouTubePlayerView) itemView.findViewById(R.id.youtube_view1);

            Typeface customTypeOne = Typeface.createFromAsset(itemView.getContext().getAssets(), "font/Poppins-Medium.otf");
            Typeface type_faceHeading = Typeface.createFromAsset(Mcontext.getAssets(), "font/Poppins-Bold.ttf");
            Typeface type_faceContent = Typeface.createFromAsset(Mcontext.getAssets(), "font/Poppins-Regular.ttf");
            //gallery_img =(ImageView)  itemView.findViewById(R.id.gallery_img);
            action_News_title.setTypeface(type_faceHeading);
            action_News_date.setTypeface(type_faceContent);

        }
    }


}
