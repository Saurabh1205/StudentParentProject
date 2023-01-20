package com.wordpro.studentproject.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.wordpro.studentproject.R;
import com.wordpro.studentproject.model.DaywiseAttndModel;
import com.wordpro.studentproject.model.StudTimeTableModel;
import com.wordpro.studentproject.model.TeachPlanSubjModel;
import com.wordpro.studentproject.model.UnviSubjModel;
import com.wordpro.studentproject.webConfig.URLEndPoints;

import java.util.ArrayList;

public class New_Schedule_TimeTableAdpter extends RecyclerView.Adapter<New_Schedule_TimeTableAdpter.ViewHolder>{

    Context Mcontext;
    public static DaywiseAttndModel daywiseAttndModel;
    public static ArrayList<UnviSubjModel.DataBean> unviSubject;
    public ArrayList<StudTimeTableModel.DataBean> timeTableList;
    String ff;
    public interface OnItemClickListener {
        void onItemClick(View itemView, int position, String name);
    }
    private New_Schedule_TimeTableAdpter.OnItemClickListener mCallbacks;

    public void setOnItemClickListener(New_Schedule_TimeTableAdpter.OnItemClickListener mCallbacks){
        this.mCallbacks = mCallbacks;
    }

    public New_Schedule_TimeTableAdpter(Context context, ArrayList<StudTimeTableModel.DataBean> pendingJobDetails) {
        this.Mcontext =context;
        this.timeTableList =pendingJobDetails;

    }

    @NonNull
    @Override
    public New_Schedule_TimeTableAdpter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int postion) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.schedule_time_table_layout, viewGroup, false);
        return new ViewHolder(itemView);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int postion) {

        if (timeTableList.get(postion).getPERIOD_FROM_TIME().equalsIgnoreCase("Recess")){
            holder.txt_date.setText("Recess  ");
            holder.txt_date.setTextColor(ContextCompat.getColor(Mcontext, R.color.lunchBreak));
            holder.txt_sub_name.setText("Lunch Break");
            holder.txt_sub_name.setTextColor(ContextCompat.getColor(Mcontext, R.color.lunchBreak));
            holder.txt_th.setVisibility(View.GONE);
            holder.img_start.setImageDrawable(ContextCompat.getDrawable(Mcontext, R.drawable.icon_break));
            holder.img_end.setImageDrawable(ContextCompat.getDrawable(Mcontext, R.drawable.icon_break));
            holder.lay_branch.setVisibility(View.INVISIBLE);
            //holder.txt_pro_Name.setVisibility(View.GONE);
        }else {
            if (URLEndPoints.ConstanceLoginUser.equalsIgnoreCase("Student")) {
                holder.txt_pro_Name.setTextColor(ContextCompat.getColor(Mcontext, R.color.text_blue));
                holder.txt_th.setTextColor(ContextCompat.getColor(Mcontext, R.color.text_blue));
            }else {
                holder.txt_pro_Name.setTextColor(ContextCompat.getColor(Mcontext, R.color.present));
                holder.txt_th.setTextColor(ContextCompat.getColor(Mcontext, R.color.present));
            }

            holder.txt_date.setText(timeTableList.get(postion).getStartTime() +" "+" To "+" "+ timeTableList.get(postion).getEndTime() );

            if (!timeTableList.get(postion).getEMP_INITIAL().equalsIgnoreCase(" ")){
                holder.txt_pro_Name.setVisibility(View.VISIBLE);
                holder.txt_pro_Name.setText(timeTableList.get(postion).getEMP_FULL_NAME());
            }else {
                holder.txt_pro_Name.setVisibility(View.GONE);
            }

            holder.txt_sub_name.setText(timeTableList.get(postion).getSUB_SHORT_DESC());
            holder.txt_th.setVisibility(View.VISIBLE);
            holder.txt_th.setText(timeTableList.get(postion).getAPPLI_TYPE_SHORT_NAME());

            if (!timeTableList.get(postion).getSUB_BATCH_NAME().equals("null") && !timeTableList.get(postion).getSUB_BATCH_NAME().equalsIgnoreCase(" ")){
                holder.lay_branch.setVisibility(View.VISIBLE);
                holder.BranchAll.setVisibility(View.VISIBLE);
                holder.BranchAll.setText(timeTableList.get(postion).getSUB_BATCH_NAME());
            }else {
                holder.lay_branch.setVisibility(View.INVISIBLE);
                holder.BranchAll.setVisibility(View.INVISIBLE);
            }

        }

    }

    @Override
    public int getItemCount() {
        return timeTableList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView  txt_sub_name,txt_th,txt_date,txt_pro_Name,BranchAll;
        private ImageView img_start,img_end;
        private View view_mid;
        private LinearLayout lay_branch;
        private RecyclerView even_img_recyclerView;
        private CardView  card_view_sub;
        private RatingBar ratingBar;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //Typeface customTypeOne = Typeface.createFromAsset(itemView.getContext().getAssets(), "font/Poppins-Medium.otf");
            Typeface type_faceHeading = Typeface.createFromAsset(Mcontext.getAssets(), "font/Poppins-Bold.ttf");
            Typeface type_faceContent = Typeface.createFromAsset(Mcontext.getAssets(), "font/Poppins-Regular.ttf");

            txt_sub_name= (TextView) itemView.findViewById(R.id.txt_sub_name);
            txt_th= (TextView)itemView.findViewById(R.id.txt_th);
            txt_date= (TextView)itemView.findViewById(R.id.txt_date);
            txt_pro_Name= (TextView)itemView.findViewById(R.id.txt_pro_Name);
            BranchAll = (TextView)itemView.findViewById(R.id.BranchAll);
            lay_branch  = (LinearLayout) itemView.findViewById(R.id.lay_branch);
            img_start= (ImageView)itemView.findViewById(R.id.img_start);
            img_end= (ImageView)itemView.findViewById(R.id.img_end);
            view_mid = (View)itemView.findViewById(R.id.view_mid);

            txt_sub_name.setTypeface(type_faceContent);
            txt_th.setTypeface(type_faceContent);
            txt_date.setTypeface(type_faceHeading);
            txt_pro_Name.setTypeface(type_faceHeading);
            BranchAll.setTypeface(type_faceHeading);

        }
    }

}
