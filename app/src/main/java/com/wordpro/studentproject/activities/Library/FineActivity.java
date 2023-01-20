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
import com.wordpro.studentproject.activities.NavigationActivity;
import com.wordpro.studentproject.webConfig.URLEndPoints;

import static com.wordpro.studentproject.activities.NavigationActivity.studAcadSessName;
import static com.wordpro.studentproject.activities.NavigationActivity.studBranchStandDesc;
import static com.wordpro.studentproject.activities.NavigationActivity.studMainSemName;
import static com.wordpro.studentproject.activities.NavigationActivity.studentName;
import static com.wordpro.studentproject.fragment.LibraryFragment.bookFineAdpter;
import static com.wordpro.studentproject.fragment.LibraryFragment.bookFineDetailsArrayList;
import static com.wordpro.studentproject.network.Network.isNetworkAvailable;

public class FineActivity extends AppCompatActivity {

    public String TAG=FineActivity.class.getSimpleName();

    TextView txtStudName, txtYear, txtSem, txtSection;
    TextView txtBkName,txtExptdDate,txtActualRetrnDt,txtFineDue,txtfinePaid,txtFineOutstd;
    RecyclerView recyVwFine;
    TextView txtTotalBalncFine,txtFine,txtSessionYear;

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
            setContentView(R.layout.activity_fine);

            Typeface typefaceHeading = Typeface.createFromAsset(getAssets(), "font/Poppins-BoldItalic.otf");
            Typeface typefaceContent = Typeface.createFromAsset(getAssets(), "font/Poppins-Medium.otf");

            txtFine=(TextView)findViewById(R.id.txtFine);
            txtFine.setTypeface(typefaceHeading);
            txtBkName=(TextView)findViewById(R.id.txtBkName);
            txtBkName.setTypeface(typefaceContent);
            txtExptdDate=(TextView)findViewById(R.id.txtExptdDate);
            txtExptdDate.setTypeface(typefaceContent);
            txtActualRetrnDt=(TextView)findViewById(R.id.txtActualRetrnDt);
            txtActualRetrnDt.setTypeface(typefaceContent);
            txtFineDue=(TextView)findViewById(R.id.txtFineDue);
            txtFineDue.setTypeface(typefaceContent);
            txtfinePaid=(TextView)findViewById(R.id.txtfinePaid);
            txtfinePaid.setTypeface(typefaceContent);
            txtFineOutstd=(TextView)findViewById(R.id.txtFineOutstd);
            txtFineOutstd.setTypeface(typefaceContent);
            txtSessionYear=(TextView)findViewById(R.id.txtSessionYear);
            txtSessionYear.setTypeface(typefaceHeading);
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

            txtTotalBalncFine=(TextView)findViewById(R.id.txtTotalBalncFine);
            txtTotalBalncFine.setTypeface(typefaceContent);
            txtTotalBalncFine.setText("Total Fine Outstanding  :  0 ₹");

            recyVwFine=(RecyclerView)findViewById(R.id.recyVwFine);
            recyVwFine.setLayoutManager(new LinearLayoutManager(FineActivity.this));
            recyVwFine.setAdapter(bookFineAdpter);
            if(URLEndPoints.ConstancebookFineDetailsArrayList.size()!=0){
                int balanceFine=0;
                txtTotalBalncFine.setText("Total Fine Outstanding  :  "+String.valueOf(balanceFine)+"₹");

                for(int i=0;i<URLEndPoints.ConstancebookFineDetailsArrayList.size();i++){

                    int OutstandingFine= Integer.parseInt(URLEndPoints.ConstancebookFineDetailsArrayList.get(i).getBalanceAmt());
                    balanceFine=balanceFine+OutstandingFine;

                }

                txtTotalBalncFine.setText("Total Fine Outstanding  :  "+String.valueOf(balanceFine)+"₹");


            }
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(FineActivity.this, NavigationActivity.class);
        intent.putExtra("activity","FineActivity");
        startActivity(intent);
        finish();
    }
}
