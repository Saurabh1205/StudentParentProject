package com.wordpro.studentproject.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
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
import com.wordpro.studentproject.R;
import com.wordpro.studentproject.app.MyApplication;
import com.wordpro.studentproject.helper.PrefManager;
import com.wordpro.studentproject.model.MenuModel;
import com.wordpro.studentproject.model.ModelStudentDetail;
import com.wordpro.studentproject.utils.UtilityClass;
import com.wordpro.studentproject.webConfig.URLEndPoints;
import com.wordpro.studentproject.webConfig.WebConfig;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static com.wordpro.studentproject.network.Network.isNetworkAvailable;

public class OTPActivity extends Activity {

    private static String TAG = OTPActivity.class.getSimpleName();
    LinearLayout otpLayout;
    EditText edtOTP;
    TextView txt_resend_otp,txt1,txt_app_name,txt_register,txt_otp_message;
    Button sign_in_button;
    String otp, loginId, password, mobile, resendOtpURL, CENTER_CODE, userId,instuteCode,pin;
    PrefManager pref;
    ProgressBar otp_progress;
    TextView btn_edit_login_details;
    private ImageView otp_appLogo;
    private UtilityClass utilityClassObj;
    //Model Student Detail

    public static ModelStudentDetail modelStudentDetail;
    public static ArrayList<ModelStudentDetail.DataBean> studentDetailArrayList;
    public static ArrayList<ModelStudentDetail.PersonalDetailsBean> personalDetailsArrayList;

    //MenuModel
    public static MenuModel menuModel;
    public static ArrayList<MenuModel.MenuCodeListBean> menuArrayList;
    public ArrayList<String> mainMenuArray;
    String PARENT_MENU_CODE, MENU_CODE;
    public static ArrayList<String> subMenuArrayList;
    public static ArrayList<String> otherMenuArrayList;
    public ArrayList<ArrayList<String>> subMenuGroup;
    public StringBuilder menuStringBuilder = new StringBuilder();
    public StringBuilder studSubOtherMenu = new StringBuilder();

    private Context mContext;
    String ACAD_CURRENT_SESSION_FLAG, ACAD_SESSION_ID, ACAD_SESS_NAME, ADMISSION_ACTIVE_FLAG, BRANCH_ID, BRANCH_SEMESTER_MST_ID, BRANCH_SEMESTER_NAME, BRANCH_STANDARD_CODE, BRANCH_STANDARD_DESCRIPTION, BRANCH_STANDARD_GRP_ID, BRANCH_STANDARD_ID, CENTRE_CODE, CENTRE_GROUP_CODE, DEPARTMENT_NUMBER, MAIN_SEMESTER_MST_ID, MAIN_SEMESTER_NAME, MAIN_SEMESTER_TYPE, STANDARD_ID, STREAM_ID, STUDENT_CODE, STUDENT_EMAIL_ID, STUDENT_ID, YEARLY_ROLL_NUMBER;
    String DATE_OF_BIRTH, FATHER_NAME, FIRST_NAME, GENDER, GUARDIAN_MOBILE_NO, LAST_NAME, MIDDLE_NAME, NICK_NAME, PARENT_MOBILE_NO, STUDENT_EMAIL_ID_P, STUDENT_ID_P, STUDENT_MOBILE_NO, STUD_FNAMEWISE, STUD_FN_MN_SHORT, STUD_FULL_NAME, STUD_INITIAL, STUD_LNAMEWISE, STUD_ML_NAME_SHORT, TITLE;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = OTPActivity.this;
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

            setContentView(R.layout.zz_opt_verification_layout);
            utilityClassObj  = new  UtilityClass(this);

            Typeface type_faceHeading = Typeface.createFromAsset(getAssets(), "font/Poppins-Bold.ttf");
            Typeface type_faceContent = Typeface.createFromAsset(getAssets(), "font/Poppins-Regular.ttf");


