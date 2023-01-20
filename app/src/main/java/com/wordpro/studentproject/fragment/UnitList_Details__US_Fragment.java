package com.wordpro.studentproject.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.UriMatcher;
import android.graphics.Color;
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
import com.wordpro.studentproject.activities.syllabus_menu.SyllStatusActivity;
import com.wordpro.studentproject.activities.syllabus_menu.TeachPlanSubjActivity;
import com.wordpro.studentproject.activities.syllabus_menu.TeachingPlanActivity;
import com.wordpro.studentproject.activities.syllabus_menu.UnivSubjectActivity;
import com.wordpro.studentproject.activities.syllabus_menu.UniversitySyllbusActivity;
import com.wordpro.studentproject.adapter.Status_Syllabus_Adpter;
import com.wordpro.studentproject.adapter.SubjectDayWiseAttendanceAdpter;
import com.wordpro.studentproject.adapter.SubjectWiseAttendanceAdpter;

import com.wordpro.studentproject.adapter.TeachPlanAdapter;
import com.wordpro.studentproject.adapter.TeacherPlan_Syllabus_Adpter;
import com.wordpro.studentproject.adapter.TimeLine_Syllabus_Adpter;
import com.wordpro.studentproject.adapter.UnivSyllabusExpandAdpter;
import com.wordpro.studentproject.adapter.University_syllabus_Adpter;
import com.wordpro.studentproject.app.MyApplication;
import com.wordpro.studentproject.helper.PrefManager;
import com.wordpro.studentproject.model.DaywiseAttndModel;
import com.wordpro.studentproject.model.SubjectwiseFacultyModel;
import com.wordpro.studentproject.model.SyllStatusModel;
import com.wordpro.studentproject.model.TeachPlanDataModel;
import com.wordpro.studentproject.model.UnivSyllabusModel;
import com.wordpro.studentproject.utils.UtilityClass;
import com.wordpro.studentproject.webConfig.URLEndPoints;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import static com.wordpro.studentproject.R.id.frame_container_Action;
import static com.wordpro.studentproject.R.id.frame_syllabus_container;
import static com.wordpro.studentproject.activities.NavigationActivity.fragSyllabus;
import static com.wordpro.studentproject.activities.NavigationActivity.studBranchStandardId;
import static com.wordpro.studentproject.activities.NavigationActivity.studCenterCode;
import static com.wordpro.studentproject.activities.NavigationActivity.studSemesterType;
import static com.wordpro.studentproject.activities.NavigationActivity.studSessionId;


public class UnitList_Details__US_Fragment extends Fragment  {
    private static String TAG = UnitList_Details__US_Fragment.class.getSimpleName();
    private LinearLayout  main_linear_view,lay_dateView;
    Fragment me=this;
    UnitList_Details__US_Fragment actionNewsFragment;
    List<String> listDataHeader;
    String studCenterCode,studDepartmentNumber,student_id,studBranchStandardId;
    ProgressDialog mProgressDialog;
    private PrefManager pref;
    ProgressBar progAttndFrgmt;
    private String SYALLBUS,TEACH_PLAN,STATUS;
    private LinearLayout  lytAttndence;
    private RecyclerView rec_AllUnit;
    private CardView card_view_sub,card_view11;
    private TextView  txt_sub_name,txt_Tm,t,tt,txt_tolLecture,txt_Com_Lecture,total_unit_Value,
            txt_prof_Name,txt_syllabus,txt_teachningPlan,txt_Status;
    private TextView  txt_UnitName,txt_UnitII,txt_UnitIII,txt_UnitIV,txt_UnitV,txt_UnitVI,txt_UnitI;
    private TimeLine_Syllabus_Adpter timeLine_syllabus_adpter;
    private TeacherPlan_Syllabus_Adpter teacherPlan_syllabus_adpter;
    private Status_Syllabus_Adpter syllabusStatusAdapter;

