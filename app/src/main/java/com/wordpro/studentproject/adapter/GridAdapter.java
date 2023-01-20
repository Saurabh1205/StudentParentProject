package com.wordpro.studentproject.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wordpro.studentproject.R;

import java.util.List;

/**
 * Created by wccs1980 on 17-02-2018.
 */

public class GridAdapter extends BaseAdapter {
    private Context context;
    List<String> timeTableArrayList;
    int noOfColumns, length;

    public GridAdapter(Context context, List<String> timeTableArrayList, int noOfColumns) {
        this.context = context;
        this.timeTableArrayList = timeTableArrayList;
        this.noOfColumns = noOfColumns;
        length = timeTableArrayList.size();
    }

    @Override
    public int getCount() {
        return timeTableArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return timeTableArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View grid;
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {

            grid = new View(context);
            grid = inflater.inflate(R.layout.gridview_item_layout, null);


        } else {
            grid = (View) convertView;
        }

        Typeface customTypeOne = Typeface.createFromAsset(convertView.getContext().getAssets(), "font/Poppins-Medium.otf");
        TextView textView = (TextView) grid.findViewById(R.id.txtViewGV);
        textView.setTypeface(customTypeOne);
        ImageView imageView = (ImageView) grid.findViewById(R.id.imgViewGV);
        RelativeLayout relLytSchedule = (RelativeLayout) grid.findViewById(R.id.relLytSchedule);
        imageView.setVisibility(View.GONE);

        String text = timeTableArrayList.get(position);
       /* if (text.startsWith("Recess") || text.equalsIgnoreCase("Recess to Recess") || text.equalsIgnoreCase("Recess to Recess\n()") || text.contains("Recess0Recess0") || text.contains("Recess0")) {
            timeTableArrayList.set(position, "Recess");
        }

        if (timeTableArrayList.get(position).equalsIgnoreCase("Recess")) {

            imageView.setVisibility(View.VISIBLE);
            textView.setVisibility(View.GONE);

        }


        if(text.contains("PR")){



        }*/

      /*  if (text.contains("PR")) {

            String text1 = "";
            String text2 = "";
            if (position < timeTableArrayList.size()) {


                text1 = timeTableArrayList.get(position);
                text2 = timeTableArrayList.get(position + 1);
                if(text1!="" && text2!="" && text1.contains("PR") && text2.contains("PR")){
                    if(!text1.equalsIgnoreCase(text2)){
                        textView.setVisibility(View.GONE);
                    }
                }
            }

        }*/

     /*   textView.setText(timeTableArrayList.get(position));

        if (position < noOfColumns) {

            relLytSchedule.setBackgroundColor(Color.parseColor("#01c6d7"));
            textView.setTextSize((float) 11);
            textView.setGravity(Gravity.CENTER);
        }

        if (text.contains("PR")) {

            *//*relLytSchedule.setBackgroundColor(Color.parseColor("#FFFFFF"));
            //textView.setTextColor(Color.parseColor("#FFFFFF"));
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.FILL_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(1, 1, 0, 1); // margins as you wish
                        textView.setLayoutParams(layoutParams);

                *//*
            textView.setBackgroundColor(Color.parseColor("#F6F5C7"));
        }*/

      /*  if(text.contains("-PR")){
            relLytSchedule.setBackgroundColor(Color.parseColor("#F6F5C7"));
            if(position%2 != 0){
                if(!timeTableArrayList.get(position).isEmpty() && timeTableArrayList.get(position).contains("-PR")){

                    textView.setVisibility(View.INVISIBLE);
                   *//* RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                    params.width =300;
                    params.height =100;
                    params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                    relLytSchedule.setLayoutParams(params);*//*


                }
            }
        }*/

        if(text.startsWith("Recess") || text.equalsIgnoreCase("Recess to Recess") || text.equalsIgnoreCase("Recess to Recess\n()") || text.contains("Recess0Recess0") || text.contains("Recess0")){
            timeTableArrayList.set(position,"Recess");

        }

        if(timeTableArrayList.get(position).equalsIgnoreCase("Recess")) {

            imageView.setVisibility(View.VISIBLE);
            textView.setVisibility(View.GONE);

        }

        textView.setText(timeTableArrayList.get(position));

        if(position<noOfColumns){

            relLytSchedule.setBackgroundColor(Color.parseColor("#01c6d7"));
            textView.setTextSize((float) 11);
            textView.setGravity(Gravity.CENTER);
        }

        return grid;
    }



}