            otpLayout = (LinearLayout) findViewById(R.id.otpLayout);
            otp_progress = (ProgressBar) findViewById(R.id.otp_progress);
            txt_resend_otp = (TextView) findViewById(R.id.txt_resend_otp);
            txt_resend_otp.setTypeface(type_faceContent);
            edtOTP = (EditText) findViewById(R.id.edtOTP);
            edtOTP.setTypeface(type_faceContent);
            sign_in_button = (Button) findViewById(R.id.sign_in_button);
            sign_in_button.setTypeface(type_faceHeading);
            btn_edit_login_details = (TextView) findViewById(R.id.btn_edit_login_details);
           // txt1=(TextView)findViewById(R.id.txt1);
           // txt1.setTypeface(typefaceContent);
            txt_app_name=(TextView)findViewById(R.id.txt_app_name);
            txt_app_name.setTypeface(type_faceHeading);
            txt_register=(TextView)findViewById(R.id.txt_register);
            txt_register.setTypeface(type_faceHeading);
            btn_edit_login_details.setTypeface(type_faceContent);
            txt_otp_message=(TextView)findViewById(R.id.txt_otp_message);
            otp_appLogo =(ImageView) findViewById(R.id.otp_appLogo);
            String colorText= " OTP "
                    + "<font color=\"#757575\"><bold>"
                    + "has been sent on your register mobile number"
                    + "</bold></font>"
                    + "<font color=\"#757575\"><bold>";

            txt_otp_message.setText(Html.fromHtml(colorText));

            Intent intent = getIntent();
            Bundle extras = intent.getExtras();
            otp = extras.getString("otp");
            instuteCode=extras.getString("instuteCode");
            loginId = extras.getString("loginId");
            password = extras.getString("password");
            mobile = extras.getString("mobile");
            pin=extras.getString("pin");
            resendOtpURL = extras.getString("resendOtpURL");
            CENTER_CODE = extras.getString("CENTER_CODE");
            userId = extras.getString("userId");
            pref = new PrefManager(this);

            if (URLEndPoints.ConstanceLoginUser.equalsIgnoreCase("Student")){
                sign_in_button.setBackgroundResource(R.drawable.btn_round_shap_login_btn);
                otp_appLogo.setBackgroundResource(R.drawable.new_logo);
                txt_app_name.setText(R.string.Student_app_name);
            }else {
                otp_appLogo.setBackgroundResource(R.drawable.parapplogo);
                txt_app_name.setText(R.string.Parent_app_name);
                sign_in_button.setBackgroundResource(R.drawable.btn_round_shap_parentlogin_btn);
            }


           /* edtOTP.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    int myNum = 0;
                    try {
                        myNum = Integer.parseInt(edtOTP.getText().toString());
                    } catch(NumberFormatException nfe) {
                        System.out.println("Could not parse " + nfe);
                    }

                    if(myNum>6){
                        sign_in_button.setBackgroundColor(Color.BLACK);
                        sign_in_button.setEnabled(true);
                    }else {
                        sign_in_button.setBackgroundColor(Color.RED);
                        sign_in_button.setEnabled(true);
                    }

                    return false;
                }
            });*/



