package com.wordpro.studentproject.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wordpro.studentproject.R;
import com.wordpro.studentproject.model.TeachPlanDataModel;

import java.util.ArrayList;

/**
 * Created by wccs1980 on 23-04-2018.
 */

public class TeachPlanAdapter extends RecyclerView.Adapter<TeachPlanAdapter.ViewHolder> {

    Context context;
    ArrayList<TeachPlanDataModel.DataBean> dataBeanArrayList;

    public TeachPlanAdapter(Context context, ArrayList<TeachPlanDataModel.DataBean> dataBeanArrayList) {
        this.context = context;
        this.dataBeanArrayList = dataBeanArrayList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.teach_plan_topic_row, null);
        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        String covered_status=dataBeanArrayList.get(position).getCovered_Status();
        if(covered_status.equalsIgnoreCase("Y")){
            holder.statusLayout.setBackgroundResource(R.color.green);
            holder.stripLyt.setBackgroundResource(R.color.green);
        }else{
            holder.statusLayout.setBackgroundResource(R.color.colorPrimary);
            holder.stripLyt.setBackgroundResource(R.color.colorPrimary);

        }

        holder.txtProposeDate.setText(dataBeanArrayList.get(position).getTOPIC_TENTITIVE_STATUS());
        holder.txtActualDate.setText("Covered : "+dataBeanArrayList.get(position).getComb_Covered_date());
        holder.txtUnitName.setText("UNIT : "+dataBeanArrayList.get(position).getUNIT_NAME());
        holder.txtTopic.setText(dataBeanArrayList.get(position).getTOPIC_DESCRIPTION());
    }

    @Override
    public int getItemCount() {
        return dataBeanArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtProposeDate,txtUnitName,txtTopic,txtActualDate;
        LinearLayout statusLayout,stripLyt;

        public ViewHolder(View itemView) {
            super(itemView);
            Typeface customTypeOne = Typeface.createFromAsset(itemView.getContext().getAssets(), "font/Poppins-Medium.otf");

            txtProposeDate=(TextView)itemView.findViewById(R.id.txtProposeDate);
            txtProposeDate.setTypeface(customTypeOne);
            txtUnitName=(TextView)itemView.findViewById(R.id.txtUnitName);
            txtUnitName.setTypeface(customTypeOne);
            txtTopic=(TextView)itemView.findViewById(R.id.txtTopic);
            txtTopic.setTypeface(customTypeOne);
            txtActualDate=(TextView)itemView.findViewById(R.id.txtActualDate);
            txtActualDate.setTypeface(customTypeOne);
            statusLayout=(LinearLayout)itemView.findViewById(R.id.statusLayout);
            stripLyt=(LinearLayout)itemView.findViewById(R.id.stripLyt);


        }
    }
}
