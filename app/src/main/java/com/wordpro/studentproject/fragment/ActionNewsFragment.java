package com.wordpro.studentproject.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.media.ThumbnailUtils;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.pierfrancescosoffritti.youtubeplayer.player.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.youtubeplayer.player.YouTubePlayerView;
import com.squareup.picasso.Picasso;
import com.wordpro.studentproject.R;
import com.wordpro.studentproject.activities.NewRightActionActivity;
import com.wordpro.studentproject.activities.NewRightEventActionActivity;
import com.wordpro.studentproject.activities.NewRightGalleryActionActivity;
import com.wordpro.studentproject.activities.VideoViewActionActivity;
import com.wordpro.studentproject.activities.VideoViewActivity;
import com.wordpro.studentproject.adapter.New_NewsDetailsAdpter;
import com.wordpro.studentproject.adapter.New_NewsHeaderAdpter;
import com.wordpro.studentproject.adapter.SubjectWiseAttendanceAdpter;
import com.wordpro.studentproject.app.MyApplication;
import com.wordpro.studentproject.helper.PrefManager;
import com.wordpro.studentproject.model.DynamicArrayList;
import com.wordpro.studentproject.model.GetStudentDetailsModel;
import com.wordpro.studentproject.model.MainNoticPatten;
import com.wordpro.studentproject.model.NewDynamicArray;
import com.wordpro.studentproject.model.NoticPattenChaild;
import com.wordpro.studentproject.model.NoticPattenParents;
import com.wordpro.studentproject.model.NoticPattenSub;
import com.wordpro.studentproject.model.NoticesModel;
import com.wordpro.studentproject.model.UnivSyllabusModel;
import com.wordpro.studentproject.utils.UtilityClass;
import com.wordpro.studentproject.webConfig.URLEndPoints;
import com.wordpro.studentproject.webConfig.WebConfig;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.wordpro.studentproject.activities.JobsActivity.getStudentDetailsModel;
import static com.wordpro.studentproject.fragment.SumittedFrag.TAG;


public class ActionNewsFragment extends Fragment {

  private RelativeLayout  main_linear_view;
    Fragment me=this;
    ActionNewsFragment actionNewsFragment;
    private TextView  action_News,action_News_title,action_News_date,action_News_title1,action_News_date1,action_News_title2,action_News_date2,viewAll_news;
    private TextView action_Event,action_Events_title,actionEvents_date,action_Events_title1,actionEvents_date1,viewAll_events;
    private ImageView img_gal_1,img_gal_2;
    private ImageView imageViewItem1,imageViewItem,btnPlay1,btnPlay;
    private YouTubePlayerView youtube_view1,youtube_view;
    private TextView action_Gallery,action_Gallery_title,action_Gallery_date,viewAll_gallery,viewAll_Video,action_Video,action_Video_title,action_Video_date;
    private TextView imp_top_Link,imp_mid_Link,imp_last_Link;
    String studCenterCode,studDepartmentNumber,student_id,studBranchStandardId;
    public static ArrayList<NoticesModel.DataBean> newsArrayList;
    public static ArrayList<NoticesModel.DataBean> eventsArrayList;
    public static ArrayList<NoticesModel.DataBean> videosArrayList;
    public static ArrayList<NoticesModel.DataBean> galleryArrayList;
    ProgressDialog mProgressDialog;
    private ProgressBar progBarTimeTable;
    List<DynamicArrayList>arrayLists;
    private List<NoticPattenSub> noticPattenSubs;
    private PrefManager pref;
    final int THUMBNAIL_SIZE = 64;
    String VideoLink_1,VideoLink_2;
    String TopFilter;
    private ArrayList<String> GroupName;
    private LinearLayout newsHeader,eventHeader,galleryHeader,videoHeader;
    private RecyclerView recycle_view_news,recycle_view_event,recycle_view_video,recycle_view_gallery;
    public static Fragment fragment;
    private UtilityClass utilityClassObj;
    private  LinearLayout allEvent;
    public ActionNewsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        final View view = inflater.inflate(R.layout.activity_new_right_action, container, false);
        Typeface type_faceHeading = Typeface.createFromAsset(getActivity().getAssets(), "font/Poppins-Bold.ttf");
        Typeface type_faceContent = Typeface.createFromAsset(getActivity().getAssets(), "font/Poppins-Regular.ttf");
        pref = new PrefManager(getActivity());
        utilityClassObj  = new  UtilityClass(getActivity());


