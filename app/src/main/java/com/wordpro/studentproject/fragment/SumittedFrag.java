package com.wordpro.studentproject.fragment;

import android.app.AlertDialog;
import android.app.DownloadManager;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
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
import com.wordpro.studentproject.adapter.AssignmentAdapter;
import com.wordpro.studentproject.adapter.IndicatorAdapter;
import com.wordpro.studentproject.app.MyApplication;
import com.wordpro.studentproject.model.AssignmentModel;
import com.wordpro.studentproject.model.DownloadURLModel;
import com.wordpro.studentproject.webConfig.URLEndPoints;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import static com.android.volley.VolleyLog.TAG;
import static com.github.mikephil.charting.charts.Chart.LOG_TAG;
import static com.wordpro.studentproject.activities.NavigationActivity.BASE_URL;
import static com.wordpro.studentproject.activities.NavigationActivity.studAcadSessName;
import static com.wordpro.studentproject.activities.NavigationActivity.studBranchStandDesc;
import static com.wordpro.studentproject.activities.NavigationActivity.studBranchStandardId;
import static com.wordpro.studentproject.activities.NavigationActivity.studCenterCode;
import static com.wordpro.studentproject.activities.NavigationActivity.studMainSemName;
import static com.wordpro.studentproject.activities.NavigationActivity.studSemesterType;
import static com.wordpro.studentproject.activities.NavigationActivity.studSessionId;
import static com.wordpro.studentproject.activities.NavigationActivity.studentName;
import static com.wordpro.studentproject.activities.NavigationActivity.student_id;
import static com.wordpro.studentproject.fragment.FragE_Learning.studAssignDtlsArrayList;
import static com.wordpro.studentproject.fragment.PublishFragment.mProgressDialog;
import static com.wordpro.studentproject.network.Network.isNetworkAvailable;

/**
 * Created by wccs1980 on 19-06-2018.
 */

public class SumittedFrag extends Fragment{

    Context context;
    public static ProgressDialog mProgressDialog;
    ArrayList<AssignmentModel.StudAssignDtlsBean> submittedAssgnList;
    public AssignmentAdapter submittedAssignmentAdapter;
    IndicatorAdapter indicatorAdapter;
    List<String> statusList;
    RecyclerView recyViewIndicators;
    public static String TAG=SumittedFrag.class.getSimpleName();
    RelativeLayout submittedFrgLyt;
    AssignmentModel assignmentModel;
    ArrayList<AssignmentModel.StudAssignDtlsBean> assignSubmtAssignArrayList;
    RecyclerView recyVwSubmittedAssgn;
    ImageButton btnRefresh;


