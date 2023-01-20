package com.wordpro.studentproject.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wordpro.studentproject.R;
import com.wordpro.studentproject.model.AssignmentModel;
import com.wordpro.studentproject.model.JobManagerModel;

import java.util.ArrayList;

/**
 * Created by wccs1980 on 27-04-2018.
 */

public class Alert_Info_List_Adapter extends RecyclerView.Adapter<Alert_Info_List_Adapter.ViewHolder> {

    Context Mcontext;
   // private ArrayList<AssignmentModel.StudAssignDtlsBean> assignDtlsBeans;
    private ArrayList<JobManagerModel.PendingJobSummeryBean> jobManagerModelArrayList;

    public interface OnItemClickListener {
        void onItemClick(View itemView, int position, String name,String NoofJob);
    }
    private Alert_Info_List_Adapter.OnItemClickListener mCallbacks;

    public void setOnItemClickListener(Alert_Info_List_Adapter.OnItemClickListener mCallbacks){
        this.mCallbacks = mCallbacks;
    }


    public Alert_Info_List_Adapter(Context context , ArrayList<JobManagerModel.PendingJobSummeryBean> list) {
       this. Mcontext = context;
       this. jobManagerModelArrayList = list;


    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.alert_infoadapter_layout, null);
        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.txt_itemName.setText(jobManagerModelArrayList.get(position).getJOB_DESC());
        String  JobDes= jobManagerModelArrayList.get(position).getJOB_DESC();
        String JobNO =jobManagerModelArrayList.get(position).getNo_Of_Jobs();


        holder.cartView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Triggers click upwards to the adapter on click
                if (mCallbacks != null) {

                    if (position != RecyclerView.NO_POSITION) {
                        mCallbacks.onItemClick(v, position,JobDes,JobNO);
                    }
                }
            }
        });



    }

    @Override
    public int getItemCount() {
        return jobManagerModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txt_itemName;
        CardView   cartView;
        public ViewHolder(View itemView) {
            super(itemView);



            txt_itemName =(TextView) itemView.findViewById(R.id.txt_itemName);
            cartView =(CardView) itemView.findViewById(R.id.cartView);



          /*  Typeface typefaceHeading = Typeface.createFromAsset(Mcontext.getAssets(), "font/Poppins-Bold.otf");
            Typeface typefaceContent = Typeface.createFromAsset(Mcontext.getAssets(), "font/Poppins-Medium.otf");

            txt_itemName.setTypeface(typefaceHeading);*/


        }
    }
}
