package com.wordpro.studentproject.activities.Payables;

import android.app.Activity;
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

import static com.wordpro.studentproject.activities.NavigationActivity.studAcadSessName;
import static com.wordpro.studentproject.activities.NavigationActivity.studBranchStandDesc;
import static com.wordpro.studentproject.activities.NavigationActivity.studMainSemName;
import static com.wordpro.studentproject.activities.NavigationActivity.studentName;
import static com.wordpro.studentproject.fragment.FragPayables.acadFeeArrayList;
import static com.wordpro.studentproject.fragment.FragPayables.acadFeesAdpter;
import static com.wordpro.studentproject.fragment.FragPayables.academicArraylist;
import static com.wordpro.studentproject.fragment.FragPayables.nonAcadFeeAdpter;
import static com.wordpro.studentproject.fragment.FragPayables.nonAcadFeeArrayList;
import static com.wordpro.studentproject.fragment.FragPayables.nonacadAdapter;
import static com.wordpro.studentproject.fragment.FragPayables.nonacademicArraylist;
import static com.wordpro.studentproject.network.Network.isNetworkAvailable;

public class CompleteFeesActivity extends Activity {

    RecyclerView rexyVwAcadFees,recyVwNonAcadFees;
    TextView txtStudName,txtYear,txtSem,txtSection;
    TextView txtComplete,txt1,txt2,txt3,txt4,txt5,txt6,txt7,txt8,txt9,txt10,txt11;
    float RECEIVABLE_AMOUNT=0,FEE_RECEIPT_AMOUNT=0,DISCOUNT_AMOUNT=0,BALANCE_AMOUNT=0,ADVN_AMT_SUBTYPEWISE=0;
    float RECEIVABLE_AMOUNT1=0,FEE_RECEIPT_AMOUNT1=0,DISCOUNT_AMOUNT1=0,BALANCE_AMOUNT1=0,ADVN_AMT_SUBTYPEWISE1=0;
    TextView txtReceivable,txtReceived,txtAdjustment,txtOutstanding,txtAdvance;
    TextView txtReceivable1,txtReceived1,txtAdjustment1,txtOutstanding1,txtAdvance1;
    TextView txtReceivable2,txtReceived2,txtAdjustment2,txtOutstanding2,txtAdvance2;

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
            setContentView(R.layout.activity_complete_fees);

            Typeface typefaceHeading = Typeface.createFromAsset(getAssets(), "font/Poppins-BoldItalic.otf");
            Typeface typefaceContent = Typeface.createFromAsset(getAssets(), "font/Poppins-Medium.otf");

            txt1=(TextView)findViewById(R.id.txt1);
            txt1.setTypeface(typefaceContent);
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
            txt7=(TextView)findViewById(R.id.txt7);
            txt7.setTypeface(typefaceContent);
            txt8=(TextView)findViewById(R.id.txt8);
            txt8.setTypeface(typefaceContent);
            txt9=(TextView)findViewById(R.id.txt9);
            txt9.setTypeface(typefaceContent);
            txt10=(TextView)findViewById(R.id.txt10);
            txt10.setTypeface(typefaceContent);
            txt11=(TextView)findViewById(R.id.txt11);
            txt11.setTypeface(typefaceContent);

            txtComplete=(TextView)findViewById(R.id.txtComplete);
            txtComplete.setTypeface(typefaceHeading);
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

            txtReceivable=(TextView)findViewById(R.id.txtReceivable);
            txtReceivable.setTypeface(typefaceContent);
            txtReceived=(TextView)findViewById(R.id.txtReceived);
            txtReceived.setTypeface(typefaceContent);
            txtAdjustment=(TextView)findViewById(R.id.txtAdjustment);
            txtAdjustment.setTypeface(typefaceContent);
            txtOutstanding=(TextView)findViewById(R.id.txtOutstanding);
            txtOutstanding.setTypeface(typefaceContent);
            txtAdvance=(TextView)findViewById(R.id.txtAdvance);
            txtAdvance.setTypeface(typefaceContent);

