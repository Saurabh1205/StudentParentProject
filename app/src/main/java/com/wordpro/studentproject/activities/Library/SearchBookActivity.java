package com.wordpro.studentproject.activities.Library;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
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
import com.wordpro.studentproject.adapter.SearchedBkAdptr;
import com.wordpro.studentproject.app.MyApplication;
import com.wordpro.studentproject.model.SearchBkModel;
import com.wordpro.studentproject.webConfig.URLEndPoints;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.wordpro.studentproject.activities.NavigationActivity.BASE_URL;
import static com.wordpro.studentproject.activities.NavigationActivity.studCenterCode;
import static com.wordpro.studentproject.network.Network.isNetworkAvailable;

public class SearchBookActivity extends AppCompatActivity {

    String TAG = SearchBookActivity.class.getSimpleName();
    LinearLayout linlytSearch, lytSearchBk;
    EditText edtBookTitle, edtBookAuthor, edtBookPublication, edtSyllabusTpc;
    TextView txtSearchbk,txtSearch;
    ProgressDialog mProgressDialog;
    String PI_BOOK_TITLE, PI_AUTHOR_NAME, PI_PUBLICATION_NAME, PI_TOPIC_NAME;

    //SearchBkModel
    public static SearchBkModel searchBkModel;
    public static ArrayList<SearchBkModel.BookSearchDetailsBean> searchDetailsArrayList;
    public static SearchedBkAdptr searchedBkAdptr;



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

            setContentView(R.layout.activity_search_book);

            Typeface typefaceHeading = Typeface.createFromAsset(getAssets(), "font/Poppins-BoldItalic.otf");
            Typeface typefaceContent = Typeface.createFromAsset(getAssets(), "font/Poppins-Medium.otf");

            Intent intent = getIntent();
            Bundle extras = intent.getExtras();
            PI_BOOK_TITLE = extras.getString("PI_BOOK_TITLE");
            PI_AUTHOR_NAME = extras.getString("PI_AUTHOR_NAME");
            PI_PUBLICATION_NAME = extras.getString("PI_PUBLICATION_NAME");
            PI_TOPIC_NAME = extras.getString("PI_TOPIC_NAME");

           /* txtSearchbk=(TextView)findViewById(R.id.txtSearchbk);
            txtSearchbk.setTypeface(typefaceHeading);*/
            txtSearch=(TextView)findViewById(R.id.txtSearch);
            txtSearch.setTypeface(typefaceContent);
            edtBookTitle = (EditText) findViewById(R.id.edtBookTitle);
            edtBookTitle.setTypeface(typefaceContent);
            edtBookAuthor = (EditText) findViewById(R.id.edtBookAuthor);
            edtBookAuthor.setTypeface(typefaceContent);
            edtBookPublication = (EditText) findViewById(R.id.edtBookPublication);
            edtBookPublication.setTypeface(typefaceContent);
            edtSyllabusTpc = (EditText) findViewById(R.id.edtSyllabusTpc);
            edtSyllabusTpc.setTypeface(typefaceContent);
            mProgressDialog = new ProgressDialog(SearchBookActivity.this);

            if(PI_BOOK_TITLE.equalsIgnoreCase("NA")){
                edtBookTitle.setText("");
            }else{
                edtBookTitle.setText(PI_BOOK_TITLE);
            }

            if(PI_AUTHOR_NAME.equalsIgnoreCase("NA")){
                edtBookAuthor.setText("");
            }else{
                edtBookAuthor.setText(PI_AUTHOR_NAME);
            }

            if (PI_PUBLICATION_NAME.equalsIgnoreCase("NA")){
                edtBookPublication.setText("");
            }else {
                edtBookPublication.setText(PI_PUBLICATION_NAME);
            }

            if (PI_TOPIC_NAME.equalsIgnoreCase("NA")){
                edtSyllabusTpc.setText("");
            }else {
                edtSyllabusTpc.setText(PI_TOPIC_NAME);
            }

