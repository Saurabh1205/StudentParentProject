package com.wordpro.studentproject.fragment;

import android.app.AlertDialog;
import android.app.ProgressDialog;
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
import com.wordpro.studentproject.activities.schedule_menu.ExtraLectureActivity;
import com.wordpro.studentproject.activities.schedule_menu.NewTimetblActivity;
import com.wordpro.studentproject.activities.schedule_menu.SelfTimeTableActivity;
import com.wordpro.studentproject.activities.schedule_menu.SubjectwiseBatchActivity;
import com.wordpro.studentproject.adapter.ExtraLecAdapter;
import com.wordpro.studentproject.adapter.SubjectwiseBatchAdpter;
import com.wordpro.studentproject.adapter.SubjectwiseFacAdapter;
import com.wordpro.studentproject.app.MyApplication;
import com.wordpro.studentproject.helper.PrefManager;
import com.wordpro.studentproject.model.ExtraLecModel;
import com.wordpro.studentproject.model.StudTimeTableModel;
import com.wordpro.studentproject.model.SubjectwiseFacultyModel;
import com.wordpro.studentproject.webConfig.URLEndPoints;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.regex.Pattern;

import static com.wordpro.studentproject.activities.NavigationActivity.fragSchedule;
import static com.wordpro.studentproject.activities.NavigationActivity.studAcadSessName;
import static com.wordpro.studentproject.activities.NavigationActivity.studBranchStandDesc;
import static com.wordpro.studentproject.activities.NavigationActivity.studBranchStandardId;
import static com.wordpro.studentproject.activities.NavigationActivity.studCenterCode;
import static com.wordpro.studentproject.activities.NavigationActivity.studMainSemName;
import static com.wordpro.studentproject.activities.NavigationActivity.studSemesterType;
import static com.wordpro.studentproject.activities.NavigationActivity.studSessionId;
import static com.wordpro.studentproject.activities.NavigationActivity.studSubMenu;
import static com.wordpro.studentproject.activities.NavigationActivity.studentName;
import static com.wordpro.studentproject.activities.NavigationActivity.student_id;
import static com.wordpro.studentproject.network.Network.isNetworkAvailable;

/**
 * Created by wccs1980 on 14-05-2018.
 */

public class FragSchedule extends DialogFragment {

    private static String TAG = FragAttendance.class.getSimpleName();
    LinearLayout lytSchdl, lytTimeTbl, lytMyBatch, lytExLec;
    ProgressDialog mProgressDialog;
    TextView txtSchdCancel,txtHead,txt1,txt2,txt3;
    private PrefManager pref;

    //student timetable model
    public static StudTimeTableModel studTimeTableModel;
    public static ArrayList<StudTimeTableModel.DataBean> recordArrayList;
    public static ArrayList<String> timePrdSquenceArray;
    public static ArrayList<String> weekArrayList;
    public static ArrayList<String> periodSquenceArrayList;

    public static ArrayList<String> timming;
    public static ArrayList<String> mondayPeriod;
    public static ArrayList<String> tuesdayPeriod;
    public static ArrayList<String> wednesdayPeriod;
    public static ArrayList<String> thrusdayPeriod;
    public static ArrayList<String> fridayPeriod;
    public static ArrayList<String> saturdayPeriod;
    public static ArrayList<String> periodSquence;
    ArrayList<String> periodTimeArray;
    ArrayList<String> tuesday;
    public static int count = 0;

    //student Extra Lecture model
    public static ExtraLecModel extraLecModel;
    public static ArrayList<ExtraLecModel.DataBean> extraLecArrayList;
    public static ExtraLecAdapter extraLecAdapter;

    public static SubjectwiseFacultyModel subjectwiseBatchModel;
    public static ArrayList<SubjectwiseFacultyModel.DataBean> subjectwiseBatchArrayList;
    public static SubjectwiseBatchAdpter subjectwiseBatchAdpter;

    //Sub Menu Name
    public static final String KEYSTUAPP_SCHDUL_TMTBL = "STUAPP_SCHDUL_TMTBL";
    public static final String KEYSTUAPP_SCHDUL_EXTR_LEC = "STUAPP_SCHDUL_EXTR_LEC";
    public static final String KEYSTUAPP_SCHDUL_BATCHES = "STUAPP_SCHDUL_BATCHES";

