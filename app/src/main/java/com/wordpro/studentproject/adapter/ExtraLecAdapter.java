package com.wordpro.studentproject.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wordpro.studentproject.R;
import com.wordpro.studentproject.model.ExtraLecModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by wccs1980 on 14-05-2018.
 */

public class ExtraLecAdapter extends RecyclerView.Adapter<ExtraLecAdapter.ViewHolder> {

    Context context;
    ArrayList<ExtraLecModel.DataBean> extraLecArrayList;

    public ExtraLecAdapter(Context context, ArrayList<ExtraLecModel.DataBean> extraLecArrayList) {
        this.context = context;
        this.extraLecArrayList = extraLecArrayList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.extra_lec_row_layout, null);
        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        if(position%2==0){
            holder.extraLecLayout.setBackgroundResource(R.color.colorPrimaryDark);
          /*  holder.txtExtDay.setTextColor(Color.parseColor("#FFFFFF"));
            holder.txtExtDate.setTextColor(Color.parseColor("#FFFFFF"));*/
        }else{
            holder.extraLecLayout.setBackgroundResource(R.color.colorPrimary);
            holder.txtExtDay.setTextColor(Color.parseColor("#FFFFFF"));
            holder.txtExtDate.setTextColor(Color.parseColor("#FFFFFF"));
            holder.imgCalender.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.calendar));


        }

        holder.txtBatch.setText("BATCH : "+extraLecArrayList.get(position).getBATCH_NAME());
        holder.txtEdTime.setText(extraLecArrayList.get(position).getUPTO_TIME());
        holder.txtStTime.setText(extraLecArrayList.get(position).getFROM_TIME());
        holder.txtExtraSubj.setText("Subject : "+extraLecArrayList.get(position).getSUBJECT_DESCRIPTION());
        holder.txtRoomAlloted.setText("Room : "+extraLecArrayList.get(position).getROOM_NAME());
        holder.txtFaculty.setText("Faculty : "+extraLecArrayList.get(position).getPROF_EMP_FN_MN_SHORT());
        holder.txtExtraBrnch.setText(extraLecArrayList.get(position).getBRANCH_STANDARD_CODE());
        String PERIOD_DATE=extraLecArrayList.get(position).getPERIOD_DATE();


        PERIOD_DATE = PERIOD_DATE.replace("-", "/");
        String dt[] = PERIOD_DATE.split("/");
        String day = dt[0];
        String  month= dt[1];
        String year = dt[2];

        Date dt1 = null;
        //holder.txtDate.setText(day + "/" + month + "/" + year);
        String input_date=day + "/" + month + "/" + year;
        SimpleDateFormat format1=new SimpleDateFormat("dd/MM/yyyy");
        try {
            dt1=format1.parse(input_date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String dayOfTheWeek = (String) DateFormat.format("EEEE", dt1); // Thursday
        String dayofMonth          = (String) DateFormat.format("dd",   dt1); // 20
        String monthString  = (String) DateFormat.format("MMM",  dt1); // Jun
        String monthNumber  = (String) DateFormat.format("MM",   dt1); // 06
        String years         = (String) DateFormat.format("yyyy", dt1); // 2013

        holder.txtExtDay.setText(dayOfTheWeek);
        holder.txtExtDate.setText(dayofMonth+" " + monthString+" "+years);
    }

    @Override
    public int getItemCount() {
        return extraLecArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout extraLecLayout;
        TextView txtExtDay,txtExtDate,txtExtraSubj,txtExtraBrnch,txtRoomAlloted,txtBatch,txtFaculty,txtStTime,txtEdTime;
        ImageView imgCalender;


        public ViewHolder(View itemView) {
            super(itemView);

            Typeface customTypeOne = Typeface.createFromAsset(itemView.getContext().getAssets(), "font/Poppins-Medium.otf");
            extraLecLayout=(LinearLayout)itemView.findViewById(R.id.extraLecLayout);
            txtExtDay=(TextView)itemView.findViewById(R.id.txtExtDay);
            txtExtDay.setTypeface(customTypeOne);
            txtExtDate=(TextView)itemView.findViewById(R.id.txtExtDate);
            txtExtDate.setTypeface(customTypeOne);
            txtExtraSubj=(TextView)itemView.findViewById(R.id.txtExtraSubj);
            txtExtraSubj.setTypeface(customTypeOne);
            txtExtraBrnch=(TextView)itemView.findViewById(R.id.txtExtraBrnch);
            txtExtraBrnch.setTypeface(customTypeOne);
            txtRoomAlloted=(TextView)itemView.findViewById(R.id.txtRoomAlloted);
            txtRoomAlloted.setTypeface(customTypeOne);
            txtBatch=(TextView)itemView.findViewById(R.id.txtBatch);
            txtBatch.setTypeface(customTypeOne);
            txtFaculty=(TextView)itemView.findViewById(R.id.txtFaculty);
            txtFaculty.setTypeface(customTypeOne);
            txtStTime=(TextView)itemView.findViewById(R.id.txtStTime);
            txtStTime.setTypeface(customTypeOne);
            txtEdTime=(TextView)itemView.findViewById(R.id.txtEdTime);
            txtEdTime.setTypeface(customTypeOne);
            imgCalender=(ImageView)itemView.findViewById(R.id.imgCalender);

        }
    }
}
