package com.wordpro.studentproject.activities.schedule_menu;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.wordpro.studentproject.R;
import com.wordpro.studentproject.activities.NavigationActivity;

import java.util.ArrayList;
import java.util.regex.Pattern;

import static com.wordpro.studentproject.fragment.FragSchedule.periodSquenceArrayList;
import static com.wordpro.studentproject.fragment.FragSchedule.recordArrayList;
import static com.wordpro.studentproject.fragment.FragSchedule.timePrdSquenceArray;
import static com.wordpro.studentproject.fragment.FragSchedule.weekArrayList;
import static com.wordpro.studentproject.network.Network.isNetworkAvailable;

public class NewTimetblActivity extends Activity implements View.OnClickListener {

    String studentName, studAcadSessName, studMainSemName, studBranchStandDesc;
    TextView txtStudName, txtCourseYr, txtSemester, txtSection,txtTmtbl;
    public static String TAG = NewTimetblActivity.class.getSimpleName();
    public static int countRecess=0;


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
            setContentView(R.layout.activity_new_timetbl);

            Typeface typefaceHeading = Typeface.createFromAsset(getAssets(), "font/Poppins-BoldItalic.otf");
            Typeface typefaceContent = Typeface.createFromAsset(getAssets(), "font/Poppins-Medium.otf");

            Bundle bundle = getIntent().getExtras();
            studentName = bundle.getString("student");
            studAcadSessName = bundle.getString("year");
            studMainSemName = bundle.getString("semesetr");
            studBranchStandDesc = bundle.getString("section");

            txtTmtbl=(TextView)findViewById(R.id.txtTmtbl);
            txtTmtbl.setTypeface(typefaceHeading);

            txtStudName = (TextView) findViewById(R.id.txtStudName);
            txtStudName.setTypeface(typefaceContent);
            txtStudName.setText(studentName);

            txtCourseYr = (TextView) findViewById(R.id.txtCourseYr);
            txtCourseYr.setTypeface(typefaceContent);
            txtCourseYr.setText(studAcadSessName);

            txtSemester = (TextView) findViewById(R.id.txtSemester);
            txtSemester.setTypeface(typefaceContent);
            txtSemester.setText(studMainSemName);

            txtSection = (TextView) findViewById(R.id.txtSection);
            txtSection.setTypeface(typefaceContent);
            txtSection.setText(studBranchStandDesc);

