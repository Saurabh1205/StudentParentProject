package com.wordpro.studentproject.fragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
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
import com.wordpro.studentproject.activities.syllabus_menu.TeachPlanSubjActivity;
import com.wordpro.studentproject.adapter.DayWiseAttendApdater;
import com.wordpro.studentproject.adapter.DayWiseAttendanceAdpter;
import com.wordpro.studentproject.adapter.New_NewsDetailsAdpter;
import com.wordpro.studentproject.adapter.SubjectWiseAttendanceAdpter;
import com.wordpro.studentproject.adapter.TeachPlanSubjAdapter;
import com.wordpro.studentproject.adapter.UniverSubjAdapter;
import com.wordpro.studentproject.adapter.University_syllabus_Adpter;
import com.wordpro.studentproject.app.MyApplication;
import com.wordpro.studentproject.helper.PrefManager;
import com.wordpro.studentproject.model.DaywiseAttndModel;
import com.wordpro.studentproject.model.NoticesModel;
import com.wordpro.studentproject.model.TeachPlanSubjModel;
import com.wordpro.studentproject.model.UnviSubjModel;
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

import static com.wordpro.studentproject.R.id.frame_syllabus_container;
import static com.wordpro.studentproject.activities.NavigationActivity.fragSyllabus;
import static com.wordpro.studentproject.activities.NavigationActivity.studBranchStandardId;
import static com.wordpro.studentproject.activities.NavigationActivity.studBranch_Standard_GRP_ID;
import static com.wordpro.studentproject.activities.NavigationActivity.studCenterCode;
import static com.wordpro.studentproject.activities.NavigationActivity.studSemesterType;
import static com.wordpro.studentproject.activities.NavigationActivity.studSessionId;
import static com.wordpro.studentproject.fragment.FragSyllabus.teachPlanSubjArrayList;
import static com.wordpro.studentproject.network.Network.isNetworkAvailable;

public class UniversitySyllabusFragmen extends Fragment {
    View view;

    private RecyclerView dayWiseList;



    private PrefManager pref;
    ProgressBar progAttndFrgmt;
    String studCenterCode,studDepartmentNumber,student_id,studBranchStandardId;
    private static String TAG = NewRightActionActivity.class.getSimpleName();
    private CardView card_view;
    private RelativeLayout lytAttndence;
    public static DaywiseAttndModel daywiseAttndModel;
    public static ArrayList<DaywiseAttndModel.DataBean> daywiseArrayList;
    private LinearLayout belowId;
    private ImageView back_press;
    private TextView txt_toolbarName;
    private UtilityClass utilityClassObj;
    University_syllabus_Adpter university_syllabus_adpter;
    private Fragment fragment;

    public static UnviSubjModel univerSubjModel;
    public static ArrayList<UnviSubjModel.DataBean> univSubjArrayList;

    public static TeachPlanSubjModel teachPlanSubjModel;
    public static ArrayList<TeachPlanSubjModel.DataBean> teachPlanSubjArrayList;


