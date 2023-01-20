package com.wordpro.studentproject.activities.schedule_menu;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.wordpro.studentproject.R;
import com.wordpro.studentproject.activities.NavigationActivity;

import static com.wordpro.studentproject.activities.NavigationActivity.studAcadSessName;
import static com.wordpro.studentproject.activities.NavigationActivity.studBranchStandDesc;
import static com.wordpro.studentproject.activities.NavigationActivity.studMainSemName;
import static com.wordpro.studentproject.activities.NavigationActivity.studentName;
import static com.wordpro.studentproject.fragment.FragSchedule.subjectwiseBatchAdpter;
import static com.wordpro.studentproject.network.Network.isNetworkAvailable;

public class SubjectwiseBatchActivity extends Activity {

    TextView txtStudName,txtYear,txtSem,txtSection,txtMyBatches;
    RecyclerView recyViewMyBatch;

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

            setContentView(R.layout.activity_subjectwise_batch);

            Typeface typefaceHeading = Typeface.createFromAsset(getAssets(), "font/Poppins-BoldItalic.otf");
            Typeface typefaceContent = Typeface.createFromAsset(getAssets(), "font/Poppins-Medium.otf");

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
            txtMyBatches=(TextView)findViewById(R.id.txtMyBatches);
            txtMyBatches.setTypeface(typefaceHeading);

            recyViewMyBatch=(RecyclerView)findViewById(R.id.recyViewMyBatch);
            recyViewMyBatch.setLayoutManager(new GridLayoutManager(this, 2));
            recyViewMyBatch.setAdapter(subjectwiseBatchAdpter);

        }

    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(SubjectwiseBatchActivity.this, NavigationActivity.class);
        intent.putExtra("activity","SubjectwiseBatchActivity");
        startActivity(intent);
        finish();
    }
}
