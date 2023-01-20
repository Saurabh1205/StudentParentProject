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
import com.wordpro.studentproject.adapter.IssueBookAdpter;
import com.wordpro.studentproject.app.MyApplication;
import com.wordpro.studentproject.model.IssuedBookModel;
import com.wordpro.studentproject.utils.UtilityClass;
import com.wordpro.studentproject.webConfig.URLEndPoints;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static com.wordpro.studentproject.activities.NavigationActivity.BASE_URL;
import static com.wordpro.studentproject.activities.NavigationActivity.studAcadSessName;
import static com.wordpro.studentproject.activities.NavigationActivity.studBranchStandDesc;
import static com.wordpro.studentproject.activities.NavigationActivity.studMainSemName;
import static com.wordpro.studentproject.activities.NavigationActivity.studentName;
import static com.wordpro.studentproject.activities.NavigationActivity.student_id;
import static com.wordpro.studentproject.network.Network.isNetworkAvailable;

public class IssueBookActivity extends AppCompatActivity {

    String TAG=IssueBookActivity.class.getSimpleName();
    String fromDate,uptoDate;
    ProgressDialog mProgressDialog;
    TextView txtStudName, txtYear, txtSem, txtSection,txt_toolbarName;
    TextView txtthumbnail,txtDetailVw,txtValidity,txtOverdue,txtReturned;
    RelativeLayout relLytIssueBooks;
    private ImageView back_press;
    private UtilityClass utilityClassObj;
    //IssuedBookModel
    public static IssuedBookModel issuedBookModel;
    public static ArrayList<IssuedBookModel.CirculationRuleDtlsBean> issuedBookArraylist;
    public static IssueBookAdpter issueBookAdpter;
    RecyclerView recyVwIssueBook;


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
            setContentView(R.layout.activity_issue_book);

            Intent intent = getIntent();
            Bundle extras = intent.getExtras();
            fromDate = extras.getString("fromDate");
            uptoDate = extras.getString("uptoDate");
            utilityClassObj =new UtilityClass(getApplicationContext());
            relLytIssueBooks=(RelativeLayout)findViewById(R.id.relLytIssueBooks);
            recyVwIssueBook=(RecyclerView)findViewById(R.id.recyVwIssueBook);
            recyVwIssueBook.setLayoutManager(new LinearLayoutManager(IssueBookActivity.this));
            mProgressDialog=new ProgressDialog(IssueBookActivity.this);

            Typeface typefaceHeading = Typeface.createFromAsset(getAssets(), "font/Poppins-BoldItalic.otf");
            Typeface typefaceContent = Typeface.createFromAsset(getAssets(), "font/Poppins-Medium.otf");

            txt_toolbarName=(TextView)findViewById(R.id.txt_toolbarName);
            back_press= (ImageView)  findViewById(R.id.back_press);
            txt_toolbarName.setTypeface(typefaceHeading);
            txtValidity=(TextView)findViewById(R.id.txtValidity);
            txtValidity.setTypeface(typefaceContent);
            txtOverdue=(TextView)findViewById(R.id.txtOverdue);
            txtOverdue.setTypeface(typefaceContent);
            txtReturned=(TextView)findViewById(R.id.txtReturned);
            txtReturned.setTypeface(typefaceContent);
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

            txtthumbnail=(TextView)findViewById(R.id.txtthumbnail);
            txtthumbnail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String type="THUMBNAIL_VIEW";
                    issueBookAdpter=new IssueBookAdpter(IssueBookActivity.this,issuedBookArraylist,type);
                    recyVwIssueBook.setAdapter(issueBookAdpter);

                }
            });

            txtDetailVw=(TextView)findViewById(R.id.txtDetailVw);
            txtDetailVw.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String type="DETAIL_VIEW";
                    issueBookAdpter=new IssueBookAdpter(IssueBookActivity.this,issuedBookArraylist,type);
                    recyVwIssueBook.setAdapter(issueBookAdpter);
                }
            });


            back_press.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent =new Intent(getApplicationContext(), LibraryActivity.class);
                    startActivity(intent);
                    finish();
                }
            });

            funIssuedBook(fromDate,uptoDate);
        }
    }

    private void funIssuedBook(String fromDate, String uptoDate) {

        utilityClassObj.startLoader(getApplicationContext(),R.drawable.image_for_rotation);
        String pattern = "dd-MM-yyyy";
        String currentDt =new SimpleDateFormat(pattern).format(new Date());
        String url = BASE_URL + URLEndPoints.getIssuedBookData_URL(fromDate,currentDt,URLEndPoints.Constance_StudentID);
        Log.d(TAG, url);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d(TAG, response.toString());

                try {

                    Gson gson = new Gson();
                    issuedBookModel = gson.fromJson(response, IssuedBookModel.class);
                    utilityClassObj.stopLoader();
                    if (issuedBookModel.getStatus() == 1) {
                        mProgressDialog.dismiss();

                        issuedBookArraylist = (ArrayList<IssuedBookModel.CirculationRuleDtlsBean>) issuedBookModel.getCirculationRuleDtls();

                        if (issuedBookArraylist.size() != 0 && issuedBookArraylist != null) {

                            String type = "DETAIL_VIEW";
                            issueBookAdpter = new IssueBookAdpter(IssueBookActivity.this, issuedBookArraylist, type);
                            recyVwIssueBook.setAdapter(issueBookAdpter);

                        }

                    } else {
                        Snackbar.make(relLytIssueBooks, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }


                } catch (Exception e) {
                    utilityClassObj.stopLoader();
                    Snackbar.make(relLytIssueBooks, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    Log.d(TAG, "Error : " + e.getMessage());
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
        Intent intent = new Intent(IssueBookActivity.this, LibraryActivity.class);
        intent.putExtra("activity","RulesDescrptnActivity");
        startActivity(intent);
        finish();
    }
}
