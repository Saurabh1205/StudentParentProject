package com.wordpro.studentproject.fragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
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
import com.wordpro.studentproject.activities.NewRightActionActivity;
import com.wordpro.studentproject.adapter.DayWiseAttendApdater;
import com.wordpro.studentproject.adapter.DayWiseAttendanceAdpter;
import com.wordpro.studentproject.adapter.New_NewsDetailsAdpter;
import com.wordpro.studentproject.app.MyApplication;
import com.wordpro.studentproject.helper.PrefManager;
import com.wordpro.studentproject.model.DaywiseAttndModel;
import com.wordpro.studentproject.model.NoticesModel;
import com.wordpro.studentproject.utils.UtilityClass;
import com.wordpro.studentproject.webConfig.URLEndPoints;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.wordpro.studentproject.network.Network.isNetworkAvailable;

public class AttendanceDynamicFragmen extends Fragment {
    View view;
    EditText edit_search;
    private RecyclerView dayWiseList;
    private ImageView  imageView_search,imageView_Date;
    private TextView  show_search_date;
    private DatePicker  datePicker;
    private TextView  txt_present,txt_name,txt_Theory,txt_Regular,txt_date,wise_attendance,wise_Value,userName,txt_basic;
    private New_NewsDetailsAdpter new_newsDetailsAdpter;
    public ArrayList<NoticesModel.DataBean> newsArrayList1;
    ProgressDialog mProgressDialog;
    private PrefManager pref;
    ProgressBar progAttndFrgmt;
    String studCenterCode,studDepartmentNumber,student_id,studBranchStandardId;
    private static String TAG = NewRightActionActivity.class.getSimpleName();
    private TabLayout tabs_news;
    private ViewPager frameLayout_news;
    String currentDate;
    RecyclerView daywiseRecyVw;
    String date, type, spanFromDt, spanUptoDt;
    int  valText;
    String NameOfParam;
    private RelativeLayout lytAttndence;
    public static DaywiseAttndModel daywiseAttndModel;
    public static ArrayList<DaywiseAttndModel.DataBean> daywiseArrayList;
    DayWiseAttendApdater dayWiseAttendApdater;
    DayWiseAttendanceAdpter new_eventDetailsAdpter;
    private LinearLayout belowId;
    int val;
    TextView c;
    List<String> statusList;
    private UtilityClass utilityClassObj;
    @SuppressLint("ResourceAsColor")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.day_wise_attendance, container, false);
      //  val = getArguments().getInt("someInt",0);
        mProgressDialog=new ProgressDialog(getActivity());
        pref = new PrefManager(getActivity());
        utilityClassObj  = new  UtilityClass(getActivity());
        try {
            NameOfParam=getArguments().getString("Day_Wise");


        }catch (Exception e){
            e.printStackTrace();
        }
        //
        edit_search= (EditText) view.findViewById(R.id.edit_search);
        dayWiseList= (RecyclerView) view.findViewById(R.id.dayWiseList);
        show_search_date= (TextView) view.findViewById(R.id.show_search_date);
        imageView_search= (ImageView) view.findViewById(R.id.imageView_search);
        imageView_Date= (ImageView) view.findViewById(R.id.imageView_Date);
        datePicker = (DatePicker)view. findViewById(R.id.datePicker);
        lytAttndence = (RelativeLayout)view.  findViewById(R.id.lytAttndence);
        progAttndFrgmt = (ProgressBar) view.findViewById(R.id.progAttndFrgmt);
        belowId =(LinearLayout) view.findViewById(R.id.belowId);
        Typeface type_faceHeading = Typeface.createFromAsset(getActivity().getAssets(), "font/Poppins-Bold.ttf");
        Typeface type_faceContent = Typeface.createFromAsset(getActivity().getAssets(), "font/Poppins-Regular.ttf");



        SimpleDateFormat currentDate = new SimpleDateFormat("dd MMM yyyy");
        Date todayDate = new Date();
        String thisDate = currentDate.format(todayDate);

        show_search_date.setText(thisDate);

        if (URLEndPoints.ConstanceLoginUser.equalsIgnoreCase("Student")){
            belowId.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.text_blue));
            show_search_date.setTextColor(ContextCompat.getColor(getActivity(), R.color.text_blue));
        }else {
            belowId.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.present));
            show_search_date.setTextColor(ContextCompat.getColor(getActivity(), R.color.present));
        }



        getDayWiseAttend(thisDate);


        ShowNewData();

        return view;
    }


    private   void  ShowNewData(){

        imageView_Date.setOnClickListener(new View.OnClickListener() {
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
                }
                else {

                    Calendar ref = Calendar.getInstance();
                    int date = ref.get(Calendar.DAY_OF_MONTH);
                    int month = ref.get(Calendar.MONTH);
                    int year = ref.get(Calendar.YEAR);
                    // Providing Current date  to Date picker Dialog

                    DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                        @SuppressLint("ResourceAsColor")
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
                                String dd =selected_date;
                                Date show = null;
                                SimpleDateFormat formatter1 = new SimpleDateFormat("dd/MM/yyyy");
                                try {
                                    show = formatter1.parse(dd);
                                    formatter1= new SimpleDateFormat("dd MMM yyyy");
                                    dd = formatter1.format(show);
                                    System.out.println(date);

                                    // String v =show.toString();
                                    show_search_date.setText(dd);
                                    show_search_date.setTextColor(ContextCompat.getColor(getActivity(), R.color.text_blue));
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                                getDayWiseAttend(selected_date);
                            }
                        }
                    }, year, month, date);
                    datePickerDialog.show();
                }
            }
        });

    }
    private void getDayWiseAttend(final String period_start_date) {

        utilityClassObj.startLoader(getActivity(),R.drawable.image_for_rotation);
        String url = NavigationActivity.BASE_URL+URLEndPoints.GETDAYWISEATTEENDACE_URL;
        Log.d(TAG, "Day-wise SelfAttendance : "+url);

        StringRequest stringRequest = new StringRequest(Request.Method.POST,url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, response.toString());
                Gson gson = new Gson();
                daywiseAttndModel = gson.fromJson(response, DaywiseAttndModel.class);
                if (daywiseAttndModel.getStatus() == 1) {
                    daywiseArrayList = (ArrayList<DaywiseAttndModel.DataBean>) daywiseAttndModel.getData();

                    if (daywiseArrayList != null && daywiseArrayList.size() > 0) {
                       // daywiseRecyVw.setVisibility(View.VISIBLE);

                        new_eventDetailsAdpter = new DayWiseAttendanceAdpter(getActivity(),daywiseArrayList);
                        dayWiseList.setLayoutManager(new LinearLayoutManager(getActivity()));
                        dayWiseList.setAdapter(new_eventDetailsAdpter);
                       /* dayWiseAttendApdater = new DayWiseAttendApdater(getActivity(), daywiseArrayList);
                        daywiseRecyVw.setAdapter(dayWiseAttendApdater);*/
                    } else {
                       // daywiseRecyVw.setVisibility(View.GONE);
                        try {
                            Snackbar.make(lytAttndence, "Record not available.", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        }catch (Exception e){
                            e.printStackTrace();
                        }

                    }
                } else if (daywiseAttndModel.getStatus() == 0) {
                    try {
                        Snackbar.make(lytAttndence, "Record not available.", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
                utilityClassObj.stopLoader();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                handleVolleyError(error);
                utilityClassObj.stopLoader();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("_centercode",URLEndPoints.Constance_StudentCenterCode);
                params.put("PI_SESSION_ID",URLEndPoints.Constance_StudSessionId);
                params.put("P_BRANCH_STANDARD_ID",URLEndPoints.Constance_StudBranchStandardId);
                params.put("AcadSemisterType",URLEndPoints.Constance_StudSemesterType);
                params.put("p_Subject_Detail_Id","0");
                params.put("_iPI_APPLICABLE_NUMBER","0");
                params.put("P_STUDENT_ID",URLEndPoints.Constance_StudentID);
                params.put("start_DATE",period_start_date);
                params.put("end_DATE",period_start_date);
                params.put("P_REPORT_PATTERN","ATTENDANCE_DETAILS");
                Log.e(TAG, "Posting params:"+params.toString());
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


}