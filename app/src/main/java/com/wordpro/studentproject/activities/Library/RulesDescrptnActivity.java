package com.wordpro.studentproject.activities.Library;

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
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.wordpro.studentproject.activities.LibraryActivity;
import com.wordpro.studentproject.activities.LibraryDetailsActivity;
import com.wordpro.studentproject.activities.NavigationActivity;
import com.wordpro.studentproject.adapter.RulesAdpter;
import com.wordpro.studentproject.adapter.SlabAdpter;
import com.wordpro.studentproject.app.MyApplication;
import com.wordpro.studentproject.model.SlabModel;
import com.wordpro.studentproject.utils.UtilityClass;
import com.wordpro.studentproject.webConfig.URLEndPoints;

import java.util.ArrayList;

import static com.wordpro.studentproject.activities.NavigationActivity.BASE_URL;
import static com.wordpro.studentproject.activities.NavigationActivity.studAcadSessName;
import static com.wordpro.studentproject.activities.NavigationActivity.studBranchStandDesc;
import static com.wordpro.studentproject.activities.NavigationActivity.studCenterCode;
import static com.wordpro.studentproject.activities.NavigationActivity.studMainSemName;
import static com.wordpro.studentproject.activities.NavigationActivity.studentName;
import static com.wordpro.studentproject.fragment.LibraryFragment.rulesAdpter;
import static com.wordpro.studentproject.network.Network.isNetworkAvailable;

public class RulesDescrptnActivity extends AppCompatActivity {

    public String TAG=RulesDescrptnActivity.class.getSimpleName();

    TextView txtStudName, txtYear, txtSem, txtSection,txt_toolbarName;
    private ImageView back_press;
    ProgressDialog mProgressDialog;
    RelativeLayout rulesLyt;
    RecyclerView recyVwRules,recyVwSlab;
    LinearLayout linearLytSlab;
    private  UtilityClass utilityClassObj;
    public static SlabModel slabModel;
    public static ArrayList<SlabModel.LateChgDtlsBean> slabArrayList;
    public static SlabAdpter slabAdpter;

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
            setContentView(R.layout.activity_rules_descrptn);

            Typeface typefaceHeading = Typeface.createFromAsset(getAssets(), "font/Poppins-BoldItalic.otf");
            Typeface typefaceContent = Typeface.createFromAsset(getAssets(), "font/Poppins-Medium.otf");
            utilityClassObj =new UtilityClass(getApplicationContext());
            txt_toolbarName=(TextView)findViewById(R.id.txt_toolbarName);
            txt_toolbarName.setTypeface(typefaceHeading);
            back_press =(ImageView)findViewById(R.id.back_press);
            txtStudName = (TextView) findViewById(R.id.txtStudName);
            txtStudName.setTypeface(typefaceContent);
            txtStudName.setText(studentName.toUpperCase());
            txtYear = (TextView) findViewById(R.id.txtYear);
            txtYear.setTypeface(typefaceContent);
            txtYear.setText(studAcadSessName.toUpperCase());
            txtSem = (TextView) findViewById(R.id.txtSem);
            txtSem.setTypeface(typefaceContent);
            txtSem.setText(studMainSemName.toUpperCase());
            txtSection = (TextView) findViewById(R.id.txtSection);
            txtSection.setTypeface(typefaceContent);
            txtSection.setText(studBranchStandDesc.toUpperCase());
            mProgressDialog=new ProgressDialog(RulesDescrptnActivity.this);
            linearLytSlab=(LinearLayout)findViewById(R.id.linearLytSlab);

            rulesLyt=(RelativeLayout)findViewById(R.id.rulesLyt);
            recyVwRules=(RecyclerView)findViewById(R.id.recyVwRules);
            try {


                rulesAdpter = new RulesAdpter(getApplicationContext(), URLEndPoints.ConstanceData);
                recyVwRules.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                recyVwRules.setAdapter(rulesAdpter);

            }catch (Exception e){
                e.printStackTrace();
            }


            recyVwSlab=(RecyclerView)findViewById(R.id.recyVwSlab);
            recyVwSlab.setLayoutManager(new LinearLayoutManager(RulesDescrptnActivity.this));
            funSlabData();


            back_press.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent =new Intent(getApplicationContext(), LibraryActivity.class);
                    startActivity(intent);
                    finish();
                }
            });

        }
    }

    private void funSlabData() {
        utilityClassObj.startLoader(getApplicationContext(),R.drawable.image_for_rotation);

        String url = BASE_URL + URLEndPoints.GetLateChargeDtls_URL(studCenterCode);
        Log.d(TAG, "URL : " + url);

        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d(TAG,"RESPONSE : "+response);
                try {

                    Gson gson=new Gson();
                    slabModel=gson.fromJson(response,SlabModel.class);
                    utilityClassObj.stopLoader();
                    if(slabModel.getStatus()==1){
                        mProgressDialog.dismiss();

                        slabArrayList=(ArrayList<SlabModel.LateChgDtlsBean>)slabModel.getLateChgDtls();
                        if(slabArrayList.size()!=0 && slabArrayList!=null){

                            linearLytSlab.setVisibility(View.VISIBLE);
                            slabAdpter=new SlabAdpter(RulesDescrptnActivity.this,slabArrayList);
                            recyVwSlab.setAdapter(slabAdpter);

                        }else{

                            linearLytSlab.setVisibility(View.GONE);
                            Snackbar.make(rulesLyt, "Fine Slab Record not available.", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        }

                    }else if(slabModel.getStatus()==0){
                        Snackbar.make(rulesLyt, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }


                }catch (Exception e) {
                    utilityClassObj.stopLoader();
                    Log.d(TAG,"Error : "+e.getMessage());
                    Snackbar.make(rulesLyt, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                utilityClassObj.stopLoader();
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

        Intent intent = new Intent(RulesDescrptnActivity.this, LibraryActivity.class);
        intent.putExtra("activity","RulesDescrptnActivity");
        startActivity(intent);
        finish();
    }

}
