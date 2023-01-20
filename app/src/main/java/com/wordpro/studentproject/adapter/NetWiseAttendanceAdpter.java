package com.wordpro.studentproject.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wordpro.studentproject.R;
import com.wordpro.studentproject.model.DaywiseAttndModel;
import com.wordpro.studentproject.model.NetAttendanceModel;
import com.wordpro.studentproject.model.SpanwiseModel;

import java.util.ArrayList;

public class NetWiseAttendanceAdpter extends RecyclerView.Adapter<NetWiseAttendanceAdpter.ViewHolder>{

    Context Mcontext;

    ArrayList<NetAttendanceModel.DataBean>NetArrayList;
    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }
    private NetWiseAttendanceAdpter.OnItemClickListener mCallbacks;

    public void setOnItemClickListener(NetWiseAttendanceAdpter.OnItemClickListener mCallbacks){
        this.mCallbacks = mCallbacks;
    }

    public NetWiseAttendanceAdpter(Context context, ArrayList<NetAttendanceModel.DataBean> pendingJobDetails) {
        this.Mcontext =context;
        this.NetArrayList =pendingJobDetails;
    }

    @NonNull
    @Override
    public NetWiseAttendanceAdpter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int postion) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.net_wise_all_list, viewGroup, false);
        return new ViewHolder(itemView);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int postion) {

        holder.txt_total_pre.setText(NetArrayList.get(postion).getSUBJECT_DESCRIPTION());
        holder.txt_total_pre.setText(NetArrayList.get(postion).getSUBJECT_DESCRIPTION());


        holder.txt_sub_name.setText(NetArrayList.get(postion).getSUBJECT_DESCRIPTION()+"  ("+NetArrayList.get(postion).getAPPLI_TYPE_SHORT_NAME()+")");
        holder.txt_Tm.setText(NetArrayList.get(postion).getAPPLI_TYPE_SHORT_NAME());
        //holder.txt_tolPre.setText(NetArrayList.get(postion).getNO_OF_PERIODS_PRSNT());
        holder.txt_total_pre.setText(NetArrayList.get(postion).getATTEND_PER());
       // holder.txt_total_pre2.setText("100");
        holder.txt_tolPre.setText("" +NetArrayList.get(postion).getNO_OF_PERIODS_PRSNT()+" "+"]");
        holder.txt_tolPer.setText("" +NetArrayList.get(postion).getTOTAL_NOOF_PERIODS() +" "+"]");
        holder.card_view_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Triggers click upwards to the adapter on click
                if (mCallbacks != null) {

                    if (postion != RecyclerView.NO_POSITION) {
                        mCallbacks.onItemClick(v, postion);
                    }
                }
            }
        });



    }

    @Override
    public int getItemCount() {
        return NetArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView  txt_sub_name,txt_Tm,txt_tolPre,txt_tolPer,txt_total_pre,txt_total_pre2,t,tt;
        private ImageView gallery_img;
        private CardView  card_view_sub;

        private RecyclerView even_img_recyclerView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //Typeface customTypeOne = Typeface.createFromAsset(itemView.getContext().getAssets(), "font/Poppins-Medium.otf");
            Typeface type_faceHeading = Typeface.createFromAsset(Mcontext.getAssets(), "font/Poppins-Bold.ttf");
            Typeface type_faceContent = Typeface.createFromAsset(Mcontext.getAssets(), "font/Poppins-Regular.ttf");

            txt_total_pre= (TextView) itemView.findViewById(R.id.txt_total_pre);
            txt_sub_name= (TextView)itemView.findViewById(R.id.txt_sub_name);
            txt_Tm= (TextView)itemView.findViewById(R.id.txt_Tm);
            txt_tolPre= (TextView)itemView.findViewById(R.id.txt_tolPre);
            txt_tolPer= (TextView)itemView.findViewById(R.id.txt_tolPer);
            txt_total_pre2 = (TextView)itemView.findViewById(R.id.txt_total_pre2);
            t = (TextView)itemView.findViewById(R.id.t);
            tt = (TextView)itemView.findViewById(R.id.tt);
            card_view_sub  = (CardView) itemView.findViewById(R.id.card_view_sub);

            try {
                txt_total_pre.setTypeface(type_faceHeading);
                txt_sub_name.setTypeface(type_faceHeading);
                txt_Tm.setTypeface(type_faceHeading);
                txt_tolPre.setTypeface(type_faceHeading);
                txt_tolPer.setTypeface(type_faceHeading);
                txt_total_pre2.setTypeface(type_faceHeading);
                t.setTypeface(type_faceContent);
                tt.setTypeface(type_faceContent);
            }catch (Exception e){
                e.printStackTrace();
            }


        }
    }

}
