package com.wordpro.studentproject.fragment;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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
import com.wordpro.studentproject.adapter.D_Wallet_DownloadDetails_Adapter;
import com.wordpro.studentproject.adapter.D_wallet_download_List_Adapter;
import com.wordpro.studentproject.adapter.NetAttendanceAdpter;
import com.wordpro.studentproject.app.MyApplication;
import com.wordpro.studentproject.helper.PrefManager;
import com.wordpro.studentproject.model.DocDownloadDetailsModel;
import com.wordpro.studentproject.model.DocumentListDetails;
import com.wordpro.studentproject.model.DownloadDocumentList;
import com.wordpro.studentproject.model.ListDocDownloadModel;
import com.wordpro.studentproject.utils.UtilityClass;
import com.wordpro.studentproject.webConfig.URLEndPoints;

import java.util.List;
import java.util.regex.Pattern;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static com.wordpro.studentproject.activities.NavigationActivity.BASE_URL;
import static com.wordpro.studentproject.fragment.E_Lear_AssignmentFragment.MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE;


public class D_Wallet_Download_DetailsFragment extends Fragment {
    private static String TAG = D_Wallet_Download_DetailsFragment.class.getSimpleName();
    private LinearLayout  main_linear_view;


    private String  NameOfParam;
    private static final int PERMISSION_REQUEST_CODE = 200;
    private static final int PICK_FILE_REQUEST = 1;
    PowerManager.WakeLock wakeLock;
    private final int REQUEST_READ_PHONE_STATE = 1;
    private String selectedFilePath;
    long fileSizeInKB;
    private ImageView back_press;
    private PrefManager pref;

    private RelativeLayout  lytAttndence;
    private RecyclerView download_list;
    public DocDownloadDetailsModel downloadDocModel;
    public List<ListDocDownloadModel> documentListDtls;
    private D_Wallet_DownloadDetails_Adapter d_wallet_downloadDetails_adapter;



    public static NetAttendanceAdpter netAttendanceAdpter;

    public Fragment fragment;


    private UtilityClass utilityClassObj;
    public D_Wallet_Download_DetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        final View view = inflater.inflate(R.layout.d_download_layout, container, false);
        Typeface type_faceHeading = Typeface.createFromAsset(getActivity().getAssets(), "font/Poppins-Bold.ttf");
        Typeface type_faceContent = Typeface.createFromAsset(getActivity().getAssets(), "font/Poppins-Regular.ttf");
        pref = new PrefManager(getActivity());
        utilityClassObj  = new  UtilityClass(getActivity());

        lytAttndence = (RelativeLayout)view.findViewById(R.id.lytAttndence);
        back_press =(ImageView)view.findViewById(R.id.back_press);
        download_list= (RecyclerView) view.findViewById(R.id.download_list);
        try {

            String MST_ID_value = getArguments().getString("MST_ID");
            GetDownloadUrl(MST_ID_value,URLEndPoints.Constance_StudentID);
        }catch (Exception e){
            e.printStackTrace();
        }


        back_press .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

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



