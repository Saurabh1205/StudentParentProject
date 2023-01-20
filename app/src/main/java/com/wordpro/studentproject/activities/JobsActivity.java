package com.wordpro.studentproject.activities;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
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
import com.wordpro.studentproject.adapter.JobDetailAdpter;
import com.wordpro.studentproject.adapter.JobManagerAdpter;
import com.wordpro.studentproject.adapter.StudentDetailsAdpter;
import com.wordpro.studentproject.app.MyApplication;
import com.wordpro.studentproject.model.GetStudentDetailsModel;
import com.wordpro.studentproject.model.JobDetailsModel;
import com.wordpro.studentproject.webConfig.URLEndPoints;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.wordpro.studentproject.activities.NavigationActivity.BASE_URL;
import static com.wordpro.studentproject.activities.NavigationActivity.jobManagerAdpter;
import static com.wordpro.studentproject.activities.NavigationActivity.jobManagerModelArrayList;
import static com.wordpro.studentproject.activities.NavigationActivity.studAcadSessName;
import static com.wordpro.studentproject.activities.NavigationActivity.studBranchStandDesc;
import static com.wordpro.studentproject.activities.NavigationActivity.studMainSemName;
import static com.wordpro.studentproject.activities.NavigationActivity.studentName;
import static com.wordpro.studentproject.network.Network.isNetworkAvailable;

public class JobsActivity extends AppCompatActivity {

    public String TAG=JobsActivity.class.getSimpleName();
    TextView txtStudName, txtYear, txtSem, txtSection,txtJob;
    ProgressDialog mProgressDialog;
    RelativeLayout lytJobDetail;
    String activityFrom,jobcategory;
    RecyclerView jobRecyVw;

    //JobDetailsModel
    public static JobDetailsModel jobDetailsModel;
    public static ArrayList<JobDetailsModel.PendingJobDetailsBean> jobDetailArrayList;
    public static JobDetailAdpter jobDetailAdpter;

    public static StudentDetailsAdpter studentDetailsAdpter;

