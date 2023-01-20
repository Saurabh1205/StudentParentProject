package com.wordpro.studentproject.activities.syllabus_menu;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.wordpro.studentproject.R;
import com.wordpro.studentproject.activities.NavigationActivity;
import com.wordpro.studentproject.adapter.SyllabusAbstractAdpter;

import java.util.ArrayList;

import static com.wordpro.studentproject.activities.NavigationActivity.studAcadSessName;
import static com.wordpro.studentproject.activities.NavigationActivity.studBranchStandDesc;
import static com.wordpro.studentproject.activities.NavigationActivity.studMainSemName;
import static com.wordpro.studentproject.activities.NavigationActivity.studentName;
import static com.wordpro.studentproject.fragment.FragSyllabus.syllStatusArrayList;
import static com.wordpro.studentproject.network.Network.isNetworkAvailable;

public class SyllStatusActivity extends Activity {

    RecyclerView recyVwSyllStatus;
    SyllabusAbstractAdpter syllabusAbstractAdpter;
    TextView txtgraphview,txtSyllStatus;
    TextView txtStudName,txtYear,txtSem,txtSection,txtSubjName,txtFacultyName;

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
            setContentView(R.layout.activity_syll_status);

            Typeface typefaceHeading = Typeface.createFromAsset(getAssets(), "font/Poppins-BoldItalic.otf");
            Typeface typefaceContent = Typeface.createFromAsset(getAssets(), "font/Poppins-Medium.otf");

            txtSyllStatus=(TextView)findViewById(R.id.txtSyllStatus);
            txtSyllStatus.setTypeface(typefaceHeading);
            recyVwSyllStatus=(RecyclerView)findViewById(R.id.recyVwSyllStatus);
            txtStudName=(TextView)findViewById(R.id.txtStudName);
            txtStudName.setTypeface(typefaceContent);
            String studName=studentName.toUpperCase();
            txtStudName.setText(studName);
            txtYear=(TextView)findViewById(R.id.txtYear);
            txtYear.setTypeface(typefaceContent);
            txtYear.setText(studAcadSessName.toUpperCase());
            txtSem=(TextView)findViewById(R.id.txtSem);
            txtSem.setTypeface(typefaceContent);
            txtSem.setText(studMainSemName.toUpperCase());
            txtSection=(TextView)findViewById(R.id.txtSection);
            txtSection.setTypeface(typefaceContent);
            txtSection.setText(studBranchStandDesc.toUpperCase());

            syllabusAbstractAdpter = new SyllabusAbstractAdpter(SyllStatusActivity.this, syllStatusArrayList);
            recyVwSyllStatus.setLayoutManager(new LinearLayoutManager(SyllStatusActivity.this));
            recyVwSyllStatus.setAdapter(syllabusAbstractAdpter);

            txtgraphview=(TextView)findViewById(R.id.txtgraphview);
            txtgraphview.setTypeface(typefaceHeading);
            txtgraphview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent=new Intent(SyllStatusActivity.this,GraphViewActivity.class);
                    startActivity(intent);
                    finish();

                }
            });
        }
    }



    @Override
    public void onBackPressed() {
        Intent intent=new Intent(SyllStatusActivity.this, NavigationActivity.class);
        intent.putExtra("activity","SyllStatusActivity");
        startActivity(intent);
        finish();
    }
}
