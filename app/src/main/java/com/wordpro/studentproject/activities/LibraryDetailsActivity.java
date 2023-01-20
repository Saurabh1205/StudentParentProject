package com.wordpro.studentproject.activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
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
import com.wordpro.studentproject.activities.Library.FineActivity;
import com.wordpro.studentproject.activities.Library.IssueBookActivity;
import com.wordpro.studentproject.activities.Library.RulesDescrptnActivity;
import com.wordpro.studentproject.adapter.BookFineAdpter;
import com.wordpro.studentproject.adapter.IssueBookAdpter;
import com.wordpro.studentproject.adapter.RulesAdpter;
import com.wordpro.studentproject.app.MyApplication;
import com.wordpro.studentproject.fragment.E_Lear_AssignmentFragment;
import com.wordpro.studentproject.fragment.New_Arrivals_Fragment;
import com.wordpro.studentproject.fragment.New_Fine_Fragment;
import com.wordpro.studentproject.fragment.New_Issuedbook_Fragment;
import com.wordpro.studentproject.fragment.New_Reservation_Fragment;
import com.wordpro.studentproject.fragment.New_Rules_Fragment;
import com.wordpro.studentproject.fragment.New_SearchBook_Fragment;
import com.wordpro.studentproject.fragment.New_Suggestion_Fragment;
import com.wordpro.studentproject.helper.PrefManager;
import com.wordpro.studentproject.model.FineModel;
import com.wordpro.studentproject.model.IssuedBookModel;
import com.wordpro.studentproject.model.RulesModel;
import com.wordpro.studentproject.utils.UtilityClass;
import com.wordpro.studentproject.webConfig.URLEndPoints;

import org.json.JSONObject;

import java.util.ArrayList;

import static com.wordpro.studentproject.activities.NavigationActivity.BASE_URL;
import static com.wordpro.studentproject.activities.NavigationActivity.studBranchStandardId;
import static com.wordpro.studentproject.activities.NavigationActivity.studSessionId;
import static com.wordpro.studentproject.activities.NavigationActivity.stud_main_semester_mst_id;
import static com.wordpro.studentproject.activities.NavigationActivity.student_id;
import static com.wordpro.studentproject.fragment.DashboardFragment.libraryFragment;

public class LibraryDetailsActivity extends AppCompatActivity {
    String str;
    public static Fragment fragment;
    private TextView txt_toolbarName;
    private ImageView back_press;
    private LinearLayout  belowId;
    private UtilityClass utilityClassObj;
    private RelativeLayout  relative_error;


    public static RulesModel rulesModel;
    public static ArrayList<RulesModel.CirculationRuleDtlsBean> circulationRuleDtlsArraylist;
    public static RulesAdpter rulesAdpter;
    public static ArrayList<String> data;

    public static FineModel fineModel;
    public static ArrayList<FineModel.BookFineDetailsBean> bookFineDetailsArrayList;
    public static BookFineAdpter bookFineAdpter;

    //IssuedBookModel
    public static IssuedBookModel issuedBookModel;
    public static ArrayList<IssuedBookModel.CirculationRuleDtlsBean> issuedBookArraylist;
    public static IssueBookAdpter issueBookAdpter;
    private PrefManager pref;

