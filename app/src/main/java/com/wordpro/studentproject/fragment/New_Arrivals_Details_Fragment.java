package com.wordpro.studentproject.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.wordpro.studentproject.activities.Library.NewArrivalMonthActivity;
import com.wordpro.studentproject.activities.Library.NewArrivalsActivity;
import com.wordpro.studentproject.adapter.NewArrivalBookDtlAdpter;
import com.wordpro.studentproject.adapter.New_Arrivals_List_Adapter;
import com.wordpro.studentproject.app.MyApplication;
import com.wordpro.studentproject.helper.PrefManager;
import com.wordpro.studentproject.model.NewArrivalBookModel;
import com.wordpro.studentproject.utils.UtilityClass;
import com.wordpro.studentproject.webConfig.URLEndPoints;

import java.util.ArrayList;

import static com.wordpro.studentproject.activities.NavigationActivity.BASE_URL;
import static com.wordpro.studentproject.activities.NavigationActivity.studCenterCode;

public class New_Arrivals_Details_Fragment extends Fragment {

    private PrefManager pref;
    public static String TAG = New_Arrivals_Details_Fragment.class.getSimpleName();
    private TextView  txt_view;
    private String  date_1,date_2;
    public static NewArrivalBookDtlAdpter newArrivalBookDtlAdpter;
    private LinearLayout layout_error;
    private UtilityClass utilityClassObj;
    //NewArrivalBookModel
    public static NewArrivalBookModel newArrivalBookModel;
    private RecyclerView recycle_view_arrivals;
    public static ArrayList<NewArrivalBookModel.LibBokNewArrDtlsBean> arrivalBookDataList;
    public New_Arrivals_Details_Fragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.new_arrivals_fragment, container, false);
        pref = new PrefManager(getActivity());
        utilityClassObj  = new  UtilityClass(getActivity());
        date_1= getArguments().getString("date1");
        date_2= getArguments().getString("date2");
        txt_view =(TextView)view.findViewById(R.id.txt_view);
        recycle_view_arrivals =(RecyclerView)view.findViewById(R.id.recycle_view_arrivals);
        layout_error =(LinearLayout)view .findViewById(R.id.layout_error);
        txt_view.setVisibility(View.GONE);

        Log.d(TAG, date_1);
        Log.d(TAG, date_2);
        arrivalDetailsCall();
        return view;

    }


    private void arrivalDetailsCall(){
        utilityClassObj.startLoader(getActivity(),R.drawable.image_for_rotation);
        String url =BASE_URL+ URLEndPoints.getNewArrivalsDetails_URL(studCenterCode,date_1,date_2);
        Log.d(TAG,"URL : "+url);

        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d(TAG,"Response : "+response.toString());

                try {

                    Gson gson=new Gson();
                    newArrivalBookModel=gson.fromJson(response,NewArrivalBookModel.class);
                    utilityClassObj.stopLoader();
                    if(newArrivalBookModel.getStatus()==1){

                        arrivalBookDataList=(ArrayList<NewArrivalBookModel.LibBokNewArrDtlsBean>)newArrivalBookModel.getLibBokNewArrDtls();
                        if(arrivalBookDataList.size()!=0 && arrivalBookDataList!=null){

                            newArrivalBookDtlAdpter=new NewArrivalBookDtlAdpter(getActivity(),arrivalBookDataList);
                            recycle_view_arrivals.setLayoutManager(new LinearLayoutManager(getActivity()));
                            recycle_view_arrivals.setAdapter(newArrivalBookDtlAdpter);

                        }else{
                            Snackbar.make(layout_error, "Record not available", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        }

                    }else {
                        Snackbar.make(layout_error, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }



                }catch (Exception e){
                    utilityClassObj.stopLoader();
                    Snackbar.make(layout_error, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
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