    public static List<GetStudentDetailsModel.PendingJobDetail> pendingJobDetails;
    public static GetStudentDetailsModel getStudentDetailsModel;
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
        }
        else {

            setContentView(R.layout.activity_jobs);
            Typeface typefaceHeading = Typeface.createFromAsset(getAssets(), "font/Poppins-BoldItalic.otf");
            Typeface typefaceContent = Typeface.createFromAsset(getAssets(), "font/Poppins-Medium.otf");

            mProgressDialog=new ProgressDialog(JobsActivity.this);
            txtJob=(TextView)findViewById(R.id.txtJob);
            txtJob.setTypeface(typefaceHeading);
            txtStudName = (TextView) findViewById(R.id.txtStudName);
            txtStudName.setTypeface(typefaceContent);
            txtStudName.setText(studentName.toUpperCase());
            txtYear = (TextView) findViewById(R.id.txtYear);
            txtYear.setTypeface(typefaceContent);
            txtYear.setText(studAcadSessName.toUpperCase());
            txtSem = (TextView) findViewById(R.id.txtSem);
            txtSem.setTypeface(typefaceContent);
            txtSem.setText(studMainSemName.toUpperCase());
            txtSection = (TextView) findViewById(R.id.txtSection);
            txtSection.setTypeface(typefaceContent);
            txtSection.setText(studBranchStandDesc.toUpperCase());
            lytJobDetail=(RelativeLayout)findViewById(R.id.lytJobDetail);

            Intent intent = getIntent();
            activityFrom = intent.getStringExtra("activityFrom");
            jobcategory=intent.getStringExtra("jobcategory");

            jobRecyVw=(RecyclerView)findViewById(R.id.jobRecyVw);
            // use a linear layout manager
            jobRecyVw.setLayoutManager(new LinearLayoutManager(JobsActivity.this));
            jobRecyVw.addItemDecoration(new DividerItemDecoration(JobsActivity.this, LinearLayoutManager.VERTICAL));
            jobRecyVw.setAdapter(jobManagerAdpter);


            if(activityFrom.equalsIgnoreCase("NavigationActivity")){
                if(jobManagerAdpter!=null){

                    jobManagerAdpter.setOnItemClickListener(new JobManagerAdpter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View itemView, int position) {
                            if (!isNetworkAvailable(JobsActivity.this)) {
                                // Create an Alert Dialog
                                AlertDialog.Builder builder = new AlertDialog.Builder(JobsActivity.this);
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
                            }else {

                                String JOB_CODE=jobManagerModelArrayList.get(position).getJOB_CODE();
                                String PENDING_JOB_SRNO=jobManagerModelArrayList.get(position).getPENDING_JOB_SRNO();
                                String WRKDESIG_CENTRE_CODE ="";
                                if(jobManagerModelArrayList.get(position).getWRKDESIG_CENTRE_CODE()!=""){
                                    WRKDESIG_CENTRE_CODE=jobManagerModelArrayList.get(position).getWRKDESIG_CENTRE_CODE();
                                }else{
                                   WRKDESIG_CENTRE_CODE="";
                                }

                                String Spc_PERSON_ID=jobManagerModelArrayList.get(position).getSpc_PERSON_ID();
                                if(Spc_PERSON_ID.isEmpty() || Spc_PERSON_ID.equalsIgnoreCase("") || Spc_PERSON_ID.equalsIgnoreCase(null)){
                                    Spc_PERSON_ID="0";
                                }
                                String WORK_CODE=jobManagerModelArrayList.get(position).getWORK_CODE();
                                String StudentId =URLEndPoints.Constance_StudentID;
                                if (JOB_CODE.equals("INFO")){


                                    funTaskMAnagerGetApi(JOB_CODE,StudentId);
                                }else if (JOB_CODE.equals("ALERT")){
                                    funTaskMAnagerGetApi(JOB_CODE,StudentId);
                                }else {
                                    funJobTaskDetail(JOB_CODE,WRKDESIG_CENTRE_CODE,Spc_PERSON_ID,WORK_CODE);
                                }

                            }
                        }
                    });
                }

            }else {
                if(jobManagerAdpter!=null){

                    jobManagerAdpter.setOnItemClickListener(new JobManagerAdpter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View itemView, int position) {
                            if (!isNetworkAvailable(JobsActivity.this)) {
                                // Create an Alert Dialog
                                AlertDialog.Builder builder = new AlertDialog.Builder(JobsActivity.this);
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
                            }else {

                                String JOB_CODE=jobManagerModelArrayList.get(position).getJOB_CODE();
                                String PENDING_JOB_SRNO=jobManagerModelArrayList.get(position).getPENDING_JOB_SRNO();
                                String WRKDESIG_CENTRE_CODE ="";
                                if(jobManagerModelArrayList.get(position).getWRKDESIG_CENTRE_CODE()!=""){
                                    WRKDESIG_CENTRE_CODE=jobManagerModelArrayList.get(position).getWRKDESIG_CENTRE_CODE();
                                }else{
                                    WRKDESIG_CENTRE_CODE="";
                                }

                                String Spc_PERSON_ID=jobManagerModelArrayList.get(position).getSpc_PERSON_ID();
                                if(Spc_PERSON_ID.isEmpty() || Spc_PERSON_ID.equalsIgnoreCase("") || Spc_PERSON_ID.equalsIgnoreCase(null)){
                                    Spc_PERSON_ID="0";
                                }
                                String WORK_CODE=jobManagerModelArrayList.get(position).getWORK_CODE();
                                String StudentId =URLEndPoints.Constance_StudentID;
                                if (JOB_CODE.equals("INFO")){


                                    funTaskMAnagerGetApi(JOB_CODE,StudentId);
                                }else if (JOB_CODE.equals("ALERT")){
                                    funTaskMAnagerGetApi(JOB_CODE,StudentId);
                                }else {
                                    funJobTaskDetail(JOB_CODE,WRKDESIG_CENTRE_CODE,Spc_PERSON_ID,WORK_CODE);
                                }

                            }
                        }
                    });
                }
            }

        }
    }

    private void funTaskMAnagerGetApi(final String jobcategory,final  String student_id) {
        // Displaying user information from shared preferences
        mProgressDialog.setMessage("Please wait loading!");
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.show();

        //String url=pref.getURL()+"PendingCate";
        String url = BASE_URL+ URLEndPoints.GetStudentDetails_URL;
        Log.d(TAG, "URL : " + url);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d(TAG, response.toString());

                try {
                    Gson gson = new Gson();
                    mProgressDialog.dismiss();

                    getStudentDetailsModel = gson.fromJson(response, GetStudentDetailsModel.class);

                    if (getStudentDetailsModel.getStatus() == 1) {
                        mProgressDialog.dismiss();
                        pendingJobDetails = (ArrayList<GetStudentDetailsModel.PendingJobDetail>) getStudentDetailsModel.getPendingJobDetails();
                        // pendingJobDetails = (ArrayList<GetStudentDetailsModel.P>) jobManagerModel.getPendingJobSummery();
                        if (pendingJobDetails != null && pendingJobDetails.size() != 0) {
                            //jobManagerAdpter = new JobManagerAdpter(NavigationActivity.this, pendingJobDetails);

                           // funTaskMAnagerGet("INFO");


                            Intent intent=new Intent(JobsActivity.this, JobDetailActivity.class);
                            intent.putExtra("jobcategory",jobcategory);

                            startActivity(intent);
                            finish();

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
                    Log.d(TAG, "Error : " + e.getMessage());


                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mProgressDialog.hide();
                handleVolleyError(error);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<String, String>();
                params.put("Usertype", "STUDENT");
                params.put("studentid", student_id);

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


    private void funJobTaskDetail(final String JOB_CODE, final String WRKDESIG_CENTRE_CODE, final String spc_PERSON_ID, String WORK_CODE) {

        mProgressDialog.setMessage("Please wait loading!");
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.show();


        String url = BASE_URL + URLEndPoints.PendingJonDtls_URL;
        Log.d(TAG, "URL : " + url);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d(TAG, response.toString());

                try {
                    Gson gson=new Gson();
                    mProgressDialog.dismiss();

                    jobDetailsModel=gson.fromJson(response,JobDetailsModel.class);

                    if(jobDetailsModel.getStatus()==1){
                        mProgressDialog.dismiss();

                        jobDetailArrayList=(ArrayList<JobDetailsModel.PendingJobDetailsBean>) jobDetailsModel.getPendingJobDetails();
                        if(jobDetailArrayList!=null && jobDetailArrayList.size()!=0){

                            jobDetailAdpter=new JobDetailAdpter(JobsActivity.this,jobDetailArrayList);
                            Intent intent=new Intent(JobsActivity.this, JobDetailActivity.class);
                            intent.putExtra("jobcategory",jobcategory);

                            startActivity(intent);
                            finish();

                        }else{
                            Snackbar.make(lytJobDetail, "Record not available.", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        }

                    }else if(jobDetailsModel.getStatus()==0){
                        mProgressDialog.hide();
                        Snackbar.make(lytJobDetail, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }

                } catch (Exception e) {
                    mProgressDialog.hide();
                    Snackbar.make(lytJobDetail, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    Log.d(TAG, "Error : " + e.getMessage());

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mProgressDialog.hide();
                handleVolleyError(error);
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();

                params.put("PI_PEND_JOB_CODE", JOB_CODE);
                params.put("PI_WORK_DESIG_CODE","NA");
                params.put("PI_WRKDESIG_CENTRE_CODE", WRKDESIG_CENTRE_CODE);
                params.put("PI_SPC_PERSON_ID", spc_PERSON_ID);
                params.put("PI_WORK_CODE", JOB_CODE);

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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(JobsActivity.this,NavigationActivity.class));
    }
}
