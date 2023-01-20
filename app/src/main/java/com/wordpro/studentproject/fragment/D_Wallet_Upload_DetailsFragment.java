package com.wordpro.studentproject.fragment;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.PowerManager;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
import com.wordpro.studentproject.adapter.D_wallet_download_List_Adapter;
import com.wordpro.studentproject.adapter.NetAttendanceAdpter;
import com.wordpro.studentproject.app.MyApplication;
import com.wordpro.studentproject.helper.PrefManager;
import com.wordpro.studentproject.model.DocumPathModel;
import com.wordpro.studentproject.model.DocumentListDetails;
import com.wordpro.studentproject.model.DownloadDocumentList;
import com.wordpro.studentproject.utils.FileUtils;
import com.wordpro.studentproject.utils.UtilityClass;
import com.wordpro.studentproject.webConfig.URLEndPoints;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.content.Context.POWER_SERVICE;
import static com.wordpro.studentproject.activities.NavigationActivity.BASE_URL;
import static com.wordpro.studentproject.activities.NavigationActivity.studCenterCode;
import static com.wordpro.studentproject.activities.NavigationActivity.studSemesterType;
import static com.wordpro.studentproject.activities.NavigationActivity.studSessionId;
import static com.wordpro.studentproject.activities.NavigationActivity.student_id;
import static com.wordpro.studentproject.fragment.E_Lear_AssignmentFragment.MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE;


public class D_Wallet_Upload_DetailsFragment extends Fragment {
    private static String TAG = D_Wallet_Upload_DetailsFragment.class.getSimpleName();
    private LinearLayout  main_linear_view;

    D_Wallet_DownloadFragment actionNewsFragment;
    private String  NameOfParam;
    private static final int PERMISSION_REQUEST_CODE = 200;
    private static final int PICK_FILE_REQUEST = 1;
    PowerManager.WakeLock wakeLock;
    private final int REQUEST_READ_PHONE_STATE = 1;
    private String selectedFilePath;
    private String fileDocName;
    long fileSizeInKB;

    private PrefManager pref;

