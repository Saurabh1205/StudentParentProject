package com.wordpro.studentproject.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
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
import com.wordpro.studentproject.app.MyApplication;
import com.wordpro.studentproject.helper.PrefManager;
import com.wordpro.studentproject.model.DynamicArrayList;
import com.wordpro.studentproject.model.MainNoticPatten;
import com.wordpro.studentproject.model.NoticPattenParents;
import com.wordpro.studentproject.model.NoticPattenSub;
import com.wordpro.studentproject.webConfig.URLEndPoints;

import java.util.ArrayList;
import java.util.List;

public class NewRightGalleryActionActivity extends AppCompatActivity {


    TextView txt_toolbarName;
    private ImageView back_press;
    private New_GalleryDetailsAdpter new_galleryDetailsAdpter;
    ProgressDialog mProgressDialog;
    private PrefManager pref;
    String studCenterCode,studDepartmentNumber,student_id,studBranchStandardId;
    private static String TAG = NewRightActionActivity.class.getSimpleName();
    ArrayList<DynamicArrayList>arrayLists;
    private RecyclerView recyclerViewMainGallery;
    private ArrayList<String> GroupName;
    private String TopFilter,NotificationPatten;
    private List<NoticPattenSub> noticPattenSubs;
    private List <NoticPattenParents> noticPattenParents;
    private String NOTICE_PATTERN;
    private  String SearchFilerName=null;
    private int PageCount =1,FilterID=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gallery_main_activity_layout);
        mProgressDialog=new ProgressDialog(NewRightGalleryActionActivity.this);
        pref = new PrefManager(getApplicationContext());

        txt_toolbarName = (TextView)findViewById(R.id.txt_toolbarName);
        back_press = (ImageView)findViewById(R.id.back_press);
        recyclerViewMainGallery = (RecyclerView) findViewById(R.id.gallery_mainView);

        studCenterCode = getIntent().getStringExtra("studCenterCode");
        studDepartmentNumber =getIntent().getStringExtra("studDepartmentNumber");
        student_id =getIntent().getStringExtra("student_id");
        studBranchStandardId =getIntent().getStringExtra("studBranchStandardId");

        Typeface type_faceHeading = Typeface.createFromAsset(this.getAssets(), "font/Poppins-Bold.ttf");
        Typeface type_faceContent = Typeface.createFromAsset(this.getAssets(), "font/Poppins-Regular.ttf");
        txt_toolbarName.setText("GALLERY");
        txt_toolbarName.setTypeface(type_faceHeading);

        GroupName =new ArrayList<String>();
        back_press.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //onBackPressed();
                Intent intent = new Intent(getApplicationContext(), NavigationActivity.class);
                startActivity(intent);
            }
        });

        TopFilter ="30";
        NotificationPatten ="GALLERYBOX";
        funNotices();
    }




    //new webservices call for gallery view (new design)

    private void funNotices() {


        //String url = pref.getURL() + URLEndPoints.getNoticesAllURL(URLEndPoints.Constance_StudentCenterCode,URLEndPoints.Constance_StudentDepartmentNumber,URLEndPoints.Constance_StudentID,URLEndPoints.Constance_StudBranchStandardId,TopFilter);
        String url = pref.getURL() + URLEndPoints.AlertMultiApThreeListViewAll(URLEndPoints.Constance_StudentCenterCode,URLEndPoints.Constance_StudentDepartmentNumber,URLEndPoints.Constance_StudentID,URLEndPoints.Constance_StudBranchStandardId,TopFilter,NotificationPatten,PageCount,SearchFilerName,FilterID);

        Log.d(TAG, "URL : " + url);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d(TAG, response.toString());

                try {

                    Gson gson = new Gson();

                    MainNoticPatten mainNoticPatten    = gson.fromJson(response, MainNoticPatten.class);

                    if (mainNoticPatten.getStatus() == 1) {

                        noticPattenSubs = (ArrayList<NoticPattenSub>) mainNoticPatten.getData();

                        noticPattenSubs.size();

                        noticPattenParents =new ArrayList<>();

                        for(int  i =0;i< noticPattenSubs.size();i++){
                            NOTICE_PATTERN =noticPattenSubs.get(i).getnOTICEPATTERN();
                            if (noticPattenSubs.get(i).getParent().size()>0) {
                                if (NOTICE_PATTERN.equalsIgnoreCase(noticPattenSubs.get(i).getnOTICEPATTERN())) {
                                    for (int  a =0;a< noticPattenSubs.get(i).getParent().size();a++){
                                        noticPattenParents.add(noticPattenSubs.get(i).getParent().get(a));
                                    }


                                }

                            }

                        }

                        if (noticPattenSubs.size()>0){
                            new_galleryDetailsAdpter = new New_GalleryDetailsAdpter(NewRightGalleryActionActivity.this, noticPattenParents,NOTICE_PATTERN);
                            recyclerViewMainGallery.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                            recyclerViewMainGallery.setAdapter(new_galleryDetailsAdpter);

                        }

                    }


                } catch (Exception e) {
                    //      mProgressDialog.hide();
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

}
