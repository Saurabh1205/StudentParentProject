package com.wordpro.studentproject.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
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
import com.wordpro.studentproject.DateValidation;
import com.wordpro.studentproject.R;
import com.wordpro.studentproject.app.MyApplication;
import com.wordpro.studentproject.helper.PrefManager;
import com.wordpro.studentproject.model.DynamicNoticArray;
import com.wordpro.studentproject.model.GetStudentDetailsModel;
import com.wordpro.studentproject.model.JobCategoryModel;
import com.wordpro.studentproject.model.MenuModel;
import com.wordpro.studentproject.model.NoticesModel;
import com.wordpro.studentproject.utils.UtilityClass;
import com.wordpro.studentproject.webConfig.URLEndPoints;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import static com.wordpro.studentproject.activities.NavigationActivity.BASE_URL;
import static com.wordpro.studentproject.network.Network.isNetworkAvailable;

public class VerficationActivity extends Activity {

    EditText edtSecurePin;
    Button btnPin;
    TextView txtForgotPin,txt1,txt2,txt_app_name,txt_login,txt_otp_message;
    private PrefManager pref;
    LinearLayout verificationLyt;
    TextView txtMessage;
    String validSecurityPin;
    public static String TAG=VerficationActivity.class.getSimpleName();
    ProgressDialog mProgressDialog;
    String student_id,department_number,centre_code,acad_session_id,main_semester_type,branch_standard_id;
     private ProgressBar verify_progress;

    //trail version for 30 days
    private final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    private final long ONE_DAY = 24 * 60 * 60 * 1000;
    long diff;
    long days;
    long remainDayToExpire;

    private UtilityClass utilityClassObj;
    //JobCategoryModel
    public static JobCategoryModel jobCategoryModel;
    public static ArrayList<JobCategoryModel.RecordsBean> jobCatCountArrayList;

    //NoticesModel
    public static NoticesModel noticesModel;
    public static ArrayList<NoticesModel.DataBean> noticesArrayList;
    public static ArrayList<DynamicNoticArray> dynamicNoticArrays;
    public static ArrayList<NoticesModel.DataBean> noticesArrayListupdated;

    public static ArrayList<NoticesModel.DataBean> newsArrayList;
    public static ArrayList<NoticesModel.DataBean> eventsArrayList;
    public static ArrayList<NoticesModel.DataBean> videosArrayList;
    public static ArrayList<NoticesModel.DataBean> galleryArrayList;

    public static List<String> listDataHeaderNews;
    public static List<String> listDataHeaderEvents;
    public static List<String> listDataHeaderVideos;
    public static List<String> listDataHeaderGallery;

