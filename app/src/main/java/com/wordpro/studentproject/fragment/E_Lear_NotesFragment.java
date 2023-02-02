package com.wordpro.studentproject.fragment;


import android.Manifest;
import android.app.AlertDialog;
import android.app.DownloadManager;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
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
import com.wordpro.studentproject.activities.NavigationActivity;
import com.wordpro.studentproject.activities.e_learning_menu.NotesActivity;
import com.wordpro.studentproject.adapter.Assignment_List_Adapter;
import com.wordpro.studentproject.adapter.Notes_List_Adapter;
import com.wordpro.studentproject.adapter.Notes_subjectList_Adapter;
import com.wordpro.studentproject.app.MyApplication;
import com.wordpro.studentproject.helper.PrefManager;
import com.wordpro.studentproject.model.AssignmentModel;
import com.wordpro.studentproject.model.DownloadURLModel;
import com.wordpro.studentproject.model.NotesDetailModel;
import com.wordpro.studentproject.model.NotesSemModel;
import com.wordpro.studentproject.model.SubjectModel;
import com.wordpro.studentproject.utils.UtilityClass;
import com.wordpro.studentproject.webConfig.URLEndPoints;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import static com.wordpro.studentproject.activities.NavigationActivity.BASE_URL;
import static com.wordpro.studentproject.activities.NavigationActivity.fragE_learning;
import static com.wordpro.studentproject.activities.NavigationActivity.studBranchStandardId;
import static com.wordpro.studentproject.activities.NavigationActivity.studCenterCode;
import static com.wordpro.studentproject.activities.NavigationActivity.studSessionId;
import static com.wordpro.studentproject.activities.NavigationActivity.student_id;
import static com.wordpro.studentproject.fragment.FragE_Learning.notesSemArrayList;
import static com.wordpro.studentproject.network.Network.isNetworkAvailable;


/**
 * A simple {@link Fragment} subclass.
 */
public class E_Lear_NotesFragment extends Fragment implements View.OnClickListener {

    public static String TAG = E_Lear_NotesFragment.class.getSimpleName();

    LinearLayout bottom_lay, belowId;
    private ImageView back_press;
    private ProgressBar progBarAssi;
    private TextView txt_toolbarName, act_submitted, act_publishASSi;
    public static AssignmentModel assignmentModel;
    public static ArrayList<AssignmentModel.StudAssignDtlsBean> studAssignDtlsArrayList;
    private PrefManager pref;
    private RecyclerView recycle_view;
    private RelativeLayout lay_view;
    Notes_List_Adapter notes_list_adapter;
    private TextView txt_NoteType, txt_evenType, txt_SubjectType;
    private Spinner noteType_spinner, evenType_spinner, SubjectType_spinner;
    int firstPos = 0;
    public static NotesSemModel notesSemModel;
    public static ArrayList<NotesSemModel.SemesterDtlsBean> notesSemArrayList;
    ArrayList<String> semstrArray;
    ArrayList<String> notesType;
    String selectedNotesType;
    String type="0";
    String selectedChoiceSemType,selectedSubject, selectedChoiceSubject, SUB_SHORT_DESC, SEMESTER_TYPE_CODE, selectedSemType;
    //----------------------------------
    private DownloadManager downloadManager;
    private long refid;
    private Uri Download_Uri;
    public static SubjectModel subjectModel;
    public static ArrayList<SubjectModel.DataBean> subjectArrayList;
    ArrayList<String> subjectArray;
    Notes_subjectList_Adapter notes_subjectList_adapter;

    public static NotesDetailModel notesDetailModel;
    public static ArrayList<NotesDetailModel.StudNotesDtlsBean> notesDetailArrayList;
    public static ArrayList<NotesDetailModel.StudNotesDtlsBean> subjWiseNoteDtlArrayList;
    public static ArrayList<NotesDetailModel.StudNotesDtlsBean> techArtclNoteDtlArrayList;

