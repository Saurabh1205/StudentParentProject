package com.wordpro.studentproject.adapter.homeAdpter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.method.ScrollingMovementMethod;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.pierfrancescosoffritti.youtubeplayer.player.YouTubePlayerView;
import com.wordpro.studentproject.R;
import com.wordpro.studentproject.model.YoutubeVideo;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    Context context;
    ArrayList<YoutubeVideo> newsArrayList;
    ArrayList<YoutubeVideo> youtubeVideos;

    DisplayMetrics displayMetrics = new DisplayMetrics();
    private NewsAdapter.OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(View itemView, int position, ArrayList<YoutubeVideo> newsArrayList);
    }
    public void setOnItemClickListener(NewsAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }

    public NewsAdapter(Context context, ArrayList<YoutubeVideo> newsArrayList) {
        this.context = context;
        this.newsArrayList = newsArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_news_box_items, null);
        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        String videoId=newsArrayList.get(position).getVideoId();

        if(videoId.equalsIgnoreCase("SimpleVideo")){
                    holder.relativeLytYoutubeVideo.setVisibility(View.GONE);
                    holder.imageViewItem.setVisibility(View.GONE);
                    holder.btnPlay.setVisibility(View.GONE);
                    holder.youtube_view.setVisibility(View.GONE);
                    holder.textViewYoutube.setVisibility(View.GONE);
                    holder.relativeLytSimpleText.setVisibility(View.GONE);
                    holder.imgView.setVisibility(View.GONE);
                    holder.textHeading.setVisibility(View.GONE);
                    holder.textContent.setVisibility(View.GONE);
                    holder.relativeLytSimpleVideo.setVisibility(View.VISIBLE);
                    holder.videoView.setVisibility(View.VISIBLE);
                    holder.textViewSimpleVideo.setVisibility(View.VISIBLE);

                    String v=newsArrayList.get(position).getTitle();
                    String[] val=v.split(Pattern.quote("$"));
                    holder.textViewSimpleVideo.setText(val[0]);

                    final MediaController mediacontroller = new MediaController(context);
                    mediacontroller.setAnchorView(holder.videoView);
                    holder.videoView.setMediaController(mediacontroller);
                    holder.videoView.setVideoURI(Uri.parse(newsArrayList.get(position).getImageUrl()));
                    holder.videoView.requestFocus();
                    holder.videoView.start();

                    holder.videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                public void onPrepared(MediaPlayer mp) {
                    mp.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() {
                        @Override
                        public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
                            holder.videoView.setMediaController(mediacontroller);
                            mediacontroller.setAnchorView(holder.videoView);

                            new Handler().postDelayed(new Runnable() {

                                // Using handler with postDelayed called runnable run method

                                @Override
                                public void run() {

                                    holder.videoView.stopPlayback();

                                }
                            }, 15 * 1000); // wait for 5 seconds

                            //Log.d("API123", "What " + what + " extra " + extra);

                            // videoView.start();

                        }
                    });
                }
            });

            holder.videoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                @Override
                public boolean onError(MediaPlayer mp, int what, int extra) {
                    Log.d("API123", "What " + what + " extra " + extra);
                    return false;
                }
            });


        }else if(videoId.equalsIgnoreCase("SimpleEvent")){

            holder.relativeLytYoutubeVideo.setVisibility(View.GONE);
            holder.imageViewItem.setVisibility(View.GONE);
            holder.btnPlay.setVisibility(View.GONE);
            holder.youtube_view.setVisibility(View.GONE);
            holder.textViewYoutube.setVisibility(View.GONE);
            holder.relativeLytSimpleVideo.setVisibility(View.GONE);
            holder.videoView.setVisibility(View.GONE);
            holder.textViewSimpleVideo.setVisibility(View.GONE);

            holder.relativeLytSimpleText.setVisibility(View.VISIBLE);
            holder.imgView.setVisibility(View.VISIBLE);
            holder.textHeading.setVisibility(View.VISIBLE);
            holder.textContent.setVisibility(View.GONE);

            ((Activity) this.context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int width = displayMetrics.widthPixels;
            String url=newsArrayList.get(position).getImageUrl();
            if (url != null) {
                Glide.with(this.context)
                        // .load("https://images.unsplash.com/photo-1520882948759-529963a84160?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=500&q=60").
                        .load(url)
                        .apply(new RequestOptions().override(width - 36, 200))
                        .into(holder.imgView);
            }

            String v=newsArrayList.get(position).getTitle();
            String[] val=v.split(Pattern.quote("$"));


            holder.textHeading.setText(val[0]);

            String title=newsArrayList.get(position).getTitle();
            if (title != null)
                holder.textContent.setText(val[1]);
            holder.textContent.setMovementMethod(new ScrollingMovementMethod());


        }else {

            if(newsArrayList.get(position).getVideoId().contains("https") || newsArrayList.get(position).getVideoId().contains("http")){

                holder.relativeLytYoutubeVideo.setVisibility(View.GONE);
                holder.imageViewItem.setVisibility(View.GONE);
                holder.btnPlay.setVisibility(View.GONE);
                holder.youtube_view.setVisibility(View.GONE);
                holder.textViewYoutube.setVisibility(View.GONE);
                holder.relativeLytSimpleText.setVisibility(View.GONE);
                holder.imgView.setVisibility(View.GONE);
                holder.textHeading.setVisibility(View.GONE);
                holder.textContent.setVisibility(View.GONE);
                holder.relativeLytSimpleVideo.setVisibility(View.VISIBLE);
                holder.videoView.setVisibility(View.VISIBLE);
                holder.textViewSimpleVideo.setVisibility(View.VISIBLE);

                String v=newsArrayList.get(position).getTitle();
                String[] val=v.split(Pattern.quote("$"));
                holder.textViewSimpleVideo.setText(val[0]);

                final MediaController mediacontroller = new MediaController(context);
                mediacontroller.setAnchorView(holder.videoView);
                holder.videoView.setMediaController(mediacontroller);
                holder.videoView.setVideoURI(Uri.parse(newsArrayList.get(position).getImageUrl()));
                holder.videoView.requestFocus();
                holder.videoView.start();


                holder.videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        mp.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() {
                            @Override
                            public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
                                holder.videoView.setMediaController(mediacontroller);
                                mediacontroller.setAnchorView(holder.videoView);
                                new Handler().postDelayed(new Runnable() {

                                    // Using handler with postDelayed called runnable run method

                                    @Override
                                    public void run() {

                                        holder.videoView.stopPlayback();

                                    }
                                }, 15 * 1000); // wait for 5 seconds

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

                holder.videoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                    @Override
                    public boolean onError(MediaPlayer mp, int what, int extra) {
                        Log.d("API123", "What " + what + " extra " + extra);
                        return false;
                    }
                });


            }else{


                holder.relativeLytSimpleVideo.setVisibility(View.GONE);
                holder.btnPlay.setVisibility(View.GONE);
                holder.videoView.setVisibility(View.GONE);
                holder.textViewSimpleVideo.setVisibility(View.GONE);
                holder.relativeLytSimpleText.setVisibility(View.GONE);
                holder.imgView.setVisibility(View.GONE);
                holder.textHeading.setVisibility(View.GONE);
                holder.textContent.setVisibility(View.GONE);

                holder.relativeLytYoutubeVideo.setVisibility(View.VISIBLE);
                holder.imageViewItem.setVisibility(View.VISIBLE);
                holder.youtube_view.setVisibility(View.VISIBLE);
                holder.textViewYoutube.setVisibility(View.VISIBLE);

                ((Activity) this.context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
                int width = displayMetrics.widthPixels;

                String v=newsArrayList.get(position).getTitle();
                String[] val=v.split(Pattern.quote("$"));
                holder.textViewYoutube.setText(val[0]);

                String url=newsArrayList.get(position).getImageUrl();

                if (url != null) {
                    Glide.with(this.context)
                            .load(url).
                            apply(new RequestOptions().override(width - 36, 200))
                            .into(holder.imageViewItem);
                }
                holder.imageViewItem.setVisibility(View.VISIBLE);
                holder.btnPlay.setVisibility(View.VISIBLE);
                holder.youtube_view.setVisibility(View.GONE);

                holder.btnPlay.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        holder.imageViewItem.setVisibility(View.GONE);
                        holder.youtube_view.setVisibility(View.VISIBLE);
                        holder.btnPlay.setVisibility(View.VISIBLE);
                    }
                });

            }

        }

    }

    @Override
    public int getItemCount() {
        return newsArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        RelativeLayout relativeLytYoutubeVideo;
        RelativeLayout relativeLytSimpleVideo;
        RelativeLayout relativeLytSimpleText;
        ImageView imgView;
        TextView textHeading;
        TextView textContent;
        ImageView imageViewItem;
        ImageView btnPlay;
        YouTubePlayerView youtube_view;
        TextView textViewYoutube;
        VideoView videoView;
        TextView textViewSimpleVideo;

        public ViewHolder(final View itemView) {
            super(itemView);

            Typeface customTypeOne = Typeface.createFromAsset(itemView.getContext().getAssets(), "font/Poppins-Medium.otf");

            relativeLytYoutubeVideo=(RelativeLayout)itemView.findViewById(R.id.relativeLytYoutubeVideo);
            relativeLytSimpleVideo=(RelativeLayout)itemView.findViewById(R.id.relativeLytSimpleVideo);
            relativeLytSimpleText=(RelativeLayout)itemView.findViewById(R.id.relativeLytSimpleText);
            imgView=(ImageView)itemView.findViewById(R.id.imgView);
            textHeading=(TextView)itemView.findViewById(R.id.textHeading);
            textHeading.setTypeface(customTypeOne);
            textContent=(TextView)itemView.findViewById(R.id.textContent);
            textContent.setTypeface(customTypeOne);
            imageViewItem=(ImageView)itemView.findViewById(R.id.imageViewItem);
            btnPlay=(ImageView)itemView.findViewById(R.id.btnPlay);
            youtube_view=(YouTubePlayerView)itemView.findViewById(R.id.youtube_view);
            textViewYoutube=(TextView)itemView.findViewById(R.id.textViewYoutube);
            textViewYoutube.setTypeface(customTypeOne);
            videoView=(VideoView)itemView.findViewById(R.id.videoView);
            textViewSimpleVideo=(TextView)itemView.findViewById(R.id.textViewSimpleVideo);
            textViewSimpleVideo.setTypeface(customTypeOne);

            // Setup the click listener
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Triggers click upwards to the adapter on click
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(itemView, position,newsArrayList);
                        }
                    }
                }
            });
        }

    }

}
