package com.wordpro.studentproject.activities;

import android.app.ProgressDialog;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Typeface;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.widget.EditText;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
import com.wordpro.studentproject.R;
import com.wordpro.studentproject.adapter.New_GalleryDetailsAdpter;
import com.wordpro.studentproject.adapter.NewsExpandAdpter;
import com.wordpro.studentproject.adapter.VideoExpandAdpter;
import com.wordpro.studentproject.adapter.ViewPagerGalleryAdapter;
import com.wordpro.studentproject.adapter.ViewPagerVideoAdapter;
import com.wordpro.studentproject.adapter.homeAdpter.VideoRecyViewAdpter;
import com.wordpro.studentproject.adapter.homeAdpter.VideoRecyVwAdpter;
import com.wordpro.studentproject.app.MyApplication;
import com.wordpro.studentproject.helper.PrefManager;
import com.wordpro.studentproject.model.NoticesModel;
import com.wordpro.studentproject.webConfig.URLEndPoints;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

import static com.wordpro.studentproject.activities.NavigationActivity.listDataHeaderVideos1;
import static com.wordpro.studentproject.activities.NavigationActivity.videoRecyVwAdpter;
import static com.wordpro.studentproject.activities.NavigationActivity.videosArrayList1;
import static com.wordpro.studentproject.activities.VerficationActivity.noticesArrayList;
import static com.wordpro.studentproject.activities.VerficationActivity.noticesArrayListupdated;
import static com.wordpro.studentproject.activities.VerficationActivity.videosArrayList;
import static com.wordpro.studentproject.webConfig.URLEndPoints.ConstanceArrayNews;

public class VideoViewActivity extends AppCompatActivity {
    TextView txt_toolbarName;
    private RecyclerView video_recyclerView;
    private ImageView   back_press;
    private ImageView  download_img;

    ProgressDialog mProgressDialog;
    private PrefManager pref;
    String studCenterCode,studDepartmentNumber,student_id,studBranchStandardId;
    private static String TAG = NewRightActionActivity.class.getSimpleName();
    private TabLayout tabs_news;
    private ViewPager frameLayout_news;
    public static ArrayList<NoticesModel.DataBean> newsArrayList;
    public static ArrayList<NoticesModel.DataBean> eventsArrayList;
    public static ArrayList<NoticesModel.DataBean> videosArrayList;
    public static ArrayList<NoticesModel.DataBean> galleryArrayList;


