package com.wordpro.studentproject.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
import com.wordpro.studentproject.activities.e_learning_menu.E_LearnAssignActivity;
import com.wordpro.studentproject.activities.e_learning_menu.NotesActivity;
import com.wordpro.studentproject.adapter.AssignmentAdapter;
import com.wordpro.studentproject.app.MyApplication;
import com.wordpro.studentproject.helper.PrefManager;
import com.wordpro.studentproject.model.AssignmentModel;
import com.wordpro.studentproject.model.NotesSemModel;
import com.wordpro.studentproject.webConfig.URLEndPoints;

import java.io.File;
import java.util.ArrayList;

import static com.wordpro.studentproject.activities.NavigationActivity.fragE_learning;
import static com.wordpro.studentproject.activities.NavigationActivity.studBranchStandardId;
import static com.wordpro.studentproject.activities.NavigationActivity.studCenterCode;
import static com.wordpro.studentproject.activities.NavigationActivity.studSemesterType;
import static com.wordpro.studentproject.activities.NavigationActivity.studSessionId;
import static com.wordpro.studentproject.activities.NavigationActivity.studSubMenu;
import static com.wordpro.studentproject.activities.NavigationActivity.student_id;
import static com.wordpro.studentproject.network.Network.isNetworkAvailable;

/**
 * Created by wccs1980 on 16-05-2018.
 */

public class FragE_Learning extends DialogFragment {

    private static String TAG = FragE_Learning.class.getSimpleName();
    LinearLayout lytAssignment;
    LinearLayout lytNotes;
    LinearLayout lytE_Learn;
    ProgressBar progELearnFrgmt;
    TextView txtELearning,txt1,txt2;
    Button btnELearnCancel;
    private PrefManager pref;
    //defining file name and url
    public String fileName = "";
    public String fileURL = "";
    //initialize root directory
    File rootDir = Environment.getExternalStorageDirectory();
    public static NotesSemModel notesSemModel;
    public static ArrayList<NotesSemModel.SemesterDtlsBean> notesSemArrayList;
    ArrayList<String> semstrArray;

    //Sub Menu Name
    public static final String KEYSTUAPP_E_LEARN_ASSIG = "STUAPP_E_LEARN_ASSIG";
    public static final String KEYSTUAPP_E_LEARN_NOTES = "STUAPP_E_LEARN_NOTES";

    public FragE_Learning() {
    }

