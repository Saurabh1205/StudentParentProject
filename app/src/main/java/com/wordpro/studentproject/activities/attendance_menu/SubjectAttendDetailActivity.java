package com.wordpro.studentproject.activities.attendance_menu;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import com.wordpro.studentproject.R;
import com.wordpro.studentproject.adapter.IndicatorAdapter;

import java.util.ArrayList;
import java.util.List;

import static com.wordpro.studentproject.activities.NavigationActivity.studAcadSessName;
import static com.wordpro.studentproject.activities.NavigationActivity.studBranchStandDesc;
import static com.wordpro.studentproject.activities.NavigationActivity.studMainSemName;
import static com.wordpro.studentproject.activities.NavigationActivity.studentName;
import static com.wordpro.studentproject.activities.attendance_menu.SubjectWiseAttendActivity.subjWiseAttendAdapter;
import static com.wordpro.studentproject.network.Network.isNetworkAvailable;

public class SubjectAttendDetailActivity extends Activity {

    RecyclerView recyVwSubjAttend,recyViewIndicators;
    TextView txtStudName,txtYear,txtSem,txtSection,txtSubjName,txtFacultyName,txtSubjAttnd;
    String subjectName,profName,PERIOD_START_DATE,PERIOD_END_DATE;
    List<String> statusList;
    IndicatorAdapter indicatorAdapter;
    Typeface typefaceHeading,typefaceContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!isNetworkAvailable(this)) {
            // Create an Alert Dialog
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            // Set the Alert Dialog Message
            builder.setMessage("Internet Connection Required")
                    .setCancelable(false)
                    .setPositiveButton("Retry",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int id) {
                                    // Restart the Activity
                                    Intent intent = getIntent();
                                    finish();
                                    startActivity(intent);
                                }
                            });
            AlertDialog alert = builder.create();
            alert.show();
        } else {
            setContentView(R.layout.activity_subject_attend_detail);

            typefaceHeading = Typeface.createFromAsset(getAssets(), "font/Poppins-BoldItalic.otf");
            typefaceContent = Typeface.createFromAsset(getAssets(), "font/Poppins-Medium.otf");

            Intent intent = getIntent();
            Bundle extras = intent.getExtras();
            subjectName = extras.getString("subjectName");
            profName=extras.getString("profName");
            PERIOD_START_DATE = extras.getString("PERIOD_START_DATE");
            PERIOD_END_DATE=extras.getString("PERIOD_END_DATE");

            txtSubjAttnd=(TextView)findViewById(R.id.txtSubjAttnd1);
            txtSubjAttnd.setTypeface(typefaceHeading);
            txtStudName=(TextView)findViewById(R.id.txtStudName);
            txtStudName.setTypeface(typefaceContent);
            txtStudName.setText(studentName);
            txtYear=(TextView)findViewById(R.id.txtYear);
            txtYear.setTypeface(typefaceContent);
            txtYear.setText(studAcadSessName);
            txtSem=(TextView)findViewById(R.id.txtSem);
            txtSem.setTypeface(typefaceContent);
            txtSem.setText(studMainSemName);
            txtSection=(TextView)findViewById(R.id.txtSection);
            txtSection.setTypeface(typefaceContent);
            txtSection.setText(studBranchStandDesc);
            txtFacultyName=(TextView)findViewById(R.id.txtFacultyName);
            txtFacultyName.setTypeface(typefaceContent);
            txtFacultyName.setText(profName);
            txtSubjName=(TextView)findViewById(R.id.txtSubjName);
            txtSubjName.setTypeface(typefaceContent);
            txtSubjName.setText(subjectName);

            recyVwSubjAttend=(RecyclerView)findViewById(R.id.recyVwSubjAttend);
            recyVwSubjAttend.setLayoutManager(new LinearLayoutManager(SubjectAttendDetailActivity.this));
            recyVwSubjAttend.setAdapter(subjWiseAttendAdapter);

            statusList = new ArrayList<>();
            statusList.add("Present");
            statusList.add("Absent");
            statusList.add("College Work");
            statusList.add("Leave");
            statusList.add("Class Absenteeism");
            statusList.add("Faculty Leave");
            statusList.add("College Event");
            statusList.add("Holiday");
            statusList.add("Not Updated");

            recyViewIndicators = (RecyclerView) findViewById(R.id.recyViewIndicators);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(SubjectAttendDetailActivity.this, LinearLayoutManager.HORIZONTAL, false);
            recyViewIndicators.setLayoutManager(layoutManager);
            recyViewIndicators.setItemAnimator(new DefaultItemAnimator());
            recyViewIndicators.addItemDecoration(new DividerItemDecoration(SubjectAttendDetailActivity.this, LinearLayoutManager.VERTICAL));
            indicatorAdapter = new IndicatorAdapter(SubjectAttendDetailActivity.this, statusList);
            recyViewIndicators.setAdapter(indicatorAdapter);


        }
    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(SubjectAttendDetailActivity.this,SubjectWiseAttendActivity.class);
        intent.putExtra("PERIOD_START_DATE",PERIOD_START_DATE);
        intent.putExtra("PERIOD_END_DATE",PERIOD_END_DATE);
        startActivity(intent);
        finish();
    }
}
