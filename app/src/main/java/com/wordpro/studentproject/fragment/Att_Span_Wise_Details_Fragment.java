package com.wordpro.studentproject.fragment;

import android.app.ProgressDialog;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
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
import com.wordpro.studentproject.adapter.SubjectDayWiseAttendanceAdpter;
import com.wordpro.studentproject.adapter.SubjectWiseAttendanceAdpter;
import com.wordpro.studentproject.app.MyApplication;
import com.wordpro.studentproject.helper.PrefManager;
import com.wordpro.studentproject.model.DaywiseAttndModel;
import com.wordpro.studentproject.model.SubjectwiseFacultyModel;
import com.wordpro.studentproject.utils.UtilityClass;
import com.wordpro.studentproject.webConfig.URLEndPoints;

import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class Att_Span_Wise_Details_Fragment extends Fragment  {
    private static String TAG = Att_Span_Wise_Details_Fragment.class.getSimpleName();
    private LinearLayout  main_linear_view;
    Fragment me=this;
    Att_Span_Wise_Details_Fragment actionNewsFragment;

    String studCenterCode,studDepartmentNumber,student_id,studBranchStandardId;
    ProgressDialog mProgressDialog;
    private PrefManager pref;
    ProgressBar progAttndFrgmt;
    private RelativeLayout  lytAttndence;
    private RecyclerView dayWiseList;
    private CardView card_view,card_view11;


    public static SubjectwiseFacultyModel subjectwiseFacultyModel;
    public static ArrayList<SubjectwiseFacultyModel.DataBean> subjectFacTypeArrayList;
    public static SubjectWiseAttendanceAdpter subjectwiseFacTypeAdapter;
    private SubjectDayWiseAttendanceAdpter subjectDayWiseAttendanceAdpter;
    private DatePicker  datePicker;
    private  String NameOfParam,ShowDate,SelectDate;
    private ImageView imageView_Date;
    private TextView txt_Subject_Name;
    private String  StartDate,EndDate;
    public static DaywiseAttndModel subjAttndModel;
    private ImageView  back_press;
    public static ArrayList<DaywiseAttndModel.DataBean> subjectwiseAttndList;
    public Att_Span_Wise_Details_Fragment() {
        // Required empty public constructor
    }
    private UtilityClass utilityClassObj;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        final View view = inflater.inflate(R.layout.day_wise_attendance, container, false);
        Typeface type_faceHeading = Typeface.createFromAsset(getActivity().getAssets(), "font/Poppins-Bold.ttf");
        Typeface type_faceContent = Typeface.createFromAsset(getActivity().getAssets(), "font/Poppins-Regular.ttf");
        pref = new PrefManager(getActivity());
        utilityClassObj  = new  UtilityClass(getActivity());
        Bundle bundle=getArguments();
        try {

            NameOfParam=bundle.getString("Span_Wise");
            ShowDate=bundle.getString("ShowDate");
            SelectDate =bundle.getString("SelectDate");

        }catch (Exception e){
            e.printStackTrace();
        }
        progAttndFrgmt = (ProgressBar) view.findViewById(R.id.progAttndFrgmt);
        lytAttndence = (RelativeLayout)view.findViewById(R.id.lytAttndence);
        dayWiseList= (RecyclerView) view.findViewById(R.id.dayWiseList);
        imageView_Date= (ImageView) view.findViewById(R.id.imageView_Date);
        txt_Subject_Name =(TextView)view.findViewById(R.id.txt_Subject_Name);
        datePicker = (DatePicker)view. findViewById(R.id.datePicker);
        card_view = (CardView) view. findViewById(R.id.card_view);
        back_press =(ImageView)view.findViewById(R.id.back_press);
        card_view.setVisibility(View.GONE);
        card_view11 = (CardView) view. findViewById(R.id.card_view11);
        card_view.setVisibility(View.GONE);


        if (URLEndPoints.ConstanceLoginUser.equalsIgnoreCase("Student")){
            // belowId.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.text_blue));
            txt_Subject_Name.setTextColor(ContextCompat.getColor(getActivity(), R.color.text_blue));

        }else {
            txt_Subject_Name.setTextColor(ContextCompat.getColor(getActivity(), R.color.present));

        }

        StartDate = URLEndPoints.Constance_SUB_NAME;
                EndDate =URLEndPoints.Constance_SUB_NAME;
        getSubjWiseAttend(StartDate, EndDate,"","","");


        back_press.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });


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

    private void getSubjWiseAttend(final String period_start_date, final String currentDate, final String subject_detail_id, final String subjectName, final String profName) {
        utilityClassObj.startLoader(getActivity(),R.drawable.image_for_rotation);
        String url = NavigationActivity.BASE_URL + URLEndPoints.GETDAYWISEATTEENDACE_URL;
        Log.d(TAG, "Day-wise SelfAttendance : " + url);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, response.toString());
                Gson gson = new Gson();

                subjAttndModel = gson.fromJson(response, DaywiseAttndModel.class);
                if (subjAttndModel.getStatus() == 1) {
                    utilityClassObj.stopLoader();
                    subjectwiseAttndList = (ArrayList<DaywiseAttndModel.DataBean>) subjAttndModel.getData();

                    if (subjectwiseAttndList != null && subjectwiseAttndList.size()> 0) {


                        subjectDayWiseAttendanceAdpter = new SubjectDayWiseAttendanceAdpter(getActivity(),subjectwiseAttndList);
                        dayWiseList.setLayoutManager(new LinearLayoutManager(getActivity()));
                        dayWiseList.setAdapter(subjectDayWiseAttendanceAdpter);
                        card_view11.setVisibility(View.VISIBLE);
                        txt_Subject_Name.setText(URLEndPoints.Constance_SUB_ID);
                        //txt_Subject_Name.setTextColor(ContextCompat.getColor(getActivity(), R.color.text_blue));

                    } else {
                        Snackbar.make(lytAttndence, "Record not available.", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                        utilityClassObj.stopLoader();
                    }
                } else if (subjAttndModel.getStatus() == 0) {
                    Snackbar.make(lytAttndence, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    utilityClassObj.stopLoader();
                }
               // progAttndFrgmt.setVisibility(View.GONE);

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
                params.put("_centercode", URLEndPoints.Constance_StudentCenterCode);
                params.put("PI_SESSION_ID", URLEndPoints.Constance_StudSessionId);
                params.put("P_BRANCH_STANDARD_ID",  URLEndPoints.Constance_StudBranchStandardId);
                params.put("AcadSemisterType", URLEndPoints.Constance_StudSemesterType);
                params.put("p_Subject_Detail_Id", "0");
                params.put("_iPI_APPLICABLE_NUMBER", "0");
                params.put("P_STUDENT_ID", URLEndPoints.Constance_StudentID);
                params.put("start_DATE", StartDate);
                params.put("end_DATE", EndDate);
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

        Toast.makeText(getActivity(), ""+message, Toast.LENGTH_SHORT).show();
    }

}
