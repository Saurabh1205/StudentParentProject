package com.wordpro.studentproject.fragment;


import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DownloadManager;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.NetworkOnMainThreadException;
import android.os.PowerManager;
import android.os.StrictMode;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telephony.mbms.DownloadRequest;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
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
import com.wordpro.studentproject.activities.e_learning_menu.E_LearnAssignActivity;
import com.wordpro.studentproject.adapter.AssignmentAdapter;
import com.wordpro.studentproject.adapter.Assignment_List_Adapter;
import com.wordpro.studentproject.adapter.IndicatorAdapter;
import com.wordpro.studentproject.adapter.SubjectWiseAttendanceAdpter;
import com.wordpro.studentproject.adapter.University_syllabus_Adpter;
import com.wordpro.studentproject.app.MyApplication;
import com.wordpro.studentproject.helper.PrefManager;
import com.wordpro.studentproject.model.AssignmentModel;
import com.wordpro.studentproject.model.DocumPathModel;
import com.wordpro.studentproject.model.DownloadURLModel;
import com.wordpro.studentproject.utils.FileUtils;
import com.wordpro.studentproject.utils.UtilityClass;
import com.wordpro.studentproject.webConfig.URLEndPoints;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import retrofit2.http.Url;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.content.Context.POWER_SERVICE;
import static com.android.volley.VolleyLog.TAG;
import static com.wordpro.studentproject.activities.NavigationActivity.BASE_URL;
import static com.wordpro.studentproject.activities.NavigationActivity.fragE_learning;
import static com.wordpro.studentproject.activities.NavigationActivity.newsRecyVwAdpter;
import static com.wordpro.studentproject.activities.NavigationActivity.studBranchStandardId;
import static com.wordpro.studentproject.activities.NavigationActivity.studCenterCode;
import static com.wordpro.studentproject.activities.NavigationActivity.studSemesterType;
import static com.wordpro.studentproject.activities.NavigationActivity.studSessionId;
import static com.wordpro.studentproject.activities.NavigationActivity.student_id;
import static com.wordpro.studentproject.activities.VerficationActivity.newsArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class E_Lear_AssignmentFragment extends Fragment implements View.OnClickListener {

    public static String TAG = E_Lear_AssignmentFragment.class.getSimpleName();



    private static final int PERMISSION_REQUEST_CODE = 200;
    private static final int PICK_FILE_REQUEST = 1;
    PowerManager.WakeLock wakeLock;
    private final int REQUEST_READ_PHONE_STATE = 1;
    private String selectedFilePath;
    long fileSizeInKB;
   // private String SERVER_URL = "http://192.168.1.72:500/api/PostUpload";//"http://103.208.73.138:82/api/PostUpload";//"Faculty name


    public static final int DIALOG_DOWNLOAD_PROGRESS = 0;
    //initialize root directory
    File rootDir = Environment.getExternalStorageDirectory();
    //defining file name and url
    public String fileURL = "";
    //ArrayList<AssignmentModel.StudAssignDtlsBean> publishedAssgnList;
    public static AssignmentAdapter assignmentAdapter;

    String DOCU_UPLOAD_PATH = "", UPLOAD_DOCU_PATH_DTLS_ID = "",COMPLETPATH ="";
    private DownloadManager downloadManager;
    private long refid;
    private Uri Download_Uri;
    ProgressDialog dialog;
    public static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 123;
    String filePath = "-", fileName = "-";
    int assignmentPos;

    public static DocumPathModel documPathModel;
    public static ArrayList<DocumPathModel.DtEmpsalAnnualBean> docuPathArrayList;

    LinearLayout bottom_lay,belowId;
    private ImageView  back_press;
    private ProgressBar  progBarAssi;
    private TextView txt_toolbarName,act_submitted,act_publishASSi;
    public static AssignmentModel assignmentModel;
    public static ArrayList<AssignmentModel.StudAssignDtlsBean> studAssignDtlsArrayList;
    private PrefManager pref;
    private  RecyclerView recycle_view;
    private LinearLayout  lay_view;
    private Assignment_List_Adapter assignment_list_adapter;
    Typeface typefaceHeading;
    private  boolean SelectSubmitAssig =false;
    private String AssignmentList;
    private   int downloadedSize = 0;
    private UtilityClass utilityClassObj;



    int totalSize = 0;

    String dwnload_file_path = "https://coderzheaven.com/sample_folder/sample_file.png";

    public E_Lear_AssignmentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.e_lear_assignment_fragment, container, false);

        pref = new PrefManager(getActivity());
        if (checkPermission()) {
            requestPermissionAndContinue();
        }
        downloadManager = (DownloadManager) getActivity().getSystemService(Context.DOWNLOAD_SERVICE);
        getActivity().registerReceiver(onComplete,
                new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
        utilityClassObj =new UtilityClass(getActivity());
        recycle_view = (RecyclerView)view.findViewById(R.id.recycle_view);
        txt_toolbarName  = (TextView) view.findViewById(R.id.txt_toolbarName);
        back_press = (ImageView) view.findViewById(R.id.back_press);
        bottom_lay = (LinearLayout) view.findViewById(R.id.bottom_lay);
        progBarAssi =(ProgressBar) view.findViewById(R.id.progBarAssi);
        act_submitted  = (TextView) view.findViewById(R.id.act_submitted);
        act_publishASSi  = (TextView) view.findViewById(R.id.act_publishASSi);
        lay_view =(LinearLayout)   view.findViewById(R.id.lay_view);
        belowId =(LinearLayout)   view.findViewById(R.id.belowId);

        if (URLEndPoints.ConstanceLoginUser.equalsIgnoreCase("Student")){
            belowId.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.text_blue));
        }else {
            belowId.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.present));
        }


        try {
            getActivity().findViewById(R.id.txt_toolbarName);
        }catch (Exception e){
            e.printStackTrace();
        }

        back_press.setOnClickListener(this);
        act_publishASSi.setOnClickListener(this);
        act_submitted.setOnClickListener(this);
        AssignmentList = "Published Assignments";

        funGetAssgnDtl(AssignmentList);



        typefaceHeading = Typeface.createFromAsset(getActivity().getAssets(), "font/Poppins-Bold.ttf");

        act_publishASSi.setTextColor(ContextCompat.getColor(getActivity(), R.color.white));
        act_publishASSi.setBackground(ContextCompat.getDrawable(getActivity(),R.color.selector_color));
        act_submitted.setBackground(ContextCompat.getDrawable(getActivity(),R.color.white));
        belowId.setVisibility(View.GONE);
        return view;

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_press:
                SelectSubmitAssig =false;
               // Intent  intent = new Intent(getActivity(), NavigationActivity.class);
               // startActivity(intent);

              /*  bottom_lay.setVisibility(View.GONE);
                belowId.setVisibility(View.GONE);
                getActivity().onBackPressed();*/
                 getActivity().finish();
                break;

            case R.id.act_publishASSi:
                SelectSubmitAssig =false;
                belowId.setVisibility(View.GONE);
                TextView textview11 = (TextView) getActivity().findViewById(R.id.txt_toolbarName);
                textview11.setText("Assignments (Published Assignments)");
                textview11.setTypeface(typefaceHeading);
                act_publishASSi.setTextColor(ContextCompat.getColor(getActivity(), R.color.white));
                act_publishASSi.setBackground(ContextCompat.getDrawable(getActivity(),R.color.selector_color));
                act_submitted.setTextColor(ContextCompat.getColor(getActivity(), R.color.gray_black));
                act_submitted.setBackground(ContextCompat.getDrawable(getActivity(),R.color.white));
                AssignmentList = "Published Assignments";
                funGetAssgnDtl(AssignmentList);
                break;
            case R.id.act_submitted:
                SelectSubmitAssig =true;
                TextView textview = (TextView) getActivity().findViewById(R.id.txt_toolbarName);
                textview.setText("Assignments (Submitted Assignments)");
                textview.setTypeface(typefaceHeading);
                belowId.setVisibility(View.GONE);
              //  txt_toolbarName.setText("Assignments (Submitted Assignments)");
                act_publishASSi.setTextColor(ContextCompat.getColor(getActivity(), R.color.gray_black));
                act_publishASSi.setBackground(ContextCompat.getDrawable(getActivity(),R.color.white));
                act_submitted.setTextColor(ContextCompat.getColor(getActivity(), R.color.white));
                act_submitted.setBackground(ContextCompat.getDrawable(getActivity(),R.color.selector_color));
                AssignmentList = "Submitted Assignments";
                funGetAssgnDtl(AssignmentList);
                break;
        }

    }
    private void funGetAssgnDtl(String AssignmentListName) {
        // progBarAssi.setVisibility(View.VISIBLE);
        utilityClassObj.startLoader(getActivity(),R.drawable.image_for_rotation);
        String url = pref.getURL() + URLEndPoints.GetStudAssignment_URL( URLEndPoints.Constance_StudentID, URLEndPoints.Constance_StudSessionId,URLEndPoints.Constance_StudBranchStandardId, URLEndPoints.Constance_StudSemesterType, URLEndPoints.Constance_StudentCenterCode);
        Log.d(TAG, "Assignment URL  :  " + url);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // progBarAssi.setVisibility(View.GONE);
                utilityClassObj.stopLoader();
                Log.d(TAG, response.toString());


                try {

                    Gson gson = new Gson();
                    assignmentModel = gson.fromJson(response, AssignmentModel.class);
                    if (assignmentModel.getStatus() == 1) {
                        studAssignDtlsArrayList = (ArrayList<AssignmentModel.StudAssignDtlsBean>) assignmentModel.getStudAssign_Dtls();
                        ArrayList<AssignmentModel.StudAssignDtlsBean> ff =new ArrayList<AssignmentModel.StudAssignDtlsBean>();

                        for (int i =0;i<studAssignDtlsArrayList.size();i++){
                            if (studAssignDtlsArrayList.get(i).getSTU_SUBMISSION_STATUS().equalsIgnoreCase("SUBMITTED")){
                                ff.add(studAssignDtlsArrayList.get(i));
                            }
                        }
                        if(studAssignDtlsArrayList != null) {
                            if (AssignmentListName.equalsIgnoreCase("Submitted Assignments")){

                                assignment_list_adapter = new Assignment_List_Adapter(getActivity(), ff,AssignmentListName);
                                recycle_view.setLayoutManager(new LinearLayoutManager(getActivity()));
                                recycle_view.setAdapter(assignment_list_adapter);
                                assignment_list_adapter.notifyDataSetChanged();


                                assignment_list_adapter.setOnItemClickListener(new Assignment_List_Adapter.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(View itemView, int position ,String download) {

                                        if(download.equalsIgnoreCase("download")){
                                            String strfileName = ff.get(position).getTASK_FILE_NAME();
                                            String fileName, Extent;
                                            String ORG,Path;
                                            String[] filenameNextension = strfileName.split(Pattern.quote("."));
                                            String originalfileName = filenameNextension[0];

                                            //"\\\\" +
                                            String sourcePath = ff.get(position).getDOWNLOAD_PATH();
                                            String str = sourcePath.substring(sourcePath.lastIndexOf("\\") + 1);
                                            String[] value = str.split(Pattern.quote("."));
                                            fileName = value[0];
                                            Extent = value[1];


                                            String[] colon = sourcePath.split(Pattern.quote(":"));
                                            ORG= colon[0];
                                            Path= colon[1];


                                            String[] data = fileName.split(Pattern.quote("_"));
                                            String employeeId = data[0];
                                            employeeId = employeeId.substring(1);

                                            String sourcePathsplit = "\\\\" + ff.get(position).getDOWNLOAD_PATH();
                                            String[] source = sourcePathsplit.split(Pattern.quote("Employee\\"));
                                            String originalSource = source[0] + "Employee\\" + employeeId;

                                            String comUrl = BASE_URL;          //http://117.247.82.252:500/api/
                                            String[] s = comUrl.split(Pattern.quote("api"));
                                            String downloadURL = s[0];


                                            String test = studAssignDtlsArrayList.get(position).getASSIGN_FILE_DETAILS();
                                            String[] sou = test.split(Pattern.quote("\\"));
                                            //  String origin = sou[1];
                                            String origin =  sou[0]+"/"+sou[1];
                                            String uu ="";
                                            String q,w;
                                            String str1 = test.substring(test.lastIndexOf("\\") + 1);
                                            String[] filenameNextension1 = str1.split(Pattern.quote("."));
                                            q = filenameNextension1[0];
                                            w = filenameNextension1[1];
                                            String a =q+w;
                                            String filepathname=studAssignDtlsArrayList.get(position).getDOCU_UPLOAD_STATIC_PATH().replace("\\","/");
                                            Log.e("filePath==","=="+filepathname);
                                            try {
                                                Log.e("filePath==","=="+test);
                                                uu =  studAssignDtlsArrayList.get(position).getSTATIC_IP()+""+filepathname+"/"+origin;
                                                // uu =  studAssignDtlsArrayList.get(position).getSTATIC_IP()+studAssignDtlsArrayList.get(position).getDOCU_UPLOAD_STATIC_PATH()+"//"+test;
                                            }catch (Exception e){
                                                e.printStackTrace();
                                            }
                                            String path ="http://"+uu;
                                            funDownloadFile(path,str1);
                                        }else if (download.equalsIgnoreCase("upload")){
                                            assignmentPos = position;

                                            if (!checkPermission()) {
                                                showFileChooser();
                                            } else {
                                                if (checkPermission()) {
                                                    requestPermissionAndContinue();
                                                } else {
                                                    showFileChooser();
                                                }

                                            }
                                            // submitUploadDoc(position);
                                        }



                                    }
                                });
                            }else {
                                assignment_list_adapter = new Assignment_List_Adapter(getActivity(), studAssignDtlsArrayList,AssignmentListName);
                                recycle_view.setLayoutManager(new LinearLayoutManager(getActivity()));
                                recycle_view.setAdapter(assignment_list_adapter);
                                assignment_list_adapter.notifyDataSetChanged();


                                assignment_list_adapter.setOnItemClickListener(new Assignment_List_Adapter.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(View itemView, int position ,String download) {

                                        if(download.equalsIgnoreCase("download")){
                                            String strfileName = studAssignDtlsArrayList.get(position).getTASK_FILE_NAME();
                                            String fileName, Extent;
                                            String ORG,Path;
                                            String[] filenameNextension = strfileName.split(Pattern.quote("."));
                                            String originalfileName = filenameNextension[0];

                                            //"\\\\" +
                                            String sourcePath = studAssignDtlsArrayList.get(position).getDOWNLOAD_PATH();
                                            String str = sourcePath.substring(sourcePath.lastIndexOf("\\") + 1);
                                            String[] value = str.split(Pattern.quote("."));
                                            fileName = value[0];
                                            Extent = value[1];


                                            String[] colon = sourcePath.split(Pattern.quote(":"));
                                            ORG= colon[0];
                                            Path= colon[1];


                                            String[] data = fileName.split(Pattern.quote("_"));
                                            String employeeId = data[0];
                                            employeeId = employeeId.substring(1);

                                            String sourcePathsplit = "\\\\" + studAssignDtlsArrayList.get(position).getDOWNLOAD_PATH();
                                            String[] source = sourcePathsplit.split(Pattern.quote("Employee\\"));
                                            String originalSource = source[0] + "Employee\\" + employeeId;


                                            String comUrl = BASE_URL;          //http://117.247.82.252:500/api/
                                            String[] s = comUrl.split(Pattern.quote("api"));
                                            String downloadURL = s[0];

                                            progBarAssi.setVisibility(View.VISIBLE);


                                            String test = studAssignDtlsArrayList.get(position).getASSIGN_FILE_DETAILS();
                                            String[] sou = test.split(Pattern.quote("\\"));
                                            String datas =  sou[0]+"/"+sou[1];
                                            String origin = sou[1];

                                            String uu ="";
                                            String q,w;
                                            String str1 = test.substring(test.lastIndexOf("\\") + 1);
                                            String[] filenameNextension1 = str1.split(Pattern.quote("."));
                                            q = filenameNextension1[0];
                                            w = filenameNextension1[1];
                                            String a =q+w;

                                            String filepathname=studAssignDtlsArrayList.get(position).getDOCU_UPLOAD_STATIC_PATH().replace("\\","/");
                                            Log.e("filePath==","=="+filepathname);
//                                                try {
                                            uu =  studAssignDtlsArrayList.get(position).getSTATIC_IP()+""+filepathname+"/"+datas;
//                                                }catch (Exception e){
//                                                    e.printStackTrace();
//                                                }
                                            String path ="http://"+uu;
                                            funDownloadFile(path,str1);



                                        }else if (download.equalsIgnoreCase("upload")){
                                            assignmentPos = position;

                                            if (!checkPermission()) {
                                                showFileChooser();
                                            } else {
                                                if (checkPermission()) {
                                                    requestPermissionAndContinue();
                                                } else {
                                                    showFileChooser();
                                                }

                                            }
                                            // submitUploadDoc(position);
                                        }



                                    }
                                });
                            }




                                /*assignment_list_adapter = new Assignment_List_Adapter(getActivity(), studAssignDtlsArrayList,AssignmentListName);
                                recycle_view.setLayoutManager(new LinearLayoutManager(getActivity()));
                                recycle_view.setAdapter(assignment_list_adapter);
                                assignment_list_adapter.notifyDataSetChanged();


                                assignment_list_adapter.setOnItemClickListener(new Assignment_List_Adapter.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(View itemView, int position ,String download) {

                                        if(download.equalsIgnoreCase("download")){
                                            String strfileName = studAssignDtlsArrayList.get(position).getTASK_FILE_NAME();
                                            String fileName, Extent;
                                            String ORG,Path;
                                            String[] filenameNextension = strfileName.split(Pattern.quote("."));
                                            String originalfileName = filenameNextension[0];

                                            //"\\\\" +
                                            String sourcePath = studAssignDtlsArrayList.get(position).getDOWNLOAD_PATH();
                                            String str = sourcePath.substring(sourcePath.lastIndexOf("\\") + 1);
                                            String[] value = str.split(Pattern.quote("."));
                                            fileName = value[0];
                                            Extent = value[1];


                                            String[] colon = sourcePath.split(Pattern.quote(":"));
                                            ORG= colon[0];
                                            Path= colon[1];


                                            String[] data = fileName.split(Pattern.quote("_"));
                                            String employeeId = data[0];
                                            employeeId = employeeId.substring(1);

                                            String sourcePathsplit = "\\\\" + studAssignDtlsArrayList.get(position).getDOWNLOAD_PATH();
                                            String[] source = sourcePathsplit.split(Pattern.quote("Employee\\"));
                                            String originalSource = source[0] + "Employee\\" + employeeId;
                                            String org ="\\\192.168.1.72/"+ORG+Path;
                                            String uu = org;

                                            funDownloadURL(fileName, Extent, org, "file", employeeId, fileName);
                                        }else if (download.equalsIgnoreCase("upload")){
                                            assignmentPos = position;

                                            if (!checkPermission()) {
                                                showFileChooser();
                                            } else {
                                                if (checkPermission()) {
                                                    requestPermissionAndContinue();
                                                } else {
                                                    showFileChooser();
                                                }

                                            }
                                            // submitUploadDoc(position);
                                        }



                                    }
                                });
*/

                        }else {
                            Snackbar.make(lay_view, "Record not available.", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        }




                    } else if (assignmentModel.getStatus() == 0) {
                        Snackbar.make(lay_view, "Record not available.", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }


                } catch (Exception e) {

                    Snackbar.make(lay_view, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                    Log.d(TAG, "Error : " + e.getMessage());
                    // progBarAssi.setVisibility(View.GONE);
                    utilityClassObj.stopLoader();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                handleVolleyError(error);
                // progBarAssi.setVisibility(View.GONE);
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
    private void funDownloadURL(final String fileName, final String extent, final String sourcePath, final String file, final String employee, final String originalfileName) {
        progBarAssi.setVisibility(View.VISIBLE);

        String url = BASE_URL + URLEndPoints.PostDownloadCopyTo_URL;
        Log.d(TAG, url);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Log.d(TAG, "Response : " + BASE_URL + "" + response.toString());
                try {

                    //{"status":1,"data":[{"FilePath":"APP_DOWNLOAD\\25012019024218714.docx","FileName":"documentfile"}]}
                    Gson gson = new Gson();
                    DownloadURLModel downloadURLModel = gson.fromJson(response, DownloadURLModel.class);
                    if (downloadURLModel.getStatus() == 1) {
                        progBarAssi.setVisibility(View.GONE);
                        ArrayList<DownloadURLModel.DataBean> downloadURLArrayList = (ArrayList<DownloadURLModel.DataBean>) downloadURLModel.getData();
                        if (downloadURLArrayList.size() != 0 && downloadURLArrayList != null) {

                            String str = BASE_URL;          //http://117.247.82.252:500/api/
                            String[] s = str.split(Pattern.quote("api"));
                            String downloadURL = s[0] + downloadURLArrayList.get(0).getFilePath();

                            Download_Uri = Uri.parse(downloadURL);

                          //  funDownloadFile(fileName + "." + extent);

                        }

                    } else {

                        if (downloadURLModel.getStatus() == 0){
                            String error =downloadURLModel.getError();
                            progBarAssi.setVisibility(View.GONE);

                            Snackbar.make(lay_view, error, Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();

                           // String path ="\\192.168.1.72/D/EmployeeDocuments/Assignment/Employee/E1032170368_21020_155496836262.pdf";
                           // funDownloadFile(path);
                        }

                    }


                } catch (Exception e) {
                    progBarAssi.setVisibility(View.GONE);
                    Log.d(TAG, "Error : " + e.getMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progBarAssi.setVisibility(View.GONE);
                handleVolleyError(error);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("fileName", fileName);
                params.put("Extent", extent);
                params.put("sourcePath", sourcePath);
                params.put("imageType", file);
                params.put("Employee_id", employee);
                params.put("Persone_Type", "STUDENT");
                params.put("OrignalName", originalfileName);

                Log.e(TAG, "InActive Posting params: " + params.toString());
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


    BroadcastReceiver onComplete = new BroadcastReceiver() {

        public void onReceive(Context ctxt, Intent intent) {

            long referenceId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);


            Log.e("IN", "" + referenceId);

            Log.e("INSIDE", "" + referenceId);
            NotificationCompat.Builder mBuilder =
                    new NotificationCompat.Builder(getContext())
                            .setSmallIcon(R.drawable.threeechoo)
                            .setContentTitle("ECHOAPPDOWNLOAD")
                            .setContentText("All Download completed");


            NotificationManager notificationManager = (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(455, mBuilder.build());


            //  }

        }
    };



    private void funDownloadFile(String URL,String fileName) {
        Log.e("Download Path","URL=="+URL);
//
        DownloadManager downloadmanager = (DownloadManager) getContext().getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uri = Uri.parse(URL);

        DownloadManager.Request request = new DownloadManager.Request(uri);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,"/StudentFolder/Assignment/"+fileName);
        request.setTitle(fileName);
        request.setDescription("Downloading");
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setVisibleInDownloadsUi(false);
        request.allowScanningByMediaScanner();
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);
        downloadmanager.enqueue(request);
        progBarAssi.setVisibility(View.GONE);

        Snackbar.make(lay_view, "File downloaded successfully", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();

      /*  request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
        request.setAllowedOverRoaming(false);
        request.setTitle("Downloading " + originalfilename);
        request.setDescription("Downloading... " + originalfilename);
        request.setVisibleInDownloadsUi(true);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, originalfilename);

        refid = downloadManager.enqueue(request);

        Log.e("OUT", "" + refid);
*/
    }

    private boolean checkPermission() {

        return ContextCompat.checkSelfPermission(getActivity().getApplicationContext(), WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(getActivity().getApplicationContext(), READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermissionAndContinue() {
        if (ContextCompat.checkSelfPermission(getActivity().getApplicationContext(), WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(getActivity().getApplicationContext(), READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), WRITE_EXTERNAL_STORAGE)
                    && ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), READ_EXTERNAL_STORAGE)) {
                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(getActivity());
                alertBuilder.setCancelable(true);
                alertBuilder.setTitle("Permission Necessary");
                alertBuilder.setMessage("Storage permission is necessary");
                alertBuilder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                    public void onClick(DialogInterface dialog, int which) {
                        ActivityCompat.requestPermissions(getActivity(), new String[]{WRITE_EXTERNAL_STORAGE
                                , READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
                    }
                });
                AlertDialog alert = alertBuilder.create();
                alert.show();
                Log.e("", "permission denied, show dialog");
            } else {
                ActivityCompat.requestPermissions(getActivity(), new String[]{WRITE_EXTERNAL_STORAGE,
                        READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
            }
        } else {
            showFileChooser();
        }
    }

    private void showFileChooser() {

        String[] mimeTypes =
                {"application/msword", "application/vnd.openxmlformats-officedocument.wordprocessingml.document", // .doc & .docx
                        "application/vnd.ms-excel", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", // .xls & .xlsx
                        "application/pdf",
                        "application/jpg"};

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            intent.setType(mimeTypes.length == 1 ? mimeTypes[0] : "*/*");
            if (mimeTypes.length > 0) {
                intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);
            }
        } else {
            String mimeTypesStr = "";
            for (String mimeType : mimeTypes) {
                mimeTypesStr += mimeType + "|";
            }
            intent.setType(mimeTypesStr.substring(0, mimeTypesStr.length() - 1));
        }

        //starts new activity to select file and return data
        startActivityForResult(Intent.createChooser(intent, "Choose File to Upload.."), PICK_FILE_REQUEST);
    }

    private void  submitUploadDoc(int  position){

        String STUDENT_FILE_NAME = COMPLETPATH; //studAssignDtlsArrayList.get(position).getSTUD_UPLOAD_FILE();

        if (STUDENT_FILE_NAME ==null ||  STUDENT_FILE_NAME.equalsIgnoreCase("") || STUDENT_FILE_NAME.isEmpty()) {

            Snackbar.make(lay_view, "Upload the Assignment file before submission", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();

        } else {

            String STU_SUBMISSION_STATUS = studAssignDtlsArrayList.get(position).getSTU_SUBMISSION_STATUS();
            String ASSIGN_SUBMI_UPTO_DATE = studAssignDtlsArrayList.get(position).getASSIGN_SUBMI_UPTO_DATE();
            String ASSIGN_DESCRIPTION = studAssignDtlsArrayList.get(position).getASSIGN_DESCRIPTION();
            String BRANCH_STANDARD_ID = studAssignDtlsArrayList.get(position).getBRANCH_STANDARD_ID();
            String SUBJECT_DETAIL_ID = studAssignDtlsArrayList.get(position).getSUBJECT_DETAIL_ID();
            String APPLICABLE_NUMBER = studAssignDtlsArrayList.get(position).getAPPLICABLE_NUMBER();
            String ASSIGN_SUBMISSION_ID = studAssignDtlsArrayList.get(position).getASSIGN_SUBMISSION_ID();
            String ASSIGN_ID = studAssignDtlsArrayList.get(position).getASSIGN_ID();

            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            Date date = new Date();
            String currentDate = formatter.format(date);


            Date lastDt = null, currntDt = null;
            SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
            try {
                lastDt = format1.parse(ASSIGN_SUBMI_UPTO_DATE);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            try {
                currntDt = format1.parse(currentDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.SSSS");
            String dateTime = sdf.format(date);
            Log.d(TAG, "Date & Time : " + dateTime);
            String[] dateTimeArray = dateTime.split(" ");
            String Dt = dateTimeArray[0];
            String[] d = Dt.split("-");
            String day = d[0];
            String month = d[1];
            String yr = d[2];
            String yrStr = Integer.toString(Integer.parseInt(yr));
            String yrStrEnd = yrStr.substring(yrStr.length() - 2);
            Dt = day + month + yrStrEnd;

            String Time = dateTimeArray[1];
            Time = Time.replace(":", "");
            Time = Time.replace(".", "");

            String extension;
            try {
                 extension = STUDENT_FILE_NAME.substring(STUDENT_FILE_NAME.lastIndexOf("."));
            }catch (Exception e){
                 extension = STUDENT_FILE_NAME;
            }

            String fold;
            String filename = "S" + student_id + "_" + Dt + "_" + Time;
            String datafold = studSessionId + "_" + BRANCH_STANDARD_ID + "_" + studSemesterType + "_" + SUBJECT_DETAIL_ID + "_" + APPLICABLE_NUMBER;
            String p1 = docuPathArrayList.get(0).getSTANDARD_PATH();
            String p2 = docuPathArrayList.get(0).getDOCU_UPLOAD_PATH();
            //String DemoPath =p1 +p2;
            String ActualPath = datafold + "\\" + filename + "" + extension;
            String DemoPath ="//"+p1 +p2 +filename;
            if (DOCU_UPLOAD_PATH.equalsIgnoreCase("") || DOCU_UPLOAD_PATH.isEmpty()) {
                Snackbar.make(lay_view, "Upload the Assignment file" + ".", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            } else {
                fold = DOCU_UPLOAD_PATH + datafold;
            }

            //file://192.168.1.72/d/EmployeeDocuments/Assignment/Student/E1032170368_11020_17535425615.pdf
 //DemoPath
            //String submitString =    ASSIGN_SUBMISSION_ID + "µ" + ASSIGN_ID + "µ" + student_id + "µ" + COMPLETPATH;//+ "µ" + "SUBMITTED" + "µ" + studCenterCode + "µ" + STUDENT_FILE_NAME;
            String submitString = ASSIGN_SUBMISSION_ID + "µ" + ASSIGN_ID + "µ" + student_id + "µ" + UPLOAD_DOCU_PATH_DTLS_ID + "µ" +  COMPLETPATH+ "µ" + "SUBMITTED" + "µ" + studCenterCode + "µ" + STUDENT_FILE_NAME;
            Log.d(TAG, "String : " + submitString);


            /*if (STU_SUBMISSION_STATUS.equalsIgnoreCase("") || STU_SUBMISSION_STATUS.isEmpty() && currntDt.before(lastDt) || currntDt.equals(lastDt)) {

                //submit
                funSubmitAssignment(submitString);

            } else if (STU_SUBMISSION_STATUS.equalsIgnoreCase("SENT_FOR_CORRECTION")) {

                //submit
                funSubmitAssignment(submitString);



            } else {
                Snackbar.make(lay_view, "Last Date for " + ASSIGN_DESCRIPTION + " submission was " + ASSIGN_SUBMI_UPTO_DATE + ".", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }*/
            // String path ="http://103.96.41.134/SASHA.pdf";
         //   String  subPath = ASSIGN_SUBMISSION_ID + "µ" + ASSIGN_ID + "µ" + student_id + "µ" +filename;

            funSubmitAssignment(submitString);
        }
    }
    private void funSubmitAssignment(final String submitString) {

        progBarAssi.setVisibility(View.VISIBLE);
        String url = BASE_URL + URLEndPoints.AssStudSubmit_URL;
       // String url = "http://103.96.41.134/api/"+URLEndPoints.AssStudSubmit_URL;
        Log.d(TAG, "URL : " + url);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d(TAG, response.toString());

                try {
                    progBarAssi.setVisibility(View.GONE);
                    //{"status":1,"STATUS":"TRUE","REASON":"RECORD SAVED SUCCESSFULLY"}
                    JSONObject responseObj = new JSONObject(response);

                    // Parsing json object response
                    // response will be a json object
                    int status = responseObj.getInt("status");


                    if (status == 1) {
                        String STATUS = responseObj.getString("STATUS");
                        String REASON = responseObj.getString("REASON");

                        if (STATUS.equalsIgnoreCase("TRUE")) {

                            Snackbar.make(lay_view, REASON, Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();


                            new Handler().postDelayed(new Runnable() {

                                // Using handler with postDelayed called runnable run method

                                @Override
                                public void run() {

                                    funGetAssgnDtl(AssignmentList);

                                }
                            }, 5 * 1000); // wait for 5 seconds


                        } else if (STATUS.equalsIgnoreCase("FALSE")) {
                            Snackbar.make(lay_view, REASON, Snackbar.LENGTH_LONG).setAction("Action", null).show();
                            progBarAssi.setVisibility(View.GONE);
                        }

                    }
                } catch (Exception e) {
                    progBarAssi.setVisibility(View.GONE);
                    Snackbar.make(lay_view, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    Log.d(TAG, "Error : " + e.getMessage());

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Error: " + error.getMessage());
                handleVolleyError(error);
                progBarAssi.setVisibility(View.GONE);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("P_ASG_SUBMISS_STR", submitString);
                params.put("P_STUDENT_ID", student_id);

                Log.e(TAG, "InActive Posting params: " + params.toString());

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
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == PICK_FILE_REQUEST) {
                if (data == null) {
                    //no data present
                    return;
                }

                int permissionCheck = ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_PHONE_STATE);

                if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_PHONE_STATE}, REQUEST_READ_PHONE_STATE);
                } else {
                    //TODO
                    PowerManager powerManager = (PowerManager) getActivity().getSystemService(POWER_SERVICE);
                    wakeLock = powerManager.newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK, TAG);
                    wakeLock.acquire();

                }


                if (checkPermissionREAD_EXTERNAL_STORAGE(getActivity())) {
                    // do your stuff..

                    Uri selectedFileUri = data.getData();
                    selectedFilePath = FileUtils.getRealPath(getActivity(), selectedFileUri);
                    Log.i(TAG, "Selected File Path:" + selectedFilePath);

                    File file = new File(selectedFilePath);
                    int file_size = Integer.parseInt(String.valueOf(file.length() / 1024));
                    fileSizeInKB = file_size;
                    // Get length of file in bytes

                    Log.d(TAG, "File size  " + fileSizeInKB);

                    // Log.d(TAG, "File size  " + fileSizeInBytes + " " + fileSizeInKB + " " + fileSizeInMB);

                    if (selectedFilePath != null && !selectedFilePath.equals("")) {
                        //filePath=selectedFilePath;
                        String[] parts = selectedFilePath.split("/");
                        fileName = parts[parts.length - 1];

                        AssignmentModel.StudAssignDtlsBean student = new AssignmentModel.StudAssignDtlsBean();

                        student.setASSIGN_ID(studAssignDtlsArrayList.get(assignmentPos).getASSIGN_ID());
                        student.setASSIGN_TYPE(studAssignDtlsArrayList.get(assignmentPos).getASSIGN_TYPE());
                        student.setASSIGN_DESCRIPTION(studAssignDtlsArrayList.get(assignmentPos).getASSIGN_DESCRIPTION());
                        student.setASSIGN_SUBMISSION_TYPE(studAssignDtlsArrayList.get(assignmentPos).getASSIGN_SUBMISSION_TYPE());
                        student.setASSIGN_SUBMI_FROM_DATE(studAssignDtlsArrayList.get(assignmentPos).getASSIGN_SUBMI_FROM_DATE());
                        student.setASSIGN_SUBMI_UPTO_DATE(studAssignDtlsArrayList.get(assignmentPos).getASSIGN_SUBMI_UPTO_DATE());
                        student.setSUBJECT_DESCRIPTION(studAssignDtlsArrayList.get(assignmentPos).getSUBJECT_DESCRIPTION());
                        student.setASSIGN_FILE_DETAILS(studAssignDtlsArrayList.get(assignmentPos).getASSIGN_FILE_DETAILS());
                        student.setEMP_REMARK(studAssignDtlsArrayList.get(assignmentPos).getEMP_REMARK());
                        student.setFACULTY_NAME(studAssignDtlsArrayList.get(assignmentPos).getFACULTY_NAME());
                        student.setDOWNLOAD_PATH(studAssignDtlsArrayList.get(assignmentPos).getDOWNLOAD_PATH());
                        student.setBRANCH_STANDARD_ID(studAssignDtlsArrayList.get(assignmentPos).getBRANCH_STANDARD_ID());
                        student.setSUBJECT_DETAIL_ID(studAssignDtlsArrayList.get(assignmentPos).getSUBJECT_DETAIL_ID());
                        student.setAPPLICABLE_NUMBER(studAssignDtlsArrayList.get(assignmentPos).getAPPLICABLE_NUMBER());
                        student.setSEMESTER_TYPE(studAssignDtlsArrayList.get(assignmentPos).getSEMESTER_TYPE());
                        student.setASSIGN_SUBMISSION_ID(studAssignDtlsArrayList.get(assignmentPos).getASSIGN_SUBMISSION_ID());
                        student.setSUBMISSION_DATE(studAssignDtlsArrayList.get(assignmentPos).getSUBMISSION_DATE());
                        student.setACAD_ASG_FACUL_REMARK_ID(studAssignDtlsArrayList.get(assignmentPos).getACAD_ASG_FACUL_REMARK_ID());
                        student.setFACULTY_REMARK(studAssignDtlsArrayList.get(assignmentPos).getFACULTY_REMARK());
                        student.setCORRECTION_REMARK(studAssignDtlsArrayList.get(assignmentPos).getCORRECTION_REMARK());
                        student.setASG_SUBMISSION_PATH(studAssignDtlsArrayList.get(assignmentPos).getASG_SUBMISSION_PATH());
                        student.setTASK_FILE_NAME(studAssignDtlsArrayList.get(assignmentPos).getTASK_FILE_NAME());
                        student.setSTU_SUBMISSION_STATUS(studAssignDtlsArrayList.get(assignmentPos).getSTU_SUBMISSION_STATUS());
                        student.setGRADE_DETAIL_ID(studAssignDtlsArrayList.get(assignmentPos).getGRADE_DETAIL_ID());
                        student.setGRADE_CODE(studAssignDtlsArrayList.get(assignmentPos).getGRADE_CODE());
                        student.setOBTAINED_MARKS(studAssignDtlsArrayList.get(assignmentPos).getOBTAINED_MARKS());
                        student.setEVALUATION_TYPE(studAssignDtlsArrayList.get(assignmentPos).getEVALUATION_TYPE());
                        student.setMARKS_OUT_OF(studAssignDtlsArrayList.get(assignmentPos).getMARKS_OUT_OF());
                        student.setSTUD_UPLOAD_FILE(fileName);

                       // publishedAssgnList.set(assignmentPos, student);
                        assignment_list_adapter.notifyDataSetChanged();

                        if (fileName.equalsIgnoreCase("-") || fileName.equalsIgnoreCase("") || fileName.equalsIgnoreCase(null) || fileName.isEmpty()) {
                            Snackbar.make(lay_view, "Select file to upload file!", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                            //txtFileName.setError("Select file to upload file!");
                        } else if (fileSizeInKB > 300) {
                            Snackbar.make(lay_view, "Select file to upload file have size upto 300 KB !", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                            //txtFileName.setError("Select file to upload file have size upto 300 KB !");
                        } else if (selectedFilePath != null) {
                            dialog = ProgressDialog.show(getActivity(), "", "Uploading File...", true);

                            new Thread(new Runnable() {
                                @Override
                                public void run() {

                                    try {
                                        //creating new thread to handle Http Operations
                                        uploadFile(selectedFilePath);

                                    } catch (OutOfMemoryError e) {

                                        getActivity().runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                Toast.makeText(getActivity(), "Insufficient Memory!", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                        dialog.dismiss();
                                    }

                                }
                            }).start();
                        } else {
                            Toast.makeText(getActivity(), "Please choose a File First", Toast.LENGTH_SHORT).show();
                        }

                        // tvFileName.setText(selectedFilePath);
                    } else {
                        Toast.makeText(getActivity(), "Cannot upload file to server", Toast.LENGTH_SHORT).show();
                    }
                }


            }
        }
    }
    public boolean checkPermissionREAD_EXTERNAL_STORAGE(
            final Context context) {
        int currentAPIVersion = Build.VERSION.SDK_INT;
        if (currentAPIVersion >= android.os.Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(context,
                    Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(
                        (Activity) context,
                        Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    showDialog("External storage", context,
                            Manifest.permission.READ_EXTERNAL_STORAGE);

                } else {
                    ActivityCompat
                            .requestPermissions(
                                    (Activity) context,
                                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                                    MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                }
                return false;
            } else {
                return true;
            }

        } else {
            return true;
        }
    }

    public void showDialog(final String msg, final Context context,
                           final String permission) {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
        alertBuilder.setCancelable(true);
        alertBuilder.setTitle("Permission necessary");
        alertBuilder.setMessage(msg + " permission is necessary");
        alertBuilder.setPositiveButton(android.R.string.yes,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        ActivityCompat.requestPermissions((Activity) context,
                                new String[]{permission},
                                MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                    }
                });
        AlertDialog alert = alertBuilder.create();
        alert.show();
    }


    public int uploadFile(final String selectedFilePath) {

        int serverResponseCode = 0;

        final HttpURLConnection connection;
        DataOutputStream dataOutputStream;
        String lineEnd = "\r\n";
        String twoHyphens = "--";
        String boundary = "*****";


        int bytesRead, bytesAvailable, bufferSize;
        byte[] buffer;
        int maxBufferSize = 1 * 1024 * 1024;
        File selectedFile = new File(selectedFilePath);
        String filename = selectedFilePath.substring(selectedFilePath.lastIndexOf("/") + 1);


        String[] parts = selectedFilePath.split("/");
        final String fileName = parts[parts.length - 1];
        /*String[] exten = fileName.split(Pattern.quote("."));
        String extension = exten[1];*/

        if (!selectedFile.isFile()) {
            // dialog.dismiss();

            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    dialog.dismiss();

                    Snackbar.make(lay_view, "Source File Doesn't Exist: " + selectedFilePath, Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            });
            return 0;
        } else {
            try {
                String SERVER_URL = BASE_URL +"PostUpload";
                FileInputStream fileInputStream = new FileInputStream(selectedFile);
                String filePath = "";//String.valueOf(selectedFile);
                URL url = new URL(SERVER_URL + "?P_fileName=" + filename + "&P_DOCUMENT_SHORT_CODE=ASG" + "&P_PERSON_TYPE=STUDENT&P_CENTRE_CODE=" + studCenterCode + "&P_EMPLOYEE_ID=" + student_id  + "&P_DOCPATH=" +filePath);
                connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);//Allow Inputs
                connection.setDoOutput(true);//Allow Outputs
                connection.setUseCaches(false);//Don't use a cached Copy
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Connection", "Keep-Alive");
                connection.setRequestProperty("ENCTYPE", "multipart/form-data");
                connection.setRequestProperty(
                        "Content-Type", "multipart/form-data;boundary=" + boundary);
                connection.setRequestProperty("uploaded_file", selectedFilePath);
                Log.d("Parameters : ", "selectedFilePath : " + selectedFilePath + "\n P_fileName : " + filename + "\n\n Upload URL : " + SERVER_URL + "?P_fileName=" + filename + "&P_DOCUMENT_SHORT_CODE=ASG" + "&P_PERSON_TYPE=STUDENT&P_CENTRE_CODE=" + studCenterCode + "&P_EMPLOYEE_ID=" + student_id +"&P_DOCPATH=" +filePath);

                dataOutputStream = new DataOutputStream(connection.getOutputStream());
                //writing bytes to data outputstream
                dataOutputStream.writeBytes(twoHyphens + boundary + lineEnd);
                dataOutputStream.writeBytes("Content-Disposition: form-data; name=\"uploaded_file\";filename=\""
                        + selectedFilePath + "\"" + lineEnd);

                dataOutputStream.writeBytes(lineEnd);
                //returns no. of bytes present in fileInputStream
                bytesAvailable = fileInputStream.available();
                //selecting the buffer size as minimum of available bytes or 1 MB
                bufferSize = Math.min(bytesAvailable, maxBufferSize);
                //setting the buffer as byte array of size of bufferSize
                buffer = new byte[bufferSize];
                //reads bytes from FileInputStream(from 0th index of buffer to buffersize)
                bytesRead = fileInputStream.read(buffer, 0, bufferSize);
                //loop repeats till bytesRead = -1, i.e., no bytes are left to read
                while (bytesRead > 0) {

                    try {

                        //write the bytes read from inputstream
                        dataOutputStream.write(buffer, 0, bufferSize);
                    } catch (OutOfMemoryError e) {
                        Toast.makeText(getActivity(), "Insufficient Memory!", Toast.LENGTH_SHORT).show();
                    }
                    bytesAvailable = fileInputStream.available();
                    bufferSize = Math.min(bytesAvailable, maxBufferSize);
                    bytesRead = fileInputStream.read(buffer, 0, bufferSize);
                }

                dataOutputStream.writeBytes(lineEnd);
                dataOutputStream.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

                try {
                    serverResponseCode = connection.getResponseCode();
                } catch (OutOfMemoryError e) {
                    Toast.makeText(getActivity(), "Memory Insufficient!", Toast.LENGTH_SHORT).show();
                }
                String serverResponseMessage = connection.getResponseMessage();

                Log.i(TAG, "Server Response is: " + serverResponseMessage + ": " + serverResponseCode);

                //response code of 200 indicates the server status OK
                if (serverResponseCode == 200 || serverResponseCode == 201) {

                    Snackbar.make(lay_view, "File uploded successfully!", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                    BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    filePath = br.readLine();
                    //\\192.168.1.151\d\EmployeeDocuments\LeaveDoc\ENGG_SC\1039140001/demo.docx
                    Log.d(TAG, "BufferedReader Created : " + filePath);
                    String s,v;
                    String sourcePath =filePath;
                    String str = sourcePath.substring(sourcePath.lastIndexOf("\\") + 1);
                    String[] value = str.split(Pattern.quote("\\"));
                    s = value[0];
                    String path =s;
                   // String path ="http://103.96.41.134/SASHA.pdf";

                    funDocumPath(path);


                } else {

                    dialog.dismiss();

                    //  tvFileName.setText("Server response : "+serverResponseCode +" : "+ serverResponseMessage);
                    BufferedReader br = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                    String c = br.readLine();
                    Log.d(TAG, "BufferedReader Error : " + c);

                    Log.d("Response ", "Server response : " + serverResponseCode + " : " + serverResponseMessage);
                }

                //closing the input and output streams
                fileInputStream.close();
                dataOutputStream.flush();
                dataOutputStream.close();

               /* if (wakeLock.isHeld()) {

                    wakeLock.release();
                }
*/

            } catch (FileNotFoundException e) {
                e.printStackTrace();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(), "File Not Found", Toast.LENGTH_SHORT).show();
                    }
                });
            } catch (MalformedURLException e) {
                e.printStackTrace();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(), "URL Error!", Toast.LENGTH_SHORT).show();
                    }
                });

            } catch (IOException e) {
                e.printStackTrace();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(), "Cannot Read/Write File", Toast.LENGTH_SHORT).show();
                    }
                });
            }
            dialog.dismiss();
            return serverResponseCode;
        }
    }
    private void funDocumPath(String Path) {

       /* mProgressDialog.setMessage("Please wait loading uploaded assignments!");
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.show();*/

       // String url = BASE_URL + URLEndPoints.GetUploadFileP_URL + studCenterCode;
        String url =BASE_URL +URLEndPoints.getUpload_Doc_URL(URLEndPoints.Constance_StudentCenterCode);
        Log.d(TAG, "URL : " + url);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, response.toString());
