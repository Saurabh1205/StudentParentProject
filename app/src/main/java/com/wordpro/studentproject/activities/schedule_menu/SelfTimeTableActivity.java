package com.wordpro.studentproject.activities.schedule_menu;

        import android.app.Activity;
        import android.app.AlertDialog;
        import android.content.DialogInterface;
        import android.content.Intent;
        import android.graphics.Typeface;
        import android.os.Bundle;
        import android.support.v7.widget.RecyclerView;
        import android.util.Log;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.GridView;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.wordpro.studentproject.R;
        import com.wordpro.studentproject.activities.NavigationActivity;
        import com.wordpro.studentproject.adapter.GridAdapter;
        import com.wordpro.studentproject.model.StudTimeTableModel;

        import java.util.ArrayList;
        import java.util.List;

        import static com.wordpro.studentproject.network.Network.isNetworkAvailable;

public class SelfTimeTableActivity extends Activity {

    private static String TAG = SelfTimeTableActivity.class.getSimpleName();
    GridView gv;
    List<String> timeTableArrayList;
    GridAdapter gridAdapter;
    ArrayList<String> periodTimming;
    ArrayList<String> mondayPeriod;
    ArrayList<String> tuesdayPeriod;
    ArrayList<String> wednesdayPeriod;
    ArrayList<String> thrusdayPeriod;
    ArrayList<String> fridayPeriod;
    ArrayList<String> saturdayPeriod;
    RecyclerView subCodeRecyVw;
    int timeTableSize, rows;
    ArrayList<String> mondayPeriodUpdt;
    ArrayList<String> tuesdayPeriodUpdt;
    ArrayList<String> wednesdayPeriodUpdt;
    ArrayList<String> thrusdayPeriodUpdt;
    ArrayList<String> fridayPeriodUpdt;
    ArrayList<String> saturdayPeriodUpdt;

    String studentName, studAcadSessName, studMainSemName, studBranchStandDesc;
    TextView txtStudName, txtCourseYr, txtSemester, txtSection,txtTmtbl;

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
            setContentView(R.layout.activity_self_time_table);
// Get the widgets reference from XML layout
            gv = (GridView) findViewById(R.id.gv);


            Typeface typefaceHeading = Typeface.createFromAsset(getAssets(), "font/Poppins-BoldItalic.otf");
            Typeface typefaceContent = Typeface.createFromAsset(getAssets(), "font/Poppins-Medium.otf");
            Bundle bundle = getIntent().getExtras();

            periodTimming = (ArrayList<String>) bundle.getStringArrayList("time");

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

            Object[] st = periodTimming.toArray();
            for (Object s : st) {
                if (periodTimming.indexOf(s) != periodTimming.lastIndexOf(s)) {
                    periodTimming.remove(periodTimming.lastIndexOf(s));
                }
            }

            for (int i = 0; i < periodTimming.size(); i++) {

                if (i != periodTimming.size() - 1) {
                    if (periodTimming.get(i).startsWith("Recess") && periodTimming.get(i + 1).startsWith("Recess")) {
                        periodTimming.remove(i + 1);
                    }
                }

            }

            timeTableSize = periodTimming.size();
            Log.d("Time Table Size", String.valueOf(timeTableSize));

            mondayPeriod = (ArrayList<String>) bundle.getStringArrayList("mondayPeriod");

            for (int i = 0; i < mondayPeriod.size(); i++) {
                if (i != periodTimming.size() - 1) {

                    if (mondayPeriod.get(i).startsWith("Recess") && mondayPeriod.get(i + 1).startsWith("Recess")) {

                        mondayPeriod.remove(i + 1);
                    }
                }
            }
            tuesdayPeriod = (ArrayList<String>) bundle.getStringArrayList("tuesdayPeriod");

            for (int i = 0; i < tuesdayPeriod.size(); i++) {
                if (i != periodTimming.size() - 1) {

                    if (tuesdayPeriod.get(i).startsWith("Recess") && tuesdayPeriod.get(i + 1).startsWith("Recess")) {

                        tuesdayPeriod.remove(i + 1);
                    }
                }
            }

            wednesdayPeriod = (ArrayList<String>) bundle.getStringArrayList("wednesdayPeriod");
            for (int i = 0; i < wednesdayPeriod.size(); i++) {

                if (i != periodTimming.size() - 1) {

                    if (wednesdayPeriod.get(i).startsWith("Recess") && wednesdayPeriod.get(i + 1).startsWith("Recess")) {

                        wednesdayPeriod.remove(i + 1);
                    }
                }
            }

