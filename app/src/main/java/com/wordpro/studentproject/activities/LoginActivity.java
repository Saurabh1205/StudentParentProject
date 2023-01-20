package com.wordpro.studentproject.activities;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;

import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
import com.wordpro.studentproject.BuildConfig;
import com.wordpro.studentproject.DateValidation;
import com.wordpro.studentproject.R;
import com.wordpro.studentproject.app.Config;
import com.wordpro.studentproject.app.MyApplication;
import com.wordpro.studentproject.helper.PrefManager;
import com.wordpro.studentproject.helper.ThemeColorStyle;
import com.wordpro.studentproject.utils.UtilityClass;
import com.wordpro.studentproject.webConfig.URLEndPoints;
import com.wordpro.studentproject.webConfig.WebConfig;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static com.wordpro.studentproject.network.Network.isNetworkAvailable;

public class LoginActivity extends Activity {

    private static String TAG = LoginActivity.class.getSimpleName();
    LinearLayout loginLayout;
    ProgressBar login_progress;
    EditText edtInstituteCode;
    EditText edtLoginId;
    EditText edtPassword;
    EditText edtMobile;
    EditText edtPIN1;
    EditText edtPIN2;
    Button email_sign_in_button;
    TextView txt_app_name,txt_register;
    private TextView txt_screen_count,back_press;
    String msg1 = "Entered Mobile No does not match with the Mobile No registered in CAS-ERP";
    String msg2 = "USER Id/ Password Mismatch ERR-ADM05";
    String msg3 = "USER Id/ Password Mismatch ERR-ADM08";
    ArrayList<String> InstituteCode = new ArrayList<>();
    private PrefManager pref;
    boolean flagInstituteCode = false;
    String otp, userId, resendOtpURL, CENTER_CODE;
    boolean button_count=false;
    private UtilityClass utilityClassObj;
    private ImageView appLogo;
    private RadioGroup radioGroup;
    private TextInputLayout  textIC_layout_view,textLD_layout_view,textMOB_layout_view,textPASS_layout_view,textPin1_layout_view,textPin2_layout_view;
    private boolean isLightOn;
    private String ModuleCode="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!isNetworkAvailable(this)) {
            // Create an Alert Dialog
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
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

            setContentView(R.layout.zz_login_screen);

            utilityClassObj =new UtilityClass(this);

            Typeface type_faceHeading = Typeface.createFromAsset(getAssets(), "font/Poppins-Bold.ttf");
            Typeface type_faceContent = Typeface.createFromAsset(getAssets(), "font/Poppins-Regular.ttf");


            loginLayout = (LinearLayout) findViewById(R.id.loginLayout);
            login_progress = (ProgressBar) findViewById(R.id.login_progress);
            edtInstituteCode = (EditText) findViewById(R.id.edtInstituteCode);
            appLogo = (ImageView) findViewById(R.id.appLogo);
            textIC_layout_view = (TextInputLayout) findViewById(R.id.textIC_layout_view);
            textLD_layout_view = (TextInputLayout) findViewById(R.id.textLD_layout_view);
            textMOB_layout_view = (TextInputLayout) findViewById(R.id.textMOB_layout_view);
            textPASS_layout_view = (TextInputLayout) findViewById(R.id.textPASS_layout_view);
            textPin1_layout_view = (TextInputLayout) findViewById(R.id.textPin1_layout_view);
            textPin2_layout_view = (TextInputLayout) findViewById(R.id.textPin2_layout_view);
            txt_app_name =(TextView)findViewById(R.id.txt_app_name);
            txt_register =(TextView)findViewById(R.id.txt_register);
            txt_app_name .setTypeface(type_faceHeading);
            txt_register.setTypeface(type_faceHeading);

