package com.wordpro.studentproject.fragment;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
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
import com.wordpro.studentproject.activities.Library.FineActivity;
import com.wordpro.studentproject.activities.Library.IssueBookActivity;
import com.wordpro.studentproject.activities.Library.NewArrivalMonthActivity;
import com.wordpro.studentproject.activities.Library.ReserveActivity;
import com.wordpro.studentproject.activities.Library.RulesDescrptnActivity;
import com.wordpro.studentproject.activities.Library.SearchBookActivity;
import com.wordpro.studentproject.activities.Library.SuggestionActivity;
import com.wordpro.studentproject.adapter.BookFineAdpter;
import com.wordpro.studentproject.adapter.BookReserveAdptr;
import com.wordpro.studentproject.adapter.NewArrivalAdpter;
import com.wordpro.studentproject.adapter.RulesAdpter;
import com.wordpro.studentproject.app.MyApplication;
import com.wordpro.studentproject.model.BookReservtnModel;
import com.wordpro.studentproject.model.FineModel;
import com.wordpro.studentproject.model.NewArrvlMnthModel;
import com.wordpro.studentproject.model.RulesModel;
import com.wordpro.studentproject.webConfig.URLEndPoints;

import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.ArrayList;

import static com.wordpro.studentproject.activities.NavigationActivity.BASE_URL;
import static com.wordpro.studentproject.activities.NavigationActivity.studBranchStandardId;
import static com.wordpro.studentproject.activities.NavigationActivity.studCenterCode;
import static com.wordpro.studentproject.activities.NavigationActivity.studSemesterType;
import static com.wordpro.studentproject.activities.NavigationActivity.studSessionId;
import static com.wordpro.studentproject.activities.NavigationActivity.stud_main_semester_mst_id;
import static com.wordpro.studentproject.activities.NavigationActivity.student_id;
import static com.wordpro.studentproject.fragment.DashboardFragment.libraryFragment;
import static com.wordpro.studentproject.network.Network.isNetworkAvailable;

/**
 * A simple {@link Fragment} subclass.
 */
public class LibraryFragment extends DialogFragment {

    private static String TAG = LibraryFragment.class.getSimpleName();
    private LinearLayout linearLayout1, linearLayout2, linearLayout3, linearLayout4, linearLayout5, linearLayout6, linearLayout7;
    TextView txt1,txt2,txt3,txt4,txt5,txt6,txt7,txt8;
    LinearLayout libraryLayout;
    ProgressDialog mProgressDialog;

    public static RulesModel rulesModel;
    public static ArrayList<RulesModel.CirculationRuleDtlsBean> circulationRuleDtlsArraylist;
    public static RulesAdpter rulesAdpter;
    public static ArrayList<String> data;

    public static BookReservtnModel bookReservtnModel;
    public static ArrayList<BookReservtnModel.LibBokReserStatusBean> bookReservArraylist;
    public static BookReserveAdptr bookReserveAdptr;

    //NewArrvlMnthModel
    public static NewArrvlMnthModel newArrvlMnthModel;
    public static ArrayList<NewArrvlMnthModel.LibBokNewArrAbstBean> newArrvlMnthArraylist;
    public static NewArrivalAdpter newArrivalAdpter;

    //FineModel
    public static FineModel fineModel;
    public static ArrayList<FineModel.BookFineDetailsBean> bookFineDetailsArrayList;
    public static BookFineAdpter bookFineAdpter;

    public LibraryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       // return inflater.inflate(R.layout.fragment_library, container, false);

        View view = inflater.inflate(R.layout.fragment_library, container);
        mProgressDialog = new ProgressDialog(getActivity());

        Typeface typefaceHeading = Typeface.createFromAsset(getActivity().getAssets(), "font/Poppins-BoldItalic.otf");
        Typeface typefaceContent = Typeface.createFromAsset(getActivity().getAssets(), "font/Poppins-Medium.otf");

        libraryLayout = (LinearLayout) view.findViewById(R.id.libraryLayout);

