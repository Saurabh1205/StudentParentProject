package com.wordpro.studentproject.fragment;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.wordpro.studentproject.activities.Library.SuggestionActivity;
import com.wordpro.studentproject.app.MyApplication;
import com.wordpro.studentproject.helper.PrefManager;
import com.wordpro.studentproject.utils.UtilityClass;
import com.wordpro.studentproject.webConfig.URLEndPoints;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.wordpro.studentproject.activities.NavigationActivity.BASE_URL;
import static com.wordpro.studentproject.activities.NavigationActivity.studCenterCode;
import static com.wordpro.studentproject.activities.NavigationActivity.student_id;
import static com.wordpro.studentproject.network.Network.isNetworkAvailable;

public class New_Suggestion_Fragment extends Fragment {

    private PrefManager pref;
    public static String TAG = New_Suggestion_Fragment.class.getSimpleName();
    private TextView  txt_view;
    Spinner spinnerBookType;
    String selectedChoiceBookType;
    Button btnSubmit;
    EditText edtBkTitle, edtAuthorNm, edtPublication, edtLanguage, edtPrice, edtDescrptn;
    ProgressDialog mProgressDialog;
    RelativeLayout relvtLyt;
    TextView txthead,txtMsg,txtBookType,txtTitle,txtAuthor,txtPublication,txtLanguage,txtPrice,txtDescription;
    String bookTitle, authorName, publicationName, language, price, descrption;
    private UtilityClass utilityClassObj;
    private  AlertDialog.Builder builder;
    public New_Suggestion_Fragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.activity_suggestion, container, false);
        pref = new PrefManager(getActivity());
        utilityClassObj =new UtilityClass(getActivity());
        builder = new AlertDialog.Builder(getActivity());
        String value = getArguments().getString("Suggestion");
       // txt_view =(TextView)view.findViewById(R.id.txt_view);
       // txt_view.setText("Suggestion");
        init(view);
        Log.d(TAG, value);
        return view;

    }
    private void init(View v){






        Typeface type_faceHeading = Typeface.createFromAsset(getActivity().getAssets(), "font/Poppins-Bold.ttf");
        Typeface type_faceContent = Typeface.createFromAsset(getActivity().getAssets(), "font/Poppins-Regular.ttf");


        ArrayList<String> bookType = new ArrayList<>();
        bookType.add("HAND BOOK");
        bookType.add("REFERENCE BOOK");
        bookType.add("TEXT BOOK");
        bookType.add("TECHNICAL ARTICLE");
        bookType.add("PERIODICALS");
        bookType.add("JOURNALS");
        bookType.add("NOVELS");

       // mProgressDialog = new ProgressDialog(SuggestionActivity.this);


        txthead=(TextView)v.findViewById(R.id.txthead);
        txthead.setTypeface(type_faceContent);
      //  txtMsg=(TextView)v.findViewById(R.id.txtMsg);
      //  txtMsg.setTypeface(type_faceContent);
        txtBookType=(TextView)v.findViewById(R.id.txtBookType);
        txtBookType.setTypeface(type_faceContent);
        txtTitle=(TextView)v.findViewById(R.id.txtTitle);
        txtTitle.setTypeface(type_faceContent);
        txtAuthor=(TextView)v.findViewById(R.id.txtAuthor);
        txtAuthor.setTypeface(type_faceContent);
        txtPublication=(TextView)v.findViewById(R.id.txtPublication);
        txtPublication.setTypeface(type_faceContent);
        txtLanguage=(TextView)v.findViewById(R.id.txtLanguage);
        txtLanguage.setTypeface(type_faceContent);
        txtPrice=(TextView)v.findViewById(R.id.txtPrice);
        txtPrice.setTypeface(type_faceContent);
        txtDescription=(TextView)v.findViewById(R.id.txtDescription);
        txtDescription.setTypeface(type_faceContent);
        btnSubmit = (Button)v. findViewById(R.id.btnSubmit);
        btnSubmit.setTypeface(type_faceContent);
        edtBkTitle = (EditText)v. findViewById(R.id.edtBkTitle);
        edtBkTitle.setTypeface(type_faceContent);
        edtAuthorNm = (EditText)v. findViewById(R.id.edtAuthorNm);
        edtAuthorNm.setTypeface(type_faceContent);
        edtPublication = (EditText)v. findViewById(R.id.edtPublication);
        edtPublication.setTypeface(type_faceContent);
        edtLanguage = (EditText) v.findViewById(R.id.edtLanguage);
        edtLanguage.setTypeface(type_faceContent);
        edtPrice = (EditText)v. findViewById(R.id.edtPrice);
        edtPrice.setTypeface(type_faceContent);
        edtDescrptn = (EditText) v.findViewById(R.id.edtDescrptn);
        edtDescrptn.setTypeface(type_faceContent);
        relvtLyt = (RelativeLayout)v. findViewById(R.id.relvtLyt);

        spinnerBookType = (Spinner)v. findViewById(R.id.spinnerBookType);
        ArrayAdapter acadSesnAdpter = new ArrayAdapter(getActivity(), R.layout.multiline_spinner_item, bookType);
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
                                            // Restart the Activity
                                            Intent intent = getActivity().getIntent();
                                            getActivity().finish();
                                            startActivity(intent);
                                        }
                                    });
                    AlertDialog alert = builder.create();
                    alert.show();
                }else {
                    if (isValid()) {

                        utilityClassObj.startLoader(getActivity(),R.drawable.image_for_rotation);

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
                                    utilityClassObj.stopLoader();
                                    if (status == 1) {

                                        if (STATUS.equalsIgnoreCase("TRUE")) {

                                            Snackbar.make(relvtLyt, MSG, Snackbar.LENGTH_LONG)
                                                    .setAction("Action", null).show();
                                            AlertDialog();
                                        } else {

                                            Snackbar.make(relvtLyt, MSG, Snackbar.LENGTH_LONG)
                                                    .setAction("Action", null).show();
                                        }

                                    } else {

                                        Snackbar.make(relvtLyt, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
                                                .setAction("Action", null).show();
                                    }


                                } catch (Exception e) {
                                    utilityClassObj.stopLoader();
                                    Snackbar.make(relvtLyt, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
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

        Toast.makeText(getActivity(), ""+message, Toast.LENGTH_SHORT).show();
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


    private  void AlertDialog(){

        builder.setMessage(R.string.dialog_message) .setTitle(R.string.dialog_title)

        //Setting message manually and performing action on button click
        //builder.setMessage("Do you want to close this application ?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                       getActivity().finish();

                    }
                });

        //Creating dialog box
        AlertDialog alert = builder.create();
        //Setting the title manually
        alert.setTitle("Books Suggestion Status");
        alert.show();
    }



}
