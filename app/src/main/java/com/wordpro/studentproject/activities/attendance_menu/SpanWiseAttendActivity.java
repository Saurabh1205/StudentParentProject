package com.wordpro.studentproject.activities.attendance_menu;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
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
import android.widget.DatePicker;
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
import com.wordpro.studentproject.adapter.SpanListAdapter;
import com.wordpro.studentproject.app.MyApplication;
import com.wordpro.studentproject.model.SpanwiseModel;
import com.wordpro.studentproject.webConfig.URLEndPoints;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
import static com.wordpro.studentproject.network.Network.isNetworkAvailable;

public class SpanWiseAttendActivity extends Activity {

    private static String TAG = DayWiseAttendActivity.class.getSimpleName();
    TextView txtStudName, txtYear, txtSem, txtSection,txtSpan;
    TextView txtFromDt, txtUptoDt;
    LinearLayout lytFromDt, lytUptoDate;
    DatePicker datePicker;
    ProgressBar prgBrSpanAttnd;
    RelativeLayout lytSpanAttnd;
    public static SpanwiseModel spanwiseModel;
    public static ArrayList<SpanwiseModel.DataBean> spanArrayList;
    public static SpanListAdapter spanListAdapter;
    RecyclerView spanRecyVw;
    String spanFromDt,spanUptoDt,type;
    TextView txtMsg;
    LinearLayout lytHeading;
    Typeface typefaceHeading,typefaceContent;

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

            setContentView(R.layout.activity_span_wise_attend);

            typefaceHeading = Typeface.createFromAsset(getAssets(), "font/Poppins-BoldItalic.otf");
            typefaceContent = Typeface.createFromAsset(getAssets(), "font/Poppins-Medium.otf");

            Intent intent = getIntent();
            Bundle extras = intent.getExtras();
            spanFromDt = extras.getString("spanFromDt");
            spanUptoDt=extras.getString("spanUptoDt");
            type=extras.getString("type");

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
            txtSpan=(TextView)findViewById(R.id.txtSpan);
            txtSpan.setTypeface(typefaceHeading);
            prgBrSpanAttnd=(ProgressBar)findViewById(R.id.prgBrSpanAttnd);
            lytSpanAttnd=(RelativeLayout)findViewById(R.id.lytSpanAttnd);
            spanRecyVw=(RecyclerView)findViewById(R.id.spanRecyVw);
            spanRecyVw.setLayoutManager(new LinearLayoutManager(SpanWiseAttendActivity.this));

            txtFromDt = (TextView) findViewById(R.id.txtFromDt);
            txtFromDt.setTypeface(typefaceContent);
            txtFromDt.setText(spanFromDt);
            txtUptoDt = (TextView) findViewById(R.id.txtUptoDt);
            txtUptoDt.setTypeface(typefaceContent);
            txtUptoDt.setText(spanUptoDt);
            txtMsg=(TextView)findViewById(R.id.txtMsg);
            txtMsg.setTypeface(typefaceContent);
            lytHeading=(LinearLayout)findViewById(R.id.lytHeading);

            if(type.equalsIgnoreCase("day")){
                getDatesValid();
            }

            lytFromDt = (LinearLayout) findViewById(R.id.lytFromDt);
            lytFromDt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    txtUptoDt.setText("select upto date");

                    Calendar ref = Calendar.getInstance();
                    int date = ref.get(Calendar.DAY_OF_MONTH);
                    int month = ref.get(Calendar.MONTH);
                    int year = ref.get(Calendar.YEAR);
                    // Providing Current date  to Date picker Dialog