            txtReceivable1=(TextView)findViewById(R.id.txtReceivable1);
            txtReceivable1.setTypeface(typefaceContent);
            txtReceived1=(TextView)findViewById(R.id.txtReceived1);
            txtReceived1.setTypeface(typefaceContent);
            txtAdjustment1=(TextView)findViewById(R.id.txtAdjustment1);
            txtAdjustment1.setTypeface(typefaceContent);
            txtOutstanding1=(TextView)findViewById(R.id.txtOutstanding1);
            txtOutstanding1.setTypeface(typefaceContent);
            txtAdvance1=(TextView)findViewById(R.id.txtAdvance1);
            txtAdvance1.setTypeface(typefaceContent);

            txtReceivable2=(TextView)findViewById(R.id.txtReceivable2);
            txtReceivable2.setTypeface(typefaceContent);
            txtReceived2=(TextView)findViewById(R.id.txtReceived2);
            txtReceived2.setTypeface(typefaceContent);
            txtAdjustment2=(TextView)findViewById(R.id.txtAdjustment2);
            txtAdjustment2.setTypeface(typefaceContent);
            txtOutstanding2=(TextView)findViewById(R.id.txtOutstanding2);
            txtOutstanding2.setTypeface(typefaceContent);
            txtAdvance2=(TextView)findViewById(R.id.txtAdvance2);
            txtAdvance2.setTypeface(typefaceContent);

            rexyVwAcadFees=(RecyclerView)findViewById(R.id.rexyVwAcadFees);
            rexyVwAcadFees.setLayoutManager(new LinearLayoutManager(CompleteFeesActivity.this));
            rexyVwAcadFees.setAdapter(acadFeesAdpter);

            recyVwNonAcadFees=(RecyclerView)findViewById(R.id.recyVwNonAcadFees);
            recyVwNonAcadFees.setLayoutManager(new LinearLayoutManager(CompleteFeesActivity.this));
            recyVwNonAcadFees.setAdapter(nonacadAdapter);


            for(int i=0;i< academicArraylist.size();i++){

                RECEIVABLE_AMOUNT=RECEIVABLE_AMOUNT+ Float.parseFloat(academicArraylist.get(i).getRECEIVABLE_AMOUNT());
                FEE_RECEIPT_AMOUNT=FEE_RECEIPT_AMOUNT+ Float.parseFloat(academicArraylist.get(i).getFEE_RECEIPT_AMOUNT());
                DISCOUNT_AMOUNT=DISCOUNT_AMOUNT+ Float.parseFloat(academicArraylist.get(i).getDISCOUNT_AMOUNT());
                BALANCE_AMOUNT=BALANCE_AMOUNT+ Float.parseFloat(academicArraylist.get(i).getBALANCE_AMOUNT());
                ADVN_AMT_SUBTYPEWISE=ADVN_AMT_SUBTYPEWISE+ Float.parseFloat(academicArraylist.get(i).getADVN_AMT_SUBTYPEWISE());

            }

            txtReceivable.setText( String.format("%.02f", RECEIVABLE_AMOUNT)+" ₹");
            txtReceived.setText( String.format("%.02f", FEE_RECEIPT_AMOUNT)+" ₹");
            txtAdjustment.setText( String.format("%.02f", DISCOUNT_AMOUNT)+" ₹");
            txtOutstanding.setText( String.format("%.02f", BALANCE_AMOUNT)+" ₹");
            txtAdvance.setText( String.format("%.02f", ADVN_AMT_SUBTYPEWISE)+" ₹");

