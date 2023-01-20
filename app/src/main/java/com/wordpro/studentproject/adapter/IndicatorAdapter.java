package com.wordpro.studentproject.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wordpro.studentproject.R;

import java.util.List;

/**
 * Created by wccs1980 on 04-05-2018.
 */

public class IndicatorAdapter extends RecyclerView.Adapter<IndicatorAdapter.ViewHolder> {

    private Context context;
    private List<String> statusList;

    public IndicatorAdapter(Context context, List<String> statusList) {
        this.context = context;
        this.statusList = statusList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.status_list, null);
        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String status=statusList.get(position);

        if(status.equalsIgnoreCase("Present")){

            holder.linearBg.setBackgroundColor(Color.parseColor("#4CAF50"));
            holder.txtStatus.setText(status +" (PR)");
            holder.txtStatus.setTextColor(Color.parseColor("#4CAF50"));

        }else if(status.equalsIgnoreCase("Absent")){

            holder.linearBg.setBackgroundColor(Color.parseColor("#f44336"));
            holder.txtStatus.setText(status+" (AB)");
            holder.txtStatus.setTextColor(Color.parseColor("#f44336"));

        }else if(status.equalsIgnoreCase("College Work")){

            holder.linearBg.setBackgroundColor(Color.parseColor("#2196F3"));
            holder.txtStatus.setText(status+" (CW)");
            holder.txtStatus.setTextColor(Color.parseColor("#2196F3"));

        }else if(status.equalsIgnoreCase("Leave")){

            holder.linearBg.setBackgroundColor(Color.parseColor("#a49da7"));
            holder.txtStatus.setText(status+" (LV)");
            holder.txtStatus.setTextColor(Color.parseColor("#a49da7"));

        }else if(status.equalsIgnoreCase("Class Absenteeism")){

            holder.linearBg.setBackgroundColor(Color.parseColor("#9C27B0"));
            holder.txtStatus.setText(status+" (CA)");
            holder.txtStatus.setTextColor(Color.parseColor("#9C27B0"));

        }else if(status.equalsIgnoreCase("College Event")){

            holder.linearBg.setBackgroundColor(Color.parseColor("#FFC107"));
            holder.txtStatus.setText(status+" (CE)");
            holder.txtStatus.setTextColor(Color.parseColor("#FFC107"));

        }else if(status.equalsIgnoreCase("Holiday")){

            holder.linearBg.setBackgroundColor(Color.parseColor("#9b5c04"));
            holder.txtStatus.setText(status+" (HL)");
            holder.txtStatus.setTextColor(Color.parseColor("#9b5c04"));

        }else if(status.equalsIgnoreCase("Not Updated")){

            holder.linearBg.setBackgroundColor(Color.parseColor("#3F51B5"));
            holder.txtStatus.setText(status+" (NU)");
            holder.txtStatus.setTextColor(Color.parseColor("#3F51B5"));

        }else if(status.equalsIgnoreCase("Faculty Leave")){

            holder.linearBg.setBackgroundColor(Color.parseColor("#c2cf0c"));
            holder.txtStatus.setText(status+" (FL)");
            holder.txtStatus.setTextColor(Color.parseColor("#c2cf0c"));

        }else if(status.equalsIgnoreCase("SUBMITTED")){
            holder.linearBg.setBackgroundColor(Color.parseColor("#FFC107"));
            holder.txtStatus.setText(status);
            holder.txtStatus.setTextColor(Color.parseColor("#FFC107"));
        }else if(status.equalsIgnoreCase("APPROVED_BY_FACULTY")){
            holder.linearBg.setBackgroundColor(Color.parseColor("#4CAF50"));
            holder.txtStatus.setText(status);
            holder.txtStatus.setTextColor(Color.parseColor("#4CAF50"));
        }else if(status.equalsIgnoreCase("SENT_FOR_CORRECTION")){
            holder.linearBg.setBackgroundColor(Color.parseColor("#c2cf0c"));
            holder.txtStatus.setText(status);
            holder.txtStatus.setTextColor(Color.parseColor("#c2cf0c"));
        }else if(status.equalsIgnoreCase("REJECTED_BY_FACULTY")){
            holder.linearBg.setBackgroundColor(Color.parseColor("#f44336"));
            holder.txtStatus.setText(status);
            holder.txtStatus.setTextColor(Color.parseColor("#f44336"));
        }else if(status.equalsIgnoreCase("PUBLISHED")){
            holder.linearBg.setBackgroundColor(Color.parseColor("#01c6d7"));
            holder.txtStatus.setText(status);
            holder.txtStatus.setTextColor(Color.parseColor("#01c6d7"));
        }

    }

    @Override
    public int getItemCount() {
        return statusList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout linearBg;
        TextView txtStatus;

        public ViewHolder(View itemView) {
            super(itemView);
            Typeface customTypeOne = Typeface.createFromAsset(itemView.getContext().getAssets(), "font/Poppins-Medium.otf");
            linearBg=(LinearLayout)itemView.findViewById(R.id.linearBg);
            txtStatus=(TextView)itemView.findViewById(R.id.txtStatus);
            txtStatus.setTypeface(customTypeOne);
        }
    }
}
