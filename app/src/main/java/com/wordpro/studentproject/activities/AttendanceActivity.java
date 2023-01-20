package com.wordpro.studentproject.activities;

import android.app.ActionBar;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.fenchtose.tooltip.Tooltip;
import com.wordpro.studentproject.R;
import com.wordpro.studentproject.adapter.SubjectWiseAttendanceAdpter;
import com.wordpro.studentproject.fragment.AttendanceDynamicFragmen;
import com.wordpro.studentproject.fragment.AttendanceSubject_WiseFragment;
import com.wordpro.studentproject.fragment.Attendance_Net_WiseFragment;
import com.wordpro.studentproject.fragment.Attendance_Span_WiseFragment;
import com.wordpro.studentproject.utils.UtilityClass;
import com.wordpro.studentproject.webConfig.URLEndPoints;

import java.util.ArrayList;
import java.util.List;

import static com.wordpro.studentproject.R.id.frame_container_Action;

public class AttendanceActivity extends AppCompatActivity {
    ViewPager viewPager;
    TabLayout tabLayout;
    List<View> views;
    private  PagerAdapter pagerAdapter;
    int no_of_categories=-1;
    private ImageView  back_press,setting_option;
    private TextView   txt_toolbarName;
    ArrayList<String> allContactNames = new ArrayList<>();
    TypedArray allContacts;
    Tooltip.Builder customTooltip;
    private TextView  txt_day_wise,txt_sub_wise,txt_span_wise,txt_net_wise;
    private ImageView img_day_wise,img_sub_wise,img_span_wise,img_net_wise;
    private LinearLayout lay_day_wise,lay_sub_wise,lay_span_wise,lay_net_wise,view;
    public Fragment fragment;
    PopupWindow mpopup;
    private LinearLayout belowId;
    private UtilityClass utilityClassObj;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);
        belowId =(LinearLayout) findViewById(R.id.belowId);
        back_press =(ImageView)findViewById(R.id.back_press);
        txt_toolbarName= (TextView)findViewById(R.id.txt_toolbarName);
        setting_option  =(ImageView)findViewById(R.id.setting_option);

        if (URLEndPoints.ConstanceLoginUser.equalsIgnoreCase("Student")){
            belowId.setBackgroundColor(ContextCompat.getColor(this, R.color.text_blue));
        }else {
            belowId.setBackgroundColor(ContextCompat.getColor(this, R.color.present));
        }

        txt_toolbarName.setText("ATTENDANCE - (Day-Wise)");
        fragment = new AttendanceDynamicFragmen();
        Bundle bundle=new Bundle();
        txt_toolbarName.setText("ATTENDANCE - (Day-Wise)");
        bundle.putString("Day_Wise", "day_wise_att");
        loadFragmentMain(fragment);

        back_press.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), NavigationActivity.class);
                startActivity(intent);
                finish();
            }
        });


        setting_option.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                View popUpView = getLayoutInflater().inflate(R.layout.attendance_fragment_view,
                        null); // inflating popup layout
                mpopup = new PopupWindow(popUpView, ActionBar.LayoutParams.FILL_PARENT,
                        ActionBar.LayoutParams.WRAP_CONTENT, true); // Creation of popup
                mpopup.setAnimationStyle(android.R.style.Animation_Dialog);
                mpopup.showAtLocation(popUpView, Gravity.TOP, 0, 0);
                Typeface type_faceHeading = Typeface.createFromAsset(getAssets(), "font/Poppins-Bold.ttf");
                Typeface type_faceContent = Typeface.createFromAsset(getAssets(), "font/Poppins-Regular.ttf");
                lay_day_wise =(LinearLayout)popUpView.findViewById(R.id.lay_day_wise);
                lay_sub_wise=(LinearLayout)popUpView.findViewById(R.id.lay_sub_wise);
                lay_span_wise=(LinearLayout)popUpView.findViewById(R.id.lay_span_wise);
                lay_net_wise=(LinearLayout)popUpView.findViewById(R.id.lay_net_wise);
                view =(LinearLayout)popUpView.findViewById(R.id.view);
                txt_day_wise =(TextView)popUpView.findViewById(R.id.txt_day_wise);
                txt_sub_wise=(TextView)popUpView.findViewById(R.id.txt_sub_wise);
                txt_span_wise=(TextView)popUpView.findViewById(R.id.txt_span_wise);
                txt_net_wise=(TextView)popUpView.findViewById(R.id.txt_net_wise);

                txt_day_wise.setTypeface(type_faceHeading);
                txt_sub_wise.setTypeface(type_faceHeading);
                txt_span_wise.setTypeface(type_faceHeading);
                txt_net_wise.setTypeface(type_faceHeading);

                lay_day_wise.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        belowId.setVisibility(View.VISIBLE);
                        fragment = new AttendanceDynamicFragmen();
                        Bundle bundle=new Bundle();
                        txt_toolbarName.setText("Attendance- (Day-Wise)");
                        bundle.putString("Day_Wise", "day_wise_att");
                        fragment.setArguments(bundle);
                        loadFragmentMain(fragment);
                        mpopup.dismiss();
                    }
                });

                lay_sub_wise.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        belowId.setVisibility(View.GONE);
                        Bundle bundle=new Bundle();
                        bundle.putString("Subject_Wise", "Subject_Wise_att");
                        txt_toolbarName.setText("Attendance - (Subject-Wise)");
                        fragment = new AttendanceSubject_WiseFragment();
                        loadFragmentMain(fragment);
                        mpopup.dismiss();
                    }
                });

                lay_span_wise.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        belowId.setVisibility(View.GONE);
                        txt_toolbarName.setText("Attendance - (Span-Wise)");
                        Bundle bundle=new Bundle();
                        bundle.putString("Span_Wise", "Span_Wise_att");
                        fragment = new Attendance_Span_WiseFragment();
                        loadFragmentMain(fragment);
                        mpopup.dismiss();
                    }
                });

                lay_net_wise.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        belowId.setVisibility(View.GONE);
                        txt_toolbarName.setText("Attendance - (Net)");
                        fragment = new Attendance_Net_WiseFragment();
                        loadFragmentMain(fragment);
                        mpopup.dismiss();
                    }
                });
            }
        });


    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), NavigationActivity.class);
        startActivity(intent);
        finish();
    }
    private void loadFragmentMain(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(frame_container_Action, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
