package com.wordpro.studentproject.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.wordpro.studentproject.R;
import com.wordpro.studentproject.adapter.StudentDetailsAdpter;
import com.wordpro.studentproject.app.MyApplication;
import com.wordpro.studentproject.model.GetStudentDetailsModel;
import com.wordpro.studentproject.webConfig.URLEndPoints;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.wordpro.studentproject.activities.JobsActivity.getStudentDetailsModel;
import static com.wordpro.studentproject.activities.NavigationActivity.BASE_URL;

public class JobDetailActivity extends AppCompatActivity {

    private RecyclerView jobDetails_list;
    private StudentDetailsAdpter studentDetailsAdpter;
    ProgressDialog mProgressDialog;
    RelativeLayout lytJobDetail;
    private TextView txt_alter;
    String JobId;
    public static List<GetStudentDetailsModel.PendingJobDetail> pendingJobDetails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_detail);
        mProgressDialog=new ProgressDialog(JobDetailActivity.this);
        lytJobDetail=(RelativeLayout)findViewById(R.id.lytJobDetail);
        jobDetails_list = (RecyclerView)findViewById(R.id.recyclerView);
        txt_alter =(TextView)findViewById(R.id.txt_alter);
        JobId= getIntent().getStringExtra("jobcategory");
        if (JobId!=null &&JobId.equals("INFO")){
            txt_alter.setText(JobId);
            funTaskMAnagerGet(JobId);
        }else {
            txt_alter.setText(JobId);
            funTaskMAnagerGet(JobId);
        }



    }


    private void funTaskMAnagerGet(final String jobcategory) {
        // Displaying user information from shared preferences
        mProgressDialog.setMessage("Please wait loading!");
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.show();

        //String url=pref.getURL()+"PendingCate";
        String url = BASE_URL+ URLEndPoints.Getnoticedata_URL;
      //  Log.d(TAG, "URL : " + url);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                //Log.d(TAG, response.toString());

                try {
                    Gson gson = new Gson();
                    mProgressDialog.dismiss();

                    getStudentDetailsModel = gson.fromJson(response, GetStudentDetailsModel.class);
                    if (getStudentDetailsModel.getStatus() == 1) {
                        mProgressDialog.dismiss();

                        pendingJobDetails = (ArrayList<GetStudentDetailsModel.PendingJobDetail>) getStudentDetailsModel.getPendingJobDetails();
                        if (pendingJobDetails != null && pendingJobDetails.size() != 0) {

                            studentDetailsAdpter = new StudentDetailsAdpter(JobDetailActivity.this, (ArrayList<GetStudentDetailsModel.PendingJobDetail>) pendingJobDetails);
                            //Intent intent=new Intent(LeaveActivity.this,LeaveApplicationActivity.class);
                           /* jobDetails_list.setLayoutManager(new LinearLayoutManager(JobDetailActivity.this));
                            jobDetails_list.setAdapter(studentDetailsAdpter);*/

                            RecyclerView.LayoutManager manager = new LinearLayoutManager(JobDetailActivity.this);
                            jobDetails_list.setLayoutManager(manager);
                            jobDetails_list.setAdapter(studentDetailsAdpter);
                        } else {
                            Snackbar.make(lytJobDetail, "Record not available.", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        }

                    } else if (getStudentDetailsModel.getStatus() == 0) {
                        mProgressDialog.hide();
                        Snackbar.make(lytJobDetail, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }

                } catch (Exception e) {
                    mProgressDialog.hide();
                    Snackbar.make(lytJobDetail, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    //Log.d(TAG, "Error : " + e.getMessage());


                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mProgressDialog.hide();
               // handleVolleyError(error);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                String studCenterCode = URLEndPoints.Constance_StudentCenterCode;
                String studDepartmentNumber =URLEndPoints.Constance_StudentDepartmentNumber;
                Map<String, String> params = new HashMap<String, String>();
                params.put("PI_CENTRE_CODE", studCenterCode);
                params.put("PI_DEPARTMENT_NUMBER",studDepartmentNumber);
                params.put("PI_JOB_CATEGORY", jobcategory);
                params.put("PI_PERSON_ID", "0");
                params.put("PI_WORK_DESIG_CODE","");
                params.put("PI_BRANCH_STANDARD_GRP_ID","0");
                params.put("PI_JOB_PROFILE_ID", "0");
                params.put("PI_PERSON_TYPE","STUDENT");
                params.put("PI_NOTICE_TYPE", jobcategory);
                // params.put("PI_Interface_from", "NA");
               // Log.e(TAG, " Posting params: " + params.toString());
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
}