        main_linear_view =(RelativeLayout) view.findViewById(R.id.main_linear_view);

        viewAll_Video =(TextView) view.findViewById(R.id.viewAll_Video);

        progBarTimeTable =(ProgressBar)view.findViewById(R.id.progBarTimeTable);

        imp_top_Link=(TextView) view.findViewById(R.id.imp_top_Link);
        imp_mid_Link=(TextView) view.findViewById(R.id.imp_mid_Link);
        imp_last_Link=(TextView) view.findViewById(R.id.imp_last_Link);

        newsHeader =(LinearLayout) view.findViewById(R.id.newsHeader);
        eventHeader =(LinearLayout) view.findViewById(R.id.eventHeader);
        galleryHeader =(LinearLayout) view.findViewById(R.id.galleryHeader);
        videoHeader=(LinearLayout) view.findViewById(R.id.videoHeader);

        action_News=(TextView) view.findViewById(R.id.action_News);
        action_News_title=(TextView) view.findViewById(R.id.action_News_title);
        action_News_date=(TextView) view.findViewById(R.id.action_News_date);
        action_News_title1=(TextView) view.findViewById(R.id.action_News_title1);
        action_News_date1=(TextView) view.findViewById(R.id.action_News_date1);
        action_News_title2=(TextView) view.findViewById(R.id.action_News_title2);
        action_News_date2=(TextView) view.findViewById(R.id.action_News_date2);
        viewAll_news=(TextView) view.findViewById(R.id.viewAll_news);
        allEvent =(LinearLayout) view.findViewById(R.id.allEvent);

        action_Event=(TextView) view.findViewById(R.id.action_News);
        action_Events_title=(TextView) view.findViewById(R.id.action_Events_title);
        actionEvents_date=(TextView) view.findViewById(R.id.actionEvents_date);
        action_Events_title1=(TextView) view.findViewById(R.id.action_Events_title1);
        actionEvents_date1=(TextView) view.findViewById(R.id.actionEvents_date1);
        viewAll_events=(TextView) view.findViewById(R.id.viewAll_events);

        action_Gallery=(TextView) view.findViewById(R.id.action_Gallery);
        action_Gallery_title=(TextView) view.findViewById(R.id.action_Gallery_title);
        action_Gallery_date=(TextView) view.findViewById(R.id.action_Gallery_date);
        viewAll_gallery=(TextView) view.findViewById(R.id.viewAll_gallery);
        img_gal_1=(ImageView) view.findViewById(R.id.img_gal_1);
        img_gal_2=(ImageView) view.findViewById(R.id.img_gal_2);

        imageViewItem=(ImageView) view.findViewById(R.id.imageViewItem);
        imageViewItem1=(ImageView) view.findViewById(R.id.imageViewItem1);
        btnPlay=(ImageView) view.findViewById(R.id.btnPlay);
        btnPlay1=(ImageView) view.findViewById(R.id.btnPlay1);

        youtube_view=(YouTubePlayerView) view.findViewById(R.id.youtube_view);
        youtube_view1=(YouTubePlayerView) view.findViewById(R.id.youtube_view1);
        action_Gallery.setTypeface(type_faceHeading);
        action_Gallery_title.setTypeface(type_faceHeading);
        action_Gallery_date.setTypeface(type_faceContent);
        action_Events_title1.setTypeface(type_faceHeading);
        viewAll_gallery.setTypeface(type_faceContent);

