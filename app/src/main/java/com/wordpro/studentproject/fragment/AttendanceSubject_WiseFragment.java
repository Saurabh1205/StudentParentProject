package com.wordpro.studentproject.fragment;

import android.app.ProgressDialog;
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
import com.wordpro.studentproject.adapter.SpanListAdapter;
import com.wordpro.studentproject.adapter.SubjectWiseAttendanceAdpter;
import com.wordpro.studentproject.adapter.TeachPlanSubjAdapter;
import com.wordpro.studentproject.app.MyApplication;
import com.wordpro.studentproject.helper.PrefManager;
import com.wordpro.studentproject.model.SubjectwiseFacultyModel;
import com.wordpro.studentproject.model.TeachPlanSubjModel;
import com.wordpro.studentproject.utils.UtilityClass;
import com.wordpro.studentproject.webConfig.URLEndPoints;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.wordpro.studentproject.R.id.frame_container_Action;
import static com.wordpro.studentproject.R.id.show_search_startdate;
import static com.wordpro.studentproject.network.Network.isNetworkAvailable;


public class AttendanceSubject_WiseFragment extends Fragment {
    private static String TAG = AttendanceSubject_WiseFragment.class.getSimpleName();
    private LinearLayout  main_linear_view;
    Fragment me=this;
    AttendanceSubject_WiseFragment actionNewsFragment;
    private TextView  txt_day_wise,txt_sub_wise,txt_span_wise,txt_net_wise;
    private ImageView imageView_search,imageView_Date;
    private LinearLayout lay_day_wise,lay_sub_wise,lay_span_wise,lay_net_wise;
    String studCenterCode,studDepartmentNumber,student_id,studBranchStandardId;
    ProgressDialog mProgressDialog;
    private PrefManager pref;
    ProgressBar progAttndFrgmt;
    private RelativeLayout  lytAttndence;
    private RecyclerView dayWiseList;
    private CardView card_view,card_view11;

    public static TeachPlanSubjModel subjectListModel;
    public static ArrayList<TeachPlanSubjModel.DataBean> subjectArrayList;
    public static TeachPlanSubjAdapter subjectAdapter;

    public static SubjectwiseFacultyModel subjectwiseFacultyModel;
    public static ArrayList<SubjectwiseFacultyModel.DataBean> subjectFacTypeArrayList;
    public static SubjectWiseAttendanceAdpter subjectwiseFacTypeAdapter;
    private SubjectWiseAttendanceAdpter subjectWiseAttendanceAdpter;
    private DatePicker  datePicker;
    private  String NameOfParam;
    public Fragment fragment;
    private UtilityClass utilityClassObj;
    public AttendanceSubject_WiseFragment() {
        // Required empty public constructor
    }


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
        try {

            NameOfParam=getArguments().getString("Subject_Wise");

        }catch (Exception e){
            e.printStackTrace();
        }
        progAttndFrgmt = (ProgressBar) view.findViewById(R.id.progAttndFrgmt);
        lytAttndence = (RelativeLayout)view.findViewById(R.id.lytAttndence);
        dayWiseList= (RecyclerView) view.findViewById(R.id.dayWiseList);

        imageView_Date= (ImageView) view.findViewById(R.id.imageView_Date);
        datePicker = (DatePicker)view. findViewById(R.id.datePicker);
        card_view = (CardView) view. findViewById(R.id.card_view);
        card_view11 = (CardView) view. findViewById(R.id.card_view11);
        card_view.setVisibility(View.GONE);
        card_view11.setVisibility(View.GONE);


        getStartEndDate("SUBJ_WISE_ATTEND");
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
                              //  getNetAttendanceData(PERIOD_START_DATE, PERIOD_END_DATE);
                            } else if (attendance_type.equalsIgnoreCase("DAY_WISE_ATTEND")) {


                            } else if (attendance_type.equalsIgnoreCase("SPAN_WISE_ATTEND")) {

                            } else if (attendance_type.equalsIgnoreCase("SUBJ_WISE_ATTEND")) {
                                //progAttndFrgmt.setVisibility(View.VISIBLE);
                                getSubjectListFun(PERIOD_START_DATE, PERIOD_END_DATE);
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
                            dayWiseList.setLayoutManager(new LinearLayoutManager(getActivity()));
                            dayWiseList.setAdapter(subjectWiseAttendanceAdpter);
                            subjectWiseAttendanceAdpter.setOnItemClickListener(new SubjectWiseAttendanceAdpter.OnItemClickListener() {
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
                            });
                           // subjectwiseFacTypeAdapter = new SubjectWiseAttendanceAdpter(getActivity(), subjectFacTypeArrayList);
                           /* Intent intent = new Intent(getActivity(), SubjectWiseAttendActivity.class);
                            intent.putExtra("PERIOD_START_DATE", PERIOD_START_DATE);
                            intent.putExtra("PERIOD_END_DATE", PERIOD_END_DATE);
                            startActivity(intent);
                            getActivity().finish();*/

                        } else if (subjectArrayList == null) {
                            Snackbar.make(lytAttndence, "Record not available.", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                            utilityClassObj.stopLoader();
                        }
                    } else if (subjectListModel.getStatus() == 0) {
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
                utilityClassObj.stopLoader();
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

   /* @Override
    public void onItemClicked(String subjectDetailsId, String subName, String StockName) {
        Toast.makeText(getActivity(), "Stock terpilih " + StockName, Toast.LENGTH_LONG).show();
        fragment = new AttendanceSubject_Wise_Details_Fragment();
        Bundle bundle=new Bundle();
        bundle.putString("Subject_Wise", "Subject_wise_att");
        bundle.putString("SubjectId", subjectDetailsId);
        bundle.putString("SubjectName", subName);
        bundle.putString("SubjectShortDes", StockName);
        loadFragmentMain(fragment);
    }*/

    private void loadFragmentMain(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(frame_container_Action, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }


}
