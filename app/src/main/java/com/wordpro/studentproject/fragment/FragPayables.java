package com.wordpro.studentproject.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.wordpro.studentproject.activities.Payables.AcadFeesActivity;
import com.wordpro.studentproject.activities.Payables.CompleteFeesActivity;
import com.wordpro.studentproject.activities.Payables.NonAcademicFeeActivity;
import com.wordpro.studentproject.adapter.AcadFeesAdpter;
import com.wordpro.studentproject.adapter.NonAcadFeeAdpter;
import com.wordpro.studentproject.app.MyApplication;
import com.wordpro.studentproject.helper.PrefManager;
import com.wordpro.studentproject.model.AcademicFeeModel;
import com.wordpro.studentproject.model.NonAcadFeesModel;
import com.wordpro.studentproject.webConfig.URLEndPoints;

import org.json.JSONObject;

import java.util.ArrayList;

import static com.wordpro.studentproject.activities.NavigationActivity.fragPayables;
import static com.wordpro.studentproject.activities.NavigationActivity.studSubMenu;
import static com.wordpro.studentproject.activities.NavigationActivity.student_id;
import static com.wordpro.studentproject.network.Network.isNetworkAvailable;

/**
 * Created by wccs1980 on 12-04-2018.
 */

public class FragPayables extends DialogFragment {

    private static String TAG = FragPayables.class.getSimpleName();
    LinearLayout lytAcadFee, lytNonAcadFee, lytCmpltFee, lytFees;
    ProgressBar progFeesFrgmt;
    TextView txtPayCancel,txtPayables,txt1,txt2,txt3;
    private PrefManager pref;

    //Academic Fees Model
    public static AcademicFeeModel academicFeeModel;
    public static ArrayList<AcademicFeeModel.DataBean> acadFeeArrayList;
    public static ArrayList<AcademicFeeModel.DataBean> academicArraylist;
    public static ArrayList<AcademicFeeModel.DataBean> nonacademicArraylist;

    public static AcadFeesAdpter acadFeesAdpter;
    public static AcadFeesAdpter nonacadAdapter;

    //Non Academic Fees Model
    public static NonAcadFeesModel nonAcadFeesModel;
    public static ArrayList<NonAcadFeesModel.DataBean> nonAcadFeeArrayList;
    public static NonAcadFeeAdpter nonAcadFeeAdpter;

