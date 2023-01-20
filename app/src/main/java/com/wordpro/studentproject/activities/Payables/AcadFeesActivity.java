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
import static com.wordpro.studentproject.fragment.FragPayables.acadFeeArrayList;
import static com.wordpro.studentproject.fragment.FragPayables.acadFeesAdpter;
import static com.wordpro.studentproject.network.Network.isNetworkAvailable;

public class AcadFeesActivity extends Activity {

    RecyclerView rexyVwAcadFees;
    TextView txtStudName,txtYear,txtSem,txtSection,txtAcdFee,txtFeesType,txtParticulars,txtReceivableAmt,txtReceivedAmt,txtAdjustment;
    TextView txtOutstanding,txtAdvance,txtStructural,txtTotal,txtRecvAmt;
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
            setContentView(R.layout.activity_acad_fees);

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
            txtAcdFee=(TextView)findViewById(R.id.txtAcdFee);
            txtAcdFee.setTypeface(typefaceHeading);
            txtFeesType=(TextView)findViewById(R.id.txtFeesType);
            txtFeesType.setTypeface(typefaceContent);
            txtParticulars=(TextView)findViewById(R.id.txtParticulars);
            txtParticulars.setTypeface(typefaceContent);
            txtReceivableAmt=(TextView)findViewById(R.id.txtReceivableAmt);
            txtReceivableAmt.setTypeface(typefaceContent);
            txtReceivedAmt=(TextView)findViewById(R.id.txtReceivedAmt);
            txtReceivedAmt.setTypeface(typefaceContent);
            txtAdjustment=(TextView)findViewById(R.id.txtAdjustment);
            txtAdjustment.setTypeface(typefaceContent);
            txtOutstanding=(TextView)findViewById(R.id.txtOutstanding);
            txtOutstanding.setTypeface(typefaceContent);
            txtAdvance=(TextView)findViewById(R.id.txtAdvance);
            txtAdvance.setTypeface(typefaceContent);
            txtStructural=(TextView)findViewById(R.id.txtStructural);
            txtStructural.setTypeface(typefaceContent);
            txtTotal=(TextView)findViewById(R.id.txtTotal);
            txtTotal.setTypeface(typefaceContent);


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

            rexyVwAcadFees=(RecyclerView)findViewById(R.id.rexyVwAcadFees);
            rexyVwAcadFees.setLayoutManager(new LinearLayoutManager(AcadFeesActivity.this));
            rexyVwAcadFees.setAdapter(acadFeesAdpter);

            for(int i=0;i< acadFeeArrayList.size();i++){

                RECEIVABLE_AMOUNT=RECEIVABLE_AMOUNT+ Integer.parseInt(acadFeeArrayList.get(i).getRECEIVABLE_AMOUNT());
                FEE_RECEIPT_AMOUNT=FEE_RECEIPT_AMOUNT+ Integer.parseInt(acadFeeArrayList.get(i).getFEE_RECEIPT_AMOUNT());
                DISCOUNT_AMOUNT=DISCOUNT_AMOUNT+ Integer.parseInt(acadFeeArrayList.get(i).getDISCOUNT_AMOUNT());
                BALANCE_AMOUNT=BALANCE_AMOUNT+ Integer.parseInt(acadFeeArrayList.get(i).getBALANCE_AMOUNT());
                ADVN_AMT_SUBTYPEWISE=ADVN_AMT_SUBTYPEWISE+ Integer.parseInt(acadFeeArrayList.get(i).getADVN_AMT_SUBTYPEWISE());

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
        Intent intent=new Intent(AcadFeesActivity.this, NavigationActivity.class);
        intent.putExtra("activity","AcadFeesActivity");
        startActivity(intent);
        finish();
    }
}