    public static String TAG = LibraryDetailsActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library_details);
        Typeface type_faceHeading = Typeface.createFromAsset(this.getAssets(), "font/Poppins-Bold.ttf");
        Typeface type_faceContent = Typeface.createFromAsset(this.getAssets(), "font/Poppins-Regular.ttf");
        pref = new PrefManager(getApplicationContext());
        utilityClassObj =new UtilityClass(getApplicationContext());
        relative_error =(RelativeLayout)findViewById(R.id.relative_error);
        txt_toolbarName =(TextView)findViewById(R.id.txt_toolbarName);
        back_press =(ImageView)findViewById(R.id.back_press);
        belowId =(LinearLayout) findViewById(R.id.belowId);
        Intent intent = getIntent();
        str = intent.getStringExtra("ComValue");
        Onclick();
        if (str.equalsIgnoreCase("NEW_ARRIVALS")){
            txt_toolbarName.setText("New Arrivals");
            fragment = new New_Arrivals_Fragment();
            Bundle bundle=new Bundle();
            bundle.putString("Arrivals", "NewArrivals");
            fragment.setArguments(bundle);
            loadFragment(fragment);
        }else if (str.equalsIgnoreCase("RULES")){
            txt_toolbarName.setText("Rules");
           /* fragment = new New_Rules_Fragment();
            Bundle bundle=new Bundle();
            bundle.putString("Rules", "NewRules");
            fragment.setArguments(bundle);
            loadFragment(fragment);*/
            getRules();
        }else if (str.equalsIgnoreCase("ISSUE_BOOKS")){
            txt_toolbarName.setText("Issued Books");
           /* fragment = new New_Issuedbook_Fragment();
            Bundle bundle=new Bundle();
            bundle.putString("Issued Books", "NewIssuedBooks");
            fragment.setArguments(bundle);
            loadFragment(fragment);*/
            getIssuesBooks();
        }else if (str.equalsIgnoreCase("SEARCH_BOOKS")){
            belowId.setVisibility(View.GONE);
            //txt_toolbarName.setText("Search Books");
            fragment = new New_SearchBook_Fragment();
            Bundle bundle=new Bundle();
            bundle.putString("Search Books", "NewSearchBooks");
            fragment.setArguments(bundle);
            loadFragment(fragment);
        }else if (str.equalsIgnoreCase("SUGGESTION")){
            txt_toolbarName.setText("Suggestion");
            fragment = new New_Suggestion_Fragment();
            Bundle bundle=new Bundle();
            bundle.putString("Suggestion", "NewSuggestion");
            fragment.setArguments(bundle);
            loadFragment(fragment);
        }else if (str.equalsIgnoreCase("RESERVATION")){
            txt_toolbarName.setText("Reservation Status");
            fragment = new New_Reservation_Fragment();
            Bundle bundle=new Bundle();
            bundle.putString("Reservation", "NewReservation");
            fragment.setArguments(bundle);
            loadFragment(fragment);

        }else if (str.equalsIgnoreCase("FINE")){
            txt_toolbarName.setText("Fine");
           /* fragment = new New_Fine_Fragment();
            Bundle bundle=new Bundle();
            bundle.putString("Fine", "NewFine");
            fragment.setArguments(bundle);
            loadFragment(fragment);*/

            getfineDetails();
        }


    }
    private void Onclick(){
        back_press.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(getApplicationContext(),LibraryActivity.class);
                startActivity(intent);
            }
        });
    }


    private void getfineDetails(){
        String url=BASE_URL + URLEndPoints.GetLibFineDetails_URL(student_id);
        //String url ="http://103.225.174.30:82/api/Library/GetLibFineDetails?PI_LIBRARY_MST_ID=4&PI_Member_Type=EMPLOYEE&PI_Member_Id=1039180120&PI_Acc_Session_Id=30";

       // http://192.168.1.72:500//api/Library/GetLibFineDetails?PI_LIBRARY_MST_ID=4&PI_Member_Type=EMPLOYEE&PI_Member_Id=1039180120&PI_Acc_Session_Id=20


        Log.d(TAG,"URL : "+url);

        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {



                Log.d(TAG,"Response : "+response);

                try{

                    Gson gson=new Gson();
                    fineModel=gson.fromJson(response, FineModel.class);
                    if(fineModel.getStatus()==1){


                        bookFineDetailsArrayList=(ArrayList<FineModel.BookFineDetailsBean>)fineModel.getBookFineDetails();
                        URLEndPoints.ConstancebookFineDetailsArrayList =bookFineDetailsArrayList;
                        if(bookFineDetailsArrayList.size()!=0 && bookFineDetailsArrayList!=null){
                            bookFineAdpter=new BookFineAdpter(getApplicationContext(),bookFineDetailsArrayList);

                            Intent intent = new Intent(getApplicationContext(), FineActivity.class);
                            startActivity(intent);
                            finish();

                        }else {
                            Snackbar.make(relative_error, "Record not available.", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        }


                    }else{

                        Snackbar.make(relative_error, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();

                    }




                }catch (Exception e) {
                    Snackbar.make(relative_error, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    Log.d(TAG, "Error : " + e.getMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

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

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.library_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    private void getRules(){
        utilityClassObj.startLoader(getApplicationContext(),R.drawable.image_for_rotation);
        String url = BASE_URL + URLEndPoints.GetCirculationRule_url(URLEndPoints.Constance_StudentCenterCode,"9");
        //Log.d(TAG, "URL : " + url);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                //Log.d(TAG, "RESPONSE : " + response);
                try {

                    Gson gson = new Gson();
                    rulesModel = gson.fromJson(response, RulesModel.class);

                    if (rulesModel.getStatus() == 1) {
                        //  mProgressDialog.dismiss();

                        circulationRuleDtlsArraylist = (ArrayList<RulesModel.CirculationRuleDtlsBean>) rulesModel.getCirculationRuleDtls();
                        if (circulationRuleDtlsArraylist.size() != 0 && circulationRuleDtlsArraylist != null) {

                            data = new ArrayList<>();

                            for (int i = 0; i < circulationRuleDtlsArraylist.size(); i++) {

                                if (i == 0) {
                                    String LIBRARY_NAME = circulationRuleDtlsArraylist.get(i).getLIBRARY_NAME();
                                    String MEMBER_TYPE = circulationRuleDtlsArraylist.get(i).getMEMBER_TYPE();
                                    String BOOK_RULE_DESC = circulationRuleDtlsArraylist.get(i).getBOOK_RULE_DESC();
                                    String TYPEWISE_BOOK_ALLOWED = circulationRuleDtlsArraylist.get(i).getBoKTyp_BokAllowed();
                                    String BOOK_TYPE_DESCRIPTION = circulationRuleDtlsArraylist.get(i).getBOOK_TYPE_DESCRIPTION();
                                    String MAX_BOOK_ALLOWED = circulationRuleDtlsArraylist.get(i).getConsol_Tot_Books();
                                    String MAX_DAYS_ALLOWED = circulationRuleDtlsArraylist.get(i).getMAX_DAYS_ALLOWED();
                                    String SLAB_DESC = circulationRuleDtlsArraylist.get(i).getSLAB_DESC();
                                    String SLAB_MASTER_ID = circulationRuleDtlsArraylist.get(i).getSLAB_MASTER_ID();

                                    String rule1 = "The circulation rules of " + LIBRARY_NAME + " are described under rule " + BOOK_RULE_DESC;
                                    data.add(rule1);
                                    String rule2 = "Maximum " + MAX_BOOK_ALLOWED + "  books can be issued from the " + LIBRARY_NAME;
                                    data.add(rule2);
                                    String rule3 = "Its mandatory to return the issued book upto the expected return date.If a person fails to return the issued book on the expected return date ,then the fine will be applicable based on the Fine Slab";
                                    data.add(rule3);


                                }
                            }


                            for (int i = 0; i < circulationRuleDtlsArraylist.size(); i++) {

                                String BoKTyp_BokAllowed = circulationRuleDtlsArraylist.get(i).getBoKTyp_BokAllowed();
                                String MAX_DAYS_ALLOWED = circulationRuleDtlsArraylist.get(i).getMAX_DAYS_ALLOWED();
                                String BOOK_TYPE_DESCRIPTION = circulationRuleDtlsArraylist.get(i).getBOOK_TYPE_DESCRIPTION();
                                String SLAB_DESC = circulationRuleDtlsArraylist.get(i).getSLAB_DESC();

                                String rule = "Maximum " + BoKTyp_BokAllowed + " " + BOOK_TYPE_DESCRIPTION + " type books are allowed for maximum " + MAX_DAYS_ALLOWED + " days. If the issued books are not returned within the expected return date to the Library, then Fine Slab for " + BOOK_TYPE_DESCRIPTION + " book type is " + SLAB_DESC + ".";
                                data.add(rule);


                            }

                            Object[] st1 = data.toArray();
                            for (Object s : st1) {
                                if (data.indexOf(s) != data.lastIndexOf(s)) {
                                    data.remove(data.lastIndexOf(s));
                                }
                            }
                            URLEndPoints.ConstanceData =data;

                            rulesAdpter = new RulesAdpter(getApplicationContext(), data);
                            Intent intent = new Intent(getApplicationContext(), RulesDescrptnActivity.class);
                            startActivity(intent);
                            finish();

                        } else {
                            utilityClassObj.stopLoader();
                            Snackbar.make(relative_error, "Record not available.", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        }

                    } else if (rulesModel.getStatus() == 0) {
                        utilityClassObj.stopLoader();
                        Snackbar.make(relative_error, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }


                } catch (Exception e) {
                    utilityClassObj.stopLoader();
                    //Log.d(TAG, "Error : " + e.getMessage());
                    Snackbar.make(relative_error, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                utilityClassObj.stopLoader();
                Snackbar.make(relative_error, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                60000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        // Adding request to request queue

        MyApplication.getInstance().addToRequestQueue(stringRequest);

    }


    private void  getIssuesBooks(){


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

                            Intent intent=new Intent(getApplicationContext(), IssueBookActivity.class);
                            intent.putExtra("fromDate", PERIOD_START_DATE);
                            intent.putExtra("uptoDate", PERIOD_END_DATE);
                            startActivity(intent);
                            libraryFragment.dismiss();
                            finish();

                        } else {
                            /*Snackbar snackbar = Snackbar
                                    .make(libraryLayout, "Record not found.", Snackbar.LENGTH_LONG);
                            snackbar.show();*/

                        }



                    } else if (status == 0) {
                        /*Snackbar.make(libraryLayout, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();*/


                    }


                } catch (Exception e) {

                   /* Snackbar.make(libraryLayout, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();*/

                    Log.d(TAG, "Error : " + e.getMessage());




                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Error: " + error.getMessage());
               // handleVolleyError(error);
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

    }
}