    public static DownloadURLModel downloadURLModel;
    public static ArrayList<DownloadURLModel.DataBean> downloadURLArrayList;
    private UtilityClass utilityClassObj;
    public E_Lear_NotesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.e_lear_notes_fragment, container, false);

        pref = new PrefManager(getActivity());
        utilityClassObj  = new  UtilityClass(getActivity());
        downloadManager = (DownloadManager) getActivity().getSystemService(Context.DOWNLOAD_SERVICE);
        downloadManager = (DownloadManager) getActivity().getSystemService(Context.DOWNLOAD_SERVICE);
        getActivity().registerReceiver(onComplete,
                new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    11);
        }

        notesType=new ArrayList<>();
        lay_view = (RelativeLayout) view.findViewById(R.id.lay_view);
        recycle_view = (RecyclerView) view.findViewById(R.id.recycle_view);
        txt_toolbarName = (TextView) view.findViewById(R.id.txt_toolbarName);
        back_press = (ImageView) view.findViewById(R.id.back_press);
        progBarAssi = (ProgressBar) view.findViewById(R.id.progBarAssi);

        txt_NoteType = (TextView) view.findViewById(R.id.txt_NoteType);
        txt_evenType = (TextView) view.findViewById(R.id.txt_evenType);
        txt_SubjectType = (TextView) view.findViewById(R.id.txt_SubjectType);

        noteType_spinner = (Spinner) view.findViewById(R.id.noteType_spinner);
        evenType_spinner = (Spinner) view.findViewById(R.id.evenType_spinner);
        SubjectType_spinner = (Spinner) view.findViewById(R.id.SubjectType_spinner);

        belowId = (LinearLayout) view.findViewById(R.id.belowId);

        try {
            getActivity().findViewById(R.id.txt_toolbarName);
        } catch (Exception e) {
            e.printStackTrace();
        }

        belowId.setVisibility(View.GONE);
        Typeface type_faceHeading = Typeface.createFromAsset(getActivity().getAssets(), "font/Poppins-Bold.ttf");
        Typeface type_faceContent = Typeface.createFromAsset(getActivity().getAssets(), "font/Poppins-Regular.ttf");

        TextView textview = (TextView) getActivity().findViewById(R.id.txt_toolbarName);
        textview.setText("Notes");
        textview.setTypeface(type_faceHeading);

        back_press.setOnClickListener(this);
        // act_publishASSi.setOnClickListener(this);
        // act_submitted.setOnClickListener(this);


        // NoteType();

        //funNotesDtls();
        funNotesDetails(URLEndPoints.Constance_StudSemesterType);
        return view;

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_press:
//                Intent intent = new Intent(getActivity(), NavigationActivity.class);
//                startActivity(intent);
//                onDestroy();
                getActivity().finish();
                break;

        }

    }


    /*   private void funNotesDtls() {

           progBarAssi.setVisibility(View.VISIBLE);
           String url = pref.getURL() + URLEndPoints.getNOTES_Details_URL(studSessionId, studCenterCode);

           Log.d(TAG, " URL  :  " + url);

           StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
               @Override
               public void onResponse(String response) {
                   Log.d(TAG, response.toString());
                   try {

                       Gson gson = new Gson();
                       notesSemModel = gson.fromJson(response, NotesSemModel.class);

                       if (notesSemModel.getStatus() == 1) {

                           progBarAssi.setVisibility(View.GONE);
                           notesSemArrayList = (ArrayList<NotesSemModel.SemesterDtlsBean>) notesSemModel.getSemester_Dtls();

                           if (notesSemArrayList != null) {
                               semstrArray = new ArrayList<String>();

                               for (int i = 0; i < notesSemArrayList.size(); i++) {
                                   String SEMESTER_TYPE_NAME = notesSemArrayList.get(i).getSEMESTER_TYPE_NAME();
                                   semstrArray.add(SEMESTER_TYPE_NAME);
                               }

                               //SemesterType();

                           } else {
                               Snackbar.make(lay_view, "Record not available.", Snackbar.LENGTH_LONG)
                                       .setAction("Action", null).show();
                               progBarAssi.setVisibility(View.GONE);

                           }

                       } else if (assignmentModel.getStatus() == 0) {
                           Snackbar.make(lay_view, "Record not available.", Snackbar.LENGTH_LONG)
                                   .setAction("Action", null).show();
                           progBarAssi.setVisibility(View.GONE);

                       }

                   } catch (Exception e) {
                       Snackbar.make(lay_view, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
                               .setAction("Action", null).show();
                       Log.d(TAG, "Error : " + e.getMessage());
                       progBarAssi.setVisibility(View.GONE);
                   }

               }
           }, new Response.ErrorListener() {
               @Override
               public void onErrorResponse(VolleyError error) {
                   handleVolleyError(error);
                   progBarAssi.setVisibility(View.GONE);
               }
           });
           stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                   60000,
                   DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                   DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

           // Adding request to request queue
           MyApplication.getInstance().addToRequestQueue(stringRequest);

       }

       private void NoteType(){

           notesType.add("--Select--");
           notesType.add("Subject Notes");
           notesType.add("Practical Notes");
           notesType.add("Technical Article");
           ArrayAdapter notesTypeAdpter=new ArrayAdapter(getActivity(),R.layout.multiline_spinner_item,notesType);
           notesTypeAdpter.setDropDownViewResource(R.layout.textlayout);
           noteType_spinner.setAdapter(notesTypeAdpter);

           noteType_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
               @Override
               public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {


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
                       selectedNotesType=adapterView.getItemAtPosition(position).toString();
                       txt_NoteType.setText(selectedNotesType);
                       if(adapterView.getItemAtPosition(position).toString().equalsIgnoreCase("Technical Article")){
                           type="1";
                          // funNotesDetailsTechnical();
                       }else if(adapterView.getItemAtPosition(position).toString().equalsIgnoreCase("Subject Notes") || adapterView.getItemAtPosition(position).toString().equalsIgnoreCase("Practical Notes")){
                           type="2";
                       }else if(adapterView.getItemAtPosition(position).toString().equalsIgnoreCase("--Select--")){
                           Snackbar.make(lay_view, "Select notes type.", Snackbar.LENGTH_LONG)
                                   .setAction("Action", null).show();
                       }
                       funNotesDtls();
                   }


               }

               @Override
               public void onNothingSelected(AdapterView<?> parent) {

               }
           });

       }
       private void SemesterType(){

           ArrayAdapter semAdapter = new ArrayAdapter(getActivity(), R.layout.multiline_spinner_item, semstrArray);
           semAdapter.setDropDownViewResource(R.layout.textlayout);
           evenType_spinner.setAdapter(semAdapter);
           evenType_spinner.setSelection(firstPos);
           txt_evenType.setText(semstrArray.get(firstPos).toString());

           evenType_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
               @Override
               public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {



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
                       selectedChoiceSemType = adapterView.getItemAtPosition(position).toString();
                       txt_evenType.setText(selectedChoiceSemType);

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

       private void SubjectList(){
           ArrayAdapter subjAdapter = new ArrayAdapter(getActivity(), R.layout.multiline_spinner_item, subjectArray);
           subjAdapter.setDropDownViewResource(R.layout.textlayout);
           SubjectType_spinner.setAdapter(subjAdapter);
           //spinnerSubject.setSelection(firstPos);

           SubjectType_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
               @Override
               public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                   selectedChoiceSubject = adapterView.getItemAtPosition(position).toString();
                   txt_SubjectType.setText(selectedChoiceSubject);

                   if(selectedChoiceSubject.equalsIgnoreCase("--Select--")){
                       Snackbar.make(lay_view, "Select Subject.", Snackbar.LENGTH_LONG)
                               .setAction("Action", null).show();
                   }else{
                       if(SUB_SHORT_DESC!=null){
                           SUB_SHORT_DESC = subjectArrayList.get(position-1).getSUBJECT_DESCRIPTION() + " - " + subjectArrayList.get(position-1).getAPPLI_TYPE_DESCRIPTION();
                           selectedSubject = SUB_SHORT_DESC;
                          // funNotesDetails(semester_type_code, selectedSubject);
                       }

                   }

               }

               @Override
               public void onNothingSelected(AdapterView<?> adapterView) {

               }
           });

       }
       private void funSubjectList(final String semester_type_code) {

           progBarAssi.setVisibility(View.VISIBLE);
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
                           progBarAssi.setVisibility(View.GONE);
                           subjectArrayList = (ArrayList<SubjectModel.DataBean>) subjectModel.getData();

                           if (subjectArrayList != null && subjectArrayList.size()!=0) {

                               subjectArray = new ArrayList<String>();
                               subjectArray.add("--Select--");
                               for (int i = 0; i < subjectArrayList.size(); i++) {

                                   subjectArray.add(subjectArrayList.get(i).getSUBJECT_DESCRIPTION() + " - " + subjectArrayList.get(i).getAPPLI_TYPE_DESCRIPTION());
                               }

                               SubjectList();


                           } else {
                               Snackbar.make(lay_view, "Record not available.", Snackbar.LENGTH_LONG)
                                       .setAction("Action", null).show();
                               progBarAssi.setVisibility(View.GONE);

                           }


                       } else if (subjectModel.getStatus() == 0) {

                           Snackbar.make(lay_view, "Record not available.", Snackbar.LENGTH_LONG)
                                   .setAction("Action", null).show();
                           progBarAssi.setVisibility(View.GONE);

                       }

                   } catch (Exception e) {

                       Snackbar.make(lay_view, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
                               .setAction("Action", null).show();

                       Log.d(TAG, "Error : " + e.getMessage());
                       progBarAssi.setVisibility(View.GONE);
                   }


               }
           }, new Response.ErrorListener() {
               @Override
               public void onErrorResponse(VolleyError error) {

                   handleVolleyError(error);
                   progBarAssi.setVisibility(View.GONE);
               }
           });
           stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                   60000,
                   DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                   DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

           // Adding request to request queue
           MyApplication.getInstance().addToRequestQueue(stringRequest);


       }*/
    private void funNotesDetails(String semester_type_code) {

        utilityClassObj.startLoader(getActivity(),R.drawable.image_for_rotation);

        String url = pref.getURL() + URLEndPoints.getStudNoteDetails_URL(URLEndPoints.Constance_studBranch_Standard_GRP_ID,URLEndPoints.Constance_StudentDepartmentNumber,semester_type_code,URLEndPoints.Constance_StudentCenterCode,URLEndPoints.Constance_StudentID);
        Log.e(TAG, " URL  :  " + url);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e(TAG, response.toString());

                try {

                    Gson gson = new Gson();
                    notesDetailModel = gson.fromJson(response, NotesDetailModel.class);

                    if (notesDetailModel.getStatus() == 1) {
                        utilityClassObj.stopLoader();
                        notesDetailArrayList = (ArrayList<NotesDetailModel.StudNotesDtlsBean>) notesDetailModel.getStudNotes_Dtls();
                        if (notesDetailArrayList != null) {


                            notes_list_adapter = new Notes_List_Adapter(getActivity(), notesDetailArrayList);
                            recycle_view.setLayoutManager(new LinearLayoutManager(getActivity()));
                            recycle_view.setAdapter(notes_list_adapter);




                            notes_list_adapter.setOnItemClickListener(new Notes_List_Adapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(View itemView, int position ,String download) {

                                    if(download.equalsIgnoreCase("download")){
                                        String duplicatefileName;
                                        String extention;
                                        String sourcePath;
                                        String employeeId;
                                        String ORG,Path;
                                        String originalFilename;

                                        sourcePath= notesDetailArrayList.get(position).getFULL_PATH();
                                        String NOTES_FILE_PATH=notesDetailArrayList.get(position).getNOTES_FILE_PATH();
                                        String[] value=NOTES_FILE_PATH.split(Pattern.quote("\\"));
                                        String filewithextension=value[1];
                                        String[] val=filewithextension.split(Pattern.quote("."));
                                        duplicatefileName=val[0];
                                        extention=val[1];

                                        String[] colon = sourcePath.split(Pattern.quote(":"));
                                        ORG= colon[0];
                                        // Path= colon[1];

                                        String[] data = val[0].split(Pattern.quote("_"));
                                        employeeId = data[0];
                                        employeeId = employeeId.substring(1);


                                        String test = notesDetailArrayList.get(position).getNOTES_FILE_PATH();

                                        String[] sou = test.split(Pattern.quote("\\"));
                                        String origin = sou[0]+"/"+sou[1];
                                        //  String path = notesDetailArrayList.get(position).getNOTES_FILE_PATH();


                                        String uu ="";
                                        String q,w;
                                        String str = test.substring(test.lastIndexOf("\\") + 1);
                                        String[] filenameNextension = str.split(Pattern.quote("."));
                                        q = filenameNextension[0];
                                        w = filenameNextension[1];
                                        String a =q+w;
                                        try{
                                            String filepathname=notesDetailArrayList.get(position).getDOCU_UPLOAD_STATIC_PATH().replace("\\","/");
                                            Log.e("filePath==","=="+filepathname);
                                            uu =  notesDetailArrayList.get(position).getSTATIC_IP()+""+filepathname+"/"+origin;
                                        }catch (Exception e){
                                            e.printStackTrace();
                                        }


                                        String path ="http://"+uu;

                                        // funDownloadFile(path);



                                        //  String org =downloadURL+ORG+Path;

                                        // String path ="http://103.96.41.134/SASHA.pdf";

                                        //  funDownloadFile(path);
                                        funDownloadFile(path,str);


                                        //String org ="\\\\192.168.1.72/"+ORG+Path;
                                        // String org = ORG+Path;
                                        // String uu = org;
                                        //funDownloadURL(duplicatefileName, extention, sourcePath, "file", employeeId, originalFilename);



                                        //funDownloadURL(duplicatefileName, extention, org, "file", employeeId, originalFilename);

                                    }else if (download.equalsIgnoreCase("like")){
                                        NotesDetailModel.StudNotesDtlsBean note = notesDetailArrayList.get(position);
                                        String LIKE_STATUS=note.getLIKE_STATUS();
                                        String LIKE_COUNT=note.getLIKE_COUNT();
                                        String DISLIKE_STATUS=note.getDISLIKE_STATUS();
                                        String DISLIKE_COUNT=note.getDISLIKE_COUNT();
                                        String type = "LIKE";
                                        String ACAD_NOTES_MASTER_ID = note.getACAD_NOTES_MASTER_ID();

                                        if(LIKE_STATUS.equalsIgnoreCase("F") && DISLIKE_STATUS.equalsIgnoreCase("F")){
                                            note.setLIKE_STATUS("T");
                                            note.setLIKE_COUNT(String.valueOf(Integer.parseInt(LIKE_COUNT)+1));
                                            notes_list_adapter.notifyDataSetChanged();
                                            funLikeUnlike(ACAD_NOTES_MASTER_ID, type);

                                        }else if(LIKE_STATUS.equalsIgnoreCase("T") && DISLIKE_STATUS.equalsIgnoreCase("F")){
                                            note.setLIKE_STATUS("F");
                                            note.setLIKE_COUNT(String.valueOf(Integer.parseInt(LIKE_COUNT)-1));
                                            notes_list_adapter.notifyDataSetChanged();
                                            //funLikeUnlike(ACAD_NOTES_MASTER_ID, type);

                                        }else if(LIKE_STATUS.equalsIgnoreCase("F") && DISLIKE_STATUS.equalsIgnoreCase("T")){
                                            note.setLIKE_STATUS("T");
                                            note.setLIKE_COUNT(String.valueOf(Integer.parseInt(LIKE_COUNT)+1));
                                            note.setDISLIKE_STATUS("F");
                                            note.setDISLIKE_COUNT(String.valueOf(Integer.parseInt(DISLIKE_COUNT)-1));
                                            notes_list_adapter.notifyDataSetChanged();
                                        }

                                    }else if (download.equalsIgnoreCase("unlike")){
                                        NotesDetailModel.StudNotesDtlsBean note = notesDetailArrayList.get(position);

                                        String LIKE_STATUS=note.getLIKE_STATUS();
                                        String LIKE_COUNT=note.getLIKE_COUNT();
                                        String DISLIKE_STATUS=note.getDISLIKE_STATUS();
                                        String DISLIKE_COUNT=note.getDISLIKE_COUNT();
                                        String type = "DISLIKE";
                                        String ACAD_NOTES_MASTER_ID = note.getACAD_NOTES_MASTER_ID();

                                        if(DISLIKE_STATUS.equalsIgnoreCase("F") && LIKE_STATUS.equalsIgnoreCase("F")){
                                            note.setDISLIKE_STATUS("T");
                                            note.setDISLIKE_COUNT(String.valueOf(Integer.parseInt(DISLIKE_COUNT)+1));
                                            notes_list_adapter.notifyDataSetChanged();
                                            funLikeUnlike(ACAD_NOTES_MASTER_ID, type);

                                        }else if(DISLIKE_STATUS.equalsIgnoreCase("T") && LIKE_STATUS.equalsIgnoreCase("F")){
                                            note.setDISLIKE_STATUS("F");
                                            note.setDISLIKE_COUNT(String.valueOf(Integer.parseInt(DISLIKE_COUNT)-1));
                                            notes_list_adapter.notifyDataSetChanged();
                                            //funLikeUnlike(ACAD_NOTES_MASTER_ID, type);

                                        }else if(DISLIKE_STATUS.equalsIgnoreCase("F") && LIKE_STATUS.equalsIgnoreCase("T")){
                                            note.setDISLIKE_STATUS("T");
                                            note.setDISLIKE_COUNT(String.valueOf(Integer.parseInt(DISLIKE_COUNT)+1));
                                            note.setLIKE_STATUS("F");
                                            note.setLIKE_COUNT(String.valueOf(Integer.parseInt(LIKE_COUNT)-1));
                                            notes_list_adapter.notifyDataSetChanged();
                                        }
                                    }



                                }
                            });

                        } else {
                            Snackbar.make(lay_view, "Record not available.", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                            utilityClassObj.stopLoader();
                        }



                    } else if (notesDetailModel.getStatus() == 0) {

                        Snackbar.make(lay_view, "Record not available.", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                        utilityClassObj.stopLoader();
                    }


                } catch (Exception e) {

                    Snackbar.make(lay_view, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                    Log.d(TAG, "Error : " + e.getMessage());
                    utilityClassObj.stopLoader();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                handleVolleyError(error);
                utilityClassObj.stopLoader();
            }
        });
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                60000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        // Adding request to request queue
        MyApplication.getInstance().addToRequestQueue(stringRequest);


    }
    private void funDownloadURL(final String fileName, final String extent, final String sourcePath, final String imageType, final String employeeId,final String originalFilename) {
        progBarAssi.setVisibility(View.VISIBLE);
        String url= BASE_URL + URLEndPoints.PostDownloadCopyTo_URL;
        Log.d(TAG, url);
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Response : "+ BASE_URL+""+response.toString());
                try{

                    //{"status":1,"data":[{"FilePath":"APP_DOWNLOAD\\25012019024218714.docx","FileName":"documentfile"}]}
                    Gson gson=new Gson();
                    downloadURLModel=gson.fromJson(response, DownloadURLModel.class);
                    if(downloadURLModel.getStatus()==1){

                        progBarAssi.setVisibility(View.GONE);
                        downloadURLArrayList=(ArrayList<DownloadURLModel.DataBean>)downloadURLModel.getData();
                        if(downloadURLArrayList.size()!=0 && downloadURLArrayList!=null){

                            String str= BASE_URL;          //http://117.247.82.252:500/api/
                            String[] s=str.split(Pattern.quote("api"));
                            String downloadURL=s[0]+downloadURLArrayList.get(0).getFilePath();
                            String Originalfilename=downloadURLArrayList.get(0).getFileName();
                            Download_Uri = Uri.parse(downloadURL);

                            //funDownloadFile(Originalfilename);

                            Log.d(TAG, "File download complete. : "+downloadURL);

                            Snackbar.make( lay_view, "File downloaded in the Download folder.", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        }

                    }else{
                        progBarAssi.setVisibility(View.GONE);
                        Snackbar.make(lay_view, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }


                }catch (Exception e) {
                    progBarAssi.setVisibility(View.GONE);
                    Snackbar.make( lay_view, e.getMessage(), Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    Log.d(TAG, "Error : " + e.getMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progBarAssi.setVisibility(View.GONE);
                Log.e(TAG, "Error: " + error.getMessage());
                Snackbar.make(lay_view, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
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



    private void funDownloadFile(String URL,String fileName) {
        Log.e("File Path","is="+URL+"File Extension"+fileName);
        DownloadManager downloadmanager = (DownloadManager) getContext().getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uri = Uri.parse(URL);

        DownloadManager.Request request = new DownloadManager.Request(uri);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,"/StudentFolder/Notes/"+fileName);
        request.setTitle(fileName);
        request.setDescription("Downloading");
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setVisibleInDownloadsUi(false);
        request.allowScanningByMediaScanner();
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);
        downloadmanager.enqueue(request);
        utilityClassObj.stopLoader();

        Snackbar.make(lay_view, "File downloaded successfully", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();

/*
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(originalfilename));
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
        request.setAllowedOverRoaming(false);
        request.setTitle("Downloading " + originalfilename);
        request.setDescription("Downloading... " +originalfilename);
        request.setVisibleInDownloadsUi(true);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, originalfilename);

        refid = downloadManager.enqueue(request);

        Log.e("OUT", "" + refid);*/
    }

    BroadcastReceiver onComplete = new BroadcastReceiver() {

        public void onReceive(Context ctxt, Intent intent) {

            long referenceId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);


            Log.e("IN", "" + referenceId);

            Log.e("INSIDE", "" + referenceId);
            NotificationCompat.Builder mBuilder =
                    new NotificationCompat.Builder(getActivity())
                            .setSmallIcon(R.drawable.new_logo)
                            .setContentTitle("SASHA APP DOWNLOAD")
                            .setContentText("All Download completed");


            NotificationManager notificationManager = (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(455, mBuilder.build());


            //  }

        }
    };
    private void funLikeUnlike(final String ACAD_NOTES_MASTER_ID, final String type) {

        utilityClassObj.startLoader(getActivity(),R.drawable.image_for_rotation);
        String url = pref.getURL() + URLEndPoints.SubDataInsert_URL;
        Log.e(TAG, "URL : " + url);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {

                    Log.e(TAG, "response : " + response);

                    utilityClassObj.stopLoader();
                } catch (Exception e) {

                    Snackbar.make(lay_view, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                    Log.d(TAG, "Error : " + e.getMessage());
                    utilityClassObj.stopLoader();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                handleVolleyError(error);
                utilityClassObj.stopLoader();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("P_ACAD_NOTES_MASTER_ID", ACAD_NOTES_MASTER_ID);
                params.put("P_PERSON_TYPE", "STUDENT");
                params.put("P_PERSON_ID", URLEndPoints.Constance_StudentID);
                params.put("P_CENTRE_CODE", URLEndPoints.Constance_StudentCenterCode);
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
