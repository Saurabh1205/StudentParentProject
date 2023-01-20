package com.wordpro.studentproject.activities.syllabus_menu;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.wordpro.studentproject.R;
import com.wordpro.studentproject.model.TeachPlanDataModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.wordpro.studentproject.activities.NavigationActivity.studAcadSessName;
import static com.wordpro.studentproject.activities.NavigationActivity.studBranchStandDesc;
import static com.wordpro.studentproject.activities.NavigationActivity.studMainSemName;
import static com.wordpro.studentproject.activities.NavigationActivity.studentName;
import static com.wordpro.studentproject.activities.syllabus_menu.TeachPlanSubjActivity.teachPlanAdapter;
import static com.wordpro.studentproject.activities.syllabus_menu.TeachPlanSubjActivity.teachPlanDataArrayList;
import static com.wordpro.studentproject.network.Network.isNetworkAvailable;

public class TeachingPlanActivity extends Activity {

        public static List<String> unitList;
        private static String TAG = TeachingPlanActivity.class.getSimpleName();
        RecyclerView recyVwTeachPlan;
        TextView txtStudName,txtYear,txtSem,txtSection,txtSubjName,txtFacultyName,txtTeachingPlan;
        String subjectName,profName;

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

            setContentView(R.layout.activity_teaching_plan);

            Typeface typefaceHeading = Typeface.createFromAsset(getAssets(), "font/Poppins-BoldItalic.otf");
            Typeface typefaceContent = Typeface.createFromAsset(getAssets(), "font/Poppins-Medium.otf");

            Intent intent = getIntent();
            Bundle extras = intent.getExtras();
            subjectName = extras.getString("subjectName");
            profName=extras.getString("profName");

            txtTeachingPlan=(TextView)findViewById(R.id.txtTeachingPlan);
            txtTeachingPlan.setTypeface(typefaceHeading);
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

            recyVwTeachPlan=(RecyclerView)findViewById(R.id.recyVwTeachPlan);
            recyVwTeachPlan.setLayoutManager(new LinearLayoutManager(TeachingPlanActivity.this));
            recyVwTeachPlan.setAdapter(teachPlanAdapter);

        }
    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(TeachingPlanActivity.this,TeachPlanSubjActivity.class);
        startActivity(intent);
        finish();
    }
}