    //Sub Menu Name
    public static final String KEYSTUAPP_FEE_ACAD = "STUAPP_FEE_ACAD";
    public static final String KEYSTUAPP_FEE_NONACAD = "STUAPP_FEE_NONACAD";
    public static final String KEYSTUAPP_FEE_CMPLTFEE = "STUAPP_FEE_CMPLTFEE";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.payables_fragment, container);

        Typeface typefaceHeading = Typeface.createFromAsset(getActivity().getAssets(), "font/Poppins-BoldItalic.otf");
        Typeface typefaceContent = Typeface.createFromAsset(getActivity().getAssets(), "font/Poppins-Medium.otf");

        txtPayables=(TextView)view.findViewById(R.id.txtPayables);
        txtPayables.setTypeface(typefaceHeading);
        txtPayCancel = (TextView) view.findViewById(R.id.txtPayCancel);
        txtPayCancel.setTypeface(typefaceContent);
        txt1=(TextView)view.findViewById(R.id.txt1);
        txt1.setTypeface(typefaceContent);
        txt2=(TextView)view.findViewById(R.id.txt2);
        txt2.setTypeface(typefaceContent);
        txt3=(TextView)view.findViewById(R.id.txt3);
        txt3.setTypeface(typefaceContent);
        lytAcadFee = (LinearLayout) view.findViewById(R.id.lytAcadFee);
        lytAcadFee.setVisibility(View.GONE);
        lytNonAcadFee = (LinearLayout) view.findViewById(R.id.lytNonAcadFee);
        lytNonAcadFee.setVisibility(View.GONE);
        lytCmpltFee = (LinearLayout) view.findViewById(R.id.lytCmpltFee);
        lytCmpltFee.setVisibility(View.GONE);
        progFeesFrgmt = (ProgressBar) view.findViewById(R.id.progFeesFrgmt);
        lytFees = (LinearLayout) view.findViewById(R.id.lytFees);
        pref = new PrefManager(getActivity());

        if (studSubMenu != null && studSubMenu.size() != 0) {

            for (int i = 0; i < studSubMenu.size(); i++) {

                String value = studSubMenu.get(i);

                if (value.equalsIgnoreCase(KEYSTUAPP_FEE_ACAD)) {

                    lytAcadFee.setVisibility(View.VISIBLE);

                } else if (value.equalsIgnoreCase(KEYSTUAPP_FEE_NONACAD)) {

                    lytNonAcadFee.setVisibility(View.VISIBLE);

                } else if (value.equalsIgnoreCase(KEYSTUAPP_FEE_CMPLTFEE)) {

                    lytCmpltFee.setVisibility(View.VISIBLE);

                }
            }
        }



        txtPayCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragPayables.dismiss();
            }
        });

        lytAcadFee.setOnClickListener(new View.OnClickListener() {
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
                                            Toast.makeText(getActivity(), "Please check your internet", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                    AlertDialog alert = builder.create();
                    alert.show();
                }else {
                    lytAcadFee.setEnabled(false);

                    getFeesData("ACADEMIC");
                }


            }
        });

        lytNonAcadFee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lytNonAcadFee.setEnabled(false);
                getFeesData("NONACADEMIC");

            }
        });

        lytCmpltFee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                lytCmpltFee.setEnabled(false);
                getFeesData("ALL");

            }
        });

        return view;
    }

    private void getFeesData(final String feeType) {

        progFeesFrgmt.setVisibility(View.VISIBLE);

        String url = pref.getURL() + URLEndPoints.GetStudDtls_URL(student_id,feeType);

        Log.d(TAG, "Fees Data :  " + url);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, response.toString());
                try {

                    Gson gson = new Gson();

                    if (feeType.equalsIgnoreCase("ACADEMIC")) {

                       JSONObject jsonObject = new JSONObject(response);
                       int status = jsonObject.getInt("status");
                       if (status == 1){
                           academicFeeModel = gson.fromJson(response, AcademicFeeModel.class);
                           if (academicFeeModel.getStatus() == 1) {
                               acadFeeArrayList = (ArrayList<AcademicFeeModel.DataBean>) academicFeeModel.getData();
                               if (acadFeeArrayList != null) {

                                   acadFeesAdpter = new AcadFeesAdpter(getActivity(), acadFeeArrayList);
                                   Intent intent = new Intent(getActivity(), AcadFeesActivity.class);
                                   startActivity(intent);
                                   fragPayables.dismiss();
                                   getActivity().finish();

                               } else {
                                   Snackbar.make(lytFees, "Record not available.", Snackbar.LENGTH_LONG)
                                           .setAction("Action", null).show();
                               }

                           } else if (academicFeeModel.getStatus() == 0) {
                               Snackbar.make(lytFees, "Record not available.", Snackbar.LENGTH_LONG)
                                       .setAction("Action", null).show();
                           }
                       }else {
                           Snackbar.make(lytFees, "Record not available.", Snackbar.LENGTH_LONG)
                                   .setAction("Action", null).show();
                       }

                    } else if (feeType.equalsIgnoreCase("NONACADEMIC")) {
                        JSONObject jsonObject = new JSONObject(response);
                        int status = jsonObject.getInt("status");
                        if (status == 1){
                            nonAcadFeesModel = gson.fromJson(response, NonAcadFeesModel.class);
                            if (nonAcadFeesModel.getStatus() == 1) {
                                nonAcadFeeArrayList = (ArrayList<NonAcadFeesModel.DataBean>) nonAcadFeesModel.getData();
                                if (nonAcadFeeArrayList != null) {

                                    nonAcadFeeAdpter = new NonAcadFeeAdpter(getActivity(), nonAcadFeeArrayList);
                                    Intent intent = new Intent(getActivity(), NonAcademicFeeActivity.class);
                                    startActivity(intent);
                                    fragPayables.dismiss();
                                    getActivity().finish();

                                } else {
                                    Snackbar.make(lytFees, "Record not available.", Snackbar.LENGTH_LONG)
                                            .setAction("Action", null).show();
                                }

                            } else if (nonAcadFeesModel.getStatus() == 0) {
                                Snackbar.make(lytFees, "Record not available.", Snackbar.LENGTH_LONG)
                                        .setAction("Action", null).show();
                            }
                        }else {
                            Snackbar.make(lytFees, "Record not available.", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        }




                    } else if (feeType.equalsIgnoreCase("ALL")) {
                        JSONObject jsonObject = new JSONObject(response);
                        int status = jsonObject.getInt("status");
                        if (status == 1){
                            academicFeeModel = gson.fromJson(response, AcademicFeeModel.class);
                            if (academicFeeModel.getStatus() == 1) {

                                acadFeeArrayList = (ArrayList<AcademicFeeModel.DataBean>) academicFeeModel.getData();

                                academicArraylist=new ArrayList<AcademicFeeModel.DataBean>();
                                nonacademicArraylist=new ArrayList<AcademicFeeModel.DataBean>();

                                for (int i=0;i<acadFeeArrayList.size();i++){

                                    String feeType=acadFeeArrayList.get(i).getFEE_TYPE_DESC();

                                    if(feeType.equalsIgnoreCase("NON STRUCTURAL FEES")){

                                        academicArraylist.add(acadFeeArrayList.get(i));

                                    }else if (feeType.equalsIgnoreCase("STRUCTURAL FEES")){

                                        nonacademicArraylist.add(acadFeeArrayList.get(i));
                                    }

                                }

                                //nonAcadFeeArrayList = (ArrayList<NonAcadFeesModel.DataBean>) nonAcadFeesModel.getData();

                                if (acadFeeArrayList != null ) {
                                    acadFeesAdpter = new AcadFeesAdpter(getActivity(), academicArraylist);
                                    nonacadAdapter = new AcadFeesAdpter(getActivity(), nonacademicArraylist);
                                    Intent intent = new Intent(getActivity(), CompleteFeesActivity.class);
                                    startActivity(intent);
                                    fragPayables.dismiss();
                                    getActivity().finish();

                                } else {
                                    Snackbar.make(lytFees, "Record not available.", Snackbar.LENGTH_LONG)
                                            .setAction("Action", null).show();
                                }

                            } else if (academicFeeModel.getStatus() == 0) {
                                Snackbar.make(lytFees, "Record not available.", Snackbar.LENGTH_LONG)
                                        .setAction("Action", null).show();
                            }
                        }else {
                            Snackbar.make(lytFees, "Record not available.", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        }

                    }


                    progFeesFrgmt.setVisibility(View.GONE);
                } catch (Exception e) {

                    Snackbar.make(lytFees, "Server not responding.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                    Log.d(TAG, "Error : " + e.getMessage());

                    progFeesFrgmt.setVisibility(View.GONE);

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Error: " + error.getMessage());
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

        Toast.makeText(getActivity(), ""+message, Toast.LENGTH_SHORT).show();
    }
}
