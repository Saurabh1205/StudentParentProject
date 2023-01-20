package com.wordpro.studentproject.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.wordpro.studentproject.R;
import com.wordpro.studentproject.model.JobManagerModel;

import java.util.ArrayList;

public class JobManagerAdpter extends RecyclerView.Adapter<JobManagerAdpter.ViewHolder>{

    Context context;
    ArrayList<JobManagerModel.PendingJobSummeryBean> jobList;


    private JobManagerAdpter.OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }

    public void setOnItemClickListener(JobManagerAdpter.OnItemClickListener listener) {
        this.listener = listener;
    }


    public JobManagerAdpter(Context context, ArrayList<JobManagerModel.PendingJobSummeryBean> jobList) {
        this.context = context;
        this.jobList = jobList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_layout, null);
        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.txtJob.setText(jobList.get(position).getJOB_DESC());
        holder.btnCount.setText(jobList.get(position).getNo_Of_Jobs());

    }

    @Override
    public int getItemCount() {
        return jobList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtJob;
        Button btnCount;

        public ViewHolder(final View itemView) {
            super(itemView);
            Typeface customTypeOne = Typeface.createFromAsset(itemView.getContext().getAssets(), "font/Poppins-Medium.otf");
            txtJob=(TextView)itemView.findViewById(R.id.txtJob);
            txtJob.setTypeface(customTypeOne);
            btnCount=(Button)itemView.findViewById(R.id.btnCount);
            btnCount.setTypeface(customTypeOne);

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
