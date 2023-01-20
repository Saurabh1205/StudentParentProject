package com.wordpro.studentproject.fragment;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.wordpro.studentproject.adapter.Assignment_List_Adapter;
import com.wordpro.studentproject.adapter.D_wallet_download_List_Adapter;
import com.wordpro.studentproject.adapter.NetAttendanceAdpter;
import com.wordpro.studentproject.adapter.SpanDayWiseAttendanceAdpter;
import com.wordpro.studentproject.app.MyApplication;
import com.wordpro.studentproject.helper.PrefManager;
import com.wordpro.studentproject.model.DocumentListDetails;
import com.wordpro.studentproject.model.DownloadDocumentList;
import com.wordpro.studentproject.utils.UtilityClass;
import com.wordpro.studentproject.webConfig.URLEndPoints;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static com.wordpro.studentproject.R.id.frame_container_Action;
import static com.wordpro.studentproject.activities.NavigationActivity.BASE_URL;


public class D_Wallet_DownloadFragment extends Fragment {
    private static String TAG = D_Wallet_DownloadFragment.class.getSimpleName();
    private LinearLayout  main_linear_view;

    D_Wallet_DownloadFragment actionNewsFragment;
    private String  NameOfParam;


    private PrefManager pref;

    private RelativeLayout  lytAttndence;
    private RecyclerView download_list;
    public DownloadDocumentList downloadDocModel;
    public List<DocumentListDetails> documentListDtls;
    private  D_wallet_download_List_Adapter d_wallet_download_list_adapter;




    public Fragment fragment;


    private UtilityClass utilityClassObj;
    public D_Wallet_DownloadFragment() {
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
        try {
            NameOfParam=getArguments().getString("Download");


        }catch (Exception e){
            e.printStackTrace();
        }

        lytAttndence = (RelativeLayout)view.findViewById(R.id.lytAttndence);

        download_list= (RecyclerView) view.findViewById(R.id.download_list);


        GetDownloadUrl();

      // getStartEndDate("NET_ATTENDANCE");
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


    private void GetDownloadUrl() {

        utilityClassObj.startLoader(getActivity(),R.drawable.image_for_rotation);
        String url = BASE_URL + URLEndPoints.GetDocumentListURL("0","S",URLEndPoints.Constance_StudentID);
        Log.d(TAG, "URL : " + url);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d(TAG, response.toString());

                try {
                    Gson gson=new Gson();

                    downloadDocModel =gson.fromJson(response,DownloadDocumentList.class);

                    utilityClassObj.stopLoader();
                    if(downloadDocModel.getStatus()==1){

                        documentListDtls=(downloadDocModel.getDocumentListDtls());
                        if(documentListDtls!=null && documentListDtls.size()!=0){

                            d_wallet_download_list_adapter = new D_wallet_download_List_Adapter(getActivity(), documentListDtls ,"download");
                            download_list.setLayoutManager(new LinearLayoutManager(getActivity()));
                            download_list.setAdapter(d_wallet_download_list_adapter);
                            d_wallet_download_list_adapter.notifyDataSetChanged();


                            d_wallet_download_list_adapter.setOnItemClickListener(new D_wallet_download_List_Adapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(View itemView, int position, String download) {
                                    Snackbar.make(lytAttndence, download, Snackbar.LENGTH_LONG)
                                            .setAction("Action", null).show();

                                    fragment = new D_Wallet_Download_DetailsFragment();
                                    Bundle args = new Bundle();
                                    args.putString("MST_ID", documentListDtls.get(position).getdOCUMENTMSTID());
                                    fragment.setArguments(args);

                                    loadFragmentMain(fragment);

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
    private void loadFragmentMain(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container_e_ler, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}
