package com.wordpro.studentproject.fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DownloadManager;
import android.app.NotificationManager;
import android.app.ProgressDialog;
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
import android.os.PowerManager;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.wordpro.studentproject.adapter.AssignmentAdapter;
import com.wordpro.studentproject.adapter.IndicatorAdapter;
import com.wordpro.studentproject.app.MyApplication;
import com.wordpro.studentproject.helper.FilePath;
import com.wordpro.studentproject.model.AssignmentModel;
import com.wordpro.studentproject.model.DocumPathModel;
import com.wordpro.studentproject.model.DownloadURLModel;
import com.wordpro.studentproject.utils.FileUtils;
import com.wordpro.studentproject.webConfig.URLEndPoints;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.content.Context.POWER_SERVICE;
import static com.android.volley.VolleyLog.TAG;
import static com.github.mikephil.charting.charts.Chart.LOG_TAG;
import static com.wordpro.studentproject.activities.NavigationActivity.BASE_URL;
import static com.wordpro.studentproject.activities.NavigationActivity.fragE_learning;
import static com.wordpro.studentproject.activities.NavigationActivity.studAcadSessName;
import static com.wordpro.studentproject.activities.NavigationActivity.studBranchStandDesc;
import static com.wordpro.studentproject.activities.NavigationActivity.studBranchStandardId;
import static com.wordpro.studentproject.activities.NavigationActivity.studCenterCode;
import static com.wordpro.studentproject.activities.NavigationActivity.studMainSemName;
import static com.wordpro.studentproject.activities.NavigationActivity.studSemesterType;
import static com.wordpro.studentproject.activities.NavigationActivity.studSessionId;
import static com.wordpro.studentproject.activities.NavigationActivity.studentName;
import static com.wordpro.studentproject.activities.NavigationActivity.student_id;
import static com.wordpro.studentproject.fragment.FragE_Learning.studAssignDtlsArrayList;
import static com.wordpro.studentproject.network.Network.isNetworkAvailable;

/**
 * Created by wccs1980 on 19-06-2018.
 */

public class PublishFragment extends Fragment {

    Context context;
    public static ProgressDialog mProgressDialog;
    private static final int PERMISSION_REQUEST_CODE = 200;
    private static final int PICK_FILE_REQUEST = 1;
    PowerManager.WakeLock wakeLock;
    private final int REQUEST_READ_PHONE_STATE = 1;
    private String selectedFilePath;
    long fileSizeInKB;
    private String SERVER_URL = "http://192.168.1.72:500/api/PostUpload"; // "http://103.208.73.138:82/api/PostUpload";

    public static final int DIALOG_DOWNLOAD_PROGRESS = 0;
    //initialize root directory
    File rootDir = Environment.getExternalStorageDirectory();
    //defining file name and url
    public String fileURL = "";
    ArrayList<AssignmentModel.StudAssignDtlsBean> publishedAssgnList;
    public static AssignmentAdapter assignmentAdapter;
    IndicatorAdapter indicatorAdapter;
    List<String> statusList;
    RecyclerView recyViewIndicators;
    RelativeLayout relPublishAssgn;
    String DOCU_UPLOAD_PATH = "", UPLOAD_DOCU_PATH_DTLS_ID = "";
    private DownloadManager downloadManager;
    private long refid;
    private Uri Download_Uri;
    ProgressDialog dialog;
    public static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 123;
    String filePath = "-", fileName = "-";
    int assignmentPos;
    AssignmentModel assignmentModel;
    ArrayList<AssignmentModel.StudAssignDtlsBean> studAssignDtlsArrayListNew;
    RecyclerView recyVwPublishAssgn;
    //DocumPathModel
    public static DocumPathModel documPathModel;
    public static ArrayList<DocumPathModel.DtEmpsalAnnualBean> docuPathArrayList;