            thrusdayPeriod = (ArrayList<String>) bundle.getStringArrayList("thrusdayPeriod");
            for (int i = 0; i < thrusdayPeriod.size(); i++) {
                if (i != periodTimming.size() - 1) {

                    if (thrusdayPeriod.get(i).startsWith("Recess") && thrusdayPeriod.get(i + 1).startsWith("Recess")) {

                        thrusdayPeriod.remove(i + 1);
                    }
                }
            }
            fridayPeriod = (ArrayList<String>) bundle.getStringArrayList("fridayPeriod");
            for (int i = 0; i < fridayPeriod.size(); i++) {
                if (i != periodTimming.size() - 1) {

                    if (fridayPeriod.get(i).startsWith("Recess") && fridayPeriod.get(i + 1).startsWith("Recess")) {

                        fridayPeriod.remove(i + 1);
                    }
                }
            }
            saturdayPeriod = (ArrayList<String>) bundle.getStringArrayList("saturdayPeriod");
            for (int i = 0; i < saturdayPeriod.size(); i++) {

                if (i != periodTimming.size() - 1) {

                    if (saturdayPeriod.get(i).startsWith("Recess") && saturdayPeriod.get(i + 1).startsWith("Recess")) {

                        saturdayPeriod.remove(i + 1);
                    }
                }
            }


            for (int i = 0; i < periodTimming.size(); i++) {

                if (i != periodTimming.size() - 1) {
                    if (periodTimming.get(i).startsWith("Recess") && periodTimming.get(i + 1).startsWith("Recess")) {
                        periodTimming.remove(i + 1);
                    }
                }

            }

            timeTableSize = periodTimming.size();
            Log.d("Time Table Size", String.valueOf(timeTableSize));

            mondayPeriod = (ArrayList<String>) bundle.getStringArrayList("mondayPeriod");

            for (int i = 0; i < mondayPeriod.size(); i++) {

                if (i != periodTimming.size() - 1) {

                    if (mondayPeriod.get(i).startsWith("Recess") && mondayPeriod.get(i + 1).startsWith("Recess")) {

                        mondayPeriod.remove(i + 1);
                    }
                }
            }
            tuesdayPeriod = (ArrayList<String>) bundle.getStringArrayList("tuesdayPeriod");

            for (int i = 0; i < tuesdayPeriod.size(); i++) {

                if (i != periodTimming.size() - 1) {

                    if (tuesdayPeriod.get(i).startsWith("Recess") && tuesdayPeriod.get(i + 1).startsWith("Recess")) {

                        tuesdayPeriod.remove(i + 1);
                    }
                }
            }

            wednesdayPeriod = (ArrayList<String>) bundle.getStringArrayList("wednesdayPeriod");
            for (int i = 0; i < wednesdayPeriod.size(); i++) {

                if (i != periodTimming.size() - 1) {

                    if (wednesdayPeriod.get(i).startsWith("Recess") && wednesdayPeriod.get(i + 1).startsWith("Recess")) {

                        wednesdayPeriod.remove(i + 1);
                    }
                }
            }

            thrusdayPeriod = (ArrayList<String>) bundle.getStringArrayList("thrusdayPeriod");
            for (int i = 0; i < thrusdayPeriod.size(); i++) {

                if (i != periodTimming.size() - 1) {

                    if (thrusdayPeriod.get(i).startsWith("Recess") && thrusdayPeriod.get(i + 1).startsWith("Recess")) {

                        thrusdayPeriod.remove(i + 1);
                    }
                }
            }
            fridayPeriod = (ArrayList<String>) bundle.getStringArrayList("fridayPeriod");
            for (int i = 0; i < fridayPeriod.size(); i++) {

                if (i != periodTimming.size() - 1) {

                    if (fridayPeriod.get(i).startsWith("Recess") && fridayPeriod.get(i + 1).startsWith("Recess")) {

                        fridayPeriod.remove(i + 1);
                    }
                }
            }
            saturdayPeriod = (ArrayList<String>) bundle.getStringArrayList("saturdayPeriod");
            for (int i = 0; i < saturdayPeriod.size(); i++) {

                if (i != periodTimming.size() - 1) {

                    if (saturdayPeriod.get(i).startsWith("Recess") && saturdayPeriod.get(i + 1).startsWith("Recess")) {

                        saturdayPeriod.remove(i + 1);
                    }
                }
            }

