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
import com.wordpro.studentproject.adapter.NewArrivalAdpter;
import com.wordpro.studentproject.adapter.NewArrivalBookDtlAdpter;
import com.wordpro.studentproject.app.MyApplication;
import com.wordpro.studentproject.model.NewArrivalBookModel;
import com.wordpro.studentproject.webConfig.URLEndPoints;

import java.util.ArrayList;
import java.util.regex.Pattern;

import static com.wordpro.studentproject.activities.NavigationActivity.BASE_URL;
import static com.wordpro.studentproject.activities.NavigationActivity.studAcadSessName;
import static com.wordpro.studentproject.activities.NavigationActivity.studBranchStandDesc;
import static com.wordpro.studentproject.activities.NavigationActivity.studCenterCode;
import static com.wordpro.studentproject.activities.NavigationActivity.studMainSemName;
import static com.wordpro.studentproject.activities.NavigationActivity.studentName;
import static com.wordpro.studentproject.fragment.LibraryFragment.fineModel;
import static com.wordpro.studentproject.fragment.LibraryFragment.newArrivalAdpter;
import static com.wordpro.studentproject.fragment.LibraryFragment.newArrvlMnthArraylist;
import static com.wordpro.studentproject.network.Network.isNetworkAvailable;

public class NewArrivalMonthActivity extends AppCompatActivity {

    public String TAG =NewArrivalMonthActivity.class.getSimpleName();

    TextView txtStudName, txtYear, txtSem, txtSection,txtHead;
    RecyclerView recyVwMonth;
    ProgressDialog mProgressDialog;
    RelativeLayout relLyArrival;
    public static NewArrivalBookDtlAdpter newArrivalBookDtlAdpter;

    //NewArrivalBookModel
    public static NewArrivalBookModel newArrivalBookModel;
    public static ArrayList<NewArrivalBookModel.LibBokNewArrDtlsBean> arrivalBookDataList;



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
            setContentView(R.layout.activity_new_arrival_month);

            Typeface typefaceHeading = Typeface.createFromAsset(getAssets(), "font/Poppins-BoldItalic.otf");
            Typeface typefaceContent = Typeface.createFromAsset(getAssets(), "font/Poppins-Medium.otf");

            txtHead=(TextView)findViewById(R.id.txtHead);
            txtHead.setTypeface(typefaceHeading);
            txtStudName = (TextView) findViewById(R.id.txtStudName);
            txtStudName.setTypeface(typefaceContent);
            txtStudName.setText(studentName.toUpperCase());
            txtYear = (TextView) findViewById(R.id.txtYear);
            txtYear.setTypeface(typefaceContent);
            txtYear.setText(studAcadSessName.toUpperCase());
            txtSem = (TextView) findViewById(R.id.txtSem);
            txtSem.setText(studMainSemName.toUpperCase());
            txtSem.setTypeface(typefaceContent);
            txtSection = (TextView) findViewById(R.id.txtSection);
            txtSection.setTypeface(typefaceContent);
            txtSection.setText(studBranchStandDesc.toUpperCase());

            relLyArrival=(RelativeLayout)findViewById(R.id.relLyArrival);
            mProgressDialog=new ProgressDialog(NewArrivalMonthActivity.this);

            recyVwMonth=(RecyclerView)findViewById(R.id.recyVwMonth);
            recyVwMonth.setLayoutManager(new LinearLayoutManager(NewArrivalMonthActivity.this));
            recyVwMonth.setAdapter(newArrivalAdpter);

            newArrivalAdpter.setOnItemClickListener(new NewArrivalAdpter.OnItemClickListener() {
                @Override
                public void onItemClick(View itemView, int position) {
                    if (!isNetworkAvailable(NewArrivalMonthActivity.this)) {
                        // Create an Alert Dialog
                        AlertDialog.Builder builder = new AlertDialog.Builder(NewArrivalMonthActivity.this);
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

                        String FROM_DATE= newArrvlMnthArraylist.get(position).getStart_Day_Month();
                        String[] v1=FROM_DATE.split(Pattern.quote(" "));
                        String[] d1=v1[0].split(Pattern.quote("/"));
                        String date1=d1[1]+"/"+d1[0]+"/"+d1[2];

                        String UPTO_DATE=newArrvlMnthArraylist.get(position).getLast_Day_Month();
                        String[] v2=UPTO_DATE.split(Pattern.quote(" "));
                        String[] d2=v2[0].split(Pattern.quote("/"));
                        String date2=d2[1]+"/"+d2[0]+"/"+d2[2];

                        mProgressDialog.setMessage("Please wait loading.");
                        mProgressDialog.setCanceledOnTouchOutside(false);
                        mProgressDialog.show();

                        String url =BASE_URL+ URLEndPoints.getNewArrivalsDetails_URL(studCenterCode,date1,date2);
                        Log.d(TAG,"URL : "+url);

                        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                Log.d(TAG,"Response : "+response.toString());

                                try {

                                    Gson gson=new Gson();
                                    newArrivalBookModel=gson.fromJson(response,NewArrivalBookModel.class);

                                    if(newArrivalBookModel.getStatus()==1){
                                        mProgressDialog.dismiss();

                                        arrivalBookDataList=(ArrayList<NewArrivalBookModel.LibBokNewArrDtlsBean>)newArrivalBookModel.getLibBokNewArrDtls();
                                        if(arrivalBookDataList.size()!=0 && arrivalBookDataList!=null){

                                            newArrivalBookDtlAdpter=new NewArrivalBookDtlAdpter(NewArrivalMonthActivity.this,arrivalBookDataList);
                                            Intent intent=new Intent(NewArrivalMonthActivity.this,NewArrivalsActivity.class);
                                            startActivity(intent);
                                            finish();

                                        }else{
                                            Snackbar.make(relLyArrival, "Record not available", Snackbar.LENGTH_LONG)
                                                    .setAction("Action", null).show();
                                        }

                                    }else {
                                        mProgressDialog.dismiss();
                                        Snackbar.make(relLyArrival, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
                                                .setAction("Action", null).show();
                                    }



                                }catch (Exception e){
                                    Snackbar.make(relLyArrival, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
                                            .setAction("Action", null).show();

                                    Log.d(TAG, "Error : " + e.getMessage());

                                    mProgressDialog.dismiss();
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
                }
            });

        }
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
        Intent intent = new Intent(NewArrivalMonthActivity.this, NavigationActivity.class);
        intent.putExtra("activity","NewArrivalMonthActivity");
        startActivity(intent);
        finish();
    }
}
