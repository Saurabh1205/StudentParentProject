package com.wordpro.studentproject.activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
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
import com.wordpro.studentproject.adapter.Alert_Info_Details_List_Adapter;
import com.wordpro.studentproject.adapter.JobDetailAdpter;
import com.wordpro.studentproject.adapter.StudentDetailsAdpter;
import com.wordpro.studentproject.app.MyApplication;
import com.wordpro.studentproject.helper.PrefManager;
import com.wordpro.studentproject.model.GetStudentDetailsModel;
import com.wordpro.studentproject.model.JobDetailsModel;
import com.wordpro.studentproject.utils.UtilityClass;
import com.wordpro.studentproject.webConfig.URLEndPoints;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Alert_Info_Activity extends AppCompatActivity {

    private static String TAG = Alert_Info_Activity.class.getSimpleName();
    private LinearLayout main_linear_view;



    String studCenterCode,studDepartmentNumber,student_id,studBranchStandardId;

    private PrefManager pref;
    ProgressBar progAttndFrgmt;
    private RelativeLayout relative_layout;
    private RecyclerView recycleListView;
    private CardView card_view;
    private TextView txt_toolbarName;
    private ImageView back_press,imageView_search,imageView_filter;
    private EditText search_noti;
    private String  JobCategory;

    public static JobDetailsModel jobDetailsModel;
    public static ArrayList<JobDetailsModel.PendingJobDetailsBean> jobDetailArrayList;
    public static JobDetailAdpter jobDetailAdpter;

    public static StudentDetailsAdpter studentDetailsAdpter;
    public Alert_Info_Details_List_Adapter alert_info_details_list_adapter;

    public static List<GetStudentDetailsModel.PendingJobDetail> pendingJobDetails;
    public static GetStudentDetailsModel getStudentDetailsModel;
    private UtilityClass utilityClassObj;
    public static Fragment fragment;
    FrameLayout container_e_ler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert__info_);


        Typeface type_faceHeading = Typeface.createFromAsset(this.getAssets(), "font/Poppins-Bold.ttf");
        Typeface type_faceContent = Typeface.createFromAsset(this.getAssets(), "font/Poppins-Regular.ttf");
        pref = new PrefManager(getApplicationContext());
        utilityClassObj =new UtilityClass(this);
        progAttndFrgmt = (ProgressBar) findViewById(R.id.progAttndFrgmt);
        relative_layout =(RelativeLayout)findViewById(R.id.relative_layout);
        back_press= (ImageView) findViewById(R.id.back_press);
        imageView_search= (ImageView) findViewById(R.id.imageView_search);
        imageView_filter= (ImageView) findViewById(R.id.imageView_filter);
        txt_toolbarName= (TextView) findViewById(R.id.txt_toolbarName);
        search_noti =(EditText)findViewById(R.id.search_noti);
        card_view =(CardView)findViewById(R.id.card_view);
        recycleListView =(RecyclerView)findViewById(R.id.recycleListView);
        txt_toolbarName.setText(URLEndPoints.Constance_Work);

        if (!URLEndPoints.Constance_NoOFJob.equalsIgnoreCase("0")){
            funTaskMAnagerGet(URLEndPoints.Constance_JobName);
        }else {
            Snackbar.make(relative_layout, "Record not available.", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }


        back_press.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // onBackPressed();
                Intent intent=new Intent(getApplicationContext(), AlertInfoNotificationActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }


    private void funTaskMAnagerGet(final String jobcategory) {
        // Displaying user information from shared preferences
        utilityClassObj.startLoader(this,R.drawable.image_for_rotation);
        String URLCodeBased = pref.getURL() + URLEndPoints.Getnoticedata_URL;
        //String url=pref.getURL()+"PendingCate";
       /// String url = BASE_URL+ URLEndPoints.Getnoticedata_URL;
          Log.d(TAG, "URL : " + URLCodeBased);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLCodeBased, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                //Log.d(TAG, response.toString());

                try {
                    Gson gson = new Gson();

                    getStudentDetailsModel = gson.fromJson(response, GetStudentDetailsModel.class);
                    if (getStudentDetailsModel.getStatus() == 1) {
                        utilityClassObj.stopLoader();
                        pendingJobDetails = (ArrayList<GetStudentDetailsModel.PendingJobDetail>) getStudentDetailsModel.getPendingJobDetails();
                        if (pendingJobDetails != null && pendingJobDetails.size() != 0) {

                            alert_info_details_list_adapter =new Alert_Info_Details_List_Adapter(getApplicationContext(),pendingJobDetails);
                            recycleListView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                            recycleListView.setAdapter(alert_info_details_list_adapter);


                            alert_info_details_list_adapter.setOnItemClickListener(new Alert_Info_Details_List_Adapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(View itemView, int position) {
                                Intent intent = new Intent(getApplicationContext(), NoticDetailsJobTypeActivity.class);
                                intent.putExtra("NoticeType", pendingJobDetails.get(position).getNOTICETYPE());
                                intent.putExtra("JobTablePKValue", pendingJobDetails.get(position).getJOBTABLEPKVALUE1());
                                startActivity(intent);
                                finish();
                            }
                        });


                        } else {
                            Snackbar.make(relative_layout, "Record not available.", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        }


                    } else if (getStudentDetailsModel.getStatus() == 0) {
                        utilityClassObj.stopLoader();
                        Snackbar.make(relative_layout, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }

                } catch (Exception e) {
                    utilityClassObj.stopLoader();
                    Snackbar.make(relative_layout, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    //Log.d(TAG, "Error : " + e.getMessage());


                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                utilityClassObj.stopLoader();
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
                params.put("PI_PERSON_ID", URLEndPoints.Constance_StudentID);
                params.put("PI_WORK_DESIG_CODE",URLEndPoints.Constance_stander_code);
                params.put("PI_BRANCH_STANDARD_GRP_ID",URLEndPoints.Constance_StudBranchStandardId);
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

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container_e_ler, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
