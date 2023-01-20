package com.wordpro.studentproject.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wordpro.studentproject.R;
import com.wordpro.studentproject.model.DaywiseAttndModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static com.wordpro.studentproject.activities.NavigationActivity.BASE_URL;

/**
 * Created by wccs1980 on 09-05-2018.
 */

public class SubjWiseAttendAdapter extends RecyclerView.Adapter<SubjWiseAttendAdapter.ViewHolder> {

    Context context;
    ArrayList<DaywiseAttndModel.DataBean> subjWiseAttndArrayList;

    public SubjWiseAttendAdapter(Context context, ArrayList<DaywiseAttndModel.DataBean> subjWiseAttndArrayList) {
        this.context = context;
        this.subjWiseAttndArrayList = subjWiseAttndArrayList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.subjwise_attnd_row_layout, null);
        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        String date=  "16 Jul  2019";//subjWiseAttndArrayList.get(position).getATTENDANCE_DATE();
        String[] value=date.split(" ");
        String dt=value[0];
        dt = dt.replace("-", "/");
        String d[] = dt.split("/");
        String day="",month="",year="";

        if(BASE_URL.equalsIgnoreCase("http://117.220.228.12:80/api/")){
            month = d[0];
            day = d[1];
            year = d[2];
        }else{
            month = d[0];
            day = d[1];
            year = d[2];
        }