            linlytSearch = (LinearLayout) findViewById(R.id.linlytSearch);
            lytSearchBk = (LinearLayout) findViewById(R.id.lytSearchBk);
            linlytSearch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (!isNetworkAvailable(SearchBookActivity.this)) {
                        // Create an Alert Dialog
                        AlertDialog.Builder builder = new AlertDialog.Builder(SearchBookActivity.this);
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
                        if (isValid()) {
                            funSearchBook();
                        }
                    }

                }
            });
        }
    }

    private void funSearchBook() {

        mProgressDialog.setMessage("Please wait loading");
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.show();

        PI_BOOK_TITLE = edtBookTitle.getText().toString();
        if (PI_BOOK_TITLE.length() < 1) {
            PI_BOOK_TITLE = "NA";
        }

        PI_AUTHOR_NAME = edtBookAuthor.getText().toString();
        if (PI_AUTHOR_NAME.length() < 1) {
            PI_AUTHOR_NAME = "NA";
        }

        PI_PUBLICATION_NAME = edtBookPublication.getText().toString();
        if (PI_PUBLICATION_NAME.length() < 1) {
            PI_PUBLICATION_NAME = "NA";
        }

        PI_TOPIC_NAME = edtSyllabusTpc.getText().toString();
        if (PI_TOPIC_NAME.length() < 1) {
            PI_TOPIC_NAME = "NA";
        }

        String url =BASE_URL + URLEndPoints.BOOK_SEARCHING_URL;
        Log.d(TAG,url);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d(TAG, "Response" + response);

                try {

                    Gson gson = new Gson();
                    searchBkModel = gson.fromJson(response, SearchBkModel.class);

                    if (searchBkModel.getStatus() == 1) {
                        mProgressDialog.dismiss();

                        searchDetailsArrayList = (ArrayList<SearchBkModel.BookSearchDetailsBean>) searchBkModel.getBookSearchDetails();

                        if (searchDetailsArrayList.size() != 0 && searchDetailsArrayList != null) {

                            searchedBkAdptr=new SearchedBkAdptr(SearchBookActivity.this,searchDetailsArrayList);
                            Intent intent = new Intent(SearchBookActivity.this, SearchedBookActivity.class);
                            intent.putExtra("PI_BOOK_TITLE", PI_BOOK_TITLE);
                            intent.putExtra("PI_AUTHOR_NAME", PI_AUTHOR_NAME);
                            intent.putExtra("PI_PUBLICATION_NAME", PI_PUBLICATION_NAME);
                            intent.putExtra("PI_TOPIC_NAME", PI_TOPIC_NAME);
                            startActivity(intent);
                            finish();

                        } else {
                            Snackbar.make(lytSearchBk, "Record not available", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        }

                    } else {
                        mProgressDialog.dismiss();
                        Snackbar.make(lytSearchBk, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }

                } catch (Exception e) {
                    Snackbar.make(lytSearchBk, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
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
        }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();

                params.put("PI_CENTRE_CODE",studCenterCode);
                params.put("PI_BOOK_TITLE", PI_BOOK_TITLE);
                params.put("PI_PUBLICATION_NAME", PI_PUBLICATION_NAME);
                params.put("PI_AUTHOR_NAME", PI_AUTHOR_NAME);
                params.put("PI_TOPIC_NAME", PI_TOPIC_NAME);

                Log.e(TAG, "Posting params: " + params.toString());
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

        Toast.makeText(this, ""+message, Toast.LENGTH_SHORT).show();
    }

    public boolean isValid() {

        if (edtBookTitle.getText().toString().length() < 1 && edtBookPublication.getText().toString().length() < 1 && edtBookAuthor.getText().toString().length() < 1
                && edtSyllabusTpc.getText().toString().length() < 1) {

            Snackbar.make(lytSearchBk, "Please enter search Book title name or publication name or author name or syllabus topic name", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            return false;

        } else


            return true;
    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(SearchBookActivity.this, NavigationActivity.class);
        intent.putExtra("activity", "SearchBookActivity");
        startActivity(intent);
        finish();
    }
}
