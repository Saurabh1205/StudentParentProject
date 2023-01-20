package com.wordpro.studentproject.activities.syllabus_menu;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.utils.ValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.wordpro.studentproject.R;

import java.util.ArrayList;

import static com.wordpro.studentproject.fragment.FragSyllabus.syllStatusArrayList;
import static com.wordpro.studentproject.network.Network.isNetworkAvailable;

public class GraphViewActivity extends Activity {

    BarChart chart;
    RadioButton radioPercentage,radioAttendance;
    TextView txt1,txt2;
    String value, SUBJECT_DESCRIPTION, BRANCH_STANDARD_CODE, TOTAL_TOPICS_IN_SYLLABUS, TOT_SCHEDULED_TOPICS, TOT_COVERED_TOPICS, SYLLABUS_COVERED_PERCENTAGE, ACTUAL_COVERED_PERCENTAGE,selectedChoiceAcdmSessn,selectedChoiceSemType;

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
            setContentView(R.layout.activity_graph_view);

            Typeface typefaceHeading = Typeface.createFromAsset(getAssets(), "font/Poppins-BoldItalic.otf");
            Typeface typefaceContent = Typeface.createFromAsset(getAssets(), "font/Poppins-Medium.otf");

            chart=(BarChart)findViewById(R.id.chart);
            radioPercentage=(RadioButton)findViewById(R.id.radioPercentage);
            radioPercentage.setTypeface(typefaceContent);
            radioAttendance=(RadioButton)findViewById(R.id.radioAttendance);
            radioAttendance.setTypeface(typefaceContent);
            txt1=(TextView)findViewById(R.id.txt1);
            txt1.setTypeface(typefaceContent);
            txt2=(TextView)findViewById(R.id.txt2);
            txt2.setTypeface(typefaceContent);


            radioPercentage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    radioAttendance.setChecked(false);

