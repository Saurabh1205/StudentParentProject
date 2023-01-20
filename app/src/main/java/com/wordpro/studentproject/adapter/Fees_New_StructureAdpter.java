package com.wordpro.studentproject.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.wordpro.studentproject.R;
import com.wordpro.studentproject.model.AcademicFeeModel;
import com.wordpro.studentproject.model.DaywiseAttndModel;
import com.wordpro.studentproject.model.FeesChildModel;
import com.wordpro.studentproject.model.TeachPlanSubjModel;
import com.wordpro.studentproject.model.UnviSubjModel;

import java.util.ArrayList;
import java.util.List;

public class Fees_New_StructureAdpter extends RecyclerView.Adapter<Fees_New_StructureAdpter.ViewHolder>{

    Context Mcontext;
    public static DaywiseAttndModel daywiseAttndModel;
    public static ArrayList<AcademicFeeModel.DataBean> feesArray;
    private String FeesType;
    private List<FeesChildModel> feesChildModels ;


    public Fees_New_StructureAdpter(Context context, List<FeesChildModel> feesArrayList) {
        this.Mcontext= context;
        this.feesChildModels =feesArrayList;
    }


    @NonNull
    @Override
    public Fees_New_StructureAdpter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int postion) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.fees_recycle_layout, viewGroup, false);
        return new ViewHolder(itemView);
    }


    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int postion) {

       /* holder.card_view_tu_fee.setVisibility(View.VISIBLE);
        holder.txt_Tu_Fee.setText(feesChildModels.get(postion).getfEESUBTYPEDESC());
        holder.txt_total_Tu_val.setText(feesChildModels.get(postion).getfEERECEIPTAMOUNT());
        holder.txt_tu_out.setText(feesChildModels.get(postion).getrECEIVABLEAMOUNT());
        holder.txt_Pen_val.setText(feesChildModels.get(postion).getbALANCEAMOUNT());
        holder.txt_Adj_val.setText(feesChildModels.get(postion).getdISCOUNTAMOUNT());*/


        try{
            holder.card_view_tu_fee.setVisibility(View.VISIBLE);
            holder.txt_Tu_Fee.setText(feesChildModels.get(postion).getfEESUBTYPEDESC());
            holder.txt_total_Tu_val.setText(feesChildModels.get(postion).getfEERECEIPTAMOUNT());
            holder.txt_tu_out.setText(feesChildModels.get(postion).getrECEIVABLEAMOUNT());
            holder.txt_Pen_val.setText(feesChildModels.get(postion).getbALANCEAMOUNT());
            holder.txt_Adj_val.setText(feesChildModels.get(postion).getdISCOUNTAMOUNT());
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return feesChildModels.size();
    }


public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView  txt_Tu_Fee,txt_tu_out,txt_Received,txt_total_Tu_val,txt_Pending,txt_Adjustment,
                txt_Advance,txt_Pen_val,txt_Adj_val,txt_Adv_val;

        private CardView  card_view_tu_fee;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //Typeface customTypeOne = Typeface.createFromAsset(itemView.getContext().getAssets(), "font/Poppins-Medium.otf");
            Typeface type_faceHeading = Typeface.createFromAsset(Mcontext.getAssets(), "font/Poppins-Bold.ttf");
            Typeface type_faceContent = Typeface.createFromAsset(Mcontext.getAssets(), "font/Poppins-Regular.ttf");

            card_view_tu_fee =(CardView)itemView.findViewById(R.id.card_view_tu_fee);
            txt_Tu_Fee  =(TextView) itemView. findViewById(R.id.txt_Tu_Fee);
            txt_tu_out  =(TextView) itemView. findViewById(R.id.txt_tu_out);
            txt_Received  =(TextView)  itemView.findViewById(R.id.txt_Received);
            txt_total_Tu_val  =(TextView) itemView. findViewById(R.id.txt_total_Tu_val);
            txt_Pending  =(TextView) itemView. findViewById(R.id.txt_Pending);
            txt_Adjustment  =(TextView) itemView. findViewById(R.id.txt_Adjustment);
            txt_Pen_val  =(TextView) itemView. findViewById(R.id.txt_Pen_val);
            txt_Adj_val  =(TextView)  itemView.findViewById(R.id.txt_Adj_val);

            txt_Tu_Fee.setTypeface(type_faceHeading);
            txt_tu_out.setTypeface(type_faceHeading);
            txt_Received.setTypeface(type_faceContent);
            txt_total_Tu_val.setTypeface(type_faceHeading);
            txt_Pending.setTypeface(type_faceHeading);
            txt_Adjustment.setTypeface(type_faceHeading);
            txt_Pen_val.setTypeface(type_faceHeading);
            txt_Adj_val.setTypeface(type_faceContent);

        }
    }

}
