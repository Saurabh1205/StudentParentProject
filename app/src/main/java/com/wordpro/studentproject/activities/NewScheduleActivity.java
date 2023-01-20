package com.wordpro.studentproject.activities;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
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
import com.wordpro.studentproject.activities.schedule_menu.ExtraLectureActivity;
import com.wordpro.studentproject.activities.schedule_menu.NewTimetblActivity;
import com.wordpro.studentproject.adapter.ExtraLecAdapter;
import com.wordpro.studentproject.adapter.New_Schedule_TimeTableAdpter;
import com.wordpro.studentproject.adapter.University_syllabus_Adpter;
import com.wordpro.studentproject.app.MyApplication;
import com.wordpro.studentproject.fragment.FragAttendance;
import com.wordpro.studentproject.fragment.UniversitySyllabusFragmen;
import com.wordpro.studentproject.helper.PrefManager;
import com.wordpro.studentproject.model.ExtraLecModel;
import com.wordpro.studentproject.model.StudTimeTableModel;
import com.wordpro.studentproject.utils.UtilityClass;
import com.wordpro.studentproject.webConfig.URLEndPoints;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Pattern;

import static com.wordpro.studentproject.activities.NavigationActivity.fragSchedule;
import static com.wordpro.studentproject.activities.NavigationActivity.studAcadSessName;
import static com.wordpro.studentproject.activities.NavigationActivity.studBranchStandDesc;
import static com.wordpro.studentproject.activities.NavigationActivity.studBranchStandardId;
import static com.wordpro.studentproject.activities.NavigationActivity.studCenterCode;
import static com.wordpro.studentproject.activities.NavigationActivity.studMainSemName;
import static com.wordpro.studentproject.activities.NavigationActivity.studSemesterType;
import static com.wordpro.studentproject.activities.NavigationActivity.studSessionId;
import static com.wordpro.studentproject.activities.NavigationActivity.studentName;
import static com.wordpro.studentproject.activities.NavigationActivity.student_id;
import static com.wordpro.studentproject.activities.VerficationActivity.eventsArrayList;
import static com.wordpro.studentproject.activities.VerficationActivity.galleryArrayList;
import static com.wordpro.studentproject.activities.VerficationActivity.newsArrayList;
import static com.wordpro.studentproject.activities.VerficationActivity.noticesArrayListupdated;
import static com.wordpro.studentproject.activities.VerficationActivity.videosArrayList;

public class NewScheduleActivity extends AppCompatActivity {
    private ImageView back_press;
    private TextView txt_toolbarName,E,Sun,Mon,Tue,Wed,Thu,Fir,Sat,txt_today_date,txt_today;
    private CardView card_view_E,card_view_Sun,card_view_Mon,card_view_Tue,
            card_view_Wed,card_view_Thu,card_view_Fir,card_view_Sat;
    private RecyclerView rec_dayList;
    private RelativeLayout lytTimeTable;
    private ProgressBar progBarTimeTable;
    public static android.support.v4.app.Fragment fragment;
    private PrefManager pref;
    public static ExtraLecModel extraLecModel;
    public static ArrayList<ExtraLecModel.DataBean> extraLecArrayList;
    private static String TAG = NewScheduleActivity.class.getSimpleName() ;
    public static StudTimeTableModel studTimeTableModel;
    public static ArrayList<StudTimeTableModel.DataBean> recordArrayList;
    private LinearLayout viewList;
    private LinearLayout belowId;

    // date wise value add in array

    public static ArrayList<StudTimeTableModel.DataBean> MonArray;
    public static ArrayList<StudTimeTableModel.DataBean> TueArray;
    public static ArrayList<StudTimeTableModel.DataBean> WedArray;
    public static ArrayList<StudTimeTableModel.DataBean> ThuArray;
    public static ArrayList<StudTimeTableModel.DataBean> FirArray;
    public static ArrayList<StudTimeTableModel.DataBean> SatArray;
    public static ArrayList<StudTimeTableModel.DataBean> SunArray;
    private String TodayDate;

    public static ArrayList<String> timePrdSquenceArray;
    public static ArrayList<String> weekArrayList;
    public static ArrayList<String> periodSquenceArrayList;

    private New_Schedule_TimeTableAdpter newScheduleTimeTableAdpter;

