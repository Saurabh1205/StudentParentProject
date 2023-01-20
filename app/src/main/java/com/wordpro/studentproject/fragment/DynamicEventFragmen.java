package com.wordpro.studentproject.fragment;

import android.app.ProgressDialog;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

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
import com.wordpro.studentproject.activities.NewRightEventActionActivity;
import com.wordpro.studentproject.adapter.DayWiseAttendanceAdpter;
import com.wordpro.studentproject.app.MyApplication;
import com.wordpro.studentproject.helper.PrefManager;
import com.wordpro.studentproject.model.NoticesModel;
import com.wordpro.studentproject.webConfig.URLEndPoints;

import java.util.ArrayList;
import java.util.regex.Pattern;

import static com.wordpro.studentproject.activities.VerficationActivity.noticesArrayList;
import static com.wordpro.studentproject.activities.VerficationActivity.noticesArrayListupdated;

public class DynamicEventFragmen extends Fragment {
    View view;
    EditText serch_news;
    private RecyclerView  news_recyclerView;
    private TextView  txt_header_Name,txt_header_date,txt_downloadAttachment,txt_share,txt_bookmark,txt_news_details;
    private DayWiseAttendanceAdpter new_eventDetailsAdpter;

    ProgressDialog mProgressDialog;
    private PrefManager pref;
    String studCenterCode,studDepartmentNumber,student_id,studBranchStandardId;
    private static String TAG = NewRightEventActionActivity.class.getSimpleName();
    private TabLayout tabs_news;
    private ViewPager frameLayout_news;
    public static ArrayList<NoticesModel.DataBean> newsArrayList;
    public static ArrayList<NoticesModel.DataBean> eventsArrayList;
    public static ArrayList<NoticesModel.DataBean> videosArrayList;
    public static ArrayList<NoticesModel.DataBean> galleryArrayList;
    int  valText;


    int val;
    TextView c;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.dynamic_fragment, container, false);
        val = getArguments().getInt("someInt",0);
        mProgressDialog=new ProgressDialog(getActivity());
        pref = new PrefManager(getActivity());


       /* txt_header_Name= (TextView) view.findViewById(R.id.txt_header_Name);
        txt_header_date= (TextView)view.findViewById(R.id.txt_header_date);
        txt_downloadAttachment= (TextView)view.findViewById(R.id.txt_downloadAttachment);
        txt_share = (TextView)view.findViewById(R.id.txt_share);
        txt_bookmark= (TextView)view.findViewById(R.id.txt_bookmark);
        news_recyclerView =(RecyclerView) view.findViewById(R.id.news_recyclerView);
        txt_news_details= (TextView)view.findViewById(R.id.txt_news_details);
*/
        studCenterCode = getActivity().getIntent().getStringExtra("studCenterCode");
        studDepartmentNumber =getActivity().getIntent().getStringExtra("studDepartmentNumber");
        student_id =getActivity().getIntent().getStringExtra("student_id");
        studBranchStandardId =getActivity().getIntent().getStringExtra("studBranchStandardId");


        Typeface type_faceHeading = Typeface.createFromAsset(getActivity().getAssets(), "font/Poppins-Bold.ttf");
        Typeface type_faceContent = Typeface.createFromAsset(getActivity().getAssets(), "font/Poppins-Regular.ttf");

    /*    txt_header_Name.setTypeface(type_faceHeading);
        txt_header_date.setTypeface(type_faceContent);
        txt_downloadAttachment.setTypeface(type_faceHeading);
        txt_share.setTypeface(type_faceHeading);
        txt_bookmark.setTypeface(type_faceHeading);
        txt_news_details.setTypeface(type_faceContent);
*/

        funNotices();

        return view;
    }
    public static DynamicEventFragmen addfrag(int val) {
        DynamicEventFragmen fragment = new DynamicEventFragmen();
        Bundle args = new Bundle();
        args.putInt("someInt", val);
        fragment.setArguments(args);
        return fragment;
    }

    private   void  ShowNewData(){

       /* ArrayList<NoticesModel.DataBean> TestNewsArrayList = new ArrayList<>();


       String GroupName = ConstanceArrayNews.get(val).getGROUP_NAME();
        for (int i =0;i<eventsArrayList.size();i++){
            if (GroupName.equalsIgnoreCase(eventsArrayList.get(i).getGROUP_NAME())){
                txt_header_Name.setText(eventsArrayList.get(i).getGROUP_NAME());
                TestNewsArrayList.add(eventsArrayList.get(i));
            }
        }



       // txt_header_Name.setText("Fragment - " + val);
        txt_header_Name.setText(TestNewsArrayList.get(val).getGROUP_NAME());
        txt_news_details.setText(TestNewsArrayList.get(val).getNOTICE_DESCRIPTION());

        DayWiseAttendanceAdpter new_eventDetailsAdpter = new DayWiseAttendanceAdpter(getActivity(), GroupName, (ArrayList<NoticesModel.DataBean>) TestNewsArrayList);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 3);
        news_recyclerView.setLayoutManager(mLayoutManager);
        news_recyclerView.setAdapter(new_eventDetailsAdpter);*/
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

       // Toast.makeText(this, ""+message, Toast.LENGTH_SHORT).show();
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