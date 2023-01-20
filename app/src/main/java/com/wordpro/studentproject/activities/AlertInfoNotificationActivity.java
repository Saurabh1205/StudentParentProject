package com.wordpro.studentproject.activities;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
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
import com.wordpro.studentproject.adapter.Alert_Info_List_Adapter;
import com.wordpro.studentproject.app.MyApplication;
import com.wordpro.studentproject.helper.PrefManager;
import com.wordpro.studentproject.model.JobManagerModel;
import com.wordpro.studentproject.utils.UtilityClass;
import com.wordpro.studentproject.webConfig.URLEndPoints;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AlertInfoNotificationActivity extends AppCompatActivity {
    private RecyclerView rec_noteList;
    private RelativeLayout root;

    private ProgressBar progBar_Noti;
    private PrefManager pref;
    private String TAG = AlertInfoNotificationActivity.class.getSimpleName();
    public static String BASE_URL;
    public static JobManagerModel jobManagerModel;
    public static ArrayList<JobManagerModel.PendingJobSummeryBean> jobManagerModelArrayList;
    public Alert_Info_List_Adapter alert_info_list_adapter;
    String PassValue,TotalCount;
    String NameVa;
    private TextView  txt_toolbarName;
    private ImageView back_press;
    public Fragment fragment;
    private UtilityClass utilityClassObj;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_alert_info_notification);
        progBar_Noti = (ProgressBar)findViewById(R.id.progBar_Noti);
        root=(RelativeLayout) findViewById(R.id.root);
        rec_noteList =(RecyclerView)findViewById(R.id.rec_noteList);
        txt_toolbarName=(TextView)findViewById(R.id.txt_toolbarName);
        back_press =(ImageView)findViewById(R.id.back_press);
        pref = new PrefManager(this);
        utilityClassObj  = new  UtilityClass(this);
        //PassValue ="INFO";

        txt_toolbarName.setText(URLEndPoints.Constance_JobName + " NOTIFICATION");
        try {
            Intent intent = getIntent();

            NameVa = intent.getStringExtra("NamePassVal");


        } catch (Exception e) {
            e.printStackTrace();
            Log.e("getStringExtra_EX", e + "");
        }

        funTaskMAnager(URLEndPoints.Constance_JobName);




        back_press.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), NavigationActivity.class);
                startActivity(intent);
                finish();
                //onBackPressed();
            }
        });
    }
    private void funTaskMAnager(String jobcategory) {
        // Displaying user information from shared preferences

        utilityClassObj.startLoader(this,R.drawable.image_for_rotation);


        String URLCodeBased = pref.getURL() + URLEndPoints.PendingJon_URL;
        Log.d(TAG, "URL : " + URLCodeBased);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLCodeBased, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d(TAG, response.toString());

                try {
                    Gson gson = new Gson();

                    jobManagerModel = gson.fromJson(response, JobManagerModel.class);

                    if (jobManagerModel.getStatus() == 1) {
                        utilityClassObj.stopLoader();

                        jobManagerModelArrayList = (ArrayList<JobManagerModel.PendingJobSummeryBean>) jobManagerModel.getPendingJobSummery();
                        if (jobManagerModelArrayList != null && jobManagerModelArrayList.size() != 0) {
                           /// jobManagerAdpter = new JobManagerAdpter(this, jobManagerModelArrayList);

                            alert_info_list_adapter =new Alert_Info_List_Adapter(getApplicationContext(),jobManagerModelArrayList);
                            rec_noteList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                            rec_noteList.setAdapter(alert_info_list_adapter);


                            alert_info_list_adapter.setOnItemClickListener(new Alert_Info_List_Adapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(View itemView, int position,String name,String JobNo) {

                                    String  a= name;
                                    Toast toast = Toast.makeText(getApplicationContext(),
                                            a,
                                            Toast.LENGTH_SHORT);

                                    toast.show();


                                    URLEndPoints.Constance_JOB_CODE="";
                                    URLEndPoints.Constance_Work =name;
                                    URLEndPoints.Constance_NoOFJob =JobNo;
                                    if (!URLEndPoints.Constance_NoOFJob.equalsIgnoreCase("0")){
                                        Intent intent=new Intent(getApplicationContext(), Alert_Info_Activity.class);
                                        startActivity(intent);
                                        finish();
                                    }else {
                                        Snackbar.make(root, "Record not available.", Snackbar.LENGTH_LONG)
                                                .setAction("Action", null).show();
                                    }
                                }
                            });

//
                        } else {
                            Snackbar.make(root, "Record not available.", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        }

                    } else if (jobManagerModel.getStatus() == 0) {
                        utilityClassObj.stopLoader();
                        Snackbar.make(root, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }

                } catch (Exception e) {
                    utilityClassObj.stopLoader();
                    Snackbar.make(root, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    Log.d(TAG, "Error : " + e.getMessage());


                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                utilityClassObj.stopLoader();
                handleVolleyError(error);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<String, String>();
                params.put("PI_EMPLOYEE_ID", URLEndPoints.Constance_StudentID );
                params.put("PI_EMPLOYEE_TYPE", "S");
                params.put("PI_WORK_DESIG_CODE", URLEndPoints.Constance_stander_code);
                params.put("PI_WORK_DESG_MST_ID",  URLEndPoints.Constance_StudBranchStandardId);
                params.put("PI_DEPARTMENT_NUMBER", URLEndPoints.Constance_StudentDepartmentNumber );
                params.put("PI_CENTER_CODE", URLEndPoints.Constance_StudentCenterCode);
                params.put("PI_FILTER_VAL",  URLEndPoints.Constance_StudBranchStandardId);
                params.put("PI_SESSION_ID",  URLEndPoints.Constance_StudSessionId);
                params.put("PI_Semester_Type",  URLEndPoints.Constance_StudSemesterType);
                params.put("PI_Interface_from", "NA");
                params.put("PI_JOB_CATEGORY", jobcategory);

                Log.e(TAG, " Posting params: " + params.toString());
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
   /* private void loadFragmentMain(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container_Action, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }*/
    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container_e_ler, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }


}