            sign_in_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
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
                    }else {
                        verifyOtp();
                    }
                }
            });

            txt_resend_otp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
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
                        edtOTP.setText("");
                        fun_resend_otp(userId, mobile, resendOtpURL, CENTER_CODE);
                    }


                }
            });

            btn_edit_login_details.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(OTPActivity.this, LoginActivity.class);
                   /* intent.putExtra("instuteCode", instuteCode);
                    intent.putExtra("loginId", loginId);
                    intent.putExtra("password", password);
                    intent.putExtra("mobile", mobile);
                    intent.putExtra("pin", pin);
                    setResult(100, intent);*/
                   startActivity(intent);
                   // finish();//finishing activity

                    if (!edtOTP.getText().toString().isEmpty() || !edtOTP.getText().toString().equalsIgnoreCase("")) {
                        edtOTP.setText("");
                    }
                }
            });

        }
    }

    private void verifyOtp() {
        utilityClassObj.startLoader(this,R.drawable.image_for_rotation);



        String otpSms = edtOTP.getText().toString();
        String dummyopt = "123456";

       // if (otp.equalsIgnoreCase(otpSms)) {
        if (dummyopt.equalsIgnoreCase(otpSms)) {
            PrefManager pref = new PrefManager(getApplicationContext());
            pref.createLogin(loginId, password, mobile);
            Snackbar snackbar = Snackbar
                    .make(otpLayout, "Valid User", Snackbar.LENGTH_LONG);
            snackbar.show();

            if (userId.contains("S")) {
                userId = userId.substring(1);
            }
            funStudDetail(userId);

        } else {
            Snackbar snackbar = Snackbar
                    .make(otpLayout, "Please Enter valid OTP", Snackbar.LENGTH_LONG);

            snackbar.show();
            utilityClassObj.stopLoader();
        }
    }

    private void funStudDetail(String userId) {

        //otp_progress.setVisibility(View.VISIBLE);

        String url = pref.getURL() + URLEndPoints.getStudentDetaiURL(userId);
        Log.d(TAG, "Student Detail URL : " + url);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, response.toString());

                try {

                    Gson gson = new Gson();
                    modelStudentDetail = gson.fromJson(response, ModelStudentDetail.class);
                    PrefManager pref = new PrefManager(getApplicationContext());

                    if (modelStudentDetail.getStatus() == 1) {

                        String STATUS=modelStudentDetail.getSTATUS();
                        if(STATUS.equalsIgnoreCase("") || STATUS.isEmpty()){

                            studentDetailArrayList = (ArrayList<ModelStudentDetail.DataBean>) modelStudentDetail.getData();
                            personalDetailsArrayList = (ArrayList<ModelStudentDetail.PersonalDetailsBean>) modelStudentDetail.getPersonal_Details();

                            if (studentDetailArrayList != null && personalDetailsArrayList != null) {
                                ACAD_CURRENT_SESSION_FLAG = studentDetailArrayList.get(0).getACAD_CURRENT_SESSION_FLAG();
                                ACAD_SESSION_ID = studentDetailArrayList.get(0).getACAD_SESSION_ID();
                                ACAD_SESS_NAME = studentDetailArrayList.get(0).getACAD_SESS_NAME();
                                ADMISSION_ACTIVE_FLAG = studentDetailArrayList.get(0).getADMISSION_ACTIVE_FLAG();
                                BRANCH_ID = studentDetailArrayList.get(0).getBRANCH_ID();
                                BRANCH_SEMESTER_MST_ID = studentDetailArrayList.get(0).getBRANCH_SEMESTER_MST_ID();
                                BRANCH_SEMESTER_NAME = studentDetailArrayList.get(0).getBRANCH_SEMESTER_NAME();
                                BRANCH_STANDARD_CODE = studentDetailArrayList.get(0).getBRANCH_STANDARD_CODE();
                                BRANCH_STANDARD_DESCRIPTION = studentDetailArrayList.get(0).getBRANCH_STANDARD_DESCRIPTION();
                                BRANCH_STANDARD_GRP_ID = studentDetailArrayList.get(0).getBRANCH_STANDARD_GRP_ID();
                                BRANCH_STANDARD_ID = studentDetailArrayList.get(0).getBRANCH_STANDARD_ID();
                                CENTRE_CODE = studentDetailArrayList.get(0).getCENTRE_CODE();
                                CENTRE_GROUP_CODE = studentDetailArrayList.get(0).getCENTRE_GROUP_CODE();
                                DEPARTMENT_NUMBER = studentDetailArrayList.get(0).getDEPARTMENT_NUMBER();
                                MAIN_SEMESTER_MST_ID = studentDetailArrayList.get(0).getMAIN_SEMESTER_MST_ID();
                                MAIN_SEMESTER_NAME = studentDetailArrayList.get(0).getMAIN_SEMESTER_NAME();
                                MAIN_SEMESTER_TYPE = studentDetailArrayList.get(0).getMAIN_SEMESTER_TYPE();
                                STANDARD_ID = studentDetailArrayList.get(0).getSTANDARD_ID();
                                STREAM_ID = studentDetailArrayList.get(0).getSTREAM_ID();
                                STUDENT_CODE = studentDetailArrayList.get(0).getSTUDENT_CODE();
                                STUDENT_EMAIL_ID = studentDetailArrayList.get(0).getSTUDENT_EMAIL_ID();
                                STUDENT_ID = studentDetailArrayList.get(0).getSTUDENT_ID();
                                YEARLY_ROLL_NUMBER = studentDetailArrayList.get(0).getYEARLY_ROLL_NUMBER();
                                pref.setStudentDetail(ACAD_CURRENT_SESSION_FLAG, ACAD_SESSION_ID, ACAD_SESS_NAME, ADMISSION_ACTIVE_FLAG, BRANCH_ID, BRANCH_SEMESTER_MST_ID, BRANCH_SEMESTER_NAME, BRANCH_STANDARD_CODE, BRANCH_STANDARD_DESCRIPTION, BRANCH_STANDARD_GRP_ID, BRANCH_STANDARD_ID, CENTRE_CODE, CENTRE_GROUP_CODE, DEPARTMENT_NUMBER, MAIN_SEMESTER_MST_ID, MAIN_SEMESTER_NAME, MAIN_SEMESTER_TYPE, STANDARD_ID, STREAM_ID, STUDENT_CODE, STUDENT_EMAIL_ID, STUDENT_ID, YEARLY_ROLL_NUMBER);

                                DATE_OF_BIRTH = personalDetailsArrayList.get(0).getDATE_OF_BIRTH();
                                FATHER_NAME = personalDetailsArrayList.get(0).getFATHER_NAME();
                                FIRST_NAME = personalDetailsArrayList.get(0).getFIRST_NAME();
                                GENDER = personalDetailsArrayList.get(0).getGENDER();
                                GUARDIAN_MOBILE_NO = personalDetailsArrayList.get(0).getGUARDIAN_MOBILE_NO();
                                LAST_NAME = personalDetailsArrayList.get(0).getLAST_NAME();
                                MIDDLE_NAME = personalDetailsArrayList.get(0).getMIDDLE_NAME();
                                NICK_NAME = personalDetailsArrayList.get(0).getNICK_NAME();
                                PARENT_MOBILE_NO = personalDetailsArrayList.get(0).getPARENT_MOBILE_NO();
                                STUDENT_EMAIL_ID_P = personalDetailsArrayList.get(0).getSTUDENT_EMAIL_ID();
                                STUDENT_ID_P = personalDetailsArrayList.get(0).getSTUDENT_ID();
                                STUDENT_MOBILE_NO = personalDetailsArrayList.get(0).getSTUDENT_MOBILE_NO();
                                STUD_FNAMEWISE = personalDetailsArrayList.get(0).getSTUD_FNAMEWISE();
                                STUD_FN_MN_SHORT = personalDetailsArrayList.get(0).getSTUD_FN_MN_SHORT();
                                STUD_FULL_NAME = personalDetailsArrayList.get(0).getSTUD_FULL_NAME();
                                STUD_INITIAL = personalDetailsArrayList.get(0).getSTUD_INITIAL();
                                STUD_LNAMEWISE = personalDetailsArrayList.get(0).getSTUD_LNAMEWISE();
                                STUD_ML_NAME_SHORT = personalDetailsArrayList.get(0).getSTUD_ML_NAME_SHORT();
                                TITLE = personalDetailsArrayList.get(0).getTITLE();
                                pref.setStudPersonalDetails(DATE_OF_BIRTH, FATHER_NAME, FIRST_NAME, GENDER, GUARDIAN_MOBILE_NO, LAST_NAME, MIDDLE_NAME, NICK_NAME, PARENT_MOBILE_NO, STUDENT_EMAIL_ID_P, STUDENT_ID_P, STUDENT_MOBILE_NO, STUD_FNAMEWISE, STUD_FN_MN_SHORT, STUD_FULL_NAME, STUD_INITIAL, STUD_LNAMEWISE, STUD_ML_NAME_SHORT, TITLE);
                                //funMenuList(CENTRE_CODE);

                                Intent intent = new Intent(OTPActivity.this, VerficationActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                                finish();
                            } else {

                                // Toast.makeText(getActivity(), "Something went wrong", Toast.LENGTH_LONG).show();
                                Snackbar.make(otpLayout, "Student record not found.", Snackbar.LENGTH_LONG)
                                        .setAction("Action", null).show();
                                utilityClassObj.stopLoader();
                            }


                        } else {

                            // Toast.makeText(getActivity(), "Something went wrong", Toast.LENGTH_LONG).show();
                            Snackbar.make(otpLayout, "Student record not found.", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                            utilityClassObj.stopLoader();
                        }

                    } else if (modelStudentDetail.getStatus() == 0) {

                        // Toast.makeText(getActivity(), "Something went wrong", Toast.LENGTH_LONG).show();
                        Snackbar.make(otpLayout, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();

                    }
                    utilityClassObj.stopLoader();

                } catch (Exception e) {

                    Snackbar.make(otpLayout, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                         /*   Toast.makeText(getActivity(),
                                    "Error1: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();*/

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
                200000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        // Adding request to request queue

        MyApplication.getInstance().addToRequestQueue(stringRequest);
    }

    private void handleVolleyError(VolleyError error) {
        //if any error occurs in the network operations, show the TextView that contains the error message
        String message = null;
        if (error instanceof NoConnectionError) {
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

    @Override
    protected void onResume() {
        super.onResume();
       /* int myNum = 0;
        try {
            myNum = Integer.parseInt(edtOTP.getText().toString());
        } catch(NumberFormatException nfe) {
            System.out.println("Could not parse " + nfe);
        }

        if(myNum>6){
            sign_in_button.setBackgroundColor(Color.BLACK);
            sign_in_button.setEnabled(true);
        }*/
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

                                    if (PARENT_MENU_CODE.equalsIgnoreCase(null) || PARENT_MENU_CODE.equalsIgnoreCase("") || PARENT_MENU_CODE.isEmpty()) {
                                        mainMenuArray.add(MENU_CODE);
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




                                pref.setSubMenuArray(subMenuGroup);

                                Intent intent = new Intent(OTPActivity.this, VerficationActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                                finish();
                                utilityClassObj.stopLoader();

                            } else {

                                //  Toast.makeText(SmsActivity.this, "Menu Rights not alloted", Toast.LENGTH_LONG).show();
                                Snackbar snackbar = Snackbar
                                        .make(otpLayout, "Menu Rights not alloted", Snackbar.LENGTH_LONG);

                                snackbar.show();
                                utilityClassObj.stopLoader();

                            }


                        } else if (menuModel.getStatus() == 0) {

                            // Toast.makeText(SmsActivity.this, "Something went wrong", Toast.LENGTH_LONG).show();
                            Snackbar snackbar = Snackbar
                                    .make(otpLayout, "Server not responding.Please try later.", Snackbar.LENGTH_LONG);

                            snackbar.show();
                            utilityClassObj.stopLoader();


                        }

                    }else {

                        //  Toast.makeText(SmsActivity.this, "Please try again", Toast.LENGTH_LONG).show();
                        Snackbar snackbar = Snackbar
                                .make(otpLayout, "Server not responding.Please try later.", Snackbar.LENGTH_LONG);

                        snackbar.show();
                        utilityClassObj.stopLoader();

                    }

                } catch (JSONException e) {
                    Log.d(TAG, "Error: " + e.getMessage());
                    Snackbar snackbar = Snackbar
                            .make(otpLayout, "Server not responding.Please try later.", Snackbar.LENGTH_LONG);
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

    /**
     * Resending the OTP to server and activating the user
     */
    private void fun_resend_otp(final String userId, final String mobileNo, final String resendOtpURL, final String CENTER_CODE) {

       // otp_progress.setVisibility(View.VISIBLE);
        utilityClassObj.startLoader(this,R.drawable.image_for_rotation);
        String url = pref.getURL() + URLEndPoints.RESEND_OTP_URL;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, response.toString());
                //{"status":1,"OTP":"723826","STATUS":"TRUE","MESSAGE":"Done"}
                try {
                    JSONObject responseObj = new JSONObject(response);
                    int status = responseObj.getInt("status");
                    if (status == 1) {
                        utilityClassObj.stopLoader();
                        otp = responseObj.getString("OTP");
                        String statusDb = responseObj.getString("STATUS");
                        String message = responseObj.getString("MESSAGE");

                        if (statusDb.equalsIgnoreCase("TRUE")) {
                            Snackbar snackbar = Snackbar
                                    .make(otpLayout, "Request send please wait for OTP.", Snackbar.LENGTH_LONG);
                            snackbar.show();
                        } else if (statusDb.equalsIgnoreCase("FALSE")) {
                            Snackbar snackbar = Snackbar
                                    .make(otpLayout, message, Snackbar.LENGTH_LONG);
                            snackbar.show();
                        }
                    } else {
                        Snackbar snackbar = Snackbar
                                .make(otpLayout, "Server not responding.Please try later.", Snackbar.LENGTH_LONG);
                        snackbar.show();
                        utilityClassObj.stopLoader();
                    }
                } catch (JSONException e) {
                    Log.d(TAG, "Error: " + e.getMessage());
                    Snackbar snackbar = Snackbar
                            .make(otpLayout, "Server not responding.Please try later.", Snackbar.LENGTH_LONG);
                    snackbar.show();
                    utilityClassObj.stopLoader();
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
                params.put("empid", userId);
                params.put("mobileno", mobileNo);
                params.put("SMSAPI", resendOtpURL);
                params.put("CENTER_CODE", CENTER_CODE);
                Log.e(TAG, "Posting params: " + params.toString());
                return params;
            }
        };

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                100000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        // Adding request to request queue

        MyApplication.getInstance().addToRequestQueue(stringRequest);


    }





    @Override
    public void onBackPressed() {
        finish();
    }

}