    private void GetDownloadUrl(String MST_ID,String UserID) {

        utilityClassObj.startLoader(getActivity(),R.drawable.image_for_rotation);
        String url = BASE_URL + URLEndPoints.GetDocumentdetailsURL(MST_ID,UserID);
        Log.d(TAG, "URL : " + url);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d(TAG, response.toString());

                try {
                    Gson gson=new Gson();

                    downloadDocModel =gson.fromJson(response,DocDownloadDetailsModel.class);
                    utilityClassObj.stopLoader();
                    if(downloadDocModel.getStatus()==1){

                        documentListDtls=(downloadDocModel.getDocumentListDtls());
                        if(documentListDtls!=null && documentListDtls.size()!=0){

                            d_wallet_downloadDetails_adapter = new D_Wallet_DownloadDetails_Adapter(getActivity(), documentListDtls);
                            download_list.setLayoutManager(new LinearLayoutManager(getActivity()));
                            download_list.setAdapter(d_wallet_downloadDetails_adapter);
                            d_wallet_downloadDetails_adapter.notifyDataSetChanged();

                            d_wallet_downloadDetails_adapter.setOnItemClickListener(new D_Wallet_DownloadDetails_Adapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(View itemView, int position) {
                                    try {
                                        String strfileName = documentListDtls.get(position).getSOFTCOPYPATH();
                                        String fileName, Extent;
                                        String ORG,Path;
                                        String[] filenameNextension = strfileName.split(Pattern.quote("."));
                                        String originalfileName = filenameNextension[0];

                                        //"\\\\" +
                                        String sourcePath = documentListDtls.get(position).getSOFTCOPYPATH();
                                        String str = sourcePath.substring(sourcePath.lastIndexOf("\\") + 1);
                                        String[] value = str.split(Pattern.quote("."));
                                        fileName = value[0];
                                        Extent = value[1];

                                        if (documentListDtls.get(position).getSTATICIP() != null) {
                                            String uu ="";
                                            try {
                                                uu =  documentListDtls.get(position).getSTATICIP()+"/" +documentListDtls.get(position).getDOCUUPLOADSTATICPATH()+sourcePath;
                                            }catch (Exception e){
                                                e.printStackTrace();
                                            }
                                            String path ="http://"+uu;
                                            funDownloadFile(path,Extent);

                                        }else {
                                            Snackbar.make(lytAttndence, "StaticIp not available.", Snackbar.LENGTH_LONG)
                                                    .setAction("Action", null).show();
                                        }
                                        /*String[] colon = sourcePath.split(Pattern.quote(":"));
                                        ORG= colon[0];
                                        Path= colon[1];


                                        String[] data = fileName.split(Pattern.quote("_"));
                                        String employeeId = data[0];
                                        employeeId = employeeId.substring(1);

                                        String sourcePathsplit = "\\\\" + documentListDtls.get(position).getSOFTCOPYPATH();
                                        String[] source = sourcePathsplit.split(Pattern.quote("Employee\\"));
                                        String originalSource = source[0] + "Employee\\" + employeeId;

                                        String comUrl = BASE_URL;          //http://117.247.82.252:500/api/
                                        String[] s = comUrl.split(Pattern.quote("api"));
                                        String downloadURL = s[0];


                                        String test = documentListDtls.get(position).getSOFTCOPYPATH();

                                        String[] sou = test.split(Pattern.quote("\\"));
                                        String origin = sou[1];
                                        String uu ="";
                                        String q,w;
                                        String str1 = test.substring(test.lastIndexOf("\\") + 1);
                                        String[] filenameNextension1 = str1.split(Pattern.quote("."));
                                        q = filenameNextension1[0];
                                        w = filenameNextension1[1];
                                        String a =q+w;*/

                                    }catch (Exception e){
                                        Snackbar.make(lytAttndence, "StaticIp not available.", Snackbar.LENGTH_LONG)
                                                .setAction("Action", null).show();
                                        e.printStackTrace();
                                    }






                                }
                            });


                        }else{
                            Snackbar.make(lytAttndence, "Record not available.", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        }

                    }else if(downloadDocModel.getStatus()==0){

                        Snackbar.make(lytAttndence, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }

                } catch (Exception e) {
                    utilityClassObj.stopLoader();
                    Snackbar.make(lytAttndence, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    Log.d(TAG, "Error : " + e.getMessage());


                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                utilityClassObj.stopLoader();
                handleVolleyError(error);
                Log.e(TAG, "Error: " + error.getMessage());
                Snackbar.make(lytAttndence, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                60000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

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
    private void funDownloadFile(String originalfilename,String fileformat) {

        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(originalfilename));

        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.HONEYCOMB){
            request.allowScanningByMediaScanner();
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);

        }
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, originalfilename);
        DownloadManager downloadManager =(DownloadManager)getActivity().getSystemService(Context.DOWNLOAD_SERVICE);
        request.setMimeType("application/"+fileformat);
        //request.setMimeType("application/pdf");
        request.allowScanningByMediaScanner();
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);
        downloadManager.enqueue(request);

        Snackbar.make(lytAttndence, "File downloaded succesfully", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

}
