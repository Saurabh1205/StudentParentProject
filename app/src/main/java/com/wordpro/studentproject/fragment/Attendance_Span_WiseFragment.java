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
import com.wordpro.studentproject.activities.attendance_menu.DayWiseAttendActivity;
import com.wordpro.studentproject.activities.attendance_menu.SpanWiseAttendActivity;
import com.wordpro.studentproject.activities.attendance_menu.SubjectWiseAttendActivity;
import com.wordpro.studentproject.adapter.DayWiseAttendanceAdpter;
import com.wordpro.studentproject.adapter.SpanDayWiseAttendanceAdpter;
import com.wordpro.studentproject.adapter.SpanListAdapter;
import com.wordpro.studentproject.adapter.SubjectWiseAttendanceAdpter;
import com.wordpro.studentproject.adapter.TeachPlanSubjAdapter;
import com.wordpro.studentproject.app.MyApplication;
import com.wordpro.studentproject.helper.PrefManager;
import com.wordpro.studentproject.model.SpanwiseModel;
import com.wordpro.studentproject.model.SubjectwiseFacultyModel;
import com.wordpro.studentproject.model.TeachPlanSubjModel;
import com.wordpro.studentproject.utils.UtilityClass;
import com.wordpro.studentproject.webConfig.URLEndPoints;

import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.wordpro.studentproject.R.id.frame_container_Action;
import static com.wordpro.studentproject.R.id.show_search_startdate;
import static com.wordpro.studentproject.activities.NavigationActivity.BASE_URL;
import static com.wordpro.studentproject.activities.NavigationActivity.fragAttendance;
import static com.wordpro.studentproject.activities.NavigationActivity.studBranchStandardId;
import static com.wordpro.studentproject.activities.NavigationActivity.studCenterCode;
import static com.wordpro.studentproject.activities.NavigationActivity.studSemesterType;
import static com.wordpro.studentproject.activities.NavigationActivity.studSessionId;
import static com.wordpro.studentproject.activities.NavigationActivity.stud_main_semester_mst_id;
import static com.wordpro.studentproject.activities.NavigationActivity.student_id;
import static com.wordpro.studentproject.network.Network.isNetworkAvailable;


public class Attendance_Span_WiseFragment extends Fragment {
    private static String TAG = Attendance_Span_WiseFragment.class.getSimpleName();
    private LinearLayout  main_linear_view;
    Fragment me=this;
    Attendance_Span_WiseFragment actionNewsFragment;
    private TextView  show_search_startdate,show_search_enddate;
    private ImageView imageView_startDate,imageView_endDate;
    private LinearLayout lay_day_wise,lay_sub_wise,lay_span_wise,lay_net_wise;
    String studCenterCode,studDepartmentNumber,student_id,studBranchStandardId;
    ProgressDialog mProgressDialog;
    private PrefManager pref;
    ProgressBar progAttndFrgmt;
    private RelativeLayout  lytAttndence;
    private RecyclerView spanWiseList;
    private CardView card_view,card_view1;
    public static SpanwiseModel spanwiseModel;
    public static ArrayList<SpanwiseModel.DataBean> spanArrayList;
    private SpanDayWiseAttendanceAdpter spanDayWiseAttendanceAdpter;
    public Fragment fragment;
    private DatePicker  datePicker;
    private  String NameOfParam;
    private String StartDate,EndDate;

