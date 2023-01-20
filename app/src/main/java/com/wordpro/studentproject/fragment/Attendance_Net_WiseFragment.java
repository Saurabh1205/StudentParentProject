package com.wordpro.studentproject.fragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.wordpro.studentproject.activities.attendance_menu.NetAttendanceActivity;
import com.wordpro.studentproject.adapter.NetAttendanceAdpter;
import com.wordpro.studentproject.adapter.NetWiseAttendanceAdpter;
import com.wordpro.studentproject.adapter.SpanDayWiseAttendanceAdpter;
import com.wordpro.studentproject.app.MyApplication;
import com.wordpro.studentproject.helper.PrefManager;
import com.wordpro.studentproject.model.NetAttendanceModel;
import com.wordpro.studentproject.model.SpanwiseModel;
import com.wordpro.studentproject.utils.UtilityClass;
import com.wordpro.studentproject.webConfig.URLEndPoints;

import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.wordpro.studentproject.R.id.frame_container_Action;
import static com.wordpro.studentproject.activities.NavigationActivity.fragAttendance;
import static com.wordpro.studentproject.activities.NavigationActivity.studBranchStandardId;
import static com.wordpro.studentproject.activities.NavigationActivity.studSemesterType;
import static com.wordpro.studentproject.activities.NavigationActivity.studSessionId;
import static com.wordpro.studentproject.activities.NavigationActivity.student_id;
import static com.wordpro.studentproject.network.Network.isNetworkAvailable;


public class Attendance_Net_WiseFragment extends Fragment {
    private static String TAG = Attendance_Net_WiseFragment.class.getSimpleName();
    private LinearLayout  main_linear_view;
    Fragment me=this;
    Attendance_Net_WiseFragment actionNewsFragment;
    private TextView  show_search_startdate,show_search_enddate,txt_sub_name,txt_Tm,txt_tolPre,txt_total_pre,txt_total_pre2,txt_tolPer;
    private ImageView imageView_startDate,imageView_endDate,imgPer;
    private LinearLayout lay_day_wise,lay_sub_wise,lay_span_wise,lay_net_wise;
    String studCenterCode,studDepartmentNumber,student_id,studBranchStandardId;
    ProgressDialog mProgressDialog;
    private PrefManager pref;
    ProgressBar progAttndFrgmt;
    private RelativeLayout  lytAttndence;
    private RecyclerView spanWiseList;
    private LinearLayout date_layout;
    private CardView card_view,card_view1,card_view_net;
    public static NetAttendanceModel netAttendanceModel;
    public static ArrayList<NetAttendanceModel.DataBean> netAttndArrayList;
    public static NetAttendanceAdpter netAttendanceAdpter;
    private NetWiseAttendanceAdpter netWiseAttendanceAdpter;
    public Fragment fragment;
    private DatePicker  datePicker;
    private  String NameOfParam;
    private String StartDate,EndDate;
    private UtilityClass utilityClassObj;
    public Attendance_Net_WiseFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        final View view = inflater.inflate(R.layout.span_wise_attendance, container, false);
        Typeface type_faceHeading = Typeface.createFromAsset(getActivity().getAssets(), "font/Poppins-Bold.ttf");
        Typeface type_faceContent = Typeface.createFromAsset(getActivity().getAssets(), "font/Poppins-Regular.ttf");
        pref = new PrefManager(getActivity());
        utilityClassObj  = new  UtilityClass(getActivity());

        progAttndFrgmt = (ProgressBar) view.findViewById(R.id.progAttndFrgmt);
        lytAttndence = (RelativeLayout)view.findViewById(R.id.lytAttndence);
        show_search_startdate =(TextView) view.findViewById(R.id.show_search_startdate);
        show_search_enddate=(TextView) view.findViewById(R.id.show_search_enddate);


        spanWiseList= (RecyclerView) view.findViewById(R.id.spanWiseList);
        imageView_startDate = (ImageView) view.findViewById(R.id.imageView_startDate);
        imageView_endDate= (ImageView) view.findViewById(R.id.imageView_endDate);
        date_layout= (LinearLayout) view.findViewById(R.id.date_layout);
        datePicker = (DatePicker)view. findViewById(R.id.datePicker);
        card_view = (CardView) view. findViewById(R.id.card_view);
        card_view_net = (CardView) view. findViewById(R.id.card_view_net);

        txt_sub_name = (TextView) view.findViewById(R.id.txt_sub_name);
        txt_Tm = (TextView) view.findViewById(R.id.txt_Tm);
        txt_tolPre = (TextView) view.findViewById(R.id.txt_tolPre);
        txt_tolPer = (TextView) view.findViewById(R.id.txt_tolPer);
        txt_total_pre = (TextView) view.findViewById(R.id.txt_total_pre);
        txt_total_pre2 = (TextView) view.findViewById(R.id.txt_total_pre2);
        imgPer = (ImageView) view.findViewById(R.id.imgPer);

