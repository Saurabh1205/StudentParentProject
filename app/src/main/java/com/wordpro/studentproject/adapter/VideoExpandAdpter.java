package com.wordpro.studentproject.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.squareup.picasso.Picasso;
import com.wordpro.studentproject.R;
import com.wordpro.studentproject.activities.ShowDetailsViewActivity;
import com.wordpro.studentproject.fragment.DynamicEventFragmen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by wccs1980 on 30-04-2018.
 */

public class VideoExpandAdpter extends BaseExpandableListAdapter {

    private Context _context;
    private List<String> _listDataHeader; // header titles
    private HashMap<String, List<String>> _listDataChild;
    List<String> listDataHeaderExp;
    private int countList=0;
    private String cot;
    private TextView txt_readMore;
    HashMap<String, List<String>> moduleNamesWithAllParameters = new
            HashMap<String, List<String>>();

    int hight=20;


    public VideoExpandAdpter(Context context, ArrayList<String> groupName, HashMap<String, List<String>> listDataChild) {
        this._context = context;
        this._listDataHeader = groupName;
        this._listDataChild = listDataChild;
      //  countList =listDataChild.size();
    }

    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        /*return this._listDataChild.get(groupPosition)
                .size();*/
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {

        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .get(childPosition);


    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.expandebellist_group, null);
        }
        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.lblListHeader);
        TextView Count = (TextView) convertView
                .findViewById(R.id.itemCount);
        Iterator it = _listDataChild.values().iterator();


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            _listDataChild.entrySet().forEach(e -> {
                countList = e.getValue().size();

                  /*  cot= String.valueOf(countList);
                    Count.setText(cot);*/

            });
        }

        cot= String.valueOf(countList);
        Count.setText(cot);
        lblListHeader.setText(headerTitle);

       //

      /*  String[] header=headerTitle.split(Pattern.quote(" "));
        String unitname=header[0];
        String weightage=header[1];
        String noLec=header[2];
        Typeface customTypeOne = Typeface.createFromAsset(convertView.getContext().getAssets(), "font/Poppins-Medium.otf");

        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.lblListHeader);
        lblListHeader.setTypeface(customTypeOne);
        lblListHeader.setText(unitname);

        TextView lblListCount=(TextView)convertView.findViewById(R.id.lblListCount);
        lblListCount.setTypeface(customTypeOne);
        lblListCount.setText("UNIT "+String.valueOf(groupPosition+1)+" : ");

        TextView lblListWeightage=(TextView)convertView.findViewById(R.id.lblListWeightage);
        lblListWeightage.setTypeface(customTypeOne);
        lblListWeightage.setText(weightage);

        TextView lblListPeriod=(TextView)convertView.findViewById(R.id.lblListPeriod);
        lblListPeriod.setTypeface(customTypeOne);
        lblListPeriod.setText(noLec);*/

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final String childText = (String) getChild(groupPosition, childPosition);
        // data = (NoticesModel.DataBean) getChild(groupPosition, childPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.dynamic_video_fragment, null);
        }
       /* MediaController mediaController = new MediaController(_context);

        String[] value=childText.split(Pattern.quote("$"));
        String topic_name=value[0];
        String topic_descrptn=value[1];
        String topic_img =value[2];
        String topic_id=value[3];

        VideoView videoView = (VideoView) convertView.findViewById(R.id.video_view);
       // ImageView download_img = (ImageView) convertView.findViewById(R.id.download_img);

        TextView txt_header_Name = (TextView) convertView.findViewById(R.id.txt_header_Name);
        TextView txt_header_date = (TextView) convertView.findViewById(R.id.txt_header_date);
        TextView txt_news_details = (TextView) convertView.findViewById(R.id.txt_news_details);
       // TextView txt_downloadAttachment = (TextView) convertView.findViewById(R.id.txt_downloadAttachment);
        TextView txt_share = (TextView) convertView.findViewById(R.id.txt_share);
        TextView txt_bookmark = (TextView) convertView.findViewById(R.id.txt_bookmark);
        txt_readMore = (TextView) convertView.findViewById(R.id.txt_readMore);

        Typeface type_faceHeading = Typeface.createFromAsset(_context.getAssets(), "font/Poppins-Bold.ttf");
        Typeface type_faceContent = Typeface.createFromAsset(_context.getAssets(), "font/Poppins-Regular.ttf");

        txt_header_Name.setTypeface(type_faceHeading);
        txt_header_date.setTypeface(type_faceContent);
        txt_news_details.setTypeface(type_faceContent);

        txt_readMore.setTypeface(type_faceHeading);
      //  txt_downloadAttachment.setTypeface(type_faceHeading);
        txt_share.setTypeface(type_faceHeading);
        txt_bookmark.setTypeface(type_faceHeading);



        txt_header_Name.setText(topic_name);
        txt_header_date.setText("02 -02 -2020  |  12:23 AM");
        txt_news_details.setText(topic_descrptn);

       *//* Uri uri = Uri.parse(topic_img);
        try {
            videoView.setVideoPath(topic_img);
            videoView.start();

        }catch (Exception e){
            e.printStackTrace();
        }
*//*

        videoView.setVideoURI(Uri.parse(topic_img));
        videoView.requestFocus();

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
            }
        });

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                videoView.start();
                mediaPlayer.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() {
                    @Override
                    public void onVideoSizeChanged(MediaPlayer mp, int width, int hight) {
                        MediaController mediaController = new MediaController(_context);
                        videoView.setMediaController(mediaController);
                        mediaController.setAnchorView(videoView);
                    }
                });
            }
        });

        videoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mediaPlayer, int i, int i1) {
                return false;
            }
        });

        txt_readMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(_context, ShowDetailsViewActivity.class);
                intent.putExtra("topic_name",topic_name);
                intent.putExtra("topic_img",topic_img);
                intent.putExtra("topic_descrptn",topic_descrptn);
                intent.putExtra("imgUrl","Video");
                _context.startActivity(intent);
            }
        });
        txt_header_Name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(_context, ShowDetailsViewActivity.class);
                intent.putExtra("topic_name",topic_name);
                intent.putExtra("topic_img",topic_img);
                intent.putExtra("topic_descrptn",topic_descrptn);
                intent.putExtra("imgUrl","Video");
                _context.startActivity(intent);
            }
        });
        txt_news_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(_context, ShowDetailsViewActivity.class);
                intent.putExtra("topic_name",topic_name);
                intent.putExtra("topic_img",topic_img);
                intent.putExtra("topic_descrptn",topic_descrptn);
                intent.putExtra("imgUrl","Video");
                _context.startActivity(intent);
            }
        });*/


        return convertView;
    }



    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

}
