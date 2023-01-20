package com.wordpro.studentproject.adapter;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wordpro.studentproject.R;
import com.wordpro.studentproject.model.DaywiseAttndModel;

import java.util.ArrayList;

/**
 * Created by wccs1980 on 07-05-2018.
 */

public class DayWiseAttendApdater extends RecyclerView.Adapter<DayWiseAttendApdater.ViewHolder> {


    Context context;
    ArrayList<DaywiseAttndModel.DataBean> daywiseArrayList;

    public DayWiseAttendApdater(Context context, ArrayList<DaywiseAttndModel.DataBean> daywiseArrayList) {
        this.context = context;
        this.daywiseArrayList = daywiseArrayList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.daywise_attnd_row_layout, null);
        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.txtStartTime.setText(daywiseArrayList.get(position).getPERIOD_FROM_TIME());
        holder.txtEndTime.setText(daywiseArrayList.get(position).getPERIOD_UPTO_TIME());
        holder.txtSubject.setText("SUBJECT : "+daywiseArrayList.get(position).getSUBJECT_DESCRIPTION() +" ("+daywiseArrayList.get(position).getTYPE_SHORT_NAME()+")");
        holder.txtPrdSq.setText(daywiseArrayList.get(position).getPERIOD_SEQUENCE_NO());
        holder.txtPrdType.setText(daywiseArrayList.get(position).getPERIOD_TYPE());
        holder.txtFaculty.setText("FACULTY : "+daywiseArrayList.get(position).getPROF_EMP_FN_MN_SHORT());