        Date dt1 = null;
        //holder.txtDate.setText(day + "/" + month + "/" + year);
        String input_date = day + "/" + month + "/" + year;
        SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
        try {
            dt1 = format1.parse(input_date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String dayOfTheWeek = (String) DateFormat.format("EEEE", dt1); // Thursday
        String dayofMonth = (String) DateFormat.format("dd", dt1); // 20
        String monthString = (String) DateFormat.format("MMM", dt1); // Jun
        String monthNumber = (String) DateFormat.format("MM", dt1); // 06
        String years = (String) DateFormat.format("yyyy", dt1); // 2013


        holder.txtDate.setText(dayOfTheWeek+"\n"+dayofMonth+" "+monthString+" "+years);
        holder.txtTime.setText(subjWiseAttndArrayList.get(position).getPERIOD_FROM_TIME() +" TO "+subjWiseAttndArrayList.get(position).getPERIOD_UPTO_TIME());
        holder.txtPrdSq.setText(subjWiseAttndArrayList.get(position).getPERIOD_SEQUENCE_NO());
        String topic=subjWiseAttndArrayList.get(position).getTOPIC_DESCRIPTION();
        topic=topic.replace("#"," \n");
        holder.txtTopicDescptn.setText(topic);

        holder.txtPrdtype.setText(subjWiseAttndArrayList.get(position).getTYPE_DESCRIPTION());

        String ATTENDANCE_LOCK=subjWiseAttndArrayList.get(position).getATTENDANCE_LOCK();
        if(ATTENDANCE_LOCK.equalsIgnoreCase("N")){
            holder.txtstatusAttnd.setText("NU");
            holder.linearTimeLyt.setBackgroundResource(R.color.UnMarkAttend);
            holder.txtPrdtype.setBackgroundResource(R.color.UnMarkAttend);
            holder.circularLyt.getBackground().setColorFilter(Color.parseColor("#3F51B5"), PorterDuff.Mode.SRC_ATOP);

        }else if(ATTENDANCE_LOCK.equalsIgnoreCase("Y")){

            String STUDENT_STATUS=subjWiseAttndArrayList.get(position).getSTUDENT_STATUS();
            String ATTENDANCE_STATUS=subjWiseAttndArrayList.get(position).getATTENDANCE_STATUS();
            if(ATTENDANCE_STATUS.equalsIgnoreCase("PERIOD_TAKEN")){
                if(STUDENT_STATUS.equalsIgnoreCase("ABSENT")){
                    holder.txtstatusAttnd.setText("AB");
                    holder.linearTimeLyt.setBackgroundResource(R.color.absent);
                    holder.txtPrdtype.setBackgroundResource(R.color.absent);
                    holder.circularLyt.getBackground().setColorFilter(Color.parseColor("#f44336"), PorterDuff.Mode.SRC_ATOP);
                }else if(STUDENT_STATUS.equalsIgnoreCase("PRESENT")){
                    holder.txtstatusAttnd.setText("PR");
                    holder.linearTimeLyt.setBackgroundResource(R.color.present);
                    holder.txtPrdtype.setBackgroundResource(R.color.present);
                    holder.circularLyt.getBackground().setColorFilter(Color.parseColor("#4CAF50"), PorterDuff.Mode.SRC_ATOP);
                }else if(STUDENT_STATUS.equalsIgnoreCase("C-work")){
                    holder.txtstatusAttnd.setText("CW");
                    holder.linearTimeLyt.setBackgroundResource(R.color.cwork);
                    holder.txtPrdtype.setBackgroundResource(R.color.cwork);
                    holder.circularLyt.getBackground().setColorFilter(Color.parseColor("#2196F3"), PorterDuff.Mode.SRC_ATOP);
                }else if(STUDENT_STATUS.equalsIgnoreCase("S-LEAVE")){
                    holder.txtstatusAttnd.setText("LV");
                    holder.linearTimeLyt.setBackgroundResource(R.color.leave);
                    holder.txtPrdtype.setBackgroundResource(R.color.leave);
                    holder.circularLyt.getBackground().setColorFilter(Color.parseColor("#E91E63"), PorterDuff.Mode.SRC_ATOP);
                }
            }else if(ATTENDANCE_STATUS.equalsIgnoreCase("COLLEGE_WORK")){
                holder.txtstatusAttnd.setText("CW");
                holder.linearTimeLyt.setBackgroundResource(R.color.cwork);
                holder.txtPrdtype.setBackgroundResource(R.color.cwork);
                holder.circularLyt.getBackground().setColorFilter(Color.parseColor("#2196F3"), PorterDuff.Mode.SRC_ATOP);
            }else if(ATTENDANCE_STATUS.equalsIgnoreCase("BUNK")){
                holder.txtstatusAttnd.setText("CA");
                holder.linearTimeLyt.setBackgroundResource(R.color.ClassAb);
                holder.txtPrdtype.setBackgroundResource(R.color.ClassAb);
                holder.circularLyt.getBackground().setColorFilter(Color.parseColor("#9C27B0"), PorterDuff.Mode.SRC_ATOP);
            }else if(ATTENDANCE_STATUS.equalsIgnoreCase("COLLEGE_EVENT")){
                holder.txtstatusAttnd.setText("CE");
                holder.linearTimeLyt.setBackgroundResource(R.color.CEvent);
                holder.txtPrdtype.setBackgroundResource(R.color.CEvent);
                holder.circularLyt.getBackground().setColorFilter(Color.parseColor("#FFC107"), PorterDuff.Mode.SRC_ATOP);
            }else if(ATTENDANCE_STATUS.equalsIgnoreCase("HOLIDAY")){
                holder.txtstatusAttnd.setText("HL");
                holder.linearTimeLyt.setBackgroundResource(R.color.Holiday);
                holder.txtPrdtype.setBackgroundResource(R.color.Holiday);
                holder.circularLyt.getBackground().setColorFilter(Color.parseColor("#9b5c04"), PorterDuff.Mode.SRC_ATOP);
            }else if(ATTENDANCE_STATUS.equalsIgnoreCase("ABSENT") && STUDENT_STATUS.equalsIgnoreCase("")){
                holder.txtstatusAttnd.setText("FL");
                holder.linearTimeLyt.setBackgroundResource(R.color.FacultyLeave);
                holder.txtPrdtype.setBackgroundResource(R.color.FacultyLeave);
                holder.circularLyt.getBackground().setColorFilter(Color.parseColor("#c2cf0c"), PorterDuff.Mode.SRC_ATOP);
            }

        }
    }

    @Override
    public int getItemCount() {
        return subjWiseAttndArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtDate,txtTime,txtPrdSq,txtTopicDescptn,txtstatusAttnd,txtPrdtype;
        LinearLayout linearTimeLyt,circularLyt;

        public ViewHolder(View itemView) {
            super(itemView);
            Typeface customTypeOne = Typeface.createFromAsset(itemView.getContext().getAssets(), "font/Poppins-Medium.otf");

            txtDate=(TextView)itemView.findViewById(R.id.txtDate);
            txtDate.setTypeface(customTypeOne);
            txtTime=(TextView)itemView.findViewById(R.id.txtTime);
            txtTime.setTypeface(customTypeOne);
            txtPrdSq=(TextView)itemView.findViewById(R.id.txtPrdSq);
            txtPrdSq.setTypeface(customTypeOne);
            txtTopicDescptn=(TextView)itemView.findViewById(R.id.txtTopicDescptn);
            txtTopicDescptn.setTypeface(customTypeOne);
            txtstatusAttnd=(TextView)itemView.findViewById(R.id.txtstatusAttnd);
            txtstatusAttnd.setTypeface(customTypeOne);
            linearTimeLyt=(LinearLayout) itemView.findViewById(R.id.linearTimeLyt);
            circularLyt=(LinearLayout)itemView.findViewById(R.id.circularLyt);
            txtPrdtype=(TextView)itemView.findViewById(R.id.txtPrdtype);
            txtPrdtype.setTypeface(customTypeOne);

        }
    }
}
