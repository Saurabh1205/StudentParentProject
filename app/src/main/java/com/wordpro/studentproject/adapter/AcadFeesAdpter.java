package com.wordpro.studentproject.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wordpro.studentproject.R;
import com.wordpro.studentproject.model.AcademicFeeModel;

import java.util.ArrayList;

/**
 * Created by wccs1980 on 28-05-2018.
 */

public class AcadFeesAdpter extends RecyclerView.Adapter<AcadFeesAdpter.ViewHolder> {

    Context context;
    ArrayList<AcademicFeeModel.DataBean> acadFeesArrayList;

    public AcadFeesAdpter(Context context, ArrayList<AcademicFeeModel.DataBean> acadFeesArrayList) {
        this.context = context;
        this.acadFeesArrayList = acadFeesArrayList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.acad_fee_layout, null);
        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.txtParticulars.setText(acadFeesArrayList.get(position).getFEE_SUB_TYPE_DESC());
        holder.txtReceivableAmt.setText(acadFeesArrayList.get(position).getRECEIVABLE_AMOUNT() +" ₹");
        holder.txtReceivedAmt.setText(acadFeesArrayList.get(position).getFEE_RECEIPT_AMOUNT() +" ₹");
        holder.txtAdjustment.setText(acadFeesArrayList.get(position).getDISCOUNT_AMOUNT() +" ₹");
        holder.txtOutstanding.setText(acadFeesArrayList.get(position).getBALANCE_AMOUNT() +" ₹");
        holder.txtAdvance.setText(acadFeesArrayList.get(position).getADVN_AMT_SUBTYPEWISE() +" ₹");

    }

    @Override
    public int getItemCount() {
        return acadFeesArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtParticulars,txtReceivableAmt,txtReceivedAmt,txtAdjustment,txtOutstanding,txtAdvance;


        public ViewHolder(View itemView) {
            super(itemView);
            Typeface customTypeOne = Typeface.createFromAsset(itemView.getContext().getAssets(), "font/Poppins-Medium.otf");

            txtParticulars=(TextView)itemView.findViewById(R.id.txtParticulars);
            txtParticulars.setTypeface(customTypeOne);
            txtReceivableAmt=(TextView)itemView.findViewById(R.id.txtReceivableAmt);
            txtReceivableAmt.setTypeface(customTypeOne);
            txtReceivedAmt=(TextView)itemView.findViewById(R.id.txtReceivedAmt);
            txtReceivedAmt.setTypeface(customTypeOne);
            txtAdjustment=(TextView)itemView.findViewById(R.id.txtAdjustment);
            txtAdjustment.setTypeface(customTypeOne);
            txtOutstanding=(TextView)itemView.findViewById(R.id.txtOutstanding);
            txtOutstanding.setTypeface(customTypeOne);
            txtAdvance=(TextView)itemView.findViewById(R.id.txtAdvance);
            txtAdvance.setTypeface(customTypeOne);

        }
    }
}