    private RelativeLayout  lytAttndence;
    private RecyclerView download_list;
    public DownloadDocumentList downloadDocModel;
    public List<DocumentListDetails> documentListDtls;
    private D_wallet_download_List_Adapter d_wallet_download_list_adapter;
    private LinearLayout ivAttachment,layout_view;
    private TextView txtChoose,txtFileName;
    private Button btn_upload;
    public static NetAttendanceAdpter netAttendanceAdpter;
    public Fragment fragment;
    private UtilityClass utilityClassObj;
    public static DocumPathModel documPathModel;
    public static ArrayList<DocumPathModel.DtEmpsalAnnualBean> docuPathArrayList;
    String DOCU_UPLOAD_PATH = "", UPLOAD_DOCU_PATH_DTLS_ID = "",COMPLETPATH ="",FilepATH ="";
    public D_Wallet_Upload_DetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.upload_file_layout, container, false);
        Typeface type_faceHeading = Typeface.createFromAsset(getActivity().getAssets(), "font/Poppins-Bold.ttf");
        Typeface type_faceContent = Typeface.createFromAsset(getActivity().getAssets(), "font/Poppins-Regular.ttf");
        pref = new PrefManager(getActivity());
        utilityClassObj  = new  UtilityClass(getActivity());

        try {
            NameOfParam=getArguments().getString("MST_ID");
        }catch (Exception e){
            e.printStackTrace();
        }
        ivAttachment =(LinearLayout)view.findViewById(R.id.ivAttachment);
        txtChoose =(TextView) view.findViewById(R.id.txtChoose);
        txtFileName =(TextView) view.findViewById(R.id.txtFileName);
        layout_view =(LinearLayout)view.findViewById(R.id.layout_view);
        btn_upload =(Button)view.findViewById(R.id.btn_upload);
        onclickMethod();

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

    }
    @Override
    public void onStop() {
        super.onStop();

    }


    @Override
    public void onResume() {
        super.onResume();

    }
    private void onclickMethod(){
        ivAttachment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!checkPermission()) {
                    showFileChooser();
                } else {
                    if (checkPermission()) {
                        requestPermissionAndContinue();
                    } else {
                        showFileChooser();
                    }
                }
            }
        });
        btn_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new Thread(new Runnable(){
                    @Override
                    public void run() {
                        try {
                            uploadFile(selectedFilePath);
                        }
                        catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                }).start();

            }
        });
    }
    private void requestPermissionAndContinue() {
        if (ContextCompat.checkSelfPermission(getActivity().getApplicationContext(), WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(getActivity().getApplicationContext(), READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), WRITE_EXTERNAL_STORAGE)
                    && ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), READ_EXTERNAL_STORAGE)) {
                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(getActivity());
                alertBuilder.setCancelable(true);
                alertBuilder.setTitle("Permission Necessary");
                alertBuilder.setMessage("Storage permission is necessary");
                alertBuilder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                    public void onClick(DialogInterface dialog, int which) {
                        ActivityCompat.requestPermissions(getActivity(), new String[]{WRITE_EXTERNAL_STORAGE
                                , READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
                    }
                });
                AlertDialog alert = alertBuilder.create();
                alert.show();
                Log.e("", "permission denied, show dialog");
            } else {
                ActivityCompat.requestPermissions(getActivity(), new String[]{WRITE_EXTERNAL_STORAGE,
                        READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
            }
        } else {
            showFileChooser();
        }
    }
    private boolean checkPermission() {
        return ContextCompat.checkSelfPermission(getActivity().getApplicationContext(), WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(getActivity().getApplicationContext(), READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED;
    }

    private void showFileChooser() {
        String[] mimeTypes =
                {"application/msword", "application/vnd.openxmlformats-officedocument.wordprocessingml.document", // .doc & .docx
                        "application/vnd.ms-excel", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", // .xls & .xlsx
                        "application/pdf",
                        "application/jpg"};

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            intent.setType(mimeTypes.length == 1 ? mimeTypes[0] : "*/*");
            if (mimeTypes.length > 0) {
                intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);
            }
        } else {
            String mimeTypesStr = "";
            for (String mimeType : mimeTypes) {
                mimeTypesStr += mimeType + "|";
            }
            intent.setType(mimeTypesStr.substring(0, mimeTypesStr.length() - 1));
        }
        //starts new activity to select file and return data
        startActivityForResult(Intent.createChooser(intent, "Choose File to Upload.."), PICK_FILE_REQUEST);
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

    public void showDialog(final String msg, final Context context,
                           final String permission) {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
        alertBuilder.setCancelable(true);
        alertBuilder.setTitle("Permission necessary");
        alertBuilder.setMessage(msg + " permission is necessary");
        alertBuilder.setPositiveButton(android.R.string.yes,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        ActivityCompat.requestPermissions((Activity) context,
                                new String[]{permission},
                                MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                    }
                });
        AlertDialog alert = alertBuilder.create();
        alert.show();
    }

    public boolean checkPermissionREAD_EXTERNAL_STORAGE(
            final Context context) {
        int currentAPIVersion = Build.VERSION.SDK_INT;
        if (currentAPIVersion >= android.os.Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(context,
                    Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(
                        (Activity) context,
                        Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    showDialog("External storage", context,
                            Manifest.permission.READ_EXTERNAL_STORAGE);

                } else {
                    ActivityCompat
                            .requestPermissions(
                                    (Activity) context,
                                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                                    MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                }
                return false;
            } else {
                return true;
            }

        } else {
            return true;
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == PICK_FILE_REQUEST) {
                if (data == null) {
                    //no data present
                    return;
                }
                PowerManager powerManager = (PowerManager) getActivity().getSystemService(POWER_SERVICE);
                wakeLock = powerManager.newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK, TAG);
                wakeLock.acquire();
                if (checkPermissionREAD_EXTERNAL_STORAGE(getActivity())) {
                    // do your stuff..
                    Uri selectedFileUri = data.getData();
                    selectedFilePath = FileUtils.getRealPath(getActivity(), selectedFileUri);
                    Log.i(TAG, "Selected File Path:" + selectedFilePath);
                    File file = new File(selectedFilePath);
                    int file_size = Integer.parseInt(String.valueOf(file.length() / 1024));
                    fileSizeInKB = file_size;
                    Log.d(TAG, "File size  " + fileSizeInKB);
                    if (selectedFilePath != null && !selectedFilePath.equals("")) {
                        //filePath=selectedFilePath;
                        String[] parts = selectedFilePath.split("/");
                        final String filename = parts[parts.length - 1];
                        txtFileName.setText(filename);
                        //fileName = filename;
                        // tvFileName.setText(selectedFilePath);
                    } else {
                        Toast.makeText(getActivity(), "Cannot upload file to server", Toast.LENGTH_SHORT).show();
                    }
                }


            }
        }
    }

    public int uploadFile(final String selectedFilePath) {

        int serverResponseCode = 0;

        final HttpURLConnection connection;
        DataOutputStream dataOutputStream;
        String lineEnd = "\r\n";
        String twoHyphens = "--";
        String boundary = "*****";


        int bytesRead, bytesAvailable, bufferSize;
        byte[] buffer;
        int maxBufferSize = 1 * 1024 * 1024;
        File selectedFile = new File(selectedFilePath);
        String filename = selectedFilePath.substring(selectedFilePath.lastIndexOf("/") + 1);


        String[] parts = selectedFilePath.split("/");
        final String fileName = parts[parts.length - 1];
        /*String[] exten = fileName.split(Pattern.quote("."));
        String extension = exten[1];*/

        if (!selectedFile.isFile()) {
            // dialog.dismiss();

            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Snackbar.make(layout_view, "Source File Doesn't Exist: " + selectedFilePath, Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            });
            return 0;
        } else {
            try {
                String SERVER_URL = BASE_URL +"PostUpload";
                FileInputStream fileInputStream = new FileInputStream(selectedFile);
                String filePath = "";//String.valueOf(selectedFile);
                URL url = new URL(SERVER_URL + "?P_fileName=" + filename + "&P_DOCUMENT_SHORT_CODE=DIGITALDOCUMENT" + "&P_PERSON_TYPE=STUDENT&P_CENTRE_CODE=" + studCenterCode + "&P_EMPLOYEE_ID=" + student_id  + "&P_DOCPATH=" +filePath);
                connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);//Allow Inputs
                connection.setDoOutput(true);//Allow Outputs
                connection.setUseCaches(false);//Don't use a cached Copy
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Connection", "Keep-Alive");
                connection.setRequestProperty("ENCTYPE", "multipart/form-data");
                connection.setRequestProperty(
                        "Content-Type", "multipart/form-data;boundary=" + boundary);
                connection.setRequestProperty("uploaded_file", selectedFilePath);
                Log.d("Parameters : ", "selectedFilePath : " + selectedFilePath + "\n P_fileName : " + filename + "\n\n Upload URL : " + SERVER_URL + "?P_fileName=" + filename + "&P_DOCUMENT_SHORT_CODE=ASG" + "&P_PERSON_TYPE=STUDENT&P_CENTRE_CODE=" + studCenterCode + "&P_EMPLOYEE_ID=" + student_id +"&P_DOCPATH=" +filePath);
                fileDocName =filename;
                dataOutputStream = new DataOutputStream(connection.getOutputStream());
                //writing bytes to data outputstream
                dataOutputStream.writeBytes(twoHyphens + boundary + lineEnd);
                dataOutputStream.writeBytes("Content-Disposition: form-data; name=\"uploaded_file\";filename=\""
                        + selectedFilePath + "\"" + lineEnd);

                dataOutputStream.writeBytes(lineEnd);
                //returns no. of bytes present in fileInputStream
                bytesAvailable = fileInputStream.available();
                //selecting the buffer size as minimum of available bytes or 1 MB
                bufferSize = Math.min(bytesAvailable, maxBufferSize);
                //setting the buffer as byte array of size of bufferSize
                buffer = new byte[bufferSize];
                //reads bytes from FileInputStream(from 0th index of buffer to buffersize)
                bytesRead = fileInputStream.read(buffer, 0, bufferSize);
                //loop repeats till bytesRead = -1, i.e., no bytes are left to read
                while (bytesRead > 0) {

                    try {

                        //write the bytes read from inputstream
                        dataOutputStream.write(buffer, 0, bufferSize);
                    } catch (OutOfMemoryError e) {
                        Toast.makeText(getActivity(), "Insufficient Memory!", Toast.LENGTH_SHORT).show();
                    }
                    bytesAvailable = fileInputStream.available();
                    bufferSize = Math.min(bytesAvailable, maxBufferSize);
                    bytesRead = fileInputStream.read(buffer, 0, bufferSize);
                }

                dataOutputStream.writeBytes(lineEnd);
                dataOutputStream.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

                try {
                    serverResponseCode = connection.getResponseCode();
                } catch (OutOfMemoryError e) {
                    Toast.makeText(getActivity(), "Memory Insufficient!", Toast.LENGTH_SHORT).show();
                }
                String serverResponseMessage = connection.getResponseMessage();

                Log.i(TAG, "Server Response is: " + serverResponseMessage + ": " + serverResponseCode);

                //response code of 200 indicates the server status OK
                if (serverResponseCode == 200 || serverResponseCode == 201) {

                    Snackbar.make(layout_view, "File uploded successfully!", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                    BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    filePath = br.readLine();
                    //\\192.168.1.151\d\EmployeeDocuments\LeaveDoc\ENGG_SC\1039140001/demo.docx
                    Log.d(TAG, "BufferedReader Created : " + filePath);
                    String s,v;
                    String sourcePath =filePath;
                    String str = sourcePath.substring(sourcePath.lastIndexOf("\\") + 1);
                    String[] value = str.split(Pattern.quote("\\"));
                    s = value[0];
                    String path =s;
                    // String path ="http://103.96.41.134/SASHA.pdf";

                    funDocumPath(path);


                } else {
                    BufferedReader br = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                    String c = br.readLine();
                    Log.d(TAG, "BufferedReader Error : " + c);

                    Log.d("Response ", "Server response : " + serverResponseCode + " : " + serverResponseMessage);
                }

                //closing the input and output streams
                fileInputStream.close();
                dataOutputStream.flush();
                dataOutputStream.close();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(), "File Not Found", Toast.LENGTH_SHORT).show();
                    }
                });
            } catch (MalformedURLException e) {
                e.printStackTrace();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(), "URL Error!", Toast.LENGTH_SHORT).show();
                    }
                });

            } catch (IOException e) {
                e.printStackTrace();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(), "Cannot Read/Write File", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            return serverResponseCode;
        }
    }
    private void funDocumPath(String Path) {
       /* mProgressDialog.setMessage("Please wait loading uploaded assignments!");
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.show();*/

        // String url = BASE_URL + URLEndPoints.GetUploadFileP_URL + studCenterCode;
        String url =BASE_URL +URLEndPoints.getUpload_Doc_URL_DWallet(URLEndPoints.Constance_StudentCenterCode);
        Log.d(TAG, "URL : " + url);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, response.toString());
                try {

                    Gson gson = new Gson();

                    documPathModel = gson.fromJson(response, DocumPathModel.class);

                    if (documPathModel.getStatus() == 1) {

                        docuPathArrayList = (ArrayList<DocumPathModel.DtEmpsalAnnualBean>) documPathModel.getDtEmpsalAnnual();
                        if (docuPathArrayList.size() != 0 && docuPathArrayList != null) {

                            DOCU_UPLOAD_PATH = docuPathArrayList.get(0).getDOCU_UPLOAD_PATH();
                            UPLOAD_DOCU_PATH_DTLS_ID = docuPathArrayList.get(0).getUPLOAD_DOCU_PATH_DTLS_ID();
                            Log.d(TAG, DOCU_UPLOAD_PATH); //EmployeeDocuments\Assignment\Student\



                            String staticIp = docuPathArrayList.get(0).getSTATIC_IP();
                            String staticDOCPATH = docuPathArrayList.get(0).getDOCU_UPLOAD_STATIC_PATH();
                            FilepATH = Path;
                            COMPLETPATH =staticIp +staticDOCPATH +"/"+Path;

                            funSavefile();

                        } else {


                            Snackbar.make(layout_view, "Record not available", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        }
                    } else {
                        //  mProgressDialog.dismiss();

                        Snackbar.make(layout_view, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }


                } catch (Exception e) {
                    //dialog.hide();
                    Snackbar.make(layout_view, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    Log.d(TAG, "Error : " + e.getMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //dialog.hide();
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
    private void funSavefile() {
        utilityClassObj.startLoader(getActivity(),R.drawable.image_for_rotation);
        String url = BASE_URL + URLEndPoints.POSTSAVEDOCUMENT;

        Log.d(TAG, "URL : " + url);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d(TAG, response.toString());
                try {
                    utilityClassObj.stopLoader();
                    JSONObject responseObj = new JSONObject(response);

                    int status = responseObj.getInt("status");

                    if (status == 1) {
                        String STATUS = responseObj.getString("STATUS");
                        String REASON = responseObj.getString("REASON");

                        if (STATUS.equalsIgnoreCase("TRUE")) {


                        } else if (STATUS.equalsIgnoreCase("FALSE")) {
                            Snackbar.make(layout_view, REASON, Snackbar.LENGTH_LONG).setAction("Action", null).show();
                        }

                    }
                } catch (Exception e) {
                    utilityClassObj.stopLoader();
                    Snackbar.make(layout_view, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    Log.d(TAG, "Error : " + e.getMessage());

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                utilityClassObj.stopLoader();
                Log.e(TAG, "Error: " + error.getMessage());
                handleVolleyError(error);

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("PI_DOCUMENT_MST_ID",NameOfParam);
                params.put("PI_EMPLOYEE_ID", "0");
                params.put("PI_WORK_DESIG_CODE", "0");
                params.put("PI_DEPARTMENT_NUMBER", URLEndPoints.Constance_StudentDepartmentNumber);
                params.put("PI_CENTRE_CODE", URLEndPoints.Constance_StudentCenterCode);
                params.put("PI_SOFT_COPY_NAME", fileDocName);
                params.put("PI_SOFT_COPY_PATH", FilepATH);
                params.put("PI_SOFT_COPY_SIZE", "0");
                params.put("PI_REMARK", "0");
                params.put("PI_USER_ID", student_id);
                params.put("PI_USER_TYPE","S");
                params.put("PI_STUDENT_ID", student_id);
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

}