            for(int i=0;i< nonacademicArraylist.size();i++){

                RECEIVABLE_AMOUNT1=RECEIVABLE_AMOUNT1+ Float.parseFloat(nonacademicArraylist.get(i).getRECEIVABLE_AMOUNT());
                FEE_RECEIPT_AMOUNT1=FEE_RECEIPT_AMOUNT1+Float.parseFloat(nonacademicArraylist.get(i).getFEE_RECEIPT_AMOUNT());
                DISCOUNT_AMOUNT1=DISCOUNT_AMOUNT1+Float.parseFloat(nonacademicArraylist.get(i).getDISCOUNT_AMOUNT());
                BALANCE_AMOUNT1=BALANCE_AMOUNT1+ Float.parseFloat(nonacademicArraylist.get(i).getBALANCE_AMOUNT());
                ADVN_AMT_SUBTYPEWISE1=ADVN_AMT_SUBTYPEWISE1+ Float.parseFloat(nonacademicArraylist.get(i).getADVN_AMT_SUBTYPEWISE());
            }

            txtReceivable1.setText( String.format("%.02f", RECEIVABLE_AMOUNT1)+" ₹");
            txtReceived1.setText( String.format("%.02f", FEE_RECEIPT_AMOUNT1)+" ₹");
            txtAdjustment1.setText( String.format("%.02f", DISCOUNT_AMOUNT1)+" ₹");
            txtOutstanding1.setText( String.format("%.02f", BALANCE_AMOUNT1)+" ₹");
            txtAdvance1.setText( String.format("%.02f", ADVN_AMT_SUBTYPEWISE1)+" ₹");

            float totalRECEIVABLE_AMOUNT=(float) RECEIVABLE_AMOUNT+RECEIVABLE_AMOUNT1;
            String totRECEIVABLE_AMOUNT = String.format("%.02f", totalRECEIVABLE_AMOUNT);
            float totalFEE_RECEIPT_AMOUNT=(float)FEE_RECEIPT_AMOUNT+FEE_RECEIPT_AMOUNT1;
            String totFEE_RECEIPT_AMOUNT = String.format("%.02f", totalFEE_RECEIPT_AMOUNT);
            float totalDISCOUNT_AMOUNT=(float)DISCOUNT_AMOUNT+DISCOUNT_AMOUNT1;
            String totDISCOUNT_AMOUNT = String.format("%.02f", totalDISCOUNT_AMOUNT);
            float totalBALANCE_AMOUNT=(float)BALANCE_AMOUNT+BALANCE_AMOUNT1;
            String totBALANCE_AMOUNT = String.format("%.02f", totalBALANCE_AMOUNT);
            float totalADVN_AMT_SUBTYPEWISE=(float)ADVN_AMT_SUBTYPEWISE+ADVN_AMT_SUBTYPEWISE1;
            String totADVN_AMT_SUBTYPEWISE = String.format("%.02f", totalADVN_AMT_SUBTYPEWISE);

            txtReceivable2.setText(totRECEIVABLE_AMOUNT+" ₹");
            txtReceived2.setText(totFEE_RECEIPT_AMOUNT+" ₹");
            txtAdjustment2.setText(totDISCOUNT_AMOUNT+" ₹");
            txtOutstanding2.setText(totBALANCE_AMOUNT+" ₹");
            txtAdvance2.setText(totADVN_AMT_SUBTYPEWISE+" ₹");


        }

    }

    @Override
    public void onBackPressed() {

        RECEIVABLE_AMOUNT=0;
        FEE_RECEIPT_AMOUNT=0;
        DISCOUNT_AMOUNT=0;
        BALANCE_AMOUNT=0;
        ADVN_AMT_SUBTYPEWISE=0;
        RECEIVABLE_AMOUNT1=0;
        FEE_RECEIPT_AMOUNT1=0;
        DISCOUNT_AMOUNT1=0;
        BALANCE_AMOUNT1=0;
        ADVN_AMT_SUBTYPEWISE1=0;
        Intent intent=new Intent(CompleteFeesActivity.this, NavigationActivity.class);
        intent.putExtra("activity","CompleteFeesActivity");
        startActivity(intent);
        finish();
    }
}
