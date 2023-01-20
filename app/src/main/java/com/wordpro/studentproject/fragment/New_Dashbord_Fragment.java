package com.wordpro.studentproject.fragment;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.wordpro.studentproject.adapter.FeesTopHeaderNameAdapterClass;
import com.wordpro.studentproject.adapter.JobDetailAdpter;
import com.wordpro.studentproject.adapter.NetWiseAttendanceAdpter;
import com.wordpro.studentproject.adapter.StudentDetailsAdpter;
import com.wordpro.studentproject.adapter.SubjectDayWiseAttendanceAdpter;
import com.wordpro.studentproject.adapter.SubjectWiseAttendanceAdpter;
import com.wordpro.studentproject.app.MyApplication;
import com.wordpro.studentproject.helper.PrefManager;
import com.wordpro.studentproject.model.DaywiseAttndModel;
import com.wordpro.studentproject.model.FeesChildModel;
import com.wordpro.studentproject.model.FeesDataList;
import com.wordpro.studentproject.model.FeesMainDataMode;
import com.wordpro.studentproject.model.FeesParentModel;
import com.wordpro.studentproject.model.GetStudentDetailsModel;
import com.wordpro.studentproject.model.JobDetailsModel;
import com.wordpro.studentproject.model.NetAttendanceModel;
import com.wordpro.studentproject.model.SubjectwiseFacultyModel;
import com.wordpro.studentproject.model.TeachPlanSubjModel;
import com.wordpro.studentproject.utils.UtilityClass;
import com.wordpro.studentproject.webConfig.URLEndPoints;

import org.json.JSONObject;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.wordpro.studentproject.R.id.frame_container_Action;


public class New_Dashbord_Fragment extends Fragment  {
    private static String TAG = New_Dashbord_Fragment.class.getSimpleName();
    private LinearLayout  main_linear_view;
    Fragment me=this;
    New_Dashbord_Fragment actionNewsFragment;

    String studCenterCode,studDepartmentNumber,student_id,studBranchStandardId;

    private PrefManager pref;
    ProgressBar progAttndFrgmt;

    private RecyclerView subject_List;
    private CardView card_view_net;

    private ImageView back_press,imageView_search,imageView_filter;

    private TextView  txt_sub_name,txt_Tm,txt_tolPre,txt_total_pre,txt_total_pre2,txt_tolPer;

    private LinearLayout lay_net_wise;

    //net Attendance list
    public static NetAttendanceModel netAttendanceModel;
    public static ArrayList<NetAttendanceModel.DataBean> netAttndArrayList;
    //Subject  wise List
    public Fragment fragment;
    public static TeachPlanSubjModel subjectListModel;
    public static ArrayList<TeachPlanSubjModel.DataBean> subjectArrayList;
    public static SubjectwiseFacultyModel subjectwiseFacultyModel;
    public static ArrayList<SubjectwiseFacultyModel.DataBean> subjectFacTypeArrayList;
    public static SubjectWiseAttendanceAdpter subjectwiseFacTypeAdapter;
    private SubjectWiseAttendanceAdpter subjectWiseAttendanceAdpter;
    private TextView txt_CompleteFee,txt_Academic_Fees,txt_Non_Academic ,txt_Outstanding_Fees,txt_Exam_Fees;
    private List<FeesDataList> feesDataLists;
    private TextView txt_date,txt_Fee_name,txt_totalfee,txt_Outstanding,txt_total_fee_value,txt_outVal,txt_Outsatnding_val,txt_Adju_val,txt_Adj;
    private UtilityClass utilityClassObj;

    public New_Dashbord_Fragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        final View view = inflater.inflate(R.layout.new_dashboard_fragment, container, false);
        Typeface type_faceHeading = Typeface.createFromAsset(getActivity().getAssets(), "font/Poppins-Bold.ttf");
        Typeface type_faceContent = Typeface.createFromAsset(getActivity().getAssets(), "font/Poppins-Regular.ttf");
        pref = new PrefManager(getActivity());
        utilityClassObj  = new  UtilityClass(getActivity());
       // progAttndFrgmt = (ProgressBar) view.findViewById(R.id.progAttndFrgmt);
        card_view_net = (CardView) view. findViewById(R.id.card_view_net);

