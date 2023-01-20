package com.wordpro.studentproject.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.pierfrancescosoffritti.youtubeplayer.player.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.youtubeplayer.player.YouTubePlayerView;
import com.wordpro.studentproject.R;
import com.wordpro.studentproject.adapter.homeAdpter.YoutubeListRecyclerAdapter;
import com.wordpro.studentproject.model.YoutubeVideo;

import java.util.ArrayList;
import java.util.regex.Pattern;

import static com.wordpro.studentproject.network.Network.isNetworkAvailable;

public class YoutubeListActivity extends Activity {

    String videoID, videoImageUrl, videoTitle, videoYoutubeID,groupNAme,dataFrom;
    ImageView imageViewItem;
    com.pierfrancescosoffritti.youtubeplayer.player.YouTubePlayerView youTubePlayerView;
    ImageView btnPlay;
    //TextView textViewTitle;
    DisplayMetrics displayMetrics = new DisplayMetrics();
    YoutubeListRecyclerAdapter youtubeListRecyclerAdapter;
    RecyclerView newsRecyVw;
    ArrayList<YoutubeVideo> data;

    RelativeLayout relativeLytYoutubeVideo;
    RelativeLayout relativeLytSimpleVideo;
    RelativeLayout relativeLytSimpleText;
    ImageView imgView;
    //TextView textHeading;
    // TextView textContent;
    YouTubePlayerView youtube_view;
    // TextView textViewYoutube;
    VideoView videoView;
    // TextView textViewSimpleVideo;
    TextView txtGroupNAme;
    TextView txtDataFrom;

