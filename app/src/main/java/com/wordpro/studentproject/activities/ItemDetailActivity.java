package com.wordpro.studentproject.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.github.chrisbanes.photoview.PhotoView;
import com.github.chrisbanes.photoview.PhotoViewAttacher;
import com.pierfrancescosoffritti.youtubeplayer.player.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.youtubeplayer.player.YouTubePlayerView;
import com.wordpro.studentproject.R;
import com.wordpro.studentproject.helper.TouchImageView;
import com.wordpro.studentproject.model.YoutubeVideo;

import java.util.ArrayList;

import static com.wordpro.studentproject.network.Network.isNetworkAvailable;

public class ItemDetailActivity extends Activity {


    String videoID, videoImageUrl, videoTitle, videoYoutubeID, groupNAme, dataFrom;
    ArrayList<YoutubeVideo> data;
    TextView textData, textViewDescriptn;
    TouchImageView imageViewItem;
    ImageView btnPlay;
    DisplayMetrics displayMetrics = new DisplayMetrics();
    RelativeLayout relativeLytYoutubeVideo;
    RelativeLayout relativeLytSimpleVideo;
    RelativeLayout relativeLytSimpleText;
    TouchImageView imgView;
    YouTubePlayerView youtube_view;
    VideoView videoView;
    PhotoViewAttacher photoViewAttacher ;



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
            setContentView(R.layout.activity_item_detail);

            Typeface typefaceHeading = Typeface.createFromAsset(getAssets(), "font/Poppins-BoldItalic.otf");
            Typeface typefaceContent = Typeface.createFromAsset(getAssets(), "font/Poppins-Medium.otf");

            Intent intent = getIntent();
            videoID = intent.getExtras().getString("videoID");
            videoImageUrl = intent.getExtras().getString("videoImageUrl");
            videoTitle = intent.getExtras().getString("videoTitle");
            videoYoutubeID = intent.getExtras().getString("videoYoutubeID");
            groupNAme = intent.getExtras().getString("groupNAme");
            data = (ArrayList<YoutubeVideo>) intent.getSerializableExtra("newsArrayList");
            dataFrom = intent.getExtras().getString("dataFrom");


            relativeLytYoutubeVideo = (RelativeLayout) findViewById(R.id.relativeLytYoutubeVideo);
            relativeLytSimpleVideo = (RelativeLayout) findViewById(R.id.relativeLytSimpleVideo);
            relativeLytSimpleText = (RelativeLayout) findViewById(R.id.relativeLytSimpleText);
            imgView = (TouchImageView) findViewById(R.id.imgView);
            imageViewItem = (TouchImageView) findViewById(R.id.imageViewItem);
            btnPlay = (ImageView) findViewById(R.id.btnPlay);
            youtube_view = (YouTubePlayerView) findViewById(R.id.youtube_view);
            videoView = (VideoView) findViewById(R.id.videoView);
            textData = (TextView) findViewById(R.id.textData);
            textData.setTypeface(typefaceHeading);
            textViewDescriptn = (TextView) findViewById(R.id.textViewDescriptn);
            textViewDescriptn.setTypeface(typefaceContent);
            textViewDescriptn.setMovementMethod(new ScrollingMovementMethod());
            textData.setText(dataFrom);

            // Register to receive messages.
            // We are registering an observer (mMessageReceiver) to receive Intents
            // with actions named "custom-message".
            LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver,
                    new IntentFilter("custom-message"));

            if (videoYoutubeID.equalsIgnoreCase("SimpleVideo")) {
                relativeLytYoutubeVideo.setVisibility(View.GONE);
                imageViewItem.setVisibility(View.GONE);
                btnPlay.setVisibility(View.GONE);
                youtube_view.setVisibility(View.GONE);
                relativeLytSimpleText.setVisibility(View.GONE);
                imgView.setVisibility(View.GONE);
                relativeLytSimpleVideo.setVisibility(View.VISIBLE);
                videoView.setVisibility(View.VISIBLE);

                String title = videoTitle;
                title = title.replace("$", ":");
                textViewDescriptn.setText(title);


                final MediaController mediacontroller = new MediaController(ItemDetailActivity.this);
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
                relativeLytSimpleVideo.setVisibility(View.GONE);
                videoView.setVisibility(View.GONE);

                relativeLytSimpleText.setVisibility(View.VISIBLE);
                imgView.setVisibility(View.VISIBLE);
                ((Activity) ItemDetailActivity.this).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
                int width = displayMetrics.widthPixels;
                String url = videoImageUrl;
                if (url != null) {
                    Glide.with(ItemDetailActivity.this)
                            // .load("https://images.unsplash.com/photo-1520882948759-529963a84160?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=500&q=60").
                            .load(url)
                            .apply(new RequestOptions().override(width - 36, 200))
                            .into(imgView);
                }


                String title = videoTitle;
                title = title.replace("$", ":");
                textViewDescriptn.setText(title);

            } else {

                if (videoID.contains("https") || videoID.contains("http")) {

                    relativeLytYoutubeVideo.setVisibility(View.GONE);
                    imageViewItem.setVisibility(View.GONE);
                    btnPlay.setVisibility(View.GONE);
                    youtube_view.setVisibility(View.GONE);
                    relativeLytSimpleText.setVisibility(View.GONE);
                    imgView.setVisibility(View.GONE);
                    relativeLytSimpleVideo.setVisibility(View.VISIBLE);
                    videoView.setVisibility(View.VISIBLE);
                    String title = videoTitle;
                    title = title.replace("$", ":");
                    textViewDescriptn.setText(title);


                    final MediaController mediacontroller = new MediaController(ItemDetailActivity.this);
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
                    relativeLytSimpleText.setVisibility(View.GONE);
                    imgView.setVisibility(View.GONE);
                    relativeLytYoutubeVideo.setVisibility(View.VISIBLE);
                    imageViewItem.setVisibility(View.VISIBLE);
                    youtube_view.setVisibility(View.VISIBLE);


                    ((Activity) ItemDetailActivity.this).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
                    int width = displayMetrics.widthPixels;

                    String title = videoTitle;
                    title = title.replace("$", ":");
                    textViewDescriptn.setText(title);


                    String url = videoImageUrl;

                    if (url != null) {
                        Glide.with(ItemDetailActivity.this)
                                .load(url).
                                apply(new RequestOptions().override(width - 36, 200))
                                .into(imageViewItem);

                        photoViewAttacher = new PhotoViewAttacher(imageViewItem);

                        photoViewAttacher.update();
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
        Intent intent = new Intent(ItemDetailActivity.this, YoutubeListActivity.class);
        intent.putExtra("videoID", videoID);
        intent.putExtra("videoImageUrl", videoImageUrl);
        intent.putExtra("videoTitle", videoTitle);
        intent.putExtra("videoYoutubeID", videoYoutubeID);
        intent.putExtra("groupNAme", groupNAme);
        intent.putExtra("newsArrayList", data);
        intent.putExtra("dataFrom", dataFrom);
        startActivity(intent);
        finish();
    }
}