    public FragSchedule() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.schedule_frag, container);

        Typeface typefaceHeading = Typeface.createFromAsset(getActivity().getAssets(), "font/Poppins-BoldItalic.otf");
        Typeface typefaceContent = Typeface.createFromAsset(getActivity().getAssets(), "font/Poppins-Medium.otf");

        lytSchdl = (LinearLayout) view.findViewById(R.id.lytSchdl);
        lytTimeTbl = (LinearLayout) view.findViewById(R.id.lytTimeTbl);
        lytTimeTbl.setVisibility(View.GONE);
        lytMyBatch = (LinearLayout) view.findViewById(R.id.lytMyBatch);
        lytMyBatch.setVisibility(View.GONE);
        lytExLec = (LinearLayout) view.findViewById(R.id.lytExLec);
        lytExLec.setVisibility(View.GONE);

        mProgressDialog=new ProgressDialog(getActivity());
        txtSchdCancel = (TextView) view.findViewById(R.id.txtSchdCancel);
        txtSchdCancel.setTypeface(typefaceHeading);
        txtHead=(TextView)view.findViewById(R.id.txtHead);
        txtHead.setTypeface(typefaceHeading);
        txt1=(TextView)view.findViewById(R.id.txt1);
        txt1.setTypeface(typefaceContent);
        txt2=(TextView)view.findViewById(R.id.txt2);
        txt2.setTypeface(typefaceContent);
        txt3=(TextView)view.findViewById(R.id.txt3);
        txt3.setTypeface(typefaceContent);
        pref = new PrefManager(getActivity());


        if (studSubMenu != null && studSubMenu.size() != 0) {

            for (int i = 0; i < studSubMenu.size(); i++) {

                String value = studSubMenu.get(i);

                if (value.equalsIgnoreCase(KEYSTUAPP_SCHDUL_TMTBL)) {

                    lytTimeTbl.setVisibility(View.VISIBLE);

                } else if (value.equalsIgnoreCase(KEYSTUAPP_SCHDUL_EXTR_LEC)) {

                    lytExLec.setVisibility(View.VISIBLE);

                } else if (value.equalsIgnoreCase(KEYSTUAPP_SCHDUL_BATCHES)) {

                    lytMyBatch.setVisibility(View.VISIBLE);

                }
            }
        }

        lytTimeTbl.setOnClickListener(new View.OnClickListener() {
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
                    lytTimeTbl.setEnabled(false);
                    getTimetbleData();
                }

            }
        });

        lytMyBatch.setOnClickListener(new View.OnClickListener() {
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
                    lytMyBatch.setEnabled(false);
                    getMyBatchData();
                }

            }
        });

        lytExLec.setOnClickListener(new View.OnClickListener() {
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
                    lytExLec.setEnabled(false);
                    getExtraLectureData();
                }

            }
        });

        txtSchdCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragSchedule.dismiss();
            }
        });

        return view;
    }

    private void getMyBatchData() {
       mProgressDialog.setMessage("Loading please wait");
       mProgressDialog.setCanceledOnTouchOutside(false);
       mProgressDialog.show();

        String url = pref.getURL() + URLEndPoints.GetSubjectWiseFaculty_URL;
        Log.d(TAG, "Subject and faculty List : " + url);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, response.toString());

                Gson gson = new Gson();
                subjectwiseBatchModel = gson.fromJson(response, SubjectwiseFacultyModel.class);
                if (subjectwiseBatchModel.getStatus() == 1) {

                    subjectwiseBatchArrayList = (ArrayList<SubjectwiseFacultyModel.DataBean>) subjectwiseBatchModel.getData();
                    if (subjectwiseBatchArrayList != null) {

                        subjectwiseBatchAdpter = new SubjectwiseBatchAdpter(getActivity(), subjectwiseBatchArrayList);
                        Intent intent = new Intent(getActivity(), SubjectwiseBatchActivity.class);
                        startActivity(intent);
                        fragSchedule.dismiss();
                        getActivity().finish();

                    } else {
                        Snackbar.make(lytSchdl, "Record not found.", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }

                } else if (subjectwiseBatchModel.getStatus() == 0) {
                    Snackbar.make(lytSchdl, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }

                mProgressDialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                handleVolleyError(error);

                mProgressDialog.dismiss();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("_centercode", studCenterCode);
                params.put("PI_SESSION_ID", studSessionId);
                params.put("P_BRANCH_STANDARD_ID", studBranchStandardId);
                params.put("AcadSemisterType", studSemesterType);
                params.put("P_STUDENT_ID", student_id);

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

        Toast.makeText(getActivity(), ""+message, Toast.LENGTH_SHORT).show();
    }

    private void getExtraLectureData() {
        mProgressDialog.setMessage("Loading please wait");
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.show();

        String url = pref.getURL() + URLEndPoints.GetStudentExtraLecture_URL(studCenterCode,studSessionId,studBranchStandardId,studSemesterType,student_id);
        Log.d(TAG, "Student Extra Time table : " + url);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, response.toString());
                try {

                    Gson gson = new Gson();
                    extraLecModel = gson.fromJson(response, ExtraLecModel.class);

                    if (extraLecModel.getStatus() == 1) {

                        extraLecArrayList = (ArrayList<ExtraLecModel.DataBean>) extraLecModel.getData();

                        if (extraLecArrayList != null) {

                            extraLecAdapter = new ExtraLecAdapter(getActivity(), extraLecArrayList);
                            Intent intent = new Intent(getActivity(), ExtraLectureActivity.class);
                            startActivity(intent);
                            fragSchedule.dismiss();
                            getActivity().finish();

                        } else {
                            Snackbar.make(lytSchdl, "Record not available.", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        }
                    } else if (extraLecModel.getStatus() == 0) {

                        Snackbar.make(lytSchdl, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }


                    mProgressDialog.dismiss();

                } catch (Exception e) {

                    Snackbar.make(lytSchdl, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                    Log.d(TAG, "Error : " + e.getMessage());

                    mProgressDialog.dismiss();
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

    private void getTimetbleData() {

        mProgressDialog.setMessage("Loading please wait");
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.show();

        String url = pref.getURL() + URLEndPoints.GetSelfTimetable_URL(studSessionId,studSemesterType,studBranchStandardId,studCenterCode);
        Log.d(TAG, "Student Time table : " + url);


        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, response.toString());

                try {

                    Gson gson = new Gson();
                    studTimeTableModel = gson.fromJson(response, StudTimeTableModel.class);

                    if (studTimeTableModel.getStatus() == 1) {

                        recordArrayList = (ArrayList<StudTimeTableModel.DataBean>) studTimeTableModel.getData();
                       // selfSubCodeArrayList = (ArrayList<SelfTimeTblModel.SubCodeBean>) selfTimeTblModel.getSubCode();
                        mProgressDialog.dismiss();


                        if (recordArrayList.size() != 0 && recordArrayList != null) {

                            weekArrayList = new ArrayList<>();
                            for (int i = 0; i < recordArrayList.size(); i++) {

                                String WEEK_DESCRIPTION = recordArrayList.get(i).getWEEK_DESCRIPTION();
                                weekArrayList.add(WEEK_DESCRIPTION);

                            }

                            Object[] st = weekArrayList.toArray();
                            for (Object s : st) {
                                if (weekArrayList.indexOf(s) != weekArrayList.lastIndexOf(s)) {
                                    weekArrayList.remove(weekArrayList.lastIndexOf(s));
                                }
                            }
                            for (String week : weekArrayList) {
                                Log.d(TAG, "\nDay : " + week);
                            }
                            Log.d(TAG, " weekArrayList Size : " + weekArrayList.size());


                            periodSquenceArrayList = new ArrayList<>();
                            for (int i = 0; i < recordArrayList.size(); i++) {

                                String PERIOD_SEQUENCE_NO = recordArrayList.get(i).getPERIOD_SEQUENCE_NO();
                                if (!PERIOD_SEQUENCE_NO.isEmpty()) {
                                    periodSquenceArrayList.add(PERIOD_SEQUENCE_NO);
                                }
                            }

                            Object[] st1 = periodSquenceArrayList.toArray();
                            for (Object s : st1) {
                                if (periodSquenceArrayList.indexOf(s) != periodSquenceArrayList.lastIndexOf(s)) {
                                    periodSquenceArrayList.remove(periodSquenceArrayList.lastIndexOf(s));
                                }
                            }

                            for (String prdSqu : periodSquenceArrayList) {
                                Log.d(TAG, "\nPeriod Sequence : " + prdSqu);
                            }
                            Log.d(TAG, " periodSquenceArrayList Size : " + periodSquenceArrayList.size());


                            String WEEK_DESCRIPTION = recordArrayList.get(0).getWEEK_DESCRIPTION();

                            timePrdSquenceArray = new ArrayList<>();

                            timePrdSquenceArray.add("Time : \nDay ");

                            for (int i = 0; i < recordArrayList.size(); i++) {

                                if (WEEK_DESCRIPTION.equalsIgnoreCase(recordArrayList.get(i).getWEEK_DESCRIPTION())) {

                                    String PERIOD_SEQUENCE_NO = recordArrayList.get(i).getPERIOD_SEQUENCE_NO();
                                    String PERIOD_FROM_TIME = recordArrayList.get(i).getPERIOD_FROM_TIME(); //"12/18/2018 11:05:00 AM"
                                    String PERIOD_UPTO_TIME = recordArrayList.get(i).getPERIOD_UPTO_TIME(); //"12/18/2018 11:05:00 AM"

                                    if (!PERIOD_SEQUENCE_NO.isEmpty() && !PERIOD_FROM_TIME.equalsIgnoreCase("Recess") && !PERIOD_UPTO_TIME.equalsIgnoreCase("Recess")) {
                                        if (!PERIOD_FROM_TIME.isEmpty() && PERIOD_FROM_TIME != "" && PERIOD_FROM_TIME != null) {
                                            String[] a = PERIOD_FROM_TIME.split(Pattern.quote(" "));
                                            String[] b = a[1].split(Pattern.quote(":"));

                                            if (a.length == 3) {
                                                PERIOD_FROM_TIME = b[0] + ":" + b[1] + " " + a[2];
                                            } else {
                                                PERIOD_FROM_TIME = b[0] + ":" + b[1];
                                            }

                                        }

                                        if (!PERIOD_UPTO_TIME.isEmpty() && PERIOD_UPTO_TIME != "" && PERIOD_UPTO_TIME != null) {
                                            String[] a = PERIOD_UPTO_TIME.split(Pattern.quote(" "));
                                            String[] b = a[1].split(Pattern.quote(":"));

                                            if (a.length == 3) {
                                                PERIOD_UPTO_TIME = b[0] + ":" + b[1] + " " + a[2];
                                            } else {
                                                PERIOD_UPTO_TIME = b[0] + ":" + b[1];
                                            }

                                        }

                                        String fromTime="";
                                        String uptoTime="";
                                        try {
                                            // PERIOD_FROM_TIME = "22:15";
                                            SimpleDateFormat _24HourSDF = new SimpleDateFormat("HH:mm");
                                            SimpleDateFormat _12HourSDF = new SimpleDateFormat("hh:mm a");
                                            Date _24HourDt = _24HourSDF.parse(PERIOD_FROM_TIME);
                                            System.out.println(_24HourDt);
                                            fromTime=_12HourSDF.format(_24HourDt);
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }

                                        try {
                                            // PERIOD_FROM_TIME = "22:15";
                                            SimpleDateFormat _24HourSDF = new SimpleDateFormat("HH:mm");
                                            SimpleDateFormat _12HourSDF = new SimpleDateFormat("hh:mm a");
                                            Date _24HourDt = _24HourSDF.parse(PERIOD_UPTO_TIME);
                                            System.out.println(_24HourDt);
                                            uptoTime=_12HourSDF.format(_24HourDt);
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }

                                        String time = fromTime + " to " + uptoTime + "\n(" + PERIOD_SEQUENCE_NO + ")";
                                        timePrdSquenceArray.add(time);

                                    }

                                }

                            }


                            for (String time : timePrdSquenceArray) {
                                Log.d(TAG, "\nTime : " + time);
                            }
                            Log.d(TAG, " timePrdSquenceArray Size : " + timePrdSquenceArray.size());

                            Object[] st2 = timePrdSquenceArray.toArray();
                            for (Object s : st2) {
                                if (timePrdSquenceArray.indexOf(s) != timePrdSquenceArray.lastIndexOf(s)) {
                                    timePrdSquenceArray.remove(timePrdSquenceArray.lastIndexOf(s));
                                }
                            }
                            for (String time : timePrdSquenceArray) {
                                Log.d(TAG, "\nTime : " + time);
                            }
                            Log.d(TAG, " timePrdSquenceArray Size : " + timePrdSquenceArray.size());





                            //addHeaders();
                            Intent intent = new Intent(getActivity(), NewTimetblActivity.class);
                            intent.putExtra("student", studentName);
                            intent.putExtra("year", studAcadSessName);
                            intent.putExtra("semesetr", studMainSemName);
                            intent.putExtra("section", studBranchStandDesc);
                            startActivity(intent);
                            fragSchedule.dismiss();
                            getActivity().finish();


                        } else {
                            Snackbar.make(lytSchdl, "Time Table record not available.", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        }


                    } else {
                        mProgressDialog.dismiss();
                        Snackbar.make(lytSchdl, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }


                } catch (Exception e) {

                    mProgressDialog.hide();
                    Snackbar.make(lytSchdl, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    Log.d(TAG, "Error : " + e.getMessage());

                }



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Error: " + error.getMessage());
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


}
