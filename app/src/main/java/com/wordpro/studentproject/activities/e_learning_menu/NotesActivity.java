package com.wordpro.studentproject.activities.e_learning_menu;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DownloadManager;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
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
import com.google.gson.Gson;
import com.wordpro.studentproject.R;
import com.wordpro.studentproject.activities.MainActivity;
import com.wordpro.studentproject.activities.NavigationActivity;
import com.wordpro.studentproject.activities.attendance_menu.SubjectWiseAttendActivity;
import com.wordpro.studentproject.adapter.SubjectWiseNotesAdpter;
import com.wordpro.studentproject.app.MyApplication;
import com.wordpro.studentproject.helper.PrefManager;
import com.wordpro.studentproject.model.DownloadURLModel;
import com.wordpro.studentproject.model.NotesDetailModel;
import com.wordpro.studentproject.model.SubjectModel;
import com.wordpro.studentproject.webConfig.URLEndPoints;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import static com.wordpro.studentproject.activities.NavigationActivity.studAcadSessName;
import static com.wordpro.studentproject.activities.NavigationActivity.studBranchStandDesc;
import static com.wordpro.studentproject.activities.NavigationActivity.studBranchStandardId;
import static com.wordpro.studentproject.activities.NavigationActivity.studBranch_Standard_GRP_ID;
import static com.wordpro.studentproject.activities.NavigationActivity.studCenterCode;
import static com.wordpro.studentproject.activities.NavigationActivity.studDepartmentNumber;
import static com.wordpro.studentproject.activities.NavigationActivity.studMainSemName;
import static com.wordpro.studentproject.activities.NavigationActivity.studSemesterType;
import static com.wordpro.studentproject.activities.NavigationActivity.studSessionId;
import static com.wordpro.studentproject.activities.NavigationActivity.studentName;
import static com.wordpro.studentproject.activities.NavigationActivity.student_id;
import static com.wordpro.studentproject.fragment.FragE_Learning.notesSemArrayList;
import static com.wordpro.studentproject.network.Network.isNetworkAvailable;

public class NotesActivity extends Activity {

    private static String TAG = NotesActivity.class.getSimpleName();

    TextView txtStudName, txtYear, txtSem, txtSection,txtNotes;
    Spinner spinnerSemType, spinnerSubject,spinnerNotesType;
    String selectedChoiceSemType, SEMESTER_TYPE_CODE, selectedSemType, selectedSubject, selectedChoiceSubject, SUB_SHORT_DESC;
    int firstPos = 0;
    ProgressBar progNotes;
    PrefManager pref;
    RelativeLayout lytNotes;
    RecyclerView recyVwNotes,recyVwTechnNotes;
    public static int countLike = 0;
    public static int countDisLike = 0;
    String type="0";
    ProgressDialog mProgressDialog;
    LinearLayout lytSem,lytSubj;
    String selectedNotesType;
    Typeface typefaceHeading,typefaceContent;

    //Subject Model
    public static SubjectModel subjectModel;
    public static ArrayList<SubjectModel.DataBean> subjectArrayList;
    ArrayList<String> subjectArray;

    //Notes Detail Model
    public static NotesDetailModel notesDetailModel;
    public static ArrayList<NotesDetailModel.StudNotesDtlsBean> notesDetailArrayList;
    public static ArrayList<NotesDetailModel.StudNotesDtlsBean> subjWiseNoteDtlArrayList;
    public static ArrayList<NotesDetailModel.StudNotesDtlsBean> techArtclNoteDtlArrayList;
    public static SubjectWiseNotesAdpter subjectWiseNotesAdpter;

    //DownloadURLModel
    public static DownloadURLModel downloadURLModel;
    public static ArrayList<DownloadURLModel.DataBean> downloadURLArrayList;

