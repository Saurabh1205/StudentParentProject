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
import com.wordpro.studentproject.activities.attendance_menu.DayWiseAttendActivity;
import com.wordpro.studentproject.activities.syllabus_menu.SubjectwiseFacultyActivity;
import com.wordpro.studentproject.activities.syllabus_menu.SyllStatusActivity;
import com.wordpro.studentproject.activities.syllabus_menu.TeachPlanSubjActivity;
import com.wordpro.studentproject.activities.syllabus_menu.UnivSubjectActivity;
import com.wordpro.studentproject.adapter.SubjectwiseFacAdapter;
import com.wordpro.studentproject.adapter.TeachPlanSubjAdapter;
import com.wordpro.studentproject.adapter.UniverSubjAdapter;
import com.wordpro.studentproject.app.MyApplication;
import com.wordpro.studentproject.helper.PrefManager;
import com.wordpro.studentproject.model.SubjectwiseFacultyModel;
import com.wordpro.studentproject.model.SyllStatusModel;
import com.wordpro.studentproject.model.TeachPlanSubjModel;
import com.wordpro.studentproject.model.UnviSubjModel;
import com.wordpro.studentproject.webConfig.URLEndPoints;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.wordpro.studentproject.activities.NavigationActivity.fragSyllabus;
import static com.wordpro.studentproject.activities.NavigationActivity.studBranchStandardId;
import static com.wordpro.studentproject.activities.NavigationActivity.studBranch_Standard_GRP_ID;
import static com.wordpro.studentproject.activities.NavigationActivity.studCenterCode;
import static com.wordpro.studentproject.activities.NavigationActivity.studSemesterType;
import static com.wordpro.studentproject.activities.NavigationActivity.studSessionId;
import static com.wordpro.studentproject.activities.NavigationActivity.studSubMenu;
import static com.wordpro.studentproject.activities.NavigationActivity.student_id;
import static com.wordpro.studentproject.network.Network.isNetworkAvailable;

/**
 * Created by wccs1980 on 11-04-2018.
 */

public class FragSyllabus extends DialogFragment {

    private static String TAG = FragAttendance.class.getSimpleName();

    public FragSyllabus() {
    }

    TextView txtSyllbusCancel,txt1,txt2,txt3,txt4,txt5,txt6;
    LinearLayout lytSubject;
    LinearLayout lytUnivSyll;
    LinearLayout lytTeachPlan;
    LinearLayout lytSyllStatus;
    LinearLayout lytSubjWiseFac;
    ProgressBar prgBarSyllbus;
    private PrefManager pref;
    LinearLayout lytFragSyllabus;

    //Teaching Plan Subject List
    public static TeachPlanSubjModel teachPlanSubjModel;
    public static ArrayList<TeachPlanSubjModel.DataBean> teachPlanSubjArrayList;
    public static TeachPlanSubjAdapter teachPlanSubjAdapter;

    //Subjectwise faculty Model
    public static SubjectwiseFacultyModel subjectwiseFacultyModel;
    public static ArrayList<SubjectwiseFacultyModel.DataBean> subjectwiseFacArrayList;
    public static SubjectwiseFacAdapter subjectwiseFacAdapter;

    //University Syllabus Subject Model
    public static UnviSubjModel univerSubjModel;
    public static ArrayList<UnviSubjModel.DataBean> univSubjArrayList;
    public static UniverSubjAdapter univerSubjAdapter;

    //Syllabus Status Model
    public static SyllStatusModel syllStatusModel;
    public static ArrayList<SyllStatusModel.DataBean> syllStatusArrayList;

