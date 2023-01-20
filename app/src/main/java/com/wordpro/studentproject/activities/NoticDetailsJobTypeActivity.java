package com.wordpro.studentproject.activities;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
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
import com.wordpro.studentproject.adapter.Alert_Info_Details_List_Adapter;
import com.wordpro.studentproject.adapter.Info_Jobtype_Adapter;
import com.wordpro.studentproject.app.MyApplication;
import com.wordpro.studentproject.helper.PrefManager;
import com.wordpro.studentproject.model.NewGetNoticAttachment;
import com.wordpro.studentproject.model.NewGetNoticDetails;
import com.wordpro.studentproject.model.PendingJobType;
import com.wordpro.studentproject.utils.UtilityClass;
import com.wordpro.studentproject.webConfig.URLEndPoints;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import static com.wordpro.studentproject.activities.NavigationActivity.BASE_URL;

public class NoticDetailsJobTypeActivity extends AppCompatActivity {

    public static String TAG = NoticDetailsJobTypeActivity.class.getSimpleName();
    private RelativeLayout  layout_errorShow;
    private ImageView back_press;

    private TextView txt_toolbarName,txt_notice_type,tv_notice_header,txt_Des;
    private PrefManager pref;
    private RecyclerView recycle_view;
    private CardView card_view_details;

    private UtilityClass utilityClassObj;

    public static PendingJobType pendingJobType;
    public static ArrayList<NewGetNoticDetails> getNoticDetailsArrayList;
    private ArrayList<NewGetNoticAttachment> getAttachment;

    Info_Jobtype_Adapter info_jobtype_adapter;

    String JobCode,JobTablePKValue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notic_details_job_type);
        init();
    }
    private void init(){

        pref = new PrefManager(this);
        utilityClassObj  = new  UtilityClass(this);
        layout_errorShow = (RelativeLayout) findViewById(R.id.layout_errorShow);
        card_view_details =(CardView)findViewById(R.id.card_view_details);
        recycle_view =(RecyclerView)findViewById(R.id.recycle_view);
        txt_toolbarName = (TextView) findViewById(R.id.txt_toolbarName);
        txt_notice_type = (TextView) findViewById(R.id.txt_notice_type);
        tv_notice_header = (TextView) findViewById(R.id.tv_notice_header);
        txt_Des = (TextView) findViewById(R.id.txt_Des);
        back_press = (ImageView) findViewById(R.id.back_press);
        JobCode = getIntent().getStringExtra("NoticeType");
        JobTablePKValue = getIntent().getStringExtra("JobTablePKValue");
        txt_toolbarName.setText("Notice Details");

        back_press.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Alert_Info_Activity.class);
                startActivity(intent);
                finish();
            }
        });


        GetNoticeDtls(JobCode,JobTablePKValue);

    }

    private void GetNoticeDtls(String JobCode,String JobTablePkValue) {

        utilityClassObj.startLoader(this,R.drawable.image_for_rotation);
        String url= BASE_URL + URLEndPoints.POSTGetNotice_Details;
        Log.d(TAG, url);
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{

                    Gson gson=new Gson();
                    pendingJobType= gson.fromJson(response, PendingJobType.class);
                    if(pendingJobType.getStatus()==1){
                        if (pendingJobType.getPendingJobDetails().size()!=0) {
                            utilityClassObj.stopLoader();
                            card_view_details.setVisibility(View.VISIBLE);
                            txt_notice_type.setText("Notice Type : " + pendingJobType.getPendingJobDetails().get(0).getnOTICETYPE());
                            tv_notice_header.setText("Notice Header : " + pendingJobType.getPendingJobDetails().get(0).getnOTICEHEADER());
                            txt_Des.setText(pendingJobType.getPendingJobDetails().get(0).getnOTICEDESCRIPTION());


                            info_jobtype_adapter = new Info_Jobtype_Adapter(getApplicationContext(), (ArrayList<NewGetNoticAttachment>) pendingJobType.getPendingJobDetails().get(0).getAttachment());
                            recycle_view.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                            recycle_view.setAdapter(info_jobtype_adapter);

                            info_jobtype_adapter.setOnItemClickListener(new Info_Jobtype_Adapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(View itemView, int position) {
                                   String test = pendingJobType.getPendingJobDetails().get(0).getAttachment().get(position).getPath();


                                    String urlPath ="";
                                   /* String q,w;
                                    String str1 = test.substring(test.lastIndexOf("\\") + 1);
                                    String[] filenameNextension1 = str1.split(Pattern.quote("."));
                                    q = filenameNextension1[0];
                                    w = filenameNextension1[1];
                                    String a =q+w;

                                    String path ="http://"+test;*/

                                    if(pendingJobType.getPendingJobDetails().get(0).getAttachment().get(position).getpATHTYPE().equalsIgnoreCase("LOCAL")){
                                        String comUrl = BASE_URL;
                                        String[] s = comUrl.split(Pattern.quote("api"));
                                        String downloadURL = s[0];
                                        urlPath= downloadURL+test;
                                    }else {
                                        urlPath= "http://"+test;
                                    }
                                    funDownloadFile(urlPath);
                                }
                            });


                        }else{
                            card_view_details.setVisibility(View.GONE);
                            utilityClassObj.stopLoader();
                            Snackbar.make(layout_errorShow, "Record not available.", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        }

                    }else{
                        card_view_details.setVisibility(View.GONE);
                        utilityClassObj.stopLoader();
                        Snackbar.make(layout_errorShow, "Record not available.", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }


                }catch (Exception e) {
                    card_view_details.setVisibility(View.GONE);
                    utilityClassObj.stopLoader();
                    Snackbar.make(layout_errorShow, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    Log.d(TAG, "Error : " + e.getMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                utilityClassObj.stopLoader();
                Snackbar.make(layout_errorShow, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                // Log.d(TAG, "Error : " + e.getMessage());
                card_view_details.setVisibility(View.GONE);
                handleVolleyError(error);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("JOB_CODE", JobCode);
                params.put("JOB_TABLE_PK_VALUE1", JobTablePkValue);
                params.put("NO_OF_JOBS","null");
                params.put("HIDDEN1", "null");
                params.put("JOB_DESC","null");
                params.put("NOTICE_DESCRIPTION","null");
                params.put("JOB_GEN_WRK_DESIG","null");
                params.put("PENDING_JOB_SRNO","null");
                params.put("WORK_REPORTING_DTL_ID","null");
                params.put("APPR_PATTERN_APPLI_FOR", "null");
                params.put("NOTICE_TYPE","null");
                params.put("JOB_GEN_PERSON_TYPE","null");
                params.put("NOTC_MASTER_ID","null");
                Log.e(TAG, "InActive Posting params: " + params.toString());
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                60000,
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
//private void funDownloadFile(String originalfilename,String fileformat) {
    private void funDownloadFile(String originalfilename) {

        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(originalfilename));
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.HONEYCOMB){
            request.allowScanningByMediaScanner();
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);

        }
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, originalfilename);
        DownloadManager downloadManager =(DownloadManager)this.getSystemService(Context.DOWNLOAD_SERVICE);
       // request.setMimeType("application/"+fileformat);
        request.setMimeType("application/pdf");
        request.allowScanningByMediaScanner();
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);
        downloadManager.enqueue(request);
        Snackbar.make(layout_errorShow, "File downloaded succesfully", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();

    }
}
