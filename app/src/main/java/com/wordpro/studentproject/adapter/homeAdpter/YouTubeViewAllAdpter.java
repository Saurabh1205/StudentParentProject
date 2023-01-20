package com.wordpro.studentproject.adapter.homeAdpter;

import android.content.Context;
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
import com.wordpro.studentproject.model.NoticPattenChaild;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class YouTubeViewAllAdpter extends RecyclerView.Adapter<YouTubeViewAllAdpter.ViewHolder>{

    Context Mcontext;
    List<NoticPattenChaild> noticPattenChailds;
    private  String  NoticPattern;

    String ATTACHMENT_LINK;
    public YouTubeViewAllAdpter(Context context, List<NoticPattenChaild> pendingJobDetails, String NOTICE_PATTERN) {
        this.Mcontext =context;
        this.noticPattenChailds =pendingJobDetails;
        this.NoticPattern =NOTICE_PATTERN;
    }

    @NonNull
    @Override
    public YouTubeViewAllAdpter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int postion) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.youtuble_details_layout, viewGroup, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int postion) {

        holder.txt_header_Name.setText(noticPattenChailds.get(postion).getgROUPNAME());
        holder.txt_header_date.setText(noticPattenChailds.get(postion).getnOTICESTARTDATE());
       // holder.txt_news_details.setText(noticPattenChailds.get(postion).getnOTICEDESCRIPTION());
        ATTACHMENT_LINK = noticPattenChailds.get(postion).getiMAGEPATH();

       // String imgUrl = "http://117.247.82.252:500/"+noticPattenChailds.get(postion).getiMAGEPATH();
        String video_id = "";

        if (TextUtils.isEmpty(ATTACHMENT_LINK)) {
            video_id = "";
        }

        String expression = "^.*((youtu.be" + "\\/)" + "|(v\\/)|(\\/u\\/w\\/)|(embed\\/)|(watch\\?))\\??v?=?([^#\\&\\?]*).*"; // var regExp = /^.*((youtu.be\/)|(v\/)|(\/u\/\w\/)|(embed\/)|(watch\?))\??v?=?([^#\&\?]*).*/;
        CharSequence input = ATTACHMENT_LINK;
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(input);
        if (matcher.matches()) {
            String groupIndex1 = matcher.group(7);
            if (groupIndex1 != null && groupIndex1.length() == 11)
                video_id = groupIndex1;
        }
        if (TextUtils.isEmpty(video_id)) {
            if (ATTACHMENT_LINK.contains("youtu.be/")) {
                String spl = ATTACHMENT_LINK.split("youtu.be/")[1];
                if (spl.contains("\\?")) {
                    video_id = spl.split("\\?")[0];
                } else {
                    video_id = spl;
                }

            }
        }

        //https://i.ytimg.com/vi/5aYSxW1e638

        if(ATTACHMENT_LINK.contains("vi/")){
            String[] val=ATTACHMENT_LINK.split("vi/");
            video_id=val[1];
            ATTACHMENT_LINK=ATTACHMENT_LINK + "/maxresdefault.jpg";
        }

        if(video_id.isEmpty() || video_id.equalsIgnoreCase("")){
            video_id=ATTACHMENT_LINK;
        }


        String ii = getYoutubeThumbnailUrlFromVideoUrl(ATTACHMENT_LINK);
        Picasso.with(Mcontext).load(ii).fit().centerCrop()
                .placeholder(R.drawable.event_img)
                .error(R.drawable.event_img)
                .into(holder.imageViewItem);

        String finalVideo_id = video_id;
        holder.btnPlay.setOnClickListener(view -> {
            holder.imageViewItem.setVisibility(View.GONE);
            holder.youtube_view.setVisibility(View.VISIBLE);
            holder.btnPlay.setVisibility(View.GONE);
            holder.youtube_view.initialize(
                    initializedYouTubePlayer -> initializedYouTubePlayer.addListener(
                            new AbstractYouTubePlayerListener() {
                                @Override
                                public void onReady() {
                                    initializedYouTubePlayer.loadVideo(finalVideo_id, 0);
                                }
                            }), true);
        });

    }

    @Override
    public int getItemCount() {
        return noticPattenChailds.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt_header_Name,txt_header_date,txt_news_details,txt_readMore,txt_share,txt_bookmark;;
        private ImageView imageViewItem,btnPlay;
        private YouTubePlayerView youtube_view;
        private RecyclerView  recyVwNewsGroup;
        private LinearLayout  layout_news_detail,layout_show_img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageViewItem =(ImageView)  itemView.findViewById(R.id.imageViewItem);
            btnPlay =(ImageView)  itemView.findViewById(R.id.btnPlay);
            txt_header_Name =(TextView)  itemView.findViewById(R.id.txt_header_Name);
            txt_header_date =(TextView)  itemView.findViewById(R.id.txt_header_date);
            txt_news_details =(TextView)  itemView.findViewById(R.id.txt_news_details);
            //txt_readMore =(TextView)  itemView.findViewById(R.id.txt_readMore);
            youtube_view  =(YouTubePlayerView)  itemView.findViewById(R.id.youtube_view);
            Typeface type_faceHeading = Typeface.createFromAsset(Mcontext.getAssets(), "font/Poppins-Bold.ttf");
            Typeface type_faceContent = Typeface.createFromAsset(Mcontext.getAssets(), "font/Poppins-Regular.ttf");

            txt_header_Name.setTypeface(type_faceHeading);
            txt_header_date.setTypeface(type_faceContent);
           // txt_news_details.setTypeface(type_faceContent);

        }
    }
    public static String getYoutubeThumbnailUrlFromVideoUrl(String videoUrl) {
        return "http://img.youtube.com/vi/"+getYoutubeVideoIdFromUrl(videoUrl) + "/0.jpg";
    }

    public static String getYoutubeVideoIdFromUrl(String inUrl) {
        inUrl = inUrl.replace("&feature=youtu.be", "");
        if (inUrl.toLowerCase().contains("youtu.be")) {
            return inUrl.substring(inUrl.lastIndexOf("/") + 1);
        }
        String pattern = "(?<=watch\\?v=|/videos/|embed\\/)[^#\\&\\?]*";
        Pattern compiledPattern = Pattern.compile(pattern);
        Matcher matcher = compiledPattern.matcher(inUrl);
        if (matcher.find()) {
            return matcher.group();
        }
        return null;
    }

}
