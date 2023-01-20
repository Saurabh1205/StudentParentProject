package com.wordpro.studentproject.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
import com.wordpro.studentproject.adapter.FilterGroupListAdapter;
import com.wordpro.studentproject.adapter.New_NewsDetailsAdpter;
import com.wordpro.studentproject.app.MyApplication;
import com.wordpro.studentproject.helper.OnImageClickListener;
import com.wordpro.studentproject.helper.PrefManager;
import com.wordpro.studentproject.model.FilterGroupNameListModel;
import com.wordpro.studentproject.model.FilterGroupNameModel;
import com.wordpro.studentproject.model.MainNoticPatten;
import com.wordpro.studentproject.model.NoticPattenParents;
import com.wordpro.studentproject.model.NoticPattenSub;
import com.wordpro.studentproject.webConfig.URLEndPoints;

import java.util.ArrayList;
import java.util.List;

public class NewRightActionActivity extends AppCompatActivity implements OnImageClickListener {

    private TextView txt_toolbarName;
    private CardView card_view,card_view2;
    private ImageView imageView_search,imageView_filter,back_press;
    private TextView  txt_viewMore;
    private Button  previous,next;
    EditText  search_news;
    String searchNewsChar;
    String SearchFilerName=null;
    private RecyclerView  newsHeaderList,filterList;
    private New_NewsDetailsAdpter new_newsDetailsAdpter;
    private FilterGroupListAdapter   filterGroupListAdapter;

    private List<FilterGroupNameListModel>filterGroupList;
    private LinearLayout libraryLayout_filter;
    ProgressDialog mProgressDialog;
    private PrefManager pref;
    String studCenterCode,studDepartmentNumber,student_id,studBranchStandardId;
    private static String TAG = NewRightActionActivity.class.getSimpleName();