    public static UnivSyllabusModel univSyllabusModel;
    public static ArrayList<UnivSyllabusModel.DataBean> univSyllabusArrayList;

    public static TeachPlanDataModel teachPlanDataModel;
    public static ArrayList<TeachPlanDataModel.DataBean> teachPlanDataArrayList;

    public static SyllStatusModel syllStatusModel;
    public static ArrayList<SyllStatusModel.DataBean> syllStatusArrayList;

    private LinearLayout belowId;
    private ImageView back_press;
    private TextView txt_toolbarName;
    private UtilityClass utilityClassObj;

    public UnitList_Details__US_Fragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.uni_syll_unit_list, container, false);
        Typeface type_faceHeading = Typeface.createFromAsset(getActivity().getAssets(), "font/Poppins-Bold.ttf");
        Typeface type_faceContent = Typeface.createFromAsset(getActivity().getAssets(), "font/Poppins-Regular.ttf");
        pref = new PrefManager(getActivity());
        utilityClassObj  = new  UtilityClass(getActivity());
        try {
            SYALLBUS =getArguments().getString("SYLLABUS_VIEW");
        }catch (Exception e){
            SYALLBUS="ss";
            e.printStackTrace();
        }
        try {
            TEACH_PLAN =getArguments().getString("TEACHING_PLAN_VIEW");
        }catch (Exception e){
            e.printStackTrace();
            TEACH_PLAN ="tt";
        }
        try {
            STATUS =getArguments().getString("STATUS_VIEW");
        }catch (Exception e){
           e.printStackTrace();
            STATUS ="sss";
        }



        txt_sub_name=(TextView)view.findViewById(R.id.txt_sub_name);
        txt_Tm=(TextView)view.findViewById(R.id.txt_Tm);
        t=(TextView)view.findViewById(R.id.t);
        tt=(TextView)view.findViewById(R.id.tt);
        txt_tolLecture=(TextView)view.findViewById(R.id.txt_tolLecture);
        txt_Com_Lecture=(TextView)view.findViewById(R.id.total_unit_Value);
        total_unit_Value=(TextView)view.findViewById(R.id.total_unit_Value);
        txt_prof_Name=(TextView)view.findViewById(R.id.txt_prof_Name);
        txt_syllabus=(TextView)view.findViewById(R.id.txt_syllabus);
        txt_teachningPlan=(TextView)view.findViewById(R.id.txt_teachningPlan);
        txt_Status=(TextView)view.findViewById(R.id.txt_Status);
       // progAttndFrgmt = (ProgressBar) view.findViewById(R.id.progAttndFrgmt);
        /*txt_UnitName=(TextView)view.findViewById(R.id.txt_UnitName);*/
        tt=(TextView)view.findViewById(R.id.tt);
    /*    txt_UnitI=(TextView)view.findViewById(R.id.txt_UnitI);
        txt_UnitII=(TextView)view.findViewById(R.id.txt_UnitII);
        txt_UnitIII=(TextView)view.findViewById(R.id.txt_UnitIII);
        txt_UnitIV=(TextView)view.findViewById(R.id.txt_UnitIV);
        txt_UnitV=(TextView)view.findViewById(R.id.txt_UnitV);
        txt_UnitVI=(TextView)view.findViewById(R.id.txt_UnitVI);*/
        rec_AllUnit = (RecyclerView)view.findViewById(R.id.rec_AllUnit);
        lytAttndence = (LinearLayout) view.findViewById(R.id.lytAttndence);
        belowId = (LinearLayout) view.findViewById(R.id.belowId);
        lay_dateView = (LinearLayout) view.findViewById(R.id.lay_dateView);

        back_press = (ImageView) view.findViewById(R.id.back_press);
        txt_toolbarName = (TextView) view.findViewById(R.id.txt_toolbarName);
        txt_sub_name.setText(URLEndPoints.ConstanceSUB_DES);
        txt_prof_Name.setText("Prof "+ URLEndPoints.ConstanceTeacherNa);
        txt_Tm.setText(URLEndPoints.ConstanceSUB_TH);
        if (URLEndPoints.ConstanceLoginUser.equalsIgnoreCase("Student")){
            belowId.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.text_blue));
        }else {
            belowId.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.present));
        }
        belowId.setVisibility(View.VISIBLE);
        txt_toolbarName.setTypeface(type_faceHeading);
        if (SYALLBUS!=null && SYALLBUS.equalsIgnoreCase("SYLLABUS")){
           // belowId.setVisibility(View.VISIBLE);
            txt_toolbarName.setText("SYLLABUS -(UNIVERSITY -SYLLABUS)");
            funGetUnivSyllFun(URLEndPoints.ConstanceSUB_GROUPID,URLEndPoints.ConstanceSUBAPPLICABLE_NO,URLEndPoints.ConstanceSUB_DES);
        }else if (TEACH_PLAN!=null && TEACH_PLAN.equalsIgnoreCase("TEACHING PLAN")){
          //  belowId.setVisibility(View.VISIBLE);
            txt_toolbarName.setText("SYLLABUS -(TEACHING PLAN)");
            //teachPlanSubjArrayList.get(position).getEmployee_Id();
           // URLEndPoints.ConstanceSUB_profName="";//teachPlanSubjArrayList.get(position).getPROF_EMP_FN_MN_SHORT();
           // URLEndPoints.ConstanceSUBJECT_DETAIL_ID ="7284";//teachPlanSubjArrayList.get(position).getSUBJECT_DETAIL_ID();
           // URLEndPoints.ConstanceSUB_NAME= "Building Technology and Material (2015 course)";
            getTeachPlanData();

        }else if (STATUS!=null && STATUS.equalsIgnoreCase("STATUS")){
          //  belowId.setVisibility(View.VISIBLE);
            txt_toolbarName.setText("SYLLABUS -(STATUS)");
            getStatus();
        }
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

    private void funGetUnivSyllFun(String subject_group_id, String applicable_number, final String SUBJECT_DESCRIPTION) {
        utilityClassObj.startLoader(getActivity(),R.drawable.image_for_rotation);
        String url= pref.getURL()+ URLEndPoints.getNewUniversitySyllabus_URL(subject_group_id, applicable_number);
        Log.d(TAG, "University Syllabus : " + url);


        final StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, response.toString());
                try {

                    Gson gson = new Gson();
                    univSyllabusModel=gson.fromJson(response, UnivSyllabusModel.class);
                    if(univSyllabusModel.getStatus()==1){
                        utilityClassObj.stopLoader();
                        univSyllabusArrayList=(ArrayList<UnivSyllabusModel.DataBean>)univSyllabusModel.getData();
                        ArrayList<UnivSyllabusModel.DataBean> UnivSyllabus =new  ArrayList<UnivSyllabusModel.DataBean>();

                        if(univSyllabusArrayList!=null){

                            int  id =0;String UnitNo="";

                            for (int i =0;i<univSyllabusArrayList.size();i++){

                                if(univSyllabusArrayList.get(i).getSYLLABUS_GROUP_DET_ID().equalsIgnoreCase(UnitNo)){
                                    //   UnitNo =;
                                }else {
                                    UnitNo =univSyllabusArrayList.get(i).getSYLLABUS_GROUP_DET_ID();
                                    id ++;
                                }
                                UnivSyllabusModel.DataBean dd =univSyllabusArrayList.get(i);

                                String str1 =Integer.toString(id);
                                dd.setUNIT_NO(str1);
                                UnivSyllabus.add(dd);


                            }
                            System.out.println(UnivSyllabus);



                         /*   listDataHeader = new ArrayList<String>();

                            listDataChild = new HashMap<String, List<String>>();
                            listDataWeight = new HashMap<String, List<String>>();
                            listDataPeriods = new HashMap<String, List<String>>();

                            for(int i=0;i<univSyllabusArrayList.size();i++){

                                String SYLLABUS_DESCRIPTION=univSyllabusArrayList.get(i).getSYLLABUS_DESCRIPTION();
                                String WEIGHTAGE=univSyllabusArrayList.get(i).getWEIGHTAGE();
                                String NO_OF_LECTURES=univSyllabusArrayList.get(i).getUnit_Tot_Lectures();
                                listDataHeader.add(SYLLABUS_DESCRIPTION+"$"+WEIGHTAGE+"$"+NO_OF_LECTURES);

                            }


                            Object[] st = listDataHeader.toArray();
                            for (Object s : st) {
                                if (listDataHeader.indexOf(s) != listDataHeader.lastIndexOf(s)) {
                                    listDataHeader.remove(listDataHeader.lastIndexOf(s));
                                }
                            }

                            for (String ListDataHeader : listDataHeader) {
                                Log.d(TAG, "ListDataHeader :  "+ ListDataHeader);
                            }


                            for(int j=0;j<listDataHeader.size();j++){

                                String unitName=listDataHeader.get(j);
                                String[] header=unitName.split(Pattern.quote("$"));
                                String unitnameTilte=header[0];
                                String weightage=header[1];
                                String noLec=header[2];
                                List<String> topic=new ArrayList<String>();
                               *//* List<String> weightage=new ArrayList<String>();
                                List<String> periods=new ArrayList<String>();*//*
                                for(int i=0;i<univSyllabusArrayList.size();i++){

                                    String SYLLABUS_DESCRIPTION=univSyllabusArrayList.get(i).getSYLLABUS_DESCRIPTION();
                                    if(unitnameTilte.equalsIgnoreCase(SYLLABUS_DESCRIPTION)){

                                        String TOPIC_DESCRIPTION=univSyllabusArrayList.get(i).getTOPIC_DESCRIPTION();
                                        String TOPIC_NAME=univSyllabusArrayList.get(i).getTOPIC_NAME();
                                        topic.add(TOPIC_NAME+"$"+TOPIC_DESCRIPTION );
                                       *//* weightage.add(WEIGHTAGE);
                                        periods.add(NO_OF_LECTURES);*//*

                                    }
                                }

                                listDataChild.put(unitName,topic);
                               *//* listDataWeight.put(unitName,weightage);
                                listDataPeriods.put(unitName,periods);*//*
                            }

                          //  univSyllabusExpandAdpter=new UnivSyllabusExpandAdpter(UnivSubjectActivity.this,listDataHeader,listDataChild);

                            timeLine_syllabus_adpter = new TimeLine_Syllabus_Adpter(getActivity(), listDataHeader,listDataChild);*/
                            timeLine_syllabus_adpter = new TimeLine_Syllabus_Adpter(getActivity(), UnivSyllabus);
                            rec_AllUnit.setLayoutManager(new LinearLayoutManager(getActivity()));
                            rec_AllUnit.setAdapter(timeLine_syllabus_adpter);


                        }else if(univSyllabusArrayList==null){
                            Snackbar.make(lytAttndence, "Record not available.", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        }
                        utilityClassObj.stopLoader();
                    }else if(univSyllabusModel.getStatus()==0){

                        //mProgressDialog.hide();
                        Snackbar.make(lytAttndence, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                        utilityClassObj.stopLoader();
                    }

                }catch (Exception e) {
                    Snackbar.make(lytAttndence, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    Log.d(TAG, "Error : " + e.getMessage());
                    //mProgressDialog.hide();
                    utilityClassObj.stopLoader();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                handleVolleyError(error);
               // mProgressDialog.dismiss();
            }
        });
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                60000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        // Adding request to request queue

        MyApplication.getInstance().addToRequestQueue(stringRequest);

    }

    private void getTeachPlanData() {
        utilityClassObj.startLoader(getActivity(),R.drawable.image_for_rotation);
        String url=pref.getURL()+ URLEndPoints.newgetTeachingPlanDataURL(URLEndPoints.Constance_StudSessionId, URLEndPoints.Constance_StudSemesterType, URLEndPoints.Constance_EMPId, URLEndPoints.Constance_Sub_Details_ID, URLEndPoints.Constance_StudBranchStandardId, URLEndPoints.Constance_StudentCenterCode,URLEndPoints.Constance_Sub_Batch_Des_ID,URLEndPoints.ConstanceSUBAPPLICABLE_NO);
        Log.d(TAG, "Subject and faculty List : " + url);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, response.toString());
                try {
                    Gson gson = new Gson();
                    teachPlanDataModel=gson.fromJson(response, TeachPlanDataModel.class);
                    if(teachPlanDataModel.getStatus()==1){
                        utilityClassObj.stopLoader();
                        teachPlanDataArrayList=(ArrayList<TeachPlanDataModel.DataBean>)teachPlanDataModel.getData();
                        ArrayList<TeachPlanDataModel.DataBean> rrr =new  ArrayList<TeachPlanDataModel.DataBean>();
                       int  id =0;String UnitNo="";

                        for (int i =0;i<teachPlanDataArrayList.size();i++){

                            if(teachPlanDataArrayList.get(i).getSYLLABUS_DET_ID().equalsIgnoreCase(UnitNo)){
                                //   UnitNo =;
                            }else {
                                UnitNo =teachPlanDataArrayList.get(i).getSYLLABUS_DET_ID();
                                id ++;
                            }
                            TeachPlanDataModel.DataBean dd =teachPlanDataArrayList.get(i);

                            String str1 =Integer.toString(id);
                            dd.setUNIT_NO(str1);
                            rrr.add(dd);
                           // teachPlanDataArrayList.add(i,dd);

                        }
                             System.out.println(rrr);

                        if(teachPlanDataArrayList!=null){
                            //teachPlanAdapter=new TeachPlanAdapter(TeachPlanSubjActivity.this,teachPlanDataArrayList);
                            lay_dateView.setVisibility(View.VISIBLE);
                            teacherPlan_syllabus_adpter = new TeacherPlan_Syllabus_Adpter(getActivity(), rrr);
                            rec_AllUnit.setLayoutManager(new LinearLayoutManager(getActivity()));
                            rec_AllUnit.setAdapter(teacherPlan_syllabus_adpter);

                        }else{
                            Snackbar.make(lytAttndence, "Record not available.", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        }

                    }else if(teachPlanDataModel.getStatus()==0){
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


    private void getStatus(){
        utilityClassObj.startLoader(getActivity(),R.drawable.image_for_rotation);
        String url = pref.getURL() + URLEndPoints.newGetSyllabusComplStatus_URL(URLEndPoints.Constance_StudSessionId, URLEndPoints.Constance_StudSemesterType,URLEndPoints.Constance_EMPId, URLEndPoints.Constance_Sub_Details_ID,URLEndPoints.Constance_StudBranchStandardId, URLEndPoints.Constance_StudentCenterCode);
        Log.d(TAG, "Syllabus Status List : " + url);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d(TAG, response.toString());
                try {
                    Gson gson = new Gson();
                    syllStatusModel = gson.fromJson(response, SyllStatusModel.class);
                    if (syllStatusModel.getStatus() == 1) {
                        utilityClassObj.stopLoader();
                        syllStatusArrayList = (ArrayList<SyllStatusModel.DataBean>) syllStatusModel.getData();
                        if (syllStatusArrayList != null) {

                            syllabusStatusAdapter = new Status_Syllabus_Adpter(getActivity(), syllStatusArrayList);
                            rec_AllUnit.setLayoutManager(new LinearLayoutManager(getActivity()));
                            rec_AllUnit.setAdapter(syllabusStatusAdapter);

                        } else if (syllStatusArrayList == null) {
                            Snackbar.make(lytAttndence, "Record not available.", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                            utilityClassObj.stopLoader();
                        }

                    } else if (syllStatusModel.getStatus() == 0) {
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


}
