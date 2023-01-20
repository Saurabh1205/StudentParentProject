package com.wordpro.studentproject.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.StringRequest;
import com.wordpro.studentproject.R;
import com.wordpro.studentproject.model.SpanwiseModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static com.wordpro.studentproject.activities.NavigationActivity.BASE_URL;

/**
 * Created by wccs1980 on 08-05-2018.
 */

public class SpanListAdapter extends RecyclerView.Adapter<SpanListAdapter.ViewHolder> {

    Context context;
    ArrayList<SpanwiseModel.DataBean> spanArrayList;

    /***** Creating OnItemClickListener *****/
    // Define listener member variable
    private SpanListAdapter.OnItemClickListener listener;

    // Define the listener interface
    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }

    // Define the method that allows the parent activity or fragment to define the listener
    public void setOnItemClickListener(SpanListAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }


    public SpanListAdapter(Context context, ArrayList<SpanwiseModel.DataBean> spanArrayList) {
        this.context = context;
        this.spanArrayList = spanArrayList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.span_row_layout, null);
        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {


        String date = spanArrayList.get(position).getAttendance_Date();
        String[] value = date.split(" ");
        String val1 = value[0];

        if (val1 != null) {

            val1 = val1.replace("-", "/");
            String dt[] = val1.split("/");

            String day="",month="",year="";

            if(BASE_URL.equalsIgnoreCase("http://117.220.228.12:80/api/")){
                 month = dt[0];
                 day = dt[1];
                 year = dt[2];
            }else{
                month = dt[0];
                day = dt[1];
                year = dt[2];
            }


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

            holder.txtdate.setText(dayofMonth + " " + monthString + " " + years+"       "+dayOfTheWeek);
        }

        holder.txtTotalLec.setText(spanArrayList.get(position).getToTal_periods());
        holder.txtPresentLec.setText(spanArrayList.get(position).getPeriod_Attended());
    }

    @Override
    public int getItemCount() {
        return spanArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtdate, txtTotalLec, txtPresentLec;

        public ViewHolder(final View itemView) {
            super(itemView);

            Typeface customTypeOne = Typeface.createFromAsset(itemView.getContext().getAssets(), "font/Poppins-Medium.otf");
            txtdate = (TextView) itemView.findViewById(R.id.txtdate);
            txtdate.setTypeface(customTypeOne);
            txtTotalLec = (TextView) itemView.findViewById(R.id.txtTotalLec);
            txtTotalLec.setTypeface(customTypeOne);
            txtPresentLec = (TextView) itemView.findViewById(R.id.txtPresentLec);
            txtPresentLec.setTypeface(customTypeOne);

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