        recycle_view_news = (RecyclerView) view.findViewById(R.id.recycle_view_news);
        recycle_view_event = (RecyclerView) view.findViewById(R.id.recycle_view_event);
        recycle_view_video = (RecyclerView) view.findViewById(R.id.recycle_view_video);
        recycle_view_gallery = (RecyclerView) view.findViewById(R.id.recycle_view_gallery);


        action_Video=(TextView) view.findViewById(R.id.action_Video);
        action_Video_title=(TextView) view.findViewById(R.id.action_Video_title);
        action_Video_date=(TextView) view.findViewById(R.id.action_Video_date);
        viewAll_Video=(TextView) view.findViewById(R.id.viewAll_Video);

        action_Video.setTypeface(type_faceHeading);
        action_Video_title.setTypeface(type_faceHeading);
        action_Video_date.setTypeface(type_faceContent);
        viewAll_Video.setTypeface(type_faceHeading);



        imp_top_Link.setTypeface(type_faceHeading);
        imp_mid_Link.setTypeface(type_faceHeading);
        imp_last_Link.setTypeface(type_faceHeading);

        action_News.setTypeface(type_faceHeading);
        action_News_title.setTypeface(type_faceHeading);
        action_News_date.setTypeface(type_faceContent);
        action_News_title1.setTypeface(type_faceHeading);
        action_News_date1.setTypeface(type_faceContent);
        action_News_title2.setTypeface(type_faceHeading);
        action_News_date2.setTypeface(type_faceContent);
        viewAll_news.setTypeface(type_faceHeading);

        action_Event.setTypeface(type_faceHeading);
        action_Events_title.setTypeface(type_faceHeading);
        actionEvents_date.setTypeface(type_faceContent);
        action_Events_title1.setTypeface(type_faceHeading);
        actionEvents_date1.setTypeface(type_faceContent);
        viewAll_events.setTypeface(type_faceHeading);
        GroupName =new ArrayList<String>();
        funNotices();

        main_linear_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                container.removeView(view);

