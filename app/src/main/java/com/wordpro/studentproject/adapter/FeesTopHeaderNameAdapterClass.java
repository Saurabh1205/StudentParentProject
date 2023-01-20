package com.wordpro.studentproject.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wordpro.studentproject.R;
import com.wordpro.studentproject.model.AcademicFeeModel;
import com.wordpro.studentproject.model.DaywiseAttndModel;
import com.wordpro.studentproject.model.FeesChildModel;
import com.wordpro.studentproject.model.FeesDataList;
import com.wordpro.studentproject.model.FeesParentModel;
import com.wordpro.studentproject.webConfig.URLEndPoints;

import java.util.ArrayList;
import java.util.List;

public class FeesTopHeaderNameAdapterClass extends RecyclerView.Adapter<FeesTopHeaderNameAdapterClass.ViewHolder>{

    Context Mcontext;

    private ArrayList<FeesDataList> feesDataLists;
    private String FeesType;
    private Fees_New_StructureAdpter fees_new_structureAdpter;
    private FeesHeaderAdapterClass feesHeaderAdapterClass;
    private LinearLayoutManager layoutManager;



    public interface OnItemClickListener {
        void onItemClick(View itemView, int position, String name);
    }
    private FeesTopHeaderNameAdapterClass.OnItemClickListener mCallbacks;

    public void setOnItemClickListener(FeesTopHeaderNameAdapterClass.OnItemClickListener mCallbacks){
        this.mCallbacks = mCallbacks;
    }

    public FeesTopHeaderNameAdapterClass(Context context, ArrayList<FeesDataList> feesData) {
        this.Mcontext =context;
        this.feesDataLists =feesData;


    }

    @NonNull
    @Override
    public FeesTopHeaderNameAdapterClass.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int postion) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.fees_header_layout, viewGroup, false);
        return new FeesTopHeaderNameAdapterClass.ViewHolder(itemView);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull FeesTopHeaderNameAdapterClass.ViewHolder holder, int postion) {

        holder.txt_header.setText(feesDataLists.get(postion).getfEEHEAD());

        if (URLEndPoints.ConstanceLoginUser.equalsIgnoreCase("Student")){
            holder.card_view_Header.setBackgroundColor(Color.parseColor("#4fa4f2"));
            holder.txt_header.setTextColor(Color.parseColor("#ffffff"));
        }else {
            holder.card_view_Header.setBackgroundColor(Color.parseColor("#4CAF50"));
            holder.txt_header.setTextColor(Color.parseColor("#ffffff"));
        }



        List<FeesParentModel> feesParentModels  = new ArrayList<>();
        feesParentModels  =  feesDataLists .get(postion).getParent();

        feesHeaderAdapterClass = new FeesHeaderAdapterClass(Mcontext, feesParentModels);
        layoutManager = new LinearLayoutManager(Mcontext);

        holder.chaild_recycleView.setLayoutManager(layoutManager);
        holder.chaild_recycleView.setAdapter(feesHeaderAdapterClass);

       /* try {

        }catch (Exception e){
            e.printStackTrace();
        }*/


        //feesHeaderAdapterClass.notifyDataSetChanged();

    }

    @Override
    public int getItemCount() {
        return feesDataLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txt_Tu_Fee,txt_tu_out,txt_Received,txt_total_Tu_val,txt_Pending,txt_Adjustment,
                txt_Advance,txt_Pen_val,txt_Adj_val,txt_Adv_val;
        private TextView txt_header;
        private RecyclerView chaild_recycleView;

        private CardView card_view_Header;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //Typeface customTypeOne = Typeface.createFromAsset(itemView.getContext().getAssets(), "font/Poppins-Medium.otf");
            Typeface type_faceHeading = Typeface.createFromAsset(Mcontext.getAssets(), "font/Poppins-Bold.ttf");
            Typeface type_faceContent = Typeface.createFromAsset(Mcontext.getAssets(), "font/Poppins-Regular.ttf");


            txt_header  =(TextView) itemView. findViewById(R.id.txt_header);
            chaild_recycleView  =(RecyclerView) itemView. findViewById(R.id.chaild_recycleView);
            card_view_Header  =(CardView)itemView.findViewById(R.id.card_view_Header);
           /* card_view_tu_fee =(CardView)itemView.findViewById(R.id.card_view_tu_fee);
            txt_Tu_Fee  =(TextView) itemView. findViewById(R.id.txt_Tu_Fee);
            txt_tu_out  =(TextView) itemView. findViewById(R.id.txt_tu_out);
            txt_Received  =(TextView)  itemView.findViewById(R.id.txt_Received);
            txt_total_Tu_val  =(TextView) itemView. findViewById(R.id.txt_total_Tu_val);
            txt_Pending  =(TextView) itemView. findViewById(R.id.txt_Pending);
            txt_Adjustment  =(TextView) itemView. findViewById(R.id.txt_Adjustment);
            txt_Pen_val  =(TextView) itemView. findViewById(R.id.txt_Pen_val);
            txt_Adj_val  =(TextView)  itemView.findViewById(R.id.txt_Adj_val);*/
            txt_header.setTypeface(type_faceHeading);
           /* txt_Tu_Fee.setTypeface(type_faceHeading);
            txt_tu_out.setTypeface(type_faceHeading);
            txt_Received.setTypeface(type_faceContent);
            txt_total_Tu_val.setTypeface(type_faceHeading);
            txt_Pending.setTypeface(type_faceHeading);
            txt_Adjustment.setTypeface(type_faceHeading);
            txt_Pen_val.setTypeface(type_faceHeading);
            txt_Adj_val.setTypeface(type_faceContent);*/

        }
    }
}
