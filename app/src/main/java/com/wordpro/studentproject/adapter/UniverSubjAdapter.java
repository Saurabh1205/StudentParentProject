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
import com.wordpro.studentproject.model.UnviSubjModel;
import java.util.ArrayList;

/**
 * Created by wccs1980 on 27-04-2018.
 */

public class UniverSubjAdapter extends RecyclerView.Adapter<UniverSubjAdapter.ViewHolder> {

    Context context;
    ArrayList<UnviSubjModel.DataBean> univSubjArrayList;

    /***** Creating OnItemClickListener *****/
    // Define listener member variable
    private UniverSubjAdapter.OnItemClickListener listener;

    // Define the listener interface
    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }

    // Define the method that allows the parent activity or fragment to define the listener
    public void setOnItemClickListener(UniverSubjAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }

    public UniverSubjAdapter(Context context, ArrayList<UnviSubjModel.DataBean> univSubjArrayList) {
        this.context = context;
        this.univSubjArrayList = univSubjArrayList;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.univ_subject_list, null);
        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        if (position % 2 == 0) {
            holder.lytSubj.setBackgroundResource(R.color.colorPrimary);
            holder.lytBlock.setBackgroundResource(R.color.colorPrimary);
        } else {
            holder.lytSubj.setBackgroundResource(R.color.colorPrimaryDark);
            holder.lytBlock.setBackgroundResource(R.color.colorPrimaryDark);
        }

        holder.txtSubjName.setText(univSubjArrayList.get(position).getSUBJECT_DESCRIPTION() + "  (" + univSubjArrayList.get(position).getTYPE_SHORT_NAME() + ")");
        holder.txtUnivCode.setText(univSubjArrayList.get(position).getUNIVERSITY_CODE());
    }

    @Override
    public int getItemCount() {
        return univSubjArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtSubjName, txtUnivCode;
        LinearLayout lytSubj;
        LinearLayout lytBlock;


        public ViewHolder(final View itemView) {
            super(itemView);
            Typeface customTypeOne = Typeface.createFromAsset(itemView.getContext().getAssets(), "font/Poppins-Medium.otf");

            txtSubjName = (TextView) itemView.findViewById(R.id.txtSubjName);
            txtSubjName.setTypeface(customTypeOne);
            txtUnivCode = (TextView) itemView.findViewById(R.id.txtUnivCode);
            txtUnivCode.setTypeface(customTypeOne);
            lytSubj = (LinearLayout) itemView.findViewById(R.id.lytSubj);
            lytBlock = (LinearLayout) itemView.findViewById(R.id.lytBlock);

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