              //  getActivity().getFragmentManager().beginTransaction().remove(me).commit();
            }
        });
        try {
            viewAll_news.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        Intent intent = new Intent(getActivity(), NewRightActionActivity.class);
                        intent.putExtra("studCenterCode",URLEndPoints.Constance_StudentCenterCode);
                        intent.putExtra("studDepartmentNumber",URLEndPoints.Constance_StudentDepartmentNumber);
                        intent.putExtra("student_id",URLEndPoints.Constance_StudentID);
                        intent.putExtra("studBranchStandardId",URLEndPoints.Constance_StudBranchStandardId);
                        startActivity(intent);
                    }catch (Exception e){
                        e.printStackTrace();
                    }


                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }



        viewAll_events.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), NewRightEventActionActivity.class);
                intent.putExtra("studCenterCode",URLEndPoints.Constance_StudentCenterCode);
                intent.putExtra("studDepartmentNumber",URLEndPoints.Constance_StudentDepartmentNumber);
                intent.putExtra("student_id",URLEndPoints.Constance_StudentID);
                intent.putExtra("studBranchStandardId",URLEndPoints.Constance_StudBranchStandardId);
                startActivity(intent);

            }
        });

        viewAll_gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), NewRightGalleryActionActivity.class);
                intent.putExtra("studCenterCode",URLEndPoints.Constance_StudentCenterCode);
                intent.putExtra("studDepartmentNumber",URLEndPoints.Constance_StudentDepartmentNumber);
                intent.putExtra("student_id",URLEndPoints.Constance_StudentID);
                intent.putExtra("studBranchStandardId",URLEndPoints.Constance_StudBranchStandardId);
                startActivity(intent);

            }
        });
        viewAll_Video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), VideoViewActionActivity.class);
                intent.putExtra("studCenterCode",URLEndPoints.Constance_StudentCenterCode);
                intent.putExtra("studDepartmentNumber",URLEndPoints.Constance_StudentDepartmentNumber);
                intent.putExtra("student_id",URLEndPoints.Constance_StudentID);
                intent.putExtra("studBranchStandardId",URLEndPoints.Constance_StudBranchStandardId);
                startActivity(intent);


               /* Fragment fragment = new VideosFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.add(R.id.frame_container, fragment);
                fragmentTransaction.commit();
*/

            }
        });
        return view;
    }

    private void funNotices() {
        utilityClassObj.startLoader(getActivity(),R.drawable.image_for_rotation);
        TopFilter ="10";
        String url = pref.getURL() + URLEndPoints.AlertMultiApThreeList(URLEndPoints.Constance_StudentCenterCode,URLEndPoints.Constance_StudentDepartmentNumber,URLEndPoints.Constance_StudentID,URLEndPoints.Constance_StudBranchStandardId,TopFilter);

        Log.d(TAG, "URL : " + url);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d(TAG, response.toString());

                try {

                    Gson gson = new Gson();
                   // mainNoticPattens
                    MainNoticPatten    mainNoticPatten    = gson.fromJson(response, MainNoticPatten.class);
                  //  NewDynamicArray noticesModel = gson.fromJson(response, NewDynamicArray.class);
                    if (mainNoticPatten.getStatus() == 1) {
                        allEvent.setVisibility(View.VISIBLE);
                        utilityClassObj.stopLoader();

                        noticPattenSubs = (ArrayList<NoticPattenSub>) mainNoticPatten.getData();

                        noticPattenSubs.size();

                        ArrayList<NoticPattenChaild> news =new ArrayList<NoticPattenChaild>();
                        ArrayList<NoticPattenChaild> event =new ArrayList<NoticPattenChaild>();
                        ArrayList<NoticPattenChaild> gallery =new ArrayList<NoticPattenChaild>();
                        ArrayList<NoticPattenChaild> video =new ArrayList<NoticPattenChaild>();
                        ArrayList<NoticPattenParents> newsParents =new ArrayList<NoticPattenParents>();
                        ArrayList<NoticPattenParents> eventParents =new ArrayList<NoticPattenParents>();
                        ArrayList<NoticPattenParents> galleryParents =new ArrayList<NoticPattenParents>();
                        ArrayList<NoticPattenParents> videoParents =new ArrayList<NoticPattenParents>();
                        for (int i=0;i<noticPattenSubs.size();i++){
                             for (int j=0;j<noticPattenSubs.get(i).getParent().size();j++) {

                                    if(noticPattenSubs.get(i).getnOTICEPATTERN().equalsIgnoreCase("NEWSBOX")){
                                      NoticPattenParents dd = noticPattenSubs.get(i).getParent().get(j);
                                      newsParents.add(dd);
                                    }else if (noticPattenSubs.get(i).getnOTICEPATTERN().equalsIgnoreCase("EVENTBOX")){
                                      NoticPattenParents dd = noticPattenSubs.get(i).getParent().get(j);
                                      eventParents.add(dd);

                                    }else if (noticPattenSubs.get(i).getnOTICEPATTERN().equalsIgnoreCase("GALLERYBOX")){
                                      NoticPattenParents dd = noticPattenSubs.get(i).getParent().get(j);
                                      galleryParents.add(dd);

                                    }else if (noticPattenSubs.get(i).getnOTICEPATTERN().equalsIgnoreCase("VIDEOBOX")){
                                      NoticPattenParents dd = noticPattenSubs.get(i).getParent().get(j);
                                      videoParents.add(dd);
                                    }


                                 /*  if(noticPattenSubs.get(i).getnOTICEPATTERN().equalsIgnoreCase("NEWSBOX")){
                                        NoticPattenChaild dd = noticPattenSubs.get(i).getParent().get(j).getChild().get(k);
                                        news.add(dd);
                                        newsHeader.setVisibility(View.VISIBLE);
                                        New_NewsHeaderAdpter new_newsHeaderAdpter = new New_NewsHeaderAdpter(getActivity(), news,"NEWSBOX");
                                        recycle_view_news.setLayoutManager(new LinearLayoutManager(getActivity()));
                                        recycle_view_news.setAdapter(new_newsHeaderAdpter);

                                    }else if (noticPattenSubs.get(i).getnOTICEPATTERN().equalsIgnoreCase("EVENTBOX")){
                                        eventHeader.setVisibility(View.VISIBLE);
                                        NoticPattenChaild dd = noticPattenSubs.get(i).getParent().get(j).getChild().get(k);
                                        event.add(dd);
                                        New_NewsHeaderAdpter new_newsHeaderAdpter = new New_NewsHeaderAdpter(getActivity(), event,"EVENTBOX");
                                        recycle_view_event.setLayoutManager(new LinearLayoutManager(getActivity()));
                                        recycle_view_event.setAdapter(new_newsHeaderAdpter);

                                    }else if (noticPattenSubs.get(i).getnOTICEPATTERN().equalsIgnoreCase("GALLERYBOX")){
                                        NoticPattenChaild dd = noticPattenSubs.get(i).getParent().get(j).getChild().get(k);
                                        gallery.add(dd);
                                        galleryHeader.setVisibility(View.VISIBLE);
                                        New_NewsHeaderAdpter new_newsHeaderAdpter = new New_NewsHeaderAdpter(getActivity(), gallery,"GALLERYBOX");
                                        recycle_view_gallery.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,
                                                false));
                                        recycle_view_gallery.setAdapter(new_newsHeaderAdpter);


                                    }else if (noticPattenSubs.get(i).getnOTICEPATTERN().equalsIgnoreCase("VIDEOBOX")){
                                        videoHeader.setVisibility(View.VISIBLE);
                                        NoticPattenChaild dd = noticPattenSubs.get(i).getParent().get(j).getChild().get(k);
                                        video.add(dd);
                                        New_NewsHeaderAdpter new_newsHeaderAdpter = new New_NewsHeaderAdpter(getActivity(), video,"VIDEOBOX");
                                        recycle_view_video.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,
                                                false));
                                        recycle_view_video.setAdapter(new_newsHeaderAdpter);

                                    }*/

                            }

                        }
                        if (newsParents.size()>0){
                            for (int a=0;a<newsParents.size();a++){
                                for (int b=0;b<newsParents.get(a).getChild().size();b++){
                                    NoticPattenChaild dd = newsParents.get(a).getChild().get(b);
                                    news.add(dd);
                                    newsHeader.setVisibility(View.VISIBLE);
                                    New_NewsHeaderAdpter new_newsHeaderAdpter = new New_NewsHeaderAdpter(getActivity(), news,"NEWSBOX");
                                    recycle_view_news.setLayoutManager(new LinearLayoutManager(getActivity()));
                                    recycle_view_news.setAdapter(new_newsHeaderAdpter);
                                }

                            }

                        }
                        if (eventParents.size()>0){
                            for (int a=0;a<eventParents.size();a++){
                                for (int b=0;b<eventParents.get(a).getChild().size();b++){
                                    NoticPattenChaild dd = eventParents.get(a).getChild().get(b);
                                    event.add(dd);
                                    eventHeader.setVisibility(View.VISIBLE);
                                    New_NewsHeaderAdpter new_newsHeaderAdpter = new New_NewsHeaderAdpter(getActivity(), event,"EVENTBOX");
                                    recycle_view_event.setLayoutManager(new LinearLayoutManager(getActivity()));
                                    recycle_view_event.setAdapter(new_newsHeaderAdpter);
                                }

                            }

                        }
                        if (galleryParents.size()>0){
                            for (int a=0;a<galleryParents.size();a++){
                                for (int b=0;b<galleryParents.get(a).getChild().size();b++){
                                    NoticPattenChaild dd = galleryParents.get(a).getChild().get(b);
                                    gallery.add(dd);
                                    galleryHeader.setVisibility(View.VISIBLE);
                                    New_NewsHeaderAdpter new_newsHeaderAdpter = new New_NewsHeaderAdpter(getActivity(), gallery,"GALLERYBOX");
                                    recycle_view_gallery.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,
                                            false));
                                    recycle_view_gallery.setAdapter(new_newsHeaderAdpter);
                                }

                            }

                        }
                        if (videoParents.size()>0){
                            for (int a=0;a<newsParents.size();a++){
                                for (int b=0;b<videoParents.get(a).getChild().size();b++){
                                    NoticPattenChaild dd = videoParents.get(a).getChild().get(b);
                                    video.add(dd);
                                    videoHeader.setVisibility(View.VISIBLE);
                                    New_NewsHeaderAdpter new_newsHeaderAdpter = new New_NewsHeaderAdpter(getActivity(), video,"VIDEOBOX");
                                    recycle_view_video.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,
                                            false));
                                    recycle_view_video.setAdapter(new_newsHeaderAdpter);
                                }

                            }

                        }
                    } else {
                        utilityClassObj.stopLoader();
                        // mSwipeRefreshLayout.setRefreshing(false);
                      //  mProgressDialog.hide();
                        allEvent.setVisibility(View.INVISIBLE);
                        Snackbar.make(main_linear_view, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                    utilityClassObj.stopLoader();
              //      mProgressDialog.hide();
                  /*  Snackbar.make(main_linear_view, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    Log.d(TAG, "Error : " + e.getMessage());
*/

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
              //  mProgressDialog.hide();
                utilityClassObj.stopLoader();
                allEvent.setVisibility(View.INVISIBLE);
                handleVolleyError(error);
            }
        });

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(60000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        // Adding request to request queue

        MyApplication.getInstance().addToRequestQueue(stringRequest);


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
    private void handleVolleyError(VolleyError error) {
        //if any error occurs in the network operations, show the TextView that contains the error message
        String message = null;
        if (error instanceof TimeoutError || error instanceof NoConnectionError) {
            message = getResources().getString(R.string.error_timeout);
        } else if (error instanceof AuthFailureError) {
            message = getResources().getString(R.string.error_auth_failure);
            //TODO
        } else if (error instanceof ServerError) {
            message = getResources().getString(R.string.error_auth_failure);
            //TODO
        } else if (error instanceof NetworkError) {
            message = getResources().getString(R.string.error_network);
            //TODO
        } else if (error instanceof ParseError) {
            message = getResources().getString(R.string.error_parser);
            //TODO
        }

        Toast.makeText(getActivity(), ""+message, Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onStart() {
        super.onStart();
        initializePlayer();
    }
    @Override
    public void onStop() {
        super.onStop();
        releasePlayer();
    }
    private void releasePlayer() {
        try {
         /*   video_1.setVideoPath(VideoLink_1);
            video_1.stopPlayback();
            video_2.setVideoPath(VideoLink_2);
            video_2.stopPlayback();*/
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    private void initializePlayer() {
      /*  try {
            video_1.setVideoPath(VideoLink_1);
            video_1.start();
            video_2.setVideoPath(VideoLink_2);
            video_2.start();
        }catch (Exception e){
            e.printStackTrace();
        }*/

    }

    @Override
    public void onResume() {
        super.onResume();

     /*   try {
            video_1.setVideoPath(VideoLink_1);
            video_1.start();
            video_2.setVideoPath(VideoLink_2);
            video_2.start();
        }catch (Exception e){
            e.printStackTrace();
        }*/

    }
}
