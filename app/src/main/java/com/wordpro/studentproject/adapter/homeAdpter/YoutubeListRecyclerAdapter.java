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
import com.pierfrancescosoffritti.youtubeplayer.player.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.youtubeplayer.player.YouTubePlayerView;
import com.wordpro.studentproject.R;
import com.wordpro.studentproject.helper.BaseViewHolder;
import com.wordpro.studentproject.model.YoutubeVideo;

import java.util.List;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;

public class YoutubeListRecyclerAdapter extends RecyclerView.Adapter<BaseViewHolder> {


    public static final int VIEW_TYPE_NORMAL = 1;

    private List<YoutubeVideo> mYoutubeVideos;
    Context context;
    DisplayMetrics displayMetrics = new DisplayMetrics();

    // Define listener member variable
    private YoutubeListRecyclerAdapter.OnItemClickListener listener;


    // Define the listener interface
    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }

    // Define the method that allows the parent activity or fragment to define the listener
    public void setOnItemClickListener(YoutubeListRecyclerAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }

    public YoutubeListRecyclerAdapter(List<YoutubeVideo> youtubeVideos, Context context) {
        mYoutubeVideos = youtubeVideos;
        this.context = context;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new YoutubeListRecyclerAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_youtube_list_vertical, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }


    @Override
    public int getItemViewType(int position) {
        return VIEW_TYPE_NORMAL;
    }

    @Override
    public int getItemCount() {
        if (mYoutubeVideos != null && mYoutubeVideos.size() > 0) {
            return mYoutubeVideos.size();
        } else {
            return 1;
        }
    }

    public void setItems(List<YoutubeVideo> youtubeVideos) {
        mYoutubeVideos = youtubeVideos;
        notifyDataSetChanged();
    }


    public class ViewHolder extends BaseViewHolder {

        @BindView(R.id.textViewTitle)
        TextView textWaveTitle;
        @BindView(R.id.btnPlay)
        ImageView playButton;
        @BindView(R.id.imageViewItem)
        ImageView imageViewItems;
        @BindView(R.id.youtube_view)
        YouTubePlayerView youTubePlayerView;
        @BindView(R.id.relativeLytYoutubeVideo)
        RelativeLayout relativeLytYoutubeVideo;
        @BindView(R.id.relativeLytSimpleVideo)
        RelativeLayout relativeLytSimpleVideo;
        @BindView(R.id.relativeLytSimpleText)
        RelativeLayout relativeLytSimpleText;
        @BindView(R.id.imgView)
        ImageView imgView;
        @BindView(R.id.videoView)
        VideoView videoView;

        public ViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            Typeface customTypeOne = Typeface.createFromAsset(itemView.getContext().getAssets(), "font/Poppins-Medium.otf");
            textWaveTitle.setTypeface(customTypeOne);

            // Setup the click listener
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Triggers click upwards to the adapter on click
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(itemView, position);
                        }
                    }
                }
            });

        }

        protected void clear() {

        }

        public void onBind(int position) {
            super.onBind(position);

            final YoutubeVideo mYoutubeVideo = mYoutubeVideos.get(position);
            int width = displayMetrics.widthPixels;
            String videoId = mYoutubeVideo.getVideoId();

            if (videoId.equalsIgnoreCase("SimpleVideo")) {
                relativeLytYoutubeVideo.setVisibility(View.GONE);
                imageViewItems.setVisibility(View.GONE);
                playButton.setVisibility(View.GONE);
                youTubePlayerView.setVisibility(View.GONE);
                //textViewYoutube.setVisibility(View.GONE);
                relativeLytSimpleText.setVisibility(View.GONE);
                imgView.setVisibility(View.GONE);
                //textHeading.setVisibility(View.GONE);
                //textContent.setVisibility(View.GONE);
                relativeLytSimpleVideo.setVisibility(View.VISIBLE);
                videoView.setVisibility(View.VISIBLE);
                //textViewSimpleVideo.setVisibility(View.VISIBLE);


                String title = mYoutubeVideo.getTitle();
                title = title.replace("$", " : ");

                textWaveTitle.setText(title);

                final MediaController mediacontroller = new MediaController(context);
                mediacontroller.setAnchorView(videoView);
                videoView.setMediaController(mediacontroller);
                videoView.setVideoURI(Uri.parse(mYoutubeVideo.getImageUrl()));
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

                                new Handler().postDelayed(new Runnable() {

                                    // Using handler with postDelayed called runnable run method

                                    @Override
                                    public void run() {

                                        videoView.stopPlayback();

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
                        videoView.setVideoURI(Uri.parse(mVideos.get(position).getImageUrl()));
                        videoView.start();
                }
            });
*/
                videoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                    @Override
                    public boolean onError(MediaPlayer mp, int what, int extra) {
                        Log.d("API123", "What " + what + " extra " + extra);
                        return false;
                    }
                });


            } else if (videoId.equalsIgnoreCase("SimpleEvent")) {

                relativeLytYoutubeVideo.setVisibility(View.GONE);
                imageViewItems.setVisibility(View.GONE);
                playButton.setVisibility(View.GONE);
                youTubePlayerView.setVisibility(View.GONE);
                //textViewYoutube.setVisibility(View.GONE);
                relativeLytSimpleVideo.setVisibility(View.GONE);
                videoView.setVisibility(View.GONE);
                //textViewSimpleVideo.setVisibility(View.GONE);

                relativeLytSimpleText.setVisibility(View.VISIBLE);
                imgView.setVisibility(View.VISIBLE);
                //textHeading.setVisibility(View.VISIBLE);
                //textContent.setVisibility(View.VISIBLE);

                ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

                String url = mYoutubeVideo.getImageUrl();
                if (url != null) {
                    Glide.with(context)
                            // .load("https://images.unsplash.com/photo-1520882948759-529963a84160?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=500&q=60").
                            .load(url)
                            .apply(new RequestOptions().override(width - 36, 200))
                            .into(imgView);
                }

                String v = mYoutubeVideo.getTitle();
                String[] val = v.split(Pattern.quote("$"));


                textWaveTitle.setText(val[0]);

                String title = mYoutubeVideo.getTitle();
                if (title != null)
                    textWaveTitle.setText(val[1]);
                textWaveTitle.setMovementMethod(new ScrollingMovementMethod());


            } else {

                if (mYoutubeVideo.getVideoId().contains("https") || mYoutubeVideo.getVideoId().contains("http")) {

                    relativeLytYoutubeVideo.setVisibility(View.GONE);
                    imageViewItems.setVisibility(View.GONE);
                    playButton.setVisibility(View.GONE);
                    youTubePlayerView.setVisibility(View.GONE);
                    //textViewYoutube.setVisibility(View.GONE);
                    relativeLytSimpleText.setVisibility(View.GONE);
                    imgView.setVisibility(View.GONE);
                    //textHeading.setVisibility(View.GONE);
                    //textContent.setVisibility(View.GONE);
                    relativeLytSimpleVideo.setVisibility(View.VISIBLE);
                    videoView.setVisibility(View.VISIBLE);
                    //textViewSimpleVideo.setVisibility(View.VISIBLE);

                    String title = mYoutubeVideo.getTitle();
                    title = title.replace("$", " : ");
                    textWaveTitle.setText(title);


                    final MediaController mediacontroller = new MediaController(context);
                    mediacontroller.setAnchorView(videoView);
                    videoView.setMediaController(mediacontroller);
                    videoView.setVideoURI(Uri.parse(mYoutubeVideo.getImageUrl()));
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
                                    new Handler().postDelayed(new Runnable() {

                                        // Using handler with postDelayed called runnable run method

                                        @Override
                                        public void run() {

                                            videoView.stopPlayback();

                                        }
                                    }, 15 * 1000); // wait for 5 seconds

                                    //Log.d("API123", "What " + what + " extra " + extra);

                                    // videoView.start();

                                }
                            });
                        }
                    });


                    videoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                        @Override
                        public boolean onError(MediaPlayer mp, int what, int extra) {
                            Log.d("API123", "What " + what + " extra " + extra);
                            return false;
                        }
                    });


                } else {


                    relativeLytSimpleVideo.setVisibility(View.GONE);
                    playButton.setVisibility(View.GONE);
                    videoView.setVisibility(View.GONE);
                    //textViewSimpleVideo.setVisibility(View.GONE);
                    relativeLytSimpleText.setVisibility(View.GONE);
                    imgView.setVisibility(View.GONE);
                    //textHeading.setVisibility(View.GONE);
                    //textContent.setVisibility(View.GONE);

                    relativeLytYoutubeVideo.setVisibility(View.VISIBLE);
                    imageViewItems.setVisibility(View.VISIBLE);
                    youTubePlayerView.setVisibility(View.VISIBLE);
                    //textViewYoutube.setVisibility(View.VISIBLE);

                    ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);


                    String title = mYoutubeVideo.getTitle();
                    title = title.replace("$", " : ");
                    if (title != null)

                        textWaveTitle.setText(title);

                    String url = mYoutubeVideo.getImageUrl();

                    if (url != null) {
                        Glide.with(context)
                                .load(url).
                                apply(new RequestOptions().override(width - 36, 200))
                                .into(imageViewItems);
                    }
                    imageViewItems.setVisibility(View.VISIBLE);
                    playButton.setVisibility(View.VISIBLE);
                    youTubePlayerView.setVisibility(View.GONE);

                    playButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            imageViewItems.setVisibility(View.GONE);
                            youTubePlayerView.setVisibility(View.VISIBLE);
                            playButton.setVisibility(View.VISIBLE);

                            youTubePlayerView.initialize(initializedYouTubePlayer -> initializedYouTubePlayer.addListener(new AbstractYouTubePlayerListener() {
                                @Override
                                public void onReady() {
                                    initializedYouTubePlayer.loadVideo(mYoutubeVideo.getVideoId(), 0);
                                }
                            }), true);
                        }
                    });

                }


            }
        }
    }

}