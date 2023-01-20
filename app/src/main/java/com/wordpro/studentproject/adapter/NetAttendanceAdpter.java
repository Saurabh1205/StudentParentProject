package com.wordpro.studentproject.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wordpro.studentproject.R;
import com.wordpro.studentproject.model.NetAttendanceModel;

import java.util.ArrayList;

/**
 * Created by wccs1980 on 25-04-2018.
 */

public class NetAttendanceAdpter extends RecyclerView.Adapter<NetAttendanceAdpter.ViewHolder> {

    Context context;
    ArrayList<NetAttendanceModel.DataBean> netAttndArrayList;

    public NetAttendanceAdpter(Context context, ArrayList<NetAttendanceModel.DataBean> netAttndArrayList) {
        this.context = context;
        this.netAttndArrayList = netAttndArrayList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.net_attnd_row_layout, null);
        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.txtSubjectName.setText(netAttndArrayList.get(position).getSUBJECT_DESCRIPTION()+"  ("+netAttndArrayList.get(position).getAPPLI_TYPE_SHORT_NAME()+")");
        holder.txtPercent.setText(netAttndArrayList.get(position).getATTEND_PER());
        holder.txtStudPrsntCount.setText(netAttndArrayList.get(position).getNO_OF_PERIODS_PRSNT());
        holder.txtTotLec.setText(netAttndArrayList.get(position).getTOTAL_NOOF_PERIODS());
    }

    @Override
    public int getItemCount() {
        return netAttndArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtSubjectName,txtStudPrsntCount,txtTotLec,txtPercent;
        Typeface customTypeOne = Typeface.createFromAsset(itemView.getContext().getAssets(), "font/Poppins-Medium.otf");

        public ViewHolder(View itemView) {
            super(itemView);

            txtSubjectName=(TextView)itemView.findViewById(R.id.txtSubjectName);
            txtSubjectName.setTypeface(customTypeOne);
            txtStudPrsntCount=(TextView)itemView.findViewById(R.id.txtStudPrsntCount);
            txtStudPrsntCount.setTypeface(customTypeOne);
            txtTotLec=(TextView)itemView.findViewById(R.id.txtTotLec);
            txtTotLec.setTypeface(customTypeOne);
            txtPercent=(TextView)itemView.findViewById(R.id.txtPercent);
            txtPercent.setTypeface(customTypeOne);

        }
    }
}
