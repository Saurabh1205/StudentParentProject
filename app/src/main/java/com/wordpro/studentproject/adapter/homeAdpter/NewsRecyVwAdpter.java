package com.wordpro.studentproject.adapter.homeAdpter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wordpro.studentproject.R;
import com.wordpro.studentproject.activities.YoutubeListActivity;
import com.wordpro.studentproject.model.NoticesModel;
import com.wordpro.studentproject.model.YoutubeVideo;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NewsRecyVwAdpter extends RecyclerView.Adapter<NewsRecyVwAdpter.ViewHolder>{

    Context context;
    List<String> listDataHeaderNews;
    ArrayList<NoticesModel.DataBean> newsArrayList;
    NewsAdapter newsAdapter;

    public NewsRecyVwAdpter(Context context, List<String> listDataHeaderNews, ArrayList<NoticesModel.DataBean> newsArrayList) {
        this.context = context;
        this.listDataHeaderNews = listDataHeaderNews;
        this.newsArrayList = newsArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_list_group, null);
        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String groupName=listDataHeaderNews.get(position);
        holder.txtNewsGroupName.setText(groupName);

        ArrayList<YoutubeVideo> youtubeVideosEvents = new ArrayList<>();

        for(int i=0;i<newsArrayList.size();i++){

            String GROUP_NAME = newsArrayList.get(i).getGROUP_NAME();

            if(groupName.equalsIgnoreCase(GROUP_NAME)){

                YoutubeVideo videoNewsData = new YoutubeVideo();

                String ATTACHMENT_LINK = newsArrayList.get(i).getATTACHMENT_LINK();
                String ATTACHMENT_SOURCE = newsArrayList.get(i).getATTACHMENT_SOURCE();
                String ATTACHMENT_TYPE = newsArrayList.get(i).getATTACHMENT_TYPE();
                String NOTICE_DESCRIPTION = newsArrayList.get(i).getNOTICE_DESCRIPTION();
                String NOTICE_HEADER = newsArrayList.get(i).getNOTICE_HEADER();
                String SOFT_COPY_PATH = newsArrayList.get(i).getSOFT_COPY_PATH();
                String STANDARD_PATH = newsArrayList.get(i).getSTANDARD_PATH();

                if (ATTACHMENT_SOURCE.equalsIgnoreCase("OFFLINE") && !STANDARD_PATH.isEmpty() && !SOFT_COPY_PATH.isEmpty()) {

                    if (ATTACHMENT_TYPE.equalsIgnoreCase("VIDEO")) {

                        videoNewsData.setVideoId(String.valueOf(i + 1));
                        videoNewsData.setImageUrl(ATTACHMENT_LINK);
                        videoNewsData.setTitle(NOTICE_HEADER + "$" + NOTICE_DESCRIPTION);
                        videoNewsData.setVideoId("SimpleVideo");
                        videoNewsData.setGroupName(GROUP_NAME);
                        youtubeVideosEvents.add(videoNewsData);


                    } else if (ATTACHMENT_TYPE.equalsIgnoreCase("FILE")) {

                        videoNewsData.setVideoId(String.valueOf(i + 1));
                        videoNewsData.setImageUrl(ATTACHMENT_LINK);
                        videoNewsData.setTitle(NOTICE_HEADER + "$" + NOTICE_DESCRIPTION);
                        videoNewsData.setVideoId("SimpleEvent");
                        videoNewsData.setGroupName(GROUP_NAME);
                        youtubeVideosEvents.add(videoNewsData);

                    }

                } else {
                    if (ATTACHMENT_TYPE.equalsIgnoreCase("VIDEO") && !ATTACHMENT_LINK.isEmpty() || ATTACHMENT_LINK != null || ATTACHMENT_LINK.equalsIgnoreCase("") && ATTACHMENT_SOURCE.equalsIgnoreCase("ONLINE")) {

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
                        if (ATTACHMENT_LINK.contains("vi/")) {
                            String[] val = ATTACHMENT_LINK.split("vi/");
                            video_id = val[1];
                            ATTACHMENT_LINK = ATTACHMENT_LINK + "/maxresdefault.jpg";
                        }

                        if (video_id.isEmpty() || video_id.equalsIgnoreCase("")) {
                            video_id = ATTACHMENT_LINK;
                        }

                        videoNewsData.setVideoId(String.valueOf(i + 1));
                        videoNewsData.setImageUrl(ATTACHMENT_LINK);
                        videoNewsData.setTitle(NOTICE_HEADER + "$" + NOTICE_DESCRIPTION);
                        videoNewsData.setVideoId(video_id);
                        videoNewsData.setGroupName(GROUP_NAME);
                        youtubeVideosEvents.add(videoNewsData);

                    }

                }


            }




        }


        newsAdapter = new NewsAdapter(context, youtubeVideosEvents);
        holder.recyVwNewsGroup.setAdapter(newsAdapter);
        newsAdapter.setOnItemClickListener(new NewsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position,ArrayList<YoutubeVideo> newsArrayList) {
                String videoID = newsArrayList.get(position).getVideoId();
                String videoImageUrl =newsArrayList.get(position).getImageUrl();
                String videoTitle = newsArrayList.get(position).getTitle();
                String videoYoutubeID = newsArrayList.get(position).getVideoId();
                String groupNAme=newsArrayList.get(position).getGroupName();

                Intent intent=new Intent(context, YoutubeListActivity.class);
                intent.putExtra("videoID", videoID);
                intent.putExtra("videoImageUrl", videoImageUrl);
                intent.putExtra("videoTitle", videoTitle);
                intent.putExtra("videoYoutubeID", videoYoutubeID);
                intent.putExtra("groupNAme",groupNAme);
                intent.putExtra("newsArrayList",newsArrayList);
                intent.putExtra("dataFrom","News");

                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listDataHeaderNews.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtNewsGroupName;
        RecyclerView recyVwNewsGroup;

        public ViewHolder(View itemView) {
            super(itemView);

            Typeface customTypeOne = Typeface.createFromAsset(itemView.getContext().getAssets(), "font/Poppins-Medium.otf");

            txtNewsGroupName=(TextView)itemView.findViewById(R.id.txtNewsGroupName);
            txtNewsGroupName.setTypeface(customTypeOne);

            recyVwNewsGroup=(RecyclerView)itemView.findViewById(R.id.recyVwNewsGroup);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            recyVwNewsGroup.setLayoutManager(layoutManager);
            recyVwNewsGroup.setItemAnimator(new DefaultItemAnimator());
            recyVwNewsGroup.addItemDecoration(new DividerItemDecoration(context, LinearLayoutManager.VERTICAL));
        }
    }
}
