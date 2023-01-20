package com.wordpro.studentproject.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wordpro.studentproject.R;
import com.wordpro.studentproject.model.GetStudentDetailsModel;
import com.wordpro.studentproject.model.JobManagerModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wccs1980 on 27-04-2018.
 */

public class Alert_Info_Details_List_Adapter extends RecyclerView.Adapter<Alert_Info_Details_List_Adapter.ViewHolder> {

    Context Mcontext;
   // private ArrayList<AssignmentModel.StudAssignDtlsBean> assignDtlsBeans;
   List<GetStudentDetailsModel.PendingJobDetail> jobList;

    public Alert_Info_Details_List_Adapter(Context context, List<GetStudentDetailsModel.PendingJobDetail> pendingJobDetails) {
        this. Mcontext = context;
        this. jobList = pendingJobDetails;
    }

    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }
    private Alert_Info_Details_List_Adapter.OnItemClickListener mCallbacks;

    public void setOnItemClickListener(Alert_Info_Details_List_Adapter.OnItemClickListener mCallbacks){
        this.mCallbacks = mCallbacks;
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.alert_info_adapter_view_layout, null);
        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.txt_headerNme.setText(jobList.get(position).getNOTICETYPE());
        holder.txt_Des.setText(jobList.get(position).getNOTICEDESCRIPTION());
        holder.tv_LastDate.setText(jobList.get(position).getJOBDESC());

        holder.card_view_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Triggers click upwards to the adapter on click
                if (mCallbacks != null) {

                    if (position != RecyclerView.NO_POSITION) {
                        mCallbacks.onItemClick(v, position);
                    }
                }
            }
        });




    }

    @Override
    public int getItemCount() {
        return jobList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txt_headerNme,txt_sub_Last_date,tv_LastDate,txt_Des;
        CardView   card_view_info;
        public ViewHolder(View itemView) {
            super(itemView);



            txt_headerNme =(TextView) itemView.findViewById(R.id.txt_headerNme);
            card_view_info =(CardView) itemView.findViewById(R.id.card_view_info);
            txt_sub_Last_date =(TextView) itemView.findViewById(R.id.txt_sub_Last_date);
            tv_LastDate =(TextView) itemView.findViewById(R.id.tv_LastDate);
            txt_Des =(TextView) itemView.findViewById(R.id.txt_Des);



          /*  Typeface typefaceHeading = Typeface.createFromAsset(Mcontext.getAssets(), "font/Poppins-Bold.otf");
            Typeface typefaceContent = Typeface.createFromAsset(Mcontext.getAssets(), "font/Poppins-Medium.otf");

            txt_itemName.setTypeface(typefaceHeading);*/


        }
    }
}
