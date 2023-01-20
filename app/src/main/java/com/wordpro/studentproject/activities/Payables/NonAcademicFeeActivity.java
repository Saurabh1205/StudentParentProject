package com.wordpro.studentproject.activities.Payables;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.wordpro.studentproject.R;
import com.wordpro.studentproject.activities.NavigationActivity;

import static com.wordpro.studentproject.activities.NavigationActivity.studAcadSessName;
import static com.wordpro.studentproject.activities.NavigationActivity.studBranchStandDesc;
import static com.wordpro.studentproject.activities.NavigationActivity.studMainSemName;
import static com.wordpro.studentproject.activities.NavigationActivity.studentName;
import static com.wordpro.studentproject.fragment.FragPayables.acadFeesAdpter;
import static com.wordpro.studentproject.fragment.FragPayables.nonAcadFeeAdpter;
import static com.wordpro.studentproject.fragment.FragPayables.nonAcadFeeArrayList;
import static com.wordpro.studentproject.network.Network.isNetworkAvailable;

public class NonAcademicFeeActivity extends Activity {

    RecyclerView recyVwNonAcadFees;
    TextView txtStudName,txtYear,txtSem,txtSection,txtHeading;
    TextView txt2,txt3,txt4,txt5,txt6;
    int FEE_SUB_TYPE_DESC=0,RECEIVABLE_AMOUNT=0,FEE_RECEIPT_AMOUNT=0,DISCOUNT_AMOUNT=0,BALANCE_AMOUNT=0,ADVN_AMT_SUBTYPEWISE=0;

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
            setContentView(R.layout.activity_non_academic_fee);

            Typeface typefaceHeading = Typeface.createFromAsset(getAssets(), "font/Poppins-BoldItalic.otf");
            Typeface typefaceContent = Typeface.createFromAsset(getAssets(), "font/Poppins-Medium.otf");

            txtHeading=(TextView)findViewById(R.id.txtHeading);
            txtHeading.setTypeface(typefaceHeading);
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

            txt2=(TextView)findViewById(R.id.txt2);
            txt2.setTypeface(typefaceContent);
            txt3=(TextView)findViewById(R.id.txt3);
            txt3.setTypeface(typefaceContent);
            txt4=(TextView)findViewById(R.id.txt4);
            txt4.setTypeface(typefaceContent);
            txt5=(TextView)findViewById(R.id.txt5);
            txt5.setTypeface(typefaceContent);
            txt6=(TextView)findViewById(R.id.txt6);
            txt6.setTypeface(typefaceContent);

            recyVwNonAcadFees=(RecyclerView)findViewById(R.id.recyVwNonAcadFees);
            recyVwNonAcadFees.setLayoutManager(new LinearLayoutManager(NonAcademicFeeActivity.this));
            recyVwNonAcadFees.setAdapter(nonAcadFeeAdpter);

            for(int i=0;i< nonAcadFeeArrayList.size();i++){

                RECEIVABLE_AMOUNT=RECEIVABLE_AMOUNT+ Integer.parseInt(nonAcadFeeArrayList.get(i).getRECEIVABLE_AMOUNT());
                FEE_RECEIPT_AMOUNT=FEE_RECEIPT_AMOUNT+ Integer.parseInt(nonAcadFeeArrayList.get(i).getFEE_RECEIPT_AMOUNT());
                DISCOUNT_AMOUNT=DISCOUNT_AMOUNT+ Integer.parseInt(nonAcadFeeArrayList.get(i).getDISCOUNT_AMOUNT());
                BALANCE_AMOUNT=BALANCE_AMOUNT+ Integer.parseInt(nonAcadFeeArrayList.get(i).getBALANCE_AMOUNT());
                ADVN_AMT_SUBTYPEWISE=ADVN_AMT_SUBTYPEWISE+ Integer.parseInt(nonAcadFeeArrayList.get(i).getADVN_AMT_SUBTYPEWISE());
            }

            txt2.setText(String .valueOf(RECEIVABLE_AMOUNT) +" ₹");
            txt3.setText(String .valueOf(FEE_RECEIPT_AMOUNT) +" ₹");
            txt4.setText(String .valueOf(DISCOUNT_AMOUNT) +" ₹");
            txt5.setText(String .valueOf(BALANCE_AMOUNT) +" ₹");
            txt6.setText(String .valueOf(ADVN_AMT_SUBTYPEWISE) +" ₹");

        }
    }

    @Override
    public void onBackPressed() {

        FEE_SUB_TYPE_DESC=0;
        RECEIVABLE_AMOUNT=0;
        FEE_RECEIPT_AMOUNT=0;
        DISCOUNT_AMOUNT=0;
        BALANCE_AMOUNT=0;
        ADVN_AMT_SUBTYPEWISE=0;
        Intent intent=new Intent(NonAcademicFeeActivity.this, NavigationActivity.class);
        intent.putExtra("activity","NonAcademicFeeActivity");
        startActivity(intent);
        finish();
    }
}

