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

import java.util.ArrayList;

public class RulesAdpter extends RecyclerView.Adapter<RulesAdpter.ViewHolder>{


    Context context;
    ArrayList<String> rulesListArrayList;

    private RulesAdpter.OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }

    public void setOnItemClickListener(RulesAdpter.OnItemClickListener listener) {
        this.listener = listener;
    }

    public RulesAdpter(Context context, ArrayList<String> rulesListArrayList) {
        this.context = context;
        this.rulesListArrayList = rulesListArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.rules_row_layout, null);
        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.txtCount.setText(String.valueOf(position+1));
        holder.txtRules.setText(rulesListArrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return rulesListArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtCount,txtRules;

        public ViewHolder(View itemView) {
            super(itemView);

            Typeface customTypeOne = Typeface.createFromAsset(itemView.getContext().getAssets(), "font/Poppins-Medium.otf");

            txtCount=(TextView)itemView.findViewById(R.id.txtCount);
            txtCount.setTypeface(customTypeOne);
            txtRules=(TextView)itemView.findViewById(R.id.txtRules);
            txtRules.setTypeface(customTypeOne);

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