        txt_sub_name = (TextView) view.findViewById(R.id.txt_sub_name);
        txt_Tm = (TextView) view.findViewById(R.id.txt_Tm);
        txt_tolPre = (TextView) view.findViewById(R.id.txt_tolPre);
        txt_tolPer = (TextView) view.findViewById(R.id.txt_tolPer);
        txt_total_pre = (TextView) view.findViewById(R.id.txt_total_pre);
        txt_total_pre2 = (TextView) view.findViewById(R.id.txt_total_pre2);
        subject_List =(RecyclerView)  view.findViewById(R.id.subject_List);

        /*txt_CompleteFee  =(TextView)view. findViewById(R.id.txt_CompleteFee);
        txt_Academic_Fees  =(TextView)view. findViewById(R.id.txt_Academic_Fees);
        txt_Non_Academic  =(TextView)view. findViewById(R.id.txt_Non_Academic);
        txt_Outstanding_Fees  =(TextView)view. findViewById(R.id.txt_Outstanding_Fees);
        txt_Exam_Fees  =(TextView)view. findViewById(R.id.txt_Exam_Fees);
*/
        txt_date  =(TextView)view. findViewById(R.id.txt_date);
        txt_Fee_name  =(TextView)view. findViewById(R.id.txt_Fee_name);
        txt_totalfee  =(TextView)view. findViewById(R.id.txt_totalfee);
        txt_Outstanding  =(TextView) view.findViewById(R.id.txt_Outstanding);
        txt_total_fee_value  =(TextView) view.findViewById(R.id.txt_total_fee_value);
        txt_outVal  =(TextView) view.findViewById(R.id.txt_outVal);
        txt_Outsatnding_val  =(TextView)view. findViewById(R.id.txt_Outsatnding_val);
        txt_Adju_val=(TextView)view. findViewById(R.id.txt_Adju_val);
        txt_Adj =(TextView) view.findViewById(R.id.txt_Adj);

        txt_total_pre.setTypeface(type_faceHeading);
        txt_sub_name.setTypeface(type_faceHeading);
        // txt_Tm.setTypeface(type_faceHeading);
        txt_tolPre.setTypeface(type_faceContent);
        txt_total_pre2.setTypeface(type_faceHeading);
        txt_tolPer.setTypeface(type_faceContent);


        txt_Fee_name.setTypeface(type_faceHeading);
        txt_totalfee.setTypeface(type_faceHeading);
        txt_Outstanding.setTypeface(type_faceHeading);
        txt_total_fee_value.setTypeface(type_faceHeading);
        txt_outVal.setTypeface(type_faceHeading);
        txt_Outsatnding_val.setTypeface(type_faceHeading);
        txt_Adju_val.setTypeface(type_faceHeading);
        txt_Adj.setTypeface(type_faceHeading);

        card_view_net.setVisibility(View.VISIBLE);


        getStartEndDate("NET_ATTENDANCE");
        getStartEndDate("SUBJ_WISE_ATTEND");
        getFeesData("COMPLETE");





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

