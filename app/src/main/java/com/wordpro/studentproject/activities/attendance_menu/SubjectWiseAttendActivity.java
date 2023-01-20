package com.wordpro.studentproject.activities.attendance_menu;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
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
import com.wordpro.studentproject.adapter.DayWiseAttendApdater;
import com.wordpro.studentproject.adapter.IndicatorAdapter;
import com.wordpro.studentproject.adapter.SubjWiseAttendAdapter;
import com.wordpro.studentproject.adapter.SubjectwiseFacTypeAdapter;
import com.wordpro.studentproject.adapter.TeachPlanSubjAdapter;
import com.wordpro.studentproject.app.MyApplication;
import com.wordpro.studentproject.model.DaywiseAttndModel;
import com.wordpro.studentproject.webConfig.URLEndPoints;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.wordpro.studentproject.activities.NavigationActivity.fragAttendance;
import static com.wordpro.studentproject.activities.NavigationActivity.studAcadSessName;
import static com.wordpro.studentproject.activities.NavigationActivity.studBranchStandDesc;
import static com.wordpro.studentproject.activities.NavigationActivity.studBranchStandardId;
import static com.wordpro.studentproject.activities.NavigationActivity.studCenterCode;
import static com.wordpro.studentproject.activities.NavigationActivity.studMainSemName;
import static com.wordpro.studentproject.activities.NavigationActivity.studSemesterType;
import static com.wordpro.studentproject.activities.NavigationActivity.studSessionId;
import static com.wordpro.studentproject.activities.NavigationActivity.studentName;
import static com.wordpro.studentproject.activities.NavigationActivity.student_id;
import static com.wordpro.studentproject.fragment.FragAttendance.subjectAdapter;
import static com.wordpro.studentproject.fragment.FragAttendance.subjectArrayList;
import static com.wordpro.studentproject.fragment.FragAttendance.subjectFacTypeArrayList;
import static com.wordpro.studentproject.fragment.FragAttendance.subjectwiseFacTypeAdapter;
import static com.wordpro.studentproject.network.Network.isNetworkAvailable;

public class SubjectWiseAttendActivity extends Activity {

    private static String TAG = SubjectWiseAttendActivity.class.getSimpleName();
    RelativeLayout lytSubjwiseData;
    TextView txtStudName,txtSubjAttnd;
    TextView txtYear;
    TextView txtSem;
    TextView txtSection;
    RecyclerView recyVwSubjectList;
    ProgressBar prgBarSubject;
    String PERIOD_START_DATE, PERIOD_END_DATE;
    Typeface typefaceHeading,typefaceContent;


    public static DaywiseAttndModel subjAttndModel;
    public static ArrayList<DaywiseAttndModel.DataBean> subjectwiseAttndList;
    public static SubjWiseAttendAdapter subjWiseAttendAdapter;

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
            setContentView(R.layout.activity_subject_wise_attend);
            fragAttendance.dismiss();
            Intent intent = getIntent();
            Bundle extras = intent.getExtras();
            PERIOD_START_DATE = extras.getString("PERIOD_START_DATE");
            PERIOD_END_DATE = extras.getString("PERIOD_END_DATE");

            typefaceHeading = Typeface.createFromAsset(getAssets(), "font/Poppins-BoldItalic.otf");
            typefaceContent = Typeface.createFromAsset(getAssets(), "font/Poppins-Medium.otf");

            lytSubjwiseData = (RelativeLayout) findViewById(R.id.lytSubjwiseData);
            txtSubjAttnd=(TextView)findViewById(R.id.txtSubjAttnd);
            txtSubjAttnd.setTypeface(typefaceHeading);
            txtStudName = (TextView) findViewById(R.id.txtStudName);
            txtStudName.setTypeface(typefaceContent);
            txtStudName.setText(studentName);
            txtYear = (TextView) findViewById(R.id.txtYear);
            txtYear.setTypeface(typefaceContent);
            txtYear.setText(studAcadSessName);
            txtSem = (TextView) findViewById(R.id.txtSem);
            txtSem.setTypeface(typefaceContent);
            txtSem.setText(studMainSemName);
            txtSection = (TextView) findViewById(R.id.txtSection);
            txtSection.setTypeface(typefaceContent);
            txtSection.setText(studBranchStandDesc);
            prgBarSubject = (ProgressBar) findViewById(R.id.prgBarSubject);

