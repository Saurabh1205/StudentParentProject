package com.wordpro.studentproject.activities.attendance_menu;

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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.wordpro.studentproject.activities.NavigationActivity.studAcadSessName;
import static com.wordpro.studentproject.activities.NavigationActivity.studBranchStandDesc;
import static com.wordpro.studentproject.activities.NavigationActivity.studMainSemName;
import static com.wordpro.studentproject.activities.NavigationActivity.studentName;
import static com.wordpro.studentproject.fragment.FragAttendance.netAttendanceAdpter;
import static com.wordpro.studentproject.fragment.FragAttendance.netAttndArrayList;
import static com.wordpro.studentproject.network.Network.isNetworkAvailable;

public class NetAttendanceActivity extends Activity {

    TextView txtStudName,txtSession,txtSemester,txtSection,txtFromDt,txtEndDate,txtNet;
    TextView txtSubjectName,txtStudPrsntCount,txtTotLec,txtPercent;
    TextView txtTotal;
    RecyclerView recyVwNetAttndData;
    TextView txtTH_PRESENT,txtTH_TOTPRDAPPL,txtTH_PERCTG,txtPR_PRESENT,txtPR_TOTPRDAPPL,txtPR_PERCTG,txtTU_PRESENT,txtTU_TOTPRDAPPL,txtTU_PERCTG,txtTotPrsnt,txtTotPeriod,txtTotPercent;
    Typeface typefaceHeading,typefaceContent;
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
            setContentView(R.layout.activity_net_attendance);

             typefaceHeading = Typeface.createFromAsset(getAssets(), "font/Poppins-BoldItalic.otf");
             typefaceContent = Typeface.createFromAsset(getAssets(), "font/Poppins-Medium.otf");

            final Intent intent = getIntent();
            Bundle extras = intent.getExtras();
            String period_start_date = extras.getString("period_start_date");
            String period_end_date = extras.getString("period_end_date");

            txtTotal=(TextView)findViewById(R.id.txtTotal);
            txtTotal.setTypeface(typefaceContent);
            txtNet=(TextView)findViewById(R.id.txtNet);
            txtNet.setTypeface(typefaceHeading);
            txtStudName=(TextView)findViewById(R.id.txtStudName);
            txtStudName.setTypeface(typefaceContent);
            txtStudName.setText(studentName);
            txtSession=(TextView)findViewById(R.id.txtSession);
            txtSession.setTypeface(typefaceContent);
            txtSession.setText(studAcadSessName);
            txtSemester=(TextView)findViewById(R.id.txtSemester);
            txtSemester.setTypeface(typefaceContent);
            txtSemester.setText(studMainSemName);
            txtSection=(TextView)findViewById(R.id.txtSection);
            txtSection.setTypeface(typefaceContent);
            txtSection.setText(studBranchStandDesc);
            txtFromDt=(TextView)findViewById(R.id.txtFromDt);
            txtFromDt.setTypeface(typefaceContent);
            txtFromDt.setText(period_start_date);
            txtEndDate=(TextView)findViewById(R.id.txtEndDate);
            txtEndDate.setTypeface(typefaceContent);

            txtSubjectName=(TextView)findViewById(R.id.txtSubjectName);
            txtSubjectName.setTypeface(typefaceHeading);
            txtStudPrsntCount=(TextView)findViewById(R.id.txtStudPrsntCount);
            txtStudPrsntCount.setTypeface(typefaceContent);
            txtTotLec=(TextView)findViewById(R.id.txtTotLec);
            txtTotLec.setTypeface(typefaceContent);
            txtPercent=(TextView)findViewById(R.id.txtPercent);
            txtPercent.setTypeface(typefaceContent);

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            try {
                Date endDate = sdf.parse(period_end_date);
                if (new Date().after(endDate)) {
                    txtEndDate.setText(period_end_date);
                }
                else{

                    SimpleDateFormat currentDate = new SimpleDateFormat("dd/MM/yyyy");
                    Date todayDate = new Date();
                    String thisDate = currentDate.format(todayDate);
                    txtEndDate.setText(thisDate);

                }
            } catch (ParseException e) {
                e.printStackTrace();
            }

            txtTH_PRESENT=(TextView)findViewById(R.id.txtTH_PRESENT);
            txtTH_PRESENT.setTypeface(typefaceContent);
            txtTH_PRESENT.setText(netAttndArrayList.get(0).getTH_PRESENT());