    private String TopFilter,NotificationPatten;
    private List<NoticPattenSub> noticPattenSubs;
    private List <NoticPattenParents> noticPattenParents;
    private String NOTICE_PATTERN;
    private int PageCount=1,FilterID=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.z_new_news);
        mProgressDialog=new ProgressDialog(NewRightActionActivity.this);
        pref = new PrefManager(getApplicationContext());


        txt_toolbarName = (TextView)findViewById(R.id.txt_toolbarName);
        back_press = (ImageView)findViewById(R.id.back_press);
        imageView_search = (ImageView)findViewById(R.id.imageView_search);
        imageView_filter = (ImageView)findViewById(R.id.imageView_filter);
        newsHeaderList =(RecyclerView)findViewById(R.id.newsHeaderList);
        filterList=(RecyclerView)findViewById(R.id.filterList);



        imageView_search =(ImageView)findViewById(R.id.imageView_search);
        imageView_filter =(ImageView)findViewById(R.id.imageView_filter);

        txt_viewMore = (TextView)findViewById(R.id.txt_viewMore);
        previous = (Button)findViewById(R.id.previous);
        next = (Button)findViewById(R.id.next);
        search_news =(EditText)findViewById(R.id.search_news);

        libraryLayout_filter =(LinearLayout)findViewById(R.id.libraryLayout_filter);

        studCenterCode = getIntent().getStringExtra("studCenterCode");
        studDepartmentNumber =getIntent().getStringExtra("studDepartmentNumber");
        student_id =getIntent().getStringExtra("student_id");
        studBranchStandardId =getIntent().getStringExtra("studBranchStandardId");

        Typeface type_faceHeading = Typeface.createFromAsset(this.getAssets(), "font/Poppins-Bold.ttf");
        Typeface type_faceContent = Typeface.createFromAsset(this.getAssets(), "font/Poppins-Regular.ttf");
        txt_toolbarName.setText("NEWS");
        txt_toolbarName.setTypeface(type_faceHeading);
        txt_viewMore.setTypeface(type_faceHeading);
        previous.setTypeface(type_faceHeading);
        next.setTypeface(type_faceHeading);
       // SearchFilerName;

        imageView_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    hideKeybaord(v);
                    searchNewsChar =search_news.getText().toString();
                    if (searchNewsChar.length()>=3){
                        SearchFilerName =searchNewsChar;
                        funNotices();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        imageView_filter.setOnClickListener(new View.OnClickListener() {
            boolean  visible;
            @Override
            public void onClick(View v) {
                visible =!visible;
                libraryLayout_filter.setVisibility(visible? View.VISIBLE:View.GONE);
                if (visible){

                }else {
                    FilterID =0;
                    funNotices();
                }

            }
        });
        search_news.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_LEFT = 0;
                final int DRAWABLE_TOP = 1;
                final int DRAWABLE_RIGHT = 2;
                final int DRAWABLE_BOTTOM = 3;

                if(event.getAction() == MotionEvent.ACTION_UP) {
                    if(event.getRawX() >= (search_news.getRight() - search_news.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        // your action here
                        hideKeybaord(v);
                        search_news.getText().clear();
                        SearchFilerName =null ;
                        funNotices();
                        return true;
                    }
                }
                return false;
            }
        });


        back_press.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // onBackPressed();
                Intent intent = new Intent(getApplicationContext(), NavigationActivity.class);
                startActivity(intent);
                finish();
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PageCount++;
                funNotices();
            }
        });

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PageCount--;
                funNotices();
            }
        });


        TopFilter ="30";
        NotificationPatten ="NEWSBOX";
        funNotices();
        funNoticesFilter();

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
    private void funNotices() {

        //String url = pref.getURL() + URLEndPoints.getNoticesAllURL(URLEndPoints.Constance_StudentCenterCode,URLEndPoints.Constance_StudentDepartmentNumber,URLEndPoints.Constance_StudentID,URLEndPoints.Constance_StudBranchStandardId,TopFilter);
        String url = pref.getURL() + URLEndPoints.AlertMultiApThreeListViewAll(URLEndPoints.Constance_StudentCenterCode,URLEndPoints.Constance_StudentDepartmentNumber,URLEndPoints.Constance_StudentID,URLEndPoints.Constance_StudBranchStandardId,TopFilter,NotificationPatten,PageCount, SearchFilerName,FilterID);

        Log.d(TAG, "URL : " + url);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, response.toString());
                try {
                    Gson gson = new Gson();

                    MainNoticPatten mainNoticPatten    = gson.fromJson(response, MainNoticPatten.class);

                    if (mainNoticPatten.getStatus() == 1) {

                        noticPattenSubs = (ArrayList<NoticPattenSub>) mainNoticPatten.getData();

                        noticPattenSubs.size();

                        noticPattenParents =new ArrayList<>();

                        for(int  i =0;i< noticPattenSubs.size();i++){
                            NOTICE_PATTERN =noticPattenSubs.get(i).getnOTICEPATTERN();
                            if (noticPattenSubs.get(i).getParent().size()>0) {
                                if (NOTICE_PATTERN.equalsIgnoreCase(noticPattenSubs.get(i).getnOTICEPATTERN())) {
                                    for (int  a =0;a< noticPattenSubs.get(i).getParent().size();a++){
                                        noticPattenParents.add(noticPattenSubs.get(i).getParent().get(a));
                                    }

                                }

                            }

                        }
                        if (noticPattenSubs.size()>0){
                            new_newsDetailsAdpter = new New_NewsDetailsAdpter(NewRightActionActivity.this, noticPattenParents,NOTICE_PATTERN);
                            newsHeaderList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                            newsHeaderList.setAdapter(new_newsDetailsAdpter);
                        }

                    }

                } catch (Exception e) {
                    //      mProgressDialog.hide();
                 /*   Snackbar.make(lytCoordnate, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    Log.d(TAG, "Error : " + e.getMessage());*/
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mProgressDialog.hide();
                handleVolleyError(error);
            }
        });

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(60000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        // Adding request to request queue

        MyApplication.getInstance().addToRequestQueue(stringRequest);


    }

    private void funNoticesFilter() {

        String url = pref.getURL() + URLEndPoints.AlertGroupFilterList(URLEndPoints.Constance_StudentCenterCode,
                URLEndPoints.Constance_StudentDepartmentNumber,URLEndPoints.Constance_StudentID,
                URLEndPoints.Constance_StudBranchStandardId,NotificationPatten);
        Log.d(TAG, "URL : " + url);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, response.toString());
                try {
                    Gson gson = new Gson();

                    FilterGroupNameModel filterGroupNameModel    = gson.fromJson(response, FilterGroupNameModel.class);

                    if (filterGroupNameModel.getStatus() == 1) {

                        filterGroupList = filterGroupNameModel.getData();
                        if (filterGroupList.size()>0){
                            filterGroupListAdapter = new FilterGroupListAdapter(NewRightActionActivity.this,filterGroupList);
                            LinearLayoutManager horizontalLayoutManagaer
                                    = new LinearLayoutManager(NewRightActionActivity.this, LinearLayoutManager.HORIZONTAL, false);
                            filterList.setLayoutManager(horizontalLayoutManagaer);
                            filterList.setAdapter(filterGroupListAdapter);

                            filterGroupListAdapter.setOnItemClickListener(new FilterGroupListAdapter.onRecyclerViewItemClickListener() {
                                @Override
                                public void onItemClickListener(View view, int position) {
                                    String dd=  filterGroupList.get(position).getgROUPNAME();
                                    String dss =filterGroupList.get(position).getnOTCGROUPMASTERID();
                                    int i=Integer.parseInt(dss);

                                    FilterID =i;
                                    funNotices();
                                    filterGroupListAdapter.notifyDataSetChanged();
                                }
                            });
                        }

                    }

                } catch (Exception e) {
                    //      mProgressDialog.hide();
                 /*   Snackbar.make(lytCoordnate, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    Log.d(TAG, "Error : " + e.getMessage());*/
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mProgressDialog.hide();
                handleVolleyError(error);
            }
        });

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(60000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        // Adding request to request queue

        MyApplication.getInstance().addToRequestQueue(stringRequest);


    }
    private void hideKeybaord(View v) {
        InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(v.getApplicationWindowToken(),0);
    }


    @Override
    public void onImageClick(String imageData) {

    }
}