    public SumittedFrag() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        funGetAssgnDtl();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.list_submit_assign, container, false);

        Typeface typefaceHeading = Typeface.createFromAsset(getActivity().getAssets(), "font/Poppins-BoldItalic.otf");
        Typeface typefaceContent = Typeface.createFromAsset(getActivity().getAssets(), "font/Poppins-Medium.otf");

        mProgressDialog=new ProgressDialog(getActivity());
        TextView txtStudName = (TextView) rootView.findViewById(R.id.txtStudName);
        txtStudName.setTypeface(typefaceContent);
        txtStudName.setText(studentName.toUpperCase());

        TextView txtYear = (TextView) rootView.findViewById(R.id.txtYear);
        txtYear.setTypeface(typefaceContent);
        txtYear.setText(studAcadSessName.toUpperCase());

        TextView txtSem = (TextView) rootView.findViewById(R.id.txtSem);
        txtSem.setTypeface(typefaceContent);
        txtSem.setText(studMainSemName.toUpperCase());

        TextView txtSection = (TextView) rootView.findViewById(R.id.txtSection);
        txtSection.setTypeface(typefaceContent);
        txtSection.setText(studBranchStandDesc.toUpperCase());

        recyVwSubmittedAssgn = (RecyclerView) rootView.findViewById(R.id.recyVwSubmittedAssgn);
        recyVwSubmittedAssgn.setLayoutManager(new LinearLayoutManager(getActivity()));

        submittedFrgLyt=(RelativeLayout)rootView.findViewById(R.id.submittedFrgLyt);
        btnRefresh=(ImageButton)rootView.findViewById(R.id.btnRefresh);


        statusList = new ArrayList<>();
        statusList.add("SUBMITTED");
        statusList.add("APPROVED_BY_FACULTY");
        statusList.add("REJECTED_BY_FACULTY");

        recyViewIndicators = (RecyclerView) rootView.findViewById(R.id.recyViewIndicators);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyViewIndicators.setLayoutManager(layoutManager);
        recyViewIndicators.setItemAnimator(new DefaultItemAnimator());
        recyViewIndicators.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        indicatorAdapter = new IndicatorAdapter(getActivity(), statusList);
        recyViewIndicators.setAdapter(indicatorAdapter);

        if (!isNetworkAvailable(getActivity())) {
            // Create an Alert Dialog
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            // Set the Alert Dialog Message
            builder.setMessage("Internet Connection Required")
                    .setCancelable(false)
                    .setPositiveButton("Retry",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int id) {
                                    Toast.makeText(getActivity(), "Please check your internt", Toast.LENGTH_SHORT).show();
                                    // Restart the Activity
                                            /*Intent intent = getIntent();
                                            finish();
                                            startActivity(intent);*/
                                }
                            });
            AlertDialog alert = builder.create();
            alert.show();
        }else {
            funGetAssgnDtl();

        }


        btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!isNetworkAvailable(getActivity())) {
                    // Create an Alert Dialog
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    // Set the Alert Dialog Message
                    builder.setMessage("Internet Connection Required")
                            .setCancelable(false)
                            .setPositiveButton("Retry",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog,
                                                            int id) {
                                            Toast.makeText(getActivity(), "Please check your internt", Toast.LENGTH_SHORT).show();
                                            // Restart the Activity
                                            /*Intent intent = getIntent();
                                            finish();
                                            startActivity(intent);*/
                                        }
                                    });
                    AlertDialog alert = builder.create();
                    alert.show();
                }else {
                    funGetAssgnDtl();
                }
            }
        });

        return rootView;

    }


    private void funGetAssgnDtl() {
        mProgressDialog.setMessage("Please wait loading uploaded assignments!");
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.show();

        String url =BASE_URL + URLEndPoints.GetStudAssignment_URL(student_id,studSessionId,studBranchStandardId,studSemesterType,studCenterCode);
        Log.d(TAG, "Assignment submitted URL  :  " + url);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d(TAG, response.toString());

                try {

                    Gson gson = new Gson();
                    assignmentModel = gson.fromJson(response, AssignmentModel.class);

                    if (assignmentModel.getStatus() == 1) {

                        assignSubmtAssignArrayList = (ArrayList<AssignmentModel.StudAssignDtlsBean>) assignmentModel.getStudAssign_Dtls();

                        if (assignSubmtAssignArrayList != null) {

                            submittedAssgnList=new ArrayList<>();
                            for(int i=0;i<assignSubmtAssignArrayList.size();i++){

                                String STU_SUBMISSION_STATUS=assignSubmtAssignArrayList.get(i).getSTU_SUBMISSION_STATUS();
                                if(STU_SUBMISSION_STATUS.equalsIgnoreCase("SUBMITTED") || STU_SUBMISSION_STATUS.equalsIgnoreCase("REJECTED_BY_FACULTY") || STU_SUBMISSION_STATUS.equalsIgnoreCase("APPROVED_BY_FACULTY")){
                                    submittedAssgnList.add(assignSubmtAssignArrayList.get(i));
                                }
                            }

                            Log.d("PublishedFragment : ",String.valueOf(submittedAssgnList.size()));

                            submittedAssignmentAdapter = new AssignmentAdapter(getActivity(), submittedAssgnList, new AssignmentAdapter.AssignDownUpListner() {
                                @Override
                                public void onDownloadClick(int position) {

                                }

                                @Override
                                public void onUploadClick(int position) {

                                }

                                @Override
                                public void onSubmitAssignClick(int position) {

                                }
                            });

                            recyVwSubmittedAssgn.setAdapter(submittedAssignmentAdapter);

                        } else {
                            Snackbar.make(submittedFrgLyt, "Record not available.", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        }

                    } else if (assignmentModel.getStatus() == 0) {
                        Snackbar.make(submittedFrgLyt, "Record not available.", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }

                    mProgressDialog.dismiss();
                } catch (Exception e) {

                    Snackbar.make(submittedFrgLyt, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                    Log.d(TAG, "Error : " + e.getMessage());
                    mProgressDialog.hide();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                handleVolleyError(error);
                mProgressDialog.dismiss();

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

        Toast.makeText(getActivity(), ""+message, Toast.LENGTH_SHORT).show();
    }

}