    //Assignment Model
    public static AssignmentModel assignmentModel;
    public static ArrayList<AssignmentModel.StudAssignDtlsBean> studAssignDtlsArrayList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.e_learning_fragment, container);

        pref = new PrefManager(getActivity());

        Typeface typefaceHeading = Typeface.createFromAsset(getActivity().getAssets(), "font/Poppins-BoldItalic.otf");
        Typeface typefaceContent = Typeface.createFromAsset(getActivity().getAssets(), "font/Poppins-Medium.otf");

        txtELearning=(TextView)view.findViewById(R.id.txtELearning);
        txtELearning.setTypeface(typefaceHeading);
        txt1=(TextView)view.findViewById(R.id.txt1);
        txt1.setTypeface(typefaceContent);
        txt2=(TextView)view.findViewById(R.id.txt2);
        txt2.setTypeface(typefaceContent);
        lytE_Learn = (LinearLayout) view.findViewById(R.id.lytE_Learn);
        lytAssignment = (LinearLayout) view.findViewById(R.id.lytAssignment);
        lytAssignment.setVisibility(View.VISIBLE);
        lytNotes = (LinearLayout) view.findViewById(R.id.lytNotes);
        lytNotes.setVisibility(View.VISIBLE);

        if (studSubMenu != null && studSubMenu.size() != 0) {

            for (int i = 0; i < studSubMenu.size(); i++) {

                String value = studSubMenu.get(i);

                if (value.equalsIgnoreCase(KEYSTUAPP_E_LEARN_ASSIG)) {

                    lytAssignment.setVisibility(View.VISIBLE);

                } else if (value.equalsIgnoreCase(KEYSTUAPP_E_LEARN_NOTES)) {

                    lytNotes.setVisibility(View.VISIBLE);

                }
            }
        }


        lytAssignment.setOnClickListener(new View.OnClickListener() {
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
                    funGetAssgnDtl();

                }

            }
        });

        lytNotes.setOnClickListener(new View.OnClickListener() {
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
                    funNotesDtls();

                }
            }
        });

        progELearnFrgmt = (ProgressBar) view.findViewById(R.id.progELearnFrgmt);
        btnELearnCancel = (Button) view.findViewById(R.id.btnELearnCancel);
        btnELearnCancel.setTypeface(typefaceHeading);
        btnELearnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fragE_learning.dismiss();
            }
        });

        return view;
    }

    private void funNotesDtls() {

        progELearnFrgmt.setVisibility(View.VISIBLE);
        String url = pref.getURL() + URLEndPoints.getNOTES_Details_URL(studSessionId,studCenterCode);

        Log.d(TAG, " URL  :  " + url);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, response.toString());

                try {

                    Gson gson = new Gson();
                    notesSemModel = gson.fromJson(response, NotesSemModel.class);

                    if (notesSemModel.getStatus() == 1) {
                        notesSemArrayList = (ArrayList<NotesSemModel.SemesterDtlsBean>) notesSemModel.getSemester_Dtls();

                        if (notesSemArrayList != null) {
                            semstrArray=new ArrayList<String>();


                            for(int i=0;i<notesSemArrayList.size();i++){

                                String SEMESTER_TYPE_NAME=notesSemArrayList.get(i).getSEMESTER_TYPE_NAME();
                                semstrArray.add(SEMESTER_TYPE_NAME);

                            }

                            Intent intent=new Intent(getActivity(), NotesActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putStringArrayList("semstrArray",semstrArray);
                            intent.putExtras(bundle);
                            startActivity(intent);
                            fragE_learning.dismiss();
                            getActivity().finish();


                        } else {
                            Snackbar.make(lytE_Learn, "Record not available.", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                            progELearnFrgmt.setVisibility(View.GONE);

                        }

                    } else if (assignmentModel.getStatus() == 0) {
                        Snackbar.make(lytE_Learn, "Record not available.", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                        progELearnFrgmt.setVisibility(View.GONE);

                    }

                } catch (Exception e) {

                    Snackbar.make(lytE_Learn, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                    Log.d(TAG, "Error : " + e.getMessage());
                    progELearnFrgmt.setVisibility(View.GONE);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                handleVolleyError(error);
                progELearnFrgmt.setVisibility(View.GONE);
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

    private void funGetAssgnDtl() {

        progELearnFrgmt.setVisibility(View.VISIBLE);
        String url = pref.getURL() + URLEndPoints.GetStudAssignment_URL(student_id, studSessionId,studBranchStandardId, studSemesterType, studCenterCode);
        Log.d(TAG, "Assignment URL  :  " + url);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d(TAG, response.toString());

                try {

                    Gson gson = new Gson();
                    assignmentModel = gson.fromJson(response, AssignmentModel.class);

                    if (assignmentModel.getStatus() == 1) {

                        studAssignDtlsArrayList = (ArrayList<AssignmentModel.StudAssignDtlsBean>) assignmentModel.getStudAssign_Dtls();

                        if (studAssignDtlsArrayList != null) {

                            Intent intent = new Intent(getActivity(), E_LearnAssignActivity.class);
                            startActivity(intent);
                            fragE_learning.dismiss();
                            getActivity().finish();

                        } else {
                            Snackbar.make(lytE_Learn, "Record not available.", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        }

                    } else if (assignmentModel.getStatus() == 0) {
                        Snackbar.make(lytE_Learn, "Record not available.", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }

                    progELearnFrgmt.setVisibility(View.GONE);
                } catch (Exception e) {

                    Snackbar.make(lytE_Learn, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                    Log.d(TAG, "Error : " + e.getMessage());
                    progELearnFrgmt.setVisibility(View.GONE);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                handleVolleyError(error);
                progELearnFrgmt.setVisibility(View.GONE);

            }
        });
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                60000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        // Adding request to request queue
        MyApplication.getInstance().addToRequestQueue(stringRequest);
    }

    //function to verify if directory exists
    public void checkAndCreateDirectory(String dirName) {
        File new_dir = new File(rootDir + dirName);
        if (!new_dir.exists()) {
            new_dir.mkdirs();
        }
    }
}
