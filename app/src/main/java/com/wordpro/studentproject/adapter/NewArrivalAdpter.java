package com.wordpro.studentproject.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wordpro.studentproject.R;
import com.wordpro.studentproject.model.NewArrvlMnthModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class NewArrivalAdpter extends RecyclerView.Adapter<NewArrivalAdpter.ViewHolder> {

    // Define listener member variable
    private NewArrivalAdpter.OnItemClickListener listener;
    // Define the method that allows the parent activity or fragment to define the listener

    // Define the listener interface
    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }

    public void setOnItemClickListener(NewArrivalAdpter.OnItemClickListener listener) {
        this.listener = listener;
    }

    Context context;
    ArrayList<NewArrvlMnthModel.LibBokNewArrAbstBean> monthList;


    public NewArrivalAdpter(Context context, ArrayList<NewArrvlMnthModel.LibBokNewArrAbstBean> monthList) {
        this.context = context;
        this.monthList = monthList;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.new_arrvl_row, null);
        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        String date = monthList.get(position).getStart_Day_Month();
        String d[] = date.split(" ");
        String splitdate = d[0];

        splitdate = splitdate.replace("-", "/");
        String dt[] = splitdate.split("/");
        String day = dt[1];
        String month = dt[0];
        String year = dt[2];

        Date dt1 = null;
        //holder.txtDate.setText(day + "/" + month + "/" + year);
        String input_date = day + "/" + month + "/" + year;
        SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
        try {
            dt1 = format1.parse(input_date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String dayOfTheWeek = (String) DateFormat.format("EEEE", dt1); // Thursday
        String dayofMonth = (String) DateFormat.format("dd", dt1); // 20
        String monthString = (String) DateFormat.format("MMM", dt1); // Jun
        String monthNumber = (String) DateFormat.format("MM", dt1); // 06
        String years = (String) DateFormat.format("yyyy", dt1); // 2013


        holder.txtMonth.setText(monthString + "  " + years);
        holder.txtCount.setText(monthList.get(position).getNo_of_Book_Title() + " ARRIVAL");

    }

    @Override
    public int getItemCount() {
        return monthList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtMonth, txtCount;

        public ViewHolder(View itemView) {
            super(itemView);
            Typeface customTypeOne = Typeface.createFromAsset(itemView.getContext().getAssets(), "font/Poppins-Medium.otf");
            txtMonth = (TextView) itemView.findViewById(R.id.txtMonth);
            txtMonth.setTypeface(customTypeOne);
            txtCount = (TextView) itemView.findViewById(R.id.txtCount);
            txtCount.setTypeface(customTypeOne);

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
