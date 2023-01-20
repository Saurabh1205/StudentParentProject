package com.wordpro.studentproject.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.wordpro.studentproject.R;
import com.wordpro.studentproject.activities.JobDetailActivity;
import com.wordpro.studentproject.model.GetStudentDetailsModel;
import com.wordpro.studentproject.model.JobManagerModel;

import java.util.ArrayList;

public class StudentDetailsAdpter extends RecyclerView.Adapter<StudentDetailsAdpter.ViewHolder>{

    Context Mcontext;
    ArrayList<GetStudentDetailsModel.PendingJobDetail> jobList;

    public StudentDetailsAdpter(Context context, ArrayList<GetStudentDetailsModel.PendingJobDetail> pendingJobDetails) {
        this.Mcontext =context;
        this.jobList =pendingJobDetails;
    }

    @NonNull
    @Override
    public StudentDetailsAdpter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.show_job_details, viewGroup, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        holder.text_jobDe.setText(jobList.get(i).getJOBDESC());
        holder.textNoteDetails.setText(jobList.get(i).getNOTICEDESCRIPTION());
        holder.textNoteType.setText(jobList.get(i).getNOTICETYPE());
    }

    @Override
    public int getItemCount() {
        return jobList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textNoteDetails,textNoteType,text_jobDe;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Typeface customTypeOne = Typeface.createFromAsset(itemView.getContext().getAssets(), "font/Poppins-Medium.otf");
            text_jobDe=(TextView)itemView.findViewById(R.id.text_jobDe);
            textNoteDetails=(TextView)itemView.findViewById(R.id.textNoteDetails);
            textNoteType=(TextView)itemView.findViewById(R.id.textNoteType);
            text_jobDe.setTypeface(customTypeOne);
            textNoteDetails.setTypeface(customTypeOne);
            textNoteType.setTypeface(customTypeOne);
        }
    }


  /*  private StudentDetailsAdpter.OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }

    public void setOnItemClickListener(StudentDetailsAdpter.OnItemClickListener listener) {
        this.listener = listener;
    }


    public StudentDetailsAdpter(Context context, ArrayList<GetStudentDetailsModel.PendingJobDetail> jobList) {
        this.context = context;
        this.jobList = jobList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.show_job_details, null);
        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.text_jobDe.setText(jobList.get(position).getJOBDESC());
        holder.textNoteDetails.setText(jobList.get(position).getNOTICEDESCRIPTION());
        holder.textNoteType.setText(jobList.get(position).getNOTICETYPE());
    }

    @Override
    public int getItemCount() {
        return jobList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textNoteDetails,textNoteType,text_jobDe;


        public ViewHolder(final View itemView) {
            super(itemView);
            Typeface customTypeOne = Typeface.createFromAsset(itemView.getContext().getAssets(), "font/Poppins-Medium.otf");
            text_jobDe=(TextView)itemView.findViewById(R.id.text_jobDe);
            textNoteDetails=(TextView)itemView.findViewById(R.id.textNoteDetails);
            textNoteType=(TextView)itemView.findViewById(R.id.textNoteType);
            text_jobDe.setTypeface(customTypeOne);
            textNoteDetails.setTypeface(customTypeOne);
            textNoteType.setTypeface(customTypeOne);


            // Setup the click listener
            *//*itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Triggers click upwards to the adapter on click
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(itemView, position);
                        }
                    }

                }
            });*//*
        }
    }*/
}