    TextView textViewHeading,textViewDescriptn;
    FrameLayout frame_container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!isNetworkAvailable(this)) {

            // Create an Alert Dialog
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            // Set the Alert Dialog Message
            builder.setMessage("Internet Connection Required")
                    .setCancelable(false)
                    .setPositiveButton("Retry",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int id) {
                                    // Restart the Activity
                                    Intent intent = getIntent();
                                    finish();
                                    startActivity(intent);
                                }
                            });
            AlertDialog alert = builder.create();
            alert.show();

        } else {

            setContentView(R.layout.activity_youtube_list);
            Typeface typefaceHeading = Typeface.createFromAsset(getAssets(), "font/Poppins-BoldItalic.otf");
            Typeface typefaceContent = Typeface.createFromAsset(getAssets(), "font/Poppins-Medium.otf");

            //Typeface typefaceHeading = Typeface.createFromAsset(getAssets(), "font/KaushanScript-Regular.otf");
            frame_container=(FrameLayout)findViewById(R.id.frame_container);
            relativeLytYoutubeVideo = (RelativeLayout) findViewById(R.id.relativeLytYoutubeVideo);
            relativeLytSimpleVideo = (RelativeLayout) findViewById(R.id.relativeLytSimpleVideo);
            relativeLytSimpleText = (RelativeLayout) findViewById(R.id.relativeLytSimpleText);
            imgView = (ImageView) findViewById(R.id.imgView);
           /* textHeading = (TextView) findViewById(R.id.textHeading);
            textContent = (TextView) findViewById(R.id.textContent);*/
            imageViewItem = (ImageView) findViewById(R.id.imageViewItem);
            btnPlay = (ImageView) findViewById(R.id.btnPlay);
            youtube_view = (YouTubePlayerView) findViewById(R.id.youtube_view);
            // textViewYoutube = (TextView) findViewById(R.id.textViewYoutube);
            videoView = (VideoView) findViewById(R.id.videoView);
            //textViewSimpleVideo = (TextView) findViewById(R.id.textViewSimpleVideo);
            txtGroupNAme=(TextView)findViewById(R.id.txtGroupNAme);
            txtGroupNAme.setTypeface(typefaceContent);
            txtDataFrom=(TextView)findViewById(R.id.txtDataFrom);
            txtDataFrom.setTypeface(typefaceHeading);
            textViewHeading=(TextView)findViewById(R.id.textViewHeading);
            textViewHeading.setTypeface(typefaceContent);
            textViewDescriptn=(TextView)findViewById(R.id.textViewDescriptn);
            textViewDescriptn.setTypeface(typefaceContent);

            newsRecyVw = (RecyclerView) findViewById(R.id.newsRecyVw);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(YoutubeListActivity.this, LinearLayoutManager.VERTICAL, false);
            newsRecyVw.setLayoutManager(layoutManager);
            newsRecyVw.setItemAnimator(new DefaultItemAnimator());
            newsRecyVw.addItemDecoration(new DividerItemDecoration(YoutubeListActivity.this, LinearLayoutManager.VERTICAL));


            // Register to receive messages.
            // We are registering an observer (mMessageReceiver) to receive Intents
            // with actions named "custom-message".
            LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver,
                    new IntentFilter("custom-message"));

            //get Intent

            Intent intent = getIntent();
            videoID = intent.getExtras().getString("videoID");
            videoImageUrl = intent.getExtras().getString("videoImageUrl");
            videoTitle = intent.getExtras().getString("videoTitle");
            videoYoutubeID = intent.getExtras().getString("videoYoutubeID");
            groupNAme=intent.getExtras().getString("groupNAme");
            data = (ArrayList<YoutubeVideo>) intent.getSerializableExtra("newsArrayList");
            dataFrom= intent.getExtras().getString("dataFrom");
            txtDataFrom.setText(dataFrom);
            txtGroupNAme.setText(groupNAme);


            frame_container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent =new Intent(YoutubeListActivity.this,ItemDetailActivity.class);
                    intent.putExtra("videoID", videoID);
                    intent.putExtra("videoImageUrl", videoImageUrl);
                    intent.putExtra("videoTitle", videoTitle);
                    intent.putExtra("videoYoutubeID", videoYoutubeID);
                    intent.putExtra("groupNAme",groupNAme);
                    intent.putExtra("newsArrayList",data);
                    intent.putExtra("dataFrom",dataFrom);
                    startActivity(intent);
                    finish();

                }
            });


            if (videoYoutubeID.equalsIgnoreCase("SimpleVideo")) {
                relativeLytYoutubeVideo.setVisibility(View.GONE);
                imageViewItem.setVisibility(View.GONE);
                btnPlay.setVisibility(View.GONE);
                youtube_view.setVisibility(View.GONE);
                //textViewYoutube.setVisibility(View.GONE);
                relativeLytSimpleText.setVisibility(View.GONE);
                imgView.setVisibility(View.GONE);
                // textHeading.setVisibility(View.GONE);
                // textContent.setVisibility(View.GONE);
                relativeLytSimpleVideo.setVisibility(View.VISIBLE);
                videoView.setVisibility(View.VISIBLE);
                //textViewSimpleVideo.setVisibility(View.VISIBLE);

                String title = videoTitle;
                String[] value=title.split(Pattern.quote("$"));
                textViewHeading.setText(value[0]);
                textViewDescriptn.setText(value[1]);

                final MediaController mediacontroller = new MediaController(YoutubeListActivity.this);
                mediacontroller.setAnchorView(videoView);
                videoView.setMediaController(mediacontroller);
                videoView.setVideoURI(Uri.parse(videoImageUrl));
                videoView.requestFocus();
                videoView.start();


                videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        mp.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() {
                            @Override
                            public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
                                videoView.setMediaController(mediacontroller);
                                mediacontroller.setAnchorView(videoView);
                                //Log.d("API123", "What " + what + " extra " + extra);

                                // videoView.start();

                            }
                        });
                    }
                });

           /* videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    Toast.makeText(context, "Video over", Toast.LENGTH_SHORT).show();

                        mp.release();
                        Toast.makeText(context, "Videos completed", Toast.LENGTH_SHORT).show();
                       *//* videoView.setVideoURI(Uri.parse(mVideos.get(position).getImageUrl()));
                        videoView.start();*//*
                }
            });*/

                videoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                    @Override
                    public boolean onError(MediaPlayer mp, int what, int extra) {
                        Log.d("API123", "What " + what + " extra " + extra);
                        return false;
                    }
                });


            } else if (videoYoutubeID.equalsIgnoreCase("SimpleEvent")) {

                relativeLytYoutubeVideo.setVisibility(View.GONE);
                imageViewItem.setVisibility(View.GONE);
                btnPlay.setVisibility(View.GONE);
                youtube_view.setVisibility(View.GONE);
                //textViewYoutube.setVisibility(View.GONE);
                relativeLytSimpleVideo.setVisibility(View.GONE);
                videoView.setVisibility(View.GONE);
                // textViewSimpleVideo.setVisibility(View.GONE);

                relativeLytSimpleText.setVisibility(View.VISIBLE);
                imgView.setVisibility(View.VISIBLE);
                //textHeading.setVisibility(View.VISIBLE);
                //textContent.setVisibility(View.VISIBLE);

                ((Activity) YoutubeListActivity.this).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
                int width = displayMetrics.widthPixels;
                String url = videoImageUrl;
                if (url != null) {
                    Glide.with(YoutubeListActivity.this)
                            // .load("https://images.unsplash.com/photo-1520882948759-529963a84160?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=500&q=60").
                            .load(url)
                            .apply(new RequestOptions().override(width - 36, 200))
                            .into(imgView);
                }

                String v = videoTitle;
                String[] val = v.split(Pattern.quote("$"));


                // textHeading.setText(val[0]);

                String title = videoTitle;
                String[] value=title.split(Pattern.quote("$"));
                textViewHeading.setText(value[0]);
                textViewDescriptn.setText(value[1]);


            } else {

                if (videoID.contains("https") || videoID.contains("http")) {

                    relativeLytYoutubeVideo.setVisibility(View.GONE);
                    imageViewItem.setVisibility(View.GONE);
                    btnPlay.setVisibility(View.GONE);
                    youtube_view.setVisibility(View.GONE);
                    //  textViewYoutube.setVisibility(View.GONE);
                    relativeLytSimpleText.setVisibility(View.GONE);
                    imgView.setVisibility(View.GONE);
                    // textHeading.setVisibility(View.GONE);
                    //textContent.setVisibility(View.GONE);
                    relativeLytSimpleVideo.setVisibility(View.VISIBLE);
                    videoView.setVisibility(View.VISIBLE);
                    //textViewSimpleVideo.setVisibility(View.VISIBLE);

                    String title = videoTitle;
                    String[] value=title.split(Pattern.quote("$"));
                    textViewHeading.setText(value[0]);
                    textViewDescriptn.setText(value[1]);


                    final MediaController mediacontroller = new MediaController(YoutubeListActivity.this);
                    mediacontroller.setAnchorView(videoView);
                    videoView.setMediaController(mediacontroller);
                    videoView.setVideoURI(Uri.parse(videoImageUrl));
                    videoView.requestFocus();
                    videoView.start();


                    videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mp) {
                            mp.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() {
                                @Override
                                public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
                                    videoView.setMediaController(mediacontroller);
                                    mediacontroller.setAnchorView(videoView);
                                    //Log.d("API123", "What " + what + " extra " + extra);

                                    // videoView.start();

                                }
                            });
                        }
                    });

           /* videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    Toast.makeText(context, "Video over", Toast.LENGTH_SHORT).show();

                        mp.release();
                        Toast.makeText(context, "Videos completed", Toast.LENGTH_SHORT).show();
                       *//* videoView.setVideoURI(Uri.parse(mVideos.get(position).getImageUrl()));
                        videoView.start();*//*
                }
            });*/

                    videoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                        @Override
                        public boolean onError(MediaPlayer mp, int what, int extra) {
                            Log.d("API123", "What " + what + " extra " + extra);
                            return false;
                        }
                    });


                } else {


                    relativeLytSimpleVideo.setVisibility(View.GONE);
                    btnPlay.setVisibility(View.GONE);
                    videoView.setVisibility(View.GONE);
                    // textViewSimpleVideo.setVisibility(View.GONE);
                    relativeLytSimpleText.setVisibility(View.GONE);
                    imgView.setVisibility(View.GONE);
                    // textHeading.setVisibility(View.GONE);
                    //textContent.setVisibility(View.GONE);

                    relativeLytYoutubeVideo.setVisibility(View.VISIBLE);
                    imageViewItem.setVisibility(View.VISIBLE);
                    youtube_view.setVisibility(View.VISIBLE);
                    //textViewYoutube.setVisibility(View.VISIBLE);

                    ((Activity) YoutubeListActivity.this).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
                    int width = displayMetrics.widthPixels;

                    String title = videoTitle;
                    String[] value=title.split(Pattern.quote("$"));
                    textViewHeading.setText(value[0]);
                    textViewDescriptn.setText(value[1]);

                    String url = videoImageUrl;

                    if (url != null) {
                        Glide.with(YoutubeListActivity.this)
                                .load(url).
                                apply(new RequestOptions().override(width - 36, 200))
                                .into(imageViewItem);
                    }
                    imageViewItem.setVisibility(View.VISIBLE);
                    btnPlay.setVisibility(View.VISIBLE);
                    youtube_view.setVisibility(View.GONE);

                    btnPlay.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            imageViewItem.setVisibility(View.GONE);
                            youtube_view.setVisibility(View.VISIBLE);
                            btnPlay.setVisibility(View.GONE);
                            youtube_view.initialize(initializedYouTubePlayer -> initializedYouTubePlayer.addListener(new AbstractYouTubePlayerListener() {
                                @Override
                                public void onReady() {
                                    initializedYouTubePlayer.loadVideo(videoID, 0);
                                }
                            }), true);
                        }
                    });

                }

            }


            Log.d("Data..................", String.valueOf(data.size()));




          /*  youTubePlayerView = (com.pierfrancescosoffritti.youtubeplayer.player.YouTubePlayerView) findViewById(R.id.youtube_view);
            imageViewItem = (ImageView) findViewById(R.id.imageViewItem);
            (YoutubeListActivity.this).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int width = displayMetrics.widthPixels;
            Glide.with(this)
                    .load(videoImageUrl).
                    apply(new RequestOptions().override(width - 36, 200))
                    .into(imageViewItem);
            btnPlay = (ImageView) findViewById(R.id.btnPlay);
            textViewTitle = (TextView) findViewById(R.id.textViewTitle);
            textViewTitle.setText(videoTitle);

            btnPlay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //textViewTitle.setText("Android: Open to the future");
                    imageViewItem.setVisibility(View.GONE);
                    youTubePlayerView.setVisibility(View.VISIBLE);
                    btnPlay.setVisibility(View.GONE);
                    youTubePlayerView.initialize(initializedYouTubePlayer -> initializedYouTubePlayer.addListener(new AbstractYouTubePlayerListener() {
                        @Override
                        public void onReady() {
                            initializedYouTubePlayer.loadVideo(videoYoutubeID, 0);  //Config.YOUTUBE_VIDEO_CODE
                        }
                    }), true);
                }
            });*/

            // prepare data for list
            //List<YoutubeVideo> youtubeVideos = prepareList();
            youtubeListRecyclerAdapter = new YoutubeListRecyclerAdapter(data,YoutubeListActivity.this);
            newsRecyVw.setAdapter(youtubeListRecyclerAdapter);
            youtubeListRecyclerAdapter.setOnItemClickListener(new YoutubeListRecyclerAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View itemView, int position) {

                    videoImageUrl = data.get(position).getImageUrl();
                    videoTitle = data.get(position).getTitle();
                    videoYoutubeID = data.get(position).getVideoId();
                    int width = displayMetrics.widthPixels;
                   /* Glide.with(YoutubeListActivity.this)
                            .load(videoImageUrl).
                            apply(new RequestOptions().override(width - 36, 200))
                            .into(imageViewItem);
                    textViewTitle.setText(videoTitle);

                    imageViewItem.setVisibility(View.GONE);
                    youTubePlayerView.setVisibility(View.VISIBLE);
                    btnPlay.setVisibility(View.GONE);
                    youTubePlayerView.initialize(initializedYouTubePlayer -> initializedYouTubePlayer.addListener(new AbstractYouTubePlayerListener() {
                        @Override
                        public void onReady() {
                            initializedYouTubePlayer.loadVideo(videoYoutubeID, 0);  //Config.YOUTUBE_VIDEO_CODE
                        }
                    }), true);*/


                    if (videoYoutubeID.equalsIgnoreCase("SimpleVideo")) {
                        relativeLytYoutubeVideo.setVisibility(View.GONE);
                        imageViewItem.setVisibility(View.GONE);
                        btnPlay.setVisibility(View.GONE);
                        youtube_view.setVisibility(View.GONE);
                        // textViewYoutube.setVisibility(View.GONE);
                        relativeLytSimpleText.setVisibility(View.GONE);
                        imgView.setVisibility(View.GONE);
                        //textHeading.setVisibility(View.GONE);
                        // textContent.setVisibility(View.GONE);
                        relativeLytSimpleVideo.setVisibility(View.VISIBLE);
                        videoView.setVisibility(View.VISIBLE);
                        //textViewSimpleVideo.setVisibility(View.VISIBLE);

                        String title = videoTitle;
                        String[] value=title.split(Pattern.quote("$"));
                        textViewHeading.setText(value[0]);
                        textViewDescriptn.setText(value[1]);

                        final MediaController mediacontroller = new MediaController(YoutubeListActivity.this);
                        mediacontroller.setAnchorView(videoView);
                        videoView.setMediaController(mediacontroller);
                        videoView.setVideoURI(Uri.parse(videoImageUrl));
                        videoView.requestFocus();
                        videoView.start();


                        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mp) {
                                mp.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() {
                                    @Override
                                    public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
                                        videoView.setMediaController(mediacontroller);
                                        mediacontroller.setAnchorView(videoView);
                                        //Log.d("API123", "What " + what + " extra " + extra);

                                        // videoView.start();

                                    }
                                });
                            }
                        });

           /* videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    Toast.makeText(context, "Video over", Toast.LENGTH_SHORT).show();

                        mp.release();
                        Toast.makeText(context, "Videos completed", Toast.LENGTH_SHORT).show();
                       *//* videoView.setVideoURI(Uri.parse(mVideos.get(position).getImageUrl()));
                        videoView.start();*//*
                }
            });*/

                        videoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                            @Override
                            public boolean onError(MediaPlayer mp, int what, int extra) {
                                Log.d("API123", "What " + what + " extra " + extra);
                                return false;
                            }
                        });


                    } else if (videoYoutubeID.equalsIgnoreCase("SimpleEvent")) {

                        relativeLytYoutubeVideo.setVisibility(View.GONE);
                        imageViewItem.setVisibility(View.GONE);
                        btnPlay.setVisibility(View.GONE);
                        youtube_view.setVisibility(View.GONE);
                        //   textViewYoutube.setVisibility(View.GONE);
                        relativeLytSimpleVideo.setVisibility(View.GONE);
                        videoView.setVisibility(View.GONE);
                        //  textViewSimpleVideo.setVisibility(View.GONE);

                        relativeLytSimpleText.setVisibility(View.VISIBLE);
                        imgView.setVisibility(View.VISIBLE);
                        // textHeading.setVisibility(View.VISIBLE);
                        // textContent.setVisibility(View.VISIBLE);

                        ((Activity) YoutubeListActivity.this).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
                        String url = videoImageUrl;
                        if (url != null) {
                            Glide.with(YoutubeListActivity.this)
                                    // .load("https://images.unsplash.com/photo-1520882948759-529963a84160?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=500&q=60").
                                    .load(url)
                                    .apply(new RequestOptions().override(width - 36, 200))
                                    .into(imgView);
                        }

                        String title = videoTitle;
                        String[] value=title.split(Pattern.quote("$"));
                        textViewHeading.setText(value[0]);
                        textViewDescriptn.setText(value[1]);



                    } else {

                        if (videoID.contains("https") || videoID.contains("http")) {

                            relativeLytYoutubeVideo.setVisibility(View.GONE);
                            imageViewItem.setVisibility(View.GONE);
                            btnPlay.setVisibility(View.GONE);
                            youtube_view.setVisibility(View.GONE);
                            //textViewYoutube.setVisibility(View.GONE);
                            relativeLytSimpleText.setVisibility(View.GONE);
                            imgView.setVisibility(View.GONE);
                            //textHeading.setVisibility(View.GONE);
                            // textContent.setVisibility(View.GONE);
                            relativeLytSimpleVideo.setVisibility(View.VISIBLE);
                            videoView.setVisibility(View.VISIBLE);
                            //textViewSimpleVideo.setVisibility(View.VISIBLE);

                            String title = videoTitle;
                            String[] value=title.split(Pattern.quote("$"));
                            textViewHeading.setText(value[0]);
                            textViewDescriptn.setText(value[1]);


                            final MediaController mediacontroller = new MediaController(YoutubeListActivity.this);
                            mediacontroller.setAnchorView(videoView);
                            videoView.setMediaController(mediacontroller);
                            videoView.setVideoURI(Uri.parse(videoImageUrl));
                            videoView.requestFocus();
                            videoView.start();


                            videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                                @Override
                                public void onPrepared(MediaPlayer mp) {
                                    mp.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() {
                                        @Override
                                        public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
                                            videoView.setMediaController(mediacontroller);
                                            mediacontroller.setAnchorView(videoView);
                                            //Log.d("API123", "What " + what + " extra " + extra);

                                            // videoView.start();

                                        }
                                    });
                                }
                            });

           /* videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    Toast.makeText(context, "Video over", Toast.LENGTH_SHORT).show();

                        mp.release();
                        Toast.makeText(context, "Videos completed", Toast.LENGTH_SHORT).show();
                       *//* videoView.setVideoURI(Uri.parse(mVideos.get(position).getImageUrl()));
                        videoView.start();*//*
                }
            });*/

                            videoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                                @Override
                                public boolean onError(MediaPlayer mp, int what, int extra) {
                                    Log.d("API123", "What " + what + " extra " + extra);
                                    return false;
                                }
                            });


                        } else {


                            relativeLytSimpleVideo.setVisibility(View.GONE);
                            btnPlay.setVisibility(View.GONE);
                            videoView.setVisibility(View.GONE);
                            //   textViewSimpleVideo.setVisibility(View.GONE);
                            relativeLytSimpleText.setVisibility(View.GONE);
                            imgView.setVisibility(View.GONE);
                            // textHeading.setVisibility(View.GONE);
                            // textContent.setVisibility(View.GONE);

                            relativeLytYoutubeVideo.setVisibility(View.VISIBLE);
                            imageViewItem.setVisibility(View.VISIBLE);
                            youtube_view.setVisibility(View.VISIBLE);
                            // textViewYoutube.setVisibility(View.VISIBLE);

                            ((Activity) YoutubeListActivity.this).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

                            String title = videoTitle;
                            String[] value=title.split(Pattern.quote("$"));
                            textViewHeading.setText(value[0]);
                            textViewDescriptn.setText(value[1]);
                            String url = videoImageUrl;

                            if (url != null) {
                                Glide.with(YoutubeListActivity.this)
                                        .load(url).
                                        apply(new RequestOptions().override(width - 36, 200))
                                        .into(imageViewItem);
                            }
                            imageViewItem.setVisibility(View.VISIBLE);
                            btnPlay.setVisibility(View.VISIBLE);
                            youtube_view.setVisibility(View.GONE);

                            btnPlay.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    imageViewItem.setVisibility(View.GONE);
                                    youtube_view.setVisibility(View.VISIBLE);
                                    btnPlay.setVisibility(View.GONE);
                                    youtube_view.initialize(initializedYouTubePlayer -> initializedYouTubePlayer.addListener(new AbstractYouTubePlayerListener() {
                                        @Override
                                        public void onReady() {
                                            initializedYouTubePlayer.loadVideo(videoID, 0);
                                        }
                                    }), true);
                                }
                            });

                        }

                    }
                }
            });

        }

    }

    public BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Get extra data included in the Intent
            videoID = intent.getStringExtra("videoID");
            videoImageUrl = intent.getStringExtra("videoImageUrl");
            videoTitle = intent.getStringExtra("videoTitle");
            videoYoutubeID = intent.getStringExtra("videoYoutubeID");

            Log.d("Values of Receiver : ", "\n" + videoID + "\n" + videoImageUrl + "\n" + videoTitle + "\n" + videoYoutubeID);
        }
    };

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(YoutubeListActivity.this, NavigationActivity.class);
        intent.putExtra("activity", "YoutubeListActivity");
        startActivity(intent);
        finish();
    }
}