            recyVwSubjectList = (RecyclerView) findViewById(R.id.recyVwSubjectList);
            recyVwSubjectList.setLayoutManager(new LinearLayoutManager(SubjectWiseAttendActivity.this));
            recyVwSubjectList.setAdapter(subjectwiseFacTypeAdapter);

            if (subjectwiseFacTypeAdapter != null) {

                subjectwiseFacTypeAdapter.setOnItemClickListener(new SubjectwiseFacTypeAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View itemView, int position) {
                        if (!isNetworkAvailable(SubjectWiseAttendActivity.this)) {
                            // Create an Alert Dialog
                            AlertDialog.Builder builder = new AlertDialog.Builder(SubjectWiseAttendActivity.this);
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
                            String subject_Detail_Id = subjectFacTypeArrayList.get(position).getSUBJECT_DETAIL_ID();
                            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                            Date date = new Date();
                            String currentDate = formatter.format(date);
                            String[] dt = currentDate.split(" ");
                            currentDate = dt[0];
                            String subjectName = subjectFacTypeArrayList.get(position).getSUBJECT_DESCRIPTION();
                            String profName = subjectFacTypeArrayList.get(position).getPROF_EMP_FN_MN_SHORT();
                            getSubjWiseAttend(PERIOD_START_DATE, currentDate, subject_Detail_Id, subjectName, profName);
                        }


                    }
                });
            }
        }
    }

    private void getSubjWiseAttend(final String period_start_date, final String currentDate, final String subject_detail_id, final String subjectName, final String profName) {
        prgBarSubject.setVisibility(View.VISIBLE);

        String url = NavigationActivity.BASE_URL + URLEndPoints.GETDAYWISEATTEENDACE_URL;
        Log.d(TAG, "Day-wise SelfAttendance : " + url);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, response.toString());
                Gson gson = new Gson();

                subjAttndModel = gson.fromJson(response, DaywiseAttndModel.class);
                if (subjAttndModel.getStatus() == 1) {
                    subjectwiseAttndList = (ArrayList<DaywiseAttndModel.DataBean>) subjAttndModel.getData();

                    if (subjectwiseAttndList != null && subjectwiseAttndList.size()> 0) {

                        subjWiseAttendAdapter = new SubjWiseAttendAdapter(SubjectWiseAttendActivity.this, subjectwiseAttndList);
                        Intent intent = new Intent(SubjectWiseAttendActivity.this, SubjectAttendDetailActivity.class);
                        intent.putExtra("subjectName", subjectName);
                        intent.putExtra("profName", profName);
                        intent.putExtra("PERIOD_START_DATE", PERIOD_START_DATE);
                        intent.putExtra("PERIOD_END_DATE", PERIOD_END_DATE);
                        startActivity(intent);
                        finish();

                    } else {
                        Snackbar.make(lytSubjwiseData, "Record not available.", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }
                } else if (subjAttndModel.getStatus() == 0) {
                    Snackbar.make(lytSubjwiseData, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
                prgBarSubject.setVisibility(View.GONE);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                handleVolleyError(error);

                prgBarSubject.setVisibility(View.GONE);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("_centercode", studCenterCode);
                params.put("PI_SESSION_ID", studSessionId);
                params.put("P_BRANCH_STANDARD_ID", studBranchStandardId);
                params.put("AcadSemisterType", studSemesterType);
                params.put("p_Subject_Detail_Id", subject_detail_id);
                params.put("_iPI_APPLICABLE_NUMBER", "0");
                params.put("P_STUDENT_ID", student_id);
                params.put("start_DATE", period_start_date);
                params.put("end_DATE", currentDate);
                params.put("P_REPORT_PATTERN", "ATTENDANCE_DETAILS");

                Log.e(TAG, "Posting params: " + params.toString());

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
        Intent intent = new Intent(SubjectWiseAttendActivity.this, NavigationActivity.class);
        intent.putExtra("activity","SubjectWiseAttendActivity");
        startActivity(intent);
        finish();
    }
}
