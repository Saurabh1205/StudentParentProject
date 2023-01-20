package com.wordpro.studentproject.activities.attendance_menu;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.design.widget.Snackbar;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
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
import com.wordpro.studentproject.adapter.DayWiseAttendApdater;
import com.wordpro.studentproject.adapter.IndicatorAdapter;
import com.wordpro.studentproject.app.MyApplication;
import com.wordpro.studentproject.model.DaywiseAttndModel;
import com.wordpro.studentproject.webConfig.URLEndPoints;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.wordpro.studentproject.activities.NavigationActivity.studAcadSessName;
import static com.wordpro.studentproject.activities.NavigationActivity.studBranchStandDesc;
import static com.wordpro.studentproject.activities.NavigationActivity.studBranchStandardId;
import static com.wordpro.studentproject.activities.NavigationActivity.studCenterCode;
import static com.wordpro.studentproject.activities.NavigationActivity.studMainSemName;
import static com.wordpro.studentproject.activities.NavigationActivity.studSemesterType;
import static com.wordpro.studentproject.activities.NavigationActivity.studSessionId;
import static com.wordpro.studentproject.activities.NavigationActivity.studentName;
import static com.wordpro.studentproject.activities.NavigationActivity.student_id;
import static com.wordpro.studentproject.network.Network.isNetworkAvailable;

public class DayWiseAttendActivity extends Activity {

    private static String TAG = DayWiseAttendActivity.class.getSimpleName();
    TextView txtStudName, txtYear, txtSem, txtSection,txtDay;
    RecyclerView recyViewIndicators;
    List<String> statusList;
    IndicatorAdapter indicatorAdapter;
    ProgressBar prgBrAttnd;
    RelativeLayout lytAttndence;
    TextView txtDate;
    ImageView imgDate;
    DatePicker datePicker;
    String currentDate;
    RecyclerView daywiseRecyVw;
    String date, type, spanFromDt, spanUptoDt;
    //Day wise Attendance Model
    public static DaywiseAttndModel daywiseAttndModel;
    public static ArrayList<DaywiseAttndModel.DataBean> daywiseArrayList;
    DayWiseAttendApdater dayWiseAttendApdater;
    LinearLayout lytDate;

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
            setContentView(R.layout.activity_day_wise_attend);
            Intent intent = getIntent();
            Bundle extras = intent.getExtras();
            date = extras.getString("date");
            type = extras.getString("type");
            spanFromDt = extras.getString("fromDate");
            spanUptoDt = extras.getString("uptoDate");
            Typeface typefaceHeading = Typeface.createFromAsset(getAssets(), "font/Poppins-BoldItalic.otf");
            Typeface typefaceContent = Typeface.createFromAsset(getAssets(), "font/Poppins-Medium.otf");
            txtDay=(TextView)findViewById(R.id.txtDay);
            txtDay.setTypeface(typefaceHeading);
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
            prgBrAttnd = (ProgressBar) findViewById(R.id.prgBrAttnd);
            lytAttndence = (RelativeLayout) findViewById(R.id.lytAttndence);
            txtDate = (TextView) findViewById(R.id.txtDate);
            txtDate.setTypeface(typefaceContent);
            txtDate.setText(date);
            daywiseRecyVw = (RecyclerView) findViewById(R.id.daywiseRecyVw);
            daywiseRecyVw.setLayoutManager(new LinearLayoutManager(DayWiseAttendActivity.this));
            imgDate = (ImageView) findViewById(R.id.imgDate);
            datePicker = (DatePicker) findViewById(R.id.datePicker);
            datePicker.setVisibility(View.INVISIBLE);
            lytDate = (LinearLayout) findViewById(R.id.lytDate);
            if (!date.equalsIgnoreCase("SELECT DATE")) {
                getDayWiseAttend(date);
            }