    private UtilityClass utilityClassObj;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_schedule);

        pref = new PrefManager(this);
        utilityClassObj  = new  UtilityClass(this);
        txt_toolbarName =(TextView)findViewById(R.id.txt_toolbarName);
        back_press =(ImageView)findViewById(R.id.back_press);
        progBarTimeTable =(ProgressBar)findViewById(R.id.progBarTimeTable);
        lytTimeTable =(RelativeLayout) findViewById(R.id.lytTimeTable);
        txt_today_date=( TextView)findViewById(R.id.txt_today_date);
        txt_today =( TextView)findViewById(R.id.txt_today);
        viewList  =( LinearLayout) findViewById(R.id.viewList);
        belowId =( LinearLayout) findViewById(R.id.belowId);
        card_view_E =(CardView)findViewById(R.id.card_view_E);
        card_view_Sun =(CardView)findViewById(R.id.card_view_Sun);
        card_view_Mon =(CardView)findViewById(R.id.card_view_Mon);
        card_view_Tue =(CardView)findViewById(R.id.card_view_Tue);
        card_view_Wed =(CardView)findViewById(R.id.card_view_Wed);
        card_view_Thu =(CardView)findViewById(R.id.card_view_Thu);
        card_view_Fir =(CardView)findViewById(R.id.card_view_Fir);
        card_view_Sat =(CardView)findViewById(R.id.card_view_Sat);

        E =(TextView)findViewById(R.id.E);
        Sun =(TextView)findViewById(R.id.Sun);
        Mon =(TextView)findViewById(R.id.Mon);
        Tue =(TextView)findViewById(R.id.Tue);
        Wed =(TextView)findViewById(R.id.Wed);
        Thu =(TextView)findViewById(R.id.Thu);
        Fir =(TextView)findViewById(R.id.Fir);
        Sat =(TextView)findViewById(R.id.Sat);

        rec_dayList  =(RecyclerView) findViewById(R.id.rec_dayList);

        MonArray =new  ArrayList<StudTimeTableModel.DataBean>();
        TueArray =new  ArrayList<StudTimeTableModel.DataBean>();
        WedArray =new  ArrayList<StudTimeTableModel.DataBean>();
        ThuArray =new  ArrayList<StudTimeTableModel.DataBean>();
        FirArray =new  ArrayList<StudTimeTableModel.DataBean>();
        SatArray =new  ArrayList<StudTimeTableModel.DataBean>();
        SunArray =new  ArrayList<StudTimeTableModel.DataBean>();

        Typeface type_faceHeading = Typeface.createFromAsset(this.getAssets(), "font/Poppins-Bold.ttf");
        Typeface type_faceContent = Typeface.createFromAsset(this.getAssets(), "font/Poppins-Regular.ttf");
        txt_toolbarName.setText("SCHEDULE");
        txt_toolbarName.setTypeface(type_faceHeading);

        if (URLEndPoints.ConstanceLoginUser.equalsIgnoreCase("Student")){
            belowId.setBackgroundColor(ContextCompat.getColor(this, R.color.text_blue));
            E.setTextColor(ContextCompat.getColor(this, R.color.sheduleDay));
            Sun.setTextColor(ContextCompat.getColor(this, R.color.sheduleDay));
            Mon.setTextColor(ContextCompat.getColor(this, R.color.sheduleDay));
            Tue.setTextColor(ContextCompat.getColor(this, R.color.sheduleDay));
            Wed.setTextColor(ContextCompat.getColor(this, R.color.sheduleDay));
            Thu.setTextColor(ContextCompat.getColor(this, R.color.sheduleDay));
            Fir.setTextColor(ContextCompat.getColor(this, R.color.sheduleDay));
            Sat.setTextColor(ContextCompat.getColor(this, R.color.sheduleDay));


        }else {
            belowId.setBackgroundColor(ContextCompat.getColor(this, R.color.present));
            E.setTextColor(ContextCompat.getColor(this, R.color.present));
            Sun.setTextColor(ContextCompat.getColor(this, R.color.present));
            Mon.setTextColor(ContextCompat.getColor(this, R.color.present));
            Tue.setTextColor(ContextCompat.getColor(this, R.color.present));
            Wed.setTextColor(ContextCompat.getColor(this, R.color.present));
            Thu.setTextColor(ContextCompat.getColor(this, R.color.present));
            Fir.setTextColor(ContextCompat.getColor(this, R.color.present));
            Sat.setTextColor(ContextCompat.getColor(this, R.color.present));

        }
        SimpleDateFormat currentDate = new SimpleDateFormat("EEEE");
        Date todayDate = new Date();
        String thisDate = currentDate.format(todayDate);
        TodayDate =thisDate.toUpperCase();
        System.out.println(thisDate);


        getTimetbleData();





        back_press.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), NavigationActivity.class);
                startActivity(intent);
                finish();
            }
        });


        card_view_Mon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewList.setVisibility(View.VISIBLE);
                txt_today.setVisibility(View.GONE);

                txt_today_date.setText(MonArray.get(0).getDate());

                if (URLEndPoints.ConstanceLoginUser.equalsIgnoreCase("Student")) {


                    txt_today_date.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.text_blue));

                    card_view_Mon.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.text_blue));
                    card_view_Tue.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    card_view_Wed.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    card_view_Thu.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    card_view_Fir.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    card_view_Sat.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    card_view_Sun.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    card_view_E.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));

                    Mon.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    Tue.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.text_blue));
                    Wed.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.text_blue));
                    Thu.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.text_blue));
                    Fir.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.text_blue));
                    Sat.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.text_blue));
                    Sun.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.text_blue));
                    E.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.text_blue));
                }else {
                    txt_today_date.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.text_blue));

                    card_view_Mon.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.present));
                    card_view_Tue.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    card_view_Wed.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    card_view_Thu.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    card_view_Fir.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    card_view_Sat.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    card_view_Sun.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    card_view_E.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));

                    Mon.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    Tue.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.present));
                    Wed.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.present));
                    Thu.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.present));
                    Fir.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.present));
                    Sat.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.present));
                    Sun.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.present));
                    E.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.present));
                }


                if (MonArray != null && MonArray.size() != 0) {
                    newScheduleTimeTableAdpter = new New_Schedule_TimeTableAdpter(getApplicationContext(),MonArray);
                    rec_dayList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    rec_dayList.setAdapter(newScheduleTimeTableAdpter);
                    newScheduleTimeTableAdpter.notifyDataSetChanged();
                } else {
                    viewList.setVisibility(View.GONE);
                    txt_today.setVisibility(View.GONE);
                    Snackbar.make(lytTimeTable, "Record not available.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                }

            }
        });

        card_view_Tue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewList.setVisibility(View.VISIBLE);
                txt_today.setVisibility(View.GONE);
                txt_today_date.setText(TueArray.get(0).getDate());

                if (URLEndPoints.ConstanceLoginUser.equalsIgnoreCase("Student")) {
                    txt_today_date.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.text_blue));

                    card_view_Mon.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    card_view_Tue.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.text_blue));
                    card_view_Wed.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    card_view_Thu.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    card_view_Fir.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    card_view_Sat.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    card_view_Sun.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    Mon.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.text_blue));
                    Tue.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    Wed.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.text_blue));
                    Thu.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.text_blue));
                    Fir.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.text_blue));
                    Sat.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.text_blue));
                    Sun.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.text_blue));

                    card_view_E.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    E.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.text_blue));
                }else {
                    txt_today_date.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.present));

                    card_view_Mon.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    card_view_Tue.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.present));
                    card_view_Wed.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    card_view_Thu.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    card_view_Fir.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    card_view_Sat.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    card_view_Sun.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    Mon.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.present));
                    Tue.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    Wed.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.present));
                    Thu.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.present));
                    Fir.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.present));
                    Sat.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.present));
                    Sun.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.present));

                    card_view_E.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    E.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.present));
                }


                if (TueArray != null && TueArray.size() != 0) {
                    newScheduleTimeTableAdpter = new New_Schedule_TimeTableAdpter(getApplicationContext(), TueArray);
                    rec_dayList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    rec_dayList.setAdapter(newScheduleTimeTableAdpter);
                    newScheduleTimeTableAdpter.notifyDataSetChanged();
                } else {
                    viewList.setVisibility(View.GONE);
                    txt_today.setVisibility(View.GONE);
                    Snackbar.make(lytTimeTable, "Record not available.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();


                }
            }
        });

        card_view_Wed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewList.setVisibility(View.VISIBLE);
                txt_today.setVisibility(View.GONE);

                txt_today_date.setText(WedArray.get(0).getDate());

                if (URLEndPoints.ConstanceLoginUser.equalsIgnoreCase("Student")) {
                    txt_today_date.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.text_blue));

                    card_view_Mon.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    card_view_Tue.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    card_view_Wed.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.text_blue));
                    card_view_Thu.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    card_view_Fir.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    card_view_Sat.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    card_view_Sun.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    Mon.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.text_blue));
                    Tue.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.text_blue));
                    Wed.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    Thu.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.text_blue));
                    Fir.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.text_blue));
                    Sat.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.text_blue));
                    Sun.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.text_blue));

                    card_view_E.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    E.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.text_blue));
                }else {
                    card_view_Mon.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    card_view_Tue.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    card_view_Wed.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.present));
                    card_view_Thu.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    card_view_Fir.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    card_view_Sat.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    card_view_Sun.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    Mon.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.present));
                    Tue.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.present));
                    Wed.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    Thu.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.present));
                    Fir.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.present));
                    Sat.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.present));
                    Sun.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.present));

                    card_view_E.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    E.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.present));
                }


                if (WedArray!=null && WedArray.size()!=0){
                    newScheduleTimeTableAdpter = new New_Schedule_TimeTableAdpter(getApplicationContext(),WedArray);
                    rec_dayList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    rec_dayList.setAdapter(newScheduleTimeTableAdpter);
                    newScheduleTimeTableAdpter.notifyDataSetChanged();
                }else {
                    viewList.setVisibility(View.GONE);
                    txt_today.setVisibility(View.GONE);
                    Snackbar.make(lytTimeTable, "Record not available.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }

            }
        });
        card_view_Thu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                viewList.setVisibility(View.VISIBLE);
                txt_today.setVisibility(View.GONE);
                txt_today_date.setText(ThuArray.get(0).getDate());
                if (URLEndPoints.ConstanceLoginUser.equalsIgnoreCase("Student")) {
                    txt_today_date.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.text_blue));

                    card_view_Mon.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    card_view_Tue.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    card_view_Wed.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    card_view_Thu.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.text_blue));
                    card_view_Fir.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    card_view_Sat.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    card_view_Sun.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    Mon.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.text_blue));
                    Tue.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.text_blue));
                    Wed.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.text_blue));
                    Thu.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    Fir.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.text_blue));
                    Sat.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.text_blue));
                    Sun.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.text_blue));
                    card_view_E.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    E.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.text_blue));
                }else {
                    txt_today_date.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.present));

                    card_view_Mon.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    card_view_Tue.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    card_view_Wed.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    card_view_Thu.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.present));
                    card_view_Fir.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    card_view_Sat.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    card_view_Sun.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    Mon.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.present));
                    Tue.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.present));
                    Wed.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.present));
                    Thu.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    Fir.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.present));
                    Sat.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.present));
                    Sun.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.present));
                    card_view_E.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    E.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.present));
                }


                if (TueArray!=null && TueArray.size()!=0){
                    newScheduleTimeTableAdpter = new New_Schedule_TimeTableAdpter(getApplicationContext(),TueArray);
                    rec_dayList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    rec_dayList.setAdapter(newScheduleTimeTableAdpter);
                    newScheduleTimeTableAdpter.notifyDataSetChanged();
                }else {
                    viewList.setVisibility(View.GONE);
                    txt_today.setVisibility(View.GONE);
                    Snackbar.make(lytTimeTable, "Record not available.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }

            }
        });
        card_view_Fir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                viewList.setVisibility(View.VISIBLE);
                txt_today.setVisibility(View.GONE);
                txt_today_date.setText(FirArray.get(0).getDate());

                if (URLEndPoints.ConstanceLoginUser.equalsIgnoreCase("Student")) {
                    txt_today_date.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.text_blue));
                    card_view_Mon.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    card_view_Tue.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    card_view_Wed.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    card_view_Thu.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    card_view_Fir.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.text_blue));
                    card_view_Sat.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    card_view_Sun.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    Mon.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.text_blue));
                    Tue.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.text_blue));
                    Wed.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.text_blue));
                    Thu.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.text_blue));
                    Fir.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    Sat.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.text_blue));
                    Sun.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.text_blue));
                    card_view_E.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    E.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.text_blue));
                }else {
                    txt_today_date.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.present));
                    card_view_Mon.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    card_view_Tue.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    card_view_Wed.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    card_view_Thu.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    card_view_Fir.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.present));
                    card_view_Sat.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    card_view_Sun.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    Mon.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.present));
                    Tue.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.present));
                    Wed.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.present));
                    Thu.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.present));
                    Fir.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    Sat.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.present));
                    Sun.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.present));
                    card_view_E.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    E.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.present));
                }
                if (FirArray!=null && FirArray.size()!=0){
                    newScheduleTimeTableAdpter = new New_Schedule_TimeTableAdpter(getApplicationContext(),FirArray);
                    rec_dayList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    rec_dayList.setAdapter(newScheduleTimeTableAdpter);
                    newScheduleTimeTableAdpter.notifyDataSetChanged();
                }else {
                    viewList.setVisibility(View.GONE);
                    txt_today.setVisibility(View.GONE);
                    Snackbar.make(lytTimeTable, "Record not available.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }


            }
        });
        card_view_Sat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                viewList.setVisibility(View.VISIBLE);
                txt_today.setVisibility(View.GONE);
                if (URLEndPoints.ConstanceLoginUser.equalsIgnoreCase("Student")) {
                    txt_today_date.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.text_blue));
                    card_view_Mon.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    card_view_Tue.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    card_view_Wed.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    card_view_Thu.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    card_view_Fir.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    card_view_Sat.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.text_blue));
                    card_view_Sun.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    Mon.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.text_blue));
                    Tue.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.text_blue));
                    Wed.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.text_blue));
                    Thu.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.text_blue));
                    Fir.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.text_blue));
                    Sat.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    Sun.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.text_blue));
                    card_view_E.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    E.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.text_blue));

                }else {
                    txt_today_date.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.present));
                    card_view_Mon.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    card_view_Tue.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    card_view_Wed.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    card_view_Thu.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    card_view_Fir.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    card_view_Sat.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.present));
                    card_view_Sun.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    Mon.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.present));
                    Tue.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.present));
                    Wed.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.present));
                    Thu.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.present));
                    Fir.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.present));
                    Sat.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    Sun.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.present));
                    card_view_E.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    E.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.present));
                }
                if (SatArray!=null && SatArray.size()!=0){
                    txt_today_date.setText(SatArray.get(0).getDate());
                    newScheduleTimeTableAdpter = new New_Schedule_TimeTableAdpter(getApplicationContext(),SatArray);
                    rec_dayList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    rec_dayList.setAdapter(newScheduleTimeTableAdpter);
                    newScheduleTimeTableAdpter.notifyDataSetChanged();
                }else {
                    viewList.setVisibility(View.GONE);
                    txt_today.setVisibility(View.GONE);
                    Snackbar.make(lytTimeTable, "Record not available.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }


            }
        });
        card_view_Sun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                viewList.setVisibility(View.GONE);
                txt_today.setVisibility(View.GONE);
                if (URLEndPoints.ConstanceLoginUser.equalsIgnoreCase("Student")) {
                    card_view_Mon.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    card_view_Tue.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    card_view_Wed.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    card_view_Thu.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    card_view_Fir.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    card_view_Sat.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    card_view_Sun.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.text_blue));
                    Mon.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.text_blue));
                    Tue.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.text_blue));
                    Wed.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.text_blue));
                    Thu.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.text_blue));
                    Fir.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.text_blue));
                    Sat.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.text_blue));
                    Sun.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    card_view_E.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    E.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.text_blue));
                }else {
                    card_view_Mon.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    card_view_Tue.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    card_view_Wed.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    card_view_Thu.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    card_view_Fir.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    card_view_Sat.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    card_view_Sun.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.present));
                    Mon.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.present));
                    Tue.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.present));
                    Wed.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.present));
                    Thu.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.present));
                    Fir.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.present));
                    Sat.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.present));
                    Sun.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    card_view_E.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    E.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.present));
                }
                Snackbar.make(lytTimeTable, "Record not available.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();


               /* newScheduleTimeTableAdpter = new New_Schedule_TimeTableAdpter(getApplicationContext(),SatArray);
                rec_dayList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                rec_dayList.setAdapter(newScheduleTimeTableAdpter);
                newScheduleTimeTableAdpter.notifyDataSetChanged();
*/
            }
        });
        card_view_E.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                viewList.setVisibility(View.GONE);
                txt_today.setVisibility(View.GONE);
                if (URLEndPoints.ConstanceLoginUser.equalsIgnoreCase("Student")) {
                    card_view_Mon.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    card_view_Tue.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    card_view_Wed.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    card_view_Thu.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    card_view_Fir.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    card_view_Sat.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    card_view_Sun.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    Mon.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.text_blue));
                    Tue.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.text_blue));
                    Wed.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.text_blue));
                    Thu.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.text_blue));
                    Fir.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.text_blue));
                    Sat.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.text_blue));
                    Sun.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.text_blue));
                    card_view_E.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.text_blue));
                    E.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                }else {
                    card_view_Mon.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    card_view_Tue.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    card_view_Wed.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    card_view_Thu.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    card_view_Fir.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    card_view_Sat.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    card_view_Sun.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    Mon.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.present));
                    Tue.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.present));
                    Wed.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.present));
                    Thu.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.present));
                    Fir.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.present));
                    Sat.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.present));
                    Sun.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.present));
                    card_view_E.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.present));
                    E.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                }
                Snackbar.make(lytTimeTable, "Record not available.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

               /* newScheduleTimeTableAdpter = new New_Schedule_TimeTableAdpter(getApplicationContext(),SatArray);
                rec_dayList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                rec_dayList.setAdapter(newScheduleTimeTableAdpter);
                newScheduleTimeTableAdpter.notifyDataSetChanged();
*/
            }
        });
    }
    private void getExtraLectureData() {
        progBarTimeTable.setVisibility(View.VISIBLE);
        String url = pref.getURL() + URLEndPoints.GetStudentExtraLecture_URL(studCenterCode,studSessionId,studBranchStandardId,studSemesterType,student_id);
        Log.d(TAG, "Student Extra Time table : " + url);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, response.toString());
                try {

                    Gson gson = new Gson();
                    extraLecModel = gson.fromJson(response, ExtraLecModel.class);

                    if (extraLecModel.getStatus() == 1) {

                        extraLecArrayList = (ArrayList<ExtraLecModel.DataBean>) extraLecModel.getData();

                        if (extraLecArrayList != null) {
/*
                            extraLecAdapter = new ExtraLecAdapter(getActivity(), extraLecArrayList);
                            Intent intent = new Intent(getActivity(), ExtraLectureActivity.class);
                            startActivity(intent);
                            fragSchedule.dismiss();
                            getActivity().finish();*/

                        } else {
                            Snackbar.make(lytTimeTable, "Record not available.", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        }
                    } else if (extraLecModel.getStatus() == 0) {

                        Snackbar.make(lytTimeTable, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }


                  progBarTimeTable.setVisibility(View.GONE);

                } catch (Exception e) {

                    Snackbar.make(lytTimeTable, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                    Log.d(TAG, "Error : " + e.getMessage());

                    progBarTimeTable.setVisibility(View.GONE);
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

    private void getTimetbleData() {

        utilityClassObj.startLoader(this,R.drawable.image_for_rotation);
        String url = pref.getURL() + URLEndPoints.newGetSelfTimetable_URL(studSessionId,studSemesterType,studBranchStandardId,studCenterCode,student_id);
        Log.d(TAG, "Student Time table : " + url);


        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, response.toString());

                try {

                    Gson gson = new Gson();
                    studTimeTableModel = gson.fromJson(response, StudTimeTableModel.class);

                    if (studTimeTableModel.getStatus() == 1) {

                        recordArrayList = (ArrayList<StudTimeTableModel.DataBean>) studTimeTableModel.getData();
                        // selfSubCodeArrayList = (ArrayList<SelfTimeTblModel.SubCodeBean>) selfTimeTblModel.getSubCode();
                        utilityClassObj.stopLoader();




                      /*  if (recordArrayList.size() != 0 && recordArrayList != null) {

                            weekArrayList = new ArrayList<>();
                            for (int i = 0; i < recordArrayList.size(); i++) {

                                String WEEK_DESCRIPTION = recordArrayList.get(i).getWEEK_DESCRIPTION();
                                weekArrayList.add(WEEK_DESCRIPTION);

                            }

                            Object[] st = weekArrayList.toArray();
                            for (Object s : st) {
                                if (weekArrayList.indexOf(s) != weekArrayList.lastIndexOf(s)) {
                                    weekArrayList.remove(weekArrayList.lastIndexOf(s));
                                }
                            }
                            for (String week : weekArrayList) {
                                Log.d(TAG, "\nDay : " + week);
                            }
                            Log.d(TAG, " weekArrayList Size : " + weekArrayList.size());


                            periodSquenceArrayList = new ArrayList<>();
                            for (int i = 0; i < recordArrayList.size(); i++) {

                                String PERIOD_SEQUENCE_NO = recordArrayList.get(i).getPERIOD_SEQUENCE_NO();
                                if (!PERIOD_SEQUENCE_NO.isEmpty()) {
                                    periodSquenceArrayList.add(PERIOD_SEQUENCE_NO);
                                }
                            }

                            Object[] st1 = periodSquenceArrayList.toArray();
                            for (Object s : st1) {
                                if (periodSquenceArrayList.indexOf(s) != periodSquenceArrayList.lastIndexOf(s)) {
                                    periodSquenceArrayList.remove(periodSquenceArrayList.lastIndexOf(s));
                                }
                            }

                            for (String prdSqu : periodSquenceArrayList) {
                                Log.d(TAG, "\nPeriod Sequence : " + prdSqu);
                            }
                            Log.d(TAG, " periodSquenceArrayList Size : " + periodSquenceArrayList.size());


                            String WEEK_DESCRIPTION = recordArrayList.get(0).getWEEK_DESCRIPTION();

                            timePrdSquenceArray = new ArrayList<>();

                            timePrdSquenceArray.add("Time : \nDay ");

                            for (int i = 0; i < recordArrayList.size(); i++) {

                                if (WEEK_DESCRIPTION.equalsIgnoreCase(recordArrayList.get(i).getWEEK_DESCRIPTION())) {

                                    String PERIOD_SEQUENCE_NO = recordArrayList.get(i).getPERIOD_SEQUENCE_NO();
                                    String PERIOD_FROM_TIME = recordArrayList.get(i).getPERIOD_FROM_TIME(); //"12/18/2018 11:05:00 AM"
                                    String PERIOD_UPTO_TIME = recordArrayList.get(i).getPERIOD_UPTO_TIME(); //"12/18/2018 11:05:00 AM"

                                    if (!PERIOD_SEQUENCE_NO.isEmpty() && !PERIOD_FROM_TIME.equalsIgnoreCase("Recess") && !PERIOD_UPTO_TIME.equalsIgnoreCase("Recess")) {
                                        if (!PERIOD_FROM_TIME.isEmpty() && PERIOD_FROM_TIME != "" && PERIOD_FROM_TIME != null) {
                                            String[] a = PERIOD_FROM_TIME.split(Pattern.quote(" "));
                                            String[] b = a[1].split(Pattern.quote(":"));

                                            if (a.length == 3) {
                                                PERIOD_FROM_TIME = b[0] + ":" + b[1] + " " + a[2];
                                            } else {
                                                PERIOD_FROM_TIME = b[0] + ":" + b[1];
                                            }

                                        }

                                        if (!PERIOD_UPTO_TIME.isEmpty() && PERIOD_UPTO_TIME != "" && PERIOD_UPTO_TIME != null) {
                                            String[] a = PERIOD_UPTO_TIME.split(Pattern.quote(" "));
                                            String[] b = a[1].split(Pattern.quote(":"));

                                            if (a.length == 3) {
                                                PERIOD_UPTO_TIME = b[0] + ":" + b[1] + " " + a[2];
                                            } else {
                                                PERIOD_UPTO_TIME = b[0] + ":" + b[1];
                                            }

                                        }

                                        String fromTime="";
                                        String uptoTime="";
                                        try {
                                            // PERIOD_FROM_TIME = "22:15";
                                            SimpleDateFormat _24HourSDF = new SimpleDateFormat("HH:mm");
                                            SimpleDateFormat _12HourSDF = new SimpleDateFormat("hh:mm a");
                                            Date _24HourDt = _24HourSDF.parse(PERIOD_FROM_TIME);
                                            System.out.println(_24HourDt);
                                            fromTime=_12HourSDF.format(_24HourDt);
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }

                                        try {
                                            // PERIOD_FROM_TIME = "22:15";
                                            SimpleDateFormat _24HourSDF = new SimpleDateFormat("HH:mm");
                                            SimpleDateFormat _12HourSDF = new SimpleDateFormat("hh:mm a");
                                            Date _24HourDt = _24HourSDF.parse(PERIOD_UPTO_TIME);
                                            System.out.println(_24HourDt);
                                            uptoTime=_12HourSDF.format(_24HourDt);
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }

                                        String time = fromTime + " to " + uptoTime + "\n(" + PERIOD_SEQUENCE_NO + ")";
                                        timePrdSquenceArray.add(time);

                                    }

                                }

                            }


                            for (String time : timePrdSquenceArray) {
                                Log.d(TAG, "\nTime : " + time);
                            }
                            Log.d(TAG, " timePrdSquenceArray Size : " + timePrdSquenceArray.size());

                            Object[] st2 = timePrdSquenceArray.toArray();
                            for (Object s : st2) {
                                if (timePrdSquenceArray.indexOf(s) != timePrdSquenceArray.lastIndexOf(s)) {
                                    timePrdSquenceArray.remove(timePrdSquenceArray.lastIndexOf(s));
                                }
                            }
                            for (String time : timePrdSquenceArray) {
                                Log.d(TAG, "\nTime : " + time);
                            }
                            Log.d(TAG, " timePrdSquenceArray Size : " + timePrdSquenceArray.size());


                          *//*  newScheduleTimeTableAdpter = new New_Schedule_TimeTableAdpter(this,timePrdSquenceArray);
                            rec_dayList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                            rec_dayList.setAdapter(newScheduleTimeTableAdpter);
*//*
*//*

                            addHeaders();
                            Intent intent = new Intent(getActivity(), NewTimetblActivity.class);
                            intent.putExtra("student", studentName);
                            intent.putExtra("year", studAcadSessName);
                            intent.putExtra("semesetr", studMainSemName);
                            intent.putExtra("section", studBranchStandDesc);
                            startActivity(intent);
                            fragSchedule.dismiss();
                            getActivity().finish();
*//*









                        } else {
                            Snackbar.make(lytTimeTable, "Time Table record not available.", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        }*/


                        if (recordArrayList.size() != 0 && recordArrayList != null) {
                            for (int i = 0; i < recordArrayList.size(); i++) {
                                String TodayDay = recordArrayList.get(i).getWEEK_DESCRIPTION();

                                if (TodayDay.equalsIgnoreCase("MONDAY")) {
                                    MonArray.add(recordArrayList.get(i));
                                } else if (TodayDay.equalsIgnoreCase("TUESDAY")) {
                                    TueArray.add(recordArrayList.get(i));
                                } else if (TodayDay.equalsIgnoreCase("WEDNESDAY")) {
                                    WedArray.add(recordArrayList.get(i));

                                } else if (TodayDay.equalsIgnoreCase("THURSDAY")) {
                                    ThuArray.add(recordArrayList.get(i));
                                } else if (TodayDay.equalsIgnoreCase("FRIDAY")) {
                                    FirArray.add(recordArrayList.get(i));

                                } else if (TodayDay.equalsIgnoreCase("SATURDAY")) {

                                    SatArray.add(recordArrayList.get(i));

                                }
                            }

                        }

                        if (TodayDate.equalsIgnoreCase("MONDAY")) {
                            txt_today_date.setText(MonArray.get(0).getDate());
                            if (URLEndPoints.ConstanceLoginUser.equalsIgnoreCase("Student")) {
                                txt_today_date.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.text_blue));
                                card_view_Mon.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.text_blue));
                            }else {
                                txt_today_date.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.present));
                                card_view_Mon.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.present));
                            }

                            Mon.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                            newScheduleTimeTableAdpter = new New_Schedule_TimeTableAdpter(getApplicationContext(),MonArray);
                            rec_dayList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                            rec_dayList.setAdapter(newScheduleTimeTableAdpter);
                            txt_today.setVisibility(View.VISIBLE);
                        }else if (TodayDate.equalsIgnoreCase("TUESDAY")) {
                            txt_today_date.setText(TueArray.get(0).getDate());
                            if (URLEndPoints.ConstanceLoginUser.equalsIgnoreCase("Student")) {
                                txt_today_date.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.text_blue));
                                card_view_Tue.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.text_blue));
                            }else {
                                txt_today_date.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.present));
                                card_view_Tue.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.present));
                            }
                            Tue.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                            newScheduleTimeTableAdpter = new New_Schedule_TimeTableAdpter(getApplicationContext(),TueArray);
                            rec_dayList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                            rec_dayList.setAdapter(newScheduleTimeTableAdpter);
                            txt_today.setVisibility(View.VISIBLE);
                        } else if (TodayDate.equalsIgnoreCase("WEDNESDAY")) {

                            txt_today_date.setText(WedArray.get(0).getDate());
                            if (URLEndPoints.ConstanceLoginUser.equalsIgnoreCase("Student")) {
                                txt_today_date.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.text_blue));
                                card_view_Wed.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.text_blue));
                            }else {
                                txt_today_date.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.present));
                                card_view_Wed.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.present));
                            }

                            Wed.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                            newScheduleTimeTableAdpter = new New_Schedule_TimeTableAdpter(getApplicationContext(),WedArray);
                            rec_dayList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                            rec_dayList.setAdapter(newScheduleTimeTableAdpter);
                            txt_today.setVisibility(View.VISIBLE);
                        } else if (TodayDate.equalsIgnoreCase("THURSDAY")) {
                            txt_today_date.setText(ThuArray.get(0).getDate());
                            if (URLEndPoints.ConstanceLoginUser.equalsIgnoreCase("Student")) {
                                txt_today_date.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.text_blue));
                                Thu.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                                card_view_Thu.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.text_blue));
                            }else {
                                txt_today_date.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.present));
                                Thu.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                                card_view_Thu.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.present));
                            }



                            newScheduleTimeTableAdpter = new New_Schedule_TimeTableAdpter(getApplicationContext(),ThuArray);
                            rec_dayList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                            rec_dayList.setAdapter(newScheduleTimeTableAdpter);

                        } else if (TodayDate.equalsIgnoreCase("FRIDAY")) {

                            txt_today_date.setText(FirArray.get(0).getDate());
                            if (URLEndPoints.ConstanceLoginUser.equalsIgnoreCase("Student")) {
                                txt_today_date.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.text_blue));
                                card_view_Fir.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.text_blue));
                            }else {
                                txt_today_date.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.present));
                                card_view_Fir.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.present));
                            }
                            Fir.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                            newScheduleTimeTableAdpter = new New_Schedule_TimeTableAdpter(getApplicationContext(),FirArray);
                            rec_dayList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                            rec_dayList.setAdapter(newScheduleTimeTableAdpter);
                            txt_today.setVisibility(View.VISIBLE);

                        } else if (TodayDate.equalsIgnoreCase("SATURDAY")) {
                            txt_today_date.setText(SatArray.get(0).getDate());
                            if (URLEndPoints.ConstanceLoginUser.equalsIgnoreCase("Student")) {
                                txt_today_date.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.text_blue));
                                card_view_Sat.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.text_blue));
                            }else {
                                txt_today_date.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.present));
                                card_view_Sat.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.present));
                            }
                            Sat.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                            newScheduleTimeTableAdpter = new New_Schedule_TimeTableAdpter(getApplicationContext(),SatArray);
                            rec_dayList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                            rec_dayList.setAdapter(newScheduleTimeTableAdpter);

                        }else if (TodayDate.equalsIgnoreCase("SUNDAY")){
                            if (URLEndPoints.ConstanceLoginUser.equalsIgnoreCase("Student")) {
                                card_view_Sun.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.absent));
                            }else {
                                card_view_Sun.setCardBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.absent));
                            }
                            Snackbar.make(lytTimeTable, "Record not available.", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                            txt_today.setVisibility(View.GONE);
                            txt_today.setVisibility(View.VISIBLE);
                        }


                    } else {
                        utilityClassObj.stopLoader();
                        Snackbar.make(lytTimeTable, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }


                } catch (Exception e) {

                    utilityClassObj.stopLoader();
                    Snackbar.make(lytTimeTable, "Server not responding.Please try later.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    Log.d(TAG, "Error : " + e.getMessage());

                }



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Error: " + error.getMessage());
                handleVolleyError(error);
                utilityClassObj.stopLoader();
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

        Toast.makeText(this, ""+message, Toast.LENGTH_SHORT).show();
    }

}