    private UtilityClass utilityClassObj;
    public Attendance_Span_WiseFragment() {
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
        try {

            //NameOfParam=getArguments().getString("Subject_Wise");

        }catch (Exception e){
            e.printStackTrace();
        }
        progAttndFrgmt = (ProgressBar) view.findViewById(R.id.progAttndFrgmt);
        lytAttndence = (RelativeLayout)view.findViewById(R.id.lytAttndence);
        show_search_startdate =(TextView) view.findViewById(R.id.show_search_startdate);
        show_search_enddate=(TextView) view.findViewById(R.id.show_search_enddate);


        spanWiseList= (RecyclerView) view.findViewById(R.id.spanWiseList);
        imageView_startDate = (ImageView) view.findViewById(R.id.imageView_startDate);
        imageView_endDate= (ImageView) view.findViewById(R.id.imageView_endDate);
        datePicker = (DatePicker)view. findViewById(R.id.datePicker);
        card_view = (CardView) view. findViewById(R.id.card_view);
        card_view1 = (CardView) view. findViewById(R.id.card_view1);

        if (URLEndPoints.ConstanceLoginUser.equalsIgnoreCase("Student")){
           // belowId.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.text_blue));
            show_search_startdate.setTextColor(ContextCompat.getColor(getActivity(), R.color.text_blue));
            show_search_enddate.setTextColor(ContextCompat.getColor(getActivity(), R.color.text_blue));
        }else {
            show_search_startdate.setTextColor(ContextCompat.getColor(getActivity(), R.color.present));
            show_search_enddate.setTextColor(ContextCompat.getColor(getActivity(), R.color.present));
        }


        clickEvent();
        if (show_search_startdate.getText().toString().equalsIgnoreCase("") && show_search_enddate.getText().toString().equalsIgnoreCase("")){
            show_search_startdate.setText( URLEndPoints.Constance_SELECTSTART_DATE);
            //show_search_startdate.setTextColor(ContextCompat.getColor(getActivity(), R.color.text_blue));
            show_search_enddate.setText( URLEndPoints.Constance_SELECTEND_DATE);
            //show_search_enddate.setTextColor(ContextCompat.getColor(getActivity(), R.color.text_blue));
        }

        getDatesValid();




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

    private void  clickEvent(){

        imageView_startDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                show_search_enddate.setText("select upto date");
                //show_search_enddate.setTextColor(ContextCompat.getColor(getActivity(), R.color.text_blue));
                Calendar ref = Calendar.getInstance();
                int date = ref.get(Calendar.DAY_OF_MONTH);
                int month = ref.get(Calendar.MONTH);
                int year = ref.get(Calendar.YEAR);
                // Providing Current date  to Date picker Dialog

                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
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
                            Snackbar.make(lytAttndence, "Select current date or date before current date.", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        }else{
                            String dd =selected_date;
                            StartDate =selected_date;
                            URLEndPoints.ConstanceSTAETDATE =selected_date;
                            Date show = null;
                            SimpleDateFormat formatter1 = new SimpleDateFormat("dd/MM/yyyy");
                            try {
                                show = formatter1.parse(dd);
                                formatter1= new SimpleDateFormat("dd MMM yyyy");
                                dd = formatter1.format(show);
                                System.out.println(date);

                               // String v =show.toString();
                                URLEndPoints.Constance_SELECTSTART_DATE =dd;
                                show_search_startdate.setText( URLEndPoints.Constance_SELECTSTART_DATE);
                               // show_search_startdate.setTextColor(ContextCompat.getColor(getActivity(), R.color.text_blue));
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }


                           // show_search_startdate.setText(selected_date);
                        }

                    }
                }, year, month, date);
                datePickerDialog.show();
            }
        });

        imageView_endDate.setOnClickListener(new View.OnClickListener() {
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

                    Calendar ref = Calendar.getInstance();
                    int date = ref.get(Calendar.DAY_OF_MONTH);
                    int month = ref.get(Calendar.MONTH);
                    int year = ref.get(Calendar.YEAR);
                    // Providing Current date  to Date picker Dialog

                    DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {

                            String selected_date = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                            String fromDate=URLEndPoints.ConstanceSTAETDATE;
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
                            try {
                                if(selDate.after(currntDt)){
                                    Snackbar.make(lytAttndence, "Select current date or date before current date.", Snackbar.LENGTH_LONG)
                                            .setAction("Action", null).show();
                                }else if(selDate.before(fromDt)){
                                    Snackbar.make(lytAttndence, "Upto date is greater than From date.Select upto date after from date", Snackbar.LENGTH_LONG)
                                            .setAction("Action", null).show();
                                }else{
                                    String dd =selected_date;
                                    EndDate =selected_date;
                                    URLEndPoints.ConstanceENDDATE =selected_date;
                                    Date show = null;
                                    SimpleDateFormat formatter1 = new SimpleDateFormat("dd/MM/yyyy");
                                    try {
                                        show = formatter1.parse(dd);
                                        formatter1= new SimpleDateFormat("dd MMM yyyy");
                                        dd = formatter1.format(show);
                                        System.out.println(date);

                                        URLEndPoints.Constance_SELECTEND_DATE =dd;
                                        show_search_enddate.setText( URLEndPoints.Constance_SELECTEND_DATE);
                                        show_search_enddate.setText(dd);
                                        show_search_enddate.setTextColor(ContextCompat.getColor(getActivity(), R.color.text_blue));
                                    } catch (ParseException e) {
                                        e.printStackTrace();
                                    }


                                    // show_search_enddate.setText(selected_date);
                                    getDatesValid();
                                }
                            }catch (Exception e){
                                e.printStackTrace();
                            }


                        }
                    }, year, month, date);
                    datePickerDialog.show();
                }
            }
        });

        //datePicker.setVisibility(View.GONE);
    }
    private void getDatesValid() {
        try {
            String fromDate=URLEndPoints.ConstanceSTAETDATE;
            String uptoDate=URLEndPoints.ConstanceENDDATE;

            if(!fromDate.isEmpty() && fromDate.equalsIgnoreCase("select from date")){
                Snackbar.make(lytAttndence, "Select from date.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }else if(!uptoDate.isEmpty() && uptoDate.equalsIgnoreCase("select upto date")){
                Snackbar.make(lytAttndence, "Select upto date.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }else if(!fromDate.isEmpty( ) && !uptoDate.isEmpty() ){

                getSpanList(fromDate,uptoDate);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private void getSpanList(final String fromDate, final String uptoDate) {

        utilityClassObj.startLoader(getActivity(),R.drawable.image_for_rotation);

        String url = NavigationActivity.BASE_URL + URLEndPoints.GETDAYWISEATTEENDACE_URL;
        Log.d(TAG, "Span-wise SelfAttendance : " + url);

        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, response.toString());
                Gson gson = new Gson();

                spanwiseModel=gson.fromJson(response, SpanwiseModel.class);
                if(spanwiseModel.getStatus()==1){
                    utilityClassObj.stopLoader();
                    spanArrayList=(ArrayList<SpanwiseModel.DataBean>)spanwiseModel.getData();
                    if(spanArrayList!=null && spanArrayList.size()!=0){
                        spanDayWiseAttendanceAdpter = new SpanDayWiseAttendanceAdpter(getActivity(),spanArrayList);
                        spanWiseList.setLayoutManager(new LinearLayoutManager(getActivity()));
                        spanWiseList.setAdapter(spanDayWiseAttendanceAdpter);

                        spanDayWiseAttendanceAdpter.setOnItemClickListener(new SpanDayWiseAttendanceAdpter.OnItemClickListener() {
                            @Override
                            public void onItemClick(View itemView, int position) {

                                String ShowDate = spanArrayList.get(position).getCommonDate();
                                String SelectDate = spanArrayList.get(position).getAttendance_Date();


                               /* SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                                Date date = new Date();
                                System.out.println(dateFormat.format(date));*/

                                SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
                                String dateInString = SelectDate;
                                Date date = null;
                                try {
                                    date = sdf.parse(dateInString);
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                                System.out.println(date);
                                DateFormat da = new SimpleDateFormat("dd/MM/yyyy");
                                DateFormat t = new SimpleDateFormat("hh:mm:ss a");

                                System.out.println("Date: " + da.format(date));
                                System.out.println("Time: " + t.format(date));

                                System.out.println(date);

                                String aa =  da.format(date);
                                String bb =t.format(date);

                             /*   Date d = f.parse(URLEndPoints.Constance_SUB_NAME);
                                DateFormat date = new SimpleDateFormat("dd/MM/yyyy");
                                DateFormat time = new SimpleDateFormat("hh:mm:ss a");

                                System.out.println("Date: " + date.format(d));
                                System.out.println("Time: " + time.format(d));*/


                                URLEndPoints.Constance_SUB_ID =ShowDate;
                                URLEndPoints.Constance_SUB_NAME =aa;

                                fragment = new Att_Span_Wise_Details_Fragment();
                                Bundle bundle=new Bundle();
                                bundle.putString("Span_Wise", "Span_wise_att");
                                bundle.putString("ShowDate", ShowDate);
                                bundle.putString("SelectDate", SelectDate);

                                loadFragmentMain(fragment);
                            }
                        });


                       // spanListAdapter=new SpanListAdapter(SpanWiseAttendActivity.this,spanArrayList);
                       // spanRecyVw.setAdapter(spanListAdapter);
                        /*if (spanListAdapter!=null && spanArrayList.size()!=0){
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
                                            *//*Intent intent = getIntent();
                                            finish();
                                            startActivity(intent);*//*
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

                                           *//* Intent intent=new Intent(SpanWiseAttendActivity.this, DayWiseAttendActivity.class);
                                            intent.putExtra("date",input_date);
                                            intent.putExtra("type","span");
                                            intent.putExtra("fromDate",fromDate);
                                            intent.putExtra("uptoDate",uptoDate);
                                            startActivity(intent);
                                            finish();*//*

                                        }
                                    }

                                }
                            });
                        }else{



                        }*/
                    }else{


                    }
                }else if(spanwiseModel.getStatus()==0){
                    Snackbar.make(lytAttndence, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
                utilityClassObj.stopLoader();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                handleVolleyError(error);
                utilityClassObj.stopLoader();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("_centercode", URLEndPoints.Constance_StudentCenterCode);
                params.put("PI_SESSION_ID", URLEndPoints.Constance_StudSessionId);
                params.put("P_BRANCH_STANDARD_ID", URLEndPoints.Constance_StudBranchStandardId);
                params.put("AcadSemisterType", URLEndPoints.Constance_StudSemesterType);
                params.put("p_Subject_Detail_Id", "0");
                params.put("_iPI_APPLICABLE_NUMBER", "0");
                params.put("P_STUDENT_ID", URLEndPoints.Constance_StudentID);
                params.put("start_DATE", fromDate);
                params.put("end_DATE", uptoDate);
                params.put("P_REPORT_PATTERN", "ATTENDANCE_ABSTRACT");
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

        progAttndFrgmt.setVisibility(View.VISIBLE);
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

                        String SESSION_ID = responseObj.getString("SESSION_ID");
                        String PERIOD_START_DATE = responseObj.getString("PERIOD_START_DATE");
                        String PERIOD_END_DATE = responseObj.getString("PERIOD_END_DATE");

                        if (PERIOD_START_DATE != null && PERIOD_END_DATE != null) {
                            if (attendance_type.equalsIgnoreCase("NET_ATTENDANCE")) {
                                //  getNetAttendanceData(PERIOD_START_DATE, PERIOD_END_DATE);
                            } else if (attendance_type.equalsIgnoreCase("DAY_WISE_ATTEND")) {


                            } else if (attendance_type.equalsIgnoreCase("SPAN_WISE_ATTEND")) {

                            } else if (attendance_type.equalsIgnoreCase("SUBJ_WISE_ATTEND")) {
                                getDatesValid();
                            }

                        } else {
                            Snackbar snackbar = Snackbar
                                    .make(lytAttndence, "Record not found.", Snackbar.LENGTH_LONG);
                            snackbar.show();

                            progAttndFrgmt.setVisibility(View.GONE);

                        }

                    } else if (status == 0) {
                        Snackbar.make(lytAttndence, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                        progAttndFrgmt.setVisibility(View.GONE);
                    }


                } catch (Exception e) {

                    Snackbar.make(lytAttndence, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
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
    private void loadFragmentMain(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(frame_container_Action, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}
