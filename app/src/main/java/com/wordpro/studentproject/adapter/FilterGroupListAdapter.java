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
import android.widget.TextView;

import com.wordpro.studentproject.R;
import com.wordpro.studentproject.activities.NewRightEventActionActivity;
import com.wordpro.studentproject.helper.OnImageClickListener;
import com.wordpro.studentproject.model.FilterGroupNameListModel;

import java.util.List;

import static com.wordpro.studentproject.webConfig.URLEndPoints.ConstanceGroupId;
import static com.wordpro.studentproject.webConfig.URLEndPoints.ConstanceGroupName;

public class FilterGroupListAdapter extends RecyclerView.Adapter<FilterGroupListAdapter.ViewHolder>{

    Context Mcontext;
    List<FilterGroupNameListModel> filterdata;
    String ATTACHMENT_LINK;
    static String Id;

    private onRecyclerViewItemClickListener mItemClickListener;

    public void setOnItemClickListener(onRecyclerViewItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    public interface onRecyclerViewItemClickListener {
        void onItemClickListener(View view, int position);
    }

    public FilterGroupListAdapter(Context context, List<FilterGroupNameListModel> pendingJobDetails) {
        this.Mcontext =context;
        this.filterdata =pendingJobDetails;

    }


    @NonNull
    @Override
    public FilterGroupListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int postion) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.filter_group_layout, viewGroup, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int postion) {

        holder.filter_id.setText(filterdata.get(postion).getgROUPNAME());

        holder.filter_id.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                ConstanceGroupId =filterdata.get(postion).getnOTCGROUPMASTERID();
                ConstanceGroupName =filterdata.get(postion).getgROUPNAME();
                if (mItemClickListener != null) {
                    mItemClickListener.onItemClickListener(v, postion);
                }
            }
        });



    }

    @Override
    public int getItemCount() {
        return filterdata.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView filter_id;
        CardView card_view;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            filter_id =(TextView)  itemView.findViewById(R.id.filter_id);
            card_view =(CardView)  itemView.findViewById(R.id.card_view);
            Typeface type_faceHeading = Typeface.createFromAsset(Mcontext.getAssets(), "font/Poppins-Bold.ttf");
            filter_id.setTypeface(type_faceHeading);

        }

        @Override
        public void onClick(View v) {

        }
    }


}
