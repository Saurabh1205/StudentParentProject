package com.wordpro.studentproject.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wordpro.studentproject.R;
import com.wordpro.studentproject.model.DaywiseAttndModel;
import com.wordpro.studentproject.model.SpanwiseModel;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class SpanDayWiseAttendanceAdpter extends RecyclerView.Adapter<SpanDayWiseAttendanceAdpter.ViewHolder>{

    Context Mcontext;
    public static DaywiseAttndModel daywiseAttndModel;
    ArrayList<SpanwiseModel.DataBean> spanArrayList;
    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }
    private SpanDayWiseAttendanceAdpter.OnItemClickListener mCallbacks;

    public void setOnItemClickListener(SpanDayWiseAttendanceAdpter.OnItemClickListener mCallbacks){
        this.mCallbacks = mCallbacks;
    }

    public SpanDayWiseAttendanceAdpter(Context context, ArrayList<SpanwiseModel.DataBean> pendingJobDetails) {
        this.Mcontext =context;
        this.spanArrayList =pendingJobDetails;
    }

    @NonNull
    @Override
    public SpanDayWiseAttendanceAdpter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int postion) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.span_wise_all_list, viewGroup, false);
        return new ViewHolder(itemView);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int postion) {

        if (spanArrayList.get(postion).getAttendance_Date()!=null){
            holder.txt_date.setText(spanArrayList.get(postion).getCommonDate());
        }else {
            holder.txt_date.setText("  ");
        }
        if (spanArrayList.get(postion).getPeriod_Attended()!=null){
            holder.txt_total_pre.setText(spanArrayList.get(postion).getPeriod_Attended());
        }else {
            holder.txt_total_pre.setText("0");
        }
        if (spanArrayList.get(postion).getToTal_periods()!=null){
            holder.txt_total_Att.setText(spanArrayList.get(postion).getToTal_periods());
        }else {
            holder.txt_total_Att.setText("0");
        }

        holder.card_view_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Triggers click upwards to the adapter on click
                if (mCallbacks != null) {

                    if (postion != RecyclerView.NO_POSITION) {
                        mCallbacks.onItemClick(v, postion);
                    }
                }
            }
        });



    }

    @Override
    public int getItemCount() {
        return spanArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView  txt_date,txt_total_pre,txt_pre,txt_total_Att,txt_Att;
        private ImageView gallery_img;
        private CardView  card_view_sub;

        private RecyclerView even_img_recyclerView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //Typeface customTypeOne = Typeface.createFromAsset(itemView.getContext().getAssets(), "font/Poppins-Medium.otf");
            Typeface type_faceHeading = Typeface.createFromAsset(Mcontext.getAssets(), "font/Poppins-Bold.ttf");
            Typeface type_faceContent = Typeface.createFromAsset(Mcontext.getAssets(), "font/Poppins-Regular.ttf");

            txt_total_pre= (TextView) itemView.findViewById(R.id.txt_total_pre);
            txt_pre= (TextView)itemView.findViewById(R.id.txt_pre);
            txt_total_Att= (TextView)itemView.findViewById(R.id.txt_total_Att);
            txt_Att= (TextView)itemView.findViewById(R.id.txt_Att);
            txt_date= (TextView)itemView.findViewById(R.id.txt_date);
            card_view_sub  = (CardView) itemView.findViewById(R.id.card_view_sub);

            try {
                txt_total_pre.setTypeface(type_faceHeading);
                txt_date.setTypeface(type_faceHeading);
                txt_total_Att.setTypeface(type_faceHeading);
                txt_Att.setTypeface(type_faceContent);
                txt_date.setTypeface(type_faceHeading);
            }catch (Exception e){
                e.printStackTrace();
            }


        }
    }

}
