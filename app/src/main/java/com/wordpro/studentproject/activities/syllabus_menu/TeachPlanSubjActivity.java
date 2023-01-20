package com.wordpro.studentproject.activities.syllabus_menu;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
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
import com.wordpro.studentproject.activities.NavigationActivity;
import com.wordpro.studentproject.adapter.TeachPlanAdapter;
import com.wordpro.studentproject.adapter.TeachPlanSubjAdapter;
import com.wordpro.studentproject.app.MyApplication;
import com.wordpro.studentproject.helper.PrefManager;
import com.wordpro.studentproject.model.TeachPlanDataModel;
import com.wordpro.studentproject.webConfig.URLEndPoints;

import java.lang.reflect.Method;
import java.util.ArrayList;

import static com.wordpro.studentproject.activities.NavigationActivity.studAcadSessName;
import static com.wordpro.studentproject.activities.NavigationActivity.studBranchStandDesc;
import static com.wordpro.studentproject.activities.NavigationActivity.studBranchStandardId;
import static com.wordpro.studentproject.activities.NavigationActivity.studCenterCode;
import static com.wordpro.studentproject.activities.NavigationActivity.studMainSemName;
import static com.wordpro.studentproject.activities.NavigationActivity.studSemesterType;
import static com.wordpro.studentproject.activities.NavigationActivity.studSessionId;
import static com.wordpro.studentproject.activities.NavigationActivity.studentName;
import static com.wordpro.studentproject.fragment.FragSyllabus.teachPlanSubjAdapter;
import static com.wordpro.studentproject.fragment.FragSyllabus.teachPlanSubjArrayList;
import static com.wordpro.studentproject.network.Network.isNetworkAvailable;

public class TeachPlanSubjActivity extends Activity {

    private static String TAG = TeachPlanSubjActivity.class.getSimpleName();

    RelativeLayout lytTeachPlanData;
    TextView txtStudName;
    TextView txtYear;
    TextView txtSem;
    TextView txtSection;
    RecyclerView recyVwSubject;
    ProgressBar prgBarSyllbus;
    PrefManager pref;
    public static TeachPlanAdapter teachPlanAdapter;

    //Teaching Plan Data
    public static TeachPlanDataModel teachPlanDataModel;
    public static ArrayList<TeachPlanDataModel.DataBean> teachPlanDataArrayList;

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
        } else {
            setContentView(R.layout.activity_teach_plan_subj);

            pref = new PrefManager(TeachPlanSubjActivity.this);
            Typeface typefaceHeading = Typeface.createFromAsset(getAssets(), "font/Poppins-BoldItalic.otf");
            Typeface typefaceContent = Typeface.createFromAsset(getAssets(), "font/Poppins-Medium.otf");

            lytTeachPlanData=(RelativeLayout)findViewById(R.id.lytTeachPlanData);
            txtStudName=(TextView)findViewById(R.id.txtStudName);
            txtStudName.setTypeface(typefaceContent);
            txtStudName.setText(studentName);
            txtYear=(TextView)findViewById(R.id.txtYear);
            txtYear.setText(studAcadSessName);
            txtYear.setTypeface(typefaceContent);
            txtSem=(TextView)findViewById(R.id.txtSem);
            txtSem.setTypeface(typefaceContent);
            txtSem.setText(studMainSemName);
            txtSection=(TextView)findViewById(R.id.txtSection);
            txtSection.setTypeface(typefaceContent);
            txtSection.setText(studBranchStandDesc);
            prgBarSyllbus=(ProgressBar)findViewById(R.id.prgBarSyllbus);

            recyVwSubject=(RecyclerView) findViewById(R.id.recyVwSubject);
            recyVwSubject.setLayoutManager(new LinearLayoutManager(TeachPlanSubjActivity.this));
            recyVwSubject.setAdapter(teachPlanSubjAdapter);
            if(teachPlanSubjAdapter!=null){

                teachPlanSubjAdapter.setOnItemClickListener(new TeachPlanSubjAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View itemView, int position) {

                        if (!isNetworkAvailable(TeachPlanSubjActivity.this)) {
                            // Create an Alert Dialog
                            AlertDialog.Builder builder = new AlertDialog.Builder(TeachPlanSubjActivity.this);
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
                            String EMPLOYEE_ID=teachPlanSubjArrayList.get(position).getEmployee_Id();
                            String Subject_Detail_Id=teachPlanSubjArrayList.get(position).getSUBJECT_DETAIL_ID();
                            String subjectName=teachPlanSubjArrayList.get(position).getSUBJECT_DESCRIPTION();
                            String profName=teachPlanSubjArrayList.get(position).getPROF_EMP_FN_MN_SHORT();

                            getTeachPlanData(EMPLOYEE_ID,Subject_Detail_Id,subjectName,profName);
                        }


                    }
                });
            }
        }
    }

    private void getTeachPlanData(String employee_id, String subject_detail_id, final String subjectName, final String profName) {
        prgBarSyllbus.setVisibility(View.VISIBLE);
        String url=pref.getURL()+ URLEndPoints.getTeachingPlanDataURL(studSessionId, studSemesterType, employee_id, subject_detail_id, studBranchStandardId, studCenterCode);
        Log.d(TAG, "Subject and faculty List : " + url);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, response.toString());
                try {
                    Gson gson = new Gson();
                    teachPlanDataModel=gson.fromJson(response,TeachPlanDataModel.class);
                    if(teachPlanDataModel.getStatus()==1){
                        teachPlanDataArrayList=(ArrayList<TeachPlanDataModel.DataBean>)teachPlanDataModel.getData();
                        if(teachPlanDataArrayList!=null){
                            teachPlanAdapter=new TeachPlanAdapter(TeachPlanSubjActivity.this,teachPlanDataArrayList);
                            if(teachPlanAdapter!=null){
                                Intent intent=new Intent(TeachPlanSubjActivity.this,TeachingPlanActivity.class);
                                intent.putExtra("subjectName",subjectName);
                                intent.putExtra("profName",profName);
                                startActivity(intent);
                                finish();
                            }
                        }else{
                            Snackbar.make(lytTeachPlanData, "Record not available.", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        }

                    }else if(teachPlanDataModel.getStatus()==0){
                        Snackbar.make(lytTeachPlanData, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();

                    }

                    prgBarSyllbus.setVisibility(View.GONE);

                } catch (Exception e) {

                    Snackbar.make(lytTeachPlanData, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                    Log.d(TAG, "Error : " + e.getMessage());

                    prgBarSyllbus.setVisibility(View.GONE);

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                handleVolleyError(error);
            }
        });
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
        Intent intent=new Intent(TeachPlanSubjActivity.this, NavigationActivity.class);
        intent.putExtra("activity","TeachPlanSubjActivity");
        startActivity(intent);
        finish();
    }
}