        txt_total_pre.setTypeface(type_faceHeading);
        txt_sub_name.setTypeface(type_faceHeading);
       // txt_Tm.setTypeface(type_faceHeading);
        txt_tolPre.setTypeface(type_faceContent);
        txt_total_pre2.setTypeface(type_faceHeading);
        txt_tolPer.setTypeface(type_faceContent);
        date_layout.setVisibility(View.GONE);
        card_view_net.setVisibility(View.VISIBLE);
        getStartEndDate("NET_ATTENDANCE");
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

    }
    @Override
    public void onStop() {
        super.onStop();

    }


    @Override
    public void onResume() {
        super.onResume();

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
                        String totalcount;
                        netWiseAttendanceAdpter = new NetWiseAttendanceAdpter(getActivity(),netAttndArrayList);
                        spanWiseList.setLayoutManager(new LinearLayoutManager(getActivity()));
                        spanWiseList.setAdapter(netWiseAttendanceAdpter);


                       // txt_Tm.setText(netAttndArrayList.get(0).getSUBJECT_DESCRIPTION());

                       // txt_total_pre2.setText("100");
                        int arrayCount = netAttndArrayList.size();
                         int totalSum =arrayCount *100;
                        int TotalADD = 0;
                        int TotalPERIODS_PRSNT =0;
                        int TOTAL_NOOF_PERIODS =0;
                        for (int i =0;i<netAttndArrayList.size();i++){
                             int Aa = Integer.parseInt(netAttndArrayList.get(i).getATTEND_PER());
                             int PeriodsPre=  Integer.parseInt(netAttndArrayList.get(i).getNO_OF_PERIODS_PRSNT());
                             int Periods=  Integer.parseInt(netAttndArrayList.get(i).getTOTAL_NOOF_PERIODS());
                             TotalADD= TotalADD + Aa;
                            TotalPERIODS_PRSNT = TotalPERIODS_PRSNT +PeriodsPre;
                            TOTAL_NOOF_PERIODS  = TOTAL_NOOF_PERIODS +Periods;
                        }

                        Double PeriodsPRE;
                        Double Periods;
                        int PeriodsPREINT;
                        int PeriodsINT;
                        try {
                            // PeriodsPRE = Double.valueOf(TotalPERIODS_PRSNT*100/totalSum);
                            // Periods = Double.valueOf(TOTAL_NOOF_PERIODS*100/totalSum);
                            PeriodsPREINT = (int)Math.round(TotalPERIODS_PRSNT);
                            PeriodsINT = (int)Math.round(TOTAL_NOOF_PERIODS);
                            String PPP=String.valueOf(PeriodsPREINT);
                            String P=String.valueOf(PeriodsINT);
                            txt_tolPre.setText(PPP +" ]");
                           // txt_total_pre.setText(PPP);
                            txt_tolPer.setText(P +" ]");
                        }catch (Exception e){
                            e.printStackTrace();
                        }

                        Double Sum= Double.valueOf(TotalADD*100/totalSum);

                        int TotalPrevalue = (int)Math.round(Sum);

                        String s=String.valueOf(TotalPrevalue);

                        txt_sub_name.setText("Net Attendance" +"(" + s + ")");
                        String totalPre=String.valueOf(Sum);
                        txt_total_pre.setText(s);
                        String totalCount=String.valueOf(totalSum);

                       // txt_total_pre2.setText("100");
                      /*  Intent intent = new Intent(getActivity(), NetAttendanceActivity.class);
                        intent.putExtra("period_start_date", period_start_date);
                        intent.putExtra("period_end_date", period_end_date);
                        startActivity(intent);
                        fragAttendance.dismiss();
                        getActivity().finish();*/


                    } else {
                        Snackbar.make(lytAttndence, "Record not found.", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }

                } else if (netAttendanceModel.getStatus() == 0) {
                    Snackbar.make(lytAttndence, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
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
                params.put("P_BRANCH_STANDARD_ID", URLEndPoints.Constance_StudBranchStandardId);
                params.put("PI_SESSION_ID", URLEndPoints.Constance_StudSessionId);
                params.put("AcadSemisterType", URLEndPoints.Constance_StudSemesterType);
                params.put("start_DATE", period_start_date);
                params.put("end_DATE", period_end_date);
                params.put("P_STUDENT_ID", URLEndPoints.Constance_StudentID);
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

    private void getStartEndDate(final String attendance_type) {

        utilityClassObj.startLoader(getActivity(),R.drawable.image_for_rotation);
        String url = pref.getURL() + URLEndPoints.getPeriodDates_URL(URLEndPoints.Constance_StudBranchStandardId,URLEndPoints.Constance_stud_main_semester_mst_id,URLEndPoints.Constance_StudSessionId);
        Log.d(TAG, "NetSelfAttendance Start and End Date : " + url);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, response.toString());
                try {

                    JSONObject responseObj = new JSONObject(response);
                    int status = responseObj.getInt("status");
                    if (status == 1) {
                        utilityClassObj.stopLoader();
                        String SESSION_ID = responseObj.getString("SESSION_ID");
                        String PERIOD_START_DATE = responseObj.getString("PERIOD_START_DATE");
                        String PERIOD_END_DATE = responseObj.getString("PERIOD_END_DATE");

                        if (PERIOD_START_DATE != null && PERIOD_END_DATE != null) {
                            if (attendance_type.equalsIgnoreCase("NET_ATTENDANCE")) {
                                  getNetAttendanceData(PERIOD_START_DATE, PERIOD_END_DATE);
                            } else if (attendance_type.equalsIgnoreCase("DAY_WISE_ATTEND")) {


                            } else if (attendance_type.equalsIgnoreCase("SPAN_WISE_ATTEND")) {

                            } else if (attendance_type.equalsIgnoreCase("SUBJ_WISE_ATTEND")) {

                            }

                        } else {
                            Snackbar snackbar = Snackbar
                                    .make(lytAttndence, "Record not found.", Snackbar.LENGTH_LONG);
                            snackbar.show();

                            utilityClassObj.stopLoader();

                        }

                    } else if (status == 0) {
                        Snackbar.make(lytAttndence, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                        utilityClassObj.stopLoader();
                    }


                } catch (Exception e) {

                    Snackbar.make(lytAttndence, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                    Log.d(TAG, "Error : " + e.getMessage());

                    utilityClassObj.stopLoader();

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


}
