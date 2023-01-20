package com.wordpro.studentproject.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.wordpro.studentproject.R;
import com.wordpro.studentproject.model.DaywiseAttndModel;
import com.wordpro.studentproject.model.TeachPlanSubjModel;
import com.wordpro.studentproject.model.UnviSubjModel;

import java.util.ArrayList;

import static com.wordpro.studentproject.R.id.frame_syllabus_container;

public class University_syllabus_Adpter extends RecyclerView.Adapter<University_syllabus_Adpter.ViewHolder>{

    Context Mcontext;
    public static DaywiseAttndModel daywiseAttndModel;
    public static ArrayList<UnviSubjModel.DataBean> unviSubject;
    public ArrayList<TeachPlanSubjModel.DataBean> teachPlanSubjArrayList;
    String ff;
    public interface OnItemClickListener {
        void onItemClick(View itemView, int position,String name);
    }
    private University_syllabus_Adpter.OnItemClickListener mCallbacks;

    public void setOnItemClickListener(University_syllabus_Adpter.OnItemClickListener mCallbacks){
        this.mCallbacks = mCallbacks;
    }

    public University_syllabus_Adpter(Context context, ArrayList<UnviSubjModel.DataBean> pendingJobDetails ,ArrayList<TeachPlanSubjModel.DataBean> teachPlanSubjArrayList) {
        this.Mcontext =context;
        this.unviSubject =pendingJobDetails;
        this.teachPlanSubjArrayList = teachPlanSubjArrayList;
    }

    @NonNull
    @Override
    public University_syllabus_Adpter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int postion) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.university_sylabuss_frg, viewGroup, false);
        return new ViewHolder(itemView);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int postion) {

       holder.txt_sub_name.setText(unviSubject.get(postion).getSUBJECT_DESCRIPTION());
       holder.txt_Tm.setText(unviSubject.get(postion).getAPPLI_TYPE_DESCRIPTION());
       holder.txt_prof_Name.setText("Prof "+ unviSubject.get(postion).getEMP_EXCH_FAC());



        holder.txt_teachningPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String teachningPlan = "TEACHING PLAN";

                holder.txt_teachningPlan.setTextColor(ContextCompat.getColor(Mcontext, R.color.text_blue));
                holder.txt_Status.setTextColor(ContextCompat.getColor(Mcontext, R.color.dd));
                holder.txt_syllabus.setTextColor(ContextCompat.getColor(Mcontext, R.color.dd));
                holder.txt_teachningPlan.setBackground(ContextCompat.getDrawable(Mcontext,R.drawable.btn_round_shap_attendance_btn));
                // Triggers click upwards to the adapter on click
                if (mCallbacks != null) {
                    if (postion != RecyclerView.NO_POSITION) {
                        mCallbacks.onItemClick(v, postion,teachningPlan);
                    }
                }
            }
        });

        holder.txt_Status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Status = "STATUS";
                // Triggers click upwards to the adapter on click
                if (mCallbacks != null) {

                    if (postion != RecyclerView.NO_POSITION) {
                        mCallbacks.onItemClick(v, postion,Status);
                    }
                }
                holder.txt_syllabus.setTextColor(ContextCompat.getColor(Mcontext, R.color.dd));
                holder.txt_teachningPlan.setTextColor(ContextCompat.getColor(Mcontext, R.color.dd));
                holder.txt_Status.setTextColor(ContextCompat.getColor(Mcontext, R.color.text_blue));
                holder.txt_Status.setBackground(ContextCompat.getDrawable(Mcontext,R.drawable.btn_round_shap_attendance_btn));

            }
        });
        holder.txt_syllabus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Syllabus = "SYLLABUS";
                // Triggers click upwards to the adapter on click
                if (mCallbacks != null) {

                    if (postion != RecyclerView.NO_POSITION) {
                        mCallbacks.onItemClick(v, postion,Syllabus);
                    }
                }

                holder.txt_syllabus.setBackground(ContextCompat.getDrawable(Mcontext,R.drawable.btn_round_shap_attendance_btn));

                holder.txt_Status.setTextColor(ContextCompat.getColor(Mcontext, R.color.dd));
                holder.txt_teachningPlan.setTextColor(ContextCompat.getColor(Mcontext, R.color.dd));
                holder.txt_syllabus.setTextColor(ContextCompat.getColor(Mcontext, R.color.text_blue));
            }
        });

    }

    @Override
    public int getItemCount() {
        return unviSubject.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView  txt_sub_name,txt_Tm,txt_tolLecture,txt_Com_Lecture,total_unit_Value,txt_prof_Name,t,tt,txt_syllabus,txt_teachningPlan,txt_Status;
        private ImageView src_img;
        private RecyclerView even_img_recyclerView;
        private CardView  card_view_sub;
        private RatingBar ratingBar;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //Typeface customTypeOne = Typeface.createFromAsset(itemView.getContext().getAssets(), "font/Poppins-Medium.otf");
            Typeface type_faceHeading = Typeface.createFromAsset(Mcontext.getAssets(), "font/Poppins-Bold.ttf");
            Typeface type_faceContent = Typeface.createFromAsset(Mcontext.getAssets(), "font/Poppins-Regular.ttf");

            txt_sub_name= (TextView) itemView.findViewById(R.id.txt_sub_name);
            txt_Tm= (TextView)itemView.findViewById(R.id.txt_Tm);
            txt_tolLecture= (TextView)itemView.findViewById(R.id.txt_tolLecture);
            txt_Com_Lecture= (TextView)itemView.findViewById(R.id.txt_Com_Lecture);
            total_unit_Value = (TextView)itemView.findViewById(R.id.total_unit_Value);
            txt_prof_Name= (TextView)itemView.findViewById(R.id.txt_prof_Name);
            t= (TextView)itemView.findViewById(R.id.t);
            tt= (TextView)itemView.findViewById(R.id.tt);

            txt_syllabus= (TextView)itemView.findViewById(R.id.txt_syllabus);
            txt_teachningPlan= (TextView)itemView.findViewById(R.id.txt_teachningPlan);
            txt_Status= (TextView)itemView.findViewById(R.id.txt_Status);


            card_view_sub = (CardView)itemView. findViewById(R.id.card_view_sub);

            txt_sub_name.setTypeface(type_faceHeading);
            txt_Tm.setTypeface(type_faceHeading);
            txt_tolLecture.setTypeface(type_faceHeading);
            txt_tolLecture.setTypeface(type_faceHeading);

            txt_syllabus.setTypeface(type_faceHeading);
            txt_teachningPlan.setTypeface(type_faceHeading);
            txt_Status.setTypeface(type_faceHeading);

            t.setTypeface(type_faceContent);
            tt.setTypeface(type_faceContent);
            txt_prof_Name.setTypeface(type_faceHeading);
            total_unit_Value.setTypeface(type_faceContent);
        }
    }

}