        linearLayout1 = (LinearLayout) view.findViewById(R.id.linearLayout1);
        // linearLayout1.setVisibility(View.GONE);
        linearLayout2 = (LinearLayout) view.findViewById(R.id.linearLayout2);
        //  linearLayout2.setVisibility(View.GONE);
        linearLayout3 = (LinearLayout) view.findViewById(R.id.linearLayout3);
        //  linearLayout3.setVisibility(View.GONE);
        linearLayout4 = (LinearLayout) view.findViewById(R.id.linearLayout4);
        //  linearLayout4.setVisibility(View.GONE);
        linearLayout5 = (LinearLayout) view.findViewById(R.id.linearLayout5);
        //  linearLayout5.setVisibility(View.GONE);
        linearLayout6 = (LinearLayout) view.findViewById(R.id.linearLayout6);
        // linearLayout6.setVisibility(View.GONE);
        linearLayout7 = (LinearLayout) view.findViewById(R.id.linearLayout7);
        // linearLayout7.setVisibility(View.GONE);

        txt1=(TextView)view.findViewById(R.id.txt1);
        txt1.setTypeface(typefaceHeading);
        txt2=(TextView)view.findViewById(R.id.txt2);
        txt2.setTypeface(typefaceContent);
        txt3=(TextView)view.findViewById(R.id.txt3);
        txt3.setTypeface(typefaceContent);
        txt4=(TextView)view.findViewById(R.id.txt4);
        txt4.setTypeface(typefaceContent);
        txt5=(TextView)view.findViewById(R.id.txt5);
        txt5.setTypeface(typefaceContent);
        txt6=(TextView)view.findViewById(R.id.txt6);
        txt6.setTypeface(typefaceContent);
        txt7=(TextView)view.findViewById(R.id.txt7);
        txt7.setTypeface(typefaceContent);
        txt8=(TextView)view.findViewById(R.id.txt8);
        txt8.setTypeface(typefaceContent);



