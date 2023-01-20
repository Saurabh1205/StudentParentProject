package com.wordpro.studentproject.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wordpro.studentproject.R;
import com.wordpro.studentproject.model.SubjectwiseFacultyModel;

import java.util.ArrayList;

/**
 * Created by wccs1980 on 15-05-2018.
 */

public class SubjectwiseBatchAdpter extends RecyclerView.Adapter<SubjectwiseBatchAdpter.ViewHolder> {

    Context context;
    ArrayList<SubjectwiseFacultyModel.DataBean> subjwiseBatchArrayList;

    public SubjectwiseBatchAdpter(Context context, ArrayList<SubjectwiseFacultyModel.DataBean> subjwiseBatchArrayList) {
        this.context = context;
        this.subjwiseBatchArrayList = subjwiseBatchArrayList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.subjectwisebatch_row_layout, null);
        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.txtSubjectName.setText(subjwiseBatchArrayList.get(position).getSUBJECT_DESCRIPTION() +"  ("+ subjwiseBatchArrayList.get(position).getTYPE_SHORT_NAME()+")");
        holder.txtBatch.setText("Batch : "+subjwiseBatchArrayList.get(position).getSUB_BATCH_NAME());
        String faculty=subjwiseBatchArrayList.get(position).getPROF_EMP_FN_MN_SHORT();
        faculty=faculty.replace("#"," \n");
        holder.txtFacultyName.setText(faculty);

    }

    @Override
    public int getItemCount() {
        return subjwiseBatchArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtSubjectName,txtBatch,txtFacultyName;
        public ViewHolder(View itemView) {
            super(itemView);
            Typeface customTypeOne = Typeface.createFromAsset(itemView.getContext().getAssets(), "font/Poppins-Medium.otf");

            txtSubjectName=(TextView)itemView.findViewById(R.id.txtSubjectName);
            txtSubjectName.setTypeface(customTypeOne);
            txtBatch=(TextView)itemView.findViewById(R.id.txtBatch);
            txtBatch.setTypeface(customTypeOne);
            txtFacultyName=(TextView)itemView.findViewById(R.id.txtFacultyName);
            txtFacultyName.setTypeface(customTypeOne);

        }
    }
}
