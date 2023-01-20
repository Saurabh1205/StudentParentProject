package com.wordpro.studentproject.activities.syllabus_menu;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.wordpro.studentproject.R;
import com.wordpro.studentproject.activities.NavigationActivity;

import static com.wordpro.studentproject.activities.NavigationActivity.studAcadSessName;
import static com.wordpro.studentproject.activities.NavigationActivity.studBranchStandDesc;
import static com.wordpro.studentproject.activities.NavigationActivity.studMainSemName;
import static com.wordpro.studentproject.activities.NavigationActivity.studentName;
import static com.wordpro.studentproject.fragment.FragSyllabus.subjectwiseFacAdapter;
import static com.wordpro.studentproject.network.Network.isNetworkAvailable;

public class SubjectwiseFacultyActivity extends Activity {

    RecyclerView recyVwSubjwiseFac;
    TextView txtStudName,txtYear,txtSem,txtSection,txt1;


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
            setContentView(R.layout.activity_subjectwise_faculty);

            Typeface typefaceHeading = Typeface.createFromAsset(getAssets(), "font/Poppins-BoldItalic.otf");
            Typeface typefaceContent = Typeface.createFromAsset(getAssets(), "font/Poppins-Medium.otf");

            txt1=(TextView)findViewById(R.id.txt1);
            txt1.setTypeface(typefaceHeading);
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
            recyVwSubjwiseFac=(RecyclerView)findViewById(R.id.recyVwSubjwiseFac);
            recyVwSubjwiseFac.setLayoutManager(new GridLayoutManager(this, 2));
            recyVwSubjwiseFac.setAdapter(subjectwiseFacAdapter);

        }
    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(SubjectwiseFacultyActivity.this,NavigationActivity.class);
        intent.putExtra("activity","SubjectwiseFacultyActivity");
        startActivity(intent);
        finish();
    }
}