            addHeadersCrYr();
        }

    }

    public void addHeadersCrYr() {

        if(countRecess==0){
            countRecess++;
            ArrayList<String> recess = new ArrayList<>();
            String RECESS_AFTER_PERIOD = recordArrayList.get(0).getRECESS_AFTER_PERIOD();
            String[] value = RECESS_AFTER_PERIOD.split(Pattern.quote(","));   //2-93,4-97
            for (int b = 0; b < value.length; b++) {
                String[] v1 = value[b].split(Pattern.quote("-"));
                recess.add(v1[0]);
            }

            int count = 0;
            for (int c = 0; c < recess.size(); c++) {
                if (count == c) {
                    periodSquenceArrayList.add(Integer.parseInt(recess.get(c)) + c, "RECESS\n");
                    timePrdSquenceArray.add(Integer.parseInt(recess.get(c)) + c + 1, "RECESS\n");
                }
                count++;

            }

            for (String prdSqu : periodSquenceArrayList) {
                Log.d(TAG, "\nPeriod Sequence : " + prdSqu);
            }
            Log.d(TAG, " periodSquenceArrayList Size : " + periodSquenceArrayList.size());

            for (String prdSqu : timePrdSquenceArray) {
                Log.d(TAG, "\ntimePrdSquenceArray : " + prdSqu);
            }
            Log.d(TAG, " timePrdSquenceArray Size : " + timePrdSquenceArray.size());


            TableLayout tl = findViewById(R.id.table);
            TableRow tr = new TableRow(this);
            tr.getLayoutParams();

            for (int i = 0; i < timePrdSquenceArray.size(); i++) {
                tr.addView(getTextViewTheory(0, timePrdSquenceArray.get(i), Color.WHITE, Typeface.BOLD, R.color.colorAccent));
            }

            tl.addView(tr, getTblLayoutParams());

            addDataCrYr();

        }else{
            TableLayout tl = findViewById(R.id.table);
            TableRow tr = new TableRow(this);
            tr.getLayoutParams();

            for (int i = 0; i < timePrdSquenceArray.size(); i++) {
                tr.addView(getTextViewTheory(0, timePrdSquenceArray.get(i), Color.WHITE, Typeface.BOLD, R.color.colorAccent));
            }

            tl.addView(tr, getTblLayoutParams());

            addDataCrYr();
        }


    }

    /**
     * This function add the data to the table
     **/
    public void addDataCrYr() {

        TableLayout tl = findViewById(R.id.table);
        /*int anywidth = 0; //Decide according to possible max text length
        int anyheight = 0; //Decide according to passible max text length
        TableRow.LayoutParams r1 = new TableRow.LayoutParams(anywidth, anyheight, 10.0f);*/

        for (int i = 0; i < weekArrayList.size(); i++) {
            TableRow tr = new TableRow(this);
            tr.setLayoutParams(getLayoutParamsTheory());
            tr.addView(getTextViewTheory(i + 1, weekArrayList.get(i), Color.WHITE, Typeface.NORMAL, ContextCompat.getColor(this, R.color.colorAccent)));


            int count = 0;

            ArrayList<String> mondayArrayList = new ArrayList<>();

            for (int b = 0; b < periodSquenceArrayList.size(); b++) {

                StringBuilder stringBuilder = new StringBuilder();
                String RECESS = "";

                for (int a = 0; a < recordArrayList.size(); a++) {

                    if (periodSquenceArrayList.get(b).equalsIgnoreCase(recordArrayList.get(a).getPERIOD_SEQUENCE_NO()) && recordArrayList.get(a).getWEEK_DESCRIPTION().equalsIgnoreCase(weekArrayList.get(i))) {
                        String COMB_SUBJECT_DETAILS = recordArrayList.get(a).getCOMB_SUBJECT_DETAILS();
                        String COMB_SUBJECT_DTLS = recordArrayList.get(a).getCOMB_SUBJECT_DTLS();

                        stringBuilder.append(COMB_SUBJECT_DETAILS + "\n");

                    }
                    if (periodSquenceArrayList.get(b).equalsIgnoreCase("RECESS\n") && recordArrayList.get(a).getWEEK_DESCRIPTION().equalsIgnoreCase(weekArrayList.get(i))) {
                        stringBuilder.append("RECESS\n");
                    }
                }

                mondayArrayList.add(stringBuilder.toString());

            }

            for (String prdSqu : mondayArrayList) {
                Log.d(TAG, "\nmondayArrayList : " + prdSqu);
            }
            Log.d(TAG, " mondayArrayList Size : " + timePrdSquenceArray.size() + "\n\n");

            for (int j = 0; j < mondayArrayList.size(); j++) {
                String COMB_SUBJECT_DTLS = mondayArrayList.get(j);


                if (COMB_SUBJECT_DTLS.contains("#")) {
                    COMB_SUBJECT_DTLS = COMB_SUBJECT_DTLS.replace("#", " ");
                    if (COMB_SUBJECT_DTLS.contains("-PR")) {
                        count++;
                        if (count == 1 || count == 3 || count == 5 || count == 7) {
                            tr.addView(getTextViewPractical2(j + 1, COMB_SUBJECT_DTLS, Color.BLUE, Typeface.NORMAL, ContextCompat.getColor(this, R.color.lightgrey)));
                            tr.addView(getTextViewPractical1(j + 1, "", Color.BLUE, Typeface.NORMAL, ContextCompat.getColor(this, R.color.lightgrey)));
                        }
                    } else {
                        tr.addView(getTextViewTheory(j + 1, COMB_SUBJECT_DTLS, Color.BLACK, Typeface.NORMAL, ContextCompat.getColor(this, R.color.lightgrey)));
                    }
                }else if(COMB_SUBJECT_DTLS.contains("RECESS\n")){
                    tr.addView(getTextViewTheory(j + 1, "RECESS", Color.BLACK, Typeface.NORMAL, ContextCompat.getColor(this, R.color.lightgrey)));
                } else {
                    tr.addView(getTextViewTheory(j + 1, COMB_SUBJECT_DTLS, Color.BLACK, Typeface.NORMAL, ContextCompat.getColor(this, R.color.lightgrey)));
                }



                // tr.addView(getTextView(j + 1, mondayArrayList.get(j), Color.WHITE, Typeface.BOLD, R.color.colorAccent));
            }

            tl.addView(tr, getTblLayoutParams());
        }


    }


    private TextView getTextViewTheory(int id, String title, int color, int typeface, int bgColor) {
        TextView tv = new TextView(this);
        tv.setId(id);
        tv.setText(title.toUpperCase());
        tv.setTextColor(color);
        tv.setPadding(30, 30, 30, 30);
        tv.setTypeface(Typeface.DEFAULT, typeface);
        tv.setBackgroundColor(bgColor);
        tv.setLayoutParams(getLayoutParamsTheory());
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(NewTimetblActivity.this, title.toUpperCase().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        return tv;
    }

    private TextView getTextViewPractical1(int id, String title, int color, int typeface, int bgColor) {
        TextView tv = new TextView(this);
        tv.setId(id);
        tv.setText(title.toUpperCase());
        tv.setTextColor(color);
        tv.setPadding(30, 30, 30, 30);
        tv.setTypeface(Typeface.DEFAULT, typeface);
        tv.setBackgroundColor(bgColor);
        tv.setLayoutParams(getLayoutParamsPractical1());
        tv.setOnClickListener(this);
        return tv;
    }

    private TextView getTextViewPractical2(int id, String title, int color, int typeface, int bgColor) {
        TextView tv = new TextView(this);
        tv.setId(id);
        tv.setText(title.toUpperCase());
        tv.setTextColor(color);
        tv.setPadding(30, 30, 30, 30);
        tv.setTypeface(Typeface.DEFAULT, typeface);
        tv.setBackgroundColor(bgColor);
        tv.setLayoutParams(getLayoutParamsPractical2());
        tv.setOnClickListener(this);
        return tv;
    }


    @NonNull
    private TableRow.LayoutParams getLayoutParamsTheory() {
        TableRow.LayoutParams params = new TableRow.LayoutParams(
                TableRow.LayoutParams.FILL_PARENT,
                TableRow.LayoutParams.FILL_PARENT);
        params.setMargins(2, 0, 0, 2);
        return params;
    }

    private TableRow.LayoutParams getLayoutParamsPractical1() {
        TableRow.LayoutParams params = new TableRow.LayoutParams(
                TableRow.LayoutParams.FILL_PARENT,
                TableRow.LayoutParams.FILL_PARENT);
        params.setMargins(0, 0, 0, 2);
        return params;
    }

    private TableRow.LayoutParams getLayoutParamsPractical2() {
        TableRow.LayoutParams params = new TableRow.LayoutParams(
                TableRow.LayoutParams.FILL_PARENT,
                TableRow.LayoutParams.FILL_PARENT);
        params.setMargins(2, 0, 0, 2);
        return params;
    }

    @NonNull
    private TableLayout.LayoutParams getTblLayoutParams() {
        return new TableLayout.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT);
    }


    @Override
    public void onClick(View v) {

        int id = v.getId();
        TextView tv = findViewById(id);
        if (null != tv) {
            Log.i("onClick", "Clicked on row :: " + id);
//            Toast.makeText(this, tv.getText(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {

        countRecess=0;

        Intent intent = new Intent(NewTimetblActivity.this, NavigationActivity.class);
        intent.putExtra("activity", "SelfTimeTableActivity");
        startActivity(intent);

        finish();
    }
}
