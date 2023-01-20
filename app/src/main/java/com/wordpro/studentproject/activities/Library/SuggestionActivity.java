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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
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
import com.wordpro.studentproject.R;
import com.wordpro.studentproject.activities.NavigationActivity;
import com.wordpro.studentproject.app.MyApplication;
import com.wordpro.studentproject.webConfig.URLEndPoints;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.wordpro.studentproject.activities.NavigationActivity.BASE_URL;
import static com.wordpro.studentproject.activities.NavigationActivity.studCenterCode;
import static com.wordpro.studentproject.activities.NavigationActivity.student_id;
import static com.wordpro.studentproject.network.Network.isNetworkAvailable;

public class SuggestionActivity extends AppCompatActivity {

    public static String TAG = SuggestionActivity.class.getSimpleName();
    Spinner spinnerBookType;
    String selectedChoiceBookType;
    Button btnSubmit;
    EditText edtBkTitle, edtAuthorNm, edtPublication, edtLanguage, edtPrice, edtDescrptn;
    ProgressDialog mProgressDialog;
    RelativeLayout relvtLyt;
    TextView txthead,txtMsg,txtBookType,txtTitle,txtAuthor,txtPublication,txtLanguage,txtPrice,txtDescription;
    String bookTitle, authorName, publicationName, language, price, descrption;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggestion);

        Typeface typefaceHeading = Typeface.createFromAsset(getAssets(), "font/Poppins-BoldItalic.otf");
        Typeface typefaceContent = Typeface.createFromAsset(getAssets(), "font/Poppins-Medium.otf");

        ArrayList<String> bookType = new ArrayList<>();
        bookType.add("HAND BOOK");
        bookType.add("REFERENCE BOOK");
        bookType.add("TEXT BOOK");
        bookType.add("TECHNICAL ARTICLE");
        bookType.add("PERIODICALS");
        bookType.add("JOURNALS");
        bookType.add("NOVELS");

        mProgressDialog = new ProgressDialog(SuggestionActivity.this);


        txthead=(TextView)findViewById(R.id.txthead);
        txthead.setTypeface(typefaceContent);
        //txtMsg=(TextView)findViewById(R.id.txtMsg);
      //  txtMsg.setTypeface(typefaceContent);
        txtBookType=(TextView)findViewById(R.id.txtBookType);
        txtBookType.setTypeface(typefaceContent);
        txtTitle=(TextView)findViewById(R.id.txtTitle);
        txtTitle.setTypeface(typefaceContent);
        txtAuthor=(TextView)findViewById(R.id.txtAuthor);
        txtAuthor.setTypeface(typefaceContent);
        txtPublication=(TextView)findViewById(R.id.txtPublication);
        txtPublication.setTypeface(typefaceContent);
        txtLanguage=(TextView)findViewById(R.id.txtLanguage);
        txtLanguage.setTypeface(typefaceContent);
        txtPrice=(TextView)findViewById(R.id.txtPrice);
        txtPrice.setTypeface(typefaceContent);
        txtDescription=(TextView)findViewById(R.id.txtDescription);
        txtDescription.setTypeface(typefaceContent);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        btnSubmit.setTypeface(typefaceHeading);
        edtBkTitle = (EditText) findViewById(R.id.edtBkTitle);
        edtBkTitle.setTypeface(typefaceContent);
        edtAuthorNm = (EditText) findViewById(R.id.edtAuthorNm);
        edtAuthorNm.setTypeface(typefaceContent);
        edtPublication = (EditText) findViewById(R.id.edtPublication);
        edtPublication.setTypeface(typefaceContent);
        edtLanguage = (EditText) findViewById(R.id.edtLanguage);
        edtLanguage.setTypeface(typefaceContent);
        edtPrice = (EditText) findViewById(R.id.edtPrice);
        edtPrice.setTypeface(typefaceContent);
        edtDescrptn = (EditText) findViewById(R.id.edtDescrptn);
        edtDescrptn.setTypeface(typefaceContent);
        relvtLyt = (RelativeLayout) findViewById(R.id.relvtLyt);

        spinnerBookType = (Spinner) findViewById(R.id.spinnerBookType);
        ArrayAdapter acadSesnAdpter = new ArrayAdapter(SuggestionActivity.this, R.layout.multiline_spinner_item, bookType);
        acadSesnAdpter.setDropDownViewResource(R.layout.textlayout);
        spinnerBookType.setAdapter(acadSesnAdpter);

        spinnerBookType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

                selectedChoiceBookType = adapterView.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isNetworkAvailable(SuggestionActivity.this)) {
                    // Create an Alert Dialog
                    AlertDialog.Builder builder = new AlertDialog.Builder(SuggestionActivity.this);
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

                        mProgressDialog.setMessage("Plaese wait loading");
                        mProgressDialog.setCanceledOnTouchOutside(false);
                        mProgressDialog.show();

                        String url = BASE_URL + URLEndPoints.SAVE_BOOK_SUGGESTION_URL;
                        Log.d(TAG, "URL : " + url);

                        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                Log.d(TAG, response.toString());
                                try {

                                    //{"status":1,"STATUS":"TRUE","MSG":"Book Suggestion saved successfully"}
                                    JSONObject responseObj = new JSONObject(response);
                                    int status = responseObj.getInt("status");
                                    String STATUS = responseObj.getString("STATUS");
                                    String MSG = responseObj.getString("MSG");

                                    if (status == 1) {

                                        mProgressDialog.dismiss();
                                        if (STATUS.equalsIgnoreCase("TRUE")) {

                                            Snackbar.make(relvtLyt, MSG, Snackbar.LENGTH_LONG)
                                                    .setAction("Action", null).show();

                                        } else {

                                            Snackbar.make(relvtLyt, MSG, Snackbar.LENGTH_LONG)
                                                    .setAction("Action", null).show();
                                        }

                                    } else {
                                        mProgressDialog.dismiss();
                                        Snackbar.make(relvtLyt, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
                                                .setAction("Action", null).show();
                                    }


                                } catch (Exception e) {
                                    Snackbar.make(relvtLyt, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
                                            .setAction("Action", null).show();
                                    Log.d(TAG, "Error : " + e.getMessage());

                                    mProgressDialog.dismiss();
                                }

                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                                mProgressDialog.dismiss();
                            }
                        }) {
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                                Map<String, String> params = new HashMap<String, String>();

                                params.put("PI_LIB_BOOK_SUGG_Id", "0");
                                params.put("PI_BOOK_TYPE", selectedChoiceBookType);
                                params.put("PI_BOOK_TITLE", "Let Us C");
                                params.put("PI_AUTHOR_NAME", "Yashavant Kanetkar ");
                                params.put("PI_PUBLICATION", "BPB Publication");
                                params.put("PI_LANGUAGE", "English");
                                params.put("PI_PRICE", "222");
                                params.put("PI_DESCRIPTION", "Description: Best way to learn any programming language is to create good programs in it. C is not exception to this rule. Once you decide to write any program you would find that there are always at least two ways to write it.");
                                params.put("PI_USER_ID", student_id);
                                params.put("PI_USER_TYPE", "S");
                                params.put("pi_centre_code", studCenterCode);
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

                }

            }
        });


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
    private boolean isValid() {


        bookTitle = edtBkTitle.getText().toString();
        authorName = edtAuthorNm.getText().toString();
        publicationName = edtPublication.getText().toString();
        language = edtLanguage.getText().toString();
        price = edtPrice.getText().toString();
        descrption = edtDescrptn.toString();

        if (bookTitle.length() == 0) {
            edtBkTitle.requestFocus();
            edtBkTitle.setError("Please enter Book Title");
            return false;
        } else if (authorName.length() == 0) {
            edtAuthorNm.requestFocus();
            edtAuthorNm.setError("Please enter Author");
            return false;
        } else if (publicationName.length() == 0) {
            edtPublication.requestFocus();
            edtPublication.setError("Please enter Publication");
            return false;
        } else if (language.length() == 0) {
            edtLanguage.requestFocus();
            edtLanguage.setError("Please enter Language");
            return false;
        } else if (price.length() == 0) {
            edtPrice.requestFocus();
            edtPrice.setError("Please enter Price");
            return false;
        } else if (descrption.length() == 0) {
            edtPrice.requestFocus();
            edtPrice.setError("Please enter descrption");
            return false;
        } else if (descrption.length() > 250) {
            edtPrice.requestFocus();
            edtPrice.setError("Please enter descrption in 250 characters");
            return false;
        } else
            return true;
    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(SuggestionActivity.this, NavigationActivity.class);
        intent.putExtra("activity", "SuggestionActivity");
        startActivity(intent);
        finish();
    }
}
