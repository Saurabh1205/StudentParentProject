package com.wordpro.studentproject.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
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
import com.wordpro.studentproject.activities.Library.SearchBookActivity;
import com.wordpro.studentproject.activities.Library.SearchedBookActivity;
import com.wordpro.studentproject.activities.LibraryActivity;
import com.wordpro.studentproject.adapter.SearchedBkAdptr;
import com.wordpro.studentproject.app.MyApplication;
import com.wordpro.studentproject.helper.PrefManager;
import com.wordpro.studentproject.model.SearchBkModel;
import com.wordpro.studentproject.utils.UtilityClass;
import com.wordpro.studentproject.webConfig.URLEndPoints;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.wordpro.studentproject.activities.NavigationActivity.BASE_URL;
import static com.wordpro.studentproject.activities.NavigationActivity.studCenterCode;

public class New_SearchBook_Fragment extends Fragment {

    private PrefManager pref;
    public static String TAG = New_SearchBook_Fragment.class.getSimpleName();
    private TextView  txt_view;
    LinearLayout linlytSearch, lytSearchBk;
    EditText edtBookTitle, edtBookAuthor, edtBookPublication, edtSyllabusTpc;
    TextView txtSearchbk,txtSearch;
    private TextView txt_toolbarName,search_icon;
    private ImageView back_press;
    private UtilityClass utilityClassObj;
    private RecyclerView  recycle_view_search_book;

    String PI_BOOK_TITLE, PI_AUTHOR_NAME, PI_PUBLICATION_NAME, PI_TOPIC_NAME;

    //SearchBkModel
    public static SearchBkModel searchBkModel;
    public static ArrayList<SearchBkModel.BookSearchDetailsBean> searchDetailsArrayList;
    public static SearchedBkAdptr searchedBkAdptr;
    private  Dialog dialog;

