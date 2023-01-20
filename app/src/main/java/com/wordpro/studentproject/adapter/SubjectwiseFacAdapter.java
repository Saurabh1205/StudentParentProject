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
 * Created by wccs1980 on 25-04-2018.
 */

public class SubjectwiseFacAdapter extends RecyclerView.Adapter<SubjectwiseFacAdapter.ViewHolder> {

    Context context;
    ArrayList<SubjectwiseFacultyModel.DataBean> subjwiseFacArrayList;

    public SubjectwiseFacAdapter(Context context, ArrayList<SubjectwiseFacultyModel.DataBean> subjwiseFacArrayList) {
        this.context = context;
        this.subjwiseFacArrayList = subjwiseFacArrayList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.subjectwisefac_row_layout, null);
        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        String faculty=subjwiseFacArrayList.get(position).getPROF_EMP_FN_MN_SHORT();
        faculty=faculty.replace("#","");
        holder.txtFacultyName.setText(faculty);
        holder.txtSubjectName.setText(subjwiseFacArrayList.get(position).getSUBJECT_DESCRIPTION()+"  ("+subjwiseFacArrayList.get(position).getTYPE_SHORT_NAME()+")");
        holder.txtSubjCode.setText("Subject Code : "+subjwiseFacArrayList.get(position).getSUB_SHORT_DESCRIPT());
        holder.txtBatch.setText("Batch : "+subjwiseFacArrayList.get(position).getSUB_BATCH_NAME());

    }

    @Override
    public int getItemCount() {
        return subjwiseFacArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtFacultyName,txtSubjectName,txtSubjCode,txtBatch;

        public ViewHolder(View itemView) {
            super(itemView);
            Typeface customTypeOne = Typeface.createFromAsset(itemView.getContext().getAssets(), "font/Poppins-Medium.otf");

            txtFacultyName=(TextView)itemView.findViewById(R.id.txtFacultyName);
            txtFacultyName.setTypeface(customTypeOne);
            txtSubjectName=(TextView)itemView.findViewById(R.id.txtSubjectName);
            txtSubjectName.setTypeface(customTypeOne);
            txtSubjCode=(TextView)itemView.findViewById(R.id.txtSubjCode);
            txtSubjCode.setTypeface(customTypeOne);
            txtBatch=(TextView)itemView.findViewById(R.id.txtBatch);
            txtBatch.setTypeface(customTypeOne);
        }
    }
}