    private ArrayList<String> GroupName;
    private ArrayList<String>listDataHeader;
    private ExpandableListView expandableListView;
    HashMap<String, List<String>> listDataChild;
    private VideoExpandAdpter videoExpandAdpter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newe_expandebal);


        mProgressDialog=new ProgressDialog(VideoViewActivity.this);
        pref = new PrefManager(getApplicationContext());

        txt_toolbarName = (TextView)findViewById(R.id.txt_toolbarName);
        back_press = (ImageView)findViewById(R.id.back_press);
        expandableListView = (ExpandableListView) findViewById(R.id.lvExpNews);


        studCenterCode = getIntent().getStringExtra("studCenterCode");
        studDepartmentNumber =getIntent().getStringExtra("studDepartmentNumber");
        student_id =getIntent().getStringExtra("student_id");
        studBranchStandardId =getIntent().getStringExtra("studBranchStandardId");

        Typeface type_faceHeading = Typeface.createFromAsset(this.getAssets(), "font/Poppins-Bold.ttf");
        Typeface type_faceContent = Typeface.createFromAsset(this.getAssets(), "font/Poppins-Regular.ttf");

        txt_toolbarName.setTypeface(type_faceHeading);
       // serch_news.setTypeface(type_faceContent);

        back_press.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        Resources r = getResources();
        int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                50, r.getDisplayMetrics());
        int px2 = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                10, r.getDisplayMetrics());
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.JELLY_BEAN_MR2) {
            expandableListView.setIndicatorBounds(width - px, width - px2);
        } else {
            expandableListView.setIndicatorBoundsRelative(width - px, width - px2);
        }


        funNotices();
    }

    private   void  ShowNewData(){


        txt_toolbarName.setText("VIDEO");



        listDataHeader = new ArrayList<String>();

        listDataChild = new HashMap<String, List<String>>();


        for(int i=0;i<videosArrayList.size();i++){
            String SYLLABUS_DESCRIPTION=videosArrayList.get(i).getNOTICE_DESCRIPTION();
            listDataHeader.add(SYLLABUS_DESCRIPTION);
        }


        Object[] st = listDataHeader.toArray();
        for (Object s : st) {
            if (listDataHeader.indexOf(s) != listDataHeader.lastIndexOf(s)) {
                listDataHeader.remove(listDataHeader.lastIndexOf(s));
            }
        }

        for (String ListDataHeader : listDataHeader) {
            Log.d(TAG, "ListDataHeader :  "+ ListDataHeader);
        }


        for(int j=0;j<listDataHeader.size();j++){

            String unitName=listDataHeader.get(j);
           /* String[] header=unitName.split(s);
            String unitnameTilte=header[0];
            String weightage=header[1];
            String noLec=header[2];*/
            List<String> topic=new ArrayList<String>();

            for(int i=0;i<videosArrayList.size();i++){

                String SYLLABUS_DESCRIPTION=videosArrayList.get(i).getNOTICE_DESCRIPTION();
                if(unitName.equalsIgnoreCase(SYLLABUS_DESCRIPTION)){

                    String TOPIC_DESCRIPTION=videosArrayList.get(i).getNOTICE_DESCRIPTION();
                    String TOPIC_NAME=videosArrayList.get(i).getGROUP_NAME();
                    String IMG_URL=videosArrayList.get(i).getATTACHMENT_LINK();
                    String TOPIC_ID=videosArrayList.get(i).getNOTC_MASTER_ID();
                    topic.add(TOPIC_NAME+"$"+TOPIC_DESCRIPTION +"$" +IMG_URL +"$"+TOPIC_ID);


                }
            }

            listDataChild.put(unitName,topic);

        }
        videoExpandAdpter = new VideoExpandAdpter(VideoViewActivity.this, listDataHeader, listDataChild);
        expandableListView.setAdapter((ExpandableListAdapter) videoExpandAdpter);

        videoExpandAdpter.notifyDataSetChanged();














       /* List<NoticesModel.DataBean> allEvents = videosArrayList;
        List<NoticesModel.DataBean> noRepeat = new ArrayList<NoticesModel.DataBean>();

        for (NoticesModel.DataBean event : allEvents) {
            boolean isFound = false;
            // check if the event name exists in noRepeat
            for (NoticesModel.DataBean e : noRepeat) {
                if (e.getGROUP_NAME().equals(event.getGROUP_NAME()) || (e.equals(event))) {
                    isFound = true;
                    break;
                }

            }
            if (!isFound) noRepeat.add(event);

        }
        ConstanceArrayNews = (ArrayList<NoticesModel.DataBean>) noRepeat;
        for (int k = 0; k <noRepeat.size(); k++) {
            String GROUP_NAME = noRepeat.get(k).getGROUP_NAME();
            tabs_news.addTab(tabs_news.newTab().setText("" + GROUP_NAME));
            tabs_news.setSelectedTabIndicatorColor(Color.parseColor("#f68e56"));
            tabs_news.setSelectedTabIndicatorHeight((int) (5 * getResources().getDisplayMetrics().density));
            tabs_news.setTabTextColors(Color.parseColor("#727272"), Color.parseColor("#ffffff"));
        }

        ViewPagerVideoAdapter adapter = new ViewPagerVideoAdapter
                (getSupportFragmentManager(), (ArrayList<NoticesModel.DataBean>) noRepeat);
        frameLayout_news.setAdapter(adapter);
        frameLayout_news.setOffscreenPageLimit(1);
        frameLayout_news.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabs_news));
        if (tabs_news.getTabCount() == 2) {
            tabs_news.setTabMode(TabLayout.MODE_FIXED);
        } else {
            tabs_news.setTabMode(TabLayout.MODE_SCROLLABLE);
        }

        tabs_news.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                frameLayout_news.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }

        });
*/

       /* txt_header_Name.setText(videosArrayList.get(0).getGROUP_NAME());


        videoRecyViewAdpter=new VideoRecyViewAdpter(this,videosArrayList1);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 1);
        video_recyclerView.setLayoutManager(mLayoutManager);
        video_recyclerView.setItemAnimator(new DefaultItemAnimator());
        video_recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        if(videosArrayList!=null && videosArrayList.size()!=0){
            video_recyclerView.setAdapter(videoRecyVwAdpter);
        }

        *//*videoRecyVwAdpter = new VideoRecyVwAdpter(VideoViewActivity.this, (ArrayList<NoticesModel.DataBean>) videosArrayList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 4);
        video_recyclerView.setLayoutManager(mLayoutManager);
        video_recyclerView.setAdapter(new_galleryDetailsAdpter);*/
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

        Toast.makeText(this, ""+message, Toast.LENGTH_SHORT).show();
    }
    private void funNotices() {


        String url = pref.getURL() + URLEndPoints.getNoticesURL(studCenterCode,studDepartmentNumber,student_id,studBranchStandardId);

        Log.d(TAG, "URL : " + url);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d(TAG, response.toString());

                try {

                    Gson gson = new Gson();
                    NoticesModel  noticesModel = gson.fromJson(response, NoticesModel.class);

                    if (noticesModel.getStatus() == 1) {

                        noticesArrayList = (ArrayList<NoticesModel.DataBean>) noticesModel.getData();

                        funGetDownloadLink();

                    } else {
                        // mSwipeRefreshLayout.setRefreshing(false);
                     /*   mProgressDialog.hide();
                        Snackbar.make(lytCoordnate, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();*/
                    }


                } catch (Exception e) {
                    mProgressDialog.hide();
                 /*   Snackbar.make(lytCoordnate, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    Log.d(TAG, "Error : " + e.getMessage());*/


                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mProgressDialog.hide();
                handleVolleyError(error);
            }
        });

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(60000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        // Adding request to request queue

        MyApplication.getInstance().addToRequestQueue(stringRequest);


    }

    private void funGetDownloadLink() {

        noticesArrayListupdated = new ArrayList<>();

        newsArrayList = new ArrayList<>();
        eventsArrayList = new ArrayList<>();
        videosArrayList = new ArrayList<>();
        galleryArrayList = new ArrayList<>();

        for (int i = 0; i < noticesArrayList.size(); i++) {

            NoticesModel.DataBean data = new NoticesModel.DataBean();
            String ATTACHMENT_SOURCE = noticesArrayList.get(i).getATTACHMENT_SOURCE();
            String ATTACHMENT_LINK = noticesArrayList.get(i).getATTACHMENT_LINK();

            if(ATTACHMENT_SOURCE.equalsIgnoreCase("")){

            }else if(ATTACHMENT_SOURCE.equalsIgnoreCase("")){

            }



            String APP_NOTIFICATION = noticesArrayList.get(i).getAPP_NOTIFICATION();

            if (ATTACHMENT_SOURCE.equalsIgnoreCase("OFFLINE")) {
                ATTACHMENT_LINK = noticesArrayList.get(i).getATTACHMENT_LINK();
                String str = pref.getURL();          //http://117.247.82.252:500/api/
                String[] s = str.split(Pattern.quote("api"));
                ATTACHMENT_LINK = s[0] + ATTACHMENT_LINK;
                Log.d("download URL : ", ATTACHMENT_LINK);
            }

            String ATTACHMENT_NAME = noticesArrayList.get(i).getATTACHMENT_NAME();
            ATTACHMENT_SOURCE = noticesArrayList.get(i).getATTACHMENT_SOURCE();
            String ATTACHMENT_TYPE = noticesArrayList.get(i).getATTACHMENT_TYPE();
            String GROUP_NAMEE = noticesArrayList.get(i).getGROUP_NAME();
            String MARQUEE = noticesArrayList.get(i).getMARQUEE();
            String NOTC_ATTACHMENT_DTLS_ID = noticesArrayList.get(i).getNOTC_ATTACHMENT_DTLS_ID();
            String NOTC_DISPLAY_PATTRN_ID = noticesArrayList.get(i).getNOTC_DISPLAY_PATTRN_ID();
            String NOTC_GROUP_MASTER_ID = noticesArrayList.get(i).getNOTC_GROUP_MASTER_ID();
            String NOTC_MASTER_ID = noticesArrayList.get(i).getNOTC_MASTER_ID();
            String NOTICE_DESCRIPTION = noticesArrayList.get(i).getNOTICE_DESCRIPTION();
            String NOTICE_HEADER = noticesArrayList.get(i).getNOTICE_HEADER();
            String NOTICE_PATTERN = noticesArrayList.get(i).getNOTICE_PATTERN();
            String SOFT_COPY_NAME = noticesArrayList.get(i).getSOFT_COPY_NAME();
            String SOFT_COPY_PATH = noticesArrayList.get(i).getSOFT_COPY_PATH();
            String SOFT_COPY_SIZE = noticesArrayList.get(i).getSOFT_COPY_SIZE();
            String STANDARD_PATH = noticesArrayList.get(i).getSTANDARD_PATH();


            data.setAPP_NOTIFICATION(APP_NOTIFICATION);
            data.setATTACHMENT_LINK(ATTACHMENT_LINK);
            data.setATTACHMENT_NAME(ATTACHMENT_NAME);
            data.setATTACHMENT_SOURCE(ATTACHMENT_SOURCE);
            data.setATTACHMENT_TYPE(ATTACHMENT_TYPE);
            data.setGROUP_NAME(GROUP_NAMEE);
            data.setMARQUEE(MARQUEE);
            data.setNOTC_ATTACHMENT_DTLS_ID(NOTC_ATTACHMENT_DTLS_ID);
            data.setNOTC_DISPLAY_PATTRN_ID(NOTC_DISPLAY_PATTRN_ID);
            data.setNOTC_GROUP_MASTER_ID(NOTC_GROUP_MASTER_ID);
            data.setNOTC_MASTER_ID(NOTC_MASTER_ID);
            data.setNOTICE_DESCRIPTION(NOTICE_DESCRIPTION);
            data.setNOTICE_HEADER(NOTICE_HEADER);
            data.setNOTICE_PATTERN(NOTICE_PATTERN);
            data.setSOFT_COPY_NAME(SOFT_COPY_NAME);
            data.setSOFT_COPY_PATH(SOFT_COPY_PATH);
            data.setSOFT_COPY_SIZE(SOFT_COPY_SIZE);
            data.setSTANDARD_PATH("\\\\" + STANDARD_PATH);

            noticesArrayListupdated.add(data);


        }

        if (noticesArrayListupdated.size() != 0 && noticesArrayListupdated != null) {

            // noticesArrayList=noticesArrayListupdated;

            for (int i = 0; i < noticesArrayListupdated.size(); i++) {

                String NOTICE_PATTERN = noticesArrayListupdated.get(i).getNOTICE_PATTERN();

                if (NOTICE_PATTERN.equalsIgnoreCase("NEWSBOX")) {

                    newsArrayList.add(noticesArrayListupdated.get(i));

                } else if (NOTICE_PATTERN.equalsIgnoreCase("EVENTBOX")) {

                    eventsArrayList.add(noticesArrayListupdated.get(i));

                } else if (NOTICE_PATTERN.equalsIgnoreCase("VIDEOBOX")) {

                    videosArrayList.add(noticesArrayListupdated.get(i));

                } else if (NOTICE_PATTERN.equalsIgnoreCase("GALLERYBOX")) {

                    galleryArrayList.add(noticesArrayListupdated.get(i));

                }

            }
            ShowNewData();
            //funArrangeData();

        } else {
            mProgressDialog.dismiss();
            //  mSwipeRefreshLayout.setRefreshing(false);
        }





    }

}
