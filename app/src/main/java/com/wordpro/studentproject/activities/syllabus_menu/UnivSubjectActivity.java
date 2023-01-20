package com.wordpro.studentproject.activities.syllabus_menu;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
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
import com.wordpro.studentproject.adapter.UnivSyllabusExpandAdpter;
import com.wordpro.studentproject.adapter.UniverSubjAdapter;
import com.wordpro.studentproject.app.MyApplication;
import com.wordpro.studentproject.helper.PrefManager;
import com.wordpro.studentproject.model.UnivSyllabusModel;
import com.wordpro.studentproject.webConfig.URLEndPoints;
import com.wordpro.studentproject.webConfig.WebConfig;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

import static com.wordpro.studentproject.activities.NavigationActivity.fragSyllabus;
import static com.wordpro.studentproject.activities.NavigationActivity.studAcadSessName;
import static com.wordpro.studentproject.activities.NavigationActivity.studBranchStandDesc;
import static com.wordpro.studentproject.activities.NavigationActivity.studMainSemName;
import static com.wordpro.studentproject.activities.NavigationActivity.studentName;
import static com.wordpro.studentproject.fragment.FragSyllabus.univSubjArrayList;
import static com.wordpro.studentproject.fragment.FragSyllabus.univerSubjAdapter;
import static com.wordpro.studentproject.network.Network.isNetworkAvailable;

public class UnivSubjectActivity extends Activity {

    private static String TAG = UnivSubjectActivity.class.getSimpleName();

    RecyclerView recyVwUnivSubj;
    //ProgressBar prgBarUnivSubj;
    ProgressDialog mProgressDialog;
    TextView txtStudName;
    TextView txtYear;
    TextView txtSem;
    TextView txtSection,txtSubj;
    private PrefManager pref;
    RelativeLayout lytUnivSubj;
    public static UnivSyllabusModel univSyllabusModel;
    public static ArrayList<UnivSyllabusModel.DataBean> univSyllabusArrayList;
    public static UnivSyllabusExpandAdpter univSyllabusExpandAdpter;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild,listDataWeight,listDataPeriods;
/*
    HashMap<String, List<String>> listDataWeight;
    HashMap<String, List<String>> listDataPeriods;
*/




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
            setContentView(R.layout.activity_univ_subject);

            Typeface typefaceHeading = Typeface.createFromAsset(getAssets(), "font/Poppins-BoldItalic.otf");
            Typeface typefaceContent = Typeface.createFromAsset(getAssets(), "font/Poppins-Medium.otf");

            txtSubj=(TextView)findViewById(R.id.txtSubj);
            txtSubj.setTypeface(typefaceHeading);
            txtStudName=(TextView)findViewById(R.id.txtStudName);
            txtStudName.setTypeface(typefaceContent);
            txtStudName.setText(studentName);
            txtYear=(TextView)findViewById(R.id.txtYear);
            txtYear.setTypeface(typefaceContent);
            txtYear.setText(studAcadSessName);
            txtSem=(TextView)findViewById(R.id.txtSem);
            txtSem.setTypeface(typefaceContent);
            txtSem.setText(studMainSemName);
            txtSection=(TextView)findViewById(R.id.txtSection);
            txtSection.setTypeface(typefaceContent);
            txtSection.setText(studBranchStandDesc);
            mProgressDialog=new ProgressDialog(UnivSubjectActivity.this);
            //prgBarUnivSubj=(ProgressBar)findViewById(R.id.prgBarUnivSubj);
            pref = new PrefManager(getApplicationContext());
            lytUnivSubj=(RelativeLayout)findViewById(R.id.lytUnivSubj);

            recyVwUnivSubj=(RecyclerView) findViewById(R.id.recyVwUnivSubj);
            recyVwUnivSubj.setLayoutManager(new LinearLayoutManager(UnivSubjectActivity.this));
            recyVwUnivSubj.setAdapter(univerSubjAdapter);