        linearLayout1.setOnClickListener(new View.OnClickListener() {
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
                                            Toast.makeText(getActivity(), "Please check your internt", Toast.LENGTH_SHORT).show();
                                            // Restart the Activity
                                            /*Intent intent = getIntent();
                                            finish();
                                            startActivity(intent);*/
                                        }
                                    });
                    AlertDialog alert = builder.create();
                    alert.show();
                } else {
                    mProgressDialog.setMessage("Please wait Loading");
                    mProgressDialog.setCanceledOnTouchOutside(false);
                    mProgressDialog.show();

                    String url=BASE_URL+ URLEndPoints.GetCirculationRule_URL(studCenterCode);
                    Log.d(TAG,"URL : "+url);

                    StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.d(TAG,"RESPONSE : "+response);

                            try {

                                Gson gson=new Gson();
                                rulesModel=gson.fromJson(response,RulesModel.class);

                                if(rulesModel.getStatus()==1){
                                    mProgressDialog.dismiss();

                                    circulationRuleDtlsArraylist=(ArrayList<RulesModel.CirculationRuleDtlsBean>)rulesModel.getCirculationRuleDtls();
                                    if(circulationRuleDtlsArraylist.size()!=0 && circulationRuleDtlsArraylist!=null){

                                        data=new ArrayList<>();

                                        for(int i=0;i<circulationRuleDtlsArraylist.size();i++) {

                                            if (i == 0) {
                                              /*  String LIBRARY_NAME = circulationRuleDtlsArraylist.get(i).getLIBRARYNAME();
                                                String BOOK_RULE_DESC = circulationRuleDtlsArraylist.get(i).getBOOKRULEDESC();
                                                String MAX_BOOK_ALLOWED = circulationRuleDtlsArraylist.get(i).getConsolTotBooks();

                                                String rule1 = "The circulation rules of " + LIBRARY_NAME + " are described under rule " + BOOK_RULE_DESC;
                                                data.add(rule1);
                                                String rule2 = "Maximum " + MAX_BOOK_ALLOWED + "  books can be issued from the " + LIBRARY_NAME;
                                                data.add(rule2);
                                                String rule3 = "Its mandatory to return the issued book upto the expected return date.If a person fails to return the issued book on the expected return date ,then the fine will be applicable based on the Fine Slab";
                                                data.add(rule3);*/


                                            }
                                        }


                                        for(int i=0;i<circulationRuleDtlsArraylist.size();i++) {

                                          /*  String BoKTyp_BokAllowed=circulationRuleDtlsArraylist.get(i).getBoKTypBokAllowed();
                                            String MAX_DAYS_ALLOWED=circulationRuleDtlsArraylist.get(i).getMAXDAYSALLOWED();
                                            String BOOK_TYPE_DESCRIPTION=circulationRuleDtlsArraylist.get(i).getBOOKTYPEDESCRIPTION();
                                            String SLAB_DESC=circulationRuleDtlsArraylist.get(i).getSLABDESC();
*/
                                         /*   String rule="Maximum "+BoKTyp_BokAllowed +" "+BOOK_TYPE_DESCRIPTION+" type books are allowed for maximum "+MAX_DAYS_ALLOWED+" days. If the issued books are not returned within the expected return date to the Library, then Fine Slab for "+BOOK_TYPE_DESCRIPTION+" book type is "+SLAB_DESC+".";
                                            data.add(rule);*/


                                        }

                                        Object[] st1 = data.toArray();
                                        for (Object s : st1) {
                                            if (data.indexOf(s) != data.lastIndexOf(s)) {
                                                data.remove(data.lastIndexOf(s));
                                            }
                                        }

                                        rulesAdpter=new RulesAdpter(getActivity(),data);
                                        Intent intent=new Intent(getActivity(),RulesDescrptnActivity.class);
                                        startActivity(intent);
                                        getActivity().finish();

                                    }else{
                                        Snackbar.make(libraryLayout, "Record not available.", Snackbar.LENGTH_LONG)
                                                .setAction("Action", null).show();
                                    }

                                }else if(rulesModel.getStatus()==0){
                                    mProgressDialog.dismiss();
                                    Snackbar.make(libraryLayout, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
                                            .setAction("Action", null).show();
                                }


                            }catch (Exception e) {

                                mProgressDialog.hide();
                                Log.d(TAG,"Error : "+e.getMessage());
                                Snackbar.make(libraryLayout, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
                                        .setAction("Action", null).show();

                            }

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                            handleVolleyError(error);
                            mProgressDialog.dismiss();

                        }
                    });


                    stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                            60000,
                            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

                    // Adding request to request queue

                    MyApplication.getInstance().addToRequestQueue(stringRequest);

                }

            }
        });


        linearLayout2.setOnClickListener(new View.OnClickListener() {
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
                                            Toast.makeText(getActivity(), "Please check your internt", Toast.LENGTH_SHORT).show();
                                            // Restart the Activity
                                            /*Intent intent = getIntent();
                                            finish();
                                            startActivity(intent);*/
                                        }
                                    });
                    AlertDialog alert = builder.create();
                    alert.show();
                }else {
                    mProgressDialog.setMessage("Please wait loading!");
                    mProgressDialog.setCanceledOnTouchOutside(false);
                    mProgressDialog.show();

                    String url = BASE_URL+ URLEndPoints.getPeriodDates_URL(studBranchStandardId,stud_main_semester_mst_id,studSessionId);
                    Log.d(TAG, "Start and End Date : " + url);

                    StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.d(TAG, response.toString());
                            try {

                                JSONObject responseObj = new JSONObject(response);

                                int status = responseObj.getInt("status");
                                if (status == 1) {

                                    String SESSION_ID = responseObj.getString("SESSION_ID");
                                    String PERIOD_START_DATE = responseObj.getString("PERIOD_START_DATE");
                                    String PERIOD_END_DATE = responseObj.getString("PERIOD_END_DATE");

                                    if (PERIOD_START_DATE != null && PERIOD_END_DATE != null) {

                                        Intent intent=new Intent(getActivity(),IssueBookActivity.class);
                                        intent.putExtra("fromDate", PERIOD_START_DATE);
                                        intent.putExtra("uptoDate", PERIOD_END_DATE);
                                        startActivity(intent);
                                        libraryFragment.dismiss();
                                        getActivity().finish();

                                    } else {
                                        Snackbar snackbar = Snackbar
                                                .make(libraryLayout, "Record not found.", Snackbar.LENGTH_LONG);
                                        snackbar.show();

                                    }

                                    mProgressDialog.dismiss();

                                } else if (status == 0) {
                                    Snackbar.make(libraryLayout, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
                                            .setAction("Action", null).show();
                                    mProgressDialog.dismiss();

                                }


                            } catch (Exception e) {

                                Snackbar.make(libraryLayout, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
                                        .setAction("Action", null).show();

                                Log.d(TAG, "Error : " + e.getMessage());

                                mProgressDialog.dismiss();


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



            }
        });

        linearLayout3.setOnClickListener(new View.OnClickListener() {
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
                                            Toast.makeText(getActivity(), "Please check your internt", Toast.LENGTH_SHORT).show();
                                            // Restart the Activity
                                            /*Intent intent = getIntent();
                                            finish();
                                            startActivity(intent);*/
                                        }
                                    });
                    AlertDialog alert = builder.create();
                    alert.show();
                }
                else {
                    mProgressDialog.setMessage("Please wait loading");
                    mProgressDialog.setCanceledOnTouchOutside(false);
                    mProgressDialog.show();

                    String url = BASE_URL + URLEndPoints.GetLibBokNewArrAbst_URL+ studCenterCode;
                    Log.d(TAG, url);

                    StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.d(TAG, response.toString());
                            try {

                                Gson gson = new Gson();
                                newArrvlMnthModel = gson.fromJson(response, NewArrvlMnthModel.class);

                                if (newArrvlMnthModel.getStatus() == 1) {

                                    newArrvlMnthArraylist = (ArrayList<NewArrvlMnthModel.LibBokNewArrAbstBean>) newArrvlMnthModel.getLibBokNewArrAbst();

                                    if (newArrvlMnthArraylist.size() != 0 && newArrvlMnthArraylist != null) {

                                        mProgressDialog.dismiss();

                                        newArrivalAdpter = new NewArrivalAdpter(getActivity(), newArrvlMnthArraylist);

                                        Intent intent = new Intent(getActivity(), NewArrivalMonthActivity.class);
                                        startActivity(intent);
                                        libraryFragment.dismiss();
                                        getActivity().finish();


                                    } else {
                                        mProgressDialog.dismiss();
                                        Snackbar.make(libraryLayout, "Records not available.", Snackbar.LENGTH_LONG)
                                                .setAction("Action", null).show();
                                    }


                                } else {
                                    mProgressDialog.dismiss();
                                    Snackbar.make(libraryLayout, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
                                            .setAction("Action", null).show();
                                }


                            } catch (Exception e) {

                                Snackbar.make(libraryLayout, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
                                        .setAction("Action", null).show();

                                Log.d(TAG, "Error : " + e.getMessage());

                                mProgressDialog.dismiss();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                            handleVolleyError(error);

                            mProgressDialog.dismiss();
                        }
                    });

                    stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                            60000,
                            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

                    // Adding request to request queue

                    MyApplication.getInstance().addToRequestQueue(stringRequest);

                }


            }
        });


        linearLayout4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SearchBookActivity.class);
                intent.putExtra("PI_BOOK_TITLE", "");
                intent.putExtra("PI_AUTHOR_NAME", "");
                intent.putExtra("PI_PUBLICATION_NAME", "");
                intent.putExtra("PI_TOPIC_NAME", "");
                startActivity(intent);
                libraryFragment.dismiss();
                getActivity().finish();
            }
        });

        linearLayout5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), SuggestionActivity.class);
                startActivity(intent);
                libraryFragment.dismiss();
                getActivity().finish();

            }
        });

        linearLayout6.setOnClickListener(new View.OnClickListener() {
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
                                            Toast.makeText(getActivity(), "Please check your internt", Toast.LENGTH_SHORT).show();
                                            // Restart the Activity
                                            /*Intent intent = getIntent();
                                            finish();
                                            startActivity(intent);*/
                                        }
                                    });
                    AlertDialog alert = builder.create();
                    alert.show();
                }
                else {
                    mProgressDialog.setMessage("Please wait loading.");
                    mProgressDialog.setCanceledOnTouchOutside(false);
                    mProgressDialog.dismiss();

                    String url= BASE_URL+ URLEndPoints.GetLibBokReserStatus_URL(student_id, studSessionId, studSemesterType);
                    Log.d(TAG,url);


                    StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            Log.d(TAG,"Response : "+response.toString());
                            try{

                                Gson gson=new Gson();
                                bookReservtnModel=gson.fromJson(response,BookReservtnModel.class);

                                if(bookReservtnModel.getStatus()==1){

                                    mProgressDialog.dismiss();
                                    bookReservArraylist=(ArrayList<BookReservtnModel.LibBokReserStatusBean>)bookReservtnModel.getLibBokReserStatus();

                                    if(bookReservArraylist.size()!=0 && bookReservArraylist!=null){

                                        String type="DETAIL_VIEW";
                                        bookReserveAdptr=new BookReserveAdptr(getActivity(),bookReservArraylist,type);

                                        Intent intent = new Intent(getActivity(), ReserveActivity.class);
                                        startActivity(intent);
                                        libraryFragment.dismiss();
                                        getActivity().finish();

                                    }else{
                                        Snackbar.make(libraryLayout, "Record not available.", Snackbar.LENGTH_LONG)
                                                .setAction("Action", null).show();
                                    }

                                }else{
                                    mProgressDialog.dismiss();
                                    Snackbar.make(libraryLayout, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
                                            .setAction("Action", null).show();
                                }



                            }catch(Exception e){

                                Snackbar.make(libraryLayout, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
                                        .setAction("Action", null).show();

                                Log.d(TAG, "Error : " + e.getMessage());

                                mProgressDialog.dismiss();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                            handleVolleyError(error);
                            mProgressDialog.dismiss();
                        }
                    });

                    stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                            60000,
                            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

                    // Adding request to request queue

                    MyApplication.getInstance().addToRequestQueue(stringRequest);

                }

            }
        });


        linearLayout7.setOnClickListener(new View.OnClickListener() {
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
                                            Toast.makeText(getActivity(), "Please check your internt", Toast.LENGTH_SHORT).show();
                                            // Restart the Activity
                                            /*Intent intent = getIntent();
                                            finish();
                                            startActivity(intent);*/
                                        }
                                    });
                    AlertDialog alert = builder.create();
                    alert.show();
                }else {
                    mProgressDialog.setMessage("loading please wait");
                    mProgressDialog.setCanceledOnTouchOutside(false);
                    mProgressDialog.show();

                    String url=BASE_URL + URLEndPoints.GetLibFineDetails_URL(student_id);
                    Log.d(TAG,"URL : "+url);

                    StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {



                            Log.d(TAG,"Response : "+response);

                            try{

                                Gson gson=new Gson();
                                fineModel=gson.fromJson(response,FineModel.class);

                                if(fineModel.getStatus()==1){

                                    mProgressDialog.dismiss();
                                    bookFineDetailsArrayList=(ArrayList<FineModel.BookFineDetailsBean>)fineModel.getBookFineDetails();

                                    if(bookFineDetailsArrayList.size()!=0 && bookFineDetailsArrayList!=null){
                                        bookFineAdpter=new BookFineAdpter(getActivity(),bookFineDetailsArrayList);

                                        Intent intent = new Intent(getActivity(), FineActivity.class);
                                        startActivity(intent);
                                        libraryFragment.dismiss();
                                        getActivity().finish();

                                    }else {
                                        Snackbar.make(libraryLayout, "Record not available.", Snackbar.LENGTH_LONG)
                                                .setAction("Action", null).show();
                                    }


                                }else{

                                    mProgressDialog.dismiss();
                                    Snackbar.make(libraryLayout, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
                                            .setAction("Action", null).show();

                                }




                            }catch (Exception e) {

                                Snackbar.make(libraryLayout, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
                                        .setAction("Action", null).show();

                                Log.d(TAG, "Error : " + e.getMessage());

                                mProgressDialog.dismiss();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                            handleVolleyError(error);
                            mProgressDialog.dismiss();
                        }
                    });


                    stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                            60000,
                            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

                    // Adding request to request queue

                    MyApplication.getInstance().addToRequestQueue(stringRequest);

                }


            }
        });

        return view;


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