            edtInstituteCode.setTypeface(type_faceContent);
            edtLoginId = (EditText) findViewById(R.id.edtLoginId);
            edtLoginId.setTypeface(type_faceContent);
            edtPassword = (EditText) findViewById(R.id.edtPassword);
            edtPassword.setTypeface(type_faceContent);
            edtMobile = (EditText) findViewById(R.id.edtMobile);
            edtMobile.setTypeface(type_faceContent);
            edtPIN1 = (EditText) findViewById(R.id.edtPIN1);
            edtPIN1.setTypeface(type_faceContent);
            edtPIN2 = (EditText) findViewById(R.id.edtPIN2);
            edtPIN2.setTypeface(type_faceContent);
            email_sign_in_button = (Button) findViewById(R.id.email_sign_in_button);
            email_sign_in_button.setTypeface(type_faceContent);
            txt_screen_count =(TextView)findViewById(R.id.txt_screen_count);
            back_press =(TextView)findViewById(R.id.back_press);
            radioGroup=(RadioGroup)findViewById(R.id.radioGroup);
            back_press.setTypeface(type_faceHeading);
            URLEndPoints.ConstanceLoginUser ="Student";
            if(URLEndPoints.ConstanceLoginUser.equalsIgnoreCase("Student")){
                appLogo.setBackgroundResource(R.drawable.new_logo);
            }


            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @SuppressLint("ResourceType")
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    RadioButton rb = (RadioButton) group.findViewById(checkedId);
                    if (null != rb && checkedId > -1) {
                        Toast.makeText(LoginActivity.this, rb.getText(), Toast.LENGTH_SHORT).show();
                        if(rb.getText().toString().equalsIgnoreCase("Student")){
                            //loginLayout.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.color.white));
                            URLEndPoints.ConstanceLoginUser =rb.getText().toString();
                            appLogo.setBackgroundResource(R.drawable.new_logo);
                            txt_app_name.setText(R.string.Student_app_name);
                            email_sign_in_button.setBackgroundResource(R.drawable.btn_round_shap_login_btn);
                        }else {
                            //loginLayout.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.color.primary_green));
                            URLEndPoints.ConstanceLoginUser =rb.getText().toString();
                            appLogo.setBackgroundResource(R.drawable.parapplogo);
                            txt_app_name.setText(R.string.Parent_app_name);
                            email_sign_in_button.setBackgroundResource(R.drawable.btn_round_shap_parentlogin_btn);
                        }
                    }


                }
            });
            /*edtLoginId.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    try {

                        edtLoginId.setFocusable(true);
                        edtLoginId.requestFocus();
                        edtLoginId.setSelection(edtLoginId.getText().length());
                        String s = edtLoginId.getText().toString();
                        String first = String.valueOf(s.charAt(0));
                        if (first.equalsIgnoreCase("P")){
                            loginLayout.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.color.tab_background_unselected));
                        }else {
                            loginLayout.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.color.white));
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }


                    return false;
                }
            });*/

            appLogo.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                   // buttonClick(v);
                   /* if(isLightOn){
                        loginLayout.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.color.tab_background_unselected));
                    } else{
                        loginLayout.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.color.white));
                    }
                    isLightOn = !isLightOn;*/
                }
            });


           /* txt_app_name =(TextView)findViewById(R.id.txt_app_name);
            txt_app_name.setTypeface(type_faceHeading);
            txt_register =(TextView)findViewById(R.id.txt_register);
            txt_register.setTypeface(type_faceHeading);*/
            //add Institute code to the Array
            InstituteCode.add("WORDPRO");            //For Wordpro College (NAGPUR)
            InstituteCode.add("KKWNSK");             //For K K Wagh Education Society
            InstituteCode.add("MITWPU");             //For MIT WPU UNIVERSITYx` ZD `
            InstituteCode.add("MGMNAND");            //For MGM College of Engg (Nanded)
            InstituteCode.add("JNECAUGB");           //For JNEC College of Engg(Aurangbad)
            InstituteCode.add("JDIET");              //For JDIET College (Yavatmal)
            InstituteCode.add("DMIMS");              //For DMIMS College (Nagpur)
            email_sign_in_button.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!isNetworkAvailable(LoginActivity.this)) {
                        // Create an Alert Dialog
                        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
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
                        if (button_count==false){
                            String instituteCode = edtInstituteCode.getText().toString().trim();
                            String loginId = edtLoginId.getText().toString().trim();
                            String password = edtPassword.getText().toString().trim();
                            String mobile = edtMobile.getText().toString().trim();

                            if (instituteCode.length() == 0) {
                                edtInstituteCode.requestFocus();
                                edtInstituteCode.setError("Please enter institute code");
                                //edtInstituteCode.setError("Please enter institute code");
                                Snackbar snackbar = Snackbar.make(loginLayout, "Please enter institute code", Snackbar.LENGTH_LONG);
                                snackbar.show();
                            } else if (loginId.length() == 0) {
                                edtLoginId.requestFocus();

                                //edtLoginId.setError("Please enter login id");
                                Snackbar snackbar = Snackbar.make(loginLayout, "Please enter login id", Snackbar.LENGTH_LONG);
                                snackbar.show();
                            } else if (loginId.isEmpty()) {


                               /* if (URLEndPoints.ConstanceLoginUser.equalsIgnoreCase("Student")){
                                    if(String.valueOf(loginId.charAt(0)).equalsIgnoreCase("S")){
                                        ModuleCode ="STUAPPANRD";
                                    }else {
                                        edtLoginId.requestFocus();
                                        Snackbar snackbar = Snackbar.make(loginLayout, "Please enter valid login id", Snackbar.LENGTH_LONG);
                                        snackbar.show();
                                    }
                                }else {
                                    if (String.valueOf(loginId.charAt(0)).equalsIgnoreCase("P")){
                                        ModuleCode ="PRNTAPPANRD";
                                    }else {
                                        edtLoginId.requestFocus();
                                        Snackbar snackbar = Snackbar.make(loginLayout, "Please enter valid login id", Snackbar.LENGTH_LONG);
                                        snackbar.show();
                                    }
                                }*/
                                //else if (!loginId.isEmpty() && !String.valueOf(loginId.charAt(0)).equalsIgnoreCase("S"))
                                edtLoginId.requestFocus();
                                //edtLoginId.setError("Please enter login id");
                                Snackbar snackbar = Snackbar.make(loginLayout, "Please enter valid login id", Snackbar.LENGTH_LONG);
                                snackbar.show();
                            } else if (password.length() == 0) {
                                edtPassword.requestFocus();
                                // edtPassword.setError("Please enter password");
                                Snackbar snackbar = Snackbar.make(loginLayout, "Please enter password", Snackbar.LENGTH_LONG);
                                snackbar.show();
                            } else if (edtMobile.length() == 0) {
                                edtMobile.requestFocus();
                                //edtMobile.setError("Please enter valid mobile number");
                                Snackbar snackbar = Snackbar.make(loginLayout, "Please enter valid mobile number", Snackbar.LENGTH_LONG);
                                snackbar.show();
                                //Toast.makeText(getApplicationContext(), "Please enter valid mobile number", Toast.LENGTH_SHORT).show();
                            } else if (edtMobile.length() > 10 || edtMobile.length() < 10) {
                                edtMobile.requestFocus();
                                //edtMobile.setError("Please enter valid mobile number");
                                Snackbar snackbar = Snackbar.make(loginLayout, "Please enter valid mobile number", Snackbar.LENGTH_LONG);
                                snackbar.show();
                            } else if (isValidPhoneNumber(mobile)) {


                                if (URLEndPoints.ConstanceLoginUser.equalsIgnoreCase("Student")){
                                    if(String.valueOf(loginId.charAt(0)).equalsIgnoreCase("S")){
                                        ModuleCode ="STUAPPANRD";
                                        textIC_layout_view.setVisibility(View.GONE);
                                        textLD_layout_view.setVisibility(View.GONE);
                                        textMOB_layout_view.setVisibility(View.GONE);
                                        textPASS_layout_view.setVisibility(View.GONE);
                                        textPin1_layout_view.setVisibility(View.VISIBLE);
                                        textPin2_layout_view.setVisibility(View.VISIBLE);

                                        edtInstituteCode.setVisibility(View.GONE);
                                        edtMobile.setVisibility(View.GONE);
                                        edtLoginId.setVisibility(View.GONE);
                                        edtPassword.setVisibility(View.GONE);
                                        edtPIN1.setVisibility(View.VISIBLE);
                                        edtPIN2.setVisibility(View.VISIBLE);
                                        txt_screen_count.setText(" 2");
                                        back_press.setVisibility(View.VISIBLE);
                                        String pin = edtPIN1.getText().toString().trim();
                                        String re_pin = edtPIN2.getText().toString().trim();

                                        if(pin.length() == 0 || pin.length() > 4 || pin.length() < 4) {
                                            edtPIN1.requestFocus();
                                            Snackbar snackbar = Snackbar.make(loginLayout, "Please enter your 4 digit security pin", Snackbar.LENGTH_LONG);
                                            snackbar.show();
                                        } else if (re_pin.length() == 0 || pin.length() > 4 || pin.length() < 4) {
                                            edtPIN2.requestFocus();
                                            Snackbar snackbar = Snackbar.make(loginLayout, "Please enter your 4 digit security pin", Snackbar.LENGTH_LONG);
                                            snackbar.show();
                                        }
                                        else if (pin.equalsIgnoreCase(re_pin)){
                                            validateForm();
                                        }else {
                                            validateForm();
                                        }

                                    }else {
                                        edtLoginId.requestFocus();
                                        Snackbar snackbar = Snackbar.make(loginLayout, "Please enter valid login id", Snackbar.LENGTH_LONG);
                                        snackbar.show();
                                    }
                                }else {
                                    if (String.valueOf(loginId.charAt(0)).equalsIgnoreCase("P")){
                                        ModuleCode ="PRNTAPPANRD";
                                        textIC_layout_view.setVisibility(View.GONE);
                                        textLD_layout_view.setVisibility(View.GONE);
                                        textMOB_layout_view.setVisibility(View.GONE);
                                        textPASS_layout_view.setVisibility(View.GONE);
                                        textPin1_layout_view.setVisibility(View.VISIBLE);
                                        textPin2_layout_view.setVisibility(View.VISIBLE);

                                        edtInstituteCode.setVisibility(View.GONE);
                                        edtMobile.setVisibility(View.GONE);
                                        edtLoginId.setVisibility(View.GONE);
                                        edtPassword.setVisibility(View.GONE);
                                        edtPIN1.setVisibility(View.VISIBLE);
                                        edtPIN2.setVisibility(View.VISIBLE);
                                        txt_screen_count.setText(" 2");
                                        back_press.setVisibility(View.VISIBLE);
                                        String pin = edtPIN1.getText().toString().trim();
                                        String re_pin = edtPIN2.getText().toString().trim();

                                        if(pin.length() == 0 || pin.length() > 4 || pin.length() < 4) {
                                            edtPIN1.requestFocus();
                                            Snackbar snackbar = Snackbar.make(loginLayout, "Please enter your 4 digit security pin", Snackbar.LENGTH_LONG);
                                            snackbar.show();
                                        } else if (re_pin.length() == 0 || pin.length() > 4 || pin.length() < 4) {
                                            edtPIN2.requestFocus();
                                            Snackbar snackbar = Snackbar.make(loginLayout, "Please enter your 4 digit security pin", Snackbar.LENGTH_LONG);
                                            snackbar.show();
                                        }
                                        else if (pin.equalsIgnoreCase(re_pin)){
                                            validateForm();
                                        }else {
                                            validateForm();
                                        }
                                    }else {
                                        edtLoginId.requestFocus();
                                        Snackbar snackbar = Snackbar.make(loginLayout, "Please enter valid login id", Snackbar.LENGTH_LONG);
                                        snackbar.show();
                                    }
                                }




                               /* textPin1_layout_view.setVisibility(View.VISIBLE);
                                textPin2_layout_view.setVisibility(View.VISIBLE);

                                edtInstituteCode.setVisibility(View.GONE);
                                edtMobile.setVisibility(View.GONE);
                                edtLoginId.setVisibility(View.GONE);
                                edtPassword.setVisibility(View.GONE);
                                edtPIN1.setVisibility(View.VISIBLE);
                                edtPIN2.setVisibility(View.VISIBLE);
                                txt_screen_count.setText(" 2");
                                back_press.setVisibility(View.VISIBLE);
                                String pin = edtPIN1.getText().toString().trim();
                                String re_pin = edtPIN2.getText().toString().trim();

                                if(pin.length() == 0 || pin.length() > 4 || pin.length() < 4) {
                                    edtPIN1.requestFocus();
                                    Snackbar snackbar = Snackbar.make(loginLayout, "Please enter your 4 digit security pin", Snackbar.LENGTH_LONG);
                                    snackbar.show();
                                } else if (re_pin.length() == 0 || pin.length() > 4 || pin.length() < 4) {
                                    edtPIN2.requestFocus();
                                    Snackbar snackbar = Snackbar.make(loginLayout, "Please enter your 4 digit security pin", Snackbar.LENGTH_LONG);
                                    snackbar.show();
                                }
                                else if (pin.equalsIgnoreCase(re_pin)){
                                    validateForm();
                                }else {
                                    validateForm();
                                }*/
                            }

                        }

                    }

                }
            });

            back_press.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    textIC_layout_view.setVisibility(View.VISIBLE);
                    textLD_layout_view.setVisibility(View.VISIBLE);
                    textMOB_layout_view.setVisibility(View.VISIBLE);
                    textPASS_layout_view.setVisibility(View.VISIBLE);
                    textPin1_layout_view.setVisibility(View.GONE);
                    textPin2_layout_view.setVisibility(View.GONE);
                    edtInstituteCode.setVisibility(View.VISIBLE);
                    edtMobile.setVisibility(View.VISIBLE);
                    edtLoginId.setVisibility(View.VISIBLE);
                    edtPassword.setVisibility(View.VISIBLE);
                    edtPIN1.setVisibility(View.GONE);
                    edtPIN2.setVisibility(View.GONE);
                    txt_screen_count.setText(" 1");
                    back_press.setVisibility(View.GONE);
                }
            });

            pref = new PrefManager(this);
            // Checking for user session
            // if user is already logged in, take him to main activity
            if (pref.isLoggedIn()) {
                Intent intent = new Intent(LoginActivity.this, VerficationActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        }
    }



    /**
     * Validating user details form
     */
    private void validateForm() {
        String instituteCode = edtInstituteCode.getText().toString().trim();
        String loginId = edtLoginId.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();
        String mobile = edtMobile.getText().toString().trim();
        String pin = edtPIN1.getText().toString().trim();
        String re_pin = edtPIN2.getText().toString().trim();
//        String character = String.valueOf(loginId.charAt(0));

        if (instituteCode.length() == 0) {
            edtInstituteCode.requestFocus();
            edtInstituteCode.setError("Please enter institute code");
            //edtInstituteCode.setError("Please enter institute code");
            Snackbar snackbar = Snackbar.make(loginLayout, "Please enter institute code", Snackbar.LENGTH_LONG);
            snackbar.show();
        } else if (loginId.length() == 0) {
            edtLoginId.requestFocus();

            //edtLoginId.setError("Please enter login id");
            Snackbar snackbar = Snackbar.make(loginLayout, "Please enter login id", Snackbar.LENGTH_LONG);
            snackbar.show();
        } else if (loginId.isEmpty()) {
        /*     else if (!loginId.isEmpty() && !String.valueOf(loginId.charAt(0)).equalsIgnoreCase("S") || !String.valueOf(loginId.charAt(0)).equalsIgnoreCase("P"))
             if(!String.valueOf(loginId.charAt(0)).equalsIgnoreCase("S")){

             }else if (!String.valueOf(loginId.charAt(0)).equalsIgnoreCase("P")){

             }*/
            edtLoginId.requestFocus();
            //edtLoginId.setError("Please enter login id");
            Snackbar snackbar = Snackbar.make(loginLayout, "Please enter valid login id", Snackbar.LENGTH_LONG);
            snackbar.show();
        } else if (password.length() == 0) {
            edtPassword.requestFocus();
            // edtPassword.setError("Please enter password");
            Snackbar snackbar = Snackbar.make(loginLayout, "Please enter password", Snackbar.LENGTH_LONG);
            snackbar.show();
        } else if (edtMobile.length() == 0) {
            edtMobile.requestFocus();
            //edtMobile.setError("Please enter valid mobile number");
            Snackbar snackbar = Snackbar.make(loginLayout, "Please enter valid mobile number", Snackbar.LENGTH_LONG);
            snackbar.show();
            //Toast.makeText(getApplicationContext(), "Please enter valid mobile number", Toast.LENGTH_SHORT).show();
        } else if (edtMobile.length() > 10 || edtMobile.length() < 10) {
            edtMobile.requestFocus();
            //edtMobile.setError("Please enter valid mobile number");
            Snackbar snackbar = Snackbar.make(loginLayout, "Please enter valid mobile number", Snackbar.LENGTH_LONG);
            snackbar.show();
        } else if (isValidPhoneNumber(mobile)) {

            if (pin.length() == 0 || pin.length() > 4 || pin.length() < 4) {
                edtPIN1.requestFocus();
                // edtPIN1.setError("Please enter your 4 digit security pin");
                Snackbar snackbar = Snackbar.make(loginLayout, "Please enter your 4 digit security pin", Snackbar.LENGTH_LONG);
                snackbar.show();
                //Toast.makeText(getApplicationContext(), "Please enter your 4 digit security pin", Toast.LENGTH_SHORT).show();
            } else if (re_pin.length() == 0 || pin.length() > 4 || pin.length() < 4) {
                edtPIN2.requestFocus();
                //edtPIN2.setError("Please re-enter your 4 digit security pin");
                Snackbar snackbar = Snackbar.make(loginLayout, "Please re-enter your 4 digit security pin", Snackbar.LENGTH_LONG);
                snackbar.show();
                //Toast.makeText(getApplicationContext(), "Please Re-enter your 4 digit security pin", Toast.LENGTH_SHORT).show();
            } else if (pin.equalsIgnoreCase(re_pin)) {

                for (int i = 0; i < InstituteCode.size(); i++) {

                    if (instituteCode.equalsIgnoreCase(InstituteCode.get(i))) {

                        Log.d(TAG, "institute code :" + InstituteCode.get(i));
                        String finalInstituteCode = "fail";
                        WebConfig webConfig = new WebConfig();

                        if (instituteCode.equalsIgnoreCase("KKWNSK")) {

                            finalInstituteCode = webConfig.getBaseURL("KKWNSK");

                        } else if (instituteCode.equalsIgnoreCase("MITWPU")) {

                            finalInstituteCode = webConfig.getBaseURL("MITWPU");

                        } else if (instituteCode.equalsIgnoreCase("MGMNAND")) {

                            finalInstituteCode = webConfig.getBaseURL("MGMNAND");

                        } else if (instituteCode.equalsIgnoreCase("JNECAUGB")) {

                            finalInstituteCode = webConfig.getBaseURL("JNECAUGB");

                        } else if (instituteCode.equalsIgnoreCase("WORDPRO")) {

                            finalInstituteCode = webConfig.getBaseURL("WORDPRO");
                        } else if (instituteCode.equalsIgnoreCase("JDIET")) {

                            finalInstituteCode = webConfig.getBaseURL("JDIET");

                        } else if (instituteCode.equalsIgnoreCase("DMIMS")) {

                            finalInstituteCode = webConfig.getBaseURL("DMIMS");
                        } else
                            finalInstituteCode = "fail";

                        if (!finalInstituteCode.equalsIgnoreCase("fail")) {
                            utilityClassObj.startLoader(this,R.drawable.image_for_rotation);
                            pref.setURL(finalInstituteCode);

                            flagInstituteCode = true;

                            pref.setPin(pin);
                            // request for sms
                            if(login_progress!=null){
                                login_progress.setVisibility(View.VISIBLE);
                            }

                            // saving the mobile number in shared preferences
                            pref.setMobileNumber(mobile);

                            // requesting for sms

                            SharedPreferences mPrefs = getSharedPreferences(Config.SHARED_PREF, 0);
                            String firebaseRegisterID = mPrefs.getString("regId", "null");

                            int versionCode = BuildConfig.VERSION_CODE;
                            String versionName = BuildConfig.VERSION_NAME;


                            Log.d(TAG, "User Details : \nversionCode : " + versionCode + "\nversionName : " + versionName + "\nfirebaseRegisterID : " + firebaseRegisterID);

                            // requesting for sms

                           /* if(String.valueOf(loginId.charAt(0)).equalsIgnoreCase("S")){
                                ModuleCode ="STUAPPANRD";
                            }else if (String.valueOf(loginId.charAt(0)).equalsIgnoreCase("P")){
                                ModuleCode ="PRNTAPPANRD";
                            }*/




                              requestForSMS(loginId, password, mobile, instituteCode,pin, firebaseRegisterID, versionName, ModuleCode);

                        } else if (finalInstituteCode.equalsIgnoreCase("fail")) {

                            edtInstituteCode.requestFocus();
                            //edtInstituteCode.setError("Please enter valid institute code");
                            Snackbar snackbar = Snackbar.make(loginLayout, "Please enter valid institute code", Snackbar.LENGTH_LONG);
                            snackbar.show();

                        }
                    }
                }

                if (flagInstituteCode == false) {
                    edtInstituteCode.requestFocus();
                    edtInstituteCode.setError("Please enter valid institute code");
                }
            } else {
                edtPIN2.requestFocus();
                edtPIN2.setError("Security pin mismatch");
            }
        }
    }

    private static boolean isValidPhoneNumber(String mobile) {
        String regEx = "^[0-9]{10}$";
        return mobile.matches(regEx);
    }

    private void requestForSMS(final String loginId, final String password, final String mobile, final String instuteCode, final String pin, String firebaseRegisterID, String versionName, String studappanrd) {
        //Remove this code for date validation
        if (!DateValidation.validateDate()) {
            Toast.makeText(this,"App need to be updated for next session", Toast.LENGTH_LONG).show();
            return;
        }
        String URLCodeBased = pref.getURL() + URLEndPoints.EMPLOYEE_MAIN_LOGIN_URL;
        Log.d(TAG, "URL : " + URLCodeBased);

        StringRequest strReq = new StringRequest(Request.Method.POST,
                URLCodeBased, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, response.toString());
                utilityClassObj.stopLoader();
                try {
                    JSONObject responseObj = new JSONObject(response);
                    int status = responseObj.getInt("status");
                    otp = responseObj.getString("OTP");
                    userId = responseObj.getString("USERID");
                    String statusDb = responseObj.getString("STATUS");
                    String message = responseObj.getString("MESSAGE");
                    resendOtpURL = responseObj.getString("SMSAPI");
                    CENTER_CODE = responseObj.getString("CENTER_CODE");

                    if (status == 1 && statusDb.equalsIgnoreCase("TRUE")) {

                        Intent intent = new Intent(LoginActivity.this, OTPActivity.class);
                        Bundle extras = new Bundle();
                        extras.putString("otp", otp);
                        extras.putString("instituteCode", instuteCode);
                        extras.putString("loginId", loginId);
                        extras.putString("password", password);
                        extras.putString("mobile", mobile);
                        extras.putString("pin", pin);
                        extras.putString("resendOtpURL", resendOtpURL);
                        extras.putString("CENTER_CODE", CENTER_CODE);
                        extras.putString("userId", userId);
                        intent.putExtras(extras);
                        startActivityForResult(intent,100);
                        startActivity(intent);
                        finish();

                        Snackbar snackbar = Snackbar
                                .make(loginLayout, message, Snackbar.LENGTH_LONG);
                        snackbar.show();
                    } else {

                        Snackbar snackbar = Snackbar
                                .make(loginLayout, message, Snackbar.LENGTH_LONG);

                        snackbar.show();
                        utilityClassObj.stopLoader();
                    }


                    // hiding the progress bar
                   /* if(login_progress!=null){
                        login_progress.setVisibility(View.GONE);
                    }*/

                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(),
                            "Error: " + e.getMessage(),
                            Toast.LENGTH_LONG).show();
                    Log.d(TAG, "Error: " + e.getMessage());
                    Snackbar snackbar = Snackbar
                            .make(loginLayout, "Server not responding.Please try later.", Snackbar.LENGTH_LONG);

                    snackbar.show();
                    utilityClassObj.stopLoader();
                   /* if(login_progress!=null){
                        login_progress.setVisibility(View.GONE);
                    }*/
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

                Log.e(TAG, "Error: " + error.getMessage());
                utilityClassObj.stopLoader();
                handleVolleyError(error);

               /* if(login_progress!=null){
                    login_progress.setVisibility(View.GONE);
                }*/

            }
        }) {

            /**
             * Passing user parameters to our server
             *  @return
             */
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("empid", loginId);
                params.put("password", password);
                params.put("mobileno", mobile);
                params.put("Device_Id", firebaseRegisterID);
                params.put("Module_Code", studappanrd);
                params.put("URN_Number", versionName);
                params.put("IN_Module_Code","ANDROID");

                Log.e(TAG, "Posting params: " + params.toString());

                return params;
            }

        };
        strReq.setRetryPolicy(new DefaultRetryPolicy(
                200000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        // Adding request to request queue
        MyApplication.getInstance().addToRequestQueue(strReq);
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

        Snackbar snackbar = Snackbar
                .make(loginLayout, message, Snackbar.LENGTH_LONG);
        snackbar.show();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            String instuteCode = data.getStringExtra("instuteCode");
            String loginId = data.getStringExtra("loginId");
            String password = data.getStringExtra("password");
            String mobile = data.getStringExtra("mobile");
            String pin = data.getStringExtra("pin");
            Log.d(TAG, " " + instuteCode + " " + loginId + " " + password + " " + mobile + " " + pin + " ");

            edtInstituteCode.setText(instuteCode);
            edtLoginId.setText(loginId);
            edtPassword.setText(password);
            edtMobile.setText(mobile);
            edtPIN1.setText(pin);
            edtPIN2.setText(pin);

        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }
    public void buttonClick(View view){
        int red= new Random().nextInt(255);
        int green= new Random().nextInt(255);
        int blue= new Random().nextInt(255);
        ThemeColorStyle.setNewThemeColor(LoginActivity.this, red, green, blue);
    }
}

