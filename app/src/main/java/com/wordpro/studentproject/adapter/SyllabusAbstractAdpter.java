package com.wordpro.studentproject.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wordpro.studentproject.R;
import com.wordpro.studentproject.model.SyllStatusModel;

import java.util.ArrayList;

/**
 * Created by wccs1980 on 03-05-2018.
 */

public class SyllabusAbstractAdpter extends RecyclerView.Adapter<SyllabusAbstractAdpter.ViewHolder> {

    Context context;
    ArrayList<SyllStatusModel.DataBean> syllAbsArrayList;

    public SyllabusAbstractAdpter(Context context, ArrayList<SyllStatusModel.DataBean> syllAbsArrayList) {
        this.context = context;
        this.syllAbsArrayList = syllAbsArrayList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.syll_abs_row, null);
        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.txtVw1.setText(syllAbsArrayList.get(position).getSUBJECT_DESCRIPTION());
        holder.txtVw2.setText(syllAbsArrayList.get(position).getPROF_EMP_FN_MN_SHORT());
        String tr = syllAbsArrayList.get(position).getSUB_BATCH_NAME();
        if (tr != null){
            holder.txtVw3.setText(syllAbsArrayList.get(position).getSUB_BATCH_NAME());
        }else {
            holder.txtVw3.setText("");
        }
        holder.txtVw4.setText(syllAbsArrayList.get(position).getBRANCH_STANDARD_CODE());
        holder.txtVw5.setText(syllAbsArrayList.get(position).getProposed_No_Of_Lectures());
        holder.txtVw6.setText(syllAbsArrayList.get(position).getNo_of_Periods());
        holder.txtVw7.setText(syllAbsArrayList.get(position).getTOTAL_TOPICS_IN_SYLLABUS());
        holder.txtVw8.setText(syllAbsArrayList.get(position).getTOT_SCHEDULED_TOPICS_ASON());
        holder.txtVw9.setText(syllAbsArrayList.get(position).getTOT_COVERED_TOPICS());
        holder.txtVw10.setText(syllAbsArrayList.get(position).getDEFICIT_PRCTG()+" %");
        holder.txtVw11.setText(syllAbsArrayList.get(position).getSYLLABUS_COVERED_PERCENTAGE());
   /*     String actualPer=syllAbsArrayList.get(position).getSYLLABUS_COVERED_PERCENTAGE();
        actualPer=actualPer.replace("%"," ");
        holder.txtVw11.setText(actualPer);*/

   /*     String coveredPer=syllAbsArrayList.get(position).getSYLLABUS_COVERED_PERCENTAGE();
        coveredPer=coveredPer.replace("%"," ");
        holder.txtVw7.setText(coveredPer);*/

    }

    @Override
    public int getItemCount() {
        return syllAbsArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtVw1,txtVw2,txtVw3,txtVw4,txtVw5,txtVw6,txtVw7,txtVw8,txtVw9,txtVw10,txtVw11,txtVw12;
        public ViewHolder(View itemView) {
            super(itemView);
            Typeface customTypeOne = Typeface.createFromAsset(itemView.getContext().getAssets(), "font/Poppins-Medium.otf");

            txtVw1=(TextView)itemView.findViewById(R.id.txtVw1);
            txtVw1.setTypeface(customTypeOne);
            txtVw2=(TextView)itemView.findViewById(R.id.txtVw2);
            txtVw2.setTypeface(customTypeOne);
            txtVw3=(TextView)itemView.findViewById(R.id.txtVw3);
            txtVw3.setTypeface(customTypeOne);
            txtVw4=(TextView)itemView.findViewById(R.id.txtVw4);
            txtVw4.setTypeface(customTypeOne);
            txtVw5=(TextView)itemView.findViewById(R.id.txtVw5);
            txtVw5.setTypeface(customTypeOne);
            txtVw6=(TextView)itemView.findViewById(R.id.txtVw6);
            txtVw6.setTypeface(customTypeOne);
            txtVw7=(TextView)itemView.findViewById(R.id.txtVw7);
            txtVw7.setTypeface(customTypeOne);
            txtVw8 = (TextView)itemView.findViewById(R.id.txtVw8);
            txtVw8.setTypeface(customTypeOne);
            txtVw9 = (TextView)itemView.findViewById(R.id.txtVw9);
            txtVw9.setTypeface(customTypeOne);
            txtVw10 = (TextView)itemView.findViewById(R.id.txtVw10);
            txtVw10.setTypeface(customTypeOne);
            txtVw11 = (TextView)itemView.findViewById(R.id.txtVw11);
            txtVw11.setTypeface(customTypeOne);

        }
    }
}