            Date todayDate = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            try {
                currentDate = String.valueOf(sdf.parse(String.valueOf(todayDate)));
                txtDate.setText(currentDate);
                getDayWiseAttend(currentDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            statusList = new ArrayList<>();
            statusList.add("Present");
            statusList.add("Absent");
            statusList.add("College Work");
            statusList.add("Leave");
            statusList.add("Faculty Leave");
            statusList.add("Class Absenteeism");
            statusList.add("College Event");
            statusList.add("Holiday");
            statusList.add("Not Updated");

            recyViewIndicators = (RecyclerView) findViewById(R.id.recyViewIndicators);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(DayWiseAttendActivity.this, LinearLayoutManager.HORIZONTAL, false);
            recyViewIndicators.setLayoutManager(layoutManager);
            recyViewIndicators.setItemAnimator(new DefaultItemAnimator());
            recyViewIndicators.addItemDecoration(new DividerItemDecoration(DayWiseAttendActivity.this, LinearLayoutManager.VERTICAL));
            indicatorAdapter = new IndicatorAdapter(DayWiseAttendActivity.this, statusList);
            recyViewIndicators.setAdapter(indicatorAdapter);


            lytDate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!isNetworkAvailable(DayWiseAttendActivity.this)) {
                        // Create an Alert Dialog
                        AlertDialog.Builder builder = new AlertDialog.Builder(DayWiseAttendActivity.this);
                        // Set the Alert Dialog Message
                        builder.setMessage("Internet Connection Required")
                                .setCancelable(false)
                                .setPositiveButton("Retry",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog,
                                                                int id) {
                                                Toast.makeText(DayWiseAttendActivity.this, "Please check your internt", Toast.LENGTH_SHORT).show();
                                                // Restart the Activity
                                            /*Intent intent = getIntent();
                                            finish();
                                            startActivity(intent);*/
                                            }
                                        });
                        AlertDialog alert = builder.create();
                        alert.show();
                    }
                    else {

                        Calendar ref = Calendar.getInstance();
                        int date = ref.get(Calendar.DAY_OF_MONTH);
                        int month = ref.get(Calendar.MONTH);
                        int year = ref.get(Calendar.YEAR);
                        // Providing Current date  to Date picker Dialog

                        DatePickerDialog datePickerDialog = new DatePickerDialog(DayWiseAttendActivity.this, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {

                                String selected_date = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;

                                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                                Date date = new Date();
                                String currentDate = formatter.format(date);


                                Date selDate = null, currntDt = null;
                                SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
                                try {
                                    selDate = format1.parse(selected_date);
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                                try {
                                    currntDt = format1.parse(currentDate);
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }

                                if (selDate.after(currntDt)) {
                                    Snackbar.make(lytAttndence, "Select current date or date before current date.", Snackbar.LENGTH_LONG)
                                            .setAction("Action", null).show();
                                } else {
                                    txtDate.setText(selected_date);
                                    getDayWiseAttend(selected_date);
                                }
                            }
                        }, year, month, date);
                        datePickerDialog.show();
                    }
                }
            });

        }
    }

    private void getDayWiseAttend(final String period_start_date) {

        prgBrAttnd.setVisibility(View.VISIBLE);
        String url = NavigationActivity.BASE_URL + URLEndPoints.GETDAYWISEATTEENDACE_URL;
        Log.d(TAG, "Day-wise SelfAttendance : " + url);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, response.toString());
                Gson gson = new Gson();
                daywiseAttndModel = gson.fromJson(response, DaywiseAttndModel.class);
                if (daywiseAttndModel.getStatus() == 1) {
                    daywiseArrayList = (ArrayList<DaywiseAttndModel.DataBean>) daywiseAttndModel.getData();

                    if (daywiseArrayList != null && daywiseArrayList.size() > 0) {
                        daywiseRecyVw.setVisibility(View.VISIBLE);
                        dayWiseAttendApdater = new DayWiseAttendApdater(DayWiseAttendActivity.this, daywiseArrayList);
                        daywiseRecyVw.setAdapter(dayWiseAttendApdater);
                    } else {
                        daywiseRecyVw.setVisibility(View.GONE);
                        Snackbar.make(lytAttndence, "Record not available.", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }
                } else if (daywiseAttndModel.getStatus() == 0) {
                    Snackbar.make(lytAttndence, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
                prgBrAttnd.setVisibility(View.GONE);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                handleVolleyError(error);
                prgBrAttnd.setVisibility(View.GONE);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("_centercode", studCenterCode);
                params.put("PI_SESSION_ID", studSessionId);
                params.put("P_BRANCH_STANDARD_ID", studBranchStandardId);
                params.put("AcadSemisterType", studSemesterType);
                params.put("p_Subject_Detail_Id", "0");
                params.put("_iPI_APPLICABLE_NUMBER", "0");
                params.put("P_STUDENT_ID", student_id);
                params.put("start_DATE", period_start_date);
                params.put("end_DATE", period_start_date);
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

        if (type.equalsIgnoreCase("day")) {
            Intent intent = new Intent(DayWiseAttendActivity.this, NavigationActivity.class);
            intent.putExtra("activity","DayWiseAttendActivity");
            startActivity(intent);
            finish();
        } else if (type.equalsIgnoreCase("span")) {
            Intent intent = new Intent(DayWiseAttendActivity.this, SpanWiseAttendActivity.class);
            intent.putExtra("spanFromDt", spanFromDt);
            intent.putExtra("spanUptoDt", spanUptoDt);
            intent.putExtra("type", "day");
            startActivity(intent);
            finish();
        }

    }

}
