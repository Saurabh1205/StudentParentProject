package com.wordpro.studentproject.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
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
import com.wordpro.studentproject.model.FeesMainDataMode;
import com.wordpro.studentproject.model.FeesParentModel;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class FeesHeaderAdapterClass  extends RecyclerView.Adapter<FeesHeaderAdapterClass.ViewHolder>{

    Context Mcontext;

    private List<FeesParentModel> feesParentModels;

    private String FeesType;
    private Fees_New_StructureAdpter fees_new_structureAdpter;



    public interface OnItemClickListener {
        void onItemClick(View itemView, int position, String name);
    }
    private FeesHeaderAdapterClass.OnItemClickListener mCallbacks;

    public void setOnItemClickListener(FeesHeaderAdapterClass.OnItemClickListener mCallbacks){
        this.mCallbacks = mCallbacks;
    }

    public FeesHeaderAdapterClass(Context context,List<FeesParentModel> feesData) {
        this.Mcontext =context;
        this.feesParentModels =feesData;


    }

    @NonNull
    @Override
    public FeesHeaderAdapterClass.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int postion) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.fees_header_layout, viewGroup, false);
        return new FeesHeaderAdapterClass.ViewHolder(itemView);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull FeesHeaderAdapterClass.ViewHolder holder, int postion) {

        String FeesTypes = feesParentModels.get(postion).getfEEINSTALLMENTNO();
        String FeeName="";

        if (FeesTypes.equalsIgnoreCase("1")){
            FeeName ="First Installment";
        }else if (FeesTypes.equalsIgnoreCase("2")){
            FeeName ="Second Installment";
        }else if (FeesTypes.equalsIgnoreCase("3")){
            FeeName ="Third Installment";
        }else if (FeesTypes.equalsIgnoreCase("4")){
            FeeName ="Fourth Installment";
        }else if (FeesTypes.equalsIgnoreCase("5")){
            FeeName ="Five Installment";
        }else if (FeesTypes.equals("")) {
            FeeName = "Fee Installment No :";
        }
       /* holder.txt_header.setText(FeeName+" "+feesParentModels.get(postion).getfEEINSTALLMENTNO());


        List<FeesChildModel> feesChildModels  =new ArrayList<>();
        feesChildModels  =  feesParentModels .get(postion).getChild();

        fees_new_structureAdpter = new Fees_New_StructureAdpter(Mcontext, feesChildModels);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(Mcontext, 2);
        holder.chaild_recycleView.setLayoutManager(mLayoutManager);
        holder.chaild_recycleView.setAdapter(fees_new_structureAdpter);*/

        try {
            holder.txt_header.setText(FeeName+" "+feesParentModels.get(postion).getfEEINSTALLMENTNO());

            List<FeesChildModel> feesChildModels  =new ArrayList<>();
            feesChildModels  =  feesParentModels .get(postion).getChild();

            fees_new_structureAdpter = new Fees_New_StructureAdpter(Mcontext, feesChildModels);
            RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(Mcontext, 2);
            holder.chaild_recycleView.setLayoutManager(mLayoutManager);
            holder.chaild_recycleView.setAdapter(fees_new_structureAdpter);
        }catch (Exception e){
            e.printStackTrace();
        }

        //fees_new_structureAdpter.notifyDataSetChanged();

    }

    @Override
    public int getItemCount() {
        return feesParentModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txt_Tu_Fee,txt_tu_out,txt_Received,txt_total_Tu_val,txt_Pending,txt_Adjustment,
                txt_Advance,txt_Pen_val,txt_Adj_val,txt_Adv_val;
        private TextView txt_header;
        private RecyclerView chaild_recycleView;

        private CardView card_view_tu_fee;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //Typeface customTypeOne = Typeface.createFromAsset(itemView.getContext().getAssets(), "font/Poppins-Medium.otf");
            Typeface type_faceHeading = Typeface.createFromAsset(Mcontext.getAssets(), "font/Poppins-Bold.ttf");
            Typeface type_faceContent = Typeface.createFromAsset(Mcontext.getAssets(), "font/Poppins-Regular.ttf");


            txt_header  =(TextView) itemView. findViewById(R.id.txt_header);
            chaild_recycleView  =(RecyclerView) itemView. findViewById(R.id.chaild_recycleView);

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