                    BarData data = new BarData(getXAxisValues(), getDataSet1());
                    chart.setData(data);
                    chart.setDescription(" ");
                    chart.animateXY(2000, 2000);
                    chart.invalidate();
                    chart.setHorizontalScrollBarEnabled(true);


                }
            });

            radioAttendance.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    radioPercentage.setChecked(false);

                    chart = (BarChart) findViewById(R.id.chart);
                    BarData data = new BarData(getXAxisValues(), getDataSet());
                    chart.setData(data);
                    chart.setDescription("");
                    chart.animateXY(2000, 2000);
                    chart.invalidate();
                    chart.setHorizontalScrollBarEnabled(true);
                    chart.getAxisLeft().setValueFormatter(new ValueFormatter() {
                        @Override
                        public String getFormattedValue(float value) {
                            return String.valueOf((int) Math.floor(value));
                        }
                    });
                    chart.getAxisRight().setValueFormatter(new ValueFormatter() {
                        @Override
                        public String getFormattedValue(float value) {
                            return String.valueOf((int) Math.floor(value));
                        }
                    });


                }
            });

              chart = (BarChart) findViewById(R.id.chart);
            BarData data = new BarData(getXAxisValues(), getDataSet());
            chart.setData(data);
            chart.setDescription(" ");
            chart.animateXY(2000, 2000);
            chart.invalidate();
            chart.setHorizontalScrollBarEnabled(true);
            chart.getAxisLeft().setValueFormatter(new ValueFormatter() {
                @Override
                public String getFormattedValue(float value) {
                    return String.valueOf((int) Math.floor(value));
                }
            });
            chart.getAxisRight().setValueFormatter(new ValueFormatter() {
                @Override
                public String getFormattedValue(float value) {
                    return String.valueOf((int) Math.floor(value));
                }
            });


        }
    }

    private ArrayList<BarDataSet> getDataSet() {
        ArrayList<BarDataSet> dataSets = null;

        ArrayList<BarEntry> valueSet1 = new ArrayList<>();
        ArrayList<BarEntry> valueSet2 = new ArrayList<>();
        ArrayList<BarEntry> valueSet3 = new ArrayList<>();


        for(int i=0;i<syllStatusArrayList.size();i++){

            TOTAL_TOPICS_IN_SYLLABUS=syllStatusArrayList.get(i).getTOTAL_TOPICS_IN_SYLLABUS();
            TOT_SCHEDULED_TOPICS=syllStatusArrayList.get(i).getTOT_SCHEDULED_TOPICS();
            TOT_COVERED_TOPICS=syllStatusArrayList.get(i).getTOT_COVERED_TOPICS();

            int totalTopic = Integer.parseInt(TOTAL_TOPICS_IN_SYLLABUS);
            int scheduleTopic = Integer.parseInt(TOT_SCHEDULED_TOPICS);
            int coveredTopic = Integer.parseInt(TOT_COVERED_TOPICS);


            BarEntry v1e = new BarEntry(totalTopic, i);
            valueSet1.add(v1e);

            BarEntry v2e = new BarEntry(scheduleTopic, i);
            valueSet2.add(v2e);

            BarEntry v3e = new BarEntry(coveredTopic, i);
            valueSet3.add(v3e);
        }


        BarDataSet barDataSet1 = new BarDataSet(valueSet1, "Total Topics");
        barDataSet1.setColor(Color.parseColor("#012561"));
        barDataSet1.setValueFormatter(new MyFormatter());
        barDataSet1.setValueTextSize(12f);

        BarDataSet barDataSet2 = new BarDataSet(valueSet2, "Scheduled Topics");
        barDataSet2.setColor(Color.parseColor("#01c6d7"));
        barDataSet2.setValueFormatter(new MyFormatter());
        barDataSet2.setValueTextSize(12f);

        BarDataSet barDataSet3 = new BarDataSet(valueSet3, "Covered Topics");
        barDataSet3.setColor(Color.parseColor("#49b1f2"));
        barDataSet3.setValueFormatter(new MyFormatter());
        barDataSet3.setValueTextSize(12f);



        dataSets = new ArrayList<>();
        dataSets.add(barDataSet1);
        dataSets.add(barDataSet2);
        dataSets.add(barDataSet3);


        return dataSets;
    }
    private ArrayList<String> getXAxisValues() {
        ArrayList<String> xAxis = new ArrayList<>();

        for(int i=0;i<syllStatusArrayList.size();i++){

            String SUBJECT_SHORT_CODE=syllStatusArrayList.get(i).getSUBJECT_SHORT_CODE();
            xAxis.add(SUBJECT_SHORT_CODE);
        }

        return xAxis;
    }


    private ArrayList<BarDataSet> getDataSet1() {
        ArrayList<BarDataSet> dataSets = null;

        ArrayList<BarEntry> valueSet1 = new ArrayList<>();
        ArrayList<BarEntry> valueSet2 = new ArrayList<>();


        for(int i=0;i<syllStatusArrayList.size();i++){

            SYLLABUS_COVERED_PERCENTAGE = syllStatusArrayList.get(i).getSYLLABUS_COVERED_PERCENTAGE();
            SYLLABUS_COVERED_PERCENTAGE=SYLLABUS_COVERED_PERCENTAGE.replace("%","");
            if(!SYLLABUS_COVERED_PERCENTAGE.contains(".")){
                SYLLABUS_COVERED_PERCENTAGE=SYLLABUS_COVERED_PERCENTAGE.replace(" ",".0");
            }

            ACTUAL_COVERED_PERCENTAGE = syllStatusArrayList.get(i).getACTUAL_COVERED_PERCENTAGE();
            ACTUAL_COVERED_PERCENTAGE=ACTUAL_COVERED_PERCENTAGE.replace("%","");
            if(!ACTUAL_COVERED_PERCENTAGE.contains(".")){
                ACTUAL_COVERED_PERCENTAGE=ACTUAL_COVERED_PERCENTAGE.replace(" ",".0");
            }


            double SYLLABUS_COVERED_PER = Double.parseDouble(SYLLABUS_COVERED_PERCENTAGE);
            double ACTUAL_COVERED_PER = Double.parseDouble(ACTUAL_COVERED_PERCENTAGE);


            BarEntry v1e = new BarEntry((float) SYLLABUS_COVERED_PER, i);
            valueSet1.add(v1e);

            BarEntry v2e = new BarEntry((float) ACTUAL_COVERED_PER, i);
            valueSet2.add(v2e);


        }


        BarDataSet barDataSet2 = new BarDataSet(valueSet1, "ACTUAL COVERED %");
        barDataSet2.setColor(Color.parseColor("#01c6d7"));
        barDataSet2.setValueTextSize(12f);

        BarDataSet barDataSet1 = new BarDataSet(valueSet2, "SYLLABUS COVERED %");
        barDataSet1.setColor(Color.parseColor("#012561"));
        barDataSet1.setValueTextSize(12f);


        dataSets = new ArrayList<>();
        dataSets.add(barDataSet1);
        dataSets.add(barDataSet2);

        return dataSets;
    }


    public class MyFormatter implements ValueFormatter{

        @Override
        public String getFormattedValue(float value) {
            return "" + ((int) value);
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(GraphViewActivity.this,SyllStatusActivity.class);
        startActivity(intent);
        finish();
    }
}