        String topic=daywiseArrayList.get(position).getTOPIC_DESCRIPTION();
        topic=topic.replace("#"," \n");
        holder.txtTopicDescptn.setText(topic);
        String ATTENDANCE_LOCK=daywiseArrayList.get(position).getATTENDANCE_LOCK();
        if(ATTENDANCE_LOCK.equalsIgnoreCase("N")){
            holder.txtstatus.setText("NU");
            holder.linearTimeLyt.setBackgroundResource(R.color.UnMarkAttend);
            holder.circularLyt.getBackground().setColorFilter(Color.parseColor("#3F51B5"), PorterDuff.Mode.SRC_ATOP);
            holder.txtTopicDescptn.setText(" ");

        }else if(ATTENDANCE_LOCK.equalsIgnoreCase("Y")){

            String STUDENT_STATUS=daywiseArrayList.get(position).getSTUDENT_STATUS();
            String ATTENDANCE_STATUS=daywiseArrayList.get(position).getATTENDANCE_STATUS();
            if(ATTENDANCE_STATUS.equalsIgnoreCase("PERIOD_TAKEN")){
                if(STUDENT_STATUS.equalsIgnoreCase("ABSENT")){
                    holder.txtstatus.setText("AB");
                    holder.linearTimeLyt.setBackgroundResource(R.color.absent);
                    holder.circularLyt.getBackground().setColorFilter(Color.parseColor("#f44336"), PorterDuff.Mode.SRC_ATOP);
                }else if(STUDENT_STATUS.equalsIgnoreCase("PRESENT")){
                    holder.txtstatus.setText("PR");
                    holder.linearTimeLyt.setBackgroundResource(R.color.present);
                    holder.circularLyt.getBackground().setColorFilter(Color.parseColor("#4CAF50"), PorterDuff.Mode.SRC_ATOP);
                }else if(STUDENT_STATUS.equalsIgnoreCase("C-work")){
                    holder.txtstatus.setText("CW");
                    holder.linearTimeLyt.setBackgroundResource(R.color.cwork);
                    holder.circularLyt.getBackground().setColorFilter(Color.parseColor("#2196F3"), PorterDuff.Mode.SRC_ATOP);
                }else if(STUDENT_STATUS.equalsIgnoreCase("S-LEAVE")){
                    holder.txtstatus.setText("LV");
                    holder.linearTimeLyt.setBackgroundResource(R.color.leave);
                    holder.circularLyt.getBackground().setColorFilter(Color.parseColor("#E91E63"), PorterDuff.Mode.SRC_ATOP);
                }
            }else if(ATTENDANCE_STATUS.equalsIgnoreCase("COLLEGE_WORK")){
                holder.txtstatus.setText("CW");
                holder.linearTimeLyt.setBackgroundResource(R.color.cwork);
                holder.circularLyt.getBackground().setColorFilter(Color.parseColor("#2196F3"), PorterDuff.Mode.SRC_ATOP);
            }else if(ATTENDANCE_STATUS.equalsIgnoreCase("BUNK")){
                holder.txtstatus.setText("CA");
                holder.linearTimeLyt.setBackgroundResource(R.color.ClassAb);
                holder.circularLyt.getBackground().setColorFilter(Color.parseColor("#9C27B0"), PorterDuff.Mode.SRC_ATOP);
            }else if(ATTENDANCE_STATUS.equalsIgnoreCase("COLLEGE_EVENT")){
                holder.txtstatus.setText("CE");
                holder.linearTimeLyt.setBackgroundResource(R.color.CEvent);
                holder.circularLyt.getBackground().setColorFilter(Color.parseColor("#FFC107"), PorterDuff.Mode.SRC_ATOP);
            }else if(ATTENDANCE_STATUS.equalsIgnoreCase("HOLIDAY")){
                holder.txtstatus.setText("HL");
                holder.linearTimeLyt.setBackgroundResource(R.color.Holiday);
                holder.circularLyt.getBackground().setColorFilter(Color.parseColor("#9b5c04"), PorterDuff.Mode.SRC_ATOP);
            }else if(ATTENDANCE_STATUS.equalsIgnoreCase("ABSENT") && STUDENT_STATUS.equalsIgnoreCase("")){
                holder.txtstatus.setText("FL");
                holder.linearTimeLyt.setBackgroundResource(R.color.FacultyLeave);
                holder.circularLyt.getBackground().setColorFilter(Color.parseColor("#c2cf0c"), PorterDuff.Mode.SRC_ATOP);
            }

        }
    }

    @Override
    public int getItemCount() {
        return daywiseArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout linearTimeLyt,circularLyt;
        TextView txtStartTime,txtEndTime,txtSubject,txtFaculty,txtPrdSq,txtTopicDescptn,txtstatus,txtPrdType;


        public ViewHolder(View itemView) {
            super(itemView);
            Typeface customTypeOne = Typeface.createFromAsset(itemView.getContext().getAssets(), "font/Poppins-Medium.otf");
            linearTimeLyt=(LinearLayout)itemView.findViewById(R.id.linearTimeLyt);
            circularLyt=(LinearLayout)itemView.findViewById(R.id.circularLyt);
            txtStartTime=(TextView)itemView.findViewById(R.id.txtStartTime);
            txtStartTime.setTypeface(customTypeOne);
            txtEndTime=(TextView)itemView.findViewById(R.id.txtEndTime);
            txtEndTime.setTypeface(customTypeOne);
            txtSubject=(TextView)itemView.findViewById(R.id.txtSubject);
            txtSubject.setTypeface(customTypeOne);
            txtPrdSq=(TextView)itemView.findViewById(R.id.txtPrdSq);
            txtPrdSq.setTypeface(customTypeOne);
            txtFaculty=(TextView)itemView.findViewById(R.id.txtFaculty);
            txtFaculty.setTypeface(customTypeOne);
            txtTopicDescptn=(TextView)itemView.findViewById(R.id.txtTopicDescptn);
            txtTopicDescptn.setTypeface(customTypeOne);
            txtstatus=(TextView)itemView.findViewById(R.id.txtstatus);
            txtstatus.setTypeface(customTypeOne);
            txtPrdType=(TextView)itemView.findViewById(R.id.txtPrdType);
            txtPrdType.setTypeface(customTypeOne);

        }
    }
}
