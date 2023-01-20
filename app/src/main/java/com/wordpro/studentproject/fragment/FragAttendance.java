package com.wordpro.studentproject.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
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
import com.wordpro.studentproject.activities.attendance_menu.DayWiseAttendActivity;
import com.wordpro.studentproject.activities.attendance_menu.NetAttendanceActivity;
import com.wordpro.studentproject.activities.attendance_menu.SpanWiseAttendActivity;
import com.wordpro.studentproject.activities.attendance_menu.SubjectWiseAttendActivity;
import com.wordpro.studentproject.activities.syllabus_menu.SubjectwiseFacultyActivity;
import com.wordpro.studentproject.activities.syllabus_menu.TeachPlanSubjActivity;
import com.wordpro.studentproject.adapter.NetAttendanceAdpter;
import com.wordpro.studentproject.adapter.SubjectwiseFacTypeAdapter;
import com.wordpro.studentproject.adapter.TeachPlanSubjAdapter;
import com.wordpro.studentproject.app.MyApplication;
import com.wordpro.studentproject.helper.PrefManager;
import com.wordpro.studentproject.model.NetAttendanceModel;
import com.wordpro.studentproject.model.SubjectwiseFacultyModel;
import com.wordpro.studentproject.model.TeachPlanSubjModel;
import com.wordpro.studentproject.webConfig.URLEndPoints;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.wordpro.studentproject.activities.NavigationActivity.fragAttendance;
import static com.wordpro.studentproject.activities.NavigationActivity.fragSyllabus;
import static com.wordpro.studentproject.activities.NavigationActivity.studBranchStandardId;
import static com.wordpro.studentproject.activities.NavigationActivity.studCenterCode;
import static com.wordpro.studentproject.activities.NavigationActivity.studSemesterType;
import static com.wordpro.studentproject.activities.NavigationActivity.studSessionId;
import static com.wordpro.studentproject.activities.NavigationActivity.studSubMenu;
import static com.wordpro.studentproject.activities.NavigationActivity.stud_main_semester_mst_id;
import static com.wordpro.studentproject.activities.NavigationActivity.student_id;
import static com.wordpro.studentproject.network.Network.isNetworkAvailable;

/**
 * Created by wccs1980 on 11-04-2018.
 */

public class FragAttendance extends DialogFragment {

    private static String TAG = FragAttendance.class.getSimpleName();
    LinearLayout lytDayWiseAttnd;
    LinearLayout lytSpanWiseAttnd;
    LinearLayout lytSubjWiseAttnd;
    LinearLayout lytNetAttend;
    TextView txtAttndCancel;
    ProgressBar progAttndFrgmt;
    private PrefManager pref;
    LinearLayout lytAttnd;
    TextView txtDaywise,txtSpanwise,txtSubjectwise,txtNet,txtAttnd;
    //Net Attendance Model
    public static NetAttendanceModel netAttendanceModel;
    public static ArrayList<NetAttendanceModel.DataBean> netAttndArrayList;
    public static NetAttendanceAdpter netAttendanceAdpter;

    public static TeachPlanSubjModel subjectListModel;
    public static ArrayList<TeachPlanSubjModel.DataBean> subjectArrayList;
    public static TeachPlanSubjAdapter subjectAdapter;

    public static SubjectwiseFacultyModel subjectwiseFacultyModel;
    public static ArrayList<SubjectwiseFacultyModel.DataBean> subjectFacTypeArrayList;
    public static SubjectwiseFacTypeAdapter subjectwiseFacTypeAdapter;


    //Sub Menu Name
    public static final String KEYSTUAPP_ATTND_DAYWISE = "STUAPP_ATTND_DAYWISE";
    public static final String KEYSTUAPP_ATTND_SPANWISE = "STUAPP_ATTND_SPANWISE";
    public static final String KEYSTUAPP_ATTND_SUBJ_WISE = "STUAPP_ATTND_SUBJ_WISE";
    public static final String KEYSTUAPP_ATTND_NET = "STUAPP_ATTND_NET";

