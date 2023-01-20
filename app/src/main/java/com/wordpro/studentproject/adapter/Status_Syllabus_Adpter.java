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
import com.wordpro.studentproject.model.SyllStatusModel;
import com.wordpro.studentproject.model.TeachPlanDataModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Status_Syllabus_Adpter extends RecyclerView.Adapter<Status_Syllabus_Adpter.ViewHolder>{

    Context Mcontext;
    public static DaywiseAttndModel daywiseAttndModel;
    ArrayList<SyllStatusModel.DataBean> syllStatusArrayList;
    private float CoveredPre;
    private int  TopVal;


    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }
    private Status_Syllabus_Adpter.OnItemClickListener mCallbacks;

    public void setOnItemClickListener(Status_Syllabus_Adpter.OnItemClickListener mCallbacks){
        this.mCallbacks = mCallbacks;
    }

    public Status_Syllabus_Adpter(Context context, ArrayList<SyllStatusModel.DataBean> pendingJobDetails) {
        this.Mcontext =context;
        this.syllStatusArrayList =pendingJobDetails;
    }

    @NonNull
    @Override
    public Status_Syllabus_Adpter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int postion) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.new_syllabus_status, viewGroup, false);
        return new ViewHolder(itemView);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int postion) {

        holder.txt_CoveredValue.setText(syllStatusArrayList.get(postion).getACTUAL_COVERED_PERCENTAGE());
        holder.txt_TopicValue.setText(syllStatusArrayList.get(postion).getTOTAL_TOPICS_IN_SYLLABUS());
        holder.txt_Topic_covValue.setText(syllStatusArrayList.get(postion).getTOT_COVERED_TOPICS());
    }

    @Override
    public int getItemCount() {
        return syllStatusArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private CardView  card_view_sub;
        private TextView txt_CoveredValue,txt_Covered_syllabus,txt_TopicValue,txt_total_topic,txt_Topic_covValue,txt_topic_covered;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //Typeface customTypeOne = Typeface.createFromAsset(itemView.getContext().getAssets(), "font/Poppins-Medium.otf");
            Typeface type_faceHeading = Typeface.createFromAsset(Mcontext.getAssets(), "font/Poppins-Bold.ttf");
            Typeface type_faceContent = Typeface.createFromAsset(Mcontext.getAssets(), "font/Poppins-Regular.ttf");
            card_view_sub = (CardView) itemView.findViewById(R.id.card_view_sub);
            txt_CoveredValue= (TextView)itemView.findViewById(R.id.txt_CoveredValue);
            txt_Covered_syllabus= (TextView)itemView.findViewById(R.id.txt_Covered_syllabus);
            txt_TopicValue= (TextView)itemView.findViewById(R.id.txt_TopicValue);
            txt_total_topic= (TextView)itemView.findViewById(R.id.txt_total_topic);
            txt_Topic_covValue= (TextView)itemView.findViewById(R.id.txt_Topic_covValue);
            txt_topic_covered= (TextView)itemView.findViewById(R.id.txt_topic_covered);


            txt_CoveredValue.setTypeface(type_faceHeading);
            txt_Covered_syllabus.setTypeface(type_faceHeading);
            txt_TopicValue.setTypeface(type_faceHeading);
            txt_total_topic.setTypeface(type_faceHeading);
            txt_Topic_covValue.setTypeface(type_faceHeading);
            txt_topic_covered.setTypeface(type_faceHeading);

        }
    }

}
