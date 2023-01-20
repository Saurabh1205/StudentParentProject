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
import com.wordpro.studentproject.model.SubjectwiseFacultyModel;

import java.util.ArrayList;

/**
 * Created by wccs1980 on 06-08-2018.
 */

public class SubjectwiseFacTypeAdapter extends RecyclerView.Adapter<SubjectwiseFacTypeAdapter.ViewHolder> {

    Context context;
    ArrayList<SubjectwiseFacultyModel.DataBean> subjectwiseFacTypeArrayList;
    /***** Creating OnItemClickListener *****/

    // Define listener member variable
    private SubjectwiseFacTypeAdapter.OnItemClickListener listener;


    // Define the listener interface
    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }

    // Define the method that allows the parent activity or fragment to define the listener
    public void setOnItemClickListener(SubjectwiseFacTypeAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }

    public SubjectwiseFacTypeAdapter(Context context, ArrayList<SubjectwiseFacultyModel.DataBean> subjectwiseFacTypeArrayList) {
        this.context = context;
        this.subjectwiseFacTypeArrayList = subjectwiseFacTypeArrayList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.subject_list, null);
        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        if(position%2==0){
            holder.lytSubj.setBackgroundResource(R.color.colorPrimary);
            holder.lytBlock.setBackgroundResource(R.color.colorPrimary);
        }else{
            holder.lytSubj.setBackgroundResource(R.color.colorPrimaryDark);
            holder.lytBlock.setBackgroundResource(R.color.colorPrimaryDark);
        }

        holder.txtSubjName.setText(subjectwiseFacTypeArrayList.get(position).getSUBJECT_DESCRIPTION() +" ("+subjectwiseFacTypeArrayList.get(position).getTYPE_SHORT_NAME()+") ");
        holder.txtFacName.setText(subjectwiseFacTypeArrayList.get(position).getPROF_EMP_FN_MN_SHORT());
    }

    @Override
    public int getItemCount() {
        return subjectwiseFacTypeArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout lytSubj;
        LinearLayout lytBlock;
        TextView txtSubjName;
        TextView txtFacName;

        public ViewHolder(final View itemView) {
            super(itemView);

            Typeface customTypeOne = Typeface.createFromAsset(itemView.getContext().getAssets(), "font/Poppins-Medium.otf");

            lytSubj=(LinearLayout)itemView.findViewById(R.id.lytSubj);
            lytBlock=(LinearLayout)itemView.findViewById(R.id.lytBlock);
            txtSubjName=(TextView)itemView.findViewById(R.id.txtSubjName);
            txtSubjName.setTypeface(customTypeOne);
            txtFacName=(TextView)itemView.findViewById(R.id.txtFacName);
            txtFacName.setTypeface(customTypeOne);

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