            if(univerSubjAdapter!=null){
                univerSubjAdapter.setOnItemClickListener(new UniverSubjAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View itemView, int position) {
                        if (!isNetworkAvailable(UnivSubjectActivity.this)) {
                            // Create an Alert Dialog
                            AlertDialog.Builder builder = new AlertDialog.Builder(UnivSubjectActivity.this);
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
                            String SUBJECT_GROUP_ID= univSubjArrayList.get(position).getSUBJECT_GROUP_ID();
                            String APPLICABLE_NUMBER=univSubjArrayList.get(position).getAPPLICABLE_NUMBER();
                            String SUBJECT_DESCRIPTION=univSubjArrayList.get(position).getSUBJECT_DESCRIPTION();

                            funGetUnivSyllFun(SUBJECT_GROUP_ID,APPLICABLE_NUMBER,SUBJECT_DESCRIPTION);
                        }

                    }
                });
            }
        }
    }

    private void funGetUnivSyllFun(String subject_group_id, String applicable_number, final String SUBJECT_DESCRIPTION) {

        mProgressDialog.setMessage("Please wait loading");
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.show();

        String url= pref.getURL()+ URLEndPoints.getUniversitySyllabus_URL(subject_group_id, applicable_number);
        Log.d(TAG, "University Syllabus : " + url);


        final StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, response.toString());
                try {

                    Gson gson = new Gson();
                    univSyllabusModel=gson.fromJson(response,UnivSyllabusModel.class);
                    if(univSyllabusModel.getStatus()==1){

                        mProgressDialog.dismiss();
                        univSyllabusArrayList=(ArrayList<UnivSyllabusModel.DataBean>)univSyllabusModel.getData();
                        if(univSyllabusArrayList!=null){

                            listDataHeader = new ArrayList<String>();

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
                               /* List<String> weightage=new ArrayList<String>();
                                List<String> periods=new ArrayList<String>();*/
                                for(int i=0;i<univSyllabusArrayList.size();i++){

                                    String SYLLABUS_DESCRIPTION=univSyllabusArrayList.get(i).getSYLLABUS_DESCRIPTION();
                                    if(unitnameTilte.equalsIgnoreCase(SYLLABUS_DESCRIPTION)){

                                        String TOPIC_DESCRIPTION=univSyllabusArrayList.get(i).getTOPIC_DESCRIPTION();
                                        String TOPIC_NAME=univSyllabusArrayList.get(i).getTOPIC_NAME();
                                        topic.add(TOPIC_NAME+"$"+TOPIC_DESCRIPTION );
                                       /* weightage.add(WEIGHTAGE);
                                        periods.add(NO_OF_LECTURES);*/

                                    }
                                }

                                listDataChild.put(unitName,topic);
                               /* listDataWeight.put(unitName,weightage);
                                listDataPeriods.put(unitName,periods);*/
                            }

                            univSyllabusExpandAdpter=new UnivSyllabusExpandAdpter(UnivSubjectActivity.this,listDataHeader,listDataChild);
                            Intent intent=new Intent(UnivSubjectActivity.this,UniversitySyllbusActivity.class);
                            intent.putExtra("SUBJECT_DESCRIPTION",SUBJECT_DESCRIPTION);
                            startActivity(intent);
                            fragSyllabus.dismiss();
                            finish();

                        }else if(univSyllabusArrayList==null){
                            Snackbar.make(lytUnivSubj, "Record not available.", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        }
                    }else if(univSyllabusModel.getStatus()==0){

                        mProgressDialog.hide();
                        Snackbar.make(lytUnivSubj, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }

                }catch (Exception e) {
                    Snackbar.make(lytUnivSubj, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    Log.d(TAG, "Error : " + e.getMessage());
                    mProgressDialog.hide();

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
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
        Toast.makeText(this, ""+message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {

        Intent intent=new Intent(UnivSubjectActivity.this, NavigationActivity.class);
        intent.putExtra("activity","UnivSubjectActivity");
        startActivity(intent);
        finish();

    }
}