    //Sub Menu Name
    public static final String KEYSTUAPP_SYLBUS_SUBJ = "STUAPP_SYLBUS_SUBJ";
    public static final String KEYSTUAPP_SYLBUS_UNVSITY = "STUAPP_SYLBUS_UNVSITY";
    public static final String KEYSTUAPP_SYLBUS_TEACH_PLAN = "STUAPP_SYLBUS_TEACH_PLAN";
    public static final String KEYSTUAPP_SYLBUS_STATUS = "STUAPP_SYLBUS_STATUS";
    public static final String KEYSTUAPP_SYLBUS_FAC = "STUAPP_SYLBUS_FAC";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.syllbus_fragment, container);
        Typeface typefaceHeading = Typeface.createFromAsset(getActivity().getAssets(), "font/Poppins-BoldItalic.otf");
        Typeface typefaceContent = Typeface.createFromAsset(getActivity().getAssets(), "font/Poppins-Medium.otf");
        pref = new PrefManager(getActivity());
        prgBarSyllbus = (ProgressBar) view.findViewById(R.id.prgBarSyllbus);
        txtSyllbusCancel = (TextView) view.findViewById(R.id.txtSyllbusCancel);
        txtSyllbusCancel.setTypeface(typefaceHeading);
        txt1=(TextView)view.findViewById(R.id.txt1);
        txt1.setTypeface(typefaceHeading);
        txt2=(TextView)view.findViewById(R.id.txt2);
        txt2.setTypeface(typefaceContent);
        txt3=(TextView)view.findViewById(R.id.txt3);
        txt3.setTypeface(typefaceContent);
        txt4=(TextView)view.findViewById(R.id.txt4);
        txt4.setTypeface(typefaceContent);
        txt5=(TextView)view.findViewById(R.id.txt5);
        txt5.setTypeface(typefaceContent);
        txt6=(TextView)view.findViewById(R.id.txt6);
        txt6.setTypeface(typefaceContent);
        lytSubject = (LinearLayout) view.findViewById(R.id.lytSubject);
        lytSubject.setVisibility(View.GONE);
        lytTeachPlan = (LinearLayout) view.findViewById(R.id.lytTeachPlan);
        lytTeachPlan.setVisibility(View.GONE);
        lytUnivSyll = (LinearLayout) view.findViewById(R.id.lytUnivSyll);
        lytUnivSyll.setVisibility(View.GONE);
        lytSyllStatus = (LinearLayout) view.findViewById(R.id.lytSyllStatus);
        lytSyllStatus.setVisibility(View.GONE);
        lytSubjWiseFac = (LinearLayout) view.findViewById(R.id.lytSubjWiseFac);
        lytSubjWiseFac.setVisibility(View.GONE);


        if (studSubMenu != null && studSubMenu.size() != 0) {

            for (int i = 0; i < studSubMenu.size(); i++) {

                String value = studSubMenu.get(i);

                if (value.equalsIgnoreCase(KEYSTUAPP_SYLBUS_SUBJ)) {

//                    lytSubject.setVisibility(View.VISIBLE);

                } else if (value.equalsIgnoreCase(KEYSTUAPP_SYLBUS_UNVSITY)) {

                    lytUnivSyll.setVisibility(View.VISIBLE);

                } else if (value.equalsIgnoreCase(KEYSTUAPP_SYLBUS_TEACH_PLAN)) {

                    lytTeachPlan.setVisibility(View.VISIBLE);

                } else if (value.equalsIgnoreCase(KEYSTUAPP_SYLBUS_STATUS)) {

                    lytSyllStatus.setVisibility(View.VISIBLE);

                } else if (value.equalsIgnoreCase(KEYSTUAPP_SYLBUS_FAC)) {

                    lytSubjWiseFac.setVisibility(View.VISIBLE);

                }
            }
        }


        lytSubject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(FragSyllabus.this.getContext(), "Work in progress..", Toast.LENGTH_SHORT).show();
            }
        });

        lytFragSyllabus = (LinearLayout) view.findViewById(R.id.lytFragSyllabus);
        txtSyllbusCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fragSyllabus.dismiss();

            }
        });

        lytTeachPlan.setOnClickListener(new View.OnClickListener() {
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

                    lytTeachPlan.setEnabled(false);
                    prgBarSyllbus.setVisibility(View.VISIBLE);

                    String url = pref.getURL() + URLEndPoints.GetActionfacultyDetails_URL(studSessionId,studSemesterType, studBranchStandardId, studCenterCode);
                    Log.d(TAG, "Subject and faculty List : " + url);

                    StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            Log.d(TAG, response.toString());
                            try {

                                Gson gson = new Gson();
                                teachPlanSubjModel = gson.fromJson(response, TeachPlanSubjModel.class);
                                if (teachPlanSubjModel.getStatus() == 1) {
                                    teachPlanSubjArrayList = (ArrayList<TeachPlanSubjModel.DataBean>) teachPlanSubjModel.getData();
                                    if (teachPlanSubjArrayList != null) {
                                        teachPlanSubjAdapter = new TeachPlanSubjAdapter(getActivity(), teachPlanSubjArrayList);
                                        Intent intent = new Intent(getActivity(), TeachPlanSubjActivity.class);
                                        startActivity(intent);
                                        fragSyllabus.dismiss();
                                        getActivity().finish();
                                    } else if (teachPlanSubjArrayList == null) {
                                        Snackbar.make(lytFragSyllabus, "Record not available.", Snackbar.LENGTH_LONG)
                                                .setAction("Action", null).show();
                                    }
                                } else if (teachPlanSubjModel.getStatus() == 0) {
                                    Snackbar.make(lytFragSyllabus, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
                                            .setAction("Action", null).show();
                                }
                                prgBarSyllbus.setVisibility(View.GONE);
                            } catch (Exception e) {

                                Snackbar.make(lytFragSyllabus, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
                                        .setAction("Action", null).show();

                                Log.d(TAG, "Error : " + e.getMessage());

                                prgBarSyllbus.setVisibility(View.GONE);

                            }

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e(TAG, "Error: " + error.getMessage());
                            handleVolleyError(error);
                            prgBarSyllbus.setVisibility(View.GONE);

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
        });

        lytSubjWiseFac.setOnClickListener(new View.OnClickListener() {
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
                    lytSubjWiseFac.setEnabled(false);
                    prgBarSyllbus.setVisibility(View.VISIBLE);

                    String url = pref.getURL() + URLEndPoints.GetSubjectWiseFaculty_URL;
                    Log.d(TAG, "Subject and faculty List : " + url);

                    StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.d(TAG, response.toString());

                            Gson gson = new Gson();
                            subjectwiseFacultyModel = gson.fromJson(response, SubjectwiseFacultyModel.class);
                            if (subjectwiseFacultyModel.getStatus() == 1) {

                                subjectwiseFacArrayList = (ArrayList<SubjectwiseFacultyModel.DataBean>) subjectwiseFacultyModel.getData();
                                if (subjectwiseFacArrayList != null) {

                                    subjectwiseFacAdapter = new SubjectwiseFacAdapter(getActivity(), subjectwiseFacArrayList);
                                    Intent intent = new Intent(getActivity(), SubjectwiseFacultyActivity.class);
                                    startActivity(intent);
                                    fragSyllabus.dismiss();
                                    getActivity().finish();

                                } else {
                                    Snackbar.make(lytFragSyllabus, "Record not found.", Snackbar.LENGTH_LONG)
                                            .setAction("Action", null).show();
                                }

                            } else if (subjectwiseFacultyModel.getStatus() == 0) {
                                Snackbar.make(lytFragSyllabus, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
                                        .setAction("Action", null).show();
                            }

                            prgBarSyllbus.setVisibility(View.GONE);
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            handleVolleyError(error);

                            prgBarSyllbus.setVisibility(View.GONE);
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

            }
        });


        lytUnivSyll.setOnClickListener(new View.OnClickListener() {
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
                    lytUnivSyll.setEnabled(false);

                    prgBarSyllbus.setVisibility(View.VISIBLE);

                    String url = pref.getURL() + URLEndPoints.GetUniversitySyllabusList_URL(studSessionId, studBranch_Standard_GRP_ID, studSemesterType);
                    Log.d(TAG, "University List : " + url);

                    StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            Log.d(TAG, response.toString());
                            try {

                                Gson gson = new Gson();
                                univerSubjModel = gson.fromJson(response, UnviSubjModel.class);
                                if (univerSubjModel.getStatus() == 1) {

                                    univSubjArrayList = (ArrayList<UnviSubjModel.DataBean>) univerSubjModel.getData();
                                    if (univSubjArrayList != null) {

                                        univerSubjAdapter = new UniverSubjAdapter(getActivity(), univSubjArrayList);
                                        Intent intent = new Intent(getActivity(), UnivSubjectActivity.class);
                                        startActivity(intent);
                                        fragSyllabus.dismiss();
                                        getActivity().finish();

                                    } else if (univSubjArrayList == null) {
                                        Snackbar.make(lytFragSyllabus, "Record not available.", Snackbar.LENGTH_LONG)
                                                .setAction("Action", null).show();
                                    }

                                } else if (univerSubjModel.getStatus() == 0) {
                                    Snackbar.make(lytFragSyllabus, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
                                            .setAction("Action", null).show();
                                }
                                prgBarSyllbus.setVisibility(View.GONE);

                            } catch (Exception e) {

                                Snackbar.make(lytFragSyllabus, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
                                        .setAction("Action", null).show();

                                Log.d(TAG, "Error : " + e.getMessage());

                                prgBarSyllbus.setVisibility(View.GONE);

                            }

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                           handleVolleyError(error);
                            prgBarSyllbus.setVisibility(View.GONE);

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
        });

        lytSyllStatus.setOnClickListener(new View.OnClickListener() {
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
                    prgBarSyllbus.setVisibility(View.VISIBLE);
                    String url = pref.getURL() + URLEndPoints.GetSyllabusComplStatus_URL(studSessionId, studSemesterType, studBranchStandardId, studCenterCode);
                    Log.d(TAG, "Syllabus Status List : " + url);

                    StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            Log.d(TAG, response.toString());
                            try {
                                Gson gson = new Gson();
                                syllStatusModel = gson.fromJson(response, SyllStatusModel.class);
                                if (syllStatusModel.getStatus() == 1) {

                                    syllStatusArrayList = (ArrayList<SyllStatusModel.DataBean>) syllStatusModel.getData();
                                    if (syllStatusArrayList != null) {

                                        Intent intent = new Intent(getActivity(), SyllStatusActivity.class);
                                        startActivity(intent);
                                        fragSyllabus.dismiss();

                                        getActivity().finish();

                                    } else if (syllStatusArrayList == null) {
                                        Snackbar.make(lytFragSyllabus, "Record not available.", Snackbar.LENGTH_LONG)
                                                .setAction("Action", null).show();
                                    }

                                } else if (syllStatusModel.getStatus() == 0) {
                                    Snackbar.make(lytFragSyllabus, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
                                            .setAction("Action", null).show();
                                }
                                prgBarSyllbus.setVisibility(View.GONE);

                            } catch (Exception e) {

                                Snackbar.make(lytFragSyllabus, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
                                        .setAction("Action", null).show();

                                Log.d(TAG, "Error : " + e.getMessage());

                                prgBarSyllbus.setVisibility(View.GONE);

                            }

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            handleVolleyError(error);
                            prgBarSyllbus.setVisibility(View.GONE);

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
        });

        return view;
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
