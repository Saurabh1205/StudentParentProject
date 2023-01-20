package com.wordpro.studentproject.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.wordpro.studentproject.R;
import com.wordpro.studentproject.model.SlabModel;
import java.util.ArrayList;

public class SlabAdpter extends RecyclerView.Adapter<SlabAdpter.ViewHolder> {

    Context context;
    ArrayList<SlabModel.LateChgDtlsBean> slabFineArrayList;

    public SlabAdpter(Context context, ArrayList<SlabModel.LateChgDtlsBean> slabFineArrayList) {
        this.context = context;
        this.slabFineArrayList = slabFineArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.slab_row_layout, null);
        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtSrNo.setText(String.valueOf(position + 1));
        holder.txtslabDescrptn.setText(slabFineArrayList.get(position).getSLAB_DESC());
        holder.txtslabtype.setText(slabFineArrayList.get(position).getSlab_for());
        holder.txtCalcuType.setText(slabFineArrayList.get(position).getCALC_Pattern());
        holder.txtslabFrom.setText(slabFineArrayList.get(position).getSLAB_FROM());
        holder.txtslabupto.setText(slabFineArrayList.get(position).getSLAB_TO());
        holder.txtslabCharge.setText(slabFineArrayList.get(position).getSLAB_CHARGES());
    }

    @Override
    public int getItemCount() {
        return slabFineArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtSrNo, txtslabDescrptn, txtslabtype, txtCalcuType, txtslabFrom, txtslabupto, txtslabCharge;

        public ViewHolder(View itemView) {
            super(itemView);
            Typeface customTypeOne = Typeface.createFromAsset(itemView.getContext().getAssets(), "font/Poppins-Medium.otf");
            txtSrNo = (TextView) itemView.findViewById(R.id.txtSrNo);
            txtSrNo.setTypeface(customTypeOne);
            txtslabDescrptn = (TextView) itemView.findViewById(R.id.txtslabDescrptn);
            txtslabDescrptn.setTypeface(customTypeOne);
            txtslabtype = (TextView) itemView.findViewById(R.id.txtslabtype);
            txtslabtype.setTypeface(customTypeOne);
            txtCalcuType = (TextView) itemView.findViewById(R.id.txtCalcuType);
            txtCalcuType.setTypeface(customTypeOne);
            txtslabFrom = (TextView) itemView.findViewById(R.id.txtslabFrom);
            txtslabFrom.setTypeface(customTypeOne);
            txtslabupto = (TextView) itemView.findViewById(R.id.txtslabupto);
            txtslabupto.setTypeface(customTypeOne);
            txtslabCharge = (TextView) itemView.findViewById(R.id.txtslabCharge);
            txtslabCharge.setTypeface(customTypeOne);

        }
    }
}