    public FragAttendance() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.attend_fragment, container);
        Typeface typefaceHeading = Typeface.createFromAsset(getActivity().getAssets(), "font/Poppins-BoldItalic.otf");
        Typeface typefaceContent = Typeface.createFromAsset(getActivity().getAssets(), "font/Poppins-Medium.otf");

        txtAttnd=(TextView)view.findViewById(R.id.txtAttnd);
        txtAttnd.setTypeface(typefaceHeading);
        txtDaywise=(TextView)view.findViewById(R.id.txtDaywise);
        txtDaywise.setTypeface(typefaceContent);
        txtSpanwise=(TextView)view.findViewById(R.id.txtSpanwise);
        txtSpanwise.setTypeface(typefaceContent);
        txtSubjectwise=(TextView)view.findViewById(R.id.txtSubjectwise);
        txtSubjectwise.setTypeface(typefaceContent);
        txtNet=(TextView)view.findViewById(R.id.txtNet);
        txtNet.setTypeface(typefaceContent);
        lytAttnd = (LinearLayout) view.findViewById(R.id.lytAttnd);

        lytDayWiseAttnd = (LinearLayout) view.findViewById(R.id.lytDayWiseAttnd);
        lytDayWiseAttnd.setVisibility(View.GONE);

        lytSpanWiseAttnd = (LinearLayout) view.findViewById(R.id.lytSpanWiseAttnd);
        lytSpanWiseAttnd.setVisibility(View.GONE);

        lytSubjWiseAttnd = (LinearLayout) view.findViewById(R.id.lytSubjWiseAttnd);
        lytSubjWiseAttnd.setVisibility(View.GONE);

        lytNetAttend = (LinearLayout) view.findViewById(R.id.lytNetAttend);
        lytNetAttend.setVisibility(View.GONE);

        txtAttndCancel = (TextView) view.findViewById(R.id.txtAttndCancel);
        progAttndFrgmt = (ProgressBar) view.findViewById(R.id.progAttndFrgmt);
        pref = new PrefManager(getActivity());


        if (studSubMenu != null && studSubMenu.size() != 0) {

            for (int i = 0; i < studSubMenu.size(); i++) {

                String value = studSubMenu.get(i);

                if (value.equalsIgnoreCase(KEYSTUAPP_ATTND_DAYWISE)) {

                    lytDayWiseAttnd.setVisibility(View.VISIBLE);

                } else if (value.equalsIgnoreCase(KEYSTUAPP_ATTND_SPANWISE)) {

                    lytSpanWiseAttnd.setVisibility(View.VISIBLE);

                } else if (value.equalsIgnoreCase(KEYSTUAPP_ATTND_SUBJ_WISE)) {

                    lytSubjWiseAttnd.setVisibility(View.VISIBLE);

                } else if (value.equalsIgnoreCase(KEYSTUAPP_ATTND_NET)) {

                    lytNetAttend.setVisibility(View.VISIBLE);

                }
            }
        }


        lytNetAttend.setOnClickListener(new View.OnClickListener() {
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
                                            // Restart the Activity
                                            Toast.makeText(getActivity(), "Please check your internet", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                    AlertDialog alert = builder.create();
                    alert.show();
                }else {
                    lytNetAttend.setEnabled(false);
                    getStartEndDate("NET_ATTENDANCE");
                }

            }
        });

        lytDayWiseAttnd.setOnClickListener(new View.OnClickListener() {
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
                    lytDayWiseAttnd.setEnabled(false);
                    getStartEndDate("DAY_WISE_ATTEND");
                }

            }
        });

        lytSpanWiseAttnd.setOnClickListener(new View.OnClickListener() {
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
                }else{
                    lytSpanWiseAttnd.setEnabled(false);
                    getStartEndDate("SPAN_WISE_ATTEND");
                }

            }
        });

        lytSubjWiseAttnd.setOnClickListener(new View.OnClickListener() {
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
                    lytSubjWiseAttnd.setEnabled(false);
                    getStartEndDate("SUBJ_WISE_ATTEND");
                }

            }
        });

        txtAttndCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragAttendance.dismiss();
            }
        });
        return view;
    }

    private void getStartEndDate(final String attendance_type) {

        progAttndFrgmt.setVisibility(View.VISIBLE);
        String url = pref.getURL() + URLEndPoints.getPeriodDates_URL(studBranchStandardId,stud_main_semester_mst_id,studSessionId);
        Log.d(TAG, "NetSelfAttendance Start and End Date : " + url);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, response.toString());
                try {

                    JSONObject responseObj = new JSONObject(response);
                    int status = responseObj.getInt("status");
                    if (status == 1) {

                        String SESSION_ID = responseObj.getString("SESSION_ID");
                        String PERIOD_START_DATE = responseObj.getString("PERIOD_START_DATE");
                        String PERIOD_END_DATE = responseObj.getString("PERIOD_END_DATE");

                        if (PERIOD_START_DATE != null && PERIOD_END_DATE != null) {
                            if (attendance_type.equalsIgnoreCase("NET_ATTENDANCE")) {
                                getNetAttendanceData(PERIOD_START_DATE, PERIOD_END_DATE);
                            } else if (attendance_type.equalsIgnoreCase("DAY_WISE_ATTEND")) {

                                Intent intent = new Intent(getActivity(), DayWiseAttendActivity.class);
                                intent.putExtra("date", "SELECT DATE");
                                intent.putExtra("type", "day");
                                intent.putExtra("fromDate", "0");
                                intent.putExtra("uptoDate", "0");
                                startActivity(intent);
                                fragAttendance.dismiss();
                                getActivity().finish();
                                progAttndFrgmt.setVisibility(View.GONE);

                            } else if (attendance_type.equalsIgnoreCase("SPAN_WISE_ATTEND")) {

                                Intent intent = new Intent(getActivity(), SpanWiseAttendActivity.class);
                                intent.putExtra("spanFromDt", "select from date");
                                intent.putExtra("spanUptoDt", "SELECT UPTO DATE");
                                intent.putExtra("type", "span");
                                startActivity(intent);
                                fragAttendance.dismiss();
                                getActivity().finish();
                                progAttndFrgmt.setVisibility(View.GONE);

                            } else if (attendance_type.equalsIgnoreCase("SUBJ_WISE_ATTEND")) {
                                //progAttndFrgmt.setVisibility(View.VISIBLE);
                                getSubjectListFun(PERIOD_START_DATE, PERIOD_END_DATE);
                            }

                        } else {
                            Snackbar snackbar = Snackbar
                                    .make(lytAttnd, "Record not found.", Snackbar.LENGTH_LONG);
                            snackbar.show();

                            progAttndFrgmt.setVisibility(View.GONE);

                        }

                    } else if (status == 0) {
                        Snackbar.make(lytAttnd, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                        progAttndFrgmt.setVisibility(View.GONE);
                    }


                } catch (Exception e) {

                    Snackbar.make(lytAttnd, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                    Log.d(TAG, "Error : " + e.getMessage());

                    progAttndFrgmt.setVisibility(View.GONE);

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

        Toast.makeText(getActivity(), ""+message, Toast.LENGTH_SHORT).show();
    }

    private void getSubjectListFun(final String PERIOD_START_DATE, final String PERIOD_END_DATE) {
        progAttndFrgmt.setVisibility(View.VISIBLE);
        // String url = pref.getURL() + "Student/GetActionfacultyDetails?Session_Id=" + studSessionId + "&main_Semester_Type=" + studSemesterType + "&EMPLOYEE_ID=0&Subject_Detail_Id=0&BRANCH_STANDARD_ID=" + studBranchStandardId + "&Sub_Dept_NUMBER=0&Emp_Dept_NUMBER=0&Centre_Code=" + studCenterCode;
//-------------------------------------------------------------------
        String url = pref.getURL() + URLEndPoints.GetSubjectWiseFaculty_URL;
        Log.d(TAG, "Subject and faculty List : " + url);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d(TAG, response.toString());
                try {
                    progAttndFrgmt.setVisibility(View.VISIBLE);

                    Gson gson = new Gson();
                    subjectwiseFacultyModel = gson.fromJson(response, SubjectwiseFacultyModel.class);
                    if (subjectwiseFacultyModel.getStatus() == 1) {

                        subjectFacTypeArrayList = (ArrayList<SubjectwiseFacultyModel.DataBean>) subjectwiseFacultyModel.getData();
                        if (subjectFacTypeArrayList != null) {

                            subjectwiseFacTypeAdapter = new SubjectwiseFacTypeAdapter(getActivity(), subjectFacTypeArrayList);
                            Intent intent = new Intent(getActivity(), SubjectWiseAttendActivity.class);
                            intent.putExtra("PERIOD_START_DATE", PERIOD_START_DATE);
                            intent.putExtra("PERIOD_END_DATE", PERIOD_END_DATE);
                            startActivity(intent);
                            getActivity().finish();

                        } else if (subjectArrayList == null) {
                            Snackbar.make(lytAttnd, "Record not available.", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();

                        }
                    } else if (subjectListModel.getStatus() == 0) {
                        Snackbar.make(lytAttnd, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                        progAttndFrgmt.setVisibility(View.GONE);

                    }

                    progAttndFrgmt.setVisibility(View.GONE);

                } catch (Exception e) {

                    Snackbar.make(lytAttnd, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                    Log.d(TAG, "Error : " + e.getMessage());

                    progAttndFrgmt.setVisibility(View.GONE);

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                handleVolleyError(error);
                progAttndFrgmt.setVisibility(View.GONE);

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();

                params.put("P_BRANCH_STANDARD_ID", studBranchStandardId);
                params.put("P_STUDENT_ID", student_id);
                params.put("AcadSemisterType", studSemesterType);
                params.put("_centercode", studCenterCode);
                params.put("PI_SESSION_ID", studSessionId);

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


    private void getNetAttendanceData(final String period_start_date, final String period_end_date) {

        String url = pref.getURL() + URLEndPoints.GetNetSelfAttendance_URL;
        Log.d(TAG, "NetSelfAttendance : " + url);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, response.toString());
                Gson gson = new Gson();

                netAttendanceModel = gson.fromJson(response, NetAttendanceModel.class);
                if (netAttendanceModel.getStatus() == 1) {

                    netAttndArrayList = (ArrayList<NetAttendanceModel.DataBean>) netAttendanceModel.getData();
                    if (netAttndArrayList != null && netAttndArrayList.size() != 0) {

                        netAttendanceAdpter = new NetAttendanceAdpter(getActivity(), netAttndArrayList);
                        Intent intent = new Intent(getActivity(), NetAttendanceActivity.class);
                        intent.putExtra("period_start_date", period_start_date);
                        intent.putExtra("period_end_date", period_end_date);
                        startActivity(intent);
                        fragAttendance.dismiss();
                        getActivity().finish();


                    } else {
                        Snackbar.make(lytAttnd, "Record not found.", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }

                } else if (netAttendanceModel.getStatus() == 0) {
                    Snackbar.make(lytAttnd, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }

                progAttndFrgmt.setVisibility(View.GONE);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                handleVolleyError(error);

                progAttndFrgmt.setVisibility(View.GONE);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("P_REPORT_PATTERN", "NET");
                params.put("p_Subject_Detail_Id", "0");
                params.put("_iPI_APPLICABLE_NUMBER", "0");
                params.put("P_BRANCH_STANDARD_ID", studBranchStandardId);
                params.put("PI_SESSION_ID", studSessionId);
                params.put("AcadSemisterType", studSemesterType);
                params.put("start_DATE", period_start_date);
                params.put("end_DATE", period_end_date);
                params.put("P_STUDENT_ID", student_id);
                params.put("PI_IS_ACTIVE", "Y");
                params.put("P_Attend_Per_From", "0");
                params.put("P_Attend_Per_Upto", "100");

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
}