            timeTableArrayList = new ArrayList<>();

            for (String timming : periodTimming) {
                Log.d(TAG, "timming " + timming);
                timeTableArrayList.add(timming);
            }

            final int noOfColumns = timeTableArrayList.size();

            mondayPeriodUpdt = new ArrayList<>();
            for (int i = 0; i < periodTimming.size(); i++) {

                String time1 = periodTimming.get(i);
                StringBuilder stringBuilderSubj = new StringBuilder();
                for (int j = 0; j < mondayPeriod.size(); j++) {

                    String value = mondayPeriod.get(j);

                    value = value.replace("@", "#");
                    //  1343-TH[BANK(E)]  AHK  A309#1@8:00:00 TO 8:40:00

                    String[] val = value.split("#");
                    String subject = val[0];
                    String time2 = val[2];

                       /* String[] v = value.split("@");
                        String[] v1 = v[0].split("#");


                        String subject = v1[0];

                        String time2 = v[1];*/

                    if (time1.equalsIgnoreCase(time2)) {
                        stringBuilderSubj.append("\n" + subject);
                    } else if (time1.startsWith("Recess")) {
                        stringBuilderSubj.append("Recess0");
                    }

                }


                mondayPeriodUpdt.add(stringBuilderSubj.toString());

            }

            for (String MondayLec : mondayPeriodUpdt) {
                Log.d(TAG, "MondayLec " + MondayLec);

                timeTableArrayList.add(MondayLec);
            }


            tuesdayPeriodUpdt = new ArrayList<>();
            for (int i = 0; i < periodTimming.size(); i++) {

                String time1 = periodTimming.get(i);
                StringBuilder stringBuilderSubj = new StringBuilder();
                for (int j = 0; j < tuesdayPeriod.size(); j++) {

                    String value = tuesdayPeriod.get(j);
                    //  1343-TH[BANK(E)]  AHK  A309#1@8:00:00 TO 8:40:00
                    value = value.replace("@", "#");
                    //  1343-TH[BANK(E)]  AHK  A309#1@8:00:00 TO 8:40:00

                    String[] val = value.split("#");
                    String subject = val[0];
                    String time2 = val[2];
                    if (time1.equalsIgnoreCase(time2)) {


                        stringBuilderSubj.append("\n" + subject);
                    } else if (time1.startsWith("Recess")) {
                        stringBuilderSubj.append("Recess0");
                    }

                }


                tuesdayPeriodUpdt.add(stringBuilderSubj.toString());

            }

            for (String TuesdayLec : tuesdayPeriodUpdt) {
                Log.d(TAG, "TuesdayLec" + TuesdayLec);

                timeTableArrayList.add(TuesdayLec);
            }


            wednesdayPeriodUpdt = new ArrayList<>();
            for (int i = 0; i < periodTimming.size(); i++) {

                String time1 = periodTimming.get(i);
                StringBuilder stringBuilderSubj = new StringBuilder();
                for (int j = 0; j < wednesdayPeriod.size(); j++) {

                    String value = wednesdayPeriod.get(j);
                    //  1343-TH[BANK(E)]  AHK  A309#1@8:00:00 TO 8:40:00
                    value = value.replace("@", "#");
                    //  1343-TH[BANK(E)]  AHK  A309#1@8:00:00 TO 8:40:00

                    String[] val = value.split("#");
                    String subject = val[0];
                    String time2 = val[2];

                    if (time1.equalsIgnoreCase(time2)) {

                        stringBuilderSubj.append("\n" + subject);
                    } else if (time1.startsWith("Recess")) {
                        stringBuilderSubj.append("Recess0");
                    }

                }


                wednesdayPeriodUpdt.add(stringBuilderSubj.toString());

            }


            for (String WednesdayLec : wednesdayPeriodUpdt) {
                Log.d(TAG, "WednesdayLec" + WednesdayLec);
                timeTableArrayList.add(WednesdayLec);
            }

