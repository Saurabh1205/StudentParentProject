package com.wordpro.studentproject.activities;

import android.app.FragmentManager;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.wordpro.studentproject.R;
import com.wordpro.studentproject.adapter.NewArrivalAdpter;
import com.wordpro.studentproject.app.MyApplication;
import com.wordpro.studentproject.fragment.E_Lear_AssignmentFragment;
import com.wordpro.studentproject.fragment.LibraryFragment;
import com.wordpro.studentproject.fragment.New_Arrivals_Fragment;
import com.wordpro.studentproject.helper.PrefManager;
import com.wordpro.studentproject.model.NewArrvlMnthModel;
import com.wordpro.studentproject.model.RulesModel;
import com.wordpro.studentproject.utils.UtilityClass;
import com.wordpro.studentproject.webConfig.URLEndPoints;

import java.util.ArrayList;

import static com.wordpro.studentproject.activities.NavigationActivity.BASE_URL;

public class LibraryActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView txt_toolbarName;
    private ImageView back_press;
    private CardView card_view_rules,card_view_issue_book,card_view_new_arrivals,card_view_search_book,
            card_view_suggestion,card_view_reservation,card_view_fine;
    private ImageView img_rules,img_issue_book,img_new_arrival,img_search_book,img_suggestion,img_reservation,img_fine;
    private TextView txt_rules,txt_issue_books,txt_new_arrivals,txt_book_search,txt_suggestion,txt_reservation,txt_fine;
    private UtilityClass utilityClassObj;
    private PrefManager pref;
    private static String TAG = LibraryActivity.class.getSimpleName();
    public static RulesModel rulesModel;
    public static ArrayList<RulesModel.CirculationRuleDtlsBean> circulationRuleDtlsArraylist;
   // public static RulesAdpter rulesAdpter;

    public static NewArrvlMnthModel newArrvlMnthModel;
    public static ArrayList<NewArrvlMnthModel.LibBokNewArrAbstBean> newArrvlMnthArraylist;
    //public static NewArrivalAdpter newArrivalAdpter;
    public static Fragment fragment;

    public static ArrayList<String> data;
    private RelativeLayout show_error_record;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);
        utilityClassObj =new UtilityClass(this);
        pref = new PrefManager(getApplicationContext());




        init();
        OnClickOption();



    }
    private void init(){
        Typeface type_faceHeading = Typeface.createFromAsset(this.getAssets(), "font/Poppins-Bold.ttf");
        Typeface type_faceContent = Typeface.createFromAsset(this.getAssets(), "font/Poppins-Regular.ttf");
        txt_toolbarName =(TextView)findViewById(R.id.txt_toolbarName);
        back_press =(ImageView)findViewById(R.id.back_press);
        card_view_rules=(CardView) findViewById(R.id.card_view_rules);
        card_view_issue_book=(CardView) findViewById(R.id.card_view_issue_book);
        card_view_new_arrivals=(CardView) findViewById(R.id.card_view_new_arrivals);
        card_view_search_book=(CardView) findViewById(R.id.card_view_search_book);
        card_view_suggestion=(CardView) findViewById(R.id.card_view_suggestion);
        card_view_reservation=(CardView) findViewById(R.id.card_view_reservation);
        card_view_fine=(CardView) findViewById(R.id.card_view_fine);
        show_error_record  =(RelativeLayout) findViewById(R.id.show_error_record);
        img_rules = (ImageView) findViewById(R.id.img_rules);
        img_issue_book = (ImageView) findViewById(R.id.img_issue_book);
        img_new_arrival = (ImageView) findViewById(R.id.img_new_arrival);
        img_search_book = (ImageView) findViewById(R.id.img_search_book);
        img_suggestion  = (ImageView) findViewById(R.id.img_suggestion);
        img_reservation = (ImageView) findViewById(R.id.img_reservation);
        img_fine = (ImageView) findViewById(R.id.img_fine);
        txt_rules = (TextView) findViewById(R.id.txt_rules);
        txt_issue_books = (TextView) findViewById(R.id.txt_issue_books);
        txt_new_arrivals = (TextView) findViewById(R.id.txt_new_arrivals);
        txt_book_search = (TextView) findViewById(R.id.txt_book_search);
        txt_suggestion = (TextView) findViewById(R.id.txt_suggestion);
        txt_reservation = (TextView) findViewById(R.id.txt_reservation);
        txt_fine = (TextView) findViewById(R.id.txt_fine);
        txt_toolbarName.setTypeface(type_faceHeading);
        txt_rules.setTypeface(type_faceHeading);
        txt_issue_books.setTypeface(type_faceContent);
        txt_new_arrivals.setTypeface(type_faceHeading);
        txt_book_search.setTypeface(type_faceContent);
        txt_suggestion.setTypeface(type_faceHeading);
        txt_reservation.setTypeface(type_faceContent);
        txt_fine.setTypeface(type_faceHeading);



        txt_toolbarName.setText("Library");
    }
    private void OnClickOption(){
        back_press.setOnClickListener(this);
        card_view_rules.setOnClickListener(this);
        card_view_issue_book.setOnClickListener(this);
        card_view_new_arrivals.setOnClickListener(this);
        card_view_search_book.setOnClickListener(this);
        card_view_suggestion.setOnClickListener(this);
        card_view_reservation.setOnClickListener(this);
        card_view_fine.setOnClickListener(this);
    }
    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.back_press:
                /*Intent intent = new Intent(getApplicationContext(), NavigationActivity.class);
                startActivity(intent);*/
                finish();
                break;
            case R.id.card_view_rules:
                Com_Intent("RULES");
                break;
            case R.id.card_view_issue_book:
                Com_Intent("ISSUE_BOOKS");
                break;
            case R.id.card_view_new_arrivals:
                Com_Intent("NEW_ARRIVALS");
                break;
            case R.id.card_view_search_book:
                Com_Intent("SEARCH_BOOKS");
                break;
            case R.id.card_view_suggestion:
                Com_Intent("SUGGESTION");
                break;
            case R.id.card_view_reservation:
                Com_Intent("RESERVATION");
                break;
            case R.id.card_view_fine:
                Com_Intent("FINE");
                break;
            default:
                break;
        }
    }

    private void Com_Intent(String ViewName){
        Intent com = new Intent(getApplicationContext(), LibraryDetailsActivity.class);
        com.putExtra("ComValue", ViewName);
        startActivity(com);
    }

}