            txtTH_TOTPRDAPPL=(TextView)findViewById(R.id.txtTH_TOTPRDAPPL);
            txtTH_TOTPRDAPPL.setTypeface(typefaceContent);
            txtTH_TOTPRDAPPL.setText(netAttndArrayList.get(0).getTH_TOTPRDAPPL());

            txtTH_PERCTG=(TextView)findViewById(R.id.txtTH_PERCTG);
            txtTH_PERCTG.setTypeface(typefaceContent);
            txtTH_PERCTG.setText(netAttndArrayList.get(0).getTH_PERCTG());

            txtPR_PRESENT=(TextView)findViewById(R.id.txtPR_PRESENT);
            txtPR_PRESENT.setTypeface(typefaceContent);
            txtPR_PRESENT.setText(netAttndArrayList.get(0).getPR_PRESENT());

            txtPR_TOTPRDAPPL=(TextView)findViewById(R.id.txtPR_TOTPRDAPPL);
            txtPR_TOTPRDAPPL.setTypeface(typefaceContent);
            txtPR_TOTPRDAPPL.setText(netAttndArrayList.get(0).getPR_TOTPRDAPPL());

            txtPR_PERCTG=(TextView)findViewById(R.id.txtPR_PERCTG);
            txtPR_PERCTG.setTypeface(typefaceContent);
            txtPR_PERCTG.setText(netAttndArrayList.get(0).getPR_PERCTG());

            txtTU_PRESENT=(TextView)findViewById(R.id.txtTU_PRESENT);
            txtTU_PRESENT.setTypeface(typefaceContent);
            txtTU_PRESENT.setText(netAttndArrayList.get(0).getTU_PRESENT());

            txtTU_TOTPRDAPPL=(TextView)findViewById(R.id.txtTU_TOTPRDAPPL);
            txtTU_TOTPRDAPPL.setTypeface(typefaceContent);
            txtTU_TOTPRDAPPL.setText(netAttndArrayList.get(0).getTU_TOTPRDAPPL());

            txtTU_PERCTG=(TextView)findViewById(R.id.txtTU_PERCTG);
            txtTU_PERCTG.setTypeface(typefaceContent);
            txtTU_PERCTG.setText(netAttndArrayList.get(0).getTU_PERCTG());

            txtTotPrsnt=(TextView)findViewById(R.id.txtTotPrsnt);
            txtTotPrsnt.setTypeface(typefaceContent);
            txtTotPeriod=(TextView)findViewById(R.id.txtTotPeriod);
            txtTotPeriod.setTypeface(typefaceContent);
            txtTotPercent=(TextView)findViewById(R.id.txtTotPercent);
            txtTotPercent.setTypeface(typefaceContent);

            int TH_PRESENT= Integer.parseInt(txtTH_PRESENT.getText().toString());
            int PR_PRESENT= Integer.parseInt(txtPR_PRESENT.getText().toString());
            int TU_PRESENT= Integer.parseInt(txtTU_PRESENT.getText().toString());
            int present=TH_PRESENT+PR_PRESENT+TU_PRESENT;
            txtTotPrsnt.setText( String.valueOf(present));

            int TH_TOTPRDAPPL= Integer.parseInt(txtTH_TOTPRDAPPL.getText().toString());
            int PR_TOTPRDAPPL= Integer.parseInt(txtPR_TOTPRDAPPL.getText().toString());
            int TU_TOTPRDAPPL= Integer.parseInt(txtTU_TOTPRDAPPL.getText().toString());
            int total=TH_TOTPRDAPPL+PR_TOTPRDAPPL+TU_TOTPRDAPPL;
            txtTotPeriod.setText( String.valueOf(total));

            float totalPer=((float)present/(float)total)*100;
            String formattedString = String.format("%.02f", totalPer);
            txtTotPercent.setText(String.valueOf(formattedString));



            recyVwNetAttndData=(RecyclerView)findViewById(R.id.recyVwNetAttndData);
            recyVwNetAttndData.setLayoutManager(new LinearLayoutManager(NetAttendanceActivity.this));
            recyVwNetAttndData.setAdapter(netAttendanceAdpter);

        }
    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(NetAttendanceActivity.this, NavigationActivity.class);
        intent.putExtra("activity","NetAttendanceActivity");
        startActivity(intent);
        finish();
    }
}