    @SuppressLint("ResourceAsColor")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.day_wise_attendance, container, false);
      //  val = getArguments().getInt("someInt",0);
        //mProgressDialog=new ProgressDialog(getActivity());
        pref = new PrefManager(getActivity());
        utilityClassObj  = new  UtilityClass(getActivity());
        dayWiseList= (RecyclerView) view.findViewById(R.id.dayWiseList);
        card_view= (CardView) view.findViewById(R.id.card_view);
        belowId = (LinearLayout) view.findViewById(R.id.belowId);
        back_press = (ImageView) view.findViewById(R.id.back_press);
        txt_toolbarName = (TextView) view.findViewById(R.id.txt_toolbarName);
        belowId.setVisibility(View.VISIBLE);
        lytAttndence = (RelativeLayout)view.  findViewById(R.id.lytAttndence);
      //  progAttndFrgmt = (ProgressBar) view.findViewById(R.id.progAttndFrgmt);
        card_view.setVisibility(View.GONE);
       // txt_toolbarName.setText("SYLLABUS -(UNIVERSITY -SYLLABUS)");
        Typeface type_faceHeading = Typeface.createFromAsset(getActivity().getAssets(), "font/Poppins-Bold.ttf");
        Typeface type_faceContent = Typeface.createFromAsset(getActivity().getAssets(), "font/Poppins-Regular.ttf");

        if (URLEndPoints.ConstanceLoginUser.equalsIgnoreCase("Student")){
            belowId.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.text_blue));
        }else {
            belowId.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.present));
        }

        txt_toolbarName.setText("SYLLABUS");
        txt_toolbarName.setTypeface(type_faceHeading);
        get_uniSyllabusData();

       // GetTeachingPlan();

        back_press.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), NavigationActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        return view;
    }


    private   void  get_uniSyllabusData(){
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

                                }
                            });
            AlertDialog alert = builder.create();
            alert.show();
        }else {

            utilityClassObj.startLoader(getActivity(),R.drawable.image_for_rotation);

            //String url = pref.getURL() + URLEndPoints.GetUniversitySyllabusList_URL(URLEndPoints.Constance_StudSessionId,URLEndPoints.Constance_StudSemesterType, URLEndPoints.Constance_StudBranchStandardId, URLEndPoints.Constance_StudentCenterCode);
            //String url = pref.getURL() + URLEndPoints.GetUniversitySyllabusList_URL(URLEndPoints.Constance_StudSessionId, URLEndPoints.Constance_studBranch_Standard_GRP_ID, URLEndPoints.Constance_StudSemesterType);
            String url = pref.getURL() + URLEndPoints.NewGetUniversitySyllabusList_URL(URLEndPoints.Constance_StudBranchStandardId,URLEndPoints.Constance_StudentCenterCode,URLEndPoints.Constance_StudSessionId,URLEndPoints.Constance_StudentID, URLEndPoints.Constance_StudSemesterType);

            Log.d(TAG, "Subject and faculty List : " + url);

            StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    Log.d(TAG, response.toString());
                    try {

                        Gson gson = new Gson();
                        univerSubjModel = gson.fromJson(response, UnviSubjModel.class);
                        if (univerSubjModel.getStatus() == 1) {
                            utilityClassObj.stopLoader();
                            univSubjArrayList = (ArrayList<UnviSubjModel.DataBean>) univerSubjModel.getData();
                            if (univSubjArrayList != null) {
                                university_syllabus_adpter = new University_syllabus_Adpter(getActivity(), univSubjArrayList,teachPlanSubjArrayList);
                                dayWiseList.setLayoutManager(new LinearLayoutManager(getActivity()));
                                dayWiseList.setAdapter(university_syllabus_adpter);

                                university_syllabus_adpter.setOnItemClickListener(new University_syllabus_Adpter.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(View itemView, int position,String name) {

                                        String subjectGroupId = univSubjArrayList.get(position).getSUBJECT_GROUP_ID();
                                        String subAPNo = univSubjArrayList.get(position).getAPPLICABLE_NUMBER();
                                        String subjectDes = univSubjArrayList.get(position).getSUBJECT_DESCRIPTION();
                                        URLEndPoints.ConstanceSUB_GROUPID =subjectGroupId;
                                        URLEndPoints.ConstanceSUBAPPLICABLE_NO =subAPNo;
                                        URLEndPoints.ConstanceSUB_DES =subjectDes;
                                        URLEndPoints.ConstanceTeacherNa =univSubjArrayList.get(position).getEMP_EXCH_FAC();
                                        URLEndPoints.ConstanceSUB_TH =univSubjArrayList.get(position).getTYPE_SHORT_NAME();
                                        URLEndPoints.Constance_EMPId = univSubjArrayList.get(position).getEmployee_id();
                                        URLEndPoints.Constance_Sub_Details_ID = univSubjArrayList.get(position).getSUBJECT_DETAIL_ID();
                                        URLEndPoints.Constance_Sub_Batch_Des_ID = univSubjArrayList.get(position).getSTU_BATCH_DET_ID();
                                        if (name.equalsIgnoreCase("SYLLABUS")){

                                            Bundle bundle=new Bundle();
                                            bundle.putString("SYLLABUS_VIEW", "SYLLABUS");
                                            fragment = new UnitList_Details__US_Fragment();
                                            fragment.setArguments(bundle);
                                            loadFragmentMain(fragment);


                                        }else if (name.equalsIgnoreCase("TEACHING PLAN")){

                                            Bundle bundle=new Bundle();
                                            bundle.putString("TEACHING_PLAN_VIEW", "TEACHING PLAN");
                                            fragment = new UnitList_Details__US_Fragment();
                                            fragment.setArguments(bundle);
                                            loadFragmentMain(fragment);


                                        }else if (name.equalsIgnoreCase("STATUS")){

                                            Bundle bundle=new Bundle();
                                            bundle.putString("STATUS_VIEW", "STATUS");
                                           // bundle.putString("EmployeeId",);
                                            fragment = new UnitList_Details__US_Fragment();
                                            fragment.setArguments(bundle);
                                            loadFragmentMain(fragment);

                                        }

                                       /* String subjectGroupId = univSubjArrayList.get(position).getSUBJECT_GROUP_ID();
                                        String subAPNo = univSubjArrayList.get(position).getAPPLICABLE_NUMBER();
                                        String subjectDes = univSubjArrayList.get(position).getSUBJECT_DESCRIPTION();
                                        URLEndPoints.ConstanceSUB_GROUPID =subjectGroupId;
                                        URLEndPoints.ConstanceSUBAPPLICABLE_NO =subAPNo;
                                        URLEndPoints.ConstanceSUB_DES =subjectDes;
                                        URLEndPoints.ConstanceSUB_TH =univSubjArrayList.get(position).getTYPE_SHORT_NAME();
                                        fragment = new UnitList_Details__US_Fragment();
                                        loadFragmentMain(fragment);*/
                                    }
                                });
                            } else if (univSubjArrayList == null) {
                                Snackbar.make(lytAttndence, "Record not available.", Snackbar.LENGTH_LONG)
                                        .setAction("Action", null).show();
                                utilityClassObj.stopLoader();
                            }
                        }  else if (univerSubjModel.getStatus() == 0) {
                            utilityClassObj.stopLoader();
                            Snackbar.make(lytAttndence, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        }
                       // progAttndFrgmt.setVisibility(View.GONE);
                    } catch (Exception e) {
                        utilityClassObj.stopLoader();
                        Snackbar.make(lytAttndence, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();

                        Log.d(TAG, "Error : " + e.getMessage());

                      //  progAttndFrgmt.setVisibility(View.GONE);

                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e(TAG, "Error: " + error.getMessage());
                    handleVolleyError(error);
                    utilityClassObj.stopLoader();

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
   /* private  void GetTeachingPlan(){
        progAttndFrgmt.setVisibility(View.VISIBLE);
        String url = pref.getURL() + URLEndPoints.GetActionfacultyDetails_URL(URLEndPoints.Constance_StudSessionId,URLEndPoints.Constance_StudSemesterType, URLEndPoints.Constance_StudBranchStandardId, URLEndPoints.Constance_StudentCenterCode);
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

                            university_syllabus_adpter = new University_syllabus_Adpter(getActivity(), univSubjArrayList,teachPlanSubjArrayList);
                            dayWiseList.setLayoutManager(new LinearLayoutManager(getActivity()));
                            dayWiseList.setAdapter(university_syllabus_adpter);

                            university_syllabus_adpter.setOnItemClickListener(new University_syllabus_Adpter.OnItemClickListener() {
                                @Override
                                public void onItemClick(View itemView, int position,String name) {

                                    String subjectGroupId = univSubjArrayList.get(position).getSUBJECT_GROUP_ID();
                                    String subAPNo = univSubjArrayList.get(position).getAPPLICABLE_NUMBER();
                                    String subjectDes = univSubjArrayList.get(position).getSUBJECT_DESCRIPTION();
                                    URLEndPoints.ConstanceSUB_GROUPID =subjectGroupId;
                                    URLEndPoints.ConstanceSUBAPPLICABLE_NO =subAPNo;
                                    URLEndPoints.ConstanceSUB_DES =subjectDes;
                                    URLEndPoints.ConstanceSUB_TH =univSubjArrayList.get(position).getTYPE_SHORT_NAME();
                                    URLEndPoints.ConstanceSUB_EMPID=teachPlanSubjArrayList.get(position).getEmployee_Id();
                                    URLEndPoints.ConstanceSUB_profName=teachPlanSubjArrayList.get(position).getPROF_EMP_FN_MN_SHORT();
                                    URLEndPoints.ConstanceSUBJECT_DETAIL_ID =teachPlanSubjArrayList.get(position).getSUBJECT_DETAIL_ID();
                                    URLEndPoints.ConstanceSUB_NAME=teachPlanSubjArrayList.get(position).getSUBJECT_DESCRIPTION();
                                    if (name.equalsIgnoreCase("SYLLABUS")){

                                        Bundle bundle=new Bundle();
                                        bundle.putString("SYLLABUS_VIEW", "SYLLABUS");
                                        fragment = new UnitList_Details__US_Fragment();
                                        fragment.setArguments(bundle);
                                        loadFragmentMain(fragment);

                                        txt_toolbarName.setText("SYLLABUS");

                                    }else if (name.equalsIgnoreCase("TEACHING PLAN")){

                                        String  StuDetailId = teachPlanSubjArrayList.get(position).getSUBJECT_DETAIL_ID();
                                                String  EmpId=teachPlanSubjArrayList.get(position).getEmployee_Id();
                                                String  StuBatchId= teachPlanSubjArrayList.get(position).getSta();

                                                Bundle bundle=new Bundle();
                                        bundle.putString("TEACHING_PLAN_VIEW", "TEACHING PLAN");

                                        fragment = new UnitList_Details__US_Fragment();
                                        fragment.setArguments(bundle);
                                        loadFragmentMain(fragment);
                                        txt_toolbarName.setText("SYLLABUS -(TEACHING PLAN)");

                                    }else if (name.equalsIgnoreCase("STATUS")){

                                        Bundle bundle=new Bundle();
                                        bundle.putString("STATUS_VIEW", "STATUS");
                                        fragment = new UnitList_Details__US_Fragment();
                                        fragment.setArguments(bundle);
                                        loadFragmentMain(fragment);
                                        txt_toolbarName.setText("SYLLABUS -(STATUS)");
                                    }

                                }
                            });



                            progAttndFrgmt.setVisibility(View.GONE);



                        } else if (teachPlanSubjArrayList == null) {
                            Snackbar.make(lytAttndence, "Record not available.", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        }
                    } else if (teachPlanSubjModel.getStatus() == 0) {
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

    }*/

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
        transaction.replace(frame_syllabus_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}