                    DatePickerDialog datePickerDialog = new DatePickerDialog(SpanWiseAttendActivity.this, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {

                            String selected_date = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;

                            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                            Date date = new Date();
                            String currentDate=formatter.format(date);


                            Date selDate = null,currntDt=null;
                            SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
                            try {
                                selDate  = format1.parse(selected_date);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            try {
                                currntDt  = format1.parse(currentDate);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }

                            if(selDate.after(currntDt)){
                                Snackbar.make(lytSpanAttnd, "Select current date or date before current date.", Snackbar.LENGTH_LONG)
                                        .setAction("Action", null).show();
                            }else{
                                txtFromDt.setText(selected_date);
                            }

                        }
                    }, year, month, date);
                    datePickerDialog.show();
                }
            });
            lytUptoDate = (LinearLayout) findViewById(R.id.lytUptoDate);
            lytUptoDate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!isNetworkAvailable(SpanWiseAttendActivity.this)) {
                        // Create an Alert Dialog
                        AlertDialog.Builder builder = new AlertDialog.Builder(SpanWiseAttendActivity.this);
                        // Set the Alert Dialog Message
                        builder.setMessage("Internet Connection Required")
                                .setCancelable(false)
                                .setPositiveButton("Retry",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog,
                                                                int id) {
                                                Toast.makeText(SpanWiseAttendActivity.this, "Please check your internt", Toast.LENGTH_SHORT).show();
                                                // Restart the Activity
                                            /*Intent intent = getIntent();
                                            finish();
                                            startActivity(intent);*/
                                            }
                                        });
                        AlertDialog alert = builder.create();
                        alert.show();
                    }else {

                        Calendar ref = Calendar.getInstance();
                        int date = ref.get(Calendar.DAY_OF_MONTH);
                        int month = ref.get(Calendar.MONTH);
                        int year = ref.get(Calendar.YEAR);
                        // Providing Current date  to Date picker Dialog

                        DatePickerDialog datePickerDialog = new DatePickerDialog(SpanWiseAttendActivity.this, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {

                                String selected_date = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                                String fromDate=txtFromDt.getText().toString();
                                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                                Date date = new Date();
                                String currentDate=formatter.format(date);


                                Date selDate = null,currntDt=null,fromDt=null;
                                SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
                                try {
                                    selDate  = format1.parse(selected_date);
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                                try {
                                    currntDt  = format1.parse(currentDate);
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                                try {
                                    fromDt  = format1.parse(fromDate);
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                                if(selDate.after(currntDt)){
                                    Snackbar.make(lytSpanAttnd, "Select current date or date before current date.", Snackbar.LENGTH_LONG)
                                            .setAction("Action", null).show();
                                }else if(selDate.before(fromDt)){
                                    Snackbar.make(lytSpanAttnd, "Upto date is greater than From date.Select upto date after from date", Snackbar.LENGTH_LONG)
                                            .setAction("Action", null).show();
                                }else{
                                    txtUptoDt.setText(selected_date);
                                    getDatesValid();
                                }

                            }
                        }, year, month, date);
                        datePickerDialog.show();
                    }
                }
            });
            datePicker = (DatePicker) findViewById(R.id.datePicker);
            datePicker.setVisibility(View.GONE);


        }

    }

    private void getDatesValid() {
        String fromDate=txtFromDt.getText().toString();
        String uptoDate=txtUptoDt.getText().toString();

        if(!fromDate.isEmpty() && fromDate.equalsIgnoreCase("select from date")){
            Snackbar.make(lytSpanAttnd, "Select from date.", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();

        }else if(!uptoDate.isEmpty() && uptoDate.equalsIgnoreCase("select upto date")){
            Snackbar.make(lytSpanAttnd, "Select upto date.", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }else if(!txtFromDt.getText().toString().isEmpty() && !txtFromDt.getText().toString().equalsIgnoreCase("select from date") && !txtUptoDt.getText().toString().isEmpty() && !txtUptoDt.getText().toString().equalsIgnoreCase("select upto date")){
            getSpanList(fromDate,uptoDate);
        }
    }

    private void getSpanList(final String fromDate, final String uptoDate) {

        prgBrSpanAttnd.setVisibility(View.VISIBLE);

        String url = NavigationActivity.BASE_URL + URLEndPoints.GETDAYWISEATTEENDACE_URL;
        Log.d(TAG, "Span-wise SelfAttendance : " + url);

        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, response.toString());
                Gson gson = new Gson();

                spanwiseModel=gson.fromJson(response,SpanwiseModel.class);
                if(spanwiseModel.getStatus()==1){
                    txtMsg.setVisibility(View.GONE);
                    spanArrayList=(ArrayList<SpanwiseModel.DataBean>)spanwiseModel.getData();
                    if(spanArrayList!=null && spanArrayList.size()!=0){
                        txtMsg.setVisibility(View.GONE);
                        spanRecyVw.setVisibility(View.VISIBLE);
                        lytHeading.setVisibility(View.VISIBLE);
                        spanListAdapter=new SpanListAdapter(SpanWiseAttendActivity.this,spanArrayList);
                        spanRecyVw.setAdapter(spanListAdapter);
                        if (spanListAdapter!=null && spanArrayList.size()!=0){
                            lytHeading.setVisibility(View.VISIBLE);
                            txtMsg.setVisibility(View.GONE);
                            spanRecyVw.setVisibility(View.VISIBLE);
                            spanListAdapter.setOnItemClickListener(new SpanListAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(View itemView, int position) {
                                    if (!isNetworkAvailable(SpanWiseAttendActivity.this)) {
                                        // Create an Alert Dialog
                                        AlertDialog.Builder builder = new AlertDialog.Builder(SpanWiseAttendActivity.this);
                                        // Set the Alert Dialog Message
                                        builder.setMessage("Internet Connection Required")
                                                .setCancelable(false)
                                                .setPositiveButton("Retry",
                                                        new DialogInterface.OnClickListener() {
                                                            public void onClick(DialogInterface dialog,
                                                                                int id) {
                                                                Toast.makeText(SpanWiseAttendActivity.this, "Please check your internt", Toast.LENGTH_SHORT).show();
                                                                // Restart the Activity
                                            /*Intent intent = getIntent();
                                            finish();
                                            startActivity(intent);*/
                                                            }
                                                        });
                                        AlertDialog alert = builder.create();
                                        alert.show();
                                    }else {
                                        String value=spanArrayList.get(position).getAttendance_Date();
                                        String[] val=value.split(" ");
                                        if(val[0]!=null){

                                            String date =val[0];
                                            date = date.replace("-", "/");
                                            String dt[] = date.split("/");

                                            String day="",month="",year="";

                                            if(BASE_URL.equalsIgnoreCase("http://117.220.228.12:80/api/")){
                                                month = dt[0];
                                                day = dt[1];
                                                year = dt[2];
                                            }else{
                                                month = dt[0];
                                                day = dt[1];
                                                year = dt[2];
                                            }

                                            String input_date = day + "/" + month + "/" + year;

                                            Intent intent=new Intent(SpanWiseAttendActivity.this,DayWiseAttendActivity.class);
                                            intent.putExtra("date",input_date);
                                            intent.putExtra("type","span");
                                            intent.putExtra("fromDate",fromDate);
                                            intent.putExtra("uptoDate",uptoDate);
                                            startActivity(intent);
                                            finish();

                                        }
                                    }

                                }
                            });
                        }else{

                            txtMsg.setVisibility(View.VISIBLE);
                            lytHeading.setVisibility(View.GONE);
                            spanRecyVw.setVisibility(View.GONE);

                        }
                    }else{
                        txtMsg.setVisibility(View.VISIBLE);
                        lytHeading.setVisibility(View.GONE);
                        spanRecyVw.setVisibility(View.GONE);

                    }
                }else if(spanwiseModel.getStatus()==0){
                    Snackbar.make(lytSpanAttnd, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
                prgBrSpanAttnd.setVisibility(View.GONE);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                handleVolleyError(error);
                prgBrSpanAttnd.setVisibility(View.GONE);
            }
        }){
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
                params.put("start_DATE", fromDate);
                params.put("end_DATE", uptoDate);
                params.put("P_REPORT_PATTERN", "ATTENDANCE_ABSTRACT");//{end_DATE=14/2/2020, _centercode=JDIET_SC, P_BRANCH_STANDARD_ID=12,
                // P_REPORT_PATTERN=ATTENDANCE_ABSTRACT, PI_SESSION_ID=29, p_Subject_Detail_Id=0,
                // AcadSemisterType=E, P_STUDENT_ID=1032180262, start_DATE=9/2/2020, _iPI_APPLICABLE_NUMBER=0}
                Log.e(TAG, "Posting params: " + params.toString());

//                [{"key":"_centercode","value":"ENGG_SC","description":""},
//                {"key":"PI_SESSION_ID","value":"18","description":""},
//                {"key":"P_BRANCH_STANDARD_ID","value":"24","description":""},
//                {"key":"AcadSemisterType","value":"E"},
//                {"key":"p_Subject_Detail_Id","value":"0","description":""},
//                {"key":"_iPI_APPLICABLE_NUMBER","value":"0","description":""},
//                {"key":"P_STUDENT_ID","value":"1032160253","description":""},
//                {"key":"start_DATE","value":"18/12/2017","description":""},
//                {"key":"end_DATE","value":"18/12/2017","description":""},
//                {"key":"P_REPORT_PATTERN","value":"ATTENDANCE_DETAILS","description":""}]

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
        Intent intent=new Intent(SpanWiseAttendActivity.this,NavigationActivity.class);
        intent.putExtra("activity","SpanWiseAttendActivity");
        startActivity(intent);
        finish();
    }
}

