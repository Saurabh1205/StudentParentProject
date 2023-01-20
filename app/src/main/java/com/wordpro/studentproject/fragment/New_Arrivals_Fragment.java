package com.wordpro.studentproject.fragment;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
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
import com.wordpro.studentproject.activities.Library.NewArrivalMonthActivity;
import com.wordpro.studentproject.adapter.NetWiseAttendanceAdpter;
import com.wordpro.studentproject.adapter.NewArrivalAdpter;
import com.wordpro.studentproject.adapter.New_Arrivals_List_Adapter;
import com.wordpro.studentproject.app.MyApplication;
import com.wordpro.studentproject.helper.PrefManager;
import com.wordpro.studentproject.model.NewArrvlMnthModel;
import com.wordpro.studentproject.utils.UtilityClass;
import com.wordpro.studentproject.webConfig.URLEndPoints;

import java.util.ArrayList;
import java.util.regex.Pattern;

import static com.wordpro.studentproject.activities.NavigationActivity.BASE_URL;
import static com.wordpro.studentproject.activities.NavigationActivity.studCenterCode;
import static com.wordpro.studentproject.fragment.DashboardFragment.libraryFragment;

public class New_Arrivals_Fragment  extends Fragment {

    private PrefManager pref;
    public static String TAG = New_Arrivals_Fragment.class.getSimpleName();
    TextView  txt_view;
    private RecyclerView recycle_view_arrivals;
    private New_Arrivals_List_Adapter new_arrivals_list_adapter;
    public static NewArrvlMnthModel newArrvlMnthModel;
    public static ArrayList<NewArrvlMnthModel.LibBokNewArrAbstBean> newArrvlMnthArraylist;
    public static NewArrivalAdpter newArrivalAdpter;
    private LinearLayout  layout_error;
    public static Fragment fragment;
    private UtilityClass utilityClassObj;
    public  New_Arrivals_Fragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.new_arrivals_fragment, container, false);
        pref = new PrefManager(getActivity());
        utilityClassObj  = new  UtilityClass(getActivity());
        String value = getArguments().getString("Arrivals");
        txt_view =(TextView)view.findViewById(R.id.txt_view);
        recycle_view_arrivals =(RecyclerView)view.findViewById(R.id.recycle_view_arrivals);
        layout_error =(LinearLayout)view.findViewById(R.id.layout_error);
        txt_view.setText("New Arrivals");
        txt_view.setVisibility(View.GONE);
        Log.d(TAG, value);
        Arrival_data();
        return view;

    }

    private void Arrival_data(){
        utilityClassObj.startLoader(getActivity(),R.drawable.image_for_rotation);
        String url = BASE_URL + URLEndPoints.GetLibBokNewArrAbst_URL+ studCenterCode;
        Log.d(TAG, url);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, response.toString());
                try {

                    Gson gson = new Gson();
                    newArrvlMnthModel = gson.fromJson(response, NewArrvlMnthModel.class);
                    utilityClassObj.stopLoader();
                    if (newArrvlMnthModel.getStatus() == 1) {

                        newArrvlMnthArraylist = (ArrayList<NewArrvlMnthModel.LibBokNewArrAbstBean>) newArrvlMnthModel.getLibBokNewArrAbst();

                        if (newArrvlMnthArraylist.size() != 0 && newArrvlMnthArraylist != null) {

                           // mProgressDialog.dismiss();
                            new_arrivals_list_adapter = new New_Arrivals_List_Adapter(getActivity(),newArrvlMnthArraylist);
                            recycle_view_arrivals.setLayoutManager(new LinearLayoutManager(getActivity()));
                            recycle_view_arrivals.setAdapter(new_arrivals_list_adapter);
                            new_arrivals_list_adapter.setOnItemClickListener(new New_Arrivals_List_Adapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(View itemView, int position) {

                                    String FROM_DATE = newArrvlMnthArraylist.get(position).getStart_Day_Month();
                                    String[] v1 = FROM_DATE.split(Pattern.quote(" "));
                                    String[] d1 = v1[0].split(Pattern.quote("/"));
                                    String date1 = d1[1] + "/" + d1[0] + "/" + d1[2];

                                    String UPTO_DATE = newArrvlMnthArraylist.get(position).getLast_Day_Month();
                                    String[] v2 = UPTO_DATE.split(Pattern.quote(" "));
                                    String[] d2 = v2[0].split(Pattern.quote("/"));
                                    String date2 = d2[1] + "/" + d2[0] + "/" + d2[2];

                                    fragment = new New_Arrivals_Details_Fragment();
                                    Bundle bundle=new Bundle();
                                    bundle.putString("date1", date1);
                                    bundle.putString("date2", date2);
                                    fragment.setArguments(bundle);
                                    loadFragment(fragment);

                                }
                            });
                        } else {
                           // mProgressDialog.dismiss();
                            Snackbar.make(layout_error, "Records not available.", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        }


                    } else {
                        //mProgressDialog.dismiss();
                        Snackbar.make(layout_error, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }


                } catch (Exception e) {
                    utilityClassObj.stopLoader();
                    Snackbar.make(layout_error, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                    Log.d(TAG, "Error : " + e.getMessage());

                   // mProgressDialog.dismiss();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                utilityClassObj.stopLoader();
                handleVolleyError(error);

              //  mProgressDialog.dismiss();
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

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.library_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