    private DownloadManager downloadManager;
    private long refid;
    private Uri Download_Uri;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    11);
        }

        final Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        ArrayList<String> semstrArray;
        semstrArray = extras.getStringArrayList("semstrArray");
        mProgressDialog=new ProgressDialog(NotesActivity.this);

        downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        registerReceiver(onComplete,
                new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));

        Typeface typefaceHeading = Typeface.createFromAsset(getAssets(), "font/Poppins-BoldItalic.otf");
        Typeface typefaceContent = Typeface.createFromAsset(getAssets(), "font/Poppins-Medium.otf");

        txtNotes=(TextView)findViewById(R.id.txtNotes);
        txtNotes.setTypeface(typefaceHeading);
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
        lytSem=(LinearLayout)findViewById(R.id.lytSem);
        lytSem.setVisibility(View.GONE);
        lytSubj=(LinearLayout)findViewById(R.id.lytSubj);
        lytSubj.setVisibility(View.GONE);

        progNotes = (ProgressBar) findViewById(R.id.progNotes);
        pref = new PrefManager(NotesActivity.this);
        lytNotes = (RelativeLayout) findViewById(R.id.lytNotes);

        spinnerSubject = (Spinner) findViewById(R.id.spinnerSubject);
        recyVwNotes = (RecyclerView) findViewById(R.id.recyVwNotes);
        recyVwNotes.setLayoutManager(new LinearLayoutManager(NotesActivity.this));
        recyVwNotes.setVisibility(View.GONE);

        recyVwTechnNotes = (RecyclerView) findViewById(R.id.recyVwTechnNotes);
        recyVwTechnNotes.setLayoutManager(new LinearLayoutManager(NotesActivity.this));
        recyVwTechnNotes.setVisibility(View.GONE);
        String aa="O";
        String sub ="SOM-cvl";

        funNotesDetails(aa, sub);

        ArrayList<String> notesType=new ArrayList<>();
        notesType.add("--Select--");
        notesType.add("Subject Notes");
        notesType.add("Practical Notes");
        notesType.add("Technical Article");
        spinnerNotesType=(Spinner)findViewById(R.id.spinnerNotesType);
        ArrayAdapter notesTypeAdpter=new ArrayAdapter(NotesActivity.this,R.layout.multiline_spinner_item,notesType);
        notesTypeAdpter.setDropDownViewResource(R.layout.textlayout);
        spinnerNotesType.setAdapter(notesTypeAdpter);
        spinnerNotesType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                if (!isNetworkAvailable(NotesActivity.this)) {
                    // Create an Alert Dialog
                    AlertDialog.Builder builder = new AlertDialog.Builder(NotesActivity.this);
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
                    selectedNotesType=adapterView.getItemAtPosition(position).toString();
                    recyVwNotes.setVisibility(View.GONE);
                    recyVwTechnNotes.setVisibility(View.GONE);

                    if(adapterView.getItemAtPosition(position).toString().equalsIgnoreCase("Technical Article")){
                        recyVwNotes.setVisibility(View.GONE);
                        recyVwTechnNotes.setVisibility(View.VISIBLE);
                        lytSem.setVisibility(View.GONE);
                        lytSubj.setVisibility(View.GONE);
                        type="1";
                        funNotesDetailsTechnical();
                    }else if(adapterView.getItemAtPosition(position).toString().equalsIgnoreCase("Subject Notes") || adapterView.getItemAtPosition(position).toString().equalsIgnoreCase("Practical Notes")){
                        recyVwNotes.setVisibility(View.VISIBLE);
                        recyVwTechnNotes.setVisibility(View.GONE);
                        lytSem.setVisibility(View.VISIBLE);
                        lytSubj.setVisibility(View.VISIBLE);
                        type="2";
                    }else if(adapterView.getItemAtPosition(position).toString().equalsIgnoreCase("--Select--")){

                        lytSem.setVisibility(View.GONE);
                        lytSubj.setVisibility(View.GONE);
                        recyVwNotes.setVisibility(View.GONE);
                        recyVwTechnNotes.setVisibility(View.GONE);
                        Snackbar.make(lytNotes, "Select notes type.", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }

                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        spinnerSemType = (Spinner) findViewById(R.id.spinnerSemType);
        ArrayAdapter semAdapter = new ArrayAdapter(NotesActivity.this, R.layout.multiline_spinner_item, semstrArray);
        semAdapter.setDropDownViewResource(R.layout.textlayout);
        spinnerSemType.setAdapter(semAdapter);
        spinnerSemType.setSelection(firstPos);

        spinnerSemType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                if (!isNetworkAvailable(NotesActivity.this)) {
                    // Create an Alert Dialog
                    AlertDialog.Builder builder = new AlertDialog.Builder(NotesActivity.this);
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
                    selectedChoiceSemType = adapterView.getItemAtPosition(position).toString();
                    SEMESTER_TYPE_CODE = notesSemArrayList.get(position).getSEMESTER_TYPE_CODE();
                    selectedSemType = SEMESTER_TYPE_CODE;

                    if (type.equalsIgnoreCase("2")){
                        funSubjectList(selectedSemType);
                    }
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void funSubjectList(final String semester_type_code) {

        progNotes.setVisibility(View.VISIBLE);
        String url = pref.getURL() + URLEndPoints.getSubjectList_URL(studSessionId, semester_type_code,studBranchStandardId);
        Log.d(TAG, " URL  :  " + url);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, response.toString());

                try {

                    Gson gson = new Gson();
                    subjectModel = gson.fromJson(response, SubjectModel.class);

                    if (subjectModel.getStatus() == 1) {

                        subjectArrayList = (ArrayList<SubjectModel.DataBean>) subjectModel.getData();

                        if (subjectArrayList != null && subjectArrayList.size()!=0) {

                            subjectArray = new ArrayList<String>();
                            subjectArray.add("--Select--");
                            for (int i = 0; i < subjectArrayList.size(); i++) {

                                subjectArray.add(subjectArrayList.get(i).getSUBJECT_DESCRIPTION() + " - " + subjectArrayList.get(i).getAPPLI_TYPE_DESCRIPTION());
                            }

                            ArrayAdapter subjAdapter = new ArrayAdapter(NotesActivity.this, R.layout.multiline_spinner_item, subjectArray);
                            subjAdapter.setDropDownViewResource(R.layout.textlayout);
                            spinnerSubject.setAdapter(subjAdapter);
                            //spinnerSubject.setSelection(firstPos);

                            spinnerSubject.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                                    selectedChoiceSubject = adapterView.getItemAtPosition(position).toString();

                                    if(selectedChoiceSubject.equalsIgnoreCase("--Select--")){
                                        Snackbar.make(lytNotes, "Select Subject.", Snackbar.LENGTH_LONG)
                                                .setAction("Action", null).show();
                                    }else{
                                      /*  SUB_SHORT_DESC = subjectArrayList.get(position-1).getSUBJECT_DESCRIPTION() + " - " + subjectArrayList.get(position-1).getAPPLI_TYPE_DESCRIPTION();
                                        selectedSubject = SUB_SHORT_DESC;
*/                                    if(SUB_SHORT_DESC!=null){
                                            SUB_SHORT_DESC = subjectArrayList.get(position-1).getSUBJECT_DESCRIPTION() + " - " + subjectArrayList.get(position-1).getAPPLI_TYPE_DESCRIPTION();
                                            selectedSubject = SUB_SHORT_DESC;
                                            funNotesDetails(semester_type_code, selectedSubject);
                                        }

                                    }

                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> adapterView) {

                                }
                            });

                            progNotes.setVisibility(View.GONE);

                        } else {
                            Snackbar.make(lytNotes, "Record not available.", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                            progNotes.setVisibility(View.GONE);

                        }
                        progNotes.setVisibility(View.GONE);

                    } else if (subjectModel.getStatus() == 0) {

                        Snackbar.make(lytNotes, "Record not available.", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                        progNotes.setVisibility(View.GONE);

                    }

                } catch (Exception e) {

                    Snackbar.make(lytNotes, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                    Log.d(TAG, "Error : " + e.getMessage());
                    progNotes.setVisibility(View.GONE);
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

               handleVolleyError(error);
                progNotes.setVisibility(View.GONE);
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

    private void funNotesDetails(String semester_type_code, final String selectedSubject) {

        progNotes.setVisibility(View.VISIBLE);

        String url = pref.getURL() + URLEndPoints.getStudNoteDetails_URL(studBranch_Standard_GRP_ID,studDepartmentNumber,semester_type_code,studCenterCode,student_id);
        Log.d(TAG, " URL  :  " + url);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, response.toString());

                try {

                    Gson gson = new Gson();
                    notesDetailModel = gson.fromJson(response, NotesDetailModel.class);

                    if (notesDetailModel.getStatus() == 1) {

                        notesDetailArrayList = (ArrayList<NotesDetailModel.StudNotesDtlsBean>) notesDetailModel.getStudNotes_Dtls();
                        if (notesDetailArrayList != null) {




                           /* subjectWiseNotesAdpter=new SubjectWiseNotesAdpter(NotesActivity.this,notesDetailArrayList);
                            recyVwNotes.setAdapter(subjectWiseNotesAdpter);*/

                            subjWiseNoteDtlArrayList = new ArrayList<NotesDetailModel.StudNotesDtlsBean>();
                            for (int i = 0; i < notesDetailArrayList.size(); i++) {

                                String subjectName = notesDetailArrayList.get(i).getSUBJECT_DESCRIPTION();
                                String NOTES_TYPE_CODE=notesDetailArrayList.get(i).getNOTES_TYPE_CODE();
                                String Notetype="";
                                if(NOTES_TYPE_CODE.equalsIgnoreCase("PRACT_NOTE")){
                                    Notetype="Practical Notes";
                                }else if(NOTES_TYPE_CODE.equalsIgnoreCase("SUBJ_NOTE")){
                                    Notetype="Subject Notes";
                                }
                                if (selectedSubject.equalsIgnoreCase(subjectName) && selectedNotesType.equalsIgnoreCase(Notetype)){

                                    subjWiseNoteDtlArrayList.add(notesDetailArrayList.get(i));
                                }
                            }

                            if (subjWiseNoteDtlArrayList != null && subjWiseNoteDtlArrayList.size()!=0) {

                                subjectWiseNotesAdpter = new SubjectWiseNotesAdpter(NotesActivity.this, subjWiseNoteDtlArrayList, new SubjectWiseNotesAdpter.ButtonClickListner() {
                                    @Override
                                    public void onLikeClick(int position) {
                                        if (!isNetworkAvailable(NotesActivity.this)) {
                                            // Create an Alert Dialog
                                            AlertDialog.Builder builder = new AlertDialog.Builder(NotesActivity.this);
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
                                            NotesDetailModel.StudNotesDtlsBean note = subjWiseNoteDtlArrayList.get(position);
                                            String LIKE_STATUS=note.getLIKE_STATUS();
                                            String LIKE_COUNT=note.getLIKE_COUNT();
                                            String DISLIKE_STATUS=note.getDISLIKE_STATUS();
                                            String DISLIKE_COUNT=note.getDISLIKE_COUNT();
                                            String type = "LIKE";
                                            String ACAD_NOTES_MASTER_ID = note.getACAD_NOTES_MASTER_ID();

                                            if(LIKE_STATUS.equalsIgnoreCase("F") && DISLIKE_STATUS.equalsIgnoreCase("F")){
                                                note.setLIKE_STATUS("T");
                                                note.setLIKE_COUNT(String.valueOf(Integer.parseInt(LIKE_COUNT)+1));
                                                subjectWiseNotesAdpter.notifyDataSetChanged();
                                                funLikeUnlike(ACAD_NOTES_MASTER_ID, type);

                                            }else if(LIKE_STATUS.equalsIgnoreCase("T") && DISLIKE_STATUS.equalsIgnoreCase("F")){
                                                note.setLIKE_STATUS("F");
                                                note.setLIKE_COUNT(String.valueOf(Integer.parseInt(LIKE_COUNT)-1));
                                                subjectWiseNotesAdpter.notifyDataSetChanged();
                                                //funLikeUnlike(ACAD_NOTES_MASTER_ID, type);

                                            }else if(LIKE_STATUS.equalsIgnoreCase("F") && DISLIKE_STATUS.equalsIgnoreCase("T")){
                                                note.setLIKE_STATUS("T");
                                                note.setLIKE_COUNT(String.valueOf(Integer.parseInt(LIKE_COUNT)+1));
                                                note.setDISLIKE_STATUS("F");
                                                note.setDISLIKE_COUNT(String.valueOf(Integer.parseInt(DISLIKE_COUNT)-1));
                                                subjectWiseNotesAdpter.notifyDataSetChanged();
                                            }
                                        }

                                    }

                                    @Override
                                    public void onUnlikeClick(int position) {
                                        if (!isNetworkAvailable(NotesActivity.this)) {
                                            // Create an Alert Dialog
                                            AlertDialog.Builder builder = new AlertDialog.Builder(NotesActivity.this);
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

                                            NotesDetailModel.StudNotesDtlsBean note = subjWiseNoteDtlArrayList.get(position);

                                            String LIKE_STATUS=note.getLIKE_STATUS();
                                            String LIKE_COUNT=note.getLIKE_COUNT();
                                            String DISLIKE_STATUS=note.getDISLIKE_STATUS();
                                            String DISLIKE_COUNT=note.getDISLIKE_COUNT();
                                            String type = "DISLIKE";
                                            String ACAD_NOTES_MASTER_ID = note.getACAD_NOTES_MASTER_ID();

                                            if(DISLIKE_STATUS.equalsIgnoreCase("F") && LIKE_STATUS.equalsIgnoreCase("F")){
                                                note.setDISLIKE_STATUS("T");
                                                note.setDISLIKE_COUNT(String.valueOf(Integer.parseInt(DISLIKE_COUNT)+1));
                                                subjectWiseNotesAdpter.notifyDataSetChanged();
                                                funLikeUnlike(ACAD_NOTES_MASTER_ID, type);

                                            }else if(DISLIKE_STATUS.equalsIgnoreCase("T") && LIKE_STATUS.equalsIgnoreCase("F")){
                                                note.setDISLIKE_STATUS("F");
                                                note.setDISLIKE_COUNT(String.valueOf(Integer.parseInt(DISLIKE_COUNT)-1));
                                                subjectWiseNotesAdpter.notifyDataSetChanged();
                                                //funLikeUnlike(ACAD_NOTES_MASTER_ID, type);

                                            }else if(DISLIKE_STATUS.equalsIgnoreCase("F") && LIKE_STATUS.equalsIgnoreCase("T")){
                                                note.setDISLIKE_STATUS("T");
                                                note.setDISLIKE_COUNT(String.valueOf(Integer.parseInt(DISLIKE_COUNT)+1));
                                                note.setLIKE_STATUS("F");
                                                note.setLIKE_COUNT(String.valueOf(Integer.parseInt(LIKE_COUNT)-1));
                                                subjectWiseNotesAdpter.notifyDataSetChanged();
                                            }
                                        }

                                    }

                                    @Override
                                    public void onViewClick(int position) {
                                        if (!isNetworkAvailable(NotesActivity.this)) {
                                            // Create an Alert Dialog
                                            AlertDialog.Builder builder = new AlertDialog.Builder(NotesActivity.this);
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

                                            String duplicatefileName;
                                            String extention;
                                            String sourcePath;
                                            String employeeId;
                                            String originalFilename;

                                            sourcePath= subjWiseNoteDtlArrayList.get(position).getFULL_PATH();
                                            String NOTES_FILE_PATH=subjWiseNoteDtlArrayList.get(position).getNOTES_FILE_PATH();
                                            String[] value=NOTES_FILE_PATH.split(Pattern.quote("\\"));
                                            String filewithextension=value[1];
                                            String[] val=filewithextension.split(Pattern.quote("."));
                                            duplicatefileName=val[0];
                                            extention=val[1];
                                            String[] data = val[0].split(Pattern.quote("_"));
                                            employeeId = data[0];
                                            employeeId = employeeId.substring(1);
                                            String ACTUAL_FILE_NAME=subjWiseNoteDtlArrayList.get(position).getACTUAL_FILE_NAME();
                                            if(ACTUAL_FILE_NAME.isEmpty()){
                                                originalFilename=duplicatefileName;
                                            }else{
                                                originalFilename=ACTUAL_FILE_NAME;
                                            }
                                            funDownloadURL(duplicatefileName, extention, sourcePath, "file", employeeId, originalFilename);

                                        }

                                    }
                                });
                                recyVwNotes.setVisibility(View.VISIBLE);
                                recyVwNotes.setAdapter(subjectWiseNotesAdpter);

                            } else {
                                Snackbar.make(lytNotes, "Record not available.", Snackbar.LENGTH_LONG)
                                        .setAction("Action", null).show();
                                recyVwNotes.setVisibility(View.GONE);
                            }

                        } else {
                            Snackbar.make(lytNotes, "Record not available.", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                            progNotes.setVisibility(View.GONE);
                            recyVwNotes.setVisibility(View.GONE);
                        }
                        progNotes.setVisibility(View.GONE);


                    } else if (notesDetailModel.getStatus() == 0) {

                        Snackbar.make(lytNotes, "Record not available.", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                        progNotes.setVisibility(View.GONE);
                    }


                } catch (Exception e) {

                    Snackbar.make(lytNotes, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                    Log.d(TAG, "Error : " + e.getMessage());
                    progNotes.setVisibility(View.GONE);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                handleVolleyError(error);
                progNotes.setVisibility(View.GONE);
            }
        });
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                60000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        // Adding request to request queue
        MyApplication.getInstance().addToRequestQueue(stringRequest);


    }


    private void funNotesDetailsTechnical() {

        progNotes.setVisibility(View.VISIBLE);

        String url = pref.getURL() + URLEndPoints.getStudNoteDetails_URL(studBranch_Standard_GRP_ID,studDepartmentNumber,studSemesterType,studCenterCode,student_id);
        Log.d(TAG, " URL  :  " + url);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, response.toString());

                try {

                    Gson gson = new Gson();
                    notesDetailModel = gson.fromJson(response, NotesDetailModel.class);

                    if (notesDetailModel.getStatus() == 1) {

                        notesDetailArrayList = (ArrayList<NotesDetailModel.StudNotesDtlsBean>) notesDetailModel.getStudNotes_Dtls();
                        if (notesDetailArrayList != null) {

                           /* subjectWiseNotesAdpter=new SubjectWiseNotesAdpter(NotesActivity.this,notesDetailArrayList);
                            recyVwNotes.setAdapter(subjectWiseNotesAdpter);*/
                            techArtclNoteDtlArrayList = new ArrayList<NotesDetailModel.StudNotesDtlsBean>();
                            for (int i = 0; i < notesDetailArrayList.size(); i++) {
                                String NOTES_TYPE_CODE=notesDetailArrayList.get(i).getNOTES_TYPE_CODE();
                              //  String subjectName = notesDetailArrayList.get(i).getSUBJECT_DESCRIPTION();
                                if (NOTES_TYPE_CODE.equalsIgnoreCase("TECHNICAL_ARTICLE")){

                                    techArtclNoteDtlArrayList.add(notesDetailArrayList.get(i));
                                }
                            }

                            if (techArtclNoteDtlArrayList != null && techArtclNoteDtlArrayList.size()!=0) {

                                subjectWiseNotesAdpter = new SubjectWiseNotesAdpter(NotesActivity.this, techArtclNoteDtlArrayList, new SubjectWiseNotesAdpter.ButtonClickListner() {
                                    @Override
                                    public void onLikeClick(int position) {

                                        NotesDetailModel.StudNotesDtlsBean note = techArtclNoteDtlArrayList.get(position);
                                        String LIKE_STATUS=note.getLIKE_STATUS();
                                        String LIKE_COUNT=note.getLIKE_COUNT();
                                        String DISLIKE_STATUS=note.getDISLIKE_STATUS();
                                        String DISLIKE_COUNT=note.getDISLIKE_COUNT();
                                        String type = "LIKE";
                                        String ACAD_NOTES_MASTER_ID = note.getACAD_NOTES_MASTER_ID();

                                        if(LIKE_STATUS.equalsIgnoreCase("F") && DISLIKE_STATUS.equalsIgnoreCase("F")){
                                            note.setLIKE_STATUS("T");
                                            note.setLIKE_COUNT(String.valueOf(Integer.parseInt(LIKE_COUNT)+1));
                                            subjectWiseNotesAdpter.notifyDataSetChanged();
                                            funLikeUnlike(ACAD_NOTES_MASTER_ID, type);

                                        }else if(LIKE_STATUS.equalsIgnoreCase("T") && DISLIKE_STATUS.equalsIgnoreCase("F")){
                                            note.setLIKE_STATUS("F");
                                            note.setLIKE_COUNT(String.valueOf(Integer.parseInt(LIKE_COUNT)-1));
                                            subjectWiseNotesAdpter.notifyDataSetChanged();
                                            //funLikeUnlike(ACAD_NOTES_MASTER_ID, type);

                                        }else if(LIKE_STATUS.equalsIgnoreCase("F") && DISLIKE_STATUS.equalsIgnoreCase("T")){
                                            note.setLIKE_STATUS("T");
                                            note.setLIKE_COUNT(String.valueOf(Integer.parseInt(LIKE_COUNT)+1));
                                            note.setDISLIKE_STATUS("F");
                                            note.setDISLIKE_COUNT(String.valueOf(Integer.parseInt(DISLIKE_COUNT)-1));
                                            subjectWiseNotesAdpter.notifyDataSetChanged();
                                        }

                                    }

                                    @Override
                                    public void onUnlikeClick(int position) {

                                        NotesDetailModel.StudNotesDtlsBean note = techArtclNoteDtlArrayList.get(position);

                                        String LIKE_STATUS=note.getLIKE_STATUS();
                                        String LIKE_COUNT=note.getLIKE_COUNT();
                                        String DISLIKE_STATUS=note.getDISLIKE_STATUS();
                                        String DISLIKE_COUNT=note.getDISLIKE_COUNT();
                                        String type = "DISLIKE";
                                        String ACAD_NOTES_MASTER_ID = note.getACAD_NOTES_MASTER_ID();

                                        if(DISLIKE_STATUS.equalsIgnoreCase("F") && LIKE_STATUS.equalsIgnoreCase("F")){
                                            note.setDISLIKE_STATUS("T");
                                            note.setDISLIKE_COUNT(String.valueOf(Integer.parseInt(DISLIKE_COUNT)+1));
                                            subjectWiseNotesAdpter.notifyDataSetChanged();
                                            funLikeUnlike(ACAD_NOTES_MASTER_ID, type);

                                        }else if(DISLIKE_STATUS.equalsIgnoreCase("T") && LIKE_STATUS.equalsIgnoreCase("F")){
                                            note.setDISLIKE_STATUS("F");
                                            note.setDISLIKE_COUNT(String.valueOf(Integer.parseInt(DISLIKE_COUNT)-1));
                                            subjectWiseNotesAdpter.notifyDataSetChanged();
                                            //funLikeUnlike(ACAD_NOTES_MASTER_ID, type);

                                        }else if(DISLIKE_STATUS.equalsIgnoreCase("F") && LIKE_STATUS.equalsIgnoreCase("T")){
                                            note.setDISLIKE_STATUS("T");
                                            note.setDISLIKE_COUNT(String.valueOf(Integer.parseInt(DISLIKE_COUNT)+1));
                                            note.setLIKE_STATUS("F");
                                            note.setLIKE_COUNT(String.valueOf(Integer.parseInt(LIKE_COUNT)-1));
                                            subjectWiseNotesAdpter.notifyDataSetChanged();
                                        }

                                    }

                                    @Override
                                    public void onViewClick(int position) {

                                       /* String strfileName = techArtclNoteDtlArrayList.get(position).getACTUAL_FILE_NAME();
                                        String fileName, Extent;
                                        String[] filenameNextension = strfileName.split(Pattern.quote("."));
                                        String originalfileName = filenameNextension[0];

                                        String sourcePath = "\\\\" + techArtclNoteDtlArrayList.get(position).getFULL_PATH();
                                        String str = sourcePath.substring(sourcePath.lastIndexOf("\\") + 1);
                                        String[] value = str.split(Pattern.quote("."));
                                        fileName = value[0];
                                        Extent = value[1];

                                        String[] data = fileName.split(Pattern.quote("_"));
                                        String employeeId = data[0];
                                        employeeId = employeeId.substring(1);

                                        String sourcePathsplit = "\\\\" + techArtclNoteDtlArrayList.get(position).getFULL_PATH();
                                        String[] source=sourcePathsplit.split(Pattern.quote("Employee\\"));
                                        String originalSource=source[0]+"Employee\\"+employeeId;


                                        funDownloadURL(fileName, Extent, originalSource, "file", employeeId, originalfileName);*/

                                        String duplicatefileName;
                                        String extention;
                                        String sourcePath;
                                        String employeeId;
                                        String originalFilename;

                                        sourcePath= techArtclNoteDtlArrayList.get(position).getFULL_PATH();
                                        String NOTES_FILE_PATH=techArtclNoteDtlArrayList.get(position).getNOTES_FILE_PATH();
                                        String[] value=NOTES_FILE_PATH.split(Pattern.quote("\\"));
                                        String filewithextension=value[1];
                                        String[] val=filewithextension.split(Pattern.quote("."));
                                        duplicatefileName=val[0];
                                        extention=val[1];
                                        String[] data = val[0].split(Pattern.quote("_"));
                                        employeeId = data[0];
                                        employeeId = employeeId.substring(1);
                                        String ACTUAL_FILE_NAME=techArtclNoteDtlArrayList.get(position).getACTUAL_FILE_NAME();
                                        if(ACTUAL_FILE_NAME.isEmpty()){
                                            originalFilename=duplicatefileName;
                                        }else{
                                            originalFilename=ACTUAL_FILE_NAME;
                                        }


                                        funDownloadURL(duplicatefileName, extention, sourcePath, "file", employeeId, originalFilename);



                                    }
                                });
                                recyVwTechnNotes.setVisibility(View.VISIBLE);
                                recyVwTechnNotes.setAdapter(subjectWiseNotesAdpter);

                            } else {
                                Snackbar.make(lytNotes, "Record not available.", Snackbar.LENGTH_LONG)
                                        .setAction("Action", null).show();
                                recyVwTechnNotes.setVisibility(View.GONE);
                            }

                        } else {
                            Snackbar.make(lytNotes, "Record not available.", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                            progNotes.setVisibility(View.GONE);
                            recyVwTechnNotes.setVisibility(View.GONE);
                        }
                        progNotes.setVisibility(View.GONE);


                    } else if (notesDetailModel.getStatus() == 0) {

                        Snackbar.make(lytNotes, "Record not available.", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                        progNotes.setVisibility(View.GONE);
                    }


                } catch (Exception e) {

                    Snackbar.make(lytNotes, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                    Log.d(TAG, "Error : " + e.getMessage());
                    progNotes.setVisibility(View.GONE);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
               handleVolleyError(error);
                progNotes.setVisibility(View.GONE);
            }
        });
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                60000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        // Adding request to request queue
        MyApplication.getInstance().addToRequestQueue(stringRequest);


    }



    private void funLikeUnlike(final String ACAD_NOTES_MASTER_ID, final String type) {

        progNotes.setVisibility(View.VISIBLE);
        String url = pref.getURL() + URLEndPoints.SubDataInsert_URL;
        Log.d(TAG, "URL : " + url);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {

                    Log.d(TAG, "response : " + response);

                    progNotes.setVisibility(View.GONE);
                } catch (Exception e) {

                    Snackbar.make(lytNotes, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                    Log.d(TAG, "Error : " + e.getMessage());
                    progNotes.setVisibility(View.GONE);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                handleVolleyError(error);
                progNotes.setVisibility(View.GONE);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("P_ACAD_NOTES_MASTER_ID", ACAD_NOTES_MASTER_ID);
                params.put("P_PERSON_TYPE", "STUDENT");
                params.put("P_PERSON_ID", student_id);
                params.put("P_CENTRE_CODE", studCenterCode);
                params.put("P_IS_ACTIVE", "Y");
                params.put("P_MARK_TYPE", type);
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

    private void funDownloadURL(final String fileName, final String extent, final String sourcePath, final String imageType, final String employeeId,final String originalFilename) {

        mProgressDialog.setMessage("Please wait loading!");
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.show();

        String url= NavigationActivity.BASE_URL + URLEndPoints.PostDownloadCopyTo_URL;
        Log.d(TAG, url);
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Response : "+NavigationActivity.BASE_URL+""+response.toString());
                try{

                    //{"status":1,"data":[{"FilePath":"APP_DOWNLOAD\\25012019024218714.docx","FileName":"documentfile"}]}
                    Gson gson=new Gson();
                    downloadURLModel=gson.fromJson(response,DownloadURLModel.class);
                    if(downloadURLModel.getStatus()==1){
                        mProgressDialog.dismiss();

                        downloadURLArrayList=(ArrayList<DownloadURLModel.DataBean>)downloadURLModel.getData();
                        if(downloadURLArrayList.size()!=0 && downloadURLArrayList!=null){

                            String str=NavigationActivity.BASE_URL;          //http://117.247.82.252:500/api/
                            String[] s=str.split(Pattern.quote("api"));
                            String downloadURL=s[0]+downloadURLArrayList.get(0).getFilePath();
                            String Originalfilename=downloadURLArrayList.get(0).getFileName();
                            Download_Uri = Uri.parse(downloadURL);

                            funDownloadFile(Originalfilename);

                            Log.d(TAG, "File download complete. : "+downloadURL);

                            Snackbar.make(lytNotes, "File downloaded in the Download folder.", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        }

                    }else{
                        mProgressDialog.hide();
                        Snackbar.make(lytNotes, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }


                }catch (Exception e) {
                    mProgressDialog.hide();
                    Snackbar.make(lytNotes, e.getMessage(), Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    Log.d(TAG, "Error : " + e.getMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mProgressDialog.hide();
                Log.e(TAG, "Error: " + error.getMessage());
                Snackbar.make(lytNotes, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("fileName", fileName);
                params.put("Extent", extent);
                params.put("sourcePath", sourcePath);
                params.put("imageType", imageType);
                params.put("Employee_id", employeeId);
                params.put("Persone_Type", "EMPLOYEE");
                params.put("OrignalName",originalFilename);

                Log.e(TAG, "InActive Posting params: " + params.toString());
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



    private void funDownloadFile(String originalfilename) {
        DownloadManager.Request request = new DownloadManager.Request(Download_Uri);
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
        request.setAllowedOverRoaming(false);
        request.setTitle("Downloading " + originalfilename);
        request.setDescription("Downloading... " +originalfilename);
        request.setVisibleInDownloadsUi(true);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOCUMENTS, originalfilename);
//        } else {
            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, originalfilename);
//        }
        refid = downloadManager.enqueue(request);
        Log.e("OUT", "" + refid);
    }

    BroadcastReceiver onComplete = new BroadcastReceiver() {

        public void onReceive(Context ctxt, Intent intent) {

            long referenceId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);


            Log.e("IN", "" + referenceId);

            Log.e("INSIDE", "" + referenceId);
            NotificationCompat.Builder mBuilder =
                    new NotificationCompat.Builder(NotesActivity.this)
                            .setSmallIcon(R.drawable.new_logo)
                            .setContentTitle("SASHA APP DOWNLOAD")
                            .setContentText("All Download completed");


            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(455, mBuilder.build());


            //  }

        }
    };


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(NotesActivity.this, NavigationActivity.class);
        intent.putExtra("activity","NotesActivity");
        startActivity(intent);
        finish();
    }


//    public void downloadFile(String uRl, String fileName) {
//        File direct = new File(Environment.getExternalStorageDirectory()
//                + "/AnhsirkDasarp");
//
//        if (!direct.exists()) {
//            direct.mkdirs();
//        }
//
//        DownloadManager mgr = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
//
//        Uri downloadUri = Uri.parse(uRl);
//        DownloadManager.Request request = new DownloadManager.Request(
//                downloadUri);
//
//        request.setAllowedNetworkTypes(
//                DownloadManager.Request.NETWORK_WIFI
//                        | DownloadManager.Request.NETWORK_MOBILE)
//                .setAllowedOverRoaming(false).setTitle("Demo")
//                .setDescription("Something useful. No, really.")
//                .setDestinationInExternalPublicDir("/AnhsirkDasarp", fileName);
//
//        mgr.enqueue(request);
//
//    }
}