            thrusdayPeriodUpdt = new ArrayList<>();
            for (int i = 0; i < periodTimming.size(); i++) {

                String time1 = periodTimming.get(i);
                StringBuilder stringBuilderSubj = new StringBuilder();
                for (int j = 0; j < thrusdayPeriod.size(); j++) {

                    String value = thrusdayPeriod.get(j);
                    //  1343-TH[BANK(E)]  AHK  A309#1@8:00:00 TO 8:40:00
                    value = value.replace("@", "#");
                    //  1343-TH[BANK(E)]  AHK  A309#1@8:00:00 TO 8:40:00

                    String[] val = value.split("#");
                    String subject = val[0];
                    String time2 = val[2];

                    if (time1.equalsIgnoreCase(time2)) {


                        stringBuilderSubj.append("\n" + subject);
                    } else if (time1.startsWith("Recess")) {
                        stringBuilderSubj.append("Recess0");
                    }

                }


                thrusdayPeriodUpdt.add(stringBuilderSubj.toString());

            }


            for (String ThrusdayLec : thrusdayPeriodUpdt) {
                Log.d(TAG, "ThrusdayLec" + ThrusdayLec);
                timeTableArrayList.add(ThrusdayLec);

            }

            fridayPeriodUpdt = new ArrayList<>();
            for (int i = 0; i < periodTimming.size(); i++) {

                String time1 = periodTimming.get(i);
                StringBuilder stringBuilderSubj = new StringBuilder();
                for (int j = 0; j < fridayPeriod.size(); j++) {

                    String value = fridayPeriod.get(j);
                    //  1343-TH[BANK(E)]  AHK  A309#1@8:00:00 TO 8:40:00
                    value = value.replace("@", "#");
                    //  1343-TH[BANK(E)]  AHK  A309#1@8:00:00 TO 8:40:00

                    String[] val = value.split("#");
                    String subject = val[0];
                    String time2 = val[2];

                    if (time1.equalsIgnoreCase(time2)) {


                        stringBuilderSubj.append("\n" + subject);
                    } else if (time1.startsWith("Recess")) {
                        stringBuilderSubj.append("Recess0");
                    }

                }


                fridayPeriodUpdt.add(stringBuilderSubj.toString());

            }


            for (String FridayLec : fridayPeriodUpdt) {
                Log.d(TAG, "FridayLec" + FridayLec);
                timeTableArrayList.add(FridayLec);

            }

            saturdayPeriodUpdt = new ArrayList<>();
            for (int i = 0; i < periodTimming.size(); i++) {

                String time1 = periodTimming.get(i);
                StringBuilder stringBuilderSubj = new StringBuilder();
                for (int j = 0; j < saturdayPeriod.size(); j++) {

                    String value = saturdayPeriod.get(j);
                    //  1343-TH[BANK(E)]  AHK  A309#1@8:00:00 TO 8:40:00
                    value = value.replace("@", "#");
                    //  1343-TH[BANK(E)]  AHK  A309#1@8:00:00 TO 8:40:00

                    String[] val = value.split("#");
                    String subject = val[0];
                    String time2 = val[2];

                    if (time1.equalsIgnoreCase(time2)) {


                        stringBuilderSubj.append("\n" + subject);
                    } else if (time1.startsWith("Recess")) {
                        stringBuilderSubj.append("Recess0");
                    }

                }


                saturdayPeriodUpdt.add(stringBuilderSubj.toString());

            }

            for (String SaturdayLec : saturdayPeriodUpdt) {
                Log.d(TAG, "SaturdayLec" + SaturdayLec);
                timeTableArrayList.add(SaturdayLec);

            }


            int timeTableElements = timeTableArrayList.size();
            rows = timeTableElements / noOfColumns;
            Log.d(TAG, "No of Rows : " + String.valueOf(rows));


            gv.setNumColumns(noOfColumns);
            gridAdapter = new GridAdapter(SelfTimeTableActivity.this, timeTableArrayList, noOfColumns);
            gv.setAdapter(gridAdapter);
            gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    TextView textView = (TextView) view.findViewById(R.id.txtViewGV);


//                    String prompt = (String)parent.getItemAtPosition(position);
                    Toast.makeText(getApplicationContext(), textView.getText(),
                            Toast.LENGTH_LONG).show();
                }
            });


        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(SelfTimeTableActivity.this, NavigationActivity.class);
        intent.putExtra("activity","SelfTimeTableActivity");
        startActivity(intent);
        finish();
    }
}