    public PublishFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.list_subject_assign, container, false);
        if (checkPermission()) {
            requestPermissionAndContinue();
        }
        downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        context.registerReceiver(onComplete,
                new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));

        Typeface typefaceHeading = Typeface.createFromAsset(getActivity().getAssets(), "font/Poppins-BoldItalic.otf");
        Typeface typefaceContent = Typeface.createFromAsset(getActivity().getAssets(), "font/Poppins-Medium.otf");

        mProgressDialog = new ProgressDialog(getActivity());
        relPublishAssgn = (RelativeLayout) rootView.findViewById(R.id.relPublishAssgn);

        TextView txtStudName = (TextView) rootView.findViewById(R.id.txtStudName);
        txtStudName.setTypeface(typefaceContent);
        txtStudName.setText(studentName.toUpperCase());

        TextView txtYear = (TextView) rootView.findViewById(R.id.txtYear);
        txtYear.setTypeface(typefaceContent);
        txtYear.setText(studAcadSessName.toUpperCase());

        TextView txtSem = (TextView) rootView.findViewById(R.id.txtSem);
        txtSem.setTypeface(typefaceContent);
        txtSem.setText(studMainSemName.toUpperCase());

        TextView txtSection = (TextView) rootView.findViewById(R.id.txtSection);
        txtSection.setTypeface(typefaceContent);
        txtSection.setText(studBranchStandDesc.toUpperCase());

        recyVwPublishAssgn = (RecyclerView) rootView.findViewById(R.id.recyVwPublishAssgn);
        recyVwPublishAssgn.setLayoutManager(new LinearLayoutManager(getActivity()));

        statusList = new ArrayList<>();
        statusList.add("PUBLISHED");
        statusList.add("SENT_FOR_CORRECTION");

        recyViewIndicators = (RecyclerView) rootView.findViewById(R.id.recyViewIndicators);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyViewIndicators.setLayoutManager(layoutManager);
        recyViewIndicators.setItemAnimator(new DefaultItemAnimator());
        recyViewIndicators.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        indicatorAdapter = new IndicatorAdapter(getActivity(), statusList);
        recyViewIndicators.setAdapter(indicatorAdapter);

        if (studAssignDtlsArrayList != null) {

            publishedAssgnList = new ArrayList<>();
            for (int i = 0; i < studAssignDtlsArrayList.size(); i++) {

                String STU_SUBMISSION_STATUS = studAssignDtlsArrayList.get(i).getSTU_SUBMISSION_STATUS();
                if (STU_SUBMISSION_STATUS.equalsIgnoreCase("") || STU_SUBMISSION_STATUS.equalsIgnoreCase(null) || STU_SUBMISSION_STATUS.isEmpty() || STU_SUBMISSION_STATUS.equalsIgnoreCase("SENT_FOR_CORRECTION")) {
                    publishedAssgnList.add(studAssignDtlsArrayList.get(i));
                }
            }

            Log.d("PublishedFragment : ", String.valueOf(publishedAssgnList.size()));

            assignmentAdapter = new AssignmentAdapter(getActivity(), publishedAssgnList, new AssignmentAdapter.AssignDownUpListner() {
                @Override
                public void onDownloadClick(int position) {

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

                        String duplicateName;
                        String extention;
                        String pathh;
                        String employeeId;
                        String originalfileName;

                        //192.168.1.151\d\EmployeeDocuments\Assignment\Employee\19_20_E_15178_1\E1039140032_09052019_105217000017.doc"
                        //"\\\\"+
                        pathh = publishedAssgnList.get(position).getDOWNLOAD_PATH();
                        String ASSIGN_FILE_DETAILS=publishedAssgnList.get(position).getASSIGN_FILE_DETAILS();
                        String[] createdFileName = ASSIGN_FILE_DETAILS.split(Pattern.quote("\\"));
                        String filewithExtnsn=createdFileName[createdFileName.length-1];
                        String[] value = filewithExtnsn.split(Pattern.quote("."));
                        duplicateName = value[0];
                        extention = value[1];

                        String[] data = duplicateName.split(Pattern.quote("_"));
                        employeeId = data[0];
                        employeeId = employeeId.substring(1);

                        originalfileName=publishedAssgnList.get(position).getTASK_FILE_NAME();
                        if(originalfileName.isEmpty()){
                            originalfileName=duplicateName;
                        }

                        funDownloadURL(duplicateName, extention, pathh, "file", employeeId, originalfileName);

                    }

                }

                @Override
                public void onUploadClick(int position) {

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
                    } else {
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


                    }

                }

                @Override
                public void onSubmitAssignClick(int position) {
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
                        String STUDENT_FILE_NAME = publishedAssgnList.get(position).getSTUD_UPLOAD_FILE();

                        if (STUDENT_FILE_NAME.equalsIgnoreCase("") || STUDENT_FILE_NAME.isEmpty()) {

                            Snackbar.make(relPublishAssgn, "Upload the Assignment file before submission", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();

                        } else {

                            String STU_SUBMISSION_STATUS = publishedAssgnList.get(position).getSTU_SUBMISSION_STATUS();
                            String ASSIGN_SUBMI_UPTO_DATE = publishedAssgnList.get(position).getASSIGN_SUBMI_UPTO_DATE();
                            String ASSIGN_DESCRIPTION = publishedAssgnList.get(position).getASSIGN_DESCRIPTION();
                            String BRANCH_STANDARD_ID = publishedAssgnList.get(position).getBRANCH_STANDARD_ID();
                            String SUBJECT_DETAIL_ID = publishedAssgnList.get(position).getSUBJECT_DETAIL_ID();
                            String APPLICABLE_NUMBER = publishedAssgnList.get(position).getAPPLICABLE_NUMBER();
                            String ASSIGN_SUBMISSION_ID = publishedAssgnList.get(position).getASSIGN_SUBMISSION_ID();
                            String ASSIGN_ID = publishedAssgnList.get(position).getASSIGN_ID();

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


                       /* String[] value = STUDENT_FILE_NAME.split(Pattern.quote("."));
                        String file = value[0];
                        String extension = value[1];
*/

                            String extension = STUDENT_FILE_NAME.substring(STUDENT_FILE_NAME.lastIndexOf("."));


                            String fold;
                            String filename = "S" + student_id + "_" + Dt + "_" + Time;
                            String datafold = studSessionId + "_" + BRANCH_STANDARD_ID + "_" + studSemesterType + "_" + SUBJECT_DETAIL_ID + "_" + APPLICABLE_NUMBER;
                            String ActualPath = datafold + "\\" + filename + "" + extension;
                            if (DOCU_UPLOAD_PATH.equalsIgnoreCase("") || DOCU_UPLOAD_PATH.isEmpty()) {
                                Snackbar.make(relPublishAssgn, "Upload the Assignment file" + ".", Snackbar.LENGTH_LONG)
                                        .setAction("Action", null).show();
                            } else {
                                fold = DOCU_UPLOAD_PATH + datafold;
                            }

                            String submitString = ASSIGN_SUBMISSION_ID + "µ" + ASSIGN_ID + "µ" + student_id + "µ" + UPLOAD_DOCU_PATH_DTLS_ID + "µ" + ActualPath + "µ" + "SUBMITTED" + "µ" + studCenterCode + "µ" + STUDENT_FILE_NAME;
                            Log.d(TAG, "String : " + submitString);
//3471 µ 1032160253 µ      µ 19_26_O_8953_1\S1032160253_11218_155950976341.docx µ SUBMITTED µ ENGG_SC µ ExtraTimeTable.docx
//3474 µ 1032160253 µ 901  µ 19_26_O_8916_1\S1032160253_011218_1619150720.doc   µ SUBMITTED µ ENGG_SC µ filename.doc
//0    µ 1032160253 µ 1004 µ 19_26_O_8944_2\S1032160253_011218_1620429750.doc   µ SUBMITTED µ ENGG_SC µ filename.doc
//0    µ 1032160253 µ 1004 µ 19_26_O_8944_2\S1032160253_011218_162340940000.doc µ SUBMITTED µ ENGG_SC µ filename.doc

                            if (STU_SUBMISSION_STATUS.equalsIgnoreCase("") || STU_SUBMISSION_STATUS.isEmpty() && currntDt.before(lastDt) || currntDt.equals(lastDt)) {

                                //submit
                                funSubmitAssignment(submitString);

                            } else if (STU_SUBMISSION_STATUS.equalsIgnoreCase("SENT_FOR_CORRECTION")) {

                                //submit
                                funSubmitAssignment(submitString);


                            } else {
                                Snackbar.make(relPublishAssgn, "Last Date for " + ASSIGN_DESCRIPTION + " submission was " + ASSIGN_SUBMI_UPTO_DATE + ".", Snackbar.LENGTH_LONG)
                                        .setAction("Action", null).show();
                            }
                        }
                    }

                }
            });

            recyVwPublishAssgn.setAdapter(assignmentAdapter);

        }

        return rootView;
    }

    private void funSubmitAssignment(final String submitString) {

        mProgressDialog.setMessage("Please wait loading uploaded assignments!");
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.show();
        String url = BASE_URL + URLEndPoints.AssStudSubmit_URL;
        Log.d(TAG, "URL : " + url);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d(TAG, response.toString());

                try {
                    mProgressDialog.dismiss();
                    //{"status":1,"STATUS":"TRUE","REASON":"RECORD SAVED SUCCESSFULLY"}
                    JSONObject responseObj = new JSONObject(response);

                    // Parsing json object response
                    // response will be a json object
                    int status = responseObj.getInt("status");


                    if (status == 1) {
                        String STATUS = responseObj.getString("STATUS");
                        String REASON = responseObj.getString("REASON");

                        if (STATUS.equalsIgnoreCase("TRUE")) {

                            Snackbar.make(relPublishAssgn, REASON, Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();


                            new Handler().postDelayed(new Runnable() {

                                // Using handler with postDelayed called runnable run method

                                @Override
                                public void run() {

                                    funGetAssgnDtl();

                                }
                            }, 5 * 1000); // wait for 5 seconds


                        } else if (STATUS.equalsIgnoreCase("FALSE")) {
                            Snackbar.make(relPublishAssgn, REASON, Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();

                            /*Intent intent = new Intent(LeaveApprovalActivity.this, MainHomeActivity.class);
                            intent.putExtra("activity", "LeaveApprovalActivity");
                            startActivity(intent);
                            finish();*/
                        }

                    }
                } catch (Exception e) {
                    mProgressDialog.hide();
                    Snackbar.make(relPublishAssgn, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    Log.d(TAG, "Error : " + e.getMessage());

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Error: " + error.getMessage());
                handleVolleyError(error);
                mProgressDialog.hide();

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

    private void funGetAssgnDtl() {
        mProgressDialog.setMessage("Please wait loading uploaded assignments!");
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.show();

        String url = BASE_URL + URLEndPoints.GetStudAssignment_URL(student_id,studSessionId,studBranchStandardId,studSemesterType,studCenterCode);
        Log.d(TAG, "Assignment URL  :  " + url);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d(TAG, response.toString());

                try {

                    Gson gson = new Gson();
                    assignmentModel = gson.fromJson(response, AssignmentModel.class);

                    if (assignmentModel.getStatus() == 1) {

                        studAssignDtlsArrayListNew = (ArrayList<AssignmentModel.StudAssignDtlsBean>) assignmentModel.getStudAssign_Dtls();

                        if (studAssignDtlsArrayListNew != null) {

                            publishedAssgnList = new ArrayList<>();
                            for (int i = 0; i < studAssignDtlsArrayListNew.size(); i++) {

                                String STU_SUBMISSION_STATUS = studAssignDtlsArrayListNew.get(i).getSTU_SUBMISSION_STATUS();
                                if (STU_SUBMISSION_STATUS.equalsIgnoreCase("") || STU_SUBMISSION_STATUS.equalsIgnoreCase(null) || STU_SUBMISSION_STATUS.isEmpty() || STU_SUBMISSION_STATUS.equalsIgnoreCase("SENT_FOR_CORRECTION")) {
                                    publishedAssgnList.add(studAssignDtlsArrayListNew.get(i));
                                }
                            }

                            Log.d("PublishedFragment : ", String.valueOf(publishedAssgnList.size()));

                            assignmentAdapter = new AssignmentAdapter(getActivity(), publishedAssgnList, new AssignmentAdapter.AssignDownUpListner() {
                                @Override
                                public void onDownloadClick(int position) {

                                    String strfileName = publishedAssgnList.get(position).getTASK_FILE_NAME();
                                    String fileName, Extent;
                                    String[] filenameNextension = strfileName.split(Pattern.quote("."));
                                    String originalfileName = filenameNextension[0];

                                    //"\\\\" +
                                    String sourcePath = publishedAssgnList.get(position).getDOWNLOAD_PATH();
                                    String str = sourcePath.substring(sourcePath.lastIndexOf("\\") + 1);
                                    String[] value = str.split(Pattern.quote("."));
                                    fileName = value[0];
                                    Extent = value[1];

                                    String[] data = fileName.split(Pattern.quote("_"));
                                    String employeeId = data[0];
                                    employeeId = employeeId.substring(1);

                                    String sourcePathsplit = "\\\\" + publishedAssgnList.get(position).getDOWNLOAD_PATH();
                                    String[] source = sourcePathsplit.split(Pattern.quote("Employee\\"));
                                    String originalSource = source[0] + "Employee\\" + employeeId;


                                    funDownloadURL(fileName, Extent, originalSource, "file", employeeId, originalfileName);

                                }

                                @Override
                                public void onUploadClick(int position) {

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


                                }

                                @Override
                                public void onSubmitAssignClick(int position) {

                                    String STUDENT_FILE_NAME = publishedAssgnList.get(position).getSTUD_UPLOAD_FILE();

                                    if (STUDENT_FILE_NAME.equalsIgnoreCase("") || STUDENT_FILE_NAME.isEmpty()) {

                                        Snackbar.make(relPublishAssgn, "Upload the Assignment file before submission", Snackbar.LENGTH_LONG)
                                                .setAction("Action", null).show();

                                    } else {

                                        String STU_SUBMISSION_STATUS = publishedAssgnList.get(position).getSTU_SUBMISSION_STATUS();
                                        String ASSIGN_SUBMI_UPTO_DATE = publishedAssgnList.get(position).getASSIGN_SUBMI_UPTO_DATE();
                                        String ASSIGN_DESCRIPTION = publishedAssgnList.get(position).getASSIGN_DESCRIPTION();
                                        String BRANCH_STANDARD_ID = publishedAssgnList.get(position).getBRANCH_STANDARD_ID();
                                        String SUBJECT_DETAIL_ID = publishedAssgnList.get(position).getSUBJECT_DETAIL_ID();
                                        String APPLICABLE_NUMBER = publishedAssgnList.get(position).getAPPLICABLE_NUMBER();
                                        String ASSIGN_SUBMISSION_ID = publishedAssgnList.get(position).getASSIGN_SUBMISSION_ID();
                                        String ASSIGN_ID = publishedAssgnList.get(position).getASSIGN_ID();

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


                       /* String[] value = STUDENT_FILE_NAME.split(Pattern.quote("."));
                        String file = value[0];
                        String extension = value[1];
*/

                                        String extension = STUDENT_FILE_NAME.substring(STUDENT_FILE_NAME.lastIndexOf("."));


                                        String fold;
                                        String filename = "S" + student_id + "_" + Dt + "_" + Time;
                                        String datafold = studSessionId + "_" + BRANCH_STANDARD_ID + "_" + studSemesterType + "_" + SUBJECT_DETAIL_ID + "_" + APPLICABLE_NUMBER;
                                        String ActualPath = datafold + "\\" + filename + "" + extension;
                                        if (DOCU_UPLOAD_PATH.equalsIgnoreCase("") || DOCU_UPLOAD_PATH.isEmpty()) {
                                            Snackbar.make(relPublishAssgn, "Upload the Assignment file" + ".", Snackbar.LENGTH_LONG)
                                                    .setAction("Action", null).show();
                                        } else {
                                            fold = DOCU_UPLOAD_PATH + datafold;
                                        }

                                        String submitString = ASSIGN_SUBMISSION_ID + "µ" + ASSIGN_ID + "µ" + student_id + "µ" + UPLOAD_DOCU_PATH_DTLS_ID + "µ" + ActualPath + "µ" + "SUBMITTED" + "µ" + studCenterCode + "µ" + STUDENT_FILE_NAME;
                                        Log.d(TAG, "String : " + submitString);
//3471 µ 1032160253 µ      µ 19_26_O_8953_1\S1032160253_11218_155950976341.docx µ SUBMITTED µ ENGG_SC µ ExtraTimeTable.docx
//3474 µ 1032160253 µ 901  µ 19_26_O_8916_1\S1032160253_011218_1619150720.doc   µ SUBMITTED µ ENGG_SC µ filename.doc
//0    µ 1032160253 µ 1004 µ 19_26_O_8944_2\S1032160253_011218_1620429750.doc   µ SUBMITTED µ ENGG_SC µ filename.doc
//0    µ 1032160253 µ 1004 µ 19_26_O_8944_2\S1032160253_011218_162340940000.doc µ SUBMITTED µ ENGG_SC µ filename.doc

                                        if (STU_SUBMISSION_STATUS.equalsIgnoreCase("") || STU_SUBMISSION_STATUS.isEmpty() && currntDt.before(lastDt) || currntDt.equals(lastDt)) {

                                            //submit
                                            funSubmitAssignment(submitString);

                                        } else if (STU_SUBMISSION_STATUS.equalsIgnoreCase("SENT_FOR_CORRECTION")) {

                                            //submit
                                            funSubmitAssignment(submitString);


                                        } else {
                                            Snackbar.make(relPublishAssgn, "Last Date for " + ASSIGN_DESCRIPTION + " submission was " + ASSIGN_SUBMI_UPTO_DATE + ".", Snackbar.LENGTH_LONG)
                                                    .setAction("Action", null).show();
                                        }
                                    }
                                }
                            });

                            recyVwPublishAssgn.setAdapter(assignmentAdapter);


                        } else {
                            Snackbar.make(relPublishAssgn, "Record not available.", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        }

                    } else if (assignmentModel.getStatus() == 0) {
                        Snackbar.make(relPublishAssgn, "Record not available.", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }

                    mProgressDialog.dismiss();
                } catch (Exception e) {

                    Snackbar.make(relPublishAssgn, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                    Log.d(TAG, "Error : " + e.getMessage());
                    mProgressDialog.hide();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Error: " + error.getMessage());
                handleVolleyError(error);
                mProgressDialog.dismiss();

            }
        });
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                60000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        // Adding request to request queue
        MyApplication.getInstance().addToRequestQueue(stringRequest);
    }


    //function to verify if directory exists
    public void checkAndCreateDirectory(String dirName) {
        File new_dir = new File(rootDir + dirName);
        if (!new_dir.exists()) {
            new_dir.mkdirs();
        }
    }


    private void funDocumPath() {

       /* mProgressDialog.setMessage("Please wait loading uploaded assignments!");
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.show();*/

        String url = BASE_URL + URLEndPoints.GetUploadFileP_URL + studCenterCode;
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
                        } else {
                            mProgressDialog.dismiss();

                            Snackbar.make(relPublishAssgn, "Record not available", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        }
                    } else {
                        mProgressDialog.dismiss();

                        Snackbar.make(relPublishAssgn, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }


                } catch (Exception e) {
                    dialog.hide();
                    Snackbar.make(relPublishAssgn, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
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

    private void funDownloadURL(final String fileName, final String extent, final String sourcePath, final String file, final String employee, final String originalfileName) {
        mProgressDialog.setMessage("Please wait downloading!");
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.show();

        String url = BASE_URL + URLEndPoints.PostDownloadCopyTo_URL;
        Log.d(TAG, url);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Response : " + BASE_URL + "" + response.toString());
                try {

                    //{"status":1,"data":[{"FilePath":"APP_DOWNLOAD\\25012019024218714.docx","FileName":"documentfile"}]}
                    Gson gson = new Gson();
                    DownloadURLModel downloadURLModel = gson.fromJson(response, DownloadURLModel.class);
                    if (downloadURLModel.getStatus() == 1) {
                        mProgressDialog.dismiss();

                        ArrayList<DownloadURLModel.DataBean> downloadURLArrayList = (ArrayList<DownloadURLModel.DataBean>) downloadURLModel.getData();
                        if (downloadURLArrayList.size() != 0 && downloadURLArrayList != null) {

                            String str = BASE_URL;          //http://117.247.82.252:500/api/
                            String[] s = str.split(Pattern.quote("api"));
                            String downloadURL = s[0] + downloadURLArrayList.get(0).getFilePath();

                            Download_Uri = Uri.parse(downloadURL);

                            funDownloadFile(fileName + "." + extent);

                            Log.d(TAG, "File download complete. : " + downloadURL);

                            Snackbar.make(relPublishAssgn, "File downloaded in the Download folder.", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        }

                    } else {
                        mProgressDialog.hide();
                        Snackbar.make(relPublishAssgn, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }


                } catch (Exception e) {
                    mProgressDialog.hide();
                    Snackbar.make(relPublishAssgn, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    Log.d(TAG, "Error : " + e.getMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mProgressDialog.hide();
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
                params.put("Persone_Type", "EMPLOYEE");
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
                    new NotificationCompat.Builder(context)
                            .setSmallIcon(R.drawable.threeechoo)
                            .setContentTitle("ECHOAPPDOWNLOAD")
                            .setContentText("All Download completed");


            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(455, mBuilder.build());


            //  }

        }
    };

    private void funDownloadFile(String originalfilename) {

        DownloadManager.Request request = new DownloadManager.Request(Download_Uri);
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
        request.setAllowedOverRoaming(false);
        request.setTitle("Downloading " + originalfilename);
        request.setDescription("Downloading... " + originalfilename);
        request.setVisibleInDownloadsUi(true);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, originalfilename);

        refid = downloadManager.enqueue(request);

        Log.e("OUT", "" + refid);

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
        //sets the select file to all types of files
        //intent.setType("application/*");
        //allows to select data and return it
        intent.setAction(Intent.ACTION_GET_CONTENT);
        //intent = new Intent(Intent.ACTION_GET_CONTENT);
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

                    // long fileSizeInBytes =selectedFilePath.length();
                    // Convert the bytes to Kilobytes (1 KB = 1024 Bytes)
                    // fileSizeInKB = fileSizeInBytes / 1024;
                /*// Convert the KB to MegaBytes (1 MB = 1024 KBytes)
                long fileSizeInMB = fileSizeInKB / 1024;*/
                    Log.d(TAG, "File size  " + fileSizeInKB);

                    // Log.d(TAG, "File size  " + fileSizeInBytes + " " + fileSizeInKB + " " + fileSizeInMB);

                    if (selectedFilePath != null && !selectedFilePath.equals("")) {
                        //filePath=selectedFilePath;
                        String[] parts = selectedFilePath.split("/");
                        fileName = parts[parts.length - 1];

                        AssignmentModel.StudAssignDtlsBean student = new AssignmentModel.StudAssignDtlsBean();

                        student.setASSIGN_ID(publishedAssgnList.get(assignmentPos).getASSIGN_ID());
                        student.setASSIGN_TYPE(publishedAssgnList.get(assignmentPos).getASSIGN_TYPE());
                        student.setASSIGN_DESCRIPTION(publishedAssgnList.get(assignmentPos).getASSIGN_DESCRIPTION());
                        student.setASSIGN_SUBMISSION_TYPE(publishedAssgnList.get(assignmentPos).getASSIGN_SUBMISSION_TYPE());
                        student.setASSIGN_SUBMI_FROM_DATE(publishedAssgnList.get(assignmentPos).getASSIGN_SUBMI_FROM_DATE());
                        student.setASSIGN_SUBMI_UPTO_DATE(publishedAssgnList.get(assignmentPos).getASSIGN_SUBMI_UPTO_DATE());
                        student.setSUBJECT_DESCRIPTION(publishedAssgnList.get(assignmentPos).getSUBJECT_DESCRIPTION());
                        student.setASSIGN_FILE_DETAILS(publishedAssgnList.get(assignmentPos).getASSIGN_FILE_DETAILS());
                        student.setEMP_REMARK(publishedAssgnList.get(assignmentPos).getEMP_REMARK());
                        student.setFACULTY_NAME(publishedAssgnList.get(assignmentPos).getFACULTY_NAME());
                        student.setDOWNLOAD_PATH(publishedAssgnList.get(assignmentPos).getDOWNLOAD_PATH());
                        student.setBRANCH_STANDARD_ID(publishedAssgnList.get(assignmentPos).getBRANCH_STANDARD_ID());
                        student.setSUBJECT_DETAIL_ID(publishedAssgnList.get(assignmentPos).getSUBJECT_DETAIL_ID());
                        student.setAPPLICABLE_NUMBER(publishedAssgnList.get(assignmentPos).getAPPLICABLE_NUMBER());
                        student.setSEMESTER_TYPE(publishedAssgnList.get(assignmentPos).getSEMESTER_TYPE());
                        student.setASSIGN_SUBMISSION_ID(publishedAssgnList.get(assignmentPos).getASSIGN_SUBMISSION_ID());
                        student.setSUBMISSION_DATE(publishedAssgnList.get(assignmentPos).getSUBMISSION_DATE());
                        student.setACAD_ASG_FACUL_REMARK_ID(publishedAssgnList.get(assignmentPos).getACAD_ASG_FACUL_REMARK_ID());
                        student.setFACULTY_REMARK(publishedAssgnList.get(assignmentPos).getFACULTY_REMARK());
                        student.setCORRECTION_REMARK(publishedAssgnList.get(assignmentPos).getCORRECTION_REMARK());
                        student.setASG_SUBMISSION_PATH(publishedAssgnList.get(assignmentPos).getASG_SUBMISSION_PATH());
                        student.setTASK_FILE_NAME(publishedAssgnList.get(assignmentPos).getTASK_FILE_NAME());
                        student.setSTU_SUBMISSION_STATUS(publishedAssgnList.get(assignmentPos).getSTU_SUBMISSION_STATUS());
                        student.setGRADE_DETAIL_ID(publishedAssgnList.get(assignmentPos).getGRADE_DETAIL_ID());
                        student.setGRADE_CODE(publishedAssgnList.get(assignmentPos).getGRADE_CODE());
                        student.setOBTAINED_MARKS(publishedAssgnList.get(assignmentPos).getOBTAINED_MARKS());
                        student.setEVALUATION_TYPE(publishedAssgnList.get(assignmentPos).getEVALUATION_TYPE());
                        student.setMARKS_OUT_OF(publishedAssgnList.get(assignmentPos).getMARKS_OUT_OF());
                        student.setSTUD_UPLOAD_FILE(fileName);

                        publishedAssgnList.set(assignmentPos, student);
                        assignmentAdapter.notifyDataSetChanged();

                        if (fileName.equalsIgnoreCase("-") || fileName.equalsIgnoreCase("") || fileName.equalsIgnoreCase(null) || fileName.isEmpty()) {
                            Snackbar.make(relPublishAssgn, "Select file to upload file!", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                            //txtFileName.setError("Select file to upload file!");
                        } else if (fileSizeInKB > 300) {
                            Snackbar.make(relPublishAssgn, "Select file to upload file have size upto 300 KB !", Snackbar.LENGTH_LONG)
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

                    Snackbar.make(relPublishAssgn, "Source File Doesn't Exist: " + selectedFilePath, Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            });
            return 0;
        } else {
            try {
                String dd= "";
                FileInputStream fileInputStream = new FileInputStream(selectedFile);
                URL url = new URL(SERVER_URL + "?P_fileName=" + filename + "&P_DOCUMENT_SHORT_CODE=ASG" + "&P_PERSON_TYPE=STUDENT&P_CENTRE_CODE=" + studCenterCode + "&P_EMPLOYEE_ID=" + student_id + "&P_DOCPATH=" + dd);
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
                Log.d("Parameters : ", "selectedFilePath : " + selectedFilePath + "\n P_fileName : " + filename + "\n\n Upload URL : " + SERVER_URL + "?P_fileName=" + filename + "&P_DOCUMENT_SHORT_CODE=ASG" + "&P_PERSON_TYPE=STUDENT&P_CENTRE_CODE=" + studCenterCode + "&P_EMPLOYEE_ID=" + student_id + "&P_DOCPATH=" + dd);

                //creating new dataoutputstream
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


                    BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    filePath = br.readLine();
                    //\\192.168.1.151\d\EmployeeDocuments\LeaveDoc\ENGG_SC\1039140001/demo.docx
                    Log.d(TAG, "BufferedReader Created : " + filePath);

                    funDocumPath();

                    //funUpload();


/*
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {


                                try {

                                    dialog.dismiss();

                                    BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                                    filePath = br.readLine();
                                    //\\192.168.1.151\d\EmployeeDocuments\LeaveDoc\ENGG_SC\1039140001/demo.docx
                                    Log.d(TAG,"BufferedReader Created : "+filePath);
                                    funUpload();

                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                          //  tvFileName.setText("File Upload completed.\n\n You can see the uploaded file here: \n\n" + "uploads/" + fileName);
                        }
                    });
*/
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

                if (wakeLock.isHeld()) {

                    wakeLock.release();
                }


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


}
