package com.wordpro.studentproject.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wordpro.studentproject.R;
import com.wordpro.studentproject.model.JobDetailsModel;

import java.util.ArrayList;

public class JobDetailAdpter extends RecyclerView.Adapter<JobDetailAdpter.ViewHolder> {

    Context context;
    ArrayList<JobDetailsModel.PendingJobDetailsBean> jobDetailList;

    private JobDetailAdpter.OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }

    public void setOnItemClickListener(JobDetailAdpter.OnItemClickListener listener) {
        this.listener = listener;
    }


    public JobDetailAdpter(Context context, ArrayList<JobDetailsModel.PendingJobDetailsBean> jobDetailList) {
        this.context = context;
        this.jobDetailList = jobDetailList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.job_detail_row_layout, null);
        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.txtJob.setText(jobDetailList.get(position).getJOB_DESC());
        holder.txtSrNo.setText(String.valueOf(position+1));
    }

    @Override
    public int getItemCount() {
        return jobDetailList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtJob,txtSrNo;

        public ViewHolder(final View itemView) {
            super(itemView);
            Typeface customTypeOne = Typeface.createFromAsset(itemView.getContext().getAssets(), "font/Poppins-Medium.otf");
            txtJob=(TextView)itemView.findViewById(R.id.txtJob);
            txtJob.setTypeface(customTypeOne);
            txtSrNo=(TextView)itemView.findViewById(R.id.txtSrNo);
            txtSrNo.setTypeface(customTypeOne);
            // Setup the click listener
            itemView.setOnClickListener(new View.OnClickListener() {
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
            });
        }
    }
}