//{"status":1,"dtEmpsalAnnual":[{"UPLOAD_DOCU_PATH_DTLS_ID":"308","DOCUMENT_SHORT_CODE":"ASG","DOCUMENT_DESCRIPTION":"ASSIGNMENT","IS_PERSON_TYPE_APPLI":"Y","DOCU_UPLOAD_PATH":"EmployeeDocuments\\Assignment\\Employee\\","DOCU_UPLOAD_BACKUP_PATH":"","PERSON_TYPE":"EMPLOYEE","STANDARD_PATH":"D:"}]}
                try {

                    Gson gson = new Gson();
                    dialog.dismiss();

                    documPathModel = gson.fromJson(response, DocumPathModel.class);

                    if (documPathModel.getStatus() == 1) {

                        docuPathArrayList = (ArrayList<DocumPathModel.DtEmpsalAnnualBean>) documPathModel.getDtEmpsalAnnual();
                        if (docuPathArrayList.size() != 0 && docuPathArrayList != null) {

                            DOCU_UPLOAD_PATH = docuPathArrayList.get(0).getDOCU_UPLOAD_PATH();
                            UPLOAD_DOCU_PATH_DTLS_ID = docuPathArrayList.get(0).getUPLOAD_DOCU_PATH_DTLS_ID();
                            Log.d(TAG, DOCU_UPLOAD_PATH); //EmployeeDocuments\Assignment\Student\



                            String staticIp = docuPathArrayList.get(0).getSTATIC_IP();
                            String staticDOCPATH = docuPathArrayList.get(0).getDOCU_UPLOAD_STATIC_PATH();

                            COMPLETPATH =staticIp +staticDOCPATH +"/"+Path;

                            submitUploadDoc(assignmentPos);

                        } else {


                            Snackbar.make(lay_view, "Record not available", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        }
                    } else {
                      //  mProgressDialog.dismiss();

                        Snackbar.make(lay_view, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }


                } catch (Exception e) {
                    dialog.hide();
                    Snackbar.make(lay_view, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    Log.d(TAG, "Error : " + e.getMessage());


                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                dialog.hide();
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


   public class DownloadFileFromURL extends AsyncTask<String, String, String> {
        ProgressDialog pd;
        String pathFolder = "";
        String pathFile = "";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(getActivity());
            pd.setTitle("Processing...");
            pd.setMessage("Please wait.");
            pd.setMax(100);
            pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            pd.setCancelable(true);
            pd.show();
        }

        @Override
        protected String doInBackground(String... f_url) {
            int count;

            try {
                pathFolder = Environment.getDownloadCacheDirectory() + "/YourAppDataFolder";
                pathFile = pathFolder + "/E1032170368_21020_155496836262.pdf";
                File futureStudioIconFile = new File(pathFolder);
                if(!futureStudioIconFile.exists()){
                    futureStudioIconFile.mkdirs();
                }

                URL url = new URL(f_url[0]);
                URLConnection connection = url.openConnection();
                connection.connect();

                // this will be useful so that you can show a tipical 0-100%
                // progress bar
                int lengthOfFile = connection.getContentLength();

                // download the file
                InputStream input = new BufferedInputStream(url.openStream());
                FileOutputStream output = new FileOutputStream(pathFile);

                byte data[] = new byte[1024]; //anybody know what 1024 means ?
                long total = 0;
                while ((count = input.read(data)) != -1) {
                    total += count;
                    // publishing the progress....
                    // After this onProgressUpdate will be called
                    publishProgress("" + (int) ((total * 100) / lengthOfFile));

                    // writing data to file
                    output.write(data, 0, count);
                }

                // flushing output
                output.flush();

                // closing streams
                output.close();
                input.close();


            } catch (Exception e) {
                Log.e("Error: ", e.getMessage());
            }

            return pathFile;
        }

        protected void onProgressUpdate(String... progress) {
            // setting progress percentage
            pd.setProgress(Integer.parseInt(progress[0]));
        }

        @Override
        protected void onPostExecute(String file_url) {
            if (pd!=null) {
                pd.dismiss();
            }
            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(builder.build());
            /*Intent i = new Intent(Intent.ACTION_VIEW);

            i.setDataAndType(Uri.fromFile(new File(file_url)), "application/vnd.android.package-archive" );
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            getActivity().startActivity(i);*/
        }
    }



}
