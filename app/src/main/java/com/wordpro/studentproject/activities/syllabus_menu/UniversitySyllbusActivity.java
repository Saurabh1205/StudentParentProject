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
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.wordpro.studentproject.R;
import com.wordpro.studentproject.adapter.UnivSyllabusExpandAdpter;

import java.util.HashMap;
import java.util.List;

import static com.wordpro.studentproject.activities.NavigationActivity.studAcadSessName;
import static com.wordpro.studentproject.activities.NavigationActivity.studBranchStandDesc;
import static com.wordpro.studentproject.activities.NavigationActivity.studMainSemName;
import static com.wordpro.studentproject.activities.NavigationActivity.studentName;
import static com.wordpro.studentproject.activities.syllabus_menu.UnivSubjectActivity.univSyllabusExpandAdpter;
import static com.wordpro.studentproject.network.Network.isNetworkAvailable;

public class UniversitySyllbusActivity extends Activity {

    ExpandableListView lvExpUnivSyll;

    TextView txtStudName;
    TextView txtYear;
    TextView txtSem;
    TextView txtSection;
    TextView txtSubjectName,txtUnivSyllbus,txtweight,txttotLec;
    String subjectName;


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

            setContentView(R.layout.activity_university_syllbus);

            Typeface typefaceHeading = Typeface.createFromAsset(getAssets(), "font/Poppins-BoldItalic.otf");
            Typeface typefaceContent = Typeface.createFromAsset(getAssets(), "font/Poppins-Medium.otf");

            Intent intent = getIntent();
            Bundle extras = intent.getExtras();
            subjectName = extras.getString("SUBJECT_DESCRIPTION");
            lvExpUnivSyll=(ExpandableListView) findViewById(R.id.lvExpUnivSyll);

            txtUnivSyllbus=(TextView)findViewById(R.id.txtUnivSyllbus);
            txtUnivSyllbus.setTypeface(typefaceHeading);
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
            txtSubjectName=(TextView)findViewById(R.id.txtSubjectName);
            txtSubjectName.setTypeface(typefaceContent);
            txtSubjectName.setText(subjectName);
            txtweight=(TextView)findViewById(R.id.txtweight);
            txtweight.setTypeface(typefaceContent);
            txttotLec=(TextView)findViewById(R.id.txttotLec);
            txttotLec.setTypeface(typefaceContent);

            lvExpUnivSyll.setAdapter(univSyllabusExpandAdpter);

        }
    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(UniversitySyllbusActivity.this,UnivSubjectActivity.class);
        startActivity(intent);
        finish();
    }
}