    public New_SearchBook_Fragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.activity_search_book, container, false);
        pref = new PrefManager(getActivity());
        utilityClassObj  = new  UtilityClass(getActivity());
        String value = getArguments().getString("Search Books");
        /*txt_view =(TextView)view.findViewById(R.id.txt_view);
        txt_view.setText("Search Books");*/
        Log.d(TAG, value);
        init(view);
        return view;

    }
     private void init(View v){
        Typeface type_faceHeading = Typeface.createFromAsset(v.getContext().getAssets(), "font/Poppins-Bold.ttf");
        Typeface type_faceContent = Typeface.createFromAsset(v.getContext().getAssets(), "font/Poppins-Regular.ttf");
        linlytSearch =(LinearLayout)v.findViewById(R.id.linlytSearch);
         lytSearchBk =(LinearLayout)v.findViewById(R.id.lytSearchBk);
        search_icon =(TextView) v.findViewById(R.id.search_icon);
        txt_toolbarName =(TextView)v.findViewById(R.id.txt_toolbarName);
        back_press =(ImageView)v.findViewById(R.id.back_press);
        recycle_view_search_book =  (RecyclerView)v.findViewById(R.id.recycle_view_search_book);
        txt_toolbarName.setText("Search Books");
       /* txtSearch=(TextView)v.findViewById(R.id.txtSearch);
        txtSearch.setTypeface(type_faceHeading);
        edtBookTitle = (EditText)v. findViewById(R.id.edtBookTitle);
        edtBookTitle.setTypeface(type_faceContent);
        edtBookAuthor = (EditText)v. findViewById(R.id.edtBookAuthor);
        edtBookAuthor.setTypeface(type_faceContent);
        edtBookPublication = (EditText) v. findViewById(R.id.edtBookPublication);
        edtBookPublication.setTypeface(type_faceContent);
        edtSyllabusTpc = (EditText)v.  findViewById(R.id.edtSyllabusTpc);
        edtSyllabusTpc.setTypeface(type_faceContent);
         if (isValid()) {
             funSearchBook();
         }*/
         ViewDialog alert = new ViewDialog();
         alert.showDialog(getActivity(), "Error de conexión al servidor");
         Onclick();
     }
    private void Onclick(){
        back_press.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchDetailsArrayList =null;
                Intent intent  = new Intent(getActivity(), LibraryActivity.class);
                startActivity(intent);
            }
        });

        search_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewDialog alert = new ViewDialog();
                alert.showDialog(getActivity(), "Error de conexión al servidor");
            }
        });
    }

     private void funSearchBook() {

        PI_BOOK_TITLE = edtBookTitle.getText().toString();
        if (PI_BOOK_TITLE.length() < 1) {
            PI_BOOK_TITLE = "NA";
        }

        PI_AUTHOR_NAME = edtBookAuthor.getText().toString();
        if (PI_AUTHOR_NAME.length() < 1) {
            PI_AUTHOR_NAME = "NA";
        }

        PI_PUBLICATION_NAME = edtBookPublication.getText().toString();
        if (PI_PUBLICATION_NAME.length() < 1) {
            PI_PUBLICATION_NAME = "NA";
        }

        PI_TOPIC_NAME = edtSyllabusTpc.getText().toString();
        if (PI_TOPIC_NAME.length() < 1) {
            PI_TOPIC_NAME = "NA";
        }
        utilityClassObj.startLoader(getActivity(),R.drawable.image_for_rotation);
        String url =BASE_URL + URLEndPoints.BOOK_SEARCHING_URL;
        Log.d(TAG,url);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d(TAG, "Response" + response);

                try {

                    Gson gson = new Gson();
                    searchBkModel = gson.fromJson(response, SearchBkModel.class);
                    utilityClassObj.stopLoader();
                    if (searchBkModel.getStatus() == 1) {
                        dialog.dismiss();
                        searchDetailsArrayList = (ArrayList<SearchBkModel.BookSearchDetailsBean>) searchBkModel.getBookSearchDetails();
                        if (searchDetailsArrayList.size() != 0 && searchDetailsArrayList != null) {

                            searchedBkAdptr=new SearchedBkAdptr(getActivity(),searchDetailsArrayList);
                            recycle_view_search_book.setLayoutManager(new LinearLayoutManager(getActivity()));
                            recycle_view_search_book.setAdapter(searchedBkAdptr);


                        } else {
                            Snackbar.make(lytSearchBk, "Record not available", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        }

                    } else {
                        dialog.dismiss();
                        Snackbar.make(lytSearchBk, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }

                } catch (Exception e) {
                    utilityClassObj.stopLoader();
                    Snackbar.make(lytSearchBk, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
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
        }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();

                params.put("PI_CENTRE_CODE",studCenterCode);
                params.put("PI_BOOK_TITLE", PI_BOOK_TITLE);
                params.put("PI_PUBLICATION_NAME", PI_PUBLICATION_NAME);
                params.put("PI_AUTHOR_NAME", PI_AUTHOR_NAME);
                params.put("PI_TOPIC_NAME", PI_TOPIC_NAME);

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

     public boolean isValid() {

        if (edtBookTitle.getText().toString().length() < 1 && edtBookPublication.getText().toString().length() < 1 && edtBookAuthor.getText().toString().length() < 1
                && edtSyllabusTpc.getText().toString().length() < 1) {

            Snackbar.make(lytSearchBk, "Please enter search Book title name or publication name or author name or syllabus topic name", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            return false;

        } else
            return true;
     }
    public class ViewDialog {

        public void showDialog(Activity activity, String msg){
            dialog = new Dialog(activity);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.search_books_dilog);

          /*  TextView text = (TextView) dialog.findViewById(R.id.text_dialog);
            text.setText(msg);*/
            Typeface type_faceHeading = Typeface.createFromAsset(dialog.getContext().getAssets(), "font/Poppins-Bold.ttf");
            Typeface type_faceContent = Typeface.createFromAsset(dialog.getContext().getAssets(), "font/Poppins-Regular.ttf");

            edtBookTitle = (EditText)dialog. findViewById(R.id.edtBookTitle);
            edtBookTitle.setTypeface(type_faceContent);
            edtBookAuthor = (EditText)dialog. findViewById(R.id.edtBookAuthor);
            edtBookAuthor.setTypeface(type_faceContent);
            edtBookPublication = (EditText)dialog. findViewById(R.id.edtBookPublication);
            edtBookPublication.setTypeface(type_faceContent);
            edtSyllabusTpc = (EditText)dialog. findViewById(R.id.edtSyllabusTpc);
            edtSyllabusTpc.setTypeface(type_faceContent);
            Button dialogBtnCancel = (Button) dialog.findViewById(R.id.btn_dialog_cancel);
            dialogBtnCancel.setTypeface(type_faceHeading);
            dialogBtnCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    if (searchDetailsArrayList == null || searchDetailsArrayList.size() == 0){
                        Intent intent  = new Intent(getActivity(), LibraryActivity.class);
                        startActivity(intent);
                    }
                }
            });
            Button dialogBtnSearch = (Button) dialog.findViewById(R.id.btn_dialog_search);
            dialogBtnSearch.setTypeface(type_faceHeading);
            dialogBtnSearch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isValid()) {
                        funSearchBook();
                    }
                }
            });
            dialog.show();

        }
    }

}
