package com.wordpro.studentproject.activities;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Switch;
import android.widget.TextView;

import com.wordpro.studentproject.R;
import com.wordpro.studentproject.fragment.AttendanceDynamicFragmen;
import com.wordpro.studentproject.fragment.AttendanceSubject_WiseFragment;
import com.wordpro.studentproject.fragment.Attendance_Net_WiseFragment;
import com.wordpro.studentproject.fragment.Attendance_Span_WiseFragment;
import com.wordpro.studentproject.fragment.E_Lear_AssignmentFragment;
import com.wordpro.studentproject.fragment.E_Lear_NotesFragment;
import com.wordpro.studentproject.fragment.HomeFragment;
import com.wordpro.studentproject.webConfig.URLEndPoints;

public class E_Learning_Activity extends AppCompatActivity implements View.OnClickListener {
    public static Fragment fragment;
    FrameLayout  container_e_ler;
    private LinearLayout bottom_lay;
    private TextView  act_pub_assi,act_sub_assi,txt_toolbarName;
    private ImageView  back_press,setting_option;
    private LinearLayout belowId;
    private PopupWindow mpopup;
    private CardView card_view2;
    private  LinearLayout lay_assignment,lay_Notes;
    private TextView   txt_assignment,txt_Notes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_e__learning_);
        txt_toolbarName =(TextView)findViewById(R.id.txt_toolbarName);
        back_press =(ImageView)findViewById(R.id.back_press);
       /* act_pub_assi =(TextView)findViewById(R.id.act_pub_assi);
        act_sub_assi =(TextView)findViewById(R.id.act_sub_assi);*/
        container_e_ler =(FrameLayout) findViewById(R.id.container_e_ler);
        //bottom_lay  =(LinearLayout) findViewById(R.id.bottom_lay);
        belowId=(LinearLayout)   findViewById(R.id.belowId);
        setting_option  =(ImageView)findViewById(R.id.setting_option);
        back_press.setOnClickListener(this);
       /* act_pub_assi.setOnClickListener(this);
        act_sub_assi.setOnClickListener(this);
*/

        try {
            belowId.setVisibility(View.VISIBLE);
            txt_toolbarName.setText("Assignment - (Published)");
            fragment = new E_Lear_AssignmentFragment();
            Bundle bundle=new Bundle();
            //  txt_toolbarName.setText("Assignment - (Published Assignments)");
            bundle.putString("assignment", "Assignment");
            fragment.setArguments(bundle);
            loadFragment(fragment);
        }catch (Exception e){
            e.printStackTrace();
        }

        // act_pub_assi.setTextColor(ContextCompat.getColor(this, R.color.white));
        //  act_pub_assi.setBackground(ContextCompat.getDrawable(this,R.color.selector_color));


        setting_option.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                View popUpView = getLayoutInflater().inflate(R.layout.assignment_fragment_view,
                        null); // inflating popup layout
                mpopup = new PopupWindow(popUpView, ActionBar.LayoutParams.FILL_PARENT,
                        ActionBar.LayoutParams.WRAP_CONTENT, true); // Creation of popup
                mpopup.setAnimationStyle(android.R.style.Animation_Dialog);
                mpopup.showAtLocation(popUpView, Gravity.TOP, 0, 0);
                Typeface type_faceHeading = Typeface.createFromAsset(getAssets(), "font/Poppins-Bold.ttf");
                Typeface type_faceContent = Typeface.createFromAsset(getAssets(), "font/Poppins-Regular.ttf");
                lay_assignment =(LinearLayout)popUpView.findViewById(R.id.lay_assignment);
                lay_Notes=(LinearLayout)popUpView.findViewById(R.id.lay_Notes);
                txt_assignment =(TextView)popUpView.findViewById(R.id.txt_assignment);
                txt_Notes=(TextView)popUpView.findViewById(R.id.txt_Notes);

                txt_assignment.setTypeface(type_faceHeading);
                txt_Notes.setTypeface(type_faceHeading);
                txt_toolbarName.setTypeface(type_faceHeading);
                lay_assignment.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        fragment = new E_Lear_AssignmentFragment();
                        Bundle bundle=new Bundle();
                        txt_toolbarName.setText("Assignment - (Published)");
                        bundle.putString("assignment", "Assignment");
                        fragment.setArguments(bundle);
                        loadFragment(fragment);
                        mpopup.dismiss();
                    }
                });

                lay_Notes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        fragment = new E_Lear_NotesFragment();
                        Bundle bundle=new Bundle();
                        txt_toolbarName.setText("Notes");
                        bundle.putString("notes", "Notes");
                        fragment.setArguments(bundle);
                        loadFragment(fragment);
                        mpopup.dismiss();
                    }
                });

            }
        });



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_press:
                Intent intent = new Intent(this, NavigationActivity.class);
                startActivity(intent);
                finish();
                //onBackPressed();
                break;

        }
    }


    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container_e_ler, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
