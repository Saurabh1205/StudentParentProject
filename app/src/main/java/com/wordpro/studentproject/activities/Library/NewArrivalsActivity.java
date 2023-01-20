package com.wordpro.studentproject.activities.Library;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.wordpro.studentproject.R;

import static com.wordpro.studentproject.activities.Library.NewArrivalMonthActivity.newArrivalBookDtlAdpter;
import static com.wordpro.studentproject.activities.NavigationActivity.studAcadSessName;
import static com.wordpro.studentproject.activities.NavigationActivity.studBranchStandDesc;
import static com.wordpro.studentproject.activities.NavigationActivity.studMainSemName;
import static com.wordpro.studentproject.activities.NavigationActivity.studentName;
import static com.wordpro.studentproject.network.Network.isNetworkAvailable;

public class NewArrivalsActivity extends AppCompatActivity {

    TextView txtStudName, txtYear, txtSem, txtSection,txtNewArrival;
    RecyclerView recyVwNewArrivals;


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


            setContentView(R.layout.activity_new_arrivals);
            recyVwNewArrivals=(RecyclerView)findViewById(R.id.recyVwNewArrivals);
            recyVwNewArrivals.setLayoutManager(new LinearLayoutManager(NewArrivalsActivity.this));
            recyVwNewArrivals.setAdapter(newArrivalBookDtlAdpter);

            Typeface typefaceHeading = Typeface.createFromAsset(getAssets(), "font/Poppins-BoldItalic.otf");
            Typeface typefaceContent = Typeface.createFromAsset(getAssets(), "font/Poppins-Medium.otf");

            txtNewArrival=(TextView)findViewById(R.id.txtNewArrival);
            txtNewArrival.setTypeface(typefaceHeading);
            txtStudName = (TextView) findViewById(R.id.txtStudName);
            txtStudName.setTypeface(typefaceContent);
            txtStudName.setText(studentName.toUpperCase());
            txtYear = (TextView) findViewById(R.id.txtYear);
            txtYear.setTypeface(typefaceContent);
            txtYear.setText(studAcadSessName.toUpperCase());
            txtSem = (TextView) findViewById(R.id.txtSem);
            txtSem.setTypeface(typefaceContent);
            txtSem.setText(studMainSemName.toUpperCase());
            txtSection = (TextView) findViewById(R.id.txtSection);
            txtSection.setTypeface(typefaceContent);
            txtSection.setText(studBranchStandDesc.toUpperCase());
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(NewArrivalsActivity.this,NewArrivalMonthActivity.class);
        startActivity(intent);
        finish();
    }
}
