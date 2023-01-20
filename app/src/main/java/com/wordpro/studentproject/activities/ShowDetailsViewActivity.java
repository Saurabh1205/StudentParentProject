package com.wordpro.studentproject.activities;

import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.squareup.picasso.Picasso;
import com.wordpro.studentproject.R;

public class ShowDetailsViewActivity extends AppCompatActivity {


    private ImageView  back_press,img_ex_news;
    private TextView  txt_toolbarName,txt_news_details;
    private VideoView video_view;

    private  String topHeaderName,topicDes,topImgUrl,topVideoUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_details_view);

        back_press =(ImageView)findViewById(R.id.back_press);
        img_ex_news =(ImageView)findViewById(R.id.img_ex_news);
        txt_toolbarName =(TextView) findViewById(R.id.txt_toolbarName);
        txt_news_details =(TextView) findViewById(R.id.txt_news_details);
        video_view  =(VideoView) findViewById(R.id.video_view);

        Typeface type_faceHeading = Typeface.createFromAsset(this.getAssets(), "font/Poppins-Bold.ttf");
        Typeface type_faceContent = Typeface.createFromAsset(this.getAssets(), "font/Poppins-Regular.ttf");


        topHeaderName = getIntent().getStringExtra("topic_name");
        topicDes =getIntent().getStringExtra("topic_descrptn");
        topImgUrl =getIntent().getStringExtra("topic_img");
        topVideoUrl =getIntent().getStringExtra("imgUrl");

        txt_toolbarName.setTypeface(type_faceHeading);
        txt_news_details.setTypeface(type_faceContent);


        txt_toolbarName.setText(topHeaderName);
        txt_news_details.setText(topicDes);

        if (topVideoUrl.equalsIgnoreCase("Video")){
            video_view.setVisibility(View.VISIBLE);
            img_ex_news.setVisibility(View.GONE);
           /* Uri uri = Uri.parse(topImgUrl);
            video_view.setVideoURI(uri);
            video_view.start();*/



            video_view.setVideoURI(Uri.parse(topImgUrl));
            video_view.requestFocus();

            video_view.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                }
            });

            video_view.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    video_view.start();
                    mediaPlayer.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() {
                        @Override
                        public void onVideoSizeChanged(MediaPlayer mp, int width, int hight) {
                            MediaController mediaController = new MediaController(getApplicationContext());
                            video_view.setMediaController(mediaController);
                            mediaController.setAnchorView(video_view);
                        }
                    });
                }
            });

            video_view.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                @Override
                public boolean onError(MediaPlayer mediaPlayer, int i, int i1) {
                    return false;
                }
            });
















        }else {
            video_view.setVisibility(View.GONE);
            img_ex_news.setVisibility(View.VISIBLE);
            Picasso.with(this).load(topImgUrl).fit().centerCrop()
                    .placeholder(R.drawable.event_img)
                    .error(R.drawable.event_img)
                    .into(img_ex_news);
























        }

        back_press.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }


}