    public static List<GetStudentDetailsModel.PendingJobDetail> pendingJobDetails;
    public static GetStudentDetailsModel getStudentDetailsModel;
    private Context mContext;
    //---------------------------
    private ImageView verAppLogo;
    public static MenuModel menuModel;
    public static ArrayList<MenuModel.MenuCodeListBean> menuArrayList;
    public ArrayList<String> mainMenuArray;
    String PARENT_MENU_CODE, MENU_CODE,PARENT_MENU_ID,MENU_NAME;
    public static ArrayList<String> subMenuArrayList;
    public static ArrayList<String> otherMenuArrayList;
    public ArrayList<ArrayList<String>> subMenuGroup;
    public StringBuilder menuStringBuilder = new StringBuilder();
    public StringBuilder studSubOtherMenu = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = VerficationActivity.this;
        if (!isNetworkAvailable(mContext)) {
            // Create an Alert Dialog
            AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
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

            setContentView(R.layout.activity_verfication);
            utilityClassObj  = new  UtilityClass(this);

            Typeface type_faceHeading = Typeface.createFromAsset(getAssets(), "font/Poppins-Bold.ttf");
            Typeface type_faceContent = Typeface.createFromAsset(getAssets(), "font/Poppins-Regular.ttf");

            verificationLyt=(LinearLayout)findViewById(R.id.verificationLyt);
            verify_progress =(ProgressBar) findViewById(R.id.verify_progress);
            edtSecurePin=(EditText)findViewById(R.id.edtSecurePin);
            edtSecurePin.setTypeface(type_faceContent);
            btnPin=(Button)findViewById(R.id.btnPin);
            btnPin.setTypeface(type_faceContent);
            txtForgotPin=(TextView)findViewById(R.id.txtForgotPin);
            txtForgotPin.setTypeface(type_faceContent);
            pref = new PrefManager(VerficationActivity.this);
           /* txtMessage=(TextView)findViewById(R.id.txtMessage);
            txtMessage.setTypeface(typefaceContent);*/
           /* txt1=(TextView)findViewById(R.id.txt1);
            txt1.setTypeface(typefaceContent);
            txt2=(TextView)findViewById(R.id.txt2);
            txt2.setTypeface(typefaceContent);*/
            verAppLogo=(ImageView) findViewById(R.id.verAppLogo);
            txt_app_name =(TextView)findViewById(R.id.txt_app_name);
            txt_app_name.setTypeface(type_faceHeading);
            txt_login =(TextView)findViewById(R.id.txt_login);
            txt_login.setTypeface(type_faceHeading);
            txt_otp_message =(TextView)findViewById(R.id.txt_otp_message);
            txt_otp_message.setTypeface(type_faceContent);

           // mProgressDialog=new ProgressDialog(VerficationActivity.this);

            // Displaying user information from shared preferences
            HashMap<String, String> studentDetails = pref.getStudentDetails();
            student_id = studentDetails.get("student_id");
            department_number=studentDetails.get("department_number");
            centre_code=studentDetails.get("centre_code");
            acad_session_id=studentDetails.get("acad_session_id");
            main_semester_type=studentDetails.get("main_semester_type");
            branch_standard_id=studentDetails.get("branch_standard_id");
            if (URLEndPoints.ConstanceLoginUser.equalsIgnoreCase("Student")){

                verAppLogo.setBackgroundResource(R.drawable.new_logo);
                txt_app_name.setText(R.string.Student_app_name);
                btnPin.setBackgroundResource(R.drawable.btn_round_shap_login_btn);
            }else {
                verAppLogo.setBackgroundResource(R.drawable.parapplogo);
                txt_app_name.setText(R.string.Parent_app_name);
                btnPin.setBackgroundResource(R.drawable.btn_round_shap_parentlogin_btn);
            }

            funMenuList(centre_code);
            //funTaskMAnagerGetApi(student_id);

            btnPin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!isNetworkAvailable(mContext)) {
                        // Create an Alert Dialog
                        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
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
                    }else{
                        validSecurityPin = pref.getPin();
                        Log.d("Message_1",validSecurityPin);
                        String securePin = edtSecurePin.getText().toString();
                        if (securePin.length() == 0 || securePin.length() > 4 || securePin.length() < 4) {
                            edtSecurePin.requestFocus();
                            edtSecurePin.setError("Please Enter 4 digit Security Pin");
                        } else if (!securePin.equalsIgnoreCase(validSecurityPin)) {
                            edtSecurePin.requestFocus();
                            edtSecurePin.setError("Enter Valid Security Pin");
                        } else if (securePin.equalsIgnoreCase(validSecurityPin)) {

                            if (!DateValidation.validateDate()){

                                return;
                            }
                            edtSecurePin.setError(null);
                            /*try {
                                mProgressDialog.setMessage("Please wait loading!");
                                mProgressDialog.setCanceledOnTouchOutside(false);
                                mProgressDialog.show();
                            }catch (Exception e){
                                e.printStackTrace();
                            }
*/
                            //closeKeyboard();
                            new Handler().postDelayed(new Runnable() {

                                // Using handler with postDelayed called runnable run method
                                @Override
                                public void run() {

                                    funJobManager();

                                }
                            }, 3* 1000);

                        }

                    }

                }
            });

            txtForgotPin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    logout();
                }
            });
        }
    }

    private void funJobManager() {
        utilityClassObj.startLoader(this,R.drawable.image_for_rotation);
        Log.d("Message_2","inside funJobManager");
        String url = pref.getURL() + URLEndPoints.PenCategory_URL;
        Log.d(TAG, "URL : " + url);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                utilityClassObj.stopLoader();
                Log.d(TAG, response.toString());
                Toast.makeText(VerficationActivity.this, "working", Toast.LENGTH_SHORT).show();
                try {

                    Gson gson = new Gson();
                    jobCategoryModel = gson.fromJson(response, JobCategoryModel.class);

                    if (jobCategoryModel.getStatus() == 1) {
                       // mProgressDialog.dismiss();
                        utilityClassObj.stopLoader();
                        String STATUS = jobCategoryModel.getSTATUS();
                        String REASON = jobCategoryModel.getREASON();
                        if (STATUS.equalsIgnoreCase("TRUE")) {

                            jobCatCountArrayList = (ArrayList<JobCategoryModel.RecordsBean>) jobCategoryModel.getRecords();
                         //  funNotices();
                            Intent intent = new Intent(VerficationActivity.this, NavigationActivity.class);
                            intent.putExtra("activity", "VerifyActivity");
                            startActivity(intent);
                            finish();
                           /* Intent intent = new Intent(VerficationActivity.this, NavigationActivity.class);
                            intent.putExtra("activity","VerifyActivity");
                            startActivity(intent);
                            finish();
*/

                        } else if (STATUS.equalsIgnoreCase("FALSE")) {
                            utilityClassObj.stopLoader();
                            Snackbar.make(verificationLyt, REASON, Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                            jobCatCountArrayList = (ArrayList<JobCategoryModel.RecordsBean>) jobCategoryModel.getRecords();
                         //   funNotices();
                          /*  Intent intent = new Intent(VerficationActivity.this, NavigationActivity.class);
                            intent.putExtra("activity","VerifyActivity");
                            startActivity(intent);
                            finish();*/

                            Intent intent = new Intent(VerficationActivity.this, NavigationActivity.class);
                            intent.putExtra("activity", "VerifyActivity");
                            startActivity(intent);
                            finish();
                        }

                    } else {
                       // mProgressDialog.hide();
                        utilityClassObj.stopLoader();
                        Snackbar.make(verificationLyt, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }

                } catch (Exception e) {
                    //mProgressDialog.hide();
                    utilityClassObj.stopLoader();
                    Snackbar.make(verificationLyt, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    Log.d(TAG, "Error : " + e.getMessage());
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
               // mProgressDialog.hide();
                utilityClassObj.stopLoader();
                handleVolleyError(error);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();

                params.put("PI_EMPLOYEE_ID",student_id);
                params.put("PI_EMPLOYEE_TYPE", "S");
                params.put("PI_WORK_DESIG_CODE", "NA");
                params.put("PI_WORK_DESG_MST_ID", "0");
                params.put("PI_DEPARTMENT_NUMBER", department_number);
                params.put("PI_CENTER_CODE", centre_code);
                params.put("PI_FILTER_VAL", "0");
                params.put("PI_SESSION_ID", acad_session_id);
                params.put("PI_Semester_Type", main_semester_type);
                params.put("PI_Interface_from", "NA");

                Log.e(TAG, " Posting params: " + params.toString());
                return params;
            }
        };

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(60000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        // Adding request to request queue

        MyApplication.getInstance().addToRequestQueue(stringRequest);

    }

    private void funTaskMAnagerGetApi(String student_id) {


        //String url=pref.getURL()+"PendingCate";
        String url = BASE_URL+ URLEndPoints.GetStudentDetails_URL;
        Log.d(TAG, "URL : " + url);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d(TAG, response.toString());

                try {
                    Gson gson = new Gson();


                    getStudentDetailsModel = gson.fromJson(response, GetStudentDetailsModel.class);

                    if (getStudentDetailsModel.getStatus() == 1) {

                        pendingJobDetails = (ArrayList<GetStudentDetailsModel.PendingJobDetail>) getStudentDetailsModel.getPendingJobDetails();
                        // pendingJobDetails = (ArrayList<GetStudentDetailsModel.P>) jobManagerModel.getPendingJobSummery();
                        if (pendingJobDetails != null && pendingJobDetails.size() != 0) {


                        } else {
                            Snackbar.make(verificationLyt, "Record not available.", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        }

                    } else if (getStudentDetailsModel.getStatus() == 0) {

                        Snackbar.make(verificationLyt, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }

                } catch (Exception e) {

                    Snackbar.make(verificationLyt, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    Log.d(TAG, "Error : " + e.getMessage());


                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                handleVolleyError(error);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<String, String>();
                params.put("Usertype", "STUDENT");
                params.put("studentid",student_id );

                Log.e(TAG, " Posting params: " + params.toString());
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

        Toast.makeText(mContext, ""+message, Toast.LENGTH_SHORT).show();
    }

    private void funNotices() {

        String url = pref.getURL() + URLEndPoints.getNoticesURL(centre_code,department_number,student_id,branch_standard_id);

        Log.d(TAG, "URL : " + url);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, response.toString());
                try {
                    Gson gson = new Gson();
                    noticesModel = gson.fromJson(response, NoticesModel.class);

                    if (noticesModel.getStatus() == 1) {

                        noticesArrayList = (ArrayList<NoticesModel.DataBean>) noticesModel.getData();

                        funGetDownloadLink();

                    } else {
                       // mProgressDialog.hide();
                        Snackbar.make(verificationLyt, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }

                } catch (Exception e) {
                  //  mProgressDialog.hide();
                    Snackbar.make(verificationLyt, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    Log.d(TAG, "Error : " + e.getMessage());


                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
               // mProgressDialog.hide();
               handleVolleyError(error);
            }
        });

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(60000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        // Adding request to request queue

        MyApplication.getInstance().addToRequestQueue(stringRequest);


    }

    private void funGetDownloadLink() {

        noticesArrayListupdated = new ArrayList<>();

        newsArrayList = new ArrayList<>();
        eventsArrayList = new ArrayList<>();
        videosArrayList = new ArrayList<>();
        galleryArrayList = new ArrayList<>();

        for (int i = 0; i < noticesArrayList.size(); i++) {

            NoticesModel.DataBean data = new NoticesModel.DataBean();
            String ATTACHMENT_SOURCE = noticesArrayList.get(i).getATTACHMENT_SOURCE();
            String ATTACHMENT_LINK = noticesArrayList.get(i).getATTACHMENT_LINK();

            if(ATTACHMENT_SOURCE.equalsIgnoreCase("")){

            }else if(ATTACHMENT_SOURCE.equalsIgnoreCase("")){

            }

            String APP_NOTIFICATION = noticesArrayList.get(i).getAPP_NOTIFICATION();

            if (ATTACHMENT_SOURCE.equalsIgnoreCase("OFFLINE")) {
                ATTACHMENT_LINK = noticesArrayList.get(i).getATTACHMENT_LINK();
                String str = pref.getURL();          //http://117.247.82.252:500/api/
                String[] s = str.split(Pattern.quote("api"));
                ATTACHMENT_LINK = s[0] + ATTACHMENT_LINK;
                Log.d("download URL : ", ATTACHMENT_LINK);
            }

            String ATTACHMENT_NAME = noticesArrayList.get(i).getATTACHMENT_NAME();
            ATTACHMENT_SOURCE = noticesArrayList.get(i).getATTACHMENT_SOURCE();
            String ATTACHMENT_TYPE = noticesArrayList.get(i).getATTACHMENT_TYPE();
            String GROUP_NAMEE = noticesArrayList.get(i).getGROUP_NAME();
            String MARQUEE = noticesArrayList.get(i).getMARQUEE();
            String NOTC_ATTACHMENT_DTLS_ID = noticesArrayList.get(i).getNOTC_ATTACHMENT_DTLS_ID();
            String NOTC_DISPLAY_PATTRN_ID = noticesArrayList.get(i).getNOTC_DISPLAY_PATTRN_ID();
            String NOTC_GROUP_MASTER_ID = noticesArrayList.get(i).getNOTC_GROUP_MASTER_ID();
            String NOTC_MASTER_ID = noticesArrayList.get(i).getNOTC_MASTER_ID();
            String NOTICE_DESCRIPTION = noticesArrayList.get(i).getNOTICE_DESCRIPTION();
            String NOTICE_HEADER = noticesArrayList.get(i).getNOTICE_HEADER();
            String NOTICE_PATTERN = noticesArrayList.get(i).getNOTICE_PATTERN();
            String SOFT_COPY_NAME = noticesArrayList.get(i).getSOFT_COPY_NAME();
            String SOFT_COPY_PATH = noticesArrayList.get(i).getSOFT_COPY_PATH();
            String SOFT_COPY_SIZE = noticesArrayList.get(i).getSOFT_COPY_SIZE();
            String STANDARD_PATH = noticesArrayList.get(i).getSTANDARD_PATH();


            data.setAPP_NOTIFICATION(APP_NOTIFICATION);
            data.setATTACHMENT_LINK(ATTACHMENT_LINK);
            data.setATTACHMENT_NAME(ATTACHMENT_NAME);
            data.setATTACHMENT_SOURCE(ATTACHMENT_SOURCE);
            data.setATTACHMENT_TYPE(ATTACHMENT_TYPE);
            data.setGROUP_NAME(GROUP_NAMEE);
            data.setMARQUEE(MARQUEE);
            data.setNOTC_ATTACHMENT_DTLS_ID(NOTC_ATTACHMENT_DTLS_ID);
            data.setNOTC_DISPLAY_PATTRN_ID(NOTC_DISPLAY_PATTRN_ID);
            data.setNOTC_GROUP_MASTER_ID(NOTC_GROUP_MASTER_ID);
            data.setNOTC_MASTER_ID(NOTC_MASTER_ID);
            data.setNOTICE_DESCRIPTION(NOTICE_DESCRIPTION);
            data.setNOTICE_HEADER(NOTICE_HEADER);
            data.setNOTICE_PATTERN(NOTICE_PATTERN);
            data.setSOFT_COPY_NAME(SOFT_COPY_NAME);
            data.setSOFT_COPY_PATH(SOFT_COPY_PATH);
            data.setSOFT_COPY_SIZE(SOFT_COPY_SIZE);
            data.setSTANDARD_PATH("\\\\" + STANDARD_PATH);

            noticesArrayListupdated.add(data);


        }

        if (noticesArrayListupdated.size() != 0 && noticesArrayListupdated != null) {

            // noticesArrayList=noticesArrayListupdated;

            for (int i = 0; i < noticesArrayListupdated.size(); i++) {

                String NOTICE_PATTERN = noticesArrayListupdated.get(i).getNOTICE_PATTERN();

                if (NOTICE_PATTERN.equalsIgnoreCase("NEWSBOX")) {

                    newsArrayList.add(noticesArrayListupdated.get(i));

                } else if (NOTICE_PATTERN.equalsIgnoreCase("EVENTBOX")) {

                    eventsArrayList.add(noticesArrayListupdated.get(i));

                } else if (NOTICE_PATTERN.equalsIgnoreCase("VIDEOBOX")) {

                    videosArrayList.add(noticesArrayListupdated.get(i));

                } else if (NOTICE_PATTERN.equalsIgnoreCase("GALLERYBOX")) {

                    galleryArrayList.add(noticesArrayListupdated.get(i));

                }

            }

            funArrangeData();

        } else {
           // mProgressDialog.dismiss();
            Intent intent = new Intent(VerficationActivity.this, NavigationActivity.class);
            intent.putExtra("activity", "VerifyActivity");
            startActivity(intent);
            finish();
        }

    }


    private void funArrangeData() {

        if (newsArrayList != null && newsArrayList.size() != 0) {
            listDataHeaderNews = new ArrayList<String>();

            for (int i = 0; i < newsArrayList.size(); i++) {

                String GROUP_NAME = newsArrayList.get(i).getGROUP_NAME();
                listDataHeaderNews.add(GROUP_NAME);
            }
            Object[] st = listDataHeaderNews.toArray();
            for (Object s : st) {
                if (listDataHeaderNews.indexOf(s) != listDataHeaderNews.lastIndexOf(s)) {
                    listDataHeaderNews.remove(listDataHeaderNews.lastIndexOf(s));
                }
            }
            for (String ListDataHeader : listDataHeaderNews) {
                Log.d(TAG, "ListDataHeader :  " + ListDataHeader);
            }

        }

        if (eventsArrayList != null && eventsArrayList.size() != 0) {
            listDataHeaderEvents = new ArrayList<String>();

            for (int i = 0; i < eventsArrayList.size(); i++) {

                String GROUP_NAME = eventsArrayList.get(i).getGROUP_NAME();
                listDataHeaderEvents.add(GROUP_NAME);
            }
            Object[] st = listDataHeaderEvents.toArray();
            for (Object s : st) {
                if (listDataHeaderEvents.indexOf(s) != listDataHeaderEvents.lastIndexOf(s)) {
                    listDataHeaderEvents.remove(listDataHeaderEvents.lastIndexOf(s));
                }
            }
            for (String ListDataHeader : listDataHeaderEvents) {
                Log.d(TAG, "ListDataHeader :  " + ListDataHeader);
            }

        }


        if (videosArrayList != null && videosArrayList.size() != 0) {
            listDataHeaderVideos = new ArrayList<String>();

            for (int i = 0; i < videosArrayList.size(); i++) {

                String GROUP_NAME = videosArrayList.get(i).getGROUP_NAME();
                listDataHeaderVideos.add(GROUP_NAME);
            }
            Object[] st = listDataHeaderVideos.toArray();
            for (Object s : st) {
                if (listDataHeaderVideos.indexOf(s) != listDataHeaderVideos.lastIndexOf(s)) {
                    listDataHeaderVideos.remove(listDataHeaderVideos.lastIndexOf(s));
                }
            }
            for (String ListDataHeader : listDataHeaderVideos) {
                Log.d(TAG, "ListDataHeader :  " + ListDataHeader);
            }

        }
        if (galleryArrayList != null && galleryArrayList.size() != 0) {
            listDataHeaderGallery = new ArrayList<String>();

            for (int i = 0; i < galleryArrayList.size(); i++) {

                String GROUP_NAME = galleryArrayList.get(i).getGROUP_NAME();
                listDataHeaderGallery.add(GROUP_NAME);
            }
            Object[] st = listDataHeaderGallery.toArray();
            for (Object s : st) {
                if (listDataHeaderGallery.indexOf(s) != listDataHeaderGallery.lastIndexOf(s)) {
                    listDataHeaderGallery.remove(listDataHeaderGallery.lastIndexOf(s));
                }
            }
            for (String ListDataHeader : listDataHeaderGallery) {
                Log.d(TAG, "ListDataHeader :  " + ListDataHeader);
            }
        }

      //  mProgressDialog.dismiss();
        Intent intent = new Intent(VerficationActivity.this, NavigationActivity.class);
        intent.putExtra("activity", "VerifyActivity");
        startActivity(intent);
        finish();


    }
    private void funMenuList(String centre_code) {

        utilityClassObj.startLoader(this,R.drawable.image_for_rotation);

        String url = pref.getURL() + URLEndPoints.getMenuList_URL(centre_code);
        Log.d(TAG, url);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, response.toString());

                try {

                    JSONObject responseObj = new JSONObject(response);
                    int status = responseObj.getInt("status");
                    if (status == 1) {
                        Gson gson = new Gson();
                        menuModel = gson.fromJson(response, MenuModel.class);


                        if (menuModel.getStatus() == 1) {

                            menuArrayList = (ArrayList<MenuModel.MenuCodeListBean>) menuModel.getMenuCodeList();

                            if (menuArrayList != null) {


                                mainMenuArray = new ArrayList<>();
                                subMenuArrayList = new ArrayList<>();
                                otherMenuArrayList = new ArrayList<>();
                                subMenuGroup = new ArrayList<>();


                                for (int i = 0; i < menuArrayList.size(); i++) {

                                    PARENT_MENU_CODE = menuArrayList.get(i).getPARENT_MENU_CODE();
                                    MENU_CODE = menuArrayList.get(i).getMENU_CODE();
                                    PARENT_MENU_ID = menuArrayList.get(i).getPARENT_MENU_ID();
                                    MENU_NAME = menuArrayList.get(i).getMENU_NAME();
                                    if (PARENT_MENU_CODE.equalsIgnoreCase(null) || PARENT_MENU_CODE.equalsIgnoreCase("") || PARENT_MENU_CODE.isEmpty()) {
                                       if(PARENT_MENU_ID.equalsIgnoreCase("0")){
                                           mainMenuArray.add(MENU_NAME);
                                       }


                                    }

                                }

                                Log.d(TAG, "Main Menu : " + mainMenuArray);
                                Log.d(TAG, "Main Menu Size : " + mainMenuArray.size());

                                Set<String> set = new HashSet<String>();
                                set.addAll(mainMenuArray);
                                pref.setMenuArrayList(set);


                                for (int j = 0; j < mainMenuArray.size(); j++) {

                                    String menuName = mainMenuArray.get(j);

                                    menuStringBuilder.append(menuName);
                                    menuStringBuilder.append("#");

                                    ArrayList<String> submenu = new ArrayList<>();

                                    for (int i = 0; i < menuArrayList.size(); i++) {

                                        PARENT_MENU_CODE = menuArrayList.get(i).getPARENT_MENU_CODE();
                                        MENU_CODE = menuArrayList.get(i).getMENU_CODE();

                                        String subMenuName = MENU_CODE;

                                        if (PARENT_MENU_CODE.equalsIgnoreCase(mainMenuArray.get(j))) {


                                            submenu.add(MENU_CODE);
                                            menuStringBuilder.append(subMenuName);
                                            menuStringBuilder.append(",");

                                        }

                                    }

                                    Log.d(TAG, "PARENT_MENU_CODE : " + submenu);
                                    Log.d(TAG, "SubMenu : " + submenu);
                                    Log.d(TAG, "Submenu Size: " + submenu.size());


                                    subMenuGroup.add(submenu);


                                    for (int ii = 0; ii < submenu.size(); ii++) {

                                        ArrayList<String> subOthermenu = new ArrayList<>();

                                        for (int jj = 0; jj < menuArrayList.size(); jj++) {

                                            PARENT_MENU_CODE = menuArrayList.get(jj).getPARENT_MENU_CODE();
                                            MENU_CODE = menuArrayList.get(jj).getMENU_CODE();

                                            if (PARENT_MENU_CODE.equalsIgnoreCase(submenu.get(ii))) {


                                                subOthermenu.add(MENU_CODE);
                                                String otherSubMenu = MENU_CODE;
                                                studSubOtherMenu.append(otherSubMenu);
                                                studSubOtherMenu.append(",");

                                            }


                                        }
                                        Log.d(TAG, "SubOtherMenu : " + subOthermenu);
                                        Log.d(TAG, "SubOthermenu Size: " + subOthermenu.size());
                                    }

                                    menuStringBuilder.append("@");
                                }


                                Log.d(TAG, "Submenu Group Array : " + subMenuGroup);
                                Log.d(TAG, "Submenu Group Size : " + subMenuGroup.size());

                                String allMenu = String.valueOf(menuStringBuilder);
                                if (allMenu != null || !allMenu.isEmpty()) {
                                    allMenu = allMenu.substring(0, allMenu.length() - 2);
                                }
                                Log.d(TAG, "Menu String : " + allMenu);

                                pref.setMenuStringBuilder(allMenu);

                                if(studSubOtherMenu.length()!=0){
                                    String allSubOtherMenu = String.valueOf(studSubOtherMenu);
                                    if (allSubOtherMenu != null || !allSubOtherMenu.isEmpty()) {
                                        allSubOtherMenu = allSubOtherMenu.substring(0, allSubOtherMenu.length() - 1);

                                    }
                                    Log.d(TAG, "Menu SubOtherMenu : " + allSubOtherMenu);

                                    pref.setSubOtherMenu(allSubOtherMenu);
                                }




                              /*  pref.setSubMenuArray(subMenuGroup);

                                Intent intent = new Intent(VerficationActivity.this, NavigationActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                                finish();*/
                                utilityClassObj.stopLoader();

                            } else {

                                //  Toast.makeText(SmsActivity.this, "Menu Rights not alloted", Toast.LENGTH_LONG).show();
                                Snackbar snackbar = Snackbar
                                        .make(verificationLyt, "Menu Rights not alloted", Snackbar.LENGTH_LONG);

                                snackbar.show();
                                utilityClassObj.stopLoader();

                            }


                        } else if (menuModel.getStatus() == 0) {

                            // Toast.makeText(SmsActivity.this, "Something went wrong", Toast.LENGTH_LONG).show();
                            Snackbar snackbar = Snackbar
                                    .make(verificationLyt, "Server not responding.Please try later.", Snackbar.LENGTH_LONG);

                            snackbar.show();
                            utilityClassObj.stopLoader();


                        }

                    }else {

                        //  Toast.makeText(SmsActivity.this, "Please try again", Toast.LENGTH_LONG).show();
                        Snackbar snackbar = Snackbar
                                .make(verificationLyt, "Server not responding.Please try later.", Snackbar.LENGTH_LONG);

                        snackbar.show();
                        utilityClassObj.stopLoader();

                    }

                } catch (JSONException e) {
                    Log.d(TAG, "Error: " + e.getMessage());
                    Snackbar snackbar = Snackbar
                            .make(verificationLyt, "Server not responding.Please try later.", Snackbar.LENGTH_LONG);
                    snackbar.show();
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
                200000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        // Adding request to request queue

        MyApplication.getInstance().addToRequestQueue(stringRequest);


    }
    private void closeKeyboard() {
        InputMethodManager inputManager = (InputMethodManager)this.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

    }
    private void logout() {
        pref.clearSession();
        Intent intent = new Intent(VerficationActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onPause() {
        utilityClassObj.stopLoader();
        super.onPause();
    }

    @Override
    protected void onStop() {
        utilityClassObj.stopLoader();
        super.onStop();
    }
    @Override
    public void finish() {
        if(utilityClassObj != null) {
            utilityClassObj.stopLoader();
        }
        super.finish();
    }

    @Override
    protected void onDestroy() {
        try {
            if (utilityClassObj != null)
                utilityClassObj.stopLoader();
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onDestroy();
    }
}