    private void getStartEndDate(final String attendance_type) {

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
                                getNetAttendanceData(PERIOD_START_DATE, PERIOD_END_DATE);
                            } else if (attendance_type.equalsIgnoreCase("DAY_WISE_ATTEND")) {


                            } else if (attendance_type.equalsIgnoreCase("SPAN_WISE_ATTEND")) {

                            } else if (attendance_type.equalsIgnoreCase("SUBJ_WISE_ATTEND")) {
                                getSubjectListFun(PERIOD_START_DATE, PERIOD_END_DATE);
                            }

                        } else {
                          /*  Snackbar snackbar = Snackbar
                                    .make(lytAttndence, "Record not found.", Snackbar.LENGTH_LONG);
                            snackbar.show();*/

                           // progAttndFrgmt.setVisibility(View.GONE);

                        }

                    } else if (status == 0) {
                       /* Snackbar.make(lytAttndence, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();*/
                       // progAttndFrgmt.setVisibility(View.GONE);
                    }


                } catch (Exception e) {

                   /* Snackbar.make(lytAttndence, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                    Log.d(TAG, "Error : " + e.getMessage());*/

                  //  progAttndFrgmt.setVisibility(View.GONE);

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
                       /* netWiseAttendanceAdpter = new NetWiseAttendanceAdpter(getActivity(),netAttndArrayList);
                        spanWiseList.setLayoutManager(new LinearLayoutManager(getActivity()));
                        spanWiseList.setAdapter(netWiseAttendanceAdpter);*/


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
                            //txt_total_pre.setText(PPP);
                            txt_tolPer.setText(P  +" ]");
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
                       /* Snackbar.make(lytAttndence, "Record not found.", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();*/
                    }

                } else if (netAttendanceModel.getStatus() == 0) {
                    /*Snackbar.make(lytAttndence, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();*/
                }

               // progAttndFrgmt.setVisibility(View.GONE);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                handleVolleyError(error);

                //progAttndFrgmt.setVisibility(View.GONE);
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
    private void getSubjectListFun(final String PERIOD_START_DATE, final String PERIOD_END_DATE) {
        utilityClassObj.startLoader(getActivity(),R.drawable.image_for_rotation);
        // String url = pref.getURL() + "Student/GetActionfacultyDetails?Session_Id=" + studSessionId + "&main_Semester_Type=" + studSemesterType + "&EMPLOYEE_ID=0&Subject_Detail_Id=0&BRANCH_STANDARD_ID=" + studBranchStandardId + "&Sub_Dept_NUMBER=0&Emp_Dept_NUMBER=0&Centre_Code=" + studCenterCode;
//-------------------------------------------------------------------
        String url = pref.getURL() + URLEndPoints.GetSubjectWiseFaculty_URL;
        Log.d(TAG, "Subject and faculty List : " + url);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d(TAG, response.toString());
                try {


                    Gson gson = new Gson();
                    subjectwiseFacultyModel = gson.fromJson(response, SubjectwiseFacultyModel.class);
                    if (subjectwiseFacultyModel.getStatus() == 1) {
                        utilityClassObj.stopLoader();

                        subjectFacTypeArrayList = (ArrayList<SubjectwiseFacultyModel.DataBean>) subjectwiseFacultyModel.getData();
                        if (subjectFacTypeArrayList != null) {

                            subjectWiseAttendanceAdpter = new SubjectWiseAttendanceAdpter(getActivity(),subjectFacTypeArrayList);
                            subject_List.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,
                                    false));
                            subject_List.setAdapter(subjectWiseAttendanceAdpter);
                            /*subjectWiseAttendanceAdpter.setOnItemClickListener(new SubjectWiseAttendanceAdpter.OnItemClickListener() {
                                @Override
                                public void onItemClick(View itemView, int position) {

                                    String subjectDetailsId = subjectFacTypeArrayList.get(position).getSUBJECT_DETAIL_ID();
                                    String subName = subjectFacTypeArrayList.get(position).getSUBJECT_DESCRIPTION();
                                    String subjectShort = subjectFacTypeArrayList.get(position).getSUB_SHORT_DESCRIPT();


                                    URLEndPoints.Constance_SUB_ID =subjectDetailsId;
                                    URLEndPoints.Constance_SUB_NAME =subName;
                                    URLEndPoints.Constance_SUB_SHORT =subjectShort;
                                    fragment = new AttendanceSubject_Wise_Details_Fragment();
                                    Bundle bundle=new Bundle();
                                    bundle.putString("Subject_Wise", "Subject_wise_att");
                                    bundle.putString("SubjectId", subjectDetailsId);
                                    bundle.putString("SubjectName", subName);
                                    bundle.putString("SubjectShortDes", subjectShort);
                                    loadFragmentMain(fragment);
                                }
                            });*/


                        } else if (subjectArrayList == null) {
                            /*Snackbar.make(lytAttndence, "Record not available.", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
*/
                        }
                    } else if (subjectListModel.getStatus() == 0) {
                        /*Snackbar.make(lytAttndence, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();*/
                        utilityClassObj.stopLoader();

                    }

                   // progAttndFrgmt.setVisibility(View.GONE);

                } catch (Exception e) {

                   /* Snackbar.make(lytAttndence, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                    Log.d(TAG, "Error : " + e.getMessage());
*/
                    utilityClassObj.stopLoader();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                handleVolleyError(error);
                //progAttndFrgmt.setVisibility(View.GONE);

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();

                params.put("P_BRANCH_STANDARD_ID", URLEndPoints.Constance_StudBranchStandardId);
                params.put("P_STUDENT_ID", URLEndPoints.Constance_StudentID);
                params.put("AcadSemisterType", URLEndPoints.Constance_StudSemesterType);
                params.put("_centercode", URLEndPoints.Constance_StudentCenterCode);
                params.put("PI_SESSION_ID", URLEndPoints.Constance_StudSessionId);

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
    private void getFeesData(final String feeType) {

        //progBarTimeTable.setVisibility(View.VISIBLE);

      //  String demoStuID= "1062180080";

        String url = pref.getURL() + URLEndPoints.GetStudFees_URL(URLEndPoints.Constance_StudentID,feeType);

        Log.d(TAG, "Fees Data :  " + url);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, response.toString());
                try {

                    Gson gson = new Gson();
                    FeesMainDataMode mainNoticPatten    = gson.fromJson(response, FeesMainDataMode.class);

                    if (mainNoticPatten.getStatus() == 1) {
                        // progBarTimeTable.setVisibility(View.GONE);
                        feesDataLists = (ArrayList<FeesDataList>) mainNoticPatten.getData();

                        List<FeesParentModel> paretData = new ArrayList<>();
                        List<FeesChildModel> ChildData = new ArrayList<>();
                        double ReceivableAmt = 0;
                        double BalanceAmt = 0;
                        double ReceiptAmt = 0;
                        double DiscountAMT=0;

                        double TotalReceivableAmt = 0;
                        double TotalBalanceAmt = 0;
                        double TotalReceiptAmt = 0;
                        double TotalDiscountAmt= 0;
                        String totalRECEIVABLE ="" ;
                        String totalBALANCE_AMT ="";
                        String totalRECEIPT_AMT ="" ;
                        String totalDiscount_Amt ="";

                        for (int  a=0;a<feesDataLists.size();a++){

                            for (int b=0;b<feesDataLists.get(a).getParent().size();b++){


                                ChildData =feesDataLists.get(a).getParent().get(b).getChild();


                                for (int d = 0; d < ChildData.size(); d++) {
                                    ReceivableAmt =  Double.parseDouble(ChildData.get(d).getrECEIVABLEAMOUNT());
                                    //ReceivableAmt = Integer.parseInt(ChildData.get(d).getrECEIVABLEAMOUNT());
                                    BalanceAmt =  Double.parseDouble(ChildData.get(d).getbALANCEAMOUNT());
                                    ReceiptAmt=  Double.parseDouble(ChildData.get(d).getfEERECEIPTAMOUNT());
                                   // ReceiptAmt = Integer.parseInt(ChildData.get(d).getfEERECEIPTAMOUNT());
                                    DiscountAMT = Double.parseDouble(ChildData.get(d).getdISCOUNTAMOUNT());
                                    TotalReceivableAmt = TotalReceivableAmt + ReceivableAmt;
                                    TotalBalanceAmt = TotalBalanceAmt + BalanceAmt;
                                    TotalReceiptAmt = TotalReceiptAmt + ReceiptAmt;
                                    TotalDiscountAmt =TotalDiscountAmt +DiscountAMT;
                                }


                                totalRECEIVABLE = String.valueOf(TotalReceivableAmt);
                                totalBALANCE_AMT = String.valueOf(TotalBalanceAmt);
                                totalRECEIPT_AMT = String.valueOf(TotalReceiptAmt);
                                totalDiscount_Amt =String.valueOf(TotalDiscountAmt);


                            }
                        }
                        txt_Fee_name.setText("Complete Fees  :    " +" "+ totalRECEIVABLE  );
                        txt_outVal.setText(totalRECEIVABLE);
                        txt_Outsatnding_val.setText(totalBALANCE_AMT);
                        txt_total_fee_value.setText(totalRECEIPT_AMT);
                        txt_Adju_val.setText(totalDiscount_Amt);
                    }

                } catch (Exception e) {

                   /* Snackbar.make(startView, "Server not responding.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
*/
                    Log.d(TAG, "Error : " + e.getMessage());

                    // progBarTimeTable.setVisibility(View.GONE);

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Error: " + error.getMessage());
                handleVolleyError(error);
                //progBarTimeTable.setVisibility(View.GONE);
                // feesTopHeaderNameAdapterClass.notifyDataSetChanged();
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